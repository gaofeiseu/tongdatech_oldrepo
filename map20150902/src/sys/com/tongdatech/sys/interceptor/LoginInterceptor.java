package com.tongdatech.sys.interceptor;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tongdatech.sys.bean.UserInfo;

/**
 * 登录拦截器 <br>
 * 未登录的用户和登录失效用户统一跳转到登录页面
 * @author xl 
 * @version    2014-4-11 上午10:39:01
 */
@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {

	public static String AJAX_HEADER="X-Requested-With";
	public static String NO_RESET_TIMEOUT="NoTimeOut";
	public static String SESSION_STATUS="sessionstatus";
	public static String SESSION_TIME_OUT="SESSION_TIME_OUT";
	public static String LAST_ACCESSED="LAST_ACCESSED";
	public static String projTimeOut="projTimeOut";
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		
		
//        if(LoginAction.class == invocation.getAction().getClass()
//        		&&"login".equals(invocation.getInvocationContext().getName())){
//        	if(session!=null){
//        		session.setAttribute(LAST_ACCESSED, System.currentTimeMillis());
//        	}
//        	return invocation.invoke();
//        }
		
		
		
		
		
		String noTimeOut = request.getHeader(NO_RESET_TIMEOUT);
		String type = request.getHeader(AJAX_HEADER);
		String timeOut=(String) ServletActionContext.getServletContext().getAttribute(projTimeOut);
		int time_out=Integer.parseInt(timeOut);
		if(session!=null){
			Object last_accessed=session.getAttribute(LAST_ACCESSED);
			long last_time = Long.MAX_VALUE;
			if(last_accessed!=null)last_time=((Long)last_accessed).longValue()+time_out*60*1000;
			if(System.currentTimeMillis()>(last_time)){
				session.invalidate();
			}		
		}
		
		session=request.getSession(false);//session 失效后取出的为null
		if(null == session
				|| null == session.getAttribute(UserInfo.USER_INFO)
				||((UserInfo)session.getAttribute(UserInfo.USER_INFO)).getUser_id()==0
				){
			if ("XMLHttpRequest".equalsIgnoreCase(type)) {
				response.setHeader(SESSION_STATUS, SESSION_TIME_OUT);
				PrintWriter out = response.getWriter();
				out.print("{SESSION_TIME_OUT:true}");
				out.flush();
				return null;
			}
			else return "go";
		}else{
			if(noTimeOut==null||"".equals(noTimeOut)){// 普通请求记录last时间
				session.setAttribute(LAST_ACCESSED, System.currentTimeMillis());
			}
		}		
		return invocation.invoke();
	}

}
