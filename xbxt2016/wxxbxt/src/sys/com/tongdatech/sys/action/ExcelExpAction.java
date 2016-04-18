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
    
	/** InputStream excelStream ��������Ӧstruts-system.xml�����õ�excelStream*/
	private InputStream excelStream; 
	/** String fileName ����excel�ļ���<br>��Ӧstruts-system.xml�е�${fileName}*/
	private String fileName; 
	/** AjaxMsg msg*/
	private AjaxMsg msg;
	private ExcelExpBean excelExpBean;

	public String execute() throws Exception {
		msg = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();

		File excelFile = excelExpBean.getExcelFile();
		fileName  = excelExpBean.getFileName();
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// �����ļ������ı���ΪISO-8859-1  // ������ȷ��ʾ
		if (excelFile != null) {
			try {
				excelStream = new FileInputStream(excelFile);
				return SUCCESS;
			} catch (IOException e1) {
				msg.setSuccess(false);
				msg.setMsg("����EXCELʧ��");
				JsonUtil.ToJson(msg, response);
				return null;
			}
		}else{
			msg.setSuccess(false);
			msg.setMsg("����EXCELʧ��:excel�ļ��ǿյġ�");
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
