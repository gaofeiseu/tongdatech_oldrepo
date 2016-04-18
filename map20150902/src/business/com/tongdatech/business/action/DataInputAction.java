package com.tongdatech.business.action;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.business.bean.DataInputBean;
import com.tongdatech.business.bean.ReturnBean;
import com.tongdatech.business.service.DataInputService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.util.JsonUtil;

public class DataInputAction extends PaginationAction implements ModelDriven<DataInputBean>{
	private static final long serialVersionUID = -7946364806189319212L;
	private static Logger log =  Logger.getLogger(DataInputAction.class);
	private DataInputBean bean = new DataInputBean();
	private DataInputService service = new DataInputService();
	private File[] upload;
	
	public String init(){
		return "init";
	}
	
	public void getChildClassCombo() throws Exception{
		ReturnBean returnBean = new ReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.getChildClassCombo(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void getTemplat() throws Exception{
		ReturnBean returnBean = new ReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.getTemplat(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void uploadTemplat() throws Exception{
		ReturnBean returnBean = new ReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.uploadTemplat(upload,bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	
	
	
	
	
	
	
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		DataInputAction.log = log;
	}
 
	public DataInputBean getBean() {
		return bean;
	}

	public void setBean(DataInputBean bean) {
		this.bean = bean;
	}

	public DataInputService getService() {
		return service;
	}

	public void setService(DataInputService service) {
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
	public DataInputBean getModel() {
		return bean;
	}
}
