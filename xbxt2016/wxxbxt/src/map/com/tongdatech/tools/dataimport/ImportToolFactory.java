package com.tongdatech.tools.dataimport;

import com.tongdatech.tools.dataimport.impl.ExcelTool;
import com.tongdatech.tools.dataimport.impl.CsvTool;
import com.tongdatech.tools.dataimport.impl.DbfTool;

/**
 * 简单工厂模式
 * @author xl
 *
 */
public class ImportToolFactory {
	public static final String EXCEL="1";
	public static final String CSV  ="2";
	public static final String DBF  ="3";
	
	
	private String type;

    
	public ImportToolFactory(String type) {
		this.type=type;

	}
	
	public ImportTool getImportTool(){
		if(EXCEL.equals(type)){
			return new ExcelTool();
		}
		if(CSV.equals(type)){
			return new CsvTool();
		}
		if(DBF.equals(type)){
			return new DbfTool();
		}
		return null;
	}

}
