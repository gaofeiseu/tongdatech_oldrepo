package com.tongdatech.xbxt.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map_mobile.utils.JsonUtils;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.xbxt.bean.XBMobile;
import com.tongdatech.xbxt.service.XBMobileService;

public class XBMobileAction extends PaginationAction implements ModelDriven<XBMobile>{
	private static final long serialVersionUID = 1L;
	private XBMobile bean = new XBMobile();
	private XBMobileService service = new XBMobileService();
	private File[] files;
	private String[] filesFileName;
	private String[] filesContentType;
	
	public String init_about_section() throws Exception {
		UserInfo userinfo = (UserInfo) ActionContext.getContext().getSession().get(UserInfo.USER_INFO);
		bean.setMgr(userinfo.getMgr());
		return "about_section";
	}
	
	public String init_MarketingRecord_section() throws Exception {
		UserInfo userinfo = (UserInfo) ActionContext.getContext().getSession().get(UserInfo.USER_INFO);
		bean.setMgr(userinfo.getMgr());
		List<Map<String,Object>> list = service.getCustType();
		bean.setList_custtype(list);
		return "MarketingRecord_section";
	}
	
	public String init_feequery_section() throws Exception {
		UserInfo userinfo = (UserInfo) ActionContext.getContext().getSession().get(UserInfo.USER_INFO);
		List<Map<String,Object>> list = service.fee_query(userinfo);
		bean.setList_map(list);
		return "feequery_section";
	}
	
	public String init_visit_log_section() throws Exception {
		UserInfo userinfo = (UserInfo) ActionContext.getContext().getSession().get(UserInfo.USER_INFO);
		List<Map<String,Object>> list = service.visit_log_query(userinfo);
		bean.setList_map(list);
		return "visit_log_section";
	}
	
	public String init_cust_warning_section() throws Exception {
		UserInfo userinfo = (UserInfo) ActionContext.getContext().getSession().get(UserInfo.USER_INFO);
		List<Map<String,Object>> list = service.cust_warning_query(userinfo);
		bean.setList_map(list);
		return "cust_warning_section";
	}
	
	public String init_cust_report_section() throws Exception {
		//UserInfo userinfo = (UserInfo) ActionContext.getContext().getSession().get(UserInfo.USER_INFO);
		
		return "cust_report_section";
	}
	
	public String init_cust_report_detail_section() throws Exception {
		UserInfo userinfo = (UserInfo) ActionContext.getContext().getSession().get(UserInfo.USER_INFO);
		List<Map<String,Object>> list = service.init_cust_report_detail_section(userinfo,bean);
		bean.setList_map(list);
		return "cust_report_detail_section";
	}
	
	public void ajax_find_cust() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg am = service.ajax_find_cust(bean);
		JsonUtil.ToJson(am,response);
	}
	
	public void marketing_record() throws Exception {
		String result = "";
		UserInfo userinfo = (UserInfo) ActionContext.getContext().getSession().get(UserInfo.USER_INFO);
		HttpServletResponse response = ServletActionContext.getResponse();
		String path = ServletActionContext.getServletContext().getRealPath("/");
		result = service.marketing_record(bean,files,filesFileName,filesContentType,path,userinfo);
		JsonUtils.sendString(result, response);
	}
	
	@Override
	public XBMobile getModel() {
		return bean;
	}
	public XBMobile getBean() {
		return bean;
	}
	public void setBean(XBMobile bean) {
		this.bean = bean;
	}
	public XBMobileService getService() {
		return service;
	}
	public void setService(XBMobileService service) {
		this.service = service;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}
}
