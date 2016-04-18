package com.tongdatech.xbxt.bean;

public class Mgr {
	private String mgrid;
	private String mgrname;
	private String zone;
	private String department;
	private String sex;
	private String edulevel;
	private String addr;
	/**
	 * 好像是手机号
	 */
	private String rela;
	private String login_sn;
	private String nation;
	private String work_time;
	private String mgr_work_time;
	private String createdate;
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
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEdulevel() {
		return edulevel;
	}
	public void setEdulevel(String edulevel) {
		this.edulevel = edulevel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getRela() {
		return rela;
	}
	public void setRela(String rela) {
		this.rela = rela;
	}
	public String getLogin_sn() {
		return login_sn;
	}
	public void setLogin_sn(String login_sn) {
		this.login_sn = login_sn;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getWork_time() {
		return work_time;
	}
	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}
	public String getMgr_work_time() {
		return mgr_work_time;
	}
	public void setMgr_work_time(String mgr_work_time) {
		this.mgr_work_time = mgr_work_time;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	@Override
	public String toString() {
		return "Mgr [mgrid=" + mgrid + ", mgrname=" + mgrname + ", zone="
				+ zone + ", department=" + department + ", sex=" + sex
				+ ", edulevel=" + edulevel + ", addr=" + addr + ", rela="
				+ rela + ", login_sn=" + login_sn + ", nation=" + nation
				+ ", work_time=" + work_time + ", mgr_work_time="
				+ mgr_work_time + ", createdate=" + createdate + "]";
	}
}
