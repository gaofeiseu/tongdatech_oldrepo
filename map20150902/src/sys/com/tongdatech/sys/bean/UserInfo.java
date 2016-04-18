package com.tongdatech.sys.bean;

import java.io.Serializable;

/**
 * @author xl 
 * @version   2014-2-28 上午11:37:02
 * 记录登录用户信息
 */

public class UserInfo implements Serializable{
	
   
	private static final long serialVersionUID = 6424949404699564437L;
	 
	/** String USER_INFO session 中记录用户信息关键字*/
	public static String USER_INFO ="userInfo";
	
    
	/** int user_id 用户代码*/
	private int user_id;
	/** String user_name 用户名称*/
	private String user_name;
	/** String nick_name 用户昵称  显示名称*/
	private String nick_name;
	
	/** int brch_no 机构代码*/
	private String brch_no;
	/** String brch_name 机构名称*/
	private String brch_name;
	/** String brch_mode 机构模式 省市县等*/
	private String brch_mode;
	/** String brch_up 上级机构代码*/
	private String brch_up;
	
	/** String area_no 地区代码*/
	private String area_no;
	/** String area_name 地区名称*/
	private String area_name;
	/** String area_parent 上级地区代码*/
	private String area_parent;
	
	/** String role_group 角色分组*/
	private int role_group;
	/** String role_id 角色代码*/
	private int role_id;
	/** String role_name 角色名称*/
	private String role_name;
	
	private String user_type;
	/** String lat 精度*/
	private String lat;
	/** String lng 纬度*/
	private String lng;
	
	private String user_ip;
	private String user_client;
	private String user_ver;
	
	/**
	 * 通过角色对象更新UserInfo对象
	 * @param role
	 */
	public void updatebyRole(Role role) {
		if(role.getUser_id()==this.user_id){
			
			if(role.getArea_no()!=null&&!"".equals(role.getArea_no()))this.area_no=role.getArea_no();
	        if(role.getArea_name()!=null&&!"".equals(role.getArea_name()))this.area_name=role.getArea_name();
	        if(role.getArea_parent()!=null&&!"".equals(role.getArea_parent()))this.area_parent=role.getArea_parent();
	   //     if(role.getBrch_name()!=null&&!"".equals(role.getBrch_name()))this.brch_name=role.getBrch_name();
	        if(role.getBrch_mode()!=null&&!"".equals(role.getBrch_mode()))this.brch_mode=role.getBrch_mode();
	        if(role.getRole_name()!=null&&!"".equals(role.getRole_name()))this.role_name=role.getRole_name();
	        
	        
	  //      if(role.getBrch_no()!=null&&!"".equals(role.getBrch_no()))this.brch_no=role.getBrch_no();
	        if(role.getBrch_up()!=null&&!"".equals(role.getBrch_up()))this.brch_up=role.getBrch_up();	        
	        if(role.getRole_id()!=0)this.role_id=role.getRole_id();
	        if(role.getRole_group()!=0)this.role_group=role.getRole_group();
		}

  
		
	}
	/**
	 * 清除角色相关信息
	 */
	public void clearRole(){
		this.area_no="";
		this.area_name="";
		this.area_parent="";
		this.brch_name="";
		this.brch_mode="";
		this.role_name="";

	//	this.brch_no="";
		this.role_id=0;
		this.brch_up="";
		this.user_type="";
	//	this.lat="";
	//	this.lng="";
		//this.user_id=0;
	}

	


	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public String getUser_client() {
		return user_client;
	}
	public void setUser_client(String user_client) {
		this.user_client = user_client;
	}
	public String getUser_ver() {
		return user_ver;
	}
	public void setUser_ver(String user_ver) {
		this.user_ver = user_ver;
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
	 * @return the brch_no
	 */
	public String getBrch_no() {
		return brch_no;
	}




	/**
	 * @param brch_no the brch_no to set
	 */
	public void setBrch_no(String brch_no) {
		this.brch_no = brch_no;
	}




	/**
	 * @return the brch_name
	 */
	public String getBrch_name() {
		return brch_name;
	}




	/**
	 * @param brch_name the brch_name to set
	 */
	public void setBrch_name(String brch_name) {
		this.brch_name = brch_name;
	}




	/**
	 * @return the brch_mode
	 */
	public String getBrch_mode() {
		return brch_mode;
	}




	/**
	 * @param brch_mode the brch_mode to set
	 */
	public void setBrch_mode(String brch_mode) {
		this.brch_mode = brch_mode;
	}




	/**
	 * @return the area_no
	 */
	public String getArea_no() {
		return area_no;
	}




	/**
	 * @param area_no the area_no to set
	 */
	public void setArea_no(String area_no) {
		this.area_no = area_no;
	}




	/**
	 * @return the area_name
	 */
	public String getArea_name() {
		return area_name;
	}




	/**
	 * @param area_name the area_name to set
	 */
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}




	/**
	 * @return the role_group
	 */
	public int getRole_group() {
		return role_group;
	}




	/**
	 * @param role_group the role_group to set
	 */
	public void setRole_group(int role_group) {
		this.role_group = role_group;
	}




	/**
	 * @return the role_id
	 */
	public int getRole_id() {
		return role_id;
	}




	/**
	 * @param role_id the role_id to set
	 */
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}




	/**
	 * @return the role_name
	 */
	public String getRole_name() {
		return role_name;
	}




	/**
	 * @param role_name the role_name to set
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}




	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	/**
	 * @return the brch_up
	 */
	public String getBrch_up() {
		return brch_up;
	}
	/**
	 * @param brch_up the brch_up to set
	 */
	public void setBrch_up(String brch_up) {
		this.brch_up = brch_up;
	}
	/**
	 * @return the area_parent
	 */
	public String getArea_parent() {
		return area_parent;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String userType) {
		user_type = userType;
	}
	/**
	 * @param area_parent the area_parent to set
	 */
	public void setArea_parent(String area_parent) {
		this.area_parent = area_parent;
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
		return "UserInfo [user_id=" + user_id + ", user_name=" + user_name
				+ ", nick_name=" + nick_name + ", brch_no=" + brch_no
				+ ", brch_name=" + brch_name + ", brch_mode=" + brch_mode
				+ ", brch_up=" + brch_up + ", area_no=" + area_no
				+ ", area_name=" + area_name + ", area_parent=" + area_parent
				+ ", role_group=" + role_group + ", role_id=" + role_id
				+ ", role_name=" + role_name + ", user_type=" + user_type
				+ ", lat=" + lat + ", lng=" + lng + ", user_ip=" + user_ip
				+ ", user_client=" + user_client + ", user_ver=" + user_ver
				+ "]";
	}
	














}


	
