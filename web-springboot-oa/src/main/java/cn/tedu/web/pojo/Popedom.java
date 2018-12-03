package cn.tedu.web.pojo;

import java.io.Serializable;

public class Popedom implements Serializable {
	private String popedomId;
	private String popedomName;
	private String popedomFatherid;
	private String popedomUrl;
	
	public String getPopedomId() {
		return popedomId;
	}
	public void setPopedomId(String popedomId) {
		this.popedomId = popedomId;
	}
	public String getPopedomName() {
		return popedomName;
	}
	public void setPopedomName(String popedomName) {
		this.popedomName = popedomName;
	}
	public String getPopedomFatherid() {
		return popedomFatherid;
	}
	public void setPopedomFatherid(String popedomFatherid) {
		this.popedomFatherid = popedomFatherid;
	}
	public String getPopedomUrl() {
		return popedomUrl;
	}
	public void setPopedomUrl(String popedomUrl) {
		this.popedomUrl = popedomUrl;
	}
	
}
