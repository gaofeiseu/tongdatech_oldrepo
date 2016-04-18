package com.tongdatech.sys.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.bean.Role;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.init.UserCount;
import com.tongdatech.sys.interceptor.LoginInterceptor;
import com.tongdatech.sys.service.LoginService;
import com.tongdatech.sys.util.ClientInfo;
import com.tongdatech.sys.util.JsonUtil;

/**
 * 欢迎Action<br>
 * @author xl 
 * @version    2014-4-11 上午10:01:02
 */
public class WelcomeAction extends ActionSupport implements ModelDriven<Role>{
	

	
	private static final long serialVersionUID = 5735456645456865069L;
	private Role role =new Role();
	
	/** String main_title 主框架 标题*/
	private String main_title;
    /** String main_url 主框架url*/
    private String main_url;
    private LoginService loginService=new LoginService();
    
	/**
	 * 欢迎界面-主框架界面
	 * @return String
	 * @throws Exception 
	 */
	public String wel() throws Exception{
		
//		Map<String, Object> sessionMap = ActionContext.getContext().getSession();		
//		UserInfo userInfo=(UserInfo)sessionMap.get(UserInfo.USER_INFO);
//		userInfo.updatebyRole(role);
//		return SUCCESS;
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();		
		UserInfo userInfo=(UserInfo)sessionMap.get(UserInfo.USER_INFO);
		//获取客户端信息
		HttpServletRequest request = ServletActionContext.getRequest();
		String userAgent = request.getHeader("User-Agent");
		ClientInfo clientInfo = new ClientInfo(userAgent);
		String clientBrowser = clientInfo.getExplorerName() + " "+ clientInfo.getExplorerVer();
		String clientOs = clientInfo.getOSNameAll();
		userInfo.setUser_ip(request.getRemoteAddr());
		userInfo.setUser_client(clientBrowser);
		userInfo.setUser_ver(clientOs);
		userInfo.updatebyRole(role);
		loginService.getLoginLog(userInfo);
		return SUCCESS;
	}
    /**
     * 顶部页面
     * @return String
     */
    public String head(){
		return "head";
	}
    
    /**
     * 主页面
     * @return String
     */
    public String main(){
    	boolean istabs=(Boolean) ServletActionContext.getServletContext().getAttribute("isTabs");
    	if(istabs)return"tabs";
    	else return  "main";
    }
    /**
     * 在线人数统计
     * @return String
     * @throws IOException
     */
    public String online() throws IOException{

    	int online = UserCount.count;
    	Map<String, Object> session = ActionContext.getContext().getSession();
    	long last_accessed=(Long) session.get(LoginInterceptor.LAST_ACCESSED);
    	String timeOut=(String) ServletActionContext.getServletContext().getAttribute(LoginInterceptor.projTimeOut);
		int time_out=Integer.parseInt(timeOut);
    	int remain_time=(int) ((last_accessed+time_out*60*1000-System.currentTimeMillis())/1000);
    	
    	Map<String, Integer> rs=new HashMap<String, Integer>();
    	rs.put("online", online);
    	rs.put("remain_time", remain_time);
    	HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs,response);
		return null;
    }
    
    /**
     * 登录保持
     * @return null
     */
    public String stay(){
    	return null;
    }
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Role getModel() {
		return role;
	}
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	/**
	 * @return the main_title
	 */
	public String getMain_title() {
		return main_title;
	}
	/**
	 * @param main_title the main_title to set
	 */
	public void setMain_title(String main_title) {
		this.main_title = main_title;
	}
	/**
	 * @return the main_url
	 */
	public String getMain_url() {
		return main_url;
	}
	/**
	 * @param main_url the main_url to set
	 */
	public void setMain_url(String main_url) {
		this.main_url = main_url;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
