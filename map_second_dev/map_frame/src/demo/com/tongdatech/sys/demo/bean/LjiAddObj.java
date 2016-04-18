/**
 * File name:Param.java
 * Create author:XY
 * Create date:2014-3-18
 */
package com.tongdatech.sys.demo.bean;

import java.util.Date;

import com.tongdatech.sys.annotation.PageParam;

/**
 * @author XY
 *
 */
public class LjiAddObj {
	@PageParam
	private int sn;
	@PageParam
	private String colstr1;
	private String colstr2;
	@PageParam
	private int colint1;
	private int colint2;
	@PageParam
	private Date coldate1;
	private Date coldate2;
	@PageParam
	private String[] colarray;
	
    
	

	public int getSn() {
		return sn;
	}




	public void setSn(int sn) {
		this.sn = sn;
	}




	public String getColstr1() {
		return colstr1;
	}




	public void setColstr1(String colstr1) {
		this.colstr1 = colstr1;
	}




	public String getColstr2() {
		return colstr2;
	}




	public void setColstr2(String colstr2) {
		this.colstr2 = colstr2;
	}




	public int getColint1() {
		return colint1;
	}




	public void setColint1(int colint1) {
		this.colint1 = colint1;
	}




	public int getColint2() {
		return colint2;
	}




	public void setColint2(int colint2) {
		this.colint2 = colint2;
	}




	public Date getColdate1() {
		return coldate1;
	}




	public void setColdate1(Date coldate1) {
		this.coldate1 = coldate1;
	}




	public Date getColdate2() {
		return coldate2;
	}




	public void setColdate2(Date coldate2) {
		this.coldate2 = coldate2;
	}




	public String[] getColarray() {
		return colarray;
	}




	public void setColarray(String[] colarray) {
		this.colarray = colarray;
	}




	@Override
	public String toString() {
		return "Param [sn=" + sn + ", colint1=" + colint1 + ", colint2=" + colint2
				+ ", colstr1=" + colstr1 + ", colstr2=" + colstr2 + ", coldate1="
				+ coldate1 + ",coldate2="+coldate2 +"]";
	}
}