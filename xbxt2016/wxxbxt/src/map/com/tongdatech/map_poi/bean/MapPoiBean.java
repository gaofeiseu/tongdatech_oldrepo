package com.tongdatech.map_poi.bean;

import com.tongdatech.sys.annotation.PageParam;

public class MapPoiBean {
	@PageParam
	private String city;
	@PageParam
	private String query_str;
	@PageParam
	private String password;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getQuery_str() {
		return query_str;
	}
	public void setQuery_str(String query_str) {
		this.query_str = query_str;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
