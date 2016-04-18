package com.tongdatech.business.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.business.bean.RemindBean;
import com.tongdatech.business.bean.RemindReturnBean;
import com.tongdatech.business.service.RemindService;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.service.MenuIpService;
import com.tongdatech.sys.util.JsonUtil;

public class RemindAction  extends PaginationAction implements ModelDriven<RemindBean>{
	private static final long serialVersionUID = -4170973878936144715L;
	private static Logger log =  Logger.getLogger(RemindAction.class);
	private RemindBean bean = new RemindBean();
	private RemindService service = new RemindService();
	private MenuIpService menuIpService =new MenuIpService();

	public String init() throws Exception{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		
//		boolean flag=menuIpService.getMenuIpNum("410", request);
//		if(flag)
//		{
			return "init";
//		}
//	 
//		return "ip_err";
//	 
	}
	
	public void getNum() throws Exception{
		RemindReturnBean returnBean = new RemindReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.getNum(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	@Pageable
	public void getDetail() throws Exception{
		RemindReturnBean returnBean = new RemindReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.getDetail(pagination,bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	public String goDetail() throws Exception{
		if("1".equals(bean.getQuery_type())){
			return "d1";
		}else if("2".equals(bean.getQuery_type())){
			return "d2";
		}else if("3".equals(bean.getQuery_type())){
			return "d3";
		}else if("4".equals(bean.getQuery_type())){
			return "d4";
		}else if("5".equals(bean.getQuery_type())){
			return "d5";
		}else if("6".equals(bean.getQuery_type())){
			return "d6";
		}else {
			return null;
		}
	}
	
	
	
	
	
	
	
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		RemindAction.log = log;
	}
	public RemindBean getBean() {
		return bean;
	}
	public void setBean(RemindBean bean) {
		this.bean = bean;
	}
	public RemindService getService() {
		return service;
	}
	public void setService(RemindService service) {
		this.service = service;
	}
	public MenuIpService getMenuIpService() {
		return menuIpService;
	}

	public void setMenuIpService(MenuIpService menuIpService) {
		this.menuIpService = menuIpService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public RemindBean getModel() {
		return bean;
	}
}
