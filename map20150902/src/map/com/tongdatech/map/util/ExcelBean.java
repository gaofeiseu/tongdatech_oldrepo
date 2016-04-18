package com.tongdatech.map.util;

import java.util.List;

public class ExcelBean {
	private String filepath;
	private String excelFileName;
	private List<String> list_title;
	private List<List<String>> list_rows;
	
	
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	public List<String> getList_title() {
		return list_title;
	}
	public void setList_title(List<String> list_title) {
		this.list_title = list_title;
	}
	public List<List<String>> getList_rows() {
		return list_rows;
	}
	public void setList_rows(List<List<String>> list_rows) {
		this.list_rows = list_rows;
	}
	
	
}
