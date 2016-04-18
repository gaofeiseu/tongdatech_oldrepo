/**
 * File name:DataDictDao.java
 * Create author:XY
 * Create date:2014-3-25
 */
package com.tongdatech.sys.demo.dao;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.demo.bean.DataDict;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

/**
 * @author XY
 * 
 */
public class DataDictDao extends BaseDao {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(DataDictDao.class);

	/**
	 * 获取数据库中表名
	 * @param dataDict
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> tableList(DataDict dataDict)throws Exception {

		String sql = "";
		String tablename = dataDict.getTable_name();
		if(tablename==null||tablename.equals("")){
			sql = "select table_name,comments from user_tab_comments "
				 +"where table_name in(select table_name from user_tables)";
		}else{
			tablename = URLDecoder.decode(tablename, "UTF-8");//将保留字符","进行解码,将tablename由GBK解码为UFT-8
			tablename = tablename.replace(",", "','");
			sql += "select table_name,comments from user_tab_comments "
				  +"where table_name in ('"+tablename.toUpperCase()+"')";
		}
		List tabList = new ArrayList();
		tabList = db.query(sql);
		return tabList;
	}
	
	/**
	 * 查询指定数据库表信息
	 * @param pagination
	 * @param tablename
	 * @return
	 * @throws Exception
	 */
	public PageUI query(Pagination pagination, String tablename)
			throws Exception {

		String sql = "select a.table_name as table_name,a.column_name as column_name,"
			+ " decode(a.char_length,0,decode (a.data_scale, null,a.data_type,"
			+ " a.data_type||'('||a.data_precision ||','|| a.data_scale|| ')'),"
			+ " a.data_type ||'('||a.char_length ||' '||'byte' ||')') as data_type,"
			+ " a.data_default as data_defalut,a.nullable as nullable,"
			+ " b.comments as col_comments" 
			+ " from sys.user_tab_columns a, sys.user_col_comments b"
			+ " where a.table_name in('"+tablename+"') and a.table_name=b.table_name and "
			+ " a.column_name = b.column_name order by a.table_name";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, DataDict.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	/**
	 * 查询数据库表信息
	 * @param pagination
	 * @param dataDict 
	 * @param tablename
	 * @return
	 * @throws Exception
	 */
	public PageUI list(Pagination pagination, DataDict dataDict)
			throws Exception {
        StringBuffer whereSql = new StringBuffer();
        if(dataDict.getTable_name()!=null&&!"".equals(dataDict.getTable_name())){
        	whereSql.append(" and table_name like '%"+dataDict.getTable_name().toUpperCase()+"%'");
        }
		String sql = "select a.table_name as table_name,a.column_name as column_name,"
			+ " decode(a.char_length,0,decode (a.data_scale, null,a.data_type,"
			+ " a.data_type||'('||a.data_precision ||','|| a.data_scale|| ')'),"
			+ " a.data_type ||'('||a.char_length ||' '||'byte' ||')') as data_type,"
			+ " a.data_default as data_defalut,a.nullable as nullable,"
			+ " b.comments as col_comments" 
			+ " from sys.user_tab_columns a, sys.user_col_comments b"
			+ " where a.table_name in(select table_name from user_tables where 1=1 "+whereSql+") and a.table_name=b.table_name and "
			+ " a.column_name = b.column_name order by a.table_name";
		
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, DataDict.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
}
