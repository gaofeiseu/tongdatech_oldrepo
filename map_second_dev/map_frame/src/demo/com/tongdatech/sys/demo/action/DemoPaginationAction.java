package com.tongdatech.sys.demo.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.tongdatech.sys.annotation.PageParam;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.demo.bean.DemoObj;
import com.tongdatech.sys.demo.service.PaginationService;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.util.ParamsUtil;
import com.tongdatech.sys.util.excel.ExcelForReport;

/**
 * @author xl
 * 
 * ModelDriven 模式驱动 直接将参数封装成对象 <br> 
 * 模式驱动 只能有一个对象 其余参数仍然可以由OGNL通过get、set方法获得
 */
public class DemoPaginationAction extends PaginationAction implements ModelDriven<DemoObj> {

	
	
	private static final long serialVersionUID = -2846633623333082666L;
	private DemoObj demoObj =new DemoObj();

	@PageParam
	private String title;
	
	private PageUI pageui;
	private PaginationService easyUIPaginationService=new PaginationService();
	
	
	public String init(){
		/**根据stuts-configs 里面的stuts-*.xml 配置*/
		return "init";
	}
	@SuppressWarnings("unused")
	@Pageable
	public String query() throws Exception{
		/***JSP 3对象获取*/
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		/***获取值栈*/
		ValueStack vs = ActionContext.getContext().getValueStack();
		/***生成参数共用类*/
		ParamsUtil p =new ParamsUtil(vs);
		
		/**调用服务层*/
		pageui=easyUIPaginationService.query(pagination,demoObj);

   	    
		return "list";
	}
	
	@Pageable
	public String excel() throws Exception{
		
		//ExcelBean 
		excelBean=new ExcelForReport(excelEdition);
		
		excelBean.setFliename("easyui 分页");     //设置导出excel名称
		excelBean.setHeadtext("easyui 分页");     //设置表头
   
		String[] titletext={"sn","colint1","colint2","colstr1","colstr2","coldate1","coldate2"};    //双行表头 第二行   没个元素占用1列
		excelBean.setTitletext(titletext);
		String[] datakey={"sn","colint1","colint2","colstr1","colstr2","coldate1","coldate2"};      //设置对应数据集合中的 数据名称 key
		excelBean.setDatakey(datakey);

		/**调用服务层*/
		PageUI p=easyUIPaginationService.query(pagination,demoObj);
		
		excelBean.setData(p.getRows());                               //设置对应数据集合
		excelBean.create();

		
		/**调用excel导出*/
		return "excel";
	}
	
	/** 
	 * 模式驱动  必须实现的方法
	 */
	@Override
	public DemoObj getModel() {
		return demoObj;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the pageui
	 */
	public PageUI getPageui() {
		return pageui;
	}
	/**
	 * @param pageui the pageui to set
	 */
	public void setPageui(PageUI pageui) {
		this.pageui = pageui;
	}




}
