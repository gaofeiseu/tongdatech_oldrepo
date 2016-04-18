package com.tongdatech.map.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map.bean.MapMarkerClassMaintanceBean;
import com.tongdatech.map.service.MapMarkerClassMaintanceService;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.Brch;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.util.JsonUtil;
/**
 * 
 * @author Mr.GaoFei
 *
 */
public class MapMarkerClassMaintanceAction extends PaginationAction implements ModelDriven<MapMarkerClassMaintanceBean>{

	private static final long serialVersionUID = 1853134098921785605L;
	private static Logger log =  Logger.getLogger(MapMarkerClassMaintanceAction.class);
	private MapMarkerClassMaintanceBean bean = new MapMarkerClassMaintanceBean();
	private MapMarkerClassMaintanceService service = new MapMarkerClassMaintanceService();
	
	public String init(){
		return "init";
	}
	public void addchildclass() throws Exception{
		AjaxMsg am = new AjaxMsg();
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	 	UserInfo userInfo = (UserInfo)sessionMap.get(UserInfo.USER_INFO);
		HttpServletResponse response = ServletActionContext.getResponse();
		int r = service.addchildclass(bean,userInfo);
		if(r>0){
			am.setSuccess(true);
			am.setMsg("子类型新增成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("子类型新增失败！");
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
			am.setMsg("子类型属性新增成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("子类型属性新增失败！");
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
			am.setMsg("子类型修改成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("子类型修改失败！");
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
			am.setMsg("子类型删除成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("子类型删除失败！");
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
			am.setMsg("子类型属性删除成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("子类型属性删除失败！");
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
			am.setMsg("子类型属性修改成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("子类型属性修改失败！");
		}
		JsonUtil.ToJson(am, response);
		return;
	}
	/**
	 * 标注管理初始加载数据函数
	 * @throws Exception
	 */
	@Pageable
	public void loaddata1() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	 	UserInfo userInfo = (UserInfo)sessionMap.get(UserInfo.USER_INFO);
		PageUI pageui=service.loaddata1(pagination,bean,userInfo);
    	JsonUtil.ToJson(pageui,response);
    	return;
	}
	
	@Pageable
	public void loaddata2() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	 	UserInfo userInfo = (UserInfo)sessionMap.get(UserInfo.USER_INFO);
		PageUI pageui=service.loaddata2(pagination,bean,userInfo);
    	JsonUtil.ToJson(pageui,response);
    	return;
	}
	
	public void copychildclass() throws Exception{
		AjaxMsg am = new AjaxMsg();
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	 	UserInfo userInfo = (UserInfo)sessionMap.get(UserInfo.USER_INFO);
		HttpServletResponse response = ServletActionContext.getResponse();
		int r = service.copychildclass(bean,userInfo);
		if(r>0){
			am.setSuccess(true);
			am.setMsg("子类型复制成功！");
		}else{
			am.setSuccess(false);
			am.setMsg("子类型复制失败！");
		}
		JsonUtil.ToJson(am,response);
		return;
	}
	
	public String brchTree() throws Exception{
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	 	UserInfo userInfo = (UserInfo)sessionMap.get(UserInfo.USER_INFO);
		HttpServletResponse response = ServletActionContext.getResponse();
		List<Brch> list=service.brchTree(userInfo);
		List<Brch> r=new LinkedList<Brch>();
		String checkBrch[]=bean.getCheckBrch().split(",");
		for(Brch b:list)
		{
			int a = Integer.parseInt(b.getBrch_mode());
			String brch = b.getBrch_no();
			for(int j=0; j<checkBrch.length; j++){
				 
				if(brch.equals(checkBrch[j].trim())){
					b.setBrch_flag("checked");
					break;
				}
				else
				{
					b.setBrch_flag("");
				}
			}
			String lev = "";
			for(int i = 1; i <= a; i++){
				lev += "&nbsp;&nbsp;&nbsp;";
			}
			b.setBrch_mode(lev);
			r.add(b);
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("brchTree", r);
		session.setAttribute("obj_name", bean.getObj_name());
		return "brchTree";
	}
	
	
	@Override
	public MapMarkerClassMaintanceBean getModel() {
		return bean;
	}


	public static Logger getLog() {
		return log;
	}


	public static void setLog(Logger log) {
		MapMarkerClassMaintanceAction.log = log;
	}


	public MapMarkerClassMaintanceBean getBean() {
		return bean;
	}


	public void setBean(MapMarkerClassMaintanceBean bean) {
		this.bean = bean;
	}


	public MapMarkerClassMaintanceService getService() {
		return service;
	}


	public void setService(MapMarkerClassMaintanceService service) {
		this.service = service;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
