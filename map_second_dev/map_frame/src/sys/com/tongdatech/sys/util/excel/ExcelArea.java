package com.tongdatech.sys.util.excel;

import org.apache.poi.ss.util.CellReference;

/**
 * Excel�������       <br>
 * @author xl 
 * @version    2014-4-11 ����02:08:27
 */
public class ExcelArea {

	/** int rowst ����ʼ 0->��1��*/
	private int rowst;
	/** int rowed �н���*/
	private int rowed;
	/** int colst ����ʼ0->A��*/
	private int colst;
	/** int coled �н���*/
	private int coled;
	
	
	
	/**
	 * ExcelArea���췽��
	 * @param rowst 0    ��Ӧ��excel ����ֵΪ1
	 * @param rowed 10 ��Ӧ��excel ����ֵΪ11
	 * @param colst 0    ��Ӧ��excel ����ֵΪA
	 * @param coled 9    ��Ӧ��excel ����ֵΪI
	 */
	public ExcelArea(int rowst, int rowed, int colst, int coled) {
		super();
		this.rowst = rowst<0?0:rowst;
		this.rowed = rowed<0?0:rowed;
		this.colst = colst<0?0:colst;
		this.coled = coled<0?0:coled;
	}

	/**
	 * ����ת��Ϊ���ʽ1
	 * 1,1,3,5
	 * @return A1:C5
	 */
	public String ToStr1(){
		
		String rs=CellReference.convertNumToColString(colst)+(rowst+1)+":"
		         +CellReference.convertNumToColString(coled)+(rowed+1);
		return rs;
		
	}
	/**
	 * ����ת��Ϊ���ʽ2
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
