package com.tongdatech.business.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.business.bean.DataBean;
import com.tongdatech.business.bean.ReturnBean;
import com.tongdatech.business.service.DataManageService;


import com.tongdatech.map.util.FileUpload;
import com.tongdatech.map.util.PubFunc;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
 
import com.tongdatech.sys.bean.UserInfo;
 
import com.tongdatech.sys.pojo.PageUI;

 
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.excel.ExcelForReport;
 

public class DataManageAction extends PaginationAction implements ModelDriven<DataBean> {

 	/**
	 * 
	 */
	private static final long serialVersionUID = 1773998502262632025L;
	private DataBean bean =new DataBean();
 	private DataManageService dataManageService=new DataManageService();

	private File pic;
	private String filename="";


	public DataBean getBean() {
		return bean;
	}


	public void setBean(DataBean bean) {
		this.bean = bean;
	}


 

 


	public DataManageService getdataManageService() {
		return dataManageService;
	}


	public void setdataManageService(DataManageService dataManageService) {
		this.dataManageService = dataManageService;
	}


	public File getPic() {
		return pic;
	}


	public void setPic(File pic) {
		this.pic = pic;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}

 


	/**
	 * 数据管理管理界面
	 * @return String
	 */
	public String init(){
		return "init";
	}
	
	/**
	 * 获取登录用户的sn
	 * @return String
	 * @throws Exception 
	 */
	public String getSN() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		List<Map> list= dataManageService.getSNINFO(userInfo);
		JsonUtil.ToJson(list,response);
		return null;
	}
	
	/**
	 * 根据sn获取数据
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String doquery() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = dataManageService.doquery(pagination,bean);
		JsonUtil.ToJson(rs,response);
		return null;
	}
	
	

	/**
	 * 根据sn获取列的属性
	 * @return String
	 * @throws Exception 
	 * @throws Exception 
	 */
	public String queryColumn() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		List<Map> resultList=new ArrayList<Map>();
		resultList=dataManageService.queryColumn(bean);
		Map<String, Object> map=new HashMap<String, Object>(); 
		map.put("colMaps", resultList);  
		PubFunc.jsonOutput(response, map);
		return null;
	}
	
	/*
	 * 更新或复制
	 */
	
	public String doSave(){
		System.out.print("");
		ReturnBean returnBean = new ReturnBean();
		
		try {
			String name="";
			if(pic!=null)
			{
				name=FileUpload.upload(pic,filename);
			 
			}
			returnBean = dataManageService.doSave(bean,name);
			
			Map<String, Object> map=new HashMap<String, Object>();
	 
			map.put("if_success", returnBean.isIf_success());
			map.put("msg", returnBean.getMsg());
			HttpServletResponse response = ServletActionContext.getResponse();
			PubFunc.jsonOutput(response, map);
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return null;
	}
	/**
	 * Excel导出功能
	 * @return String
	 * @throws Exception
	 */
	@Pageable
	public String excel() throws Exception{

		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		excelBean=new ExcelForReport(excelEdition);
		
		excelBean.setFliename("数据列表");//设置导出excel名称
		excelBean.setHeadtext("数据列表");//设置表头
		List<Map> resultList=new ArrayList<Map>();
		resultList=dataManageService.queryColumn(bean);
		String[] titletext=new String[resultList.size() ];

		String[] datakey=new String[resultList.size()];
		for(int i=0;i<resultList.size();i++)
		{
			titletext[i]=resultList.get(i).get("comments").toString();//双行表头 第二行   没个元素占用1列
			
			datakey[i]=resultList.get(i).get("column_name").toString().toLowerCase();//设置对应数据集合中的 数据名称 key
		}
		excelBean.setTitletext(titletext);
		excelBean.setDatakey(datakey);

		/**调用服务层*/
		PageUI p = dataManageService.doquery(pagination,bean);
		
		excelBean.setData(p.getRows());                               //设置对应数据集合
		excelBean.create();

		/**调用excel导出*/
		return "excel";
	}
	
	/*
	 * 查询数据表的字段
	 */
	public String toQueryColumn(){
		List<Map> resultList=new ArrayList<Map>();
		resultList=dataManageService.toQueryColumn(bean);
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("resultList", resultList);
		return "success";
	}
	

	
	@Override
	public DataBean getModel() {
		return bean;
	}

 
	


}
