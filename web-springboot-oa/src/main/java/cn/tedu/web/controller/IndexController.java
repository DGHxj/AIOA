package cn.tedu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String goLogin(){
		return "login";
	}
	
	@RequestMapping("/manage")
	public String goIndex(){
		return "manage";
	}
	
	/**
	 * 添加通用映射地址
	 */
	@RequestMapping("/{pageName}")
	public String goPage(@PathVariable String pageName){
		return pageName;
	}
}
