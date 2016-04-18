package com.tongdatech.sys.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.MenuIpBean;

import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.service.MenuIpService;
import com.tongdatech.sys.util.JsonUtil;


 

public class MenuIpAction extends PaginationAction implements ModelDriven<MenuIpBean> {

 	/**
	 * 
	 */
	private static final long serialVersionUID = 1773998502262632025L;
	private MenuIpBean bean =new MenuIpBean();
	private MenuIpService menuIpService =new MenuIpService();
	  
  

	/**
	 * ���ݹ���������
	 * @return String
	 */
	public String init(){
		return "init";
	}
	
	/**
	 * �󶨹�ϵ��ѯ
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String query() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();	
	   	PageUI rs = menuIpService.getMenuIp(pagination, bean);

    	JsonUtil.ToJson(rs,response);
		return null;
	}
	/**
	 * ����󶨹�ϵ
	 * @return String
	 * @throws Exception
	 */
	public String save()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse(); 
		AjaxMsg rs = menuIpService.menuipSave(bean);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	
	/**
	 * ɾ���󶨹�ϵ
	 * @return String
	 * @throws Exception
	 */
	public String del()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse(); 
		AjaxMsg rs = menuIpService.menuipDel(bean);
    	JsonUtil.ToJson(rs,response);
		return null;
	} 
	
	
	/**
	 * �жϵ�ǰIP�ܷ���ʸò˵�
	 * @return String
	 * @throws Exception
	 */
	public String getMenuIpNum()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		AjaxMsg rs = menuIpService.getMenuIpNum(bean.getMenu_id(),request);
    	JsonUtil.ToJson(rs,response);
		return null;
	} 
	
	
	@Override
	public MenuIpBean getModel() {
		return bean;
	}

	public MenuIpBean getBean() {
		return bean;
	}

	public void setBean(MenuIpBean bean) {
		this.bean = bean;
	}

	public MenuIpService getMenuIpService() {
		return menuIpService;
	}

	public void setMenuIpService(MenuIpService menuIpService) {
		this.menuIpService = menuIpService;
	}

 
	


}
