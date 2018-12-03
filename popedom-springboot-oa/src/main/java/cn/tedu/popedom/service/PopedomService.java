package cn.tedu.popedom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.common.util.UUIDUtil;
import cn.tedu.popedom.mapper.PopedomMapper;
import cn.tedu.popedom.pojo.Popedom;
import cn.tedu.popedom.pojo.RolePopedom;

@Service
public class PopedomService {
	@Autowired
	private PopedomMapper popedomMapper;
	
	//角色大权限查询
	public List<RolePopedom> getPopedomName(Integer roleId) {
		String roleName = popedomMapper.getRoleName(roleId);
		return popedomMapper.getRolePope(roleName);
	}
	
	//查询小权限
	public List<RolePopedom> getRolePopedom(String roleName,String popedomId) {
		return popedomMapper.getRolePopedom(roleName,popedomId);
	}
	
	//查询展示所有的角色对应的权限，
	//返回list数组
	public List<RolePopedom> showRoleAndPopedom() {
		return  popedomMapper.selectAll();		
	}
	
	//查询所有权限
	public List<Popedom>showPopedom() {
	  return popedomMapper.selectPopedomList();		
	}
		
	//角色权限添加
	public int addRolePopedom(String roleName, String popedomName) {
		Popedom popedom = popedomMapper.selectPopedom(popedomName);
		RolePopedom rolePopedom = new RolePopedom();
		rolePopedom.setId(UUIDUtil.getUUID());
		rolePopedom.setRoleName(roleName);
		rolePopedom.setPopedomId(popedom.getPopedomId());
		rolePopedom.setPopedomName(popedom.getPopedomName());
		rolePopedom.setPopedomUrl(popedom.getPopedomUrl());
		rolePopedom.setPopedomFatherid(popedom.getPopedomFatherid());
		return popedomMapper.addRolePopedom(rolePopedom);
	}
		
	//角色权限删除
	public int deleteRolePopedom(String roleName, String popedomName) {
		return popedomMapper.deleteRolePopedom(roleName,popedomName);
	}

}
