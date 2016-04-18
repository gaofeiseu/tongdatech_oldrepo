package com.tongdatech.map.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map.bean.MapEchoPathBean;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.service.MapEchoPathService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.util.JsonUtil;

public class MapEchoPathAction extends PaginationAction implements ModelDriven<MapEchoPathBean>{
	private static final long serialVersionUID = 7652468365180273194L;
	private static Logger log =  Logger.getLogger(MapMarkersAdditionAction.class);
	private MapEchoPathBean bean = new MapEchoPathBean();
	private MapEchoPathService service = new MapEchoPathService();
	private File[] upload;
	
	public String init(){
		return "init";
	}
	
	public void query() throws IOException{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.query(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	
	public void seticons() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.seticons(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void setLineMarkers() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.setLineMarkers(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void setAreaMarkers() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.setAreaMarkers(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		MapEchoPathAction.log = log;
	}

	public MapEchoPathBean getBean() {
		return bean;
	}

	public void setBean(MapEchoPathBean bean) {
		this.bean = bean;
	}

	public MapEchoPathService getService() {
		return service;
	}

	public void setService(MapEchoPathService service) {
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

	@Override
	public MapEchoPathBean getModel() {
		return bean;
	}
}
