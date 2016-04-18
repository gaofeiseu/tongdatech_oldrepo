package com.tongdatech.business.bean;

import com.tongdatech.sys.annotation.PageParam;
/**
 * 
 * @author Mr.GaoFei
 *
 */
public class DataClassBean {
	@PageParam
	private String parentclass_sn;
	@PageParam
	private String childclass_sn;
	@PageParam
	private String childclass_name;
	@PageParam
	private String childclass_brchno;
	@PageParam
	private String childclass_brchstr;
	@PageParam
	private String childclass_status;
	@PageParam
	private String childclass_status_str;
	
	
	@PageParam
	private String childclass_add_for_parentclassid;
	@PageParam
	private String childclass_add_classname;
	@PageParam
	private String childclass_add_brch;
	@PageParam
	private String childclass_add_status;
	
	
	@PageParam
	private String sn_for_deletechildclass;
	
	@PageParam
	private String childclasssn_edit;
	@PageParam
	private String childclass_edit_classname;
	@PageParam
	private String childclass_edit_brch;
	@PageParam
	private String childclass_edit_status;
	
	@PageParam
	private String classproperty_id;
	@PageParam
	private String classproperty_parentclass_id;
	@PageParam
	private String classproperty_parentclass_tn;
	@PageParam
	private String classproperty_parentclass_cn;
	@PageParam
	private String classproperty_name;
	@PageParam
	private String classproperty_type;
	@PageParam
	private String classproperty_type_str;
	@PageParam
	private String classproperty_ifnull;
	@PageParam
	private String classproperty_ifnull_str;
	@PageParam
	private String classproperty_size;
	@PageParam
	private String classproperty_status;
	@PageParam
	private String classproperty_status_str;
	
	
	@PageParam
	private String classproperty_add_sn;
	@PageParam
	private String classproperty_add_name;
	@PageParam
	private String classproperty_add_type;
	@PageParam
	private String classproperty_add_ifnull;
	@PageParam
	private String classproperty_add_size;
	@PageParam
	private String classproperty_add_status;
	
	@PageParam
	private String sn_for_deleteclassproperty;
	
	@PageParam
	private String sn_for_editclassproperty;
	@PageParam
	private String name_for_editclassproperty;
	@PageParam
	private String status_for_editclassproperty;
	
	@PageParam
	private String t_user_type;
	@PageParam
	private String table_name;
	@PageParam
	private String column_name;
	
