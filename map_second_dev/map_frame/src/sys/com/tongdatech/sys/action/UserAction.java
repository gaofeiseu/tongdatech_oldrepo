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
	/** String old_pwd 旧密码*/
	private String old_pwd;
	/** String repeat_pwd 重复密码*/
	private String repeat_pwd;
	private UserService userService = new UserService();

	/**
	 * 用户管理界面
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
	 * 用户查询
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
	 * 用户角色查询
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
	 * Excel导出功能
	 * @return String
	 * @throws Exception
	 */
	@Pageable
	public String excel() throws Exception{

		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		excelBean=new ExcelForReport(excelEdition);
		
		excelBean.setFliename("用户列表");//设置导出excel名称
		excelBean.setHeadtext("用户列表");//设置表头
   
		String[] titletext={"系统ID","用户名","用户昵称","所属机构"};//双行表头 第二行   没个元素占用1列
		excelBean.setTitletext(titletext);
		String[] datakey={"user_id","user_name","nick_name","brch_name"};//设置对应数据集合中的 数据名称 key
		excelBean.setDatakey(datakey);

		/**调用服务层*/
		PageUI p=userService.query(pagination,user,userInfo);
		
		excelBean.setData(p.getRows());                               //设置对应数据集合
		excelBean.create();

		/**调用excel导出*/
		return "excel";
	}
	
	/**
	 * 重置密码
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
	 * 用户名重复校验
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
	 * 保存用户
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
	 * 保存角色
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
	 * 删除用户
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
	 * 删除角色
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
	 * 密码修改页面
	 * @return String
	 */
	public String changePwd(){
		return "pwd";
	}
	
	/**
	 * 修改密码
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
