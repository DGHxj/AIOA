package cn.tedu.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.web.pojo.RolePopedom;
import cn.tedu.web.service.PopedomService;

@Controller
public class PopedomController {
	
	@Autowired
	private PopedomService popedomService;
	
	//角色权限列表
	@RequestMapping("/popedom/rolePopeList")
	public String selectPope(Model model) throws Exception{
		List<RolePopedom> rpList = popedomService.queryRolePope();
		model.addAttribute("rpList", rpList);
		return "popedom-table";		
	}
	
	//角色权限删除
	@RequestMapping("/popedom/deleteRolePope/{roleName}/{popedomName}")
	public String deleteRolePope(@PathVariable String roleName,@PathVariable String popedomName,Model model) throws Exception{
		String result = popedomService.deleteRolePope(roleName,popedomName);
		if("success".equals(result)){
			model.addAttribute("result", "删除成功");
			return "forward:/popedom/rolePopeList";
		}else{
			model.addAttribute("result", "删除失败");
			return "forward:/popedom/rolePopeList";
		}
	}
	
	//角色权限添加
	@RequestMapping("/popedom/addRolePope")
	public String addRolePope(String roleName,String popedomName,Model model) throws Exception{
		String result =  popedomService.addRolePope(roleName,popedomName);
		if("success".equals(result)){
			model.addAttribute("result", "添加成功");
			return "forward:/popedom/rolePopeList";
		}else{
			model.addAttribute("result", "添加失败");
			return "popedom-add";
		}
	}
	
}
