package com.tongdatech.xbxt.bean;

public class InterfaceMailInfo {
	private Integer sn;
	private String mail_code;
	private String action_info_out;
	private String office_name;
	private String relation_office_desc;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String minute;
	private String second;
	public InterfaceMailInfo(){
		
	}
	/**
	 * 
	 * @param mail_code
	 * @param action_info_out
	 * @param office_name
	 * @param relation_office_desc
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public InterfaceMailInfo(String mail_code,
			String action_info_out, String office_name,
			String relation_office_desc, String year, String month, String day,
			String hour, String minute, String second) {
		this.mail_code = mail_code;
		this.action_info_out = action_info_out;
		this.office_name = office_name;
		this.relation_office_desc = relation_office_desc;
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	/**
	 * 
	 * @param sn
	 * @param mail_code
	 * @param action_info_out
	 * @param office_name
	 * @param relation_office_desc
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public InterfaceMailInfo(Integer sn, String mail_code,
			String action_info_out, String office_name,
			String relation_office_desc, String year, String month, String day,
			String hour, String minute, String second) {
		this.sn = sn;
		this.mail_code = mail_code;
		this.action_info_out = action_info_out;
		this.office_name = office_name;
		this.relation_office_desc = relation_office_desc;
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	public Integer getSn() {
		return sn;
	}
	public void setSn(Integer sn) {
		this.sn = sn;
	}
	public String getMail_code() {
		return mail_code;
	}
	public void setMail_code(String mail_code) {
		this.mail_code = mail_code;
	}
	public String getAction_info_out() {
		return action_info_out;
	}
	public void setAction_info_out(String action_info_out) {
		this.action_info_out = action_info_out;
	}
	public String getOffice_name() {
		return office_name;
	}
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	public String getRelation_office_desc() {
		return relation_office_desc;
	}
	public void setRelation_office_desc(String relation_office_desc) {
		this.relation_office_desc = relation_office_desc;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	@Override
	public String toString() {
		return "InterfaceMailInfo [sn=" + sn + ", mail_code=" + mail_code
				+ ", action_info_out=" + action_info_out + ", office_name="
				+ office_name + ", relation_office_desc="
				+ relation_office_desc + ", year=" + year + ", month=" + month
				+ ", day=" + day + ", hour=" + hour + ", minute=" + minute
				+ ", second=" + second + "]";
	}
	
}
