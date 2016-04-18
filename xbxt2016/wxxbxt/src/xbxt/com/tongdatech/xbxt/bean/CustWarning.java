package com.tongdatech.xbxt.bean;

public class CustWarning {
	private String bigcustno;
	private String bigcustname;
	private String lastmonth_sendnum;
	private String lastlastmonth_sendnum;
	private String condition_one;
	private String condition_two;
	private String destination;
	private String mgrid;
	private String mgrname;
	
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
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getBigcustno() {
		return bigcustno;
	}
	public void setBigcustno(String bigcustno) {
		this.bigcustno = bigcustno;
	}
	public String getBigcustname() {
		return bigcustname;
	}
	public void setBigcustname(String bigcustname) {
		this.bigcustname = bigcustname;
	}
	public String getLastmonth_sendnum() {
		return lastmonth_sendnum;
	}
	public void setLastmonth_sendnum(String lastmonth_sendnum) {
		this.lastmonth_sendnum = lastmonth_sendnum;
	}
	public String getLastlastmonth_sendnum() {
		return lastlastmonth_sendnum;
	}
	public void setLastlastmonth_sendnum(String lastlastmonth_sendnum) {
		this.lastlastmonth_sendnum = lastlastmonth_sendnum;
	}
	public String getCondition_one() {
		return condition_one;
	}
	public void setCondition_one(String condition_one) {
		this.condition_one = condition_one;
	}
	public String getCondition_two() {
		return condition_two;
	}
	public void setCondition_two(String condition_two) {
		this.condition_two = condition_two;
	}
}
