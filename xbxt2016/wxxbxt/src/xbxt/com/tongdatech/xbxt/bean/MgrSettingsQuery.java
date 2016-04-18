package com.tongdatech.xbxt.bean;

public class MgrSettingsQuery {
	private String mgrid;
	private String mgrname;
	private String department;
	private Integer user_id;
	private String if_init;
	private String mgr_login_str;
	public String getMgrid() {
		return mgrid;
	}
	public void setMgrid(String mgrid) {
		this.mgrid = mgrid;
	}
	public String getMgrname() {
		return mgrname;
	}
	public void setMgrname(String mgrname) {
		this.mgrname = mgrname;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getIf_init() {
		return if_init;
	}
	public void setIf_init(String if_init) {
		this.if_init = if_init;
	}
	public String getMgr_login_str() {
		return mgr_login_str;
	}
	public void setMgr_login_str(String mgr_login_str) {
		this.mgr_login_str = mgr_login_str;
	}
	
}
