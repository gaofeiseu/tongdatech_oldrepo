package com.tongdatech.map.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map.bean.MapDataInputBean;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.service.MapDataInputService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.util.JsonUtil;

public class MapDataInputAction extends PaginationAction implements ModelDriven<MapDataInputBean>{
	private static final long serialVersionUID = -7946364806189319212L;
	private static Logger log =  Logger.getLogger(MapDataInputAction.class);
	private MapDataInputBean bean = new MapDataInputBean();
	private MapDataInputService service = new MapDataInputService();
	private File[] upload;
	
	
	private String file_path;
	private String file_name;
	private InputStream inputStream;
	
	public String init(){
		return "init";
	}
	
	public void getChildClassCombo() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo)request.getSession().getAttribute("userInfo");
		returnBean = service.getChildClassCombo(bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	
	public String downloadfile(){
		String base_dir = (String) ServletActionContext.getServletContext().getAttribute("filePath");
		System.out.println("base_dir:"+base_dir);
		String base_dir2 = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
		System.out.println("base_dir2:"+base_dir2);
		@SuppressWarnings("deprecation")
		String base_dir3 = ServletActionContext.getRequest().getRealPath("/");
		System.out.println("base_dir3:"+base_dir3);
		
		String total_path = "";
		total_path =base_dir3+file_path;//delete "/"
		System.out.println("total_path:"+total_path);
		System.out.println("file_name:"+file_name);
		System.out.println("file_path:"+file_path);
    	File file=service.getFile(total_path);
    	if(file==null){
    		System.out.println("file is null!!!!!!!!!!");
    		return "init";
    	}
    	try{
    		inputStream=new FileInputStream(file);  
        	file_name=new String(file_name.getBytes("GBK"), "ISO-8859-1");
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    		return "init";
    	}
    	System.out.println("return download");
    	return "download";
	}
	
	public void getTemplat() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.getTemplat(bean);//"/ExcelSpace/"+excel_filename;
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void uploadTemplat() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		UserInfo userinfo = new UserInfo();
		Map<String,Object> session = ActionContext.getContext().getSession();
		userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		returnBean = service.uploadTemplat(upload,bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	
	
	
	
	
	
	
	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		MapDataInputAction.log = log;
	}
	public MapDataInputBean getBean() {
		return bean;
	}
	public void setBean(MapDataInputBean bean) {
		this.bean = bean;
	}
	public MapDataInputService getService() {
		return service;
	}
	public void setService(MapDataInputService service) {
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
	public MapDataInputBean getModel() {
		return bean;
	}
}
