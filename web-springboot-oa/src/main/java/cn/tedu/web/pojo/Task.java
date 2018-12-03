package cn.tedu.web.pojo;

import java.io.Serializable;

public class Task implements Serializable{
	/*
	 *   `task_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `task_post_id` bigint(20) NOT NULL COMMENT '任务发布者id',
  `task_get_id` bigint(20) NOT NULL COMMENT '任务接收者id',
  `task_content` text NOT NULL COMMENT '任务内容',
  `task_create_time` date NOT NULL COMMENT '任务创建时间',
  `task_end_time` date NOT NULL COMMENT '任务截止时间',
  `task_finish_time` date NOT NULL COMMENT '任务完成时间',
  `task_status` int(11) NOT NULL COMMENT '1:表示待完成，2：表示待接收，3：表示已拒绝，4：表示已完成',
	 * 
	 * */
	//taskId=1&taskPostId=2&taskGetId=123456&taskContent=嘎嘎嘎嘎嘎&taskEndTime=2028-10-10
	private String taskId;
	private String taskPostId;
	private String taskGetId;	
	private String taskContent;
	private String taskCreateTime;
	private String taskEndTime;
	private String taskFinishTime;
	private int taskStatus;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskPostId() {
		return taskPostId;
	}
	public void setTaskPostId(String taskPostId) {
		this.taskPostId = taskPostId;
	}
	public String getTaskGetId() {
		return taskGetId;
	}
	public void setTaskGetId(String taskGetId) {
		this.taskGetId = taskGetId;
	}
	public String getTaskContent() {
		return taskContent;
	}
	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	
	public String getTaskCreateTime() {
		return taskCreateTime;
	}
	public void setTaskCreateTime(String taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}
	public String getTaskEndTime() {
		return taskEndTime;
	}
	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = taskEndTime;
	}
	public String getTaskFinishTime() {
		return taskFinishTime;
	}
	public void setTaskFinishTime(String taskFinishTime) {
		this.taskFinishTime = taskFinishTime;
	}
	public int getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskPostId=" + taskPostId + ", taskGetId=" + taskGetId + ", taskContent="
				+ taskContent + ", taskCreateTime=" + taskCreateTime + ", taskEndTime=" + taskEndTime
				+ ", taskFinishTime=" + taskFinishTime + ", taskStatus=" + taskStatus + "]";
	}
	
}
