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
 * ModelDriven ģʽ���� ֱ�ӽ�������װ�ɶ��� <br> 
 * ģʽ���� ֻ����һ������ ���������Ȼ������OGNLͨ��get��set�������
 */
public class DemoPaginationAction extends PaginationAction implements ModelDriven<DemoObj> {

	
	
	private static final long serialVersionUID = -2846633623333082666L;
	private DemoObj demoObj =new DemoObj();

	@PageParam
	private String title;
	
	private PageUI pageui;
	private PaginationService easyUIPaginationService=new PaginationService();
	
	
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
		pageui=easyUIPaginationService.query(pagination,demoObj);

   	    
		return "list";
	}
	
	@Pageable
	public String excel() throws Exception{
		
		//ExcelBean 
		excelBean=new ExcelForReport(excelEdition);
		
		excelBean.setFliename("easyui ��ҳ");     //���õ���excel����
		excelBean.setHeadtext("easyui ��ҳ");     //���ñ�ͷ
   
		String[] titletext={"sn","colint1","colint2","colstr1","colstr2","coldate1","coldate2"};    //˫�б�ͷ �ڶ���   û��Ԫ��ռ��1��
		excelBean.setTitletext(titletext);
		String[] datakey={"sn","colint1","colint2","colstr1","colstr2","coldate1","coldate2"};      //���ö�Ӧ���ݼ����е� �������� key
		excelBean.setDatakey(datakey);

		/**���÷����*/
		PageUI p=easyUIPaginationService.query(pagination,demoObj);
		
		excelBean.setData(p.getRows());                               //���ö�Ӧ���ݼ���
		excelBean.create();

		
		/**����excel����*/
		return "excel";
	}
	
	/** 
	 * ģʽ����  ����ʵ�ֵķ���
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
