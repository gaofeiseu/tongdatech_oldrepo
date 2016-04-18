package com.tongdatech.sys.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.Role;
import com.tongdatech.sys.bean.User;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.service.UserService;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.excel.ExcelForReport;

public class UserAction extends PaginationAction implements ModelDriven<User> {

	/** long serialVersionUID*/
	private static final long serialVersionUID = -4184196857030499555L;
	private User user =new User();
	private Role role;
	/** String old_pwd ������*/
	private String old_pwd;
	/** String repeat_pwd �ظ�����*/
	private String repeat_pwd;
	private UserService userService = new UserService();

	/**
	 * �û��������
	 * @return String
	 */
	public String init(){
		return "init";
	}
	
	public void getMyIconSelectHtml() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();	
    	JsonUtil.ToJson(null,response);
		return;
	}
	
	/**
	 * �û���ѯ
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String query() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();	
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = userService.query(pagination,user,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	
	public void submitUserIcon() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		JsonUtil.ToJson(null,response);
		return;
	}
	
	/**
	 * �û���ɫ��ѯ
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String roles() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();	
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = userService.roles(pagination,user,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	
	/**
	 * Excel��������
	 * @return String
	 * @throws Exception
	 */
	@Pageable
	public String excel() throws Exception{

		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		excelBean=new ExcelForReport(excelEdition);
		
		excelBean.setFliename("�û��б�");//���õ���excel����
		excelBean.setHeadtext("�û��б�");//���ñ�ͷ
   
		String[] titletext={"ϵͳID","�û���","�û��ǳ�","��������"};//˫�б�ͷ �ڶ���   û��Ԫ��ռ��1��
		excelBean.setTitletext(titletext);
		String[] datakey={"user_id","user_name","nick_name","brch_name"};//���ö�Ӧ���ݼ����е� �������� key
		excelBean.setDatakey(datakey);

		/**���÷����*/
		PageUI p=userService.query(pagination,user,userInfo);
		
		excelBean.setData(p.getRows());                               //���ö�Ӧ���ݼ���
		excelBean.create();

		/**����excel����*/
		return "excel";
	}
	
	/**
	 * ��������
	 * @return String
	 * @throws Exception
	 */
	public String resetPwd()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = userService.resetPwd(user,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	
	/**
	 * �û����ظ�У��
	 * @return String
	 * @throws Exception
	 */
	public String nameCheck()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();	
		AjaxMsg rs = userService.nameCheck(user);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	
	/**
	 * �����û�
	 * @return String
	 * @throws Exception
	 */
	public String saveUser()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = userService.saveUser(user,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	/**
	 * �����ɫ
	 * @return String
	 * @throws Exception
	 */
	public String saveRoles()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		
		AjaxMsg rs = userService.saveRoles(role,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	
	
	/**
	 * ɾ���û�
	 * @return String
	 * @throws Exception
	 */
	public String delUser()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		
		AjaxMsg rs = userService.delUser(user,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	
	/**
	 * ɾ����ɫ
	 * @return String
	 * @throws Exception
	 */
	public String delRoles()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		
		AjaxMsg rs = userService.delRoles(role,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	
	/**
	 * �����޸�ҳ��
	 * @return String
	 */
	public String changePwd(){
		return "pwd";
	}
	
	/**
	 * �޸�����
	 * @return String
	 * @throws Exception 
	 */
	public String savePWD() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		
		AjaxMsg rs = userService.savePWD(old_pwd,user.getPassword(),repeat_pwd,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
		
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public User getModel() {
		return user;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param old_pwd the old_pwd to set
	 */
	public void setOld_pwd(String old_pwd) {
		this.old_pwd = old_pwd;
	}

	/**
	 * @return the old_pwd
	 */
	public String getOld_pwd() {
		return old_pwd;
	}

	/**
	 * @param repeat_pwd the repeat_pwd to set
	 */
	public void setRepeat_pwd(String repeat_pwd) {
		this.repeat_pwd = repeat_pwd;
	}

	/**
	 * @return the repeat_pwd
	 */
	public String getRepeat_pwd() {
		return repeat_pwd;
	}

}
