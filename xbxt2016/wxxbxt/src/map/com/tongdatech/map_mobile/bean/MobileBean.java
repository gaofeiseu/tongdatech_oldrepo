package com.tongdatech.map_mobile.bean;

import com.tongdatech.sys.annotation.PageParam;

public class MobileBean {
	@PageParam
	private String role_type;
	@PageParam
	private String login_name;
	@PageParam
	private String my_login_id;
	@PageParam
	private String pass_word;
	@PageParam
	private String my_login_name;
	@PageParam
	private String visit_type;
	@PageParam
	private String visit_custom_name;
	@PageParam
	private String visit_content;
	@PageParam
	private String visit_note;
	@PageParam
	private String check_in_lat;
	@PageParam
	private String check_in_lng;
	@PageParam
	private String history_time_st;
	@PageParam
	private String history_time_ed;
	@PageParam
	private String visit_class;
	@PageParam
	private String file_fake_name;
	@PageParam
	private String mobile_img_url;
	
	@PageParam
	private String history_now_num;
	@PageParam
	private String history_increace_num;
	@PageParam
	private String if_wap;
	
	
	@PageParam
	private String custom_type;
	
	
	
	public String getCustom_type() {
		return custom_type;
	}
	public void setCustom_type(String custom_type) {
		this.custom_type = custom_type;
	}
	public String getIf_wap() {
		return if_wap;
	}
	public void setIf_wap(String if_wap) {
		this.if_wap = if_wap;
	}
	public String getHistory_now_num() {
		return history_now_num;
	}
	public void setHistory_now_num(String history_now_num) {
		this.history_now_num = history_now_num;
	}
	public String getHistory_increace_num() {
		return history_increace_num;
	}
	public void setHistory_increace_num(String history_increace_num) {
		this.history_increace_num = history_increace_num;
	}
	public String getMobile_img_url() {
		return mobile_img_url;
	}
	public void setMobile_img_url(String mobile_img_url) {
		this.mobile_img_url = mobile_img_url;
	}
	public String getFile_fake_name() {
		return file_fake_name;
	}
	public void setFile_fake_name(String file_fake_name) {
		this.file_fake_name = file_fake_name;
	}
	public String getVisit_class() {
		return visit_class;
	}
	public void setVisit_class(String visit_class) {
		this.visit_class = visit_class;
	}
	public String getHistory_time_st() {
		return history_time_st;
	}
	public void setHistory_time_st(String history_time_st) {
		this.history_time_st = history_time_st;
	}
	public String getHistory_time_ed() {
		return history_time_ed;
	}
	public void setHistory_time_ed(String history_time_ed) {
		this.history_time_ed = history_time_ed;
	}
	public String getMy_login_id() {
		return my_login_id;
	}
	public void setMy_login_id(String my_login_id) {
		this.my_login_id = my_login_id;
	}
	public String getCheck_in_lat() {
		return check_in_lat;
	}
	public void setCheck_in_lat(String check_in_lat) {
		this.check_in_lat = check_in_lat;
	}
	public String getCheck_in_lng() {
		return check_in_lng;
	}
	public void setCheck_in_lng(String check_in_lng) {
		this.check_in_lng = check_in_lng;
	}
	public String getMy_login_name() {
		return my_login_name;
	}
	public void setMy_login_name(String my_login_name) {
		this.my_login_name = my_login_name;
	}
	public String getVisit_type() {
		return visit_type;
	}
	public void setVisit_type(String visit_type) {
		this.visit_type = visit_type;
	}
	public String getVisit_custom_name() {
		return visit_custom_name;
	}
	public void setVisit_custom_name(String visit_custom_name) {
		this.visit_custom_name = visit_custom_name;
	}
	public String getVisit_content() {
		return visit_content;
	}
	public void setVisit_content(String visit_content) {
		this.visit_content = visit_content;
	}
	public String getVisit_note() {
		return visit_note;
	}
	public void setVisit_note(String visit_note) {
		this.visit_note = visit_note;
	}
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getPass_word() {
		return pass_word;
	}
	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}
	@Override
	public String toString() {
		return "MobileBean [role_type=" + role_type + ", login_name="
				+ login_name + ", my_login_id=" + my_login_id + ", pass_word="
				+ pass_word + ", my_login_name=" + my_login_name
				+ ", visit_type=" + visit_type + ", visit_custom_name="
				+ visit_custom_name + ", visit_content=" + visit_content
				+ ", visit_note=" + visit_note + ", check_in_lat="
				+ check_in_lat + ", check_in_lng=" + check_in_lng
				+ ", history_time_st=" + history_time_st + ", history_time_ed="
				+ history_time_ed + ", visit_class=" + visit_class
				+ ", file_fake_name=" + file_fake_name + ", mobile_img_url="
				+ mobile_img_url + ", history_now_num=" + history_now_num
				+ ", history_increace_num=" + history_increace_num
				+ ", if_wap=" + if_wap + ", custom_type=" + custom_type + "]";
	}
	
}
