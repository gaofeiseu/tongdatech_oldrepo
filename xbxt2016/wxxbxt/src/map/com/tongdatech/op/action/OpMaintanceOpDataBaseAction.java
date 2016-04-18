package com.tongdatech.op.action;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.op.bean.OpDataGridBean;
import com.tongdatech.op.bean.OpMaintanceOpDataBaseBean;
import com.tongdatech.op.bean.OpReturnBean;
import com.tongdatech.op.service.OpMaintanceOpDataBaseService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.util.JsonUtil;

public class OpMaintanceOpDataBaseAction extends PaginationAction implements ModelDriven<OpMaintanceOpDataBaseBean>{
	private static final long serialVersionUID = -5645084437482427145L;
	private static Logger log =  Logger.getLogger(OpMaintanceOpJsAction.class);
	private OpMaintanceOpDataBaseBean bean = new OpMaintanceOpDataBaseBean();
	private OpMaintanceOpDataBaseService service = new OpMaintanceOpDataBaseService();
	private File[] upload;
	
	public String init(){
		System.out.println("É¾³ýÊý¾Ý¿â±í--------->init");
		return "init";
	}
	
	public void query() throws Exception{
		OpDataGridBean returnBean = new OpDataGridBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.query(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void dodelete() throws Exception{
		OpReturnBean returnBean = new OpReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.dodelete(bean);
		JsonUtil.ToJson(returnBean, response);
		return;
	}
	
	
	
	
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		OpMaintanceOpDataBaseAction.log = log;
	}
	public OpMaintanceOpDataBaseBean getBean() {
		return bean;
	}
	public void setBean(OpMaintanceOpDataBaseBean bean) {
		this.bean = bean;
	}
	public OpMaintanceOpDataBaseService getService() {
		return service;
	}
	public void setService(OpMaintanceOpDataBaseService service) {
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
	public OpMaintanceOpDataBaseBean getModel() {
		return bean;
	}
}
