package com.tongdatech.sys.base;

import com.tongdatech.sys.util.DBUtil;

/**
 * 数据持久化层 负责数据持久化      <br>
 * @author xl 
 * @version   2014-2-28 下午02:20:48
 */
public abstract class BaseDao {
	/** DBUtil db 数据库链接*/
	protected DBUtil db=new DBUtil("JDBC/PROJ");

	/**
	 * @return the db
	 */
	public DBUtil getDb() {
		return db;
	}

	/**
	 * @param db the db to set
	 */
	public void setDb(DBUtil db) {
		this.db = db;
	}
}
