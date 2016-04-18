package com.tongdatech.map_mobile.bean;

import java.util.List;
import java.util.Map;

public class MobileReturnBean {
	private boolean if_success;
	private String msg;
	private String returnString;
	private List<String> returnList;
	private List<String> list;
	private String returnHtml;
	private List<Map<String,Object>> list_map;
	private Map<String,Object> returnMap;
	private List<Map<String,Object>> list_detail_map;
	private List<Map<String,Object>> list_more_detail_map;
	private List<String> list_more_detail_title;
	
	public List<String> getList_more_detail_title() {
		return list_more_detail_title;
	}
	public void setList_more_detail_title(List<String> list_more_detail_title) {
		this.list_more_detail_title = list_more_detail_title;
	}
	public List<Map<String, Object>> getList_detail_map() {
		return list_detail_map;
	}
	public void setList_detail_map(List<Map<String, Object>> list_detail_map) {
		this.list_detail_map = list_detail_map;
	}
	public List<Map<String, Object>> getList_more_detail_map() {
		return list_more_detail_map;
	}
	public void setList_more_detail_map(
			List<Map<String, Object>> list_more_detail_map) {
		this.list_more_detail_map = list_more_detail_map;
	}
	public Map<String, Object> getReturnMap() {
		return returnMap;
	}
	public void setReturnMap(Map<String, Object> returnMap) {
		this.returnMap = returnMap;
	}
	public boolean isIf_success() {
		return if_success;
	}
	public void setIf_success(boolean if_success) {
		this.if_success = if_success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public List<String> getReturnList() {
		return returnList;
	}
	public void setReturnList(List<String> returnList) {
		this.returnList = returnList;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getReturnHtml() {
		return returnHtml;
	}
	public void setReturnHtml(String returnHtml) {
		this.returnHtml = returnHtml;
	}
	public List<Map<String, Object>> getList_map() {
		return list_map;
	}
	public void setList_map(List<Map<String, Object>> list_map) {
		this.list_map = list_map;
	}
	@Override
	public String toString() {
		return "MobileReturnBean [if_success=" + if_success + ", msg=" + msg
				+ ", returnString=" + returnString + ", returnList="
				+ returnList + ", list=" + list + ", returnHtml=" + returnHtml
				+ ", list_map=" + list_map + ", returnMap=" + returnMap
				+ ", list_detail_map=" + list_detail_map
				+ ", list_more_detail_map=" + list_more_detail_map
				+ ", list_more_detail_title=" + list_more_detail_title + "]";
	}
}
