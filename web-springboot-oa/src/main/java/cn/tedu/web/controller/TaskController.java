package cn.tedu.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.web.pojo.Task;
import cn.tedu.web.pojo.User;
import cn.tedu.web.service.TaskService;



@Controller
public class TaskController {
	@Autowired
	private TaskService taskService;

	/**
	 * 发布任务给指定负责人
	 * @param 从页面接收数据创建对象task  页面的请求req
	 * @return 发布成功跳转到我发布的任务显示栏	失败则跳转到发布任务
	 * @throws Exception 
	 * */
	@RequestMapping("/task/sendTask")
	public String sendTask(Task task ,String taskEndTime, HttpServletRequest req,Model model) throws Exception{
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String userId =user.getUserId(); 
		task.setTaskPostId(userId);
		int flag = 0;		

		task.setTaskEndTime(taskEndTime);
		if(task.getTaskGetId()==null){

			model.addAttribute("msg","请指定任务负责人");
		}
		if(task.getTaskEndTime()==null){
			model.addAttribute("msg","截止时间不能为空");
			List<User> list = taskService.getUsers(userId);
			model.addAttribute("userList",list);
			return "task-releaseform";
		}
		if(task.getTaskContent()==null){
			model.addAttribute("msg", "任务内容不能为空");
		}
		if(task.getTaskStatus()>0 && task.getTaskStatus()<5){
			String msg = taskService.updateTask(task);
			if("任务修改成功".equals(msg)){
				flag = 1;
			}else
				model.addAttribute("msg",msg);
		}else{
			String msg = taskService.submitTask(task);
			model.addAttribute("msg",msg);
			if(msg!=null && msg!=""){
				if(msg.contains("成功")){
					flag = 1;
				}
			}
		}
		if(flag==1){
			getPage(userId,"5", model, "");
			return "task-released";
		}
		List<User> list = taskService.getUsers(userId);
		model.addAttribute("userList",list);
		return "task-releaseform";

	}
	/**
	 * 根据点击的显示栏来显示当前栏应该显示的任务
	 * 
	 * @param	无
	 * 
	 * @return 跳转至相应的页面
	 * @throws Exception 
	 * 
	 */
	@RequestMapping("/task/showTasks/{_status}")
	public String showTasks(@PathVariable("_status") String _status,HttpServletRequest req,Model model) throws Exception{
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId();

		getPage(userId, _status, model, "");
		int status = Integer.parseInt(_status);
		switch (status) {
		case 1:
			return "task-uncomplete";
		case 2:
			return "task-unaccepted";
		case 3:
			return "task-refused";
		case 4:
			return "task-completed";
		case 5:
			return "task-released";
		}
		return "task-released";
	}
	/**
	 * 接收任务
	 * 
	 * @param	任务id taskId
	 * 
	 * @return	跳转待接收页面
	 * */
	@RequestMapping("/task/receiveTask/{taskId}")
	public String receiveTask(@PathVariable("taskId") String taskId,Model model,HttpServletRequest req){
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");	
		String userId = user.getUserId();


		String msg = taskService.receiveTask(taskId,userId);
		getPage(userId, "2", model, msg);
		return "task-unaccepted";
	}

	@RequestMapping("/task/deleteTask/{taskId}/{taskStatus}")
	public String deleteTask(@PathVariable("taskId") String taskId,@PathVariable("taskStatus") String taskStatus,HttpServletRequest req,Model model){
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId();


		if(!("2".equals(taskStatus))){
			if(!("3".equals(taskStatus))){
				getPage(userId, "5", model, "抱歉，您的任务已经有人接受了，无法删除");
				return "task-released";
			}
		}
		String flag = taskService.deleteTask(taskId,userId);
		getPage(userId,5+"", model, flag);		
		return  "task-released";
	}

	@RequestMapping("/task/submitTask/{taskId}")
	public String submitTask(@PathVariable("taskId") String taskId,HttpServletRequest req,Model model){
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId(); 
		String msg = taskService.completeTask(taskId,userId);
		getPage(userId, "1", model, msg);
		return "task-uncomplete";

	}
	@RequestMapping("/task/refuseTask/{taskId}")
	public String refuseTask(@PathVariable("taskId") String taskId,HttpServletRequest req,Model model){
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId(); 
		String msg = taskService.refuseTask(taskId,userId);
		getPage(userId, "2", model, msg);

		return "task-unaccepted";
	}

	@RequestMapping("/task/showUsers")
	public String showUsers(HttpServletRequest req,Model model) throws Exception{
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId(); 
		List<User> list = taskService.getUsers(userId);
		model.addAttribute("userList",list);
		return "task-releaseform";

	}
	@RequestMapping("/task/changeTask")
	public String changeTask(Task task,Model model,HttpServletRequest req ) throws Exception{
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId();
		List<User> list = taskService.getUsers(userId);
		model.addAttribute("userList", list);
		return "task-releaseform";
	}
	@RequestMapping("/task/searchTask")
	public String searchTask(@RequestBody String search,Model model) throws Exception{
		List<Task> list = taskService.searchTask(search);
		model.addAttribute("taskList",list);
		return "task-searchPage";
	}





	public void getPage(String userId,String status,Model model,String msg){
		model.addAttribute("msg",msg);
		List<Task> list = taskService.showTasks(userId,status);
		String total = taskService.queryTotal(userId,status);
		model.addAttribute("taskList",list);
		model.addAttribute("taskTotal",total);
	}



}
