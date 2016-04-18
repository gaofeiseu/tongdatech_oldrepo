package com.tongdatech.sys.demo.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.demo.bean.DataDict;
import com.tongdatech.sys.demo.service.DataDictService;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.excel.ExcelForDataDict;

public class DataDictAction extends PaginationAction implements ModelDriven<DataDict>{

	/**
	 * XY
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger log =  Logger.getLogger(DataDictAction.class);
	DataDict dataDict = new DataDict();
	DataDictService dataDictService = new DataDictService();

	@Override
	public DataDict getModel() {
		return dataDict;
	}

	public DataDict getDataDict() {
		return dataDict;
	}

	public void setDataDict(DataDict dataDict) {
		this.dataDict = dataDict;
	}

	public DataDictService getDataDictService() {
		return dataDictService;
	}

	public void setDataDictService(DataDictService dataDictService) {
		this.dataDictService = dataDictService;
	}
	public String init(){
		/**����struts-configs �����struts-*.xml ����*/
		return "init";
	}
	
	@SuppressWarnings("unused")
	@Pageable
	public String list() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		/**���÷����*/
		PageUI p=dataDictService.list(pagination,dataDict);
   	    /**����JSON��*/
    	JsonUtil.ToJson(p,response);
		return null;
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	@Pageable
	public String query() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		/**���÷����*/
		List<Map> tabList = dataDictService.tableList(dataDict);
		
		PageUI pageui = null;
		for(int i=0;i<tabList.size();i++){
			String tablename = (String) tabList.get(i).get("table_name");
			//**���÷����*//*
			pageui=dataDictService.query(pagination, tablename);
		}
		/**����JSON��*/
    	JsonUtil.ToJson(pageui,response);
		return null;
	}
	
	
	@SuppressWarnings("rawtypes")
	@Pageable
	public String excel() throws Exception{
		
		excelBean=new ExcelForDataDict(excelEdition);
		
		excelBean.setFliename("���ݿ����Ϣ��");
		excelBean.setHeadtext("���ݿ����Ϣ");
   
		String[] titletext={"����","�ֶ���","�ֶ�����","Ĭ��ֵ","�ܷ�Ϊ��","�ֶα�ע"};
		String[] datakey={"table_name","column_name","data_type","data_default","nullable","col_comments"};      //���ö�Ӧ���ݼ����е� �������� key
		List<Map> tabList = dataDictService.tableList(dataDict);
		String tablename = "";
		String tablecomments = "";
		for(int i=0;i<tabList.size();i++){
			tablename = (String) tabList.get(i).get("table_name");
			tablecomments = (String)tabList.get(i).get("comments");
			excelBean.setTablename(tablename+":"+tablecomments);
			excelBean.setTitletext(titletext);
			excelBean.setDatakey(datakey);
			/**���÷����*/
			PageUI pageui=dataDictService.query(pagination,tablename);
			excelBean.setData(pageui.getRows());
			excelBean.create();
		}
		/**����excel����*/
		return "excel";
	}
}