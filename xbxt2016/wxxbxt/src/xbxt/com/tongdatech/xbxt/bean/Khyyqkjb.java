package com.tongdatech.xbxt.bean;

public class Khyyqkjb {
	private Integer sn;
	private String cust_id;
	private String destination;
	private Integer send_num;
	private Double standard_fee;
	private Double fee;
	private Double mail_weight;
	private Double direct_mail_cost;//网运成本
	private Double real_direct_mail_cost;//直接 成本
	private Double mail_aver_time;
	private Double total_aver_time;
	private Integer last_month_send_num;
	private Double discount_persent;
	private Double maoli;
	
	public Double getMaoli() {
		return maoli;
	}
	public void setMaoli(Double maoli) {
		this.maoli = maoli;
	}
	public Double getDiscount_persent() {
		return discount_persent;
	}
	public void setDiscount_persent(Double discount_persent) {
		this.discount_persent = discount_persent;
	}
	public Double getReal_direct_mail_cost() {
		return real_direct_mail_cost;
	}
	public void setReal_direct_mail_cost(Double real_direct_mail_cost) {
		this.real_direct_mail_cost = real_direct_mail_cost;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public Integer getSn() {
		return sn;
	}
	public void setSn(Integer sn) {
		this.sn = sn;
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
	public Double getStandard_fee() {
		return standard_fee;
	}
	public void setStandard_fee(Double standard_fee) {
		this.standard_fee = standard_fee;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Double getMail_weight() {
		return mail_weight;
	}
	public void setMail_weight(Double mail_weight) {
		this.mail_weight = mail_weight;
	}
	public Double getDirect_mail_cost() {
		return direct_mail_cost;
	}
	public void setDirect_mail_cost(Double direct_mail_cost) {
		this.direct_mail_cost = direct_mail_cost;
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
	public Integer getLast_month_send_num() {
		return last_month_send_num;
	}
	public void setLast_month_send_num(Integer last_month_send_num) {
		this.last_month_send_num = last_month_send_num;
	}
}
