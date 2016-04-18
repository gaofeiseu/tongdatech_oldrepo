package com.tongdatech.map.action;

import java.io.File;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map.bean.MapToolsBean;
import com.tongdatech.map.service.MapToolsService;
import com.tongdatech.sys.base.PaginationAction;

public class MapToolsAction extends PaginationAction implements ModelDriven<MapToolsBean>{
	private static final long serialVersionUID = 6850578754478982529L;
	private static Logger log = Logger.getLogger(MapToolsAction.class);
	private MapToolsBean bean = new MapToolsBean();
	private MapToolsService service = new MapToolsService();
	private File[] upload;
	
	public String init(){
		return "init";
	}
	
	
	
	
	
	
	
	
	
	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		MapToolsAction.log = log;
	}

	public MapToolsBean getBean() {
		return bean;
	}

	public void setBean(MapToolsBean bean) {
		this.bean = bean;
	}

	public MapToolsService getService() {
		return service;
	}

	public void setService(MapToolsService service) {
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

	public MapToolsBean getModel() {
		return bean;
	}
}
