package com.tongdatech.sys.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.bean.Brch;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.service.BrchService;
import com.tongdatech.sys.util.JsonUtil;

public class BrchAction extends ActionSupport implements ModelDriven<Brch>{

	/** long serialVersionUID*/
	private static final long serialVersionUID = -3700436986222456774L;
	@SuppressWarnings("unused")
	private static Logger log =  Logger.getLogger(RoleAction.class);
	
	private Brch brch = new Brch();
	private BrchService brchService = new BrchService();

	

	@Override
	public Brch getModel() {
		return brch;
	}
	
	
	public String init() {
		return "init";
	}
	
	public String queryOne() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Brch brchs = brchService.queryOne(brch.getBrch_no());
		JsonUtil.ToJson(brchs, response);
		return null;
	}
	
	public String brchSave() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg am = brchService.brchSave(brch);
		JsonUtil.ToJson(am, response);
		return  null;
	}
	
	public String brchEdit() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg am = brchService.brchEdit(brch);
		JsonUtil.ToJson(am, response);
		return null;
	}
	
	public String idCheck() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		//log.info("brch_no="+brch.getBrch_no()+"brch_code="+brch.getBrch_code());
		AjaxMsg am = brchService.idCheck(brch);
		JsonUtil.ToJson(am, response);
		
		return null;
	}
	
	public String isChild() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg am = brchService.isChild(brch);
		JsonUtil.ToJson(am, response);
		
		return null;
	}

}
