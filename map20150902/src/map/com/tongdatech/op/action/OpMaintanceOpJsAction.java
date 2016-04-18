package com.tongdatech.op.action;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.op.bean.OpMaintanceOpJsBean;
import com.tongdatech.op.bean.OpReturnBean;
import com.tongdatech.op.service.OpMaintanceOpJsService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.util.JsonUtil;

public class OpMaintanceOpJsAction extends PaginationAction implements ModelDriven<OpMaintanceOpJsBean>{
	private static final long serialVersionUID = 2203063350378355901L;
	private static Logger log =  Logger.getLogger(OpMaintanceOpJsAction.class);
	private OpMaintanceOpJsBean bean = new OpMaintanceOpJsBean();
	private OpMaintanceOpJsService service = new OpMaintanceOpJsService();
	private File[] upload;
	
	public String init(){
		System.out.println("地图显示参数修改-----init");
		return "init";
	}
	
	public void dosubmit() throws Exception{
		OpReturnBean returnBean = new OpReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.dosubmit(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	
	
	
	
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		OpMaintanceOpJsAction.log = log;
	}
	public OpMaintanceOpJsBean getBean() {
		return bean;
	}
	public void setBean(OpMaintanceOpJsBean bean) {
		this.bean = bean;
	}
	public OpMaintanceOpJsService getService() {
		return service;
	}
	public void setService(OpMaintanceOpJsService service) {
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
	public OpMaintanceOpJsBean getModel() {
		return bean;
	}
}
