package com.tongdatech.sys.base;

import com.tongdatech.sys.util.DBUtil;

/**
 * ���ݳ־û��� �������ݳ־û�      <br>
 * @author xl 
 * @version   2014-2-28 ����02:20:48
 */
public abstract class BaseDao {
	/** DBUtil db ���ݿ�����*/
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
