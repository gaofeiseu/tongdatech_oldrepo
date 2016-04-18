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
		/**根据struts-configs 里面的struts-*.xml 配置*/
		return "init";
	}
	
	@SuppressWarnings("unused")
	@Pageable
	public String list() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		/**调用服务层*/
		PageUI p=dataDictService.list(pagination,dataDict);
   	    /**返回JSON串*/
    	JsonUtil.ToJson(p,response);
		return null;
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	@Pageable
	public String query() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		/**调用服务层*/
		List<Map> tabList = dataDictService.tableList(dataDict);
		
		PageUI pageui = null;
		for(int i=0;i<tabList.size();i++){
			String tablename = (String) tabList.get(i).get("table_name");
			//**调用服务层*//*
			pageui=dataDictService.query(pagination, tablename);
		}
		/**返回JSON串*/
    	JsonUtil.ToJson(pageui,response);
		return null;
	}
	
	
	@SuppressWarnings("rawtypes")
	@Pageable
	public String excel() throws Exception{
		
		excelBean=new ExcelForDataDict(excelEdition);
		
		excelBean.setFliename("数据库表信息表");
		excelBean.setHeadtext("数据库表信息");
   
		String[] titletext={"表名","字段名","字段类型","默认值","能否为空","字段备注"};
		String[] datakey={"table_name","column_name","data_type","data_default","nullable","col_comments"};      //设置对应数据集合中的 数据名称 key
		List<Map> tabList = dataDictService.tableList(dataDict);
		String tablename = "";
		String tablecomments = "";
		for(int i=0;i<tabList.size();i++){
			tablename = (String) tabList.get(i).get("table_name");
			tablecomments = (String)tabList.get(i).get("comments");
			excelBean.setTablename(tablename+":"+tablecomments);
			excelBean.setTitletext(titletext);
			excelBean.setDatakey(datakey);
			/**调用服务层*/
			PageUI pageui=dataDictService.query(pagination,tablename);
			excelBean.setData(pageui.getRows());
			excelBean.create();
		}
		/**调用excel导出*/
		return "excel";
	}
}