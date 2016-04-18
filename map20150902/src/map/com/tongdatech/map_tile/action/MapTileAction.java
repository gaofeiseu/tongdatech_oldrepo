package com.tongdatech.map_tile.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map_tile.bean.MapTileBean;
import com.tongdatech.map_tile.bean.MapTileReturnBean;
import com.tongdatech.map_tile.service.MapTileService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.util.JsonUtil;

public class MapTileAction extends PaginationAction implements ModelDriven<MapTileBean>{
	private static final long serialVersionUID = 1L;
	private static Logger log =  Logger.getLogger(MapTileAction.class);
	private MapTileBean bean = new MapTileBean();
	private MapTileService service = new MapTileService();
	private File[] upload;
	private InputStream inputStream;
	private String file_path;
	private String fileName;
	private String[] uploadContentType; // 文件的内容类型 upload+ContentType
    private String[] uploadFileName; // 上传文件名upload+FileName
    
    
    public String init(){
		return "init";
	}
    public String init2(){
		return "init2";
	}
    public String init3(){
    	return "init3";
    }
	public void doop() throws Exception{
		MapTileReturnBean returnBean = new MapTileReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.doop(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	public void foop() throws Exception{
		MapTileReturnBean returnBean = new MapTileReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.foop(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	public String doo() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map = service.doo(bean);
		if(map==null){
			return null;
		}else{
			inputStream=(InputStream) map.get("inputStream");
			if(inputStream!=null){
				try {
		    		fileName = (String) map.get("y")+(String)map.get("exe");
					fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
					return "download";
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return null;
				}
			}else{
				return null;
			}
		}
	}
    
    
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
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
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		MapTileAction.log = log;
	}
	public MapTileBean getBean() {
		return bean;
	}
	public void setBean(MapTileBean bean) {
		this.bean = bean;
	}
	public MapTileService getService() {
		return service;
	}
	public void setService(MapTileService service) {
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
	public MapTileBean getModel() {
		return bean;
	}

}
