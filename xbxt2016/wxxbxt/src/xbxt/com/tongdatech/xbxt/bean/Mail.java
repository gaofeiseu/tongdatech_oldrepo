package com.tongdatech.xbxt.bean;

import java.util.Map;

public class Mail {
	private Map<String,String> actionDateTime;
	private Map<String,String> actionInfoOut;
	private Map<String,String> mailCode;
	private Map<String,String> officeName;
	private Map<String,String> relationOfficeDesc;
	
	public String getActionDateTime() {
		if(actionDateTime == null){
			return "";
		}
		String month = (String)actionDateTime.get("month");
		String day = (String)actionDateTime.get("day");
		String hour = (String)actionDateTime.get("hour");
		String minute = (String)actionDateTime.get("minute");
		String second = (String)actionDateTime.get("second");
		return actionDateTime.get("year")
				+"-"+(month.length()==1?"0"+month:month)
				+"-"+(day.length()==1?"0"+day:day)
				+" "+(hour.length()==1?"0"+hour:hour)
				+":"+(minute.length()==1?"0"+minute:minute)
				+":"+(second.length()==1?"0"+second:second)
				;
	}
	public void setActionDateTime(Map<String, String> actionDateTime) {
		this.actionDateTime = actionDateTime;
	}
	public String getActionInfoOut() {
		if(actionInfoOut == null){
			return "";
		}
		return actionInfoOut.get("value");
	}
	public void setActionInfoOut(Map<String, String> actionInfoOut) {
		this.actionInfoOut = actionInfoOut;
	}
	public String getMailCode() {
		if(mailCode == null){
			return "";
		}
		return mailCode.get("value");
	}
	public void setMailCode(Map<String, String> mailCode) {
		this.mailCode = mailCode;
	}
	public String getOfficeName() {
		if(officeName == null){
			return "";
		}
		return officeName.get("value");
	}
	public void setOfficeName(Map<String, String> officeName) {
		this.officeName = officeName;
	}
	public String getRelationOfficeDesc() {
		if(relationOfficeDesc == null){
			return "";
		}
		return relationOfficeDesc.get("value");
	}
	public void setRelationOfficeDesc(Map<String, String> relationOfficeDesc) {
		this.relationOfficeDesc = relationOfficeDesc;
	}
}
