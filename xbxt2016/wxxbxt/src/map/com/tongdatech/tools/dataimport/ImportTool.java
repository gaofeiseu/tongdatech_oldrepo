package com.tongdatech.tools.dataimport;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.ImportResult;
import com.tongdatech.sys.pojo.PageUI;

public abstract class ImportTool implements Serializable{
    
	/** long serialVersionUID*/
	private static final long serialVersionUID = 1626035075177475331L;
	protected static Logger log =Logger.getLogger(ImportTool.class);
	
	protected static final int RANDER_ROW = 100;
	public static final String ROW_NUM ="import_rownum";
	protected Map<String, Object> parameters;
	protected File file;
	
	protected char delimiter;
	

	
	
	public ImportTool() {
	
	}
	/**
	 * 反显前组织数据
	 * @return PageUI
	 * @throws IOException 
	 */
	public abstract PageUI beforeRender() throws IOException;
	/**
	 * 解析参数信息
	 */
	public abstract void parseParam();
	
	/**
	 * 获取数据迭代器
	 * @return Iterator
	 */
	public abstract Iterator<List<String>> getIterator();
	
	/**
	 * 生成记录日志sql
	 * @param irs           ImportResult结果
	 * @param userInfo      用户信息
	 * @param im_sn         导入sn
	 * @param process_name  处理过程名称
	 * @return String
	 */
	public String logSql(ImportResult irs, UserInfo userInfo, int im_sn,
			String process_name) {
		String sql="INSERT INTO t_sys_log_import" +
				"            (log_date, im_sn, tool_name, process_name, nick_name," +
				"             brch_no, brch_name, area_no, area_name, role_id," +
				"             role_name, total, success,im_sql" +
				"            )" +
				"     VALUES (sysdate, "+im_sn+", '"+this.getClass().getName()+"', '"+process_name+"', '"+userInfo.getNick_name()+"'," +
				"             "+userInfo.getBrch_no()+", '"+userInfo.getBrch_name()+"', '"+userInfo.getArea_no()+"', '"+userInfo.getArea_name()+"', "+userInfo.getRole_id()+"," +
				"             '"+userInfo.getRole_name()+"', "+irs.getTotal()+", "+irs.getSuccess()+", '"+irs.getSql()+"'" +
				"            )";
		return sql;
	}
	protected String get(String key){
		String[] paramArray=(String[])parameters.get(key);
		return paramArray[0];
	}
	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	public char getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(char delimiter) {
		this.delimiter = delimiter;
	}

	

}
