package com.tongdatech.map_mobile.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map_mobile.bean.FileOPBean;
import com.tongdatech.map_mobile.bean.JspBtn;
import com.tongdatech.map_mobile.bean.JspMsg;
import com.tongdatech.map_mobile.bean.MobileReturnBean;
import com.tongdatech.map_mobile.service.FileOPService;
import com.tongdatech.map_mobile.utils.JsonUtils;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.pojo.PageUI;

public class FileOPAction extends PaginationAction implements ModelDriven<FileOPBean>{
	private static final long serialVersionUID = 5427747693256909684L;
	private static Logger log = Logger.getLogger(FileOPAction.class);
	private FileOPBean bean = new FileOPBean();
	private FileOPService service = new FileOPService();
	private File[] upload;
	private InputStream inputStream;
	private JspMsg  jspMsg;
	private String file_path;
	private String fileName;
	private String[] uploadContentType; // 文件的内容类型 upload+ContentType
    private String[] uploadFileName; // 上传文件名upload+FileName
	
	public String uploadinit(){
		return "uploadinit";
	}
	public String downloadinit(){
		return "downloadinit";
	}
	
	public void fileupload(){
		MobileReturnBean returnBean = new MobileReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.fileupload(bean,upload,uploadContentType,uploadFileName);
		try {
			JsonUtils.ToJsonStr(returnBean, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return;
	}
	@Pageable
	public void getData1(){
		HttpServletResponse response = ServletActionContext.getResponse();
		PageUI p=service.getData1(pagination,bean);
		try {
			JsonUtils.ToJsonStr(p,response);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public String downloadfile(){
		Map<String,Object> map = new HashMap<String,Object>();
		map = service.downloadfile(bean);
		if(map==null){
			jspMsg = new JspMsg();
			jspMsg.setMsg("文件的相关信息在数据库中不存在，请联系管理员");
			JspBtn jp=new JspBtn();
        	jp.setName("返回");

        	jp.setUrl("/fileop_downloadinit.action");
        	List<JspBtn> btns = new ArrayList<JspBtn>();
        	btns.add(jp);
        	jspMsg.setBtns(btns);
    		return "MSG";
		}
		file_path = String.valueOf(map.get("file_save_path"));
		
    	File file=service.getFile(file_path);
    	if(file==null){
    		jspMsg=new JspMsg();
    		jspMsg.setMsg("文件不存在于"+file_path);
    		JspBtn jp=new JspBtn();
        	jp.setName("返回");

        	jp.setUrl("/fileop_downloadinit.action");
        	List<JspBtn> btns = new ArrayList<JspBtn>();
        	btns.add(jp);
        	jspMsg.setBtns(btns);
    		return "MSG";
    	}
    	try {
			inputStream=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	try {
    		fileName = String.valueOf(map.get("old_file_name"));
    		System.out.println("filename:"+fileName);
			fileName=new String(fileName.getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	return "download";
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
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public JspMsg getJspMsg() {
		return jspMsg;
	}
	public void setJspMsg(JspMsg jspMsg) {
		this.jspMsg = jspMsg;
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
		FileOPAction.log = log;
	}
	public FileOPBean getBean() {
		return bean;
	}
	public void setBean(FileOPBean bean) {
		this.bean = bean;
	}
	public FileOPService getService() {
		return service;
	}
	public void setService(FileOPService service) {
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
	public FileOPBean getModel() {
		return bean;
	}
}
