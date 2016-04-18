package com.tongdatech.sys.demo.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.demo.bean.DemoObj;
import com.tongdatech.sys.demo.service.PaginationService;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.excel.ExcelForReport;

/**
 * @author xl
 * 
 * ModelDriven 模式驱动 直接将参数封装成对象 <br> 
 * 模式驱动 只能有一个对象 其余参数仍然可以由OGNL通过get、set方法获得
 */
public class EasyUIPaginationAction extends PaginationAction implements ModelDriven<DemoObj> {

	
	
	private static final long serialVersionUID = -2846633623333082666L;
	private DemoObj demoObj =new DemoObj();
	private String title;
	
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
		
		/**调用服务层*/
		PageUI p=easyUIPaginationService.query(pagination,demoObj);

   	    /**返回JSON串*/
    	JsonUtil.ToJson(p,response);
		return null;
	}
	
	@Pageable
	public String excel() throws Exception{
		
		//ExcelBean 
		excelBean=new ExcelForReport(excelEdition);
		
		excelBean.setFliename("easyui 分页");     //设置导出excel名称
		excelBean.setHeadtext(title);     //设置表头
   
		String[] titletext={"sn","colint1","colint2","colstr1","colstr2","coldate1","coldate2"};    //双行表头 第二行   没个元素占用1列
		excelBean.setTitletext(titletext);
		String[] datakey={"sn","colint1","colint2","colstr1","colstr2","coldate1","coldate2"};      //设置对应数据集合中的 数据名称 key
		excelBean.setDatakey(datakey);

		/**调用服务层*/
		PageUI p=easyUIPaginationService.query(pagination,demoObj);
		
		excelBean.setData(p.getRows());                               //设置对应数据集合
		excelBean.create();
//		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("wb",excelBean.getWb());
//		request.setAttribute("fileName",excelBean.getFliename());
		
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



}
