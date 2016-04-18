package com.tongdatech.xbxt.bean;

import java.util.Date;

public class LogBean {
	private Integer sn;
	private String log_type;
	private String log_str;
	private Date log_date;
	private String log_date_str;
	
	public String getLog_date_str() {
		return log_date_str;
	}
	public void setLog_date_str(String log_date_str) {
		this.log_date_str = log_date_str;
	}
	public Integer getSn() {
		return sn;
	}
	public void setSn(Integer sn) {
		this.sn = sn;
	}
	public String getLog_type() {
		return log_type;
	}
	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}
	public String getLog_str() {
		return log_str;
	}
	public void setLog_str(String log_str) {
		this.log_str = log_str;
	}
	public Date getLog_date() {
		return log_date;
	}
	public void setLog_date(Date log_date) {
		this.log_date = log_date;
	}
}
