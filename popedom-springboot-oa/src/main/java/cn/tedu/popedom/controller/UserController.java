package cn.tedu.popedom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.popedom.pojo.User;
import cn.tedu.popedom.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("popedom/addUser")
	public String registUser(User user) {
		int result = userService.registUser(user);
		if (result == 1) {
			return "success";
		} else {
			return "";
		}

	}

	// 返回String类型fatherId
	@RequestMapping("popedom/selectMajor/{major}")
	// String major
	public String getMajorId(@PathVariable("major") String major) {
		return userService.getMajorId(major);
	}

	// 返回String类型roleId
	@RequestMapping("popedom/selectRoleId/{roleName}")
	public String getRoleId(@PathVariable("roleName") String roleName) {
		return userService.getRoleId(roleName);
	}
	
	@RequestMapping("popedom/selectUsers/{roleName}")
	public List<String> getUsers(@PathVariable("roleName") String roleName){
		return userService.getUsers(roleName);		
	}
}
