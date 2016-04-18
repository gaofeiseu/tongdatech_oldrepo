package com.tongdatech.report.action;

import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.report.bean.ReportBean;
import com.tongdatech.report.service.ReportService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.util.JsonUtil;

public class ReportAction extends PaginationAction implements ModelDriven<ReportBean>{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ReportAction.class);
	private ReportBean bean = new ReportBean();
	private ReportService service = new ReportService();
	public String init(){
		return "init";
	}
	public String init2(){
		return "init2";
	}
	/**
	 * 可能暂时不用了
	 * @throws Exception
	 */
	public void query_table1() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg am = new AjaxMsg();
		System.out.println("查询数据！");
		am = service.query_table_1(bean);
		JsonUtil.ToJson(am, response);
	}
	
	public void run_rz() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg am = new AjaxMsg();
		System.out.println("执行汇总！");
		am = service.run_rz(bean);
		JsonUtil.ToJson(am, response);
	}
	/**
	 * 有下面的替代方法了
	 * @throws Exception
	 */
	public void getexcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg am = new AjaxMsg();
		am = service.getexcel(bean);
		JsonUtil.ToJson(am, response);
	}
	/**
	 * 有下面的替代方法了
	 * @throws Exception
	 */
	public void getexcel2() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg am = new AjaxMsg();
		am = service.getexcel2(bean);
		JsonUtil.ToJson(am, response);
	}
	
	public String doExcelOne() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> sessionMap = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) sessionMap.get(UserInfo.USER_INFO);
		AjaxMsg am = new AjaxMsg();
		am = service.doExcelOne(bean,userinfo);
		if(Integer.parseInt(am.getBackParam().toString())>0){
			am = excelOne(userinfo);
		}else{
			am.setSuccess(false);
			am.setMsg("您当前权限下在"+bean.getTime()+"日期查询不到数据！无法执行导出操作！");
		}
		JsonUtil.ToJson(am, response);
		return null;
	}
	
	public String doExcelTwo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> sessionMap = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) sessionMap.get(UserInfo.USER_INFO);
		AjaxMsg am = new AjaxMsg();
		am = service.doExcelTwo(bean,userinfo);
		if(Integer.parseInt(am.getBackParam().toString())>0){
			am = excelTwo(userinfo);
		}else{
			am.setSuccess(false);
			am.setMsg("您当前权限下在"+bean.getTime()+"日期查询不到数据！无法执行导出操作！");
		}
		JsonUtil.ToJson(am, response);
		return null;
	}
	
	private AjaxMsg excelOne(UserInfo userinfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		int type = Integer.parseInt(bean.getType());
		am = service.excelOne(bean,userinfo,excelEdition,type);
		return am;
	}
	private AjaxMsg excelTwo(UserInfo userinfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		int type = Integer.parseInt(bean.getType());
		am = service.excelTwo(bean,userinfo,excelEdition,type);
		return am;
	}
	
	public ReportBean getModel() {
		return bean;
	}
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		ReportAction.log = log;
	}
	public ReportBean getBean() {
		return bean;
	}
	public void setBean(ReportBean bean) {
		this.bean = bean;
	}
	public ReportService getService() {
		return service;
	}
	public void setService(ReportService service) {
		this.service = service;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
