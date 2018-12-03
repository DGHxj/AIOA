package cn.tedu.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import cn.tedu.common.service.HttpClientService;
import cn.tedu.common.service.RedisClusterService;
import cn.tedu.common.util.ObjectUtil;
import cn.tedu.web.pojo.Task;
import cn.tedu.web.pojo.User;

@Service
public class TaskService {
	@Autowired
	HttpClientService client;
	@Autowired
	private RedisClusterService redisClusterService;
	
	
	public String submitTask(Task task) throws Exception {
		
		String url = "http://task.oa.com/task/sendTask";
		try {
			String json = ObjectUtil.mapper.writeValueAsString(task);
			String flag = client.doPostJson(url, json);
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "任务发布失败";
	}

	public List<Task> showTasks(String userId, String _status) {	
		
		
		String url = "http://task.oa.com/task/showTasks/"+userId+"/"+_status;
		List<Task> list = null;
		List<String> list_username = new ArrayList<String>();
		try {
			String json = client.doGet(url);
			JsonNode data = ObjectUtil.mapper.readTree(json);
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class,Task.class));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public String queryTotal(String userId, String status){
		String url = null;
		if("5".equals(status)){
			 url ="http://task.oa.com/task/queryPostTotal/"+userId;
		}else
			url = "http://task.oa.com/task/queryGetTotal/"+status+"/"+userId;
		try {
			String res = client.doGet(url);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "0";
	}

	public String updateTask(Task task) {
		String url = "http://task.oa.com/task/updateTask";
		try {
			String json = ObjectUtil.mapper.writeValueAsString(task);
			String msg = client.doPostJson(url, json);
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String receiveTask(String taskId, String userId) {
		String url = "http://task.oa.com/task/receiveTask/"+taskId+"/"+userId;
		String flag;
		try {
			flag = client.doGet(url);
			int _flag = Integer.parseInt(flag);
			if(_flag == 1){
				return "您的任务接受成功，加油！";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "抱歉，您的任务接收失败";
	}

	public String deleteTask(String taskId,String userId) {
		String url = "http://task.oa.com/task/deleteTask/"+taskId+"/"+userId;
		try {
			String flag = client.doGet(url);
			if("1".equals(flag)){
				return "任务删除成功";
			}else{
				return "任务删除失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "任务删除失败";
		}
	}

	public String completeTask(String taskId, String userId) {
		String url = "http://task.oa.com/task/submitTask/"+taskId+"/"+userId;
		try {
			String msg = client.doGet(url);
			if("1".equals(msg)){
				return "恭喜您，你的任务已经完成了提交";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "抱歉，您的任务提交失败";
	}

	public String refuseTask(String taskId, String userId) {
		String url = "http://task.oa.com/task/refuseTask/"+taskId+"/"+userId;
		try {
			String flag = client.doGet(url);
			if("1".equals(flag)){
				return "任务编号为"+taskId+"的任务已经被您拒绝";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "您对上司的拒绝请求失败了";
	}

	public List<User> getUsers(String userId) throws Exception {
		
		String url = "http://task.oa.com/user/getUsers/"+userId;
		String json = client.doGet(url);
		if(!StringUtils.isNotEmpty(json)){
			return new ArrayList<User>();
		}
		JsonNode data = ObjectUtil.mapper.readTree(json);
		List<User> list = ObjectUtil.mapper.readValue(data.traverse(),
				ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class,User.class));
		return list;
	}

	public List<Task> searchTask(String search) throws Exception {
		String url = "http://task.oa.com/task/searchTask/"+search;
		String json = client.doGet(url);
		if(json==null)
			return new ArrayList();
		JsonNode data = ObjectUtil.mapper.readTree(json);
		List<Task> list = ObjectUtil.mapper.readValue(data.traverse(),ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class,Task.class));
		return list;
	}





}
