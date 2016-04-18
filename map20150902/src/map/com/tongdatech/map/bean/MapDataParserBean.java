package com.tongdatech.map.bean;

import com.tongdatech.sys.annotation.PageParam;

public class MapDataParserBean {
	@PageParam
	private String test1;
	@PageParam
	private String file_name;
	@PageParam
	private String file_exe;
	
	
	public String getFile_exe() {
		return file_exe;
	}
	public void setFile_exe(String file_exe) {
		this.file_exe = file_exe;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getTest1() {
		return test1;
	}
	public void setTest1(String test1) {
		this.test1 = test1;
	}
}
