package com.tongdatech.sys.bean;

import com.tongdatech.sys.annotation.PageParam;

public class UserConfig {
	@PageParam
	private String user_name;//�û��������-�û���
	@PageParam
	private String user_nick_name;//�û��������-�ǳ�
	@PageParam
	private String user_brch_no;//�û��������-�û�������
	@PageParam
	private String user_brch_name;
	@PageParam
	private String user_power;//�û��������-�û�Ȩ��
	@PageParam
	private String brch_name;//�û��������-�û�Ȩ�޶�Ӧ��������
	@PageParam
	private String brch_no;//�û��������-�û�Ȩ�޶�Ӧ������
	@PageParam
	private String role_id;//�û��������-�û�Ȩ�޶�Ӧ��ɫID
	@PageParam
	private String role_name;//�û��������-�û�Ȩ�޶�Ӧ��ɫ����
	@PageParam
	private String password;//�û��������-�û�����
	@PageParam
	private String check_user_name;//�û��������-�û���Ψһ��AJAX��֤
	@PageParam
	private String delete_user_name;//�û��������-����ɾ�����û���Ϣ����Ӧ���û���
	@PageParam
	private String user_id;//�û�����-�û���Ӧ���û�ID
	@PageParam
	private String area_no;//�û�����Ȩ��ʱ������Ҫ��area_no
	@PageParam
	private String delete_power_username;//�û�׼������Ȩ��ɾ���������û���
	@PageParam
	private String delete_power_brchname;//�û�׼������Ȩ��ɾ�������Ļ�����
	@PageParam
	private String delete_power_rolename;//�û�׼������Ȩ��ɾ�������Ľ�ɫ��
	@PageParam
	private String power_edit_brch_no;//��ɫȨ���޸��е�brch_no
	@PageParam
	private String power_edit_role_id;//��ɫȨ���޸��е�role_id
	@PageParam
	private String power_edit_user_name;//��ɫȨ���޸��е�user_name
	@PageParam
	private String old_power_brch_no;//���ڳ־û������õ�brch_no
	@PageParam
	private String old_power_role_id;//���ڳ־û������õ�role_id
	@PageParam
	private String login_brch_no;//��¼��Ա��brch_no Ŀǰ�Ѿ���userInfo�������
	
	
	@PageParam
	private String testStr;
	@PageParam
	private String status;
	@PageParam
	private String excelFileName;
	@PageParam
	private String excelTitleName;

	

	@Override
	public String toString() {
		return "UserConfig [user_name=" + user_name + ", user_nick_name="
				+ user_nick_name + ", user_brch_no=" + user_brch_no
				+ ", user_brch_name=" + user_brch_name + ", user_power="
				+ user_power + ", brch_name=" + brch_name + ", brch_no="
				+ brch_no + ", role_id=" + role_id + ", role_name=" + role_name
				+ ", password=" + password + ", check_user_name="
				+ check_user_name + ", delete_user_name=" + delete_user_name
				+ ", user_id=" + user_id + ", area_no=" + area_no
				+ ", delete_power_username=" + delete_power_username
				+ ", delete_power_brchname=" + delete_power_brchname
				+ ", delete_power_rolename=" + delete_power_rolename
				+ ", power_edit_brch_no=" + power_edit_brch_no
				+ ", power_edit_role_id=" + power_edit_role_id
				+ ", power_edit_user_name=" + power_edit_user_name
				+ ", old_power_brch_no=" + old_power_brch_no
				+ ", old_power_role_id=" + old_power_role_id
				+ ", login_brch_no=" + login_brch_no + ", testStr=" + testStr
				+ ", status=" + status + ", excelFileName=" + excelFileName
				+ ", excelTitleName=" + excelTitleName + "]";
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getExcelTitleName() {
		return excelTitleName;
	}

	public void setExcelTitleName(String excelTitleName) {
		this.excelTitleName = excelTitleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser_brch_name() {
		return user_brch_name;
	}

	public void setUser_brch_name(String user_brch_name) {
		this.user_brch_name = user_brch_name;
	}

	public String getTestStr() {
		return testStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}

	public String getLogin_brch_no() {
		return login_brch_no;
	}

	public void setLogin_brch_no(String login_brch_no) {
		this.login_brch_no = login_brch_no;
	}

	public String getOld_power_brch_no() {
		return old_power_brch_no;
	}

	public void setOld_power_brch_no(String old_power_brch_no) {
		this.old_power_brch_no = old_power_brch_no;
	}

	public String getOld_power_role_id() {
		return old_power_role_id;
	}

	public void setOld_power_role_id(String old_power_role_id) {
		this.old_power_role_id = old_power_role_id;
	}

	public String getPower_edit_brch_no() {
		return power_edit_brch_no;
	}

	public void setPower_edit_brch_no(String power_edit_brch_no) {
		this.power_edit_brch_no = power_edit_brch_no;
	}

	public String getPower_edit_role_id() {
		return power_edit_role_id;
	}

	public void setPower_edit_role_id(String power_edit_role_id) {
		this.power_edit_role_id = power_edit_role_id;
	}

	public String getPower_edit_user_name() {
		return power_edit_user_name;
	}

	public void setPower_edit_user_name(String power_edit_user_name) {
		this.power_edit_user_name = power_edit_user_name;
	}

	public String getDelete_power_username() {
		return delete_power_username;
	}

	public void setDelete_power_username(String delete_power_username) {
		this.delete_power_username = delete_power_username;
	}

	public String getDelete_power_brchname() {
		return delete_power_brchname;
	}

	public void setDelete_power_brchname(String delete_power_brchname) {
		this.delete_power_brchname = delete_power_brchname;
	}

	public String getDelete_power_rolename() {
		return delete_power_rolename;
	}

	public void setDelete_power_rolename(String delete_power_rolename) {
		this.delete_power_rolename = delete_power_rolename;
	}

	public String getArea_no() {
		return area_no;
	}

	public void setArea_no(String area_no) {
		this.area_no = area_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_nick_name() {
		return user_nick_name;
	}
	public void setUser_nick_name(String user_nick_name) {
		this.user_nick_name = user_nick_name;
	}
	public String getDelete_user_name() {
		return delete_user_name;
	}
	public void setDelete_user_name(String delete_user_name) {
		this.delete_user_name = delete_user_name;
	}
	public String getCheck_user_name() {
		return check_user_name;
	}
	public void setCheck_user_name(String check_user_name) {
		this.check_user_name = check_user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBrch_no() {
		return brch_no;
	}
	public void setBrch_no(String brch_no) {
		this.brch_no = brch_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_brch_no() {
		return user_brch_no;
	}
	public void setUser_brch_no(String user_brch_no) {
		this.user_brch_no = user_brch_no;
	}
	public String getUser_power() {
		return user_power;
	}
	public void setUser_power(String user_power) {
		this.user_power = user_power;
	}
	public String getBrch_name() {
		return brch_name;
	}
	public void setBrch_name(String brch_name) {
		this.brch_name = brch_name;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	
}
