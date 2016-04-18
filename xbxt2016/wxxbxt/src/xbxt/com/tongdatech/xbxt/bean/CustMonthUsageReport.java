package com.tongdatech.xbxt.bean;

public class CustMonthUsageReport {
	private Integer sn;
	private String cust_id;
	private String destination;
	private Integer send_num;
	private Double fee;
	private Double mail_aver_time;
	private Double total_aver_time;
	private Double discount_persent;
	
	
	public Double getDiscount_persent() {
		return discount_persent;
	}
	public void setDiscount_persent(Double discount_persent) {
		this.discount_persent = discount_persent;
	}
	public Integer getSn() {
		return sn;
	}
	public void setSn(Integer sn) {
		this.sn = sn;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Integer getSend_num() {
		return send_num;
	}
	public void setSend_num(Integer send_num) {
		this.send_num = send_num;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Double getMail_aver_time() {
		return mail_aver_time;
	}
	public void setMail_aver_time(Double mail_aver_time) {
		this.mail_aver_time = mail_aver_time;
	}
	public Double getTotal_aver_time() {
		return total_aver_time;
	}
	public void setTotal_aver_time(Double total_aver_time) {
		this.total_aver_time = total_aver_time;
	}
}
