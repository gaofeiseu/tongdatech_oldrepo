package com.tongdatech.xbxt.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.xbxt.bean.XBMobile;
import com.tongdatech.xbxt.service.XBMobileLoginService;

public class XBMobileLoginAction extends PaginationAction implements ModelDriven<XBMobile>{
	private static final long serialVersionUID = 1L;
	private XBMobileLoginService service = new XBMobileLoginService();
	private XBMobile bean = new XBMobile();
	
	public void checkPassword() throws Exception {
		Map<String,Object> session = ActionContext.getContext().getSession();
		AjaxMsg am = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();
		if(bean.getUsername()!=null&&!"".equals(bean.getUsername().trim())&&bean.getPassword()!=null&&!"".equals(bean.getPassword().trim())){
			am = service.checkPassword(bean,session);
		}else{
			am.setSuccess(false);
			am.setMsg("请输入完整的用户名和密码！");
		}
		JsonUtil.ToJson(am, response);
	}
	
	@Override
	public XBMobile getModel() {
		return bean;
	}

	public XBMobileLoginService getService() {
		return service;
	}

	public void setService(XBMobileLoginService service) {
		this.service = service;
	}

	public XBMobile getBean() {
		return bean;
	}

	public void setBean(XBMobile bean) {
		this.bean = bean;
	}
}
