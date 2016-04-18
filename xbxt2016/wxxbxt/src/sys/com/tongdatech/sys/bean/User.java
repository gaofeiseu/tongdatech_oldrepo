package com.tongdatech.sys.bean;

/**
 * 用户对象bean       <br>
 * @author xl 
 * @version    2014-4-11 下午03:30:16
 */
public class User {

	/** int user_id 用户系统ID*/
	private int user_id;
	
	/** String user_name 用户登录名称*/
	private String user_name;
	
	/** String nick_name 昵称显示名称*/
	private String nick_name;
	
	/** String password 密码*/
	private String password;
	
	/** int brch_no 所属机构号*/
	private int brch_no;
	/** int brch_no 所属机构名称  冗余字段*/
	private String brch_name;
	
	
	/** int order_id 排序号*/
	private int order_id;
	
	/** String user_flag 用户表示 默认为1*/
	private String user_flag;
	
 
	
	/** String lat 精度*/
	private String lat;
	/** String lng 纬度*/
	private String lng;
	private String user_icon;
	private String icon_user_id;
	private String icon_user_icon;
	
	public String getUser_icon() {
		return user_icon;
	}
	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}
	public String getIcon_user_id() {
		return icon_user_id;
	}
	public void setIcon_user_id(String icon_user_id) {
		this.icon_user_id = icon_user_id;
	}
	public String getIcon_user_icon() {
		return icon_user_icon;
	}
	public void setIcon_user_icon(String icon_user_icon) {
		this.icon_user_icon = icon_user_icon;
	}
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the nick_name
	 */
	public String getNick_name() {
		return nick_name;
	}
	/**
	 * @param nick_name the nick_name to set
	 */
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the brch_no
	 */
	public int getBrch_no() {
		return brch_no;
	}
	/**
	 * @param brch_no the brch_no to set
	 */
	public void setBrch_no(int brch_no) {
		this.brch_no = brch_no;
	}
	/**
	 * @return the order_id
	 */
	public int getOrder_id() {
		return order_id;
	}
	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	/**
	 * @return the user_flag
	 */
	public String getUser_flag() {
		return user_flag;
	}
	/**
	 * @param user_flag the user_flag to set
	 */
	public void setUser_flag(String user_flag) {
		this.user_flag = user_flag;
	}
	/**
	 * @param brch_name the brch_name to set
	 */
	public void setBrch_name(String brch_name) {
		this.brch_name = brch_name;
	}
	/**
	 * @return the brch_name
	 */
	public String getBrch_name() {
		return brch_name;
	}
	
	
 
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name
				+ ", nick_name=" + nick_name + ", password=" + password
				+ ", brch_no=" + brch_no + ", brch_name=" + brch_name
				+ ", order_id=" + order_id + ", user_flag=" + user_flag 
 
				+ ", lat=" + lat+ ", lng=" + lng + "]";
	}
	
	
	
	
	
}
