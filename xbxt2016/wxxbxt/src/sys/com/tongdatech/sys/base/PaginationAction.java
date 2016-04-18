package com.tongdatech.sys.base;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tongdatech.sys.pojo.Pagination;
import com.tongdatech.sys.util.excel.ExcelBean;

/**
 * ͨ�÷�ҳ ֧��EasyUI�ͷ�EasyUI����ģʽ
 * @author xl 
 * @version   2014-3-16 ����03:16:05
 */
public abstract class PaginationAction extends ActionSupport  {


	/** long serialVersionUID*/
	private static final long serialVersionUID = -1619284495415656484L;
	
	/**ҳ�� ��1��ʼ */
	public int page=1;
	/**���� ÿҳ���� */
	public int rows=20;
	/**������ */
	public int total=20;
	
	/**��ʼ����*/
	public int stnum=0;
	/**��������*/
	public int ednum=0;
	
	/**excel�汾*/
	public int excelEdition=0;
	
	/**����URL*/
	public String back_url;
	
	/** Pagination pagination ��ҳ����*/
	public Pagination pagination;
	/** ExcelBean excelBean  excel��װ����*/
	public ExcelBean  excelBean;

	/**
	 * ����page rowsԤ�ȴ����ҳ����
	 */
	public void prePaging() throws Exception {
		if(stnum==0){
        	stnum=(page - 1) * rows+1;
        }
        if(ednum==0){
        	ednum=page * rows;
        }
       
        String uri= ServletActionContext.getRequest().getRequestURI();
        pagination=new Pagination(stnum, ednum, total, rows, page, uri, back_url);
        pagination.setAction(this);
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the stnum
	 */
	public int getStnum() {
		return stnum;
	}

	/**
	 * @param stnum the stnum to set
	 */
	public void setStnum(int stnum) {
		this.stnum = stnum;
	}

	/**
	 * @return the ednum
	 */
	public int getEdnum() {
		return ednum;
	}

	/**
	 * @param ednum the ednum to set
	 */
	public void setEdnum(int ednum) {
		this.ednum = ednum;
	}

	/**
	 * @return the excelEdition
	 */
	public int getExcelEdition() {
		return excelEdition;
	}

	/**
	 * @param excelEdition the excelEdition to set
	 */
	public void setExcelEdition(int excelEdition) {
		this.excelEdition = excelEdition;
	}

	/**
	 * @return the back_url
	 */
	public String getBack_url() {
		return back_url;
	}

	/**
	 * @param back_url the back_url to set
	 */
	public void setBack_url(String back_url) {
		this.back_url = back_url;
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the excelBean
	 */
	public ExcelBean getExcelBean() {
		return excelBean;
	}

	/**
	 * @param excelBean the excelBean to set
	 */
	public void setExcelBean(ExcelBean excelBean) {
		this.excelBean = excelBean;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
	

}
