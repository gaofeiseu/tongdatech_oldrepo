package com.tongdatech.sys.bean;

import com.google.gson.annotations.Expose;

/**
 * 角色对象      <br>
 * @author xl 
 * @version   2014-2-28 下午04:58:30
 * 
 */
public class Role {
	/** int role_group 角色组*/
	private int role_group;
	/** String role_id 角色代码*/
	@Expose
	private int role_id;
	/** String role_name 角色名称*/
	@Expose
	private String role_name ;
	/** String role_img 角色图像*/
	private String role_img="";
	/** int order_id 排序号*/
	private int order_id;
	/** String role_flag 角色状态*/
	private String role_flag; 
	
	/** String role_mode 角色模式 该角色能选择机构的类型 e.g. 2,4 对应省级和县级*/
	private String role_mode; 
	/**
	 * <b>冗余字段</b>
	 * */
	
	/** int sn T_SYS_R_USER_BR 的SN*/
	private int sn;
	/** int user_id 用户id*/
	private int  user_id;
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
	
	
	/** String brch_flag 机构状态*/
	private String brch_flag;


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
	 * @return the role_img
	 */
	public String getRole_img() {
		return role_img;
	}


	/**
	 * @param role_img the role_img to set
	 */
	public void setRole_img(String role_img) {
		this.role_img = role_img;
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
	 * @return the role_flag
	 */
	public String getRole_flag() {
		return role_flag;
	}


	/**
	 * @param role_flag the role_flag to set
	 */
	public void setRole_flag(String role_flag) {
		this.role_flag = role_flag;
	}


	/**
	 * @return the role_mode
	 */
	public String getRole_mode() {
		return role_mode;
	}


	/**
	 * @param role_mode the role_mode to set
	 */
	public void setRole_mode(String role_mode) {
		this.role_mode = role_mode;
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
	 * @return the brch_flag
	 */
	public String getBrch_flag() {
		return brch_flag;
	}


	/**
	 * @param brch_flag the brch_flag to set
	 */
	public void setBrch_flag(String brch_flag) {
		this.brch_flag = brch_flag;
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


	/**
	 * @param area_parent the area_parent to set
	 */
	public void setArea_parent(String area_parent) {
		this.area_parent = area_parent;
	}


	/**
	 * @param sn the sn to set
	 */
	public void setSn(int sn) {
		this.sn = sn;
	}


	/**
	 * @return the sn
	 */
	public int getSn() {
		return sn;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", sn=" + sn + ", user_id="
				+ user_id + ", brch_no=" + brch_no + "]";
	}
	

	
	
}
