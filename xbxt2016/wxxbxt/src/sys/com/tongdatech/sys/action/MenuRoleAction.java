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
 * ModelDriven ģʽ���� ֱ�ӽ�������װ�ɶ��� <br> 
 * ģʽ���� ֻ����һ������ ���������Ȼ������OGNLͨ��get��set�������
 */
public class MenuRoleAction extends PaginationAction {

	
	
	private static final long serialVersionUID = -2146634623333082666L;

	@PageParam
	private String title;
	
	private PageUI pageui;
	
	

	private MenuRoleService easyUIMenuRoleService=new MenuRoleService();
	
	
	public String init(){
		/**����stuts-configs �����stuts-*.xml ����*/
		return "init";
	}
	@SuppressWarnings("unused")
	@Pageable
	public String query() throws Exception{
		
		/***JSP 3�����ȡ*/
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		/***��ȡֵջ*/
		ValueStack vs = ActionContext.getContext().getValueStack();
		/***���ɲ���������*/
		ParamsUtil p =new ParamsUtil(vs);
		
		/**���÷����*/
		pageui=easyUIMenuRoleService.query(pagination);

		return "role";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Pageable
	public String excel() throws Exception{
		
		//ExcelBean 
		excelBean=new ExcelForReport(excelEdition);
		
		/**���÷����*/
		PageUI p=easyUIMenuRoleService.query(pagination);
		
		excelBean.setFliename("�˵���ɫ��");     //���õ���excel����
		excelBean.setHeadtext("�˵���ɫ��");     //���ñ�ͷ
   
		ArrayList<String> list = new ArrayList<String>();
		list.add("�˵�");
		list.add("�˵��㼶·��");
		for(Map<?, ?> mp:(List<Map>)p.getColInfo())
		{
			list.add((String) mp.get("role_name"));
		}
		String[] titleText=list.toArray(new String[0]);
		excelBean.setTitletext(titleText);
		String[] dataKey=list.toArray(new String[0]);
		excelBean.setDatakey(dataKey);

		
		
		excelBean.setData(p.getRows());                               //���ö�Ӧ���ݼ���
		excelBean.create();

		
		/**����excel����*/
		return "excel";
	}
	
	/** 
	 * ģʽ����  ����ʵ�ֵķ���
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