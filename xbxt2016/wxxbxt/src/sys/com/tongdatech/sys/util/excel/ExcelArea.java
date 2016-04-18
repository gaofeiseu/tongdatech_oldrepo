package com.tongdatech.sys.util.excel;

import org.apache.poi.ss.util.CellReference;

/**
 * Excel区域对象       <br>
 * @author xl 
 * @version    2014-4-11 下午02:08:27
 */
public class ExcelArea {

	/** int rowst 行起始 0->第1列*/
	private int rowst;
	/** int rowed 行结束*/
	private int rowed;
	/** int colst 列起始0->A列*/
	private int colst;
	/** int coled 列结束*/
	private int coled;
	
	
	
	/**
	 * ExcelArea构造方法
	 * @param rowst 0    对应与excel 索引值为1
	 * @param rowed 10 对应与excel 索引值为11
	 * @param colst 0    对应与excel 索引值为A
	 * @param coled 9    对应与excel 索引值为I
	 */
	public ExcelArea(int rowst, int rowed, int colst, int coled) {
		super();
		this.rowst = rowst<0?0:rowst;
		this.rowed = rowed<0?0:rowed;
		this.colst = colst<0?0:colst;
		this.coled = coled<0?0:coled;
	}

	/**
	 * 区域转化为表达式1
	 * 1,1,3,5
	 * @return A1:C5
	 */
	public String ToStr1(){
		
		String rs=CellReference.convertNumToColString(colst)+(rowst+1)+":"
		         +CellReference.convertNumToColString(coled)+(rowed+1);
		return rs;
		
	}
	/**
	 * 区域转化为表达式2
	 * 1,2,9,6
	 * @return $A$2:$I$6
	 */
	public String ToStr2(){
		
		String rs="$"+CellReference.convertNumToColString(colst)+"$"+(rowst+1)+":" +
				  "$"+CellReference.convertNumToColString(coled)+"$"+(rowed+1);
		return rs;
		
	}

	/**
	 * @return the rowst
	 */
	public int getRowst() {
		return rowst;
	}

	/**
	 * @param rowst the rowst to set
	 */
	public void setRowst(int rowst) {
		this.rowst = rowst;
	}

	/**
	 * @return the rowed
	 */
	public int getRowed() {
		return rowed;
	}

	/**
	 * @param rowed the rowed to set
	 */
	public void setRowed(int rowed) {
		this.rowed = rowed;
	}

	/**
	 * @return the colst
	 */
	public int getColst() {
		return colst;
	}

	/**
	 * @param colst the colst to set
	 */
	public void setColst(int colst) {
		this.colst = colst;
	}

	/**
	 * @return the coled
	 */
	public int getColed() {
		return coled;
	}

	/**
	 * @param coled the coled to set
	 */
	public void setColed(int coled) {
		this.coled = coled;
	}

	
	
}
