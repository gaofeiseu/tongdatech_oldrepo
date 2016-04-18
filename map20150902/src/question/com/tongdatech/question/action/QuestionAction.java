package com.tongdatech.question.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.question.bean.QuestionBean;
import com.tongdatech.question.service.QuestionService;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.util.JsonUtil;

public class QuestionAction extends PaginationAction implements ModelDriven<QuestionBean>{
	private static final long serialVersionUID = 1L;
	private static Logger log =  Logger.getLogger(QuestionAction.class);
	private QuestionBean bean = new QuestionBean();
	private QuestionService service = new QuestionService();
	
	public String init(){
		return "init";
	}
	public String init2(){
		return "init2";
	}
	public String init3(){
		return "init3";
	}
	@Pageable
	public void query() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.query(pagination,bean,userinfo);
		JsonUtil.ToJson(rs, response);
	}
	@Pageable
	public void queryForLock() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.queryForLock(pagination,bean,userinfo);
		JsonUtil.ToJson(rs, response);
	}
	@Pageable
	public void queryForSearch() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.queryForSearch(pagination,bean,userinfo);
		JsonUtil.ToJson(rs, response);
	}
	
	public void saveQuestion() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg am = service.saveQuestion(bean,userinfo);
		JsonUtil.ToJson(am, response);
	}
	
	public void replyQuestion() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg am = service.replyQuestion(bean,userinfo);
		JsonUtil.ToJson(am, response);
	}
	
	public void deleteQuestion() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg am = service.deleteQuestion(bean,userinfo);
		JsonUtil.ToJson(am, response);
	}
	
	public void lockQuestion() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg am = service.lockQuestion(bean,userinfo);
		JsonUtil.ToJson(am, response);
	}
	
	public QuestionBean getModel() {
		return bean;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		QuestionAction.log = log;
	}

	public QuestionBean getBean() {
		return bean;
	}

	public void setBean(QuestionBean bean) {
		this.bean = bean;
	}

	public QuestionService getService() {
		return service;
	}

	public void setService(QuestionService service) {
		this.service = service;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
