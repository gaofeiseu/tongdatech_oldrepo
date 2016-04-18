package com.tongdatech.xbxt.bean;

public class XB_Settings {
	private Integer sn;
	private String param_name;
	private String param_value;
	private String param_comments;
	private String param_flag;
	private String param_flag_str;
	
	public String getParam_flag_str() {
		return param_flag_str;
	}
	public void setParam_flag_str(String param_flag_str) {
		this.param_flag_str = param_flag_str;
	}
	public Integer getSn() {
		return sn;
	}
	public void setSn(Integer sn) {
		this.sn = sn;
	}
	public String getParam_name() {
		return param_name;
	}
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}
	public String getParam_value() {
		return param_value;
	}
	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}
	public String getParam_comments() {
		return param_comments;
	}
	public void setParam_comments(String param_comments) {
		this.param_comments = param_comments;
	}
	public String getParam_flag() {
		return param_flag;
	}
	public void setParam_flag(String param_flag) {
		this.param_flag = param_flag;
	}
}
