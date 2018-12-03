package cn.tedu.task.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.tedu.common.service.RedisClusterService;
import cn.tedu.common.util.ObjectUtil;
import cn.tedu.task.mapper.TaskMapper;
import cn.tedu.task.pojo.Task;
import cn.tedu.task.pojo.User;

@Service
public class TaskService {

	@Autowired
	private TaskMapper taskMapper;
	@Autowired
	private RedisClusterService redisClusterService;
	@Autowired
	private TransportClient client;
	
	public int insertTask(Task task) {
		return taskMapper.insertTask(task);
	}

	public List<Task> queryTasks(String userId, String status) throws JsonProcessingException {
		Map<String,String> map = new HashMap<String,String>();
		List<Task> list = null;
		map.put("taskStatus",status);
		map.put("userId",userId);
		if("5".equals(status)){
			list = taskMapper.queryPostTasks(map);
			redisClusterService.set(userId+"|"+status, ObjectUtil.mapper.writeValueAsString(list));
		}else{
			list = taskMapper.queryMyTasks(map);
			redisClusterService.set(userId+"|"+status, ObjectUtil.mapper.writeValueAsString(list));
		}
		return list;
	}

	public String updateTask(Task task) throws JsonProcessingException {
		int flag = taskMapper.updateTask(task);
		if(flag==1){
			return "任务修改成功";
		}
		return "任务修改失败";
	}

	public int deleteTask(String taskId,String userId) throws JsonProcessingException {
		Task task = taskMapper.queryTaskById(taskId);
		int status = task.getTaskStatus();		
		int flag = taskMapper.deleteTask(taskId);
		if(flag==1){
			Map<String,String> map = new HashMap<String,String>();
			map.put("userId",userId);
			map.put("taskStatus",status+"");
			List<Task> list = null;
			if(status==5){
				list = taskMapper.queryPostTasks(map);
			}else{
				list = taskMapper.queryMyTasks(map);
			}
			redisClusterService.set(userId+"|"+status,ObjectUtil.mapper.writeValueAsString(list));
		}
		return flag;
	}

	public Task queryTaskById(String taskId) throws JsonProcessingException {
		Task task = taskMapper.queryTaskById(taskId);
		redisClusterService.set(task.getTaskId(),ObjectUtil.mapper.writeValueAsString(task));
		return task;
	}


	public int refuseTask(String taskId, String userId) throws JsonProcessingException {
		Task task = taskMapper.queryTaskById(taskId);
		int status = task.getTaskStatus();
		task.setTaskStatus(3);
		int flag = taskMapper.updateTaskStatus(task);
		if(flag==1){
			saveRedis(3, status, userId);
		}
		return flag;
	}

	public int receiveTask(String taskId, String userId) throws JsonProcessingException {
		Task task = taskMapper.queryTaskById(taskId);
		int status = task.getTaskStatus();
		task.setTaskStatus(1);
		int flag = taskMapper.updateTaskStatus(task);
		if(flag==1){
			saveRedis(1, status, userId);
		}
		return flag;
	}

	public int submitTask(String taskId, String taskFinishTime, String userId) throws JsonProcessingException {
		Task task = taskMapper.queryTaskById(taskId);
		int status = task.getTaskStatus();
		task.setTaskFinishTime(taskFinishTime);
		int flag = taskMapper.updateTaskStatusAndFinishTime(task);
		if(flag==1){
			saveRedis(4, status, userId);
		}
		return flag;
	}

	public int queryGetTotal(String userId, String taskStatus) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("taskStatus",taskStatus);
		int count = taskMapper.queryGetTotal(map);
		return count;
	}

	public int queryPostTotal(String userId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId",userId);
		int count = taskMapper.queryPostTotal(map);
		return count;
	}

	public List<User> queryUsers(String userId) throws JsonProcessingException {
		User user = new User();
		user.setUserId(userId);
		List<User> list = taskMapper.queryUsers(user);
		return list;
	}

	public void saveRedis(int after_status,int before_status,String userId) throws JsonProcessingException{
		Map<String,String> map_this = new HashMap<String,String>();
		map_this.put("userId",userId);
		map_this.put("taskStatus",after_status+"");
		List<Task> list_3=taskMapper.queryMyTasks(map_this);
		redisClusterService.set(userId+"|"+after_status, ObjectUtil.mapper.writeValueAsString(list_3));

		List<Task> list_status = null;
		Map<String,String> map_status = new HashMap<String,String>();
		map_status.put("taskStatus",before_status+"");
		map_status.put("userId",userId);
		if(before_status==5){
			list_status = taskMapper.queryPostTasks(map_status);
		}else{
			list_status = taskMapper.queryMyTasks(map_status);
		}
		redisClusterService.set(userId+"|"+before_status,ObjectUtil.mapper.writeValueAsString(list_status));
	}

	public User queryUserById(String userId) {
		User user = new User();
		user.setUserId(userId);
		User user2 = taskMapper.queryUserById(user);
		return user2;
	}

	public List<Task> searchTask(String search) {
		//封装查询对象query
		MatchQueryBuilder query = QueryBuilders.matchQuery("task_content", search)
				.operator(Operator.OR);//查询结果必须包含条件中的所有分词
		//客户端调用查询对象获取查询结果
		// page rows
		int page=1;
		int rows=5;
		int start= (page-1)*rows;
		SearchResponse response = client.prepareSearch("oa").
				setQuery(query).setFrom(start).setSize(rows).get();
		//获取响应结果中的数据,hits中
		SearchHits hits = response.getHits();
//		System.out.println("共搜索到:"+hits.totalHits);
		List<Task> list = new ArrayList<Task>();
		for (SearchHit hit : hits) {
			Task task = new Task();
			task.setTaskContent(hit.getSource().get("task_content").toString());
			task.setTaskCreateTime(hit.getSource().get("task_create_time").toString());
			task.setTaskEndTime(hit.getSource().get("task_end_time").toString());
			task.setTaskGetId(hit.getSource().get("task_get_id").toString());
			task.setTaskId(hit.getSource().get("task_id").toString());
			list.add(task);
			System.out.println(task.toString());
			
		}
		return list;
	}


}
