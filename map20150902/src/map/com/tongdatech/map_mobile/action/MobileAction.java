package com.tongdatech.map_mobile.action;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map_mobile.bean.MobileBean;
import com.tongdatech.map_mobile.bean.MobileReturnBean;
import com.tongdatech.map_mobile.service.MobileService;
import com.tongdatech.map_mobile.utils.JsonUtils;
import com.tongdatech.sys.base.PaginationAction;

public class MobileAction extends PaginationAction implements
		ModelDriven<MobileBean> {
	private static final long serialVersionUID = 1198409455656155496L;
	private static Logger log = Logger.getLogger(MobileAction.class);
	private MobileBean bean = new MobileBean();
	private MobileService service = new MobileService();
	private File[] upload;
	private String[] uploadContentType; // 文件的内容类型 upload+ContentType
    private String[] uploadFileName; // 上传文件名upload+FileName
    
	public String init() {
		return "init";
	}

	public void login() throws Exception {
		MobileReturnBean returnBean = new MobileReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.login(bean);
		JsonUtils.ToJsonStr(returnBean, response);
		return;
	}
	
	public void check_in() throws Exception{
		String result = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		result = service.check_in(bean,upload,uploadContentType,uploadFileName);
		JsonUtils.sendString(result, response);
		return;
	}
	
	public void getHistory() throws Exception{
		MobileReturnBean returnBean = new MobileReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.getHistory(bean);
		JsonUtils.ToJsonStr(returnBean, response);
	}
	/**
	 * 获取该用户的所查询的时间段内的所有满足条件的签到记录的个数
	 * @throws Exception
	 */
	public void getHistoryTotalNum() throws Exception{
		MobileReturnBean returnBean = new MobileReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.getHistoryTotalNum(bean);
		JsonUtils.ToJsonStr(returnBean, response);
	}
	
	public void checkUpdate() throws Exception {
		MobileReturnBean returnBean = new MobileReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.checkUpdate(bean);
		JsonUtils.ToJsonStr(returnBean, response);
		return;
	}
	

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		MobileAction.log = log;
	}

	public MobileBean getBean() {
		return bean;
	}

	public void setBean(MobileBean bean) {
		this.bean = bean;
	}

	public MobileService getService() {
		return service;
	}

	public void setService(MobileService service) {
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

	public MobileBean getModel() {
		return bean;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
}
