package com.tongdatech.area_map.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.area_map.bean.AreaMapBean;
import com.tongdatech.area_map.bean.AreaMapReturnBean;
import com.tongdatech.area_map.service.AreaMapService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.util.JsonUtil;

public class AreaMapAction extends PaginationAction implements ModelDriven<AreaMapBean>{
	private static final long serialVersionUID = 4632046248315925092L;
	private static Logger log = Logger.getLogger(AreaMapBean.class);
	private AreaMapBean bean = new AreaMapBean();
	private AreaMapService service = new AreaMapService();
	public String init(){
		return "init";
	}
	public void insertCLOB() throws Exception{
		AreaMapReturnBean returnBean = new AreaMapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.insertCLOB(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	public void selectCLOB() throws Exception{
		AreaMapReturnBean returnBean = new AreaMapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.selectCLOB(bean);
		JsonUtil.ToJson(returnBean, response);
	}
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		AreaMapAction.log = log;
	}
	public AreaMapBean getBean() {
		return bean;
	}
	public void setBean(AreaMapBean bean) {
		this.bean = bean;
	}
	public AreaMapService getService() {
		return service;
	}
	public void setService(AreaMapService service) {
		this.service = service;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public AreaMapBean getModel() {
		return bean;
	}
}
