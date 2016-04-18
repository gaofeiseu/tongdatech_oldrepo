/**
 * File name:Param.java
 * Create author:XY
 * Create date:2014-3-18
 */
package com.tongdatech.business.bean;

import com.tongdatech.sys.annotation.PageParam;

/**
 * @author 
 *
 */
public class Param {

	
	@PageParam
	private String sn        ;       
	@PageParam
	private String login_name  ;     
	@PageParam
	private String login_id   ;      
	@PageParam
	private String check_in_lat    ; 
	@PageParam
	private String check_in_lng   ;  
	@PageParam
	private String visit_type      ; 
	@PageParam
	private String visit_custom_name;
	@PageParam
	private String visit_content    ;
	@PageParam
	private String visit_note    ;   
	@PageParam
	private String check_in_time ;   
	@PageParam
	private String visit_class    ;  
	@PageParam
	private String img_id;           
	@PageParam
	private String img_url;   
	@PageParam
	private String brch_no; 
	@PageParam
	private String _flag="";
	
	@PageParam
	private String check_in_time_start;
	@PageParam
	private String check_in_time_end;
	public String getSn() {
		return sn;
	}



	public void setSn(String sn) {
		this.sn = sn;
	}



	public String getLogin_name() {
		return login_name;
	}



	public void setLogin_name(String loginName) {
		login_name = loginName;
	}



	public String getLogin_id() {
		return login_id;
	}



	public void setLogin_id(String loginId) {
		login_id = loginId;
	}



	public String getCheck_in_lat() {
		return check_in_lat;
	}



	public void setCheck_in_lat(String checkInLat) {
		check_in_lat = checkInLat;
	}



	public String getCheck_in_lng() {
		return check_in_lng;
	}



	public void setCheck_in_lng(String checkInLng) {
		check_in_lng = checkInLng;
	}



	public String getVisit_type() {
		return visit_type;
	}



	public void setVisit_type(String visitType) {
		visit_type = visitType;
	}



	public String getVisit_custom_name() {
		return visit_custom_name;
	}



	public void setVisit_custom_name(String visitCustomName) {
		visit_custom_name = visitCustomName;
	}



	public String getVisit_content() {
		return visit_content;
	}



	public void setVisit_content(String visitContent) {
		visit_content = visitContent;
	}



	public String getVisit_note() {
		return visit_note;
	}



	public void setVisit_note(String visitNote) {
		visit_note = visitNote;
	}



	public String getCheck_in_time() {
		return check_in_time;
	}



	public void setCheck_in_time(String checkInTime) {
		check_in_time = checkInTime;
	}



	public String getVisit_class() {
		return visit_class;
	}



	public void setVisit_class(String visitClass) {
		visit_class = visitClass;
	}



	public String getImg_id() {
		return img_id;
	}



	public void setImg_id(String imgId) {
		img_id = imgId;
	}

   


	public String getImg_url() {
		return img_url;
	}



	public void setImg_url(String imgUrl) {
		img_url = imgUrl;
	}



	public String getBrch_no() {
		return brch_no;
	}



	public void setBrch_no(String brchNo) {
		brch_no = brchNo;
	}



	public String get_flag() {
		return _flag;
	}



	public void set_flag(String flag) {
		_flag = flag;
	}



	public String getCheck_in_time_start() {
		return check_in_time_start;
	}



	public void setCheck_in_time_start(String checkInTimeStart) {
		check_in_time_start = checkInTimeStart;
	}



	public String getCheck_in_time_end() {
		return check_in_time_end;
	}



	public void setCheck_in_time_end(String checkInTimeEnd) {
		check_in_time_end = checkInTimeEnd;
	}



	@Override
	public String toString() {
		return "param [sn=" + sn + ", login_name=" + login_name + ", login_id=" + login_id
				+ ", check_in_lat=" + check_in_lat + ", check_in_lng=" + check_in_lng + ", visit_type="
				+ visit_type +", visit_custom_name=" + visit_custom_name + ", visit_content=" + visit_content +
				", visit_note=" + visit_note + ", check_in_time=" + check_in_time +
				", visit_class=" + visit_class + ", img_id=" + img_id + ", img_url=" + img_url 
				+ ", brch_no=" + brch_no+"]";
	}
}