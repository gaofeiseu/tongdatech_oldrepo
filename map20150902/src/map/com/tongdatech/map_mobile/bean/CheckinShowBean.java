package com.tongdatech.map_mobile.bean;

import com.tongdatech.sys.annotation.PageParam;

public class CheckinShowBean {

	@PageParam
	private String ljhx_query_type;
	@PageParam
	private String ljhx_time_st;
	@PageParam
	private String ljhx_time_ed;
	@PageParam
	private String ljhx_query_role_type;
	@PageParam
	private String ljhx_query_loginname;
	@PageParam
	private String ljhx_query_brchno;
	@PageParam
	private String if_include_childbrch;
	@PageParam
	private String root_brch;
	@PageParam
	private String ljhx_location_type;
	@PageParam
	private String query_zoufang_type;
	
	private String sql;
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getQuery_zoufang_type() {
		return query_zoufang_type;
	}
	public void setQuery_zoufang_type(String query_zoufang_type) {
		this.query_zoufang_type = query_zoufang_type;
	}
	public String getLjhx_location_type() {
		return ljhx_location_type;
	}
	public void setLjhx_location_type(String ljhx_location_type) {
		this.ljhx_location_type = ljhx_location_type;
	}
	public String getRoot_brch() {
		return root_brch;
	}
	public void setRoot_brch(String root_brch) {
		this.root_brch = root_brch;
	}
	public String getIf_include_childbrch() {
		return if_include_childbrch;
	}
	public void setIf_include_childbrch(String if_include_childbrch) {
		this.if_include_childbrch = if_include_childbrch;
	}
	public String getLjhx_query_brchno() {
		return ljhx_query_brchno;
	}
	public void setLjhx_query_brchno(String ljhx_query_brchno) {
		this.ljhx_query_brchno = ljhx_query_brchno;
	}
	public String getLjhx_query_type() {
		return ljhx_query_type;
	}
	public void setLjhx_query_type(String ljhx_query_type) {
		this.ljhx_query_type = ljhx_query_type;
	}
	public String getLjhx_time_st() {
		return ljhx_time_st;
	}
	public void setLjhx_time_st(String ljhx_time_st) {
		this.ljhx_time_st = ljhx_time_st;
	}
	public String getLjhx_time_ed() {
		return ljhx_time_ed;
	}
	public void setLjhx_time_ed(String ljhx_time_ed) {
		this.ljhx_time_ed = ljhx_time_ed;
	}
	public String getLjhx_query_role_type() {
		return ljhx_query_role_type;
	}
	public void setLjhx_query_role_type(String ljhx_query_role_type) {
		this.ljhx_query_role_type = ljhx_query_role_type;
	}
	public String getLjhx_query_loginname() {
		return ljhx_query_loginname;
	}
	public void setLjhx_query_loginname(String ljhx_query_loginname) {
		this.ljhx_query_loginname = ljhx_query_loginname;
	}
	@Override
	public String toString() {
		return "CheckinShowBean [ljhx_query_type=" + ljhx_query_type
				+ ", ljhx_time_st=" + ljhx_time_st + ", ljhx_time_ed="
				+ ljhx_time_ed + ", ljhx_query_role_type="
				+ ljhx_query_role_type + ", ljhx_query_loginname="
				+ ljhx_query_loginname + ", ljhx_query_brchno="
				+ ljhx_query_brchno + ", if_include_childbrch="
				+ if_include_childbrch + ", root_brch=" + root_brch
				+ ", ljhx_location_type=" + ljhx_location_type
				+ ", query_zoufang_type=" + query_zoufang_type + ", sql=" + sql
				+ "]";
	}
}
