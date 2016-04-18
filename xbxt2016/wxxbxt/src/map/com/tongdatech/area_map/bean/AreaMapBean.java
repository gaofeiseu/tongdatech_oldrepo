package com.tongdatech.area_map.bean;

import com.tongdatech.sys.annotation.PageParam;

public class AreaMapBean {

	@PageParam
	private String test;
	@PageParam
	private String insert_clob;
	
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getInsert_clob() {
		return insert_clob;
	}
	public void setInsert_clob(String insert_clob) {
		this.insert_clob = insert_clob;
	}
	
}
