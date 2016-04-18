package com.tongdatech.map_mobile.bean;

import com.tongdatech.sys.annotation.PageParam;

public class FileOPBean {
	@PageParam
	private String file_class;
	@PageParam
	private String file_name;
	@PageParam
	private String file_exe;
	@PageParam
	private String user_name;
	@PageParam
	private String user_id;
	@PageParam
	private String file_save_path;
	@PageParam
	private String file_msg;
	@PageParam
	private String sn;
	@PageParam
	private String file_class_str;
	@PageParam
	private String upload_user_name;
	@PageParam
	private String input_time;
	@PageParam
	private String file_sn;
	@PageParam
	private String my_filename;
	@PageParam
	private String ljhx_brchno_query;
	@PageParam
	private String hidden_root;
	
	public String getHidden_root() {
		return hidden_root;
	}
	public void setHidden_root(String hidden_root) {
		this.hidden_root = hidden_root;
	}
	public String getLjhx_brchno_query() {
		return ljhx_brchno_query;
	}
	public void setLjhx_brchno_query(String ljhx_brchno_query) {
		this.ljhx_brchno_query = ljhx_brchno_query;
	}
	public String getMy_filename() {
		return my_filename;
	}
	public void setMy_filename(String my_filename) {
		this.my_filename = my_filename;
	}
	public String getFile_sn() {
		return file_sn;
	}
	public void setFile_sn(String file_sn) {
		this.file_sn = file_sn;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getFile_class_str() {
		return file_class_str;
	}
	public void setFile_class_str(String file_class_str) {
		this.file_class_str = file_class_str;
	}
	public String getUpload_user_name() {
		return upload_user_name;
	}
	public void setUpload_user_name(String upload_user_name) {
		this.upload_user_name = upload_user_name;
	}
	public String getInput_time() {
		return input_time;
	}
	public void setInput_time(String input_time) {
		this.input_time = input_time;
	}
	public String getFile_msg() {
		return file_msg;
	}
	public void setFile_msg(String file_msg) {
		this.file_msg = file_msg;
	}
	public String getFile_save_path() {
		return file_save_path;
	}
	public void setFile_save_path(String file_save_path) {
		this.file_save_path = file_save_path;
	}
	public String getFile_class() {
		return file_class;
	}
	public void setFile_class(String file_class) {
		this.file_class = file_class;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_exe() {
		return file_exe;
	}
	public void setFile_exe(String file_exe) {
		this.file_exe = file_exe;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "FileOPBean [file_class=" + file_class + ", file_name="
				+ file_name + ", file_exe=" + file_exe + ", user_name="
				+ user_name + ", user_id=" + user_id + ", file_save_path="
				+ file_save_path + ", file_msg=" + file_msg + ", sn=" + sn
				+ ", file_class_str=" + file_class_str + ", upload_user_name="
				+ upload_user_name + ", input_time=" + input_time
				+ ", file_sn=" + file_sn + ", my_filename=" + my_filename
				+ ", ljhx_brchno_query=" + ljhx_brchno_query + ", hidden_root="
				+ hidden_root + "]";
	}
	
}
