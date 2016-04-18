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
 * EXCEL构造对象<br>
 * 典型的<b>模版模式</b><br>
 * Excel模版继承该类
 * @author xl
 *
 */
public abstract class ExcelBean {
	
	/** int EXCEL03 03版最大行数65536*/
	public static final int EXCEL03=65500;
	
	/** int EXCEL07 07版最大行数1048576*/
	public static final int EXCEL07=1048500;
	//java  int是32位的-2147483648－－2147483647
	
	
	
	/** int excelEdition Excel版本*/
	protected int excelEdition; 
	
	/** Workbook wb POI中EXCEL对象*/
	protected Workbook wb;
	
	/** Workbook wb POI中EXCEL sheet对象*/
	protected Sheet sheet;
	
	/** CreationHelper createHelper*/
	protected CreationHelper createHelper;
	
	/** int colnum 列数量*/
	protected int colnum;
	
	/** int rownum 行数量*/
	protected int rownum;
	
	/** String fliename 文件名称*/
	protected String fliename;

	/** String tablename 多表模式表名 add by XY*/
	protected String tablename;
	
	
	
	/** String headtext 标题内容*/
	protected String headtext;

	/** String[] subtext 小标题内容*/
	protected String[] subtext; 

	/** String[] toptext 2双层表头第一层*/
	protected String[] toptext;
	
	/** String[] titletext 表头 或者 双层表头第二层*/
	protected String[] titletext;
	
	/** String[] datakey 标题KEY值*/
	protected String[] datakey;
	
	/** List data 主区域数据*/
	@SuppressWarnings("rawtypes")
	protected List data;
	
	/** String foottext 表尾内容 将来可能加强表尾功能 wait*/
	protected String foottext;
	
	
	
	
	/** ExcelArea headx 标题区域*/
	protected ExcelArea headx;
	
	/** ExcelArea subx 小标题区域*/
	protected ExcelArea subx;
	
	/** ExcelArea titlex 表头区域*/
	protected ExcelArea titlex;
	
	/** ExcelArea datax 主数据区域*/
	protected ExcelArea datax;
	
	/** ExcelArea footx 表尾区域*/
	protected ExcelArea footx;
	
	/** CellStyle cellStyle 样式*/
	protected CellStyle cellStyle;
	
	
	
	/**
	 * 构造方法
	 * @param excelEdition Excel版本
	 */
	public ExcelBean(int excelEdition) {
      this.excelEdition=excelEdition;
	}
	/**
	 *  创建基础样式
	 */
	protected void createStyle(){
		Font font = wb.createFont();
		font.setFontName("宋体");
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
     *创建字体
     */
    protected void createFont(){
    	 
     }
	/**
	 * 生成workbook 和sheet
	 */
	protected void createWorkbook(){

		
//		try {
//			fliename=new String(fliename.getBytes("gbk"), "ISO8859_1");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		//去除文件名非法字符
		String regex="\\\\|/|:|\\*|\\?|\\\"|<|>|\\|";//  \/:*?"<>|
		fliename=fliename.replaceAll(regex, "");
		//创建excel实例 大于03版的最大行数限制就 创建07版excel
		if(excelEdition<=EXCEL03){
			wb=new HSSFWorkbook();
			fliename=fliename+".xls";
		}else{
			wb=new XSSFWorkbook();
			fliename=fliename+".xlsx";
		}
		//创建sheet index=0
		sheet=wb.createSheet();
		createHelper = wb.getCreationHelper();
		
	}
	/**
	 * 创建excel表头
	 */
	protected abstract void bulidHead();
	/**
	 * 创建excel小标题
	 */
	protected abstract void bulidSubHead();
	/**
	 * 创建excel表头
	 */
	protected abstract void bulidTitle();
	/**
	 * 创建数据
	 */
	protected abstract void bulidData();
	/**
	 * 创建excel尾
	 */
	protected abstract void bulidFoot();
	
	/**
	 * 调整excel 只有在样式设置完毕后才可以调用
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
				sheet.autoSizeColumn(i); //调整列宽度
			}
		}catch(Exception e){
			System.err.println("无法调用系统图形接口,可以设置参数java.awt.headless=true 解决该问题");
			e.printStackTrace();
		}

	    /**-设置打印区域------------------------------**/
		PrintSetup ps = sheet.getPrintSetup();
	    ps.setFitWidth((short)1);
	    ps.setFitHeight((short)0);
	    /**-设置打印标题------------------------------**/
	    int lastr=0;if(titlex!=null)lastr=titlex.getRowed();
		wb.setRepeatingRowsAndColumns(0, -1, -1, 0, lastr);



	}
	/**
	 * 自由绘制excel
	 */
	protected abstract void freeDraw();
	
	/**
	 * 模版模式 创建excel
	 * @throws Exception
	 */
	public void create() throws Exception{
		//计算excel宽度
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
	 * 切换到名称为name的sheet
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
