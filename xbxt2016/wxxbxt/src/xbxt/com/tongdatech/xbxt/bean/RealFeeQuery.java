package com.tongdatech.xbxt.bean;

public class RealFeeQuery {
	private String brch_code;
	private String province;
	private Double real_fee;
	private Double param_a;
	private Double param_b;
	private Integer param_sn;
	private Double province_aver_time;
	
	public Double getProvince_aver_time() {
		return province_aver_time;
	}
	public void setProvince_aver_time(Double province_aver_time) {
		this.province_aver_time = province_aver_time;
	}
	public String getBrch_code() {
		return brch_code;
	}
	public void setBrch_code(String brch_code) {
		this.brch_code = brch_code;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Double getReal_fee() {
		return real_fee;
	}
	public void setReal_fee(Double real_fee) {
		this.real_fee = real_fee;
	}
	public Double getParam_a() {
		return param_a;
	}
	public void setParam_a(Double param_a) {
		this.param_a = param_a;
	}
	public Double getParam_b() {
		return param_b;
	}
	public void setParam_b(Double param_b) {
		this.param_b = param_b;
	}
	public Integer getParam_sn() {
		return param_sn;
	}
	public void setParam_sn(Integer param_sn) {
		this.param_sn = param_sn;
	}
}
