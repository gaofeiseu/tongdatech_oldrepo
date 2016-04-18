package com.tongdatech.sys.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel 函数      <br>
 * @author xl 
 * @version    2014-4-11 下午03:01:57
 */
public class ExcelFun {
	 public static Workbook getWorkBook(File excelFile) throws IOException{
		 return getWorkBook(excelFile,excelFile.getName());
	 }
    /**
     * 根据文件和文件名生成Workbook对象
     * @param excelFile
     * @param excelFileFileName
     * @return
     * @throws IOException
     */
    public static Workbook getWorkBook(File excelFile,String excelFileFileName) throws IOException{  
    	InputStream is=new FileInputStream(excelFile);
        if(excelFileFileName.toLowerCase().endsWith("xls")){  
            return new HSSFWorkbook(is);  
        }  
        if(excelFileFileName.toLowerCase().endsWith("xlsx")){  
            return new XSSFWorkbook(is);  
        }  
        return null;  
    } 
    /**
     * 获取Cell中内容
     * @param cell
     * @return String
     */
    public static String getValue(Cell cell){
    	String rs="";
    	if ((cell.getCellType()==Cell.CELL_TYPE_NUMERIC||cell.getCellType()==Cell.CELL_TYPE_FORMULA)&&DateUtil.isCellDateFormatted(cell)){
    		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
    		rs=getValue(cell,sf);
    	}else{
    		DecimalFormat df=new DecimalFormat("0");
    		rs=getValue(cell,df);
    	}
    	return rs;
    }
    /**
     * 获取Cell中内容
     * @param cell
     * @return String
     */
    public static String getValue(Cell cell,Format myformat){
    	String cellvalue = "";
        if (cell != null){
        	switch (cell.getCellType()) 
        	{
        	  case Cell.CELL_TYPE_NUMERIC:
        	  case Cell.CELL_TYPE_FORMULA:{
        		// 判断当前的cell是否为Date
                  if (DateUtil.isCellDateFormatted(cell)) 
                  {
                     // 如果是Date类型则，取得该Cell的Date值
                     Date date = cell.getDateCellValue();
                     cellvalue = myformat.format(date);
                     
                  }else{
//                	 cellvalue=cell.toString();
                   double num = new Double(cell.getNumericCellValue());
                   cellvalue = String.valueOf(myformat.format(num));
                  }
                  break;
        	  }
        	  case Cell.CELL_TYPE_STRING:
                  cellvalue = cell.getStringCellValue().replaceAll("'", "''");
                  cellvalue = cellvalue.trim();
                  break;
               default:
                  cellvalue = "";
            }

        		  
        	
        }

		return cellvalue;	
    }
    /**
     * 创建CELLS
     * @param r
     * @param num
     */
    public static void createCells(Row r,int num){
    	for(int i = 0; i < num; i ++) {
 		   r.createCell(i);
 	   }

    }
    /**
     * 创建ROWS
     * @param sheet
     * @param st
     * @param ed
     */
    public static void createRows(Sheet sheet,int st,int ed){
    	for(int i = st; i < ed; i ++) {
    		sheet.createRow(i);
 	   }

    }
}
