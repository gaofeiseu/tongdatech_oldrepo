package com.tongdatech.business.bean;

import com.tongdatech.sys.annotation.PageParam;

public class DataInputBean {

	@PageParam
	private String comboselect_main_class;
	@PageParam
	private String comboselect_user_type;
	@PageParam
	private String templat_mainclass_sn;
	@PageParam
	private String templat_usertype_sn;
	@PageParam
	private String templat_childclass_sn;
	@PageParam
	private String templat_childclass_name;
	@PageParam
	private String upload_excel_name;
	@PageParam
	private String upload_childclass_sn;
	
	
	public String getUpload_childclass_sn() {
		return upload_childclass_sn;
	}
	public void setUpload_childclass_sn(String upload_childclass_sn) {
		this.upload_childclass_sn = upload_childclass_sn;
	}
	public String getUpload_excel_name() {
		return upload_excel_name;
	}
	public void setUpload_excel_name(String upload_excel_name) {
		this.upload_excel_name = upload_excel_name;
	}
	public String getTemplat_mainclass_sn() {
		return templat_mainclass_sn;
	}
	public void setTemplat_mainclass_sn(String templat_mainclass_sn) {
		this.templat_mainclass_sn = templat_mainclass_sn;
	}
	public String getTemplat_usertype_sn() {
		return templat_usertype_sn;
	}
	public void setTemplat_usertype_sn(String templat_usertype_sn) {
		this.templat_usertype_sn = templat_usertype_sn;
	}
	public String getTemplat_childclass_sn() {
		return templat_childclass_sn;
	}
	public void setTemplat_childclass_sn(String templat_childclass_sn) {
		this.templat_childclass_sn = templat_childclass_sn;
	}
	public String getTemplat_childclass_name() {
		return templat_childclass_name;
	}
	public void setTemplat_childclass_name(String templat_childclass_name) {
		this.templat_childclass_name = templat_childclass_name;
	}
	public String getComboselect_main_class() {
		return comboselect_main_class;
	}
	public void setComboselect_main_class(String comboselect_main_class) {
		this.comboselect_main_class = comboselect_main_class;
	}
	public String getComboselect_user_type() {
		return comboselect_user_type;
	}
	public void setComboselect_user_type(String comboselect_user_type) {
		this.comboselect_user_type = comboselect_user_type;
	}
}
