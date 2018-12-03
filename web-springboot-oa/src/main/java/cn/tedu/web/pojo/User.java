package cn.tedu.web.pojo;

import java.io.Serializable;

public class User implements Serializable{
	private String userId;//用户id
	private String userName;//用户名称
	private String userPassword;//用户密码
	private String imgToken;//人脸地址
	private String userEmail;//用户邮箱
	private String userPhone;//用户电话
	private Integer roleId;//角色id
	private String fatherId;//父级id
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getImgToken() {
		return imgToken;
	}
	public void setImgToken(String imgToken) {
		this.imgToken = imgToken;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", imgToken="
				+ imgToken + ", userEmail=" + userEmail + ", userPhone=" + userPhone + ", roleId=" + roleId
				+ ", fatherId=" + fatherId + "]";
	}
	
	
}
