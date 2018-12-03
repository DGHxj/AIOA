package cn.tedu.web.pojo;

import java.io.Serializable;

public class Role implements Serializable{
	private Integer roleId;
	private String roleName;
	private Integer fatherRole;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getFatherRole() {
		return fatherRole;
	}
	public void setFatherRole(Integer fatherRole) {
		this.fatherRole = fatherRole;
	}	
}
