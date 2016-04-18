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
 * ModelDriven ģʽ���� ֱ�ӽ�������װ�ɶ��� <br> 
 * ģʽ���� ֻ����һ������ ���������Ȼ������OGNLͨ��get��set�������
 */
public class EasyUIPaginationAction extends PaginationAction implements ModelDriven<DemoObj> {

	
	
	private static final long serialVersionUID = -2846633623333082666L;
	private DemoObj demoObj =new DemoObj();
	private String title;
	
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
		
		/**���÷����*/
		PageUI p=easyUIPaginationService.query(pagination,demoObj);

   	    /**����JSON��*/
    	JsonUtil.ToJson(p,response);
		return null;
	}
	
	@Pageable
	public String excel() throws Exception{
		
		//ExcelBean 
		excelBean=new ExcelForReport(excelEdition);
		
		excelBean.setFliename("easyui ��ҳ");     //���õ���excel����
		excelBean.setHeadtext(title);     //���ñ�ͷ
   
		String[] titletext={"sn","colint1","colint2","colstr1","colstr2","coldate1","coldate2"};    //˫�б�ͷ �ڶ���   û��Ԫ��ռ��1��
		excelBean.setTitletext(titletext);
		String[] datakey={"sn","colint1","colint2","colstr1","colstr2","coldate1","coldate2"};      //���ö�Ӧ���ݼ����е� �������� key
		excelBean.setDatakey(datakey);

		/**���÷����*/
		PageUI p=easyUIPaginationService.query(pagination,demoObj);
		
		excelBean.setData(p.getRows());                               //���ö�Ӧ���ݼ���
		excelBean.create();
//		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("wb",excelBean.getWb());
//		request.setAttribute("fileName",excelBean.getFliename());
		
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



}
