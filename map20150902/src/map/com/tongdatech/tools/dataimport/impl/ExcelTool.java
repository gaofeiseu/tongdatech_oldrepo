package com.tongdatech.tools.dataimport.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;


import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.util.excel.ExcelFun;
import com.tongdatech.tools.dataimport.ImportTool;

public class ExcelTool extends ImportTool{

	/** long serialVersionUID*/
	private static final long serialVersionUID = -8547783771734987468L;
	private int st_row=0;
	private int ed_row=Integer.MAX_VALUE;
	
	
	public ExcelTool() {
		super();
	}

	@Override
	public PageUI beforeRender() {
		PageUI pg = new PageUI();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try {
			
			Workbook book = ExcelFun.getWorkBook(file);
			Sheet sheet=book.getSheetAt(book.getActiveSheetIndex());
			Set<String> columns=new TreeSet<String>();
			 for(int i=st_row;i<=Math.min(Math.min(ed_row, sheet.getLastRowNum()),RANDER_ROW );i++){
				 Row row=sheet.getRow(i);
				 Map<String,String> mp = new HashMap<String,String>();
				 mp.put(ROW_NUM, String.valueOf(i+1));
				 for(int j=0;j<row.getLastCellNum();j++){
					 Cell cell = row .getCell(j);
					 String key=CellReference.convertNumToColString(j);
					 mp.put(key, ExcelFun.getValue(cell));
					 columns.add(key);
				 }
				 list.add(mp);
			 }
			 
			 pg.setRows(list);
			 pg.setColumns(new ArrayList<String>(columns));
			
		} catch (IOException e) {
			log.debug("excelµº»Î", e);
		}
		return pg;
	}

	@Override
	public void parseParam() {
		String str_st_row=get("xls_st_row");
		String str_ed_row=get("xls_ed_row");
		try{
			st_row=Integer.parseInt(str_st_row);
			st_row=(st_row==0)?st_row:st_row-1;
		}catch (Exception e) {
		}
		try{
			ed_row=Integer.parseInt(str_ed_row);
			ed_row=(ed_row==0)?ed_row:ed_row-1;
		}catch (Exception e) {
		}
		
	}

	@Override
	public Iterator<List<String>> getIterator() {
		class ExcelIterator implements Iterator<List<String>>{
			private Sheet sheet;
			private int index;
	    	public ExcelIterator(){
	    		Workbook book = null;
				try {
					book = ExcelFun.getWorkBook(file);
					sheet=book.getSheetAt(book.getActiveSheetIndex());
				} catch (IOException e) {
					e.printStackTrace();
				}
			   
			    index=st_row;
	    	}
			@Override
			public boolean hasNext() {
				int last=Math.min(sheet.getLastRowNum(), ed_row);
				return index<=last;
			}

			@Override
			public List<String> next() {
				Row row = sheet.getRow(index);
				List<String> rs =new ArrayList<String>();
				for(int i=0;i<row.getLastCellNum();i++){
					Cell cell = row.getCell(i);
					rs.add(ExcelFun.getValue(cell));
				}
				index++;
				return rs;
			}

			@Override
			public void remove() {
				
				
			}
	    	
	    }
		ExcelIterator excelIterator = new ExcelIterator();
		return excelIterator;
	}
    
}
