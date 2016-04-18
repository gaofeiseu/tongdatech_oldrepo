package com.tongdatech.map.bean;

import java.util.List;
import java.util.Map;

import com.tongdatech.sys.bean.UserInfo;

public class ReturnBean {
	private List<List<String>> list_string;
	private List<Map<String,Object>> list_map;
	private String status;
	private String msg;
	private boolean if_success;
	private UserInfo userInfo;
	private String sessionAttr;
	private List<?> rows;
	private int total;
	private String returnString;
	private List<String> returnList;
	private Map<String,Object> returnMap;
	private List<List<Map<String,Object>>> list_list;
	private String returnHtml;
	
	private Map<String,Object> dataMaps;
	private List<Map<String,Object>> colLists;
	private String createBrch;
 
	
	public String getReturnHtml() {
		return returnHtml;
	}
	public void setReturnHtml(String returnHtml) {
		this.returnHtml = returnHtml;
	}
	public Map<String, Object> getDataMaps() {
		return dataMaps;
	}
	public void setDataMaps(Map<String, Object> dataMaps) {
		this.dataMaps = dataMaps;
	}
 
 
	public List<Map<String, Object>> getColLists() {
		return colLists;
	}
	public void setColLists(List<Map<String, Object>> colLists) {
		this.colLists = colLists;
	}
	public List<List<Map<String, Object>>> getList_list() {
		return list_list;
	}
	public void setList_list(List<List<Map<String, Object>>> list_list) {
		this.list_list = list_list;
	}
	public Map<String, Object> getReturnMap() {
		return returnMap;
	}
	public void setReturnMap(Map<String, Object> returnMap) {
		this.returnMap = returnMap;
	}
	public List<String> getReturnList() {
		return returnList;
	}
	public void setReturnList(List<String> returnList) {
		this.returnList = returnList;
	}
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getSessionAttr() {
		return sessionAttr;
	}
	public void setSessionAttr(String sessionAttr) {
		this.sessionAttr = sessionAttr;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public List<List<String>> getList_string() {
		return list_string;
	}
	public void setList_string(List<List<String>> list_string) {
		this.list_string = list_string;
	}
	public List<Map<String, Object>> getList_map() {
		return list_map;
	}
	public void setList_map(List<Map<String, Object>> list_map) {
		this.list_map = list_map;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isIf_success() {
		return if_success;
	}
	public void setIf_success(boolean if_success) {
		this.if_success = if_success;
	}
 
	public String getCreateBrch() {
		return createBrch;
	}
	public void setCreateBrch(String createBrch) {
		this.createBrch = createBrch;
	}
	@Override
	public String toString() {
		return "ReturnBean [list_string=" + list_string + ", list_map="
				+ list_map + ", status=" + status + ", msg=" + msg
				+ ", if_success=" + if_success + ", userInfo=" + userInfo
				+ ", sessionAttr=" + sessionAttr + ", rows=" + rows
				+ ", total=" + total + "]";
	}
	
	
	
}