	public String getT_user_type() {
		return t_user_type;
	}
	public void setT_user_type(String t_user_type) {
		this.t_user_type = t_user_type;
	}
	public String getName_for_editclassproperty() {
		return name_for_editclassproperty;
	}
	public void setName_for_editclassproperty(String name_for_editclassproperty) {
		this.name_for_editclassproperty = name_for_editclassproperty;
	}
	public String getStatus_for_editclassproperty() {
		return status_for_editclassproperty;
	}
	public void setStatus_for_editclassproperty(String status_for_editclassproperty) {
		this.status_for_editclassproperty = status_for_editclassproperty;
	}
	public String getSn_for_editclassproperty() {
		return sn_for_editclassproperty;
	}
	public void setSn_for_editclassproperty(String sn_for_editclassproperty) {
		this.sn_for_editclassproperty = sn_for_editclassproperty;
	}
	public String getSn_for_deleteclassproperty() {
		return sn_for_deleteclassproperty;
	}
	public void setSn_for_deleteclassproperty(String sn_for_deleteclassproperty) {
		this.sn_for_deleteclassproperty = sn_for_deleteclassproperty;
	}
	public String getClassproperty_add_sn() {
		return classproperty_add_sn;
	}
	public void setClassproperty_add_sn(String classproperty_add_sn) {
		this.classproperty_add_sn = classproperty_add_sn;
	}
	public String getClassproperty_add_name() {
		return classproperty_add_name;
	}
	public void setClassproperty_add_name(String classproperty_add_name) {
		this.classproperty_add_name = classproperty_add_name;
	}
	public String getClassproperty_add_type() {
		return classproperty_add_type;
	}
	public void setClassproperty_add_type(String classproperty_add_type) {
		this.classproperty_add_type = classproperty_add_type;
	}
	public String getClassproperty_add_ifnull() {
		return classproperty_add_ifnull;
	}
	public void setClassproperty_add_ifnull(String classproperty_add_ifnull) {
		this.classproperty_add_ifnull = classproperty_add_ifnull;
	}
	public String getClassproperty_add_size() {
		return classproperty_add_size;
	}
	public void setClassproperty_add_size(String classproperty_add_size) {
		this.classproperty_add_size = classproperty_add_size;
	}
	public String getClassproperty_add_status() {
		return classproperty_add_status;
	}
	public void setClassproperty_add_status(String classproperty_add_status) {
		this.classproperty_add_status = classproperty_add_status;
	}
	public String getClassproperty_parentclass_tn() {
		return classproperty_parentclass_tn;
	}
	public void setClassproperty_parentclass_tn(String classproperty_parentclass_tn) {
		this.classproperty_parentclass_tn = classproperty_parentclass_tn;
	}
	public String getClassproperty_parentclass_cn() {
		return classproperty_parentclass_cn;
	}
	public void setClassproperty_parentclass_cn(String classproperty_parentclass_cn) {
		this.classproperty_parentclass_cn = classproperty_parentclass_cn;
	}
	public String getClassproperty_type_str() {
		return classproperty_type_str;
	}
	public void setClassproperty_type_str(String classproperty_type_str) {
		this.classproperty_type_str = classproperty_type_str;
	}
	public String getClassproperty_ifnull_str() {
		return classproperty_ifnull_str;
	}
	public void setClassproperty_ifnull_str(String classproperty_ifnull_str) {
		this.classproperty_ifnull_str = classproperty_ifnull_str;
	}
	public String getClassproperty_status_str() {
		return classproperty_status_str;
	}
	public void setClassproperty_status_str(String classproperty_status_str) {
		this.classproperty_status_str = classproperty_status_str;
	}
	public String getClassproperty_id() {
		return classproperty_id;
	}
	public void setClassproperty_id(String classproperty_id) {
		this.classproperty_id = classproperty_id;
	}
	public String getClassproperty_parentclass_id() {
		return classproperty_parentclass_id;
	}
	public void setClassproperty_parentclass_id(String classproperty_parentclass_id) {
		this.classproperty_parentclass_id = classproperty_parentclass_id;
	}
	public String getClassproperty_name() {
		return classproperty_name;
	}
	public void setClassproperty_name(String classproperty_name) {
		this.classproperty_name = classproperty_name;
	}
	public String getClassproperty_type() {
		return classproperty_type;
	}
	public void setClassproperty_type(String classproperty_type) {
		this.classproperty_type = classproperty_type;
	}
	public String getClassproperty_ifnull() {
		return classproperty_ifnull;
	}
	public void setClassproperty_ifnull(String classproperty_ifnull) {
		this.classproperty_ifnull = classproperty_ifnull;
	}
	public String getClassproperty_size() {
		return classproperty_size;
	}
	public void setClassproperty_size(String classproperty_size) {
		this.classproperty_size = classproperty_size;
	}
	public String getClassproperty_status() {
		return classproperty_status;
	}
	public void setClassproperty_status(String classproperty_status) {
		this.classproperty_status = classproperty_status;
	}
	public String getChildclasssn_edit() {
		return childclasssn_edit;
	}
	public void setChildclasssn_edit(String childclasssn_edit) {
		this.childclasssn_edit = childclasssn_edit;
	}
	public String getChildclass_edit_classname() {
		return childclass_edit_classname;
	}
	public void setChildclass_edit_classname(String childclass_edit_classname) {
		this.childclass_edit_classname = childclass_edit_classname;
	}
	public String getChildclass_edit_brch() {
		return childclass_edit_brch;
	}
	public void setChildclass_edit_brch(String childclass_edit_brch) {
		this.childclass_edit_brch = childclass_edit_brch;
	}
	public String getChildclass_edit_status() {
		return childclass_edit_status;
	}
	public void setChildclass_edit_status(String childclass_edit_status) {
		this.childclass_edit_status = childclass_edit_status;
	}
	public String getSn_for_deletechildclass() {
		return sn_for_deletechildclass;
	}
	public void setSn_for_deletechildclass(String sn_for_deletechildclass) {
		this.sn_for_deletechildclass = sn_for_deletechildclass;
	}
	public String getChildclass_add_for_parentclassid() {
		return childclass_add_for_parentclassid;
	}
	public void setChildclass_add_for_parentclassid(
			String childclass_add_for_parentclassid) {
		this.childclass_add_for_parentclassid = childclass_add_for_parentclassid;
	}
	public String getChildclass_add_classname() {
		return childclass_add_classname;
	}
	public void setChildclass_add_classname(String childclass_add_classname) {
		this.childclass_add_classname = childclass_add_classname;
	}
	public String getChildclass_add_brch() {
		return childclass_add_brch;
	}
	public void setChildclass_add_brch(String childclass_add_brch) {
		this.childclass_add_brch = childclass_add_brch;
	}
	public String getChildclass_add_status() {
		return childclass_add_status;
	}
	public void setChildclass_add_status(String childclass_add_status) {
		this.childclass_add_status = childclass_add_status;
	}
	public String getChildclass_status_str() {
		return childclass_status_str;
	}
	public void setChildclass_status_str(String childclass_status_str) {
		this.childclass_status_str = childclass_status_str;
	}
	public String getChildclass_brchstr() {
		return childclass_brchstr;
	}
	public void setChildclass_brchstr(String childclass_brchstr) {
		this.childclass_brchstr = childclass_brchstr;
	}
	public String getChildclass_sn() {
		return childclass_sn;
	}
	public void setChildclass_sn(String childclass_sn) {
		this.childclass_sn = childclass_sn;
	}
	public String getChildclass_name() {
		return childclass_name;
	}
	public void setChildclass_name(String childclass_name) {
		this.childclass_name = childclass_name;
	}
	public String getChildclass_brchno() {
		return childclass_brchno;
	}
	public void setChildclass_brchno(String childclass_brchno) {
		this.childclass_brchno = childclass_brchno;
	}
	public String getChildclass_status() {
		return childclass_status;
	}
	public void setChildclass_status(String childclass_status) {
		this.childclass_status = childclass_status;
	}
	public String getParentclass_sn() {
		return parentclass_sn;
	}
	public void setParentclass_sn(String parentclass_sn) {
		this.parentclass_sn = parentclass_sn;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String tableName) {
		table_name = tableName;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String columnName) {
		column_name = columnName;
	}
	
	
	
	
}
