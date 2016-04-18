/**
 * File name:ParamAction.java
 * Create author:XY
 * Create date:2014-3-18
 */
package com.tongdatech.sys.action;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.bean.Param;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.service.ParamService;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.ParamsUtil;

/**
 * @author XY
 *
 */
public class ParamAction extends PaginationAction implements ModelDriven<Param>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log =  Logger.getLogger(ParamAction.class);
	private Param param = new Param();
	ParamService paramService = new ParamService();


	public String init(){
		return "init";
	}

	@Pageable
	public String query() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		/**调用服务层*/
		PageUI pageui=paramService.query(pagination,param);
		 /**返回JSON串*/
    	JsonUtil.ToJson(pageui,response);
		return null;
	}
	

	
	public String deleteParam() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String sns = request.getParameter("sns");
		AjaxMsg rs = paramService.deleteParam(sns);
		JsonUtil.ToJson(rs,response);
		return null;
	}
	

	public String addParam() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg rs = paramService.addParam(param);
		JsonUtil.ToJson(rs,response);
		return null;
	}
	

	public String editParam() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg rs = paramService.editParam(param);
		JsonUtil.ToJson(rs,response);
		return null;
	}
	
	public String getUserType() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		List<Map> list= paramService.getUserType(userInfo);
		JsonUtil.ToJson(list,response);
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public  String load() throws Exception{
		ServletContext application = ServletActionContext.getServletContext();
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg rs = new AjaxMsg();
		try{
			
			Map paramMap = paramService.load();
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
	public Param getModel() {
		return param;
	}
	
	public ParamService getParamService() {
		return paramService;
	}

	public void setParamService(ParamService paramService) {
		this.paramService = paramService;
	}
}