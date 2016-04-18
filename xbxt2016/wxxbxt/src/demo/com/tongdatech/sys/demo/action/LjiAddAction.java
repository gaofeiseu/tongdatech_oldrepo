/**
 * File name:ParamAction.java
 * Create author:XY
 * Create date:2014-3-18
 */
package com.tongdatech.sys.demo.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.ParamsUtil;
import com.tongdatech.sys.demo.bean.*;
import com.tongdatech.sys.demo.service.*;

/**
 * @author XY
 *
 */


public class LjiAddAction extends PaginationAction implements ModelDriven<LjiAddObj>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log =  Logger.getLogger(LjiAddAction.class);
	private LjiAddObj ljiAddObj = new LjiAddObj();
	LjiAddService ljiService = new LjiAddService();


	public String init(){
		return "init";
	}

	@Pageable
	public String query() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		/**调用服务层*/
		PageUI pageui=ljiService.query(pagination,ljiAddObj);
		 /**返回JSON串*/
    	JsonUtil.ToJson(pageui,response);
		return null;
	}
	

	
	public String deleteParam() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String sns = request.getParameter("sns");
		AjaxMsg rs = ljiService.deleteParam(sns);
		JsonUtil.ToJson(rs,response);
		return null;
	}
	

	public String addParam() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg rs = ljiService.addParam(ljiAddObj);
		JsonUtil.ToJson(rs,response);
		return null;
	}
	

	public String editParam() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg rs = ljiService.editParam(ljiAddObj);
		JsonUtil.ToJson(rs,response);
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public  String load() throws Exception{
		ServletContext application = ServletActionContext.getServletContext();
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg rs = new AjaxMsg();
		try{
			
			Map paramMap = ljiService.load();
			application.setAttribute(ParamsUtil.ParamSys, paramMap.get(ParamsUtil.ParamSys));
			application.setAttribute(ParamsUtil.ParamJson,paramMap.get(ParamsUtil.ParamJson));
			log.info("加载系统参数Param成功");
			rs.setSuccess(true);rs.setMsg("成功");
		}catch (Exception e) {
			rs.setMsg("失败");
			log.error("加载系统参数Param失败",e);
		}
		JsonUtil.ToJson(rs,response);	
		return null;
	}

	@Override
	public LjiAddObj getModel() {
		return ljiAddObj;
	}
	
	public LjiAddService getParamService() {
		return ljiService;
	}

	public void setParamService(LjiAddService ljiService) {
		this.ljiService = ljiService;
	}
}