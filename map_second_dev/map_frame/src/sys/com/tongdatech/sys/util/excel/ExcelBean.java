package com.tongdatech.sys.util.excel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * EXCEL�������<br>
 * ���͵�<b>ģ��ģʽ</b><br>
 * Excelģ��̳и���
 * @author xl
 *
 */
public abstract class ExcelBean {
	
	/** int EXCEL03 03���������65536*/
	public static final int EXCEL03=65500;
	
	/** int EXCEL07 07���������1048576*/
	public static final int EXCEL07=1048500;
	//java  int��32λ��-2147483648����2147483647
	
	
	
	/** int excelEdition Excel�汾*/
	protected int excelEdition; 
	
	/** Workbook wb POI��EXCEL����*/
	protected Workbook wb;
	
	/** Workbook wb POI��EXCEL sheet����*/
	protected Sheet sheet;
	
	/** CreationHelper createHelper*/
	protected CreationHelper createHelper;
	
	/** int colnum ������*/
	protected int colnum;
	
	/** int rownum ������*/
	protected int rownum;
	
	/** String fliename �ļ�����*/
	protected String fliename;

	/** String tablename ���ģʽ���� add by XY*/
	protected String tablename;
	
	
	
	/** String headtext ��������*/
	protected String headtext;

	/** String[] subtext С��������*/
	protected String[] subtext; 

	/** String[] toptext 2˫���ͷ��һ��*/
	protected String[] toptext;
	
	/** String[] titletext ��ͷ ���� ˫���ͷ�ڶ���*/
	protected String[] titletext;
	
	/** String[] datakey ����KEYֵ*/
	protected String[] datakey;
	
	/** List data ����������*/
	@SuppressWarnings("rawtypes")
	protected List data;
	
	/** String foottext ��β���� �������ܼ�ǿ��β���� wait*/
	protected String foottext;
	
	
	
	
	/** ExcelArea headx ��������*/
	protected ExcelArea headx;
	
	/** ExcelArea subx С��������*/
	protected ExcelArea subx;
	
	/** ExcelArea titlex ��ͷ����*/
	protected ExcelArea titlex;
	
	/** ExcelArea datax ����������*/
	protected ExcelArea datax;
	
	/** ExcelArea footx ��β����*/
	protected ExcelArea footx;
	
	/** CellStyle cellStyle ��ʽ*/
	protected CellStyle cellStyle;
	
	
	
