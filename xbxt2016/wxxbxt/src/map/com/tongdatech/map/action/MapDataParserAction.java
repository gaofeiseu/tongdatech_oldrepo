package com.tongdatech.map.action;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map.bean.MapDataParserBean;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.service.MapDataParserService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.util.JsonUtil;

public class MapDataParserAction extends PaginationAction implements ModelDriven<MapDataParserBean>{

	private static final long serialVersionUID = 1470534243147734304L;
	private static Logger log =  Logger.getLogger(MapDataParserAction.class);
	private MapDataParserBean bean = new MapDataParserBean();
	private MapDataParserService service = new MapDataParserService();
	private File[] upload;
	
	public String init(){
		return "init";
	}
	
	public void uploadTXT() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.uploadTXT(upload,bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	
	
	
	
	
	
	
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		MapDataParserAction.log = log;
	}
	public MapDataParserBean getBean() {
		return bean;
	}
	public void setBean(MapDataParserBean bean) {
		this.bean = bean;
	}
	public MapDataParserService getService() {
		return service;
	}
	public void setService(MapDataParserService service) {
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
	public MapDataParserBean getModel() {
		return bean;
	}
}
