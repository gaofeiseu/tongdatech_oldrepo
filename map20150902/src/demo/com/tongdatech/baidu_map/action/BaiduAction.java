package com.tongdatech.baidu_map.action;

import java.io.File;

import org.apache.log4j.Logger;

 

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.baidu_map.bean.BaiduBean;
import com.tongdatech.baidu_map.service.BaiduService;
import com.tongdatech.echarts_front.action.EchartsDemoAction;
import com.tongdatech.sys.base.PaginationAction;

public class BaiduAction  extends PaginationAction implements ModelDriven<BaiduBean>{
	private static final long serialVersionUID = -2502344836654573712L;
	private static Logger log =  Logger.getLogger(EchartsDemoAction.class);
	private BaiduBean bean = new BaiduBean();
	private BaiduService service = new BaiduService();
	private File[] upload;
	
	public String init1(){
		System.out.println("°Ù¶È--------------->init1");
		return "init1";
	}
	
	
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		BaiduAction.log = log;
	}
	public BaiduBean getBean() {
		return bean;
	}
	public void setBean(BaiduBean bean) {
		this.bean = bean;
	}
	public BaiduService getService() {
		return service;
	}
	public void setService(BaiduService service) {
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
	public BaiduBean getModel() {
		return bean;
	}
}
