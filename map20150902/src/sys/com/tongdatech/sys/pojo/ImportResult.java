package com.tongdatech.sys.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 导入结果对象       <br>
 * @author xl 
 * @version    2014-5-14 上午09:58:58
 */
public class ImportResult {

	private int total;
	private int success;
	private String sql;
	private List<Boolean> result;
	

	
	
	public ImportResult(String sql) {
		super();
		this.sql = sql;
		result =new ArrayList<Boolean>();
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @return the success
	 */
	public int getSuccess() {
		return success;
	}
	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}
	/**
	 * @return the result
	 */
	public List<Boolean> getResult() {
		return result;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(int success) {
		this.success = success;
	}
	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(List<Boolean> result) {
		this.result = result;
	}
	
}
