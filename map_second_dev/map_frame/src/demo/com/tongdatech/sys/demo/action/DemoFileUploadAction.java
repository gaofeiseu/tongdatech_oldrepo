package com.tongdatech.sys.demo.action;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongdatech.sys.demo.service.DemoFileService;
import com.tongdatech.sys.pojo.JspBtn;
import com.tongdatech.sys.pojo.JspMsg;

public class DemoFileUploadAction  extends ActionSupport{

	private static final long serialVersionUID = 1590211768071065187L;
	private File[]   upload;// 实际上传文件
    private String[] uploadContentType; // 文件的内容类型 upload+ContentType
    private String[] uploadFileName; // 上传文件名upload+FileName
    
    private JspMsg  jspMsg;
    
    private DemoFileService demoFileService = new DemoFileService();
    public String init(){
    	return "input";
    }
    
    public String doUp() throws Exception{
    	String base_dir = (String) ServletActionContext.getServletContext().getAttribute("filePath");
    	jspMsg =new JspMsg();jspMsg.setSuccess(true);
    	for(int i=0;i<upload.length;i++){
    		JspMsg tmp=demoFileService.save(upload[i],base_dir,uploadContentType[i],uploadFileName[i]);
    		if(!tmp.isSuccess())jspMsg.add(tmp);
    	}
    	JspBtn jp=new JspBtn();
    	jp.setName("文件下载");
    	String url="demoUpload_init";
    	url=URLEncoder.encode(url, "GBK");
    	jp.setUrl("/demoDownload_list?back_url="+url);
    	jp.setIconCls("icon-download");
    	
    	
    	JspBtn jp2=new JspBtn();
    	jp2.setName("文件上传");jp2.setIconCls("icon-upload");
    	jp.setUrl("/demoUpload_init");
    	List<JspBtn> btns = new ArrayList<JspBtn>();
    	btns.add(jp2);
    	btns.add(jp);
    	jspMsg.setBtns(btns);
		return "MSG";
    }
    
    
	/**
	 * @return the upload
	 */
	public File[] getUpload() {
		return upload;
	}
	/**
	 * @param upload the upload to set
	 */
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	/**
	 * @return the uploadContentType
	 */
	public String[] getUploadContentType() {
		return uploadContentType;
	}
	/**
	 * @param uploadContentType the uploadContentType to set
	 */
	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	/**
	 * @return the uploadFileName
	 */
	public String[] getUploadFileName() {
		return uploadFileName;
	}
	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the jspMsg
	 */
	public JspMsg getJspMsg() {
		return jspMsg;
	}

	/**
	 * @param jspMsg the jspMsg to set
	 */
	public void setJspMsg(JspMsg jspMsg) {
		this.jspMsg = jspMsg;
	}
    
    
}
