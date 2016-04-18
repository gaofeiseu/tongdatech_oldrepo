package com.tongdatech.map.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map.bean.MapMarkersAdditionBean;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.service.MapMarkersAdditionService;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.util.JsonUtil;
/**
 * 
 * @author Mr.GaoFei
 *
 */
public class MapMarkersAdditionAction extends PaginationAction implements ModelDriven<MapMarkersAdditionBean>{
	private static final long serialVersionUID = -8152248052060662744L;
	private static Logger log =  Logger.getLogger(MapMarkersAdditionAction.class);
	private MapMarkersAdditionBean bean = new MapMarkersAdditionBean();
	private MapMarkersAdditionService service = new MapMarkersAdditionService();
	private File[] upload;
	
	public String init(){
		return "init";
	}
	
	public void getUserTypeSelect() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo)request.getSession().getAttribute("userInfo");
		returnBean = service.getUserTypeSelect(bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	/**
	 * 标注添加时，获取前台用户可以使用的子类型内容
	 * @throws Exception
	 */
	public void getChildClassSelect() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo)request.getSession().getAttribute("userInfo");
		returnBean = service.getChildClassSelect(bean, userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	public void getChildClassSelectOptions() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo)request.getSession().getAttribute("userInfo");
		returnBean = service.getChildClassSelectOptions(bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void getMyIconSelectHtml() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.getMyIconSelectHtml(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void do_icon_add() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.do_icon_add(upload,bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void giveHiddenDivContent() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo)request.getSession().getAttribute("userInfo");
		returnBean = service.giveHiddenDivContent(bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void giveHiddenDivContent2() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo)request.getSession().getAttribute("userInfo");
		returnBean = service.giveHiddenDivContent2(bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void giveHiddenDivContent3() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo)request.getSession().getAttribute("userInfo");
		returnBean = service.giveHiddenDivContent3(bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void giveHiddenDivContent4() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo)request.getSession().getAttribute("userInfo");
		returnBean = service.giveHiddenDivContent4(bean,userinfo);
		JsonUtil.ToJson(returnBean, response);
		return;
	}
	
	public void giveHiddenDivContent5() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo)request.getSession().getAttribute("userInfo");
		returnBean = service.giveHiddenDivContent5(bean,userinfo);
		JsonUtil.ToJson(returnBean, response);
		return;
	}
	
	public void addPointMarkerS() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		UserInfo userinfo = new UserInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		userinfo = (UserInfo) request.getSession().getAttribute("userInfo");
		returnBean = service.addPointMarkerS(upload,bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	public void addLineMarkerS() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo) request.getSession().getAttribute("userInfo");
		returnBean = service.addLineMarkerS(upload,bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	public void addAreaMarkerS() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo) request.getSession().getAttribute("userInfo");
		returnBean = service.addAreaMarkerS(upload,bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	public void addCircleMarkerS() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo) request.getSession().getAttribute("userInfo");
		returnBean = service.addCircleMarkerS(upload,bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void addTextAreaMarkerS() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userinfo = new UserInfo();
		userinfo = (UserInfo) request.getSession().getAttribute("userInfo");
		returnBean = service.addTextAreaMarkerS(upload,bean,userinfo);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void addtextarea() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.addtextarea(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	public void getTextarea() throws Exception{
		MapReturnBean returnBean = new MapReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.getTextarea(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
	
	
	
	
	
	
	
	
	
	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		MapMarkersAdditionAction.log = log;
	}

	public MapMarkersAdditionBean getBean() {
		return bean;
	}

	public void setBean(MapMarkersAdditionBean bean) {
		this.bean = bean;
	}

	public MapMarkersAdditionService getService() {
		return service;
	}

	public void setService(MapMarkersAdditionService service) {
		this.service = service;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MapMarkersAdditionBean getModel() {
		return bean;
	}
}