	/**
	 * ���췽��
	 * @param excelEdition Excel�汾
	 */
	public ExcelBean(int excelEdition) {
      this.excelEdition=excelEdition;
	}
	/**
	 *  ����������ʽ
	 */
	protected void createStyle(){
		Font font = wb.createFont();
		font.setFontName("����");
		font.setFontHeightInPoints((short) 10);
		cellStyle=wb.createCellStyle();
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN );
		cellStyle.setBorderTop(CellStyle.BORDER_THIN );
		cellStyle.setBorderRight(CellStyle.BORDER_THIN );
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN );
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		cellStyle.setFont(font);
	};
	
     /**
     *��������
     */
    protected void createFont(){
    	 
     }
	/**
	 * ����workbook ��sheet
	 */
	protected void createWorkbook(){

		
//		try {
//			fliename=new String(fliename.getBytes("gbk"), "ISO8859_1");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		//ȥ���ļ����Ƿ��ַ�
		String regex="\\\\|/|:|\\*|\\?|\\\"|<|>|\\|";//  \/:*?"<>|
		fliename=fliename.replaceAll(regex, "");
		//����excelʵ�� ����03�������������ƾ� ����07��excel
		if(excelEdition<=EXCEL03){
			wb=new HSSFWorkbook();
			fliename=fliename+".xls";
		}else{
			wb=new XSSFWorkbook();
			fliename=fliename+".xlsx";
		}
		//����sheet index=0
		sheet=wb.createSheet();
		createHelper = wb.getCreationHelper();
		
	}
	/**
	 * ����excel��ͷ
	 */
	protected abstract void bulidHead();
	/**
	 * ����excelС����
	 */
	protected abstract void bulidSubHead();
	/**
	 * ����excel��ͷ
	 */
	protected abstract void bulidTitle();
	/**
	 * ��������
	 */
	protected abstract void bulidData();
	/**
	 * ����excelβ
	 */
	protected abstract void bulidFoot();
	
	/**
	 * ����excel ֻ������ʽ������Ϻ�ſ��Ե���
	 * @throws Exception
	 */
	protected void modify() {
		/**
		 * -To calculate column width Sheet.autoSizeColumn uses Java2D classes that throw exception 
		 *  if graphical environment is not available. In case if graphical environment is not available, 
		 *  you must tell Java that you are running in headless mode and set the following 
		 *  system property: <b>java.awt.headless=true</b> . 
        */
		try{
			for(int i=0;i<colnum;i++){
				sheet.autoSizeColumn(i); //�����п��
			}
		}catch(Exception e){
			System.err.println("�޷�����ϵͳͼ�νӿ�,�������ò���java.awt.headless=true ���������");
			e.printStackTrace();
		}

	    /**-���ô�ӡ����------------------------------**/
		PrintSetup ps = sheet.getPrintSetup();
	    ps.setFitWidth((short)1);
	    ps.setFitHeight((short)0);
	    /**-���ô�ӡ����------------------------------**/
	    int lastr=0;if(titlex!=null)lastr=titlex.getRowed();
		wb.setRepeatingRowsAndColumns(0, -1, -1, 0, lastr);



	}
	/**
	 * ���ɻ���excel
	 */
	protected abstract void freeDraw();
	
	/**
	 * ģ��ģʽ ����excel
	 * @throws Exception
	 */
	public void create() throws Exception{
		//����excel���
		int titlelen=titletext==null?0:titletext.length;
		int subtextlen=subtext==null?0:subtext.length;
		int datakeylen=datakey==null?0:datakey.length;
		colnum=Math.max(subtextlen, Math.max(titlelen,datakeylen));
		if(wb==null||sheet==null){
			createWorkbook();
			createStyle();
		}
		bulidHead();
		headx=new ExcelArea(0,sheet.getLastRowNum(),0,colnum);
		bulidSubHead();
		subx=new ExcelArea(0,sheet.getLastRowNum(),0,colnum);
		bulidTitle();
		titlex=new ExcelArea(0,sheet.getLastRowNum(),0,colnum);
		bulidData();
		datax=new ExcelArea(0,sheet.getLastRowNum(),0,colnum);
		bulidFoot();
		footx=new ExcelArea(0,sheet.getLastRowNum(),0,colnum);
		modify();
		freeDraw();
	}
	
	/**
	 * �л�������Ϊname��sheet
	 * @param name
	 */
	public final void nextsheet(String name){
		colnum=0;
		rownum=0;
		headtext=null;
		tablename=null;
		subtext=null;
		toptext=null;
		titletext=null;
		datakey=null;
	    data=null;
		foottext=null;
		sheet=wb.createSheet(name);
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
	 * @return the wb
	 */
	public Workbook getWb() {
		return wb;
	}
	/**
	 * @param wb the wb to set
	 */
	public void setWb(Workbook wb) {
		this.wb = wb;
	}
	/**
	 * @return the sheet
	 */
	public Sheet getSheet() {
		return sheet;
	}
	/**
	 * @param sheet the sheet to set
	 */
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}
	/**
	 * @return the createHelper
	 */
	public CreationHelper getCreateHelper() {
		return createHelper;
	}
	/**
	 * @param createHelper the createHelper to set
	 */
	public void setCreateHelper(CreationHelper createHelper) {
		this.createHelper = createHelper;
	}
	/**
	 * @return the colnum
	 */
	public int getColnum() {
		return colnum;
	}
	/**
	 * @param colnum the colnum to set
	 */
	public void setColnum(int colnum) {
		this.colnum = colnum;
	}
	/**
	 * @return the rownum
	 */
	public int getRownum() {
		return rownum;
	}
	/**
	 * @param rownum the rownum to set
	 */
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	/**
	 * @return the fliename
	 */
	public String getFliename() {
		return fliename;
	}
	/**
	 * @param fliename the fliename to set
	 */
	public void setFliename(String fliename) {
		this.fliename = fliename;
	}
	/**
	 * @return the tablename
	 */
	public String getTablename() {
		return tablename;
	}
	/**
	 * @param tablename the tablename to set
	 */
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	/**
	 * @return the headtext
	 */
	public String getHeadtext() {
		return headtext;
	}
	/**
	 * @param headtext the headtext to set
	 */
	public void setHeadtext(String headtext) {
		this.headtext = headtext;
	}
	/**
	 * @return the subtext
	 */
	public String[] getSubtext() {
		return subtext;
	}
	/**
	 * @param subtext the subtext to set
	 */
	public void setSubtext(String[] subtext) {
		this.subtext = subtext;
	}
	/**
	 * @return the toptext
	 */
	public String[] getToptext() {
		return toptext;
	}
	/**
	 * @param toptext the toptext to set
	 */
	public void setToptext(String[] toptext) {
		this.toptext = toptext;
	}
	/**
	 * @return the titletext
	 */
	public String[] getTitletext() {
		return titletext;
	}
	/**
	 * @param titletext the titletext to set
	 */
	public void setTitletext(String[] titletext) {
		this.titletext = titletext;
	}
	/**
	 * @return the datakey
	 */
	public String[] getDatakey() {
		return datakey;
	}
	/**
	 * @param datakey the datakey to set
	 */
	public void setDatakey(String[] datakey) {
		this.datakey = datakey;
	}
	/**
	 * @return the data
	 */
	@SuppressWarnings("rawtypes")
	public List getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	@SuppressWarnings("rawtypes")
	public void setData(List data) {
		this.data = data;
	}
	/**
	 * @return the foottext
	 */
	public String getFoottext() {
		return foottext;
	}
	/**
	 * @param foottext the foottext to set
	 */
	public void setFoottext(String foottext) {
		this.foottext = foottext;
	}
	/**
	 * @return the headx
	 */
	public ExcelArea getHeadx() {
		return headx;
	}
	/**
	 * @param headx the headx to set
	 */
	public void setHeadx(ExcelArea headx) {
		this.headx = headx;
	}
	/**
	 * @return the subx
	 */
	public ExcelArea getSubx() {
		return subx;
	}
	/**
	 * @param subx the subx to set
	 */
	public void setSubx(ExcelArea subx) {
		this.subx = subx;
	}
	/**
	 * @return the titlex
	 */
	public ExcelArea getTitlex() {
		return titlex;
	}
	/**
	 * @param titlex the titlex to set
	 */
	public void setTitlex(ExcelArea titlex) {
		this.titlex = titlex;
	}
	/**
	 * @return the datax
	 */
	public ExcelArea getDatax() {
		return datax;
	}
	/**
	 * @param datax the datax to set
	 */
	public void setDatax(ExcelArea datax) {
		this.datax = datax;
	}
	/**
	 * @return the footx
	 */
	public ExcelArea getFootx() {
		return footx;
	}
	/**
	 * @param footx the footx to set
	 */
	public void setFootx(ExcelArea footx) {
		this.footx = footx;
	}
	/**
	 * @return the cellStyle
	 */
	public CellStyle getCellStyle() {
		return cellStyle;
	}
	/**
	 * @param cellStyle the cellStyle to set
	 */
	public void setCellStyle(CellStyle cellStyle) {
		this.cellStyle = cellStyle;
	}
	/**
	 * @return the excel03
	 */
	public static int getExcel03() {
		return EXCEL03;
	}
	/**
	 * @return the excel07
	 */
	public static int getExcel07() {
		return EXCEL07;
	}
	
	/**----------------------------------------------------*/

	

}
