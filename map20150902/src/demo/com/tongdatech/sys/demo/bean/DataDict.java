/**
 * File name:DataDict.java
 * Create author:XY
 * Create date:2014-3-25
 */
package com.tongdatech.sys.demo.bean;

/**
 * @author XY
 *
 */
public class DataDict {
	private String table_name;
	private String column_name;
	private String data_type;
	private String data_default;
	private String nullable;
	private String col_comments;
	
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getData_default() {
		return data_default;
	}
	public void setData_default(String data_default) {
		this.data_default = data_default;
	}
	public String getNullable() {
		return nullable;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	public String getCol_comments() {
		return col_comments;
	}
	public void setCol_comments(String col_comments) {
		this.col_comments = col_comments;
	}
}