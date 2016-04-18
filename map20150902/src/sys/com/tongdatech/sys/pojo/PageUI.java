package com.tongdatech.sys.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * ��ҳ�������<br>
 * @author xl 
 * @version    2014-4-11 ����10:50:16
 */
public class PageUI implements Serializable{
	
	/** long serialVersionUID*/
	private static final long serialVersionUID = 322986839366830404L;
	/** List<?> rows ��Ҫ����*/
	private List<?> rows;
	/** List<?> footer �ײ�����  ��ӦEasyUI datagrid footer*/
	private List<?> footer;
	/** int total ��������*/
	private int total;
	
	/** List<?> columns �м���*/
	private List<?> columns;


	private List<?> colInfo;
	
	/**
	 * @return the rows
	 */
	public List<?> getRows() {
		return rows;
	}



	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<?> rows) {
		this.rows = rows;
	}



	/**
	 * @return the footer
	 */
	public List<?> getFooter() {
		return footer;
	}



	/**
	 * @param footer the footer to set
	 */
	public void setFooter(List<?> footer) {
		this.footer = footer;
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
	 * @param columns the columns to set
	 */
	public void setColumns(List<?> columns) {
		this.columns = columns;
	}



	/**
	 * @return the columns
	 */
	public List<?> getColumns() {
		return columns;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PageUI [rows=" + rows + ", footer=" + footer + ", total="
				+ total + "]";
	}



	public List<?> getColInfo() {
		return colInfo;
	}



	public void setColInfo(List<?> colInfo) {
		this.colInfo = colInfo;
	}


}
