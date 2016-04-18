package com.tongdatech.map.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.bean.MapUpdateBean;
import com.tongdatech.map.service.MapUpdateService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.util.JsonUtil;

public class MapUpdateAction extends PaginationAction implements ModelDriven<MapUpdateBean>{

	private static final long serialVersionUID = -8271991542439500235L;
	private static Logger log =  Logger.getLogger(MapUpdateAction.class);
	private MapUpdateBean bean = new MapUpdateBean();
	private MapUpdateService service = new MapUpdateService();
	private File[] upload;
	
	public String init(){
		return "init";
	}
	
	public void dodownload() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("downloadbean:"+bean.toString());
		String webroot_path = request.getSession().getServletContext().getRealPath("/");
		returnBean = service.dodownload(bean,webroot_path);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	
	
	
	
	
	
	
	
	
	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		MapUpdateAction.log = log;
	}

	public MapUpdateBean getBean() {
		return bean;
	}

	public void setBean(MapUpdateBean bean) {
		this.bean = bean;
	}

	public MapUpdateService getService() {
		return service;
	}

	public void setService(MapUpdateService service) {
		this.service = service;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MapUpdateBean getModel() {
		return bean;
	}
}
