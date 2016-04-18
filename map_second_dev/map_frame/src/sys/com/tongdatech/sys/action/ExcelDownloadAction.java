package com.tongdatech.sys.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.excel.ExcelBean;

/**
 * Excel导出公用Action<br>
 * struts-system.xml中<br> 
 * action name="excel" class="com.tongdatech.sys.action.ExcelDownloadAction"
 * @author xl 
 * @version    2014-4-11 上午09:28:32
 */
public class ExcelDownloadAction extends ActionSupport {

	/** long serialVersionUID*/
	private static final long serialVersionUID = 2931707914346279910L;

	/** Logger log*/
	private static Logger log =  Logger.getLogger(ExcelDownloadAction.class);
    
	/** InputStream excelStream 输入流对应struts-system.xml中配置的excelStream*/
	private InputStream excelStream; 
	/** String fileName 导出excel文件名<br>对应struts-system.xml中的${fileName}*/
	private String fileName; 
	/** AjaxMsg msg*/
	private AjaxMsg msg;
	/** ExcelBean excelBean*/
	private ExcelBean excelBean;

	/* 执行导出
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

		msg = new AjaxMsg();

	
		HttpServletResponse response = ServletActionContext.getResponse();

		Workbook wb = excelBean.getWb();
		fileName  = excelBean.getFliename();
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 下载文件名中文必须为ISO-8859-1
																		// 才能正确显示
		if (wb != null) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				wb.write(baos);
				baos.flush();
				byte[] ba = baos.toByteArray();
				excelStream = new ByteArrayInputStream(ba);
				baos.close();
				return SUCCESS;
			} catch (IOException e1) {
				log.error("EXCEL", e1);// 创建Excel失败
				msg.setSuccess(false);
				msg.setMsg("创建EXCEL失败");

				JsonUtil.ToJson(msg, response);
				return null;
			}

		} else {
			msg.setSuccess(false);
			msg.setMsg("创建EXCEL失败");

			JsonUtil.ToJson(msg, response);
			return null;
		}
	}

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public static void setLog(Logger log) {
		ExcelDownloadAction.log = log;
	}

	/**
	 * @return the excelStream
	 */
	public InputStream getExcelStream() {
		return excelStream;
	}

	/**
	 * @param excelStream the excelStream to set
	 */
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the msg
	 */
	public AjaxMsg getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(AjaxMsg msg) {
		this.msg = msg;
	}

	/**
	 * @return the excelBean
	 */
	public ExcelBean getExcelBean() {
		return excelBean;
	}

	/**
	 * @param excelBean the excelBean to set
	 */
	public void setExcelBean(ExcelBean excelBean) {
		this.excelBean = excelBean;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
