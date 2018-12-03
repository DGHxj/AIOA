package cn.tedu.task.mapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import cn.tedu.task.pojo.Task;
import cn.tedu.task.pojo.User;

public interface TaskMapper {

	public int insertTask(Task task);

	public int updateTask(Task task);

	public int deleteTask(String taskId);

	public Task queryTaskById(String taskId);

	public List<Task> queryPostTasks(Map<String, String> map);

	public List<Task> queryMyTasks(Map<String, String> map);

	public int updateTaskStatusAndFinishTime(Task task);

	public int updateTaskStatus(Task task);

	public int queryGetTotal(Map<String, String> map);

	public int queryPostTotal(Map<String, String> map);

	public List<User> queryUsers(User user);

	public User queryUserById(User user);

}
