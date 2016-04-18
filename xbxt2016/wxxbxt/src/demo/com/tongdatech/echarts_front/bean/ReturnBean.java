package com.tongdatech.echarts_front.bean;

import java.util.List;
import java.util.Map;

public class ReturnBean {
	private boolean if_success;
	private String msg;
	private String returnString;
	private List<String> returnList;
	private List<String> list;
	private String returnHtml;
	private List<Map<String,Object>> list_map;
	private ZhuzhuangBean zhuzhuangBean;
	
	
	public ZhuzhuangBean getZhuzhuangBean() {
		return zhuzhuangBean;
	}
	public void setZhuzhuangBean(ZhuzhuangBean zhuzhuangBean) {
		this.zhuzhuangBean = zhuzhuangBean;
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
		return "ReturnBean [if_success=" + if_success + ", msg=" + msg
				+ ", returnString=" + returnString + ", returnList="
				+ returnList + ", list=" + list + ", returnHtml=" + returnHtml
				+ ", list_map=" + list_map + ", zhuzhuangBean=" + zhuzhuangBean
				+ "]";
	}
}
