package com.tongdatech.report.bean;

import java.util.List;
import java.util.Map;

public class ExcelBean {
	private String filePath;
	private String excelFileName;
	private String sheetName;
	private List<List<TitleBean>> list_list_titlebean;
	private List<String> list_colname;
	private List<Map<String,Object>> list_rows;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public List<List<TitleBean>> getList_list_titlebean() {
		return list_list_titlebean;
	}
	public void setList_list_titlebean(List<List<TitleBean>> list_list_titlebean) {
		this.list_list_titlebean = list_list_titlebean;
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
	public String toString() {
		return "ExcelBean [filePath=" + filePath + ", excelFileName="
				+ excelFileName + ", sheetName=" + sheetName
				+ ", list_list_titlebean=" + list_list_titlebean
				+ ", list_colname=" + list_colname + ", list_rows=" + list_rows
				+ "]";
	}
}
