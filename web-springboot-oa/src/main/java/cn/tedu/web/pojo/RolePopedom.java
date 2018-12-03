package cn.tedu.web.pojo;

import java.io.Serializable;

public class RolePopedom implements Serializable{
	
	private String id;
	private String popedomId;
	private String popedomFatherid;
	private String roleName;
	private String popedomName;
	private String popedomUrl;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPopedomId() {
		return popedomId;
	}
	public void setPopedomId(String popedomId) {
		this.popedomId = popedomId;
	}
	public String getPopedomFatherid() {
		return popedomFatherid;
	}
	public void setPopedomFatherid(String popedomFatherid) {
		this.popedomFatherid = popedomFatherid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getPopedomName() {
		return popedomName;
	}
	public void setPopedomName(String popedomName) {
		this.popedomName = popedomName;
	}
	public String getPopedomUrl() {
		return popedomUrl;
	}
	public void setPopedomUrl(String popedomUrl) {
		this.popedomUrl = popedomUrl;
	}

	@Override
	public String toString() {
		return "RolePopedom [id=" + id + ", popedomId=" + popedomId + ", popedomFatherid=" + popedomFatherid
				+ ", roleName=" + roleName + ", popedomName=" + popedomName + ", popedomUrl=" + popedomUrl + "]";
	}
	
}
