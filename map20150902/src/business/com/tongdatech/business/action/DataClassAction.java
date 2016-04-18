package com.tongdatech.business.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.business.bean.DataClassBean;
import com.tongdatech.business.service.DataClassService;
 
import com.tongdatech.map.util.PubFunc;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.util.JsonUtil;


 

public class DataClassAction  extends PaginationAction  implements  ModelDriven<DataClassBean>
{

	private static final long serialVersionUID = -12287761761261387L;
 
 
	private static Logger log =  Logger.getLogger(DataClassAction.class);
	private DataClassBean bean = new DataClassBean();
	private DataClassService service = new DataClassService();
	
	public String init(){
		return "init";
	}
	public void addchildclass() throws Exception{
		AjaxMsg am = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		int r = service.addchildclass(bean,userInfo);
		if(r>0){
			am.setSuccess(true);
			am.setMsg("数据类型新增成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("数据类型新增失败！");
		}
		JsonUtil.ToJson(am,response);
		return;
	}
	
	public void submit_classproperty_add() throws Exception{
		AjaxMsg am = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();
		int r = service.submit_classproperty_add(bean);
		if(r>0){
			am.setSuccess(true);
			am.setMsg("数据类型属性新增成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("数据类型属性新增失败！");
		}
		JsonUtil.ToJson(am,response);
		return;
	}
	
	public void editchildclass() throws Exception{
		AjaxMsg am = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();
		int r = service.editchildclass(bean);
		if(r>0){
			am.setSuccess(true);
			am.setMsg("数据类型修改成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("数据类型修改失败！");
		}
		JsonUtil.ToJson(am,response);
		return;
	}
	
	public void deletechildclass() throws Exception{
		AjaxMsg am = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();
		int r = service.deletechildclass(bean);
		if(r>0){
			am.setSuccess(true);
			am.setMsg("数据类型删除成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("数据类型删除失败！");
		}
		JsonUtil.ToJson(am, response);
		return;
	}
	
	public void deleteclassproperty() throws Exception{
		AjaxMsg am = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();
		int r = service.deleteclassproperty(bean);
		if(r>0){
			am.setSuccess(true);
			am.setMsg("数据类型属性删除成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("数据类型属性删除失败！");
		}
		JsonUtil.ToJson(am, response);
		return;
	}
	public void editclassproperty() throws Exception{
		AjaxMsg am = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();
		int r = service.editclassproperty(bean);
		if(r>0){
			am.setSuccess(true);
			am.setMsg("数据类型属性修改成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("数据类型属性修改失败！");
		}
		JsonUtil.ToJson(am, response);
		return;
	}
	
	@Pageable
	public void loaddata1() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		PageUI pageui=service.loaddata1(pagination,bean);
    	JsonUtil.ToJson(pageui,response);
    	return;
	}
	
	@Pageable
	public void loaddata2() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		PageUI pageui=service.loaddata2(pagination,bean);
    	JsonUtil.ToJson(pageui,response);
    	return;
	}
	
	
	public String checkTableName() throws Exception{
		AjaxMsg am = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();
		int r=service.checkTableName(bean);
		if(r==0){
			am.setSuccess(true);

		}else{
			am.setSuccess(false);
			am.setMsg("表名已存在，请重新输入");
		}
    	JsonUtil.ToJson(am,response);
    	return null;
	}
	
	public String checkColName() throws Exception{

		Map<String, Object> map=new HashMap<String, Object>(); 
	 
		int r=service.checkColName(bean);
		if(r==0){
		 
			map.put("if_success", "true");
			map.put("msg","");
		}else{
			map.put("if_success", "false");
			map.put("msg","列名已存在，请重新输入");
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		PubFunc.jsonOutput(response, map);
    	return null;
	}
	
	@Override
	public DataClassBean getModel() {
		// TODO Auto-generated method stub
		return bean;
	}
	public DataClassService getService() {
		return service;
	}
	public void setService(DataClassService service) {
		this.service = service;
	}

}