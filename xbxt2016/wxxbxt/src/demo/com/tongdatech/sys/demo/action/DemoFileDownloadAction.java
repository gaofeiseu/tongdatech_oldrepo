package com.tongdatech.sys.demo.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.demo.service.DemoFileService;
import com.tongdatech.sys.pojo.JspBtn;
import com.tongdatech.sys.pojo.JspMsg;
import com.tongdatech.sys.pojo.PageUI;

public class DemoFileDownloadAction extends PaginationAction {

	/** long serialVersionUID*/
	private static final long serialVersionUID = 8496580777404978780L;
	private DemoFileService demoFileService = new DemoFileService();
	private PageUI pageui;
	
	private String file_path;
	private String file_name;
	private InputStream inputStream;
	private JspMsg  jspMsg;
	
	@Pageable
	public String list() throws Exception{
       pageui=demoFileService.list(pagination);
	   return "list";
	}
    public String down() throws Exception{
    	String base_dir = (String) ServletActionContext.getServletContext().getAttribute("filePath");
    	File file=demoFileService.getFile(base_dir+"/"+file_path);
    	if(file==null){
    		jspMsg=new JspMsg();
    		jspMsg.setMsg("文件不存在于"+base_dir);
    		JspBtn jp=new JspBtn();
        	jp.setName("返回");

        	jp.setUrl("/demoDownload_list");
        	List<JspBtn> btns = new ArrayList<JspBtn>();btns.add(jp);
        	jspMsg.setBtns(btns);
    		return "MSG";
    	}
    	inputStream=new FileInputStream(file);  
    	file_name=new String(file_name.getBytes("GBK"), "ISO-8859-1");
    	System.out.println("file_name:"+file_name);
    	return "download";
    }
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the demoFileService
	 */
	public DemoFileService getDemoFileService() {
		return demoFileService;
	}

	/**
	 * @return the pageui
	 */
	public PageUI getPageui() {
		return pageui;
	}

	/**
	 * @param demoFileService the demoFileService to set
	 */
	public void setDemoFileService(DemoFileService demoFileService) {
		this.demoFileService = demoFileService;
	}

	/**
	 * @param pageui the pageui to set
	 */
	public void setPageui(PageUI pageui) {
		this.pageui = pageui;
	}
	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @return the file_name
	 */
	public String getFile_name() {
		return file_name;
	}
	
	/**
	 * @param file_name the file_name to set
	 */
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	/**
	 * @param file_path the file_path to set
	 */
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	/**
	 * @return the file_path
	 */
	public String getFile_path() {
		return file_path;
	}
	/**
	 * @param jspMsg the jspMsg to set
	 */
	public void setJspMsg(JspMsg jspMsg) {
		this.jspMsg = jspMsg;
	}
	/**
	 * @return the jspMsg
	 */
	public JspMsg getJspMsg() {
		return jspMsg;
	}

}
