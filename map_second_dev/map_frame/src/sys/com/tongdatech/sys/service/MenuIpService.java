/**
 * File name:
 * Create author:
 * Create date:
 */
package com.tongdatech.sys.service;

 

import javax.servlet.http.HttpServletRequest;

import com.tongdatech.sys.bean.MenuIpBean;
import com.tongdatech.sys.dao.MenuIpDao;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

 


/**
 * @author 
 *
 */
public class MenuIpService {
	MenuIpDao dao=new MenuIpDao();
	public PageUI getMenuIp(Pagination pagination,MenuIpBean bean) throws Exception {
		return dao.getMenuIp(pagination,bean);
	}
	
	public AjaxMsg menuipSave(MenuIpBean bean) throws Exception {
		return dao.menuipSave(bean);
	}
	public AjaxMsg menuipDel(MenuIpBean bean) throws Exception {
		return dao.menuipDel(bean);
	}
	public  AjaxMsg  getMenuIpNum(String menu_id,HttpServletRequest request) throws Exception {
		MenuIpBean bean=new MenuIpBean();
		String ip=getIpAddr(request);
		bean.setIp(ip);
		bean.setMenu_id(menu_id); 
		int i=dao.getMenuIpNum(bean);
		
		
		boolean t=true;
		AjaxMsg rs = new AjaxMsg(); 
		if(i==0)
		{
			t=false; 
		} 
		rs.setSuccess(t); 
		return rs;
	}
	public static String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	} 
}
