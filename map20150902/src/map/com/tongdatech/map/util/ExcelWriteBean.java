package com.tongdatech.map.util;

import java.util.List;
import java.util.Map;

public class ExcelWriteBean {
	private String filepath;
	private String excelFilename;
	private String sheetname;
	private List<String> list_title;
	private List<String> list_colname;
	private List<Map<String,Object>> list_rows;
	
	
	public String getSheetname() {
		return sheetname;
	}
	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getExcelFilename() {
		return excelFilename;
	}
	public void setExcelFilename(String excelFilename) {
		this.excelFilename = excelFilename;
	}
	public List<String> getList_title() {
		return list_title;
	}
	public void setList_title(List<String> list_title) {
		this.list_title = list_title;
	}
	public List<String> getList_colname() {
		return list_colname;
	}
	public void setList_colname(List<String> list_colname) {
		this.list_colname = list_colname;
	}
	public List<Map<String, Object>> getList_rows() {
		return list_rows;
	}
	public void setList_rows(List<Map<String, Object>> list_rows) {
		this.list_rows = list_rows;
	}
	@Override
	public String toString() {
		return "ExcelWriteBean [filepath=" + filepath + ", excelFilename="
				+ excelFilename + ", list_title=" + list_title.toString()
				+ ", list_colname=" + list_colname.toString() + ", list_rows=" + list_rows.toString()
				+ "]";
	}
}
