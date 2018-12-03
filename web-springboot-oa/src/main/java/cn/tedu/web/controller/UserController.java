package cn.tedu.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.common.util.MD5Util;
import cn.tedu.web.pojo.RolePopedom;
import cn.tedu.web.pojo.User;
import cn.tedu.web.service.PopedomService;
import cn.tedu.web.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PopedomService popedomService;
	
	@RequestMapping("/user/login")
	public String login(String userName,String userPassword,HttpServletRequest req) throws Exception{
		
		//表单验证
		
		//将密码进行MD5加密
		userPassword = MD5Util.md5(userPassword);
		//调用服务层
		User user = userService.login(userName, userPassword);
		if(user != null){ //登录成功
			Integer roleId = user.getRoleId();
			//根据角色id查询角色大权限
			Map<String,List<RolePopedom>> map = popedomService.selectBigRolePope(roleId);
			req.getSession().setAttribute("map", map);
			req.getSession().setAttribute("user", user);			
		}
		return "redirect:/manage";		
	}
	
	// 注册成员
	@RequestMapping("/user/addUser")
	public String addUser(User user, String major, Model model,String roleName) throws Exception {
		String result = popedomService.addUser(user, major,roleName);
		if ("success".equals(result)) {
			model.addAttribute("result", "添加成功");
			return "forward:/popedom/rolePopeList";
		} else {
			model.addAttribute("result", "添加失败");
			return "forward:/popedom/rolePopeList";
		}
	}
	
	@RequestMapping("/user_ajax/selectMajor")
	@ResponseBody
	public List<String> selectMajor(String roleName) throws Exception{
		List<String> uList = popedomService.selectMajor(roleName);
		return uList;
	} 

	@RequestMapping("/user/logout")
	public String logout(HttpSession session){
		//清空session中的数据
		session.removeAttribute("user");
		return "redirect:/mamage";
	}

}
