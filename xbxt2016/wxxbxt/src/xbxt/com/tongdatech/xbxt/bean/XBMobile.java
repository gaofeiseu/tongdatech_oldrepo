package com.tongdatech.xbxt.bean;

import java.util.List;
import java.util.Map;

public class XBMobile {
	private String username;
	private String password;
	private Mgr mgr;
	
	private List<Map<String,Object>> list_custtype;
	
	private Integer sn;
	private String mgrname;
	private String mgrid;
	private String custname;
	private String connect_name;
	private String connect_tel;
	private String custtype;
	private String comments;
	private String cust_feedback;
	private String imgname;
	
	private List<Map<String,Object>> list_map;
	
	private Integer count;
	
	private String year;
	private String month;
	private String custid;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<Map<String, Object>> getList_map() {
		return list_map;
	}
	public void setList_map(List<Map<String, Object>> list_map) {
		this.list_map = list_map;
	}
	public String getMgrname() {
		return mgrname;
	}
	public void setMgrname(String mgrname) {
		this.mgrname = mgrname;
	}
	public String getMgrid() {
		return mgrid;
	}
	public void setMgrid(String mgrid) {
		this.mgrid = mgrid;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getConnect_name() {
		return connect_name;
	}
	public void setConnect_name(String connect_name) {
		this.connect_name = connect_name;
	}
	public String getConnect_tel() {
		return connect_tel;
	}
	public void setConnect_tel(String connect_tel) {
		this.connect_tel = connect_tel;
	}
	public String getCusttype() {
		return custtype;
	}
	public void setCusttype(String custtype) {
		this.custtype = custtype;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCust_feedback() {
		return cust_feedback;
	}
	public void setCust_feedback(String cust_feedback) {
		this.cust_feedback = cust_feedback;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Mgr getMgr() {
		return mgr;
	}
	public void setMgr(Mgr mgr) {
		this.mgr = mgr;
	}
	public Integer getSn() {
		return sn;
	}
	public void setSn(Integer sn) {
		this.sn = sn;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public List<Map<String, Object>> getList_custtype() {
		return list_custtype;
	}
	public void setList_custtype(List<Map<String, Object>> list_custtype) {
		this.list_custtype = list_custtype;
	}
}
