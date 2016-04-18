
package com.tongdatech.sys.action;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tongdatech.sys.bean.Role;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.interceptor.LoginInterceptor;
import com.tongdatech.sys.pojo.JspMsg;
import com.tongdatech.sys.service.LoginService;
import com.tongdatech.sys.util.ClientInfo;





/**
 * 登录Action <br>
 * @author xl 
 * @version    2014-4-11 上午09:34:06
 */
public class LoginAction extends ActionSupport
{


	/** long serialVersionUID*/
	private static final long serialVersionUID = -8401498799147465799L;
	/** Logger log*/
	private static Logger log =  Logger.getLogger(LoginAction.class);
	
	/** String username 登录名*/
	private String username;
	/** String password 密码*/
	private String password;
	
	/** LoginService loginService 登录服务*/
	private LoginService loginService = new LoginService();



	/** List<Role> roles 角色存储列表*/
	private List<Role> roles;






	/**
	 * 登录参数校验
	 */
	public void validateLoginExecute() {
		if(null==username||"".equals(username)){
			this.addFieldError("username", "用户名不能为空");
		}
		if(null==password||"".equals(password)){
			this.addFieldError("password", "密码不能为空");
		}
     
	}

	
	/**
	 * 登录执行
	 * @return String
	 * @throws Exception
	 */
	public String loginExecute() throws Exception
	{
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		JspMsg rs = loginService.isLogin(username, password,sessionMap);
	 
		if(!rs.isSuccess()){
			String fieldError =(String) ((Map<?, ?>)rs.getBackParam()).get("FieldError");
			this.addFieldError(fieldError, rs.getMsg());
			return INPUT;
		}
		else{
			
			log.info(username+"登陆成功: 登录ip "+request.getRemoteAddr());
	        roles = this.loginService.getRoles(username,sessionMap);
	    if(roles.size()==0){
	    	this.addFieldError("userid","该帐号无可用角色");
			return INPUT;
	    }
	    if(roles.size()==1){//只有一个用户角色
	    	Role role=roles.get(0);	
	    	//用户单位或角色处于注销状态
	    	if("0".equals(role.getBrch_flag())||"0".equals(role.getRole_flag()))return "roles";
	    	
	    	UserInfo userInfo = (UserInfo)sessionMap.get(UserInfo.USER_INFO);
        	//获取客户端信息
        	String userAgent = request.getHeader("User-Agent");
        	ClientInfo clientInfo = new ClientInfo(userAgent);
        	String clientBrowser = clientInfo.getExplorerName() + " "+ clientInfo.getExplorerVer();
        	String clientOs = clientInfo.getOSNameAll();
        	userInfo.setUser_ip(request.getRemoteAddr());
        	userInfo.setUser_client(clientBrowser);
        	userInfo.setUser_ver(clientOs);
        	
        	userInfo.updatebyRole(role);
    		loginService.getLoginLog(userInfo);
    		sessionMap.put(LoginInterceptor.LAST_ACCESSED, System.currentTimeMillis());
    		log.info(username+"登陆成功: 登录ip "+request.getRemoteAddr());
	    	
	    	return SUCCESS;

	    }else {
	    	sessionMap.put(LoginInterceptor.LAST_ACCESSED, System.currentTimeMillis());
	    	return "roles";
	    }
	    
		}
	

			
		

	}
  
	/**
	 * 注销
	 * @return String
	 * @throws Exception
	 */
	public String loginOff() throws Exception{
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		UserInfo userInfo=(UserInfo)sessionMap.get(UserInfo.USER_INFO);
		String user_name=userInfo.getUser_name();
		roles = this.loginService.getRoles(user_name,sessionMap);
		if(roles.size()>=1){
			userInfo.clearRole();
			return "roles";
		}else return loginOut();
		
	}
	/**
	 * 退出
	 * @return String
	 * @throws Exception
	 */
	public String loginOut()
	{
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		if(sessionMap!=null||sessionMap.size()>0)
		{
		String user_name=((UserInfo)sessionMap.get(UserInfo.USER_INFO)).getUser_name();
		HttpServletRequest request = ServletActionContext.getRequest();				
		HttpSession session = request.getSession();
		
		//销毁session
		session.invalidate();

		log.info(user_name+"退出成功: 登录ip "+request.getRemoteAddr());	
		}
		return "login";
	
	}
	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}
	/**
	 * @param log the log to set
	 */
	public static void setLog(Logger log) {
		LoginAction.log = log;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the loginService
	 */
	public LoginService getLoginService() {
		return loginService;
	}
	/**
	 * @param loginService the loginService to set
	 */
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	/**
	 * @return the user
	 */

}	

