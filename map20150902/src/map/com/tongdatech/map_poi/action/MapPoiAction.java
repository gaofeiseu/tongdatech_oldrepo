package com.tongdatech.map_poi.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map_poi.bean.MapPoiBean;
import com.tongdatech.map_poi.bean.MapPoiReturnBean;
import com.tongdatech.map_poi.service.MapPoiService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.util.JsonUtil;

public class MapPoiAction  extends PaginationAction implements ModelDriven<MapPoiBean>{
	private static final long serialVersionUID = 7663648737860249264L;
	private static Logger log =  Logger.getLogger(MapPoiAction.class);
	private MapPoiBean bean = new MapPoiBean();
	private MapPoiService service = new MapPoiService();
	public String init(){
		return "init";
	}
	
	public String init2(){
		return "init2";
	}
	
	public void query() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg am = new AjaxMsg();
		am = service.query(bean);
		JsonUtil.ToJson(am, response);
	}
	
	public void runsql() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg am = new AjaxMsg();
		am = service.runsql(bean);
		JsonUtil.ToJson(am, response);
	}
	
	
	public void getData() throws Exception{
		MapPoiReturnBean returnBean = new MapPoiReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.getData(bean);
		JsonUtil.ToJson(returnBean, response);
		return;
	}
	
	
	
	
	
	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		MapPoiAction.log = log;
	}

	public MapPoiBean getBean() {
		return bean;
	}

	public void setBean(MapPoiBean bean) {
		this.bean = bean;
	}

	public MapPoiService getService() {
		return service;
	}

	public void setService(MapPoiService service) {
		this.service = service;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public MapPoiBean getModel() {
		return bean;
	}
}
