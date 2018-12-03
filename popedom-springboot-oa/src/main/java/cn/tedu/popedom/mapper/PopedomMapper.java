package cn.tedu.popedom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.popedom.pojo.Popedom;
import cn.tedu.popedom.pojo.RolePopedom;

public interface PopedomMapper {
	
	String getRoleName(Integer roleId);
	
	List<RolePopedom> getRolePope(String roleName);
	
	List<RolePopedom> getRolePopedom(@Param("roleName")String roleName,@Param("popedomId")String popedomId);
	
	List<RolePopedom> selectAll();

	List<Popedom> selectPopedomList();
	
	Popedom selectPopedom(String popedomName);

	int addRolePopedom(RolePopedom rolePopedom);

	int deleteRolePopedom(@Param("roleName")String roleName,@Param("popedomName")String popedomName);

}
