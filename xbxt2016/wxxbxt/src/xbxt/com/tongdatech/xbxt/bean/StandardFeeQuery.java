package com.tongdatech.xbxt.bean;

public class StandardFeeQuery {
	private Integer param_sn;
	private String province;
	private String brch_code;
	private Double standard_fee;
	private Double discount_fee;
	
	
	
	
	public Integer getParam_sn() {
		return param_sn;
	}
	public void setParam_sn(Integer param_sn) {
		this.param_sn = param_sn;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getBrch_code() {
		return brch_code;
	}
	public void setBrch_code(String brch_code) {
		this.brch_code = brch_code;
	}
	public Double getStandard_fee() {
		return standard_fee;
	}
	public void setStandard_fee(Double standard_fee) {
		this.standard_fee = standard_fee;
	}
	public Double getDiscount_fee() {
		return discount_fee;
	}
	public void setDiscount_fee(Double discount_fee) {
		this.discount_fee = discount_fee;
	}
}
