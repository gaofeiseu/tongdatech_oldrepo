package com.tongdatech.sys.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.util.DBUtil;

/**
 * 异常拦截器 <br>
 * 记录访问URI到t_sys_log_uri <br>
 * 记录异常到日志
 * @author xl 
 * @version    2014-4-11 上午10:37:13
 */
public class ExceptionInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1008901298342362080L;
	private static final Logger log = Logger.getLogger(ExceptionInterceptor.class);


	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String,Object> session=invocation.getInvocationContext().getSession();
		
		UserInfo user = (UserInfo) session.get("userInfo");
		int user_id = user.getUser_id();	//用户ID
		String nick_name = user.getNick_name();	//昵称
		String brch_no = user.getBrch_no();	//机构号
		String brch_name = user.getBrch_name();		//机构名称
		String area_no = user.getArea_no();		//所属地区
		String area_name = user.getArea_name();		//地区名称
		int role_id = user.getRole_id();	//角色号
		String role_name =user.getRole_name();		//角色名称
	
		String actionName = invocation.getInvocationContext().getName();
		HttpServletRequest request= (HttpServletRequest)(invocation.getInvocationContext()).get(StrutsStatics.HTTP_REQUEST);
		String uri=request.getRequestURI();
		String url=request.getRequestURL().toString()+"?"+request.getQueryString();
		//String url=request.getQueryString();
		String user_ip = request.getRemoteAddr();
		
	    long startTime = System.currentTimeMillis();//计算开始日期
		try {
			String result = invocation.invoke();
			return result;
		} catch (Exception e) {
			log.error(actionName + "|errNo:" + startTime , e);
			session.put("errNo", startTime);	
			throw  e;
			
		}finally{
			long executionTime = System.currentTimeMillis() - startTime;
	        String sql="insert into t_sys_log_uri (log_date,user_id,nick_name,uri,url,brch_no,brch_name,area_no,area_name,role_id,role_name,exectime,user_ip) VALUES " +
	        		"( sysdate, "+user_id+", '"+nick_name+"', '"+uri+"', '"+url+"', "+brch_no+", '"+brch_name+"','"+area_no+"','"+area_name+"'," +
	        		""+role_id+",'"+role_name+"',"+executionTime+",'"+user_ip+"' )";
	        DBUtil db=new DBUtil();
	        try { 
	        	db.insert(sql);
			} catch (Exception e) {
				log.error("ExceptionInterceptor", e);
			}
		}
	}

}
