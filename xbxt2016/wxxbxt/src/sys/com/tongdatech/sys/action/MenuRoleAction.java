package com.tongdatech.sys.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.tongdatech.sys.annotation.PageParam;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.service.MenuRoleService;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.util.ParamsUtil;
import com.tongdatech.sys.util.excel.ExcelForReport;

/**
 * @author xl
 * 
 * ModelDriven 模式驱动 直接将参数封装成对象 <br> 
 * 模式驱动 只能有一个对象 其余参数仍然可以由OGNL通过get、set方法获得
 */
public class MenuRoleAction extends PaginationAction {

	
	
	private static final long serialVersionUID = -2146634623333082666L;

	@PageParam
	private String title;
	
	private PageUI pageui;
	
	

	private MenuRoleService easyUIMenuRoleService=new MenuRoleService();
	
	
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
		pageui=easyUIMenuRoleService.query(pagination);

		return "role";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Pageable
	public String excel() throws Exception{
		
		//ExcelBean 
		excelBean=new ExcelForReport(excelEdition);
		
		/**调用服务层*/
		PageUI p=easyUIMenuRoleService.query(pagination);
		
		excelBean.setFliename("菜单角色表");     //设置导出excel名称
		excelBean.setHeadtext("菜单角色表");     //设置表头
   
		ArrayList<String> list = new ArrayList<String>();
		list.add("菜单");
		list.add("菜单层级路径");
		for(Map<?, ?> mp:(List<Map>)p.getColInfo())
		{
			list.add((String) mp.get("role_name"));
		}
		String[] titleText=list.toArray(new String[0]);
		excelBean.setTitletext(titleText);
		String[] dataKey=list.toArray(new String[0]);
		excelBean.setDatakey(dataKey);

		
		
		excelBean.setData(p.getRows());                               //设置对应数据集合
		excelBean.create();

		
		/**调用excel导出*/
		return "excel";
	}
	
	/** 
	 * 模式驱动  必须实现的方法
	 */

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