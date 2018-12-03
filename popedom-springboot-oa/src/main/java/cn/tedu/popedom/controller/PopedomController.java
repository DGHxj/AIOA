package cn.tedu.popedom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.popedom.pojo.Popedom;
import cn.tedu.popedom.pojo.RolePopedom;
import cn.tedu.popedom.service.PopedomService;

@RestController
public class PopedomController {
	
	@Autowired
	private PopedomService popedomService;
	
	// 角色大权限查询
	@RequestMapping("popedom/selectBigRolePope/{roleId}")
	public List<RolePopedom> getPopedomName(@PathVariable Integer roleId) {
		List<RolePopedom> rpList = popedomService.getPopedomName(roleId);
		return rpList;
	}

	//查询所有小权限
	@RequestMapping("popedom/selectRolePope/{roleName}/{popedomId}")
	public List<RolePopedom> getRolePopedom(@PathVariable String roleName,
			@PathVariable String popedomId) {
		return popedomService.getRolePopedom(roleName, popedomId);
	}
		
	//权限列表
	//展示所有的角色对应的权限
	@RequestMapping("popedom/rolePopeList")
	public List<RolePopedom> showRoleAndPopedom(){
		return  popedomService.showRoleAndPopedom();
	}
	
	//查询所有权限
	@RequestMapping("popedom/selectPope")
	public List<Popedom> showPopedom(){
		return  popedomService.showPopedom();		
	}
		
	//角色添加权限
	@RequestMapping("popedom/addRolePope/{roleName}/{popedomName}")
	public String addPopedom(@PathVariable String roleName,@PathVariable String popedomName){		
		int  result = popedomService.addRolePopedom(roleName,popedomName);	
		if(result==1){
			return "success";
		}else{
			return "";
		}
		
	}
	
	//角色权限删除	
	@RequestMapping("popedom/deleteRolePope/{roleName}/{popedomName}")
	public String deletePopedom(@PathVariable String roleName,
			@PathVariable String popedomName){
		int result = popedomService.deleteRolePopedom(roleName,popedomName);
		if(result==1){
			return "success";
		}else{
			return "";
		}
	}
	
}





