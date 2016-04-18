package com.tongdatech.sys.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.excel.ExcelExpBean;

public class ExcelExpAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
    
	/** InputStream excelStream 输入流对应struts-system.xml中配置的excelStream*/
	private InputStream excelStream; 
	/** String fileName 导出excel文件名<br>对应struts-system.xml中的${fileName}*/
	private String fileName; 
	/** AjaxMsg msg*/
	private AjaxMsg msg;
	private ExcelExpBean excelExpBean;

	public String execute() throws Exception {
		msg = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();

		File excelFile = excelExpBean.getExcelFile();
		fileName  = excelExpBean.getFileName();
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 下载文件名中文必须为ISO-8859-1  // 才能正确显示
		if (excelFile != null) {
			try {
				excelStream = new FileInputStream(excelFile);
				return SUCCESS;
			} catch (IOException e1) {
				msg.setSuccess(false);
				msg.setMsg("创建EXCEL失败");
				JsonUtil.ToJson(msg, response);
				return null;
			}
		}else{
			msg.setSuccess(false);
			msg.setMsg("创建EXCEL失败:excel文件是空的。");
			JsonUtil.ToJson(msg, response);
			return null;
		}
	}

	public InputStream getExcelStream() {
		return excelStream;
	}
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public AjaxMsg getMsg() {
		return msg;
	}
	public void setMsg(AjaxMsg msg) {
		this.msg = msg;
	}
	
	public ExcelExpBean getExcelExpBean() {
		return excelExpBean;
	}

	public void setExcelExpBean(ExcelExpBean excelExpBean) {
		this.excelExpBean = excelExpBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
