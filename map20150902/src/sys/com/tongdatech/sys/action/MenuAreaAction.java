package com.tongdatech.sys.action;

import java.util.ArrayList;
import java.util.List;
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
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.service.MenuAreaService;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.util.ParamsUtil;
import com.tongdatech.sys.util.excel.ExcelForReport;

/**
 * @author xl
 * 
 * ModelDriven 模式驱动 直接将参数封装成对象 <br> 
 * 模式驱动 只能有一个对象 其余参数仍然可以由OGNL通过get、set方法获得
 */
public class MenuAreaAction extends PaginationAction implements ModelDriven<UserInfo> {

	
	
	private static final long serialVersionUID = -2146634623333082666L;
	private UserInfo userInfo =new UserInfo();

	@PageParam
	private String title;
	
	private PageUI pageui;
	
	private static String name,no;

	private MenuAreaService easyUIMenuAresService=new MenuAreaService();
	
	
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

		userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		name=userInfo.getArea_name();
		no=userInfo.getArea_no();
		
		/**调用服务层*/
		pageui=easyUIMenuAresService.query(pagination,name,no);
		
		
		

		return "area";
	}
	
	@Pageable
	public String test() throws Exception
	{
		pageui=easyUIMenuAresService.query(pagination,name,no);
		return "area";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Pageable
	public String excel() throws Exception{
		
		//ExcelBean 
		excelBean=new ExcelForReport(excelEdition);
		
		/**调用服务层*/
		PageUI p=easyUIMenuAresService.query(pagination,name,no);
		
		excelBean.setFliename("菜单地区表");     //设置导出excel名称
		excelBean.setHeadtext("菜单地区表");     //设置表头
 
		ArrayList<String> list = new ArrayList<String>();
		list.add("menu_name");
		list.add("menu_path");
		for(Map mp:(List<Map>)p.getColInfo())
		{
			list.add((String) mp.get("area_name"));
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
	@Override
	public UserInfo getModel() {
		return userInfo;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		MenuAreaAction.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		MenuAreaAction.no = no;
	}






}