package com.tongdatech.map_mobile.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map_mobile.bean.CheckinShowBean;
import com.tongdatech.map_mobile.bean.MobileReturnBean;
import com.tongdatech.map_mobile.service.CheckinShowService;
import com.tongdatech.map_mobile.utils.JsonUtils;
import com.tongdatech.sys.base.PaginationAction;

public class CheckinShowAction  extends PaginationAction implements ModelDriven<CheckinShowBean>{
	private static final long serialVersionUID = -1229669705782821518L;
	private static Logger log = Logger.getLogger(CheckinShowAction.class);
	private CheckinShowBean bean = new CheckinShowBean();
	private CheckinShowService service = new CheckinShowService();
	private File[] upload;
	
	public String init(){
		return "init";
	}
	
	public void query() throws IOException{
		MobileReturnBean returnBean = new MobileReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.query(bean);
		try {
			JsonUtils.ToJsonStr(returnBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void getTableWithAjax() throws Exception {
		MobileReturnBean returnBean = new MobileReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.getTableWithAjax(bean);
		try {
			JsonUtils.ToJsonStr(returnBean, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return;
	}
	
	
	
	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		CheckinShowAction.log = log;
	}

	public CheckinShowBean getBean() {
		return bean;
	}

	public void setBean(CheckinShowBean bean) {
		this.bean = bean;
	}

	public CheckinShowService getService() {
		return service;
	}

	public void setService(CheckinShowService service) {
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

	public CheckinShowBean getModel() {
		return bean;
	}
}
