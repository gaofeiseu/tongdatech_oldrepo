
package com.tongdatech.sys.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.Security;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import weblogic.jdbc.vendor.oracle.OracleThinBlob;
import weblogic.jdbc.vendor.oracle.OracleThinClob;

import com.tongdatech.sys.pojo.ImportResult;
/**
 * @author CY 
 * @version   2011-11-11 下午14:17:42
 * 数据库常用方法封装    <br>
 */
public class DBUtil {
	private static Logger Log = Logger.getLogger(DBUtil.class);
	/** SQL语句中的标题 **/
	public String title;
	/** SQL语句中的列 **/
	public List<String> columns;
	/** 连接 **/
	private Connection conn;
	/** 常量使用List模式存储 **/
	public final static int LISTMODE = 1;
	/** 常量使用HASHMAP模式存储 **/
	public final static int HASHMAPMODE = 2;
	/** 常量使用STRING模式存储，逗号分隔 **/
	public final static int STRINGMODE = 3;
	/** 生产库 **/
	public final static String DBSC = "JDBC/PROJ";
	/** 报表库 **/
	public final static String DBBB = "JDBC/REPORT";
	/** 元数据集 **/
	private ResultSetMetaData rsmd;
	/** statement **/
	private Statement stmt;
	/** preparedStatement **/
	private PreparedStatement ppsm;
	/** 选择的数据连接模式 **/
	private String dbmode;
	/** 自动提交标志 **/
	private boolean autoCommit = true;
	/** 数据库连接数 **/
	private static int conn_num;
	/** 存储过程执行结果 **/
	public boolean callret = false;
	
	private boolean serviceTranslation =false;

	// *******************************重写DBAccess类*****************************//
	// 提供数据源的连接，访问，撤销
	// 提供数据的访问和执行
	// 外部直接使用需要自己连接数据源，并手动释放连接
	// *******************************重写DBAccess类*****************************//

	/**
	 * 连接生产库
	 */
	public DBUtil() {
		this.dbmode = DBSC;
	}

	/**
	 * 可以选择生产库或者报表库
	 * 
	 * @param addr
	 *            使用 DbUtil.DBSC 连接生产库 或者 DbUtil.DBBB 连接报表库
	 */
	public DBUtil(String addr) {
		this.dbmode = addr;
	}

	/**
	 * 使用DBAccess类连接数据库，并使用返回的连接
	 * 
	 * @see DBAccess
	 */
	public void connectByDriver(String driver, String url, String user,
			String pswd) throws Exception {
		try {
			if (driver != null && driver.length() > 0) {
				Class.forName(driver);
			} else {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}
		} catch (ClassNotFoundException e) {
			Log.error("找不到数据库驱动");
		}
		try {
			this.conn = DriverManager.getConnection(url, user, pswd);
			conn_num++;
			Log.debug("数据库连接已建立，现存连接数：" + conn_num);
		} catch (SQLException e) {
			this.conn = null;
			Log.error("连接数据库失败！");
			throw e;
		}
	}

	/**
	 * 建立数据库连接
	 * 
	 * @param dbmode
	 * @throws Exception
	 */
	private void connect(String dbmode) throws Exception {
		try {
			Context ctx = new InitialContext();
			Object obj = ctx.lookup(dbmode);
			DataSource ds = (javax.sql.DataSource) obj;
			this.conn = ds.getConnection();
			conn_num++;
			Log.debug("数据库连接已建立，现存连接数：" + conn_num);
		} catch (Exception e) {
			this.conn = null;
			Log.error("连接数据库失败！");
			throw e;
		}
	}

	/**
	 * 建立数据库连接
	 * 
	 * @throws Exception
	 */
	public void  connect() throws Exception {
		if(serviceTranslation)return;
		connect(dbmode);
	}

	/**
	 * 返回元数据集
	 * 
	 * @return
	 */
	public ResultSetMetaData getMetaData() {
		return this.rsmd;
	}

	/**
	 * 查询 o如果为空直接执行，为list或者不定长度参数则填充完再执行
	 * 
	 * @param sql
	 * @param o
	 * @return ResultSet
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	private ResultSet excuteQuery(String sql, Object... o) throws SQLException {
		if (conn == null) {
			return null;
		}
		try {
			ResultSet rs = null;
			if (o == null || o.length == 0) {
				stmt = conn.createStatement();
				Log.debug("info_sql == > " + sql);
				rs = stmt.executeQuery(sql);
				if (rs != null) {
					rsmd = rs.getMetaData();
				}
			} else if (o[0] instanceof ArrayList) {
				ppsm = conn.prepareStatement(sql);
				fillStatement((List) o[0]);
				Log.debug("info_sql ==> " + getPreparedSQL(sql, o[0]));
				rs = ppsm.executeQuery();
				if (rs != null) {
					rsmd = rs.getMetaData();
				}
			} else {
				ppsm = conn.prepareStatement(sql);
				fillStatement(o);
				Log.debug("info_sql ==> " + getPreparedSQL(sql, o));
				rs = ppsm.executeQuery();
				if (rs != null) {
					rsmd = rs.getMetaData();
				}
			}
			return rs;
		} catch (SQLException e) {
			System.err.println("[DB ERROR]");
			Log.error(" error in DBUtil retrieve(),sql ==> "
					+ getPreparedSQL(sql, o));
			throw e;
		}
	}

	/**
	 * 关闭连接
	 */
	public void disconnect() {
		if(serviceTranslation)return;
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			// 屏蔽错误
		}
		try {
			if (ppsm != null) {
				ppsm.close();
				ppsm = null;
			}
		} catch (SQLException e) {
			// 屏蔽错误
		}
		try {
			if (conn != null) {
				conn.close();
				conn = null;
				conn_num--;
			}
			// Log.debug("数据库连接已关闭");
		} catch (SQLException e) {
			// 屏蔽错误
		}
	}

	/**
	 * 关闭游标
	 */
	private void closeStatement(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			// 屏蔽错误
		}
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			// 屏蔽错误
		}
		try {
			if (ppsm != null) {
				ppsm.close();
				ppsm = null;
			}
		} catch (SQLException e) {
			// 屏蔽错误
		}
	}

	/**
	 * 开始事务
	 * 
	 * @throws SQLException
	 */
	public void startTransaction() throws SQLException {
		if(serviceTranslation)return;
		if (conn != null) {
			conn.setAutoCommit(false);
			autoCommit = false;
		}
	}

	/**
	 * 结束事务
	 * 
	 * @throws SQLException
	 */
	public void endTransaction() throws SQLException {
		if(serviceTranslation)return;
		if (conn != null) {
			conn.commit();
			conn.setAutoCommit(true);
			autoCommit = true;
		}
	}

	/**
	 * 回滚事务
	 * 
	 * @throws SQLException
	 */
	public void rollback() throws SQLException {
		if(serviceTranslation)return;
		if (conn != null && conn.getAutoCommit() == false) {
			conn.rollback();
		}
	}

	/**
	 * 提交并重新开启事务
	 * 
	 * @throws SQLException
	 */
	public void commit() throws SQLException {
		if(serviceTranslation)return;
//		endTransaction();
//		startTransaction();
		if (conn != null && conn.getAutoCommit() == false) {
			conn.commit();
		}
	}

	/**
	 * 执行更新，返回更新数量
	 * 
	 * @param sql
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private int excuteUpdate(String sql, Object... o) throws Exception {
		int ret = 0;
		if (conn != null) {
			try {
				// 如果开启自动提交，则在这开启事务
				if (autoCommit) {
					conn.setAutoCommit(false);
				}
				if (o == null || o.length == 0) {
					stmt = conn.createStatement();
					Log.debug("info_sql == > " + sql);
					ret = stmt.executeUpdate(sql);
				} else if (o[0] instanceof ArrayList) {
					ppsm = conn.prepareStatement(sql);
					fillStatement((List) o[0]);
					Log.debug("info_sql == > " + getPreparedSQL(sql, o[0]));
					ret = ppsm.executeUpdate();
				} else {
					ppsm = conn.prepareStatement(sql);
					fillStatement(o);
					Log.debug("info_sql == > " + getPreparedSQL(sql, o));
					ret = ppsm.executeUpdate();
				}
			} catch (Exception e) {
				try {
					rollback();
					Log.error(" update has rollback() ");
				} catch (SQLException e1) {
					Log.error(" error in DBUtil rollback() ");
				}
				Log.error(" error in DBUtil update(),sql ==> "
						+ getPreparedSQL(sql, o));
				throw e;
			} finally {
				closeStatement(null);
				// 如果开启自动提交，在这里自动提交
				if (autoCommit) {
					conn.commit();
					conn.setAutoCommit(true);
				}
			}
		}
		return ret;
	}

	/**
	 * 批量带参数执行更新
	 * 
	 * @param sql
	 * @param paras
	 * @return int[]
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private int[] excuteUpdateBitch(String sql, List<List> paras)
			throws Exception {
		int ret[] = new int[paras.size()];
		Arrays.fill(ret, 0);
		if (conn != null) {
			try {
				if (autoCommit) {
					conn.setAutoCommit(false);
				}
				ppsm = conn.prepareStatement(sql);
				if (paras != null && paras.size() > 0) {
					for (int i = 0; i < paras.size(); i++) {
						fillStatement(paras.get(i));
						ppsm.addBatch();
					}
				} else {
					ppsm.addBatch();
				}

				ret = ppsm.executeBatch();
			} catch (SQLException e) {
				try {
					rollback();
					Log.error(" update has rollback() ");
				} catch (SQLException e1) {
					Log.error(" error in DBUtil rollback() ");
				}
				Log.error(" error in DBUtil update(),sql ==> " + sql);
				throw e;
			} finally {
				// 关闭游标
				closeStatement(null);
				if (autoCommit) {
					conn.commit();
					conn.setAutoCommit(true);
				}
			}
		}
		return ret;
	}

	/**
	 * 填充preparedStatement
	 */
	@SuppressWarnings("rawtypes")
	private void fillStatement(List list) throws SQLException {
		if (list == null) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				if (list.get(i) instanceof String) {
					ppsm.setString(i + 1, (String) list.get(i));
				} else if (list.get(i) instanceof Integer) {
					ppsm.setInt(i + 1, (Integer) list.get(i));
				} else if (list.get(i) instanceof Double) {
					ppsm.setDouble(i + 1, (Double) list.get(i));
				} else {
					ppsm.setObject(i + 1, list.get(i));
				}
			} else {
				int sqlType = Types.VARCHAR;
				ppsm.setNull(i + 1, sqlType);
			}
		}
	}

	/**
	 * 填充preparedStatement
	 */
	private void fillStatement(Object... p) throws SQLException {
		if (p == null || p.length == 0) {
			return;
		}
		for (int i = 0; i < p.length; i++) {
			if (p[i] != null) {
				if (p[i] instanceof String) {
					ppsm.setString(i + 1, (String) p[i]);
				} else if (p[i] instanceof Double) {
					ppsm.setDouble(i + 1, (Double) p[i]);
				} else if (p[i] instanceof Integer) {
					ppsm.setInt(i + 1, (Integer) p[i]);
				} else {
					ppsm.setObject(i + 1, p[i]);
				}
			} else {
				int sqlType = Types.VARCHAR;
				ppsm.setNull(i + 1, sqlType);
			}
		}
	}

	/**
	 * 打包结果集
	 */
	@SuppressWarnings("rawtypes")
	private List getData(ResultSet rs, int method) throws Exception {
		List<Object> v = new ArrayList<Object>();
		columns=new ArrayList<String>();
		for (int i = 0; rsmd != null && i < rsmd.getColumnCount(); i++) {
		columns.add(rsmd.getColumnLabel(i + 1).toLowerCase());
		}
		try {
			while (rs.next()) {
				v.add(fillData(rs, method));
			}
		} catch (SQLException e) {
			// 添加错误输出
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			Log.error(" error in DBUtil getData() " + sw.toString());
			throw e;
		} finally {
			closeStatement(rs);
		}
		return v;
	}

	/**
	 * 填充数据集
	 */

	private Object fillData(ResultSet rs, int mode) throws SQLException {
		
		if (mode == LISTMODE) {
			List<Object> tmp = new ArrayList<Object>();
			for (int i = 0; rsmd != null && i < rsmd.getColumnCount(); i++) {
				Object objtmp = rs.getObject(i + 1);
				if (objtmp instanceof String) {
					tmp.add(filterNull((String) objtmp));
				} else if (objtmp instanceof BigDecimal) {
					tmp.add(((BigDecimal) objtmp).doubleValue());
				} else if (objtmp == null) {
					tmp.add("");
				} else {
					tmp.add(objtmp);
				}
			}
			return tmp;
		}else if (mode == STRINGMODE) {
			//List<Object> tmp = new ArrayList<Object>();
			StringBuffer tmp =new StringBuffer();
			for (int i = 0; rsmd != null && i < rsmd.getColumnCount(); i++) {
				if(i!=0)tmp.append(",");
				Object objtmp = rs.getObject(i + 1);
				if (objtmp instanceof String) {
					tmp.append(filterNull((String) objtmp));
				} else if (objtmp instanceof BigDecimal) {
					tmp.append(((BigDecimal) objtmp).doubleValue());
				} else if (objtmp == null) {
					tmp.append("");
				} else {
					tmp.append(objtmp);
				}
			}
			return tmp.toString();
		} 
		else if (mode == HASHMAPMODE) {
			
			HashMap<String, Object> tmp = new HashMap<String, Object>();
			for (int i = 0; rsmd != null && i < rsmd.getColumnCount(); i++) {
				Object objtmp = rs.getObject(i + 1);
				
				if (objtmp instanceof String) {
					tmp.put(rsmd.getColumnLabel(i + 1).toLowerCase(),
							filterNull((String) objtmp));
				} else if (objtmp instanceof BigDecimal) {
					tmp.put(rsmd.getColumnLabel(i + 1).toLowerCase(),
							((BigDecimal) objtmp).doubleValue());
				}
				else if(objtmp instanceof weblogic.jdbc.wrapper.Clob){
					tmp.put(rsmd.getColumnLabel(i+1).toLowerCase(), rs.getString(i+1));
				}
				else if (objtmp == null) {
					tmp.put(rsmd.getColumnLabel(i + 1).toLowerCase(), "");
					// } else if (objtmp instanceof java.util.Date){
					// tmp.put(rsmd.getColumnLabel(i + 1).toLowerCase(),
					// (java.util.Date)objtmp);
				} else {
					tmp.put(rsmd.getColumnLabel(i + 1).toLowerCase(), objtmp);
				}
			}
			return tmp;
		}
		return null;
	}

	// *******************************提供的数据库操作*****************************//
	// 提供给外部调用本类实现的方法
	// 封装了查询，更新等常用方法
	// 一般会返回将查询结构进行封装
	// 不需要手动连接关闭数据库连接
	// *******************************提供的数据库操作*****************************//

	/**
	 * 返回已指定分隔符确定的数据集标题，如果不指定，则默认为','
	 * 已废弃
	 * @return
	 * @throws SQLException
	 */
	@Deprecated
	public String getTitle(String split) throws SQLException {
		if (split == null) {
			split = ",";
		}
		title = "";
		if (rsmd != null) {
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				title += rsmd.getColumnLabel(i + 1) + split;
			}
			if (title.length() > 0) {
				title = title.substring(0, title.length() - split.length());
			}
		}
		return title;
	}

	/**
	 * 以LIST方式返回数据集标题
	 * 已废弃
	 * @return
	 * @throws SQLException
	 */
	@Deprecated
	@SuppressWarnings("rawtypes")
	public List getTitleByList() throws SQLException {
		List<String> list = new ArrayList<String>();
		title = getTitle(",");
		if (title != null && !title.equals("")) {
			String[] tmp = title.split(",");
			for (int i = 0; i < tmp.length; i++) {
				list.add(tmp[i]);
			}
		}
		return list;
	}

	/**
	 * 执行查询返回集合，带返回参数
	 * 
	 * @param mode
	 *            LISTMODE 或者 HASHMAPMODE
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List query(String sql, int mode) throws Exception {
		try {
			if (autoCommit) {
				connect(dbmode);
			}
			ResultSet rs = excuteQuery(sql);
			return getData(rs, mode);
		} catch (Exception e) {
			throw e;
		} finally {
			if (autoCommit) {
				disconnect();
			}
		}
	}

	/**
	 * 执行查询返回集合，带参数，以LIST存储 参数可以是LIST或不定参数组合
	 * 
	 * @param sql
	 * @param paras
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List query(String sql, int mode, Object... paras) throws Exception {
		try {
			if (autoCommit) {
				connect(dbmode);
			}
			ResultSet rs = excuteQuery(sql, paras);
			return getData(rs, mode);
		} catch (Exception e) {
			throw e;
		} finally {
			if (autoCommit) {
				disconnect();
			}
		}
	}

	/**
	 * 根据类型填充对象，返回结果集
	 * 
	 * @param sql
	 * @param bean
	 *            bean对象 反射调用
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List query(String sql, Class bean, Object... paras) throws Exception {
		ResultSet rs = null;
		List<Object> v = new ArrayList<Object>();
		try {
			if (autoCommit) {
				connect(dbmode);
			}
			rs = excuteQuery(sql, paras);
			if (rs == null) {
				return v;
			}

			// 取bean的方法属性
			PropertyDescriptor[] pros = Introspector.getBeanInfo(bean)
					.getPropertyDescriptors();
			// 将bean的方法属性对应到元数据集中列的顺序
			int cols = getMetaData().getColumnCount();
			int columnToProperty[] = new int[cols + 1];
			Arrays.fill(columnToProperty, -1);
			for (int col = 1; col <= cols; col++) {
				String columnName = getMetaData().getColumnName(col);
				for (int i = 0; i < pros.length; i++) {
					if (columnName.equalsIgnoreCase(pros[i].getName())) {
						columnToProperty[col] = i;
						break;
					}
				}
			}
			while (rs.next()) {
				// Object obj = null;
				// if(bean instanceof java.lang.Class){
				// obj = bean.newInstance();
				// }else{
				// obj = bean.getClass().newInstance();
				// }
				// for (int i = 1; i < columnToProperty.length; i++) {
				// if (columnToProperty[i] == -1) {
				// continue;
				// }
				// PropertyDescriptor prop = pros[columnToProperty[i]];
				// Class propType = prop.getPropertyType();
				// Object value = null;
				// if (rs.getObject(i) != null) {
				// if (propType.equals(String.class)) {
				// value = rs.getString(i);
				// } else if (propType.equals(Integer.TYPE) ||
				// propType.equals(Integer.class)) {
				// value = new Integer(rs.getInt(i));
				// } else if (propType.equals(Double.TYPE) ||
				// propType.equals(Double.class)) {
				// value = new Double(rs.getDouble(i));
				// } else if (propType.equals(java.util.Date.class)) {
				// value = new java.util.Date(rs.getDate(i).getTime());
				// //value=rs.getDate(i);
				// } else if (propType.equals(Long.TYPE) ||
				// propType.equals(Long.class)) {
				// value = new Long(rs.getLong(i));
				// } else {
				// value = rs.getObject(i);
				// }
				// }
				// Method setter = prop.getWriteMethod();
				// if (setter != null && value != null) {
				// setter.invoke(obj, value);
				// }
				// }
				v.add(fillBean(rs, bean, columnToProperty, pros)); // modify by
																	// xl
																	// 独立成方法方便复用
			}
		} catch (Exception e) {
			throw e;
		} finally {
			closeStatement(rs);
			if (autoCommit) {
				disconnect();
			}
		}
		return v;
	}

	/**
	 * 根据类型填充对象，返回单个对象
	 * 
	 * @param sql
	 * @param bean
	 *            bean对象 反射调用
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Object queryOneLine(String sql, Class bean, Object... paras)
			throws Exception {
		ResultSet rs = null;
		Object obj = null;
		try {
			if (autoCommit) {
				connect(dbmode);
			}
			rs = excuteQuery(sql, paras);
			if (rs == null) {
				return null;
			}

			// 取bean的方法属性
			PropertyDescriptor[] pros = Introspector.getBeanInfo(bean)
					.getPropertyDescriptors();
			// 将bean的方法属性对应到元数据集中列的顺序
			int cols = getMetaData().getColumnCount();
			int columnToProperty[] = new int[cols + 1];
			Arrays.fill(columnToProperty, -1);
			for (int col = 1; col <= cols; col++) {
				String columnName = getMetaData().getColumnName(col);
				for (int i = 0; i < pros.length; i++) {
					if (columnName.equalsIgnoreCase(pros[i].getName())) {
						columnToProperty[col] = i;
						break;
					}
				}
			}
			if (rs.next()) {

				obj = fillBean(rs, bean, columnToProperty, pros); // modify by
																	// xl
			}
		} catch (Exception e) {
			throw e;
		} finally {
			closeStatement(rs);
			if (autoCommit) {
				disconnect();
			}
		}
		return obj;
	}

	/**
	 * 独立成方法方便复用
	 * 
	 * @param rs
	 * @param bean
	 * @param columnToProperty
	 * @param pros
	 * @return
	 * @throws Exception
	 */
	private Object fillBean(ResultSet rs, Class<?> bean,
			int[] columnToProperty, PropertyDescriptor[] pros) throws Exception {
		Object obj = null;
		if (bean instanceof java.lang.Class) {
			obj = bean.newInstance();
		} else {
			obj = bean.getClass().newInstance();
		}
		for (int i = 1; i < columnToProperty.length; i++) {
			if (columnToProperty[i] == -1) {
				continue;
			}
			PropertyDescriptor prop = pros[columnToProperty[i]];
			Class<?> propType = prop.getPropertyType();
			Object value = null;
			if (rs.getObject(i) != null) {
				if (propType.equals(String.class)) {
					value = rs.getString(i);
				} else if (propType.equals(Integer.TYPE)
						|| propType.equals(Integer.class)) {
					value = new Integer(rs.getInt(i));
				} else if (propType.equals(Double.TYPE)
						|| propType.equals(Double.class)) {
					value = new Double(rs.getDouble(i));
				} else if (propType.equals(java.util.Date.class)) {
					//value = new java.util.Date(rs.getDate(i).getTime()); modify  by xl  修复时间精度不够bug
					value = new java.util.Date(rs.getTimestamp(i).getTime());
					//value=rs.getDate(i);
				} else if (propType.equals(Long.TYPE)
						|| propType.equals(Long.class)) {
					value = new Long(rs.getLong(i));
				} else {
					value = rs.getObject(i);
				}
			}
			Method setter = prop.getWriteMethod();
			if (setter != null && value != null) {
				setter.invoke(obj, value);
			}
		}
		return obj;
	}

	/**
	 * 根据类型填充对象，返回结果集
	 * 
	 * @param sql
	 * @param bean
	 *            bean对象
	 * @return
	 * @throws Exception
	 */
	// @SuppressWarnings("rawtypes")
	// public List query(String sql, Class bean) throws Exception {
	// //return query(sql, null, bean);原版倒了?
	// return query(sql,bean,null);
	// }

	/**
	 * 执行查询返回集合，內部以List存储
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List query(String sql) throws Exception {
		return query(sql, HASHMAPMODE);
	}

	/**
	 * 执行查询返回集合，带参数，以List存储
	 * 
	 * @param sql
	 * @param paras
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List query(String sql, Object... paras) throws Exception {
		return query(sql, HASHMAPMODE, paras);
	}

	/**
	 * 执行查询返回第一行第一列结果
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private Object queryOne(String sql, Class c, Object... paras)
			throws Exception {
		ResultSet rs = null;
		Object ret = null;
		try {
			if (autoCommit) {
				connect(dbmode);
			}
			rs = excuteQuery(sql, paras);
			if (rs.next()) {
				if (c.equals(Integer.class)) {
					ret = rs.getInt(1);
				} else if (c.equals(Long.class)) {
					ret = rs.getLong(1);
				} else if (c.equals(String.class)) {
					ret = rs.getString(1);
				} else {
					ret = rs.getObject(1);
					if (ret instanceof BigDecimal) {
						return ((BigDecimal) ret).doubleValue();
					}
				}
			}
		} catch (Exception e) {
			Log.error(" error in DBUtil queryOne() ");
			throw e;
		} finally {
			closeStatement(rs);
			if (autoCommit) {
				disconnect();
			}
		}
		return ret;
	}

	/**
	 * 执行查询返回第一行第一列结果（字符串）
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public String queryString(String sql) throws Exception {
		return (String) queryOne(sql, String.class);
	}

	public String queryString(String sql, Object... para) throws Exception {
		return (String) queryOne(sql, String.class, para);
	}

	/**
	 * 执行查询返回第一行第一列结果（数值）
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int queryInt(String sql) throws Exception {
		return (Integer) queryOne(sql, Integer.class);
	}

	public int queryInt(String sql, Object... para) throws Exception {
		return (Integer) queryOne(sql, Integer.class, para);
	}

	public long queryLong(String sql) throws Exception {
		return (Long) queryOne(sql, Long.class);
	}

	/**
	 * 执行查询返回第一行第一列结果（对象）
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Object queryObj(String sql) throws Exception {
		return queryOne(sql, Object.class);
	}

	public Object queryObj(String sql, Object... para) throws Exception {
		return queryOne(sql, Object.class, para);
	}

	/**
	 * 执行查询返回第一行结果，带模式选择
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Object queryOneLine(String sql, int mode, Object... para)
			throws Exception {
		ResultSet rs = null;
		Object ret = null;
		try {
			if (autoCommit) {
				connect(dbmode);
			}
			rs = excuteQuery(sql, para);
			if (rs.next()) {
				ret = fillData(rs, mode);
			}
		} catch (Exception e) {
			// 添加错误输出
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			Log.error(" error in DBUtil queryOneLine() " + sw.toString());
			throw e;
		} finally {
			closeStatement(rs);
			if (autoCommit) {
				disconnect();
			}
		}
		return ret;
	}

	/**
	 * 执行查询返回第一行结果，以Map存储
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map queryOneLine(String sql, Object... para) throws Exception {
		return (Map) queryOneLine(sql, HASHMAPMODE, para);
	}

	/**
	 * 取得查询结果的记录条数
	 * 
	 * @param sql
	 * @return int
	 * @throws Exception
	 */
	public int count(String sql) throws Exception {
		int row = -1;
		try {
			sql = "select count(*) from ( " + sql + " ) countrow";
			row = queryInt(sql);
		} catch (Exception e) {
			throw e;
		}
		return row;
	}

	/**
	 * 取得查询结果的记录条数
	 * 
	 * @param sql
	 * @return int
	 * @throws Exception
	 */
	public int count(String sql, Object... para) throws Exception {
		int row = -1;
		try {
			sql = "select count(*) from ( " + sql + " ) countrow";
			row = queryInt(sql, para);
		} catch (Exception e) {
			throw e;
		}
		return row;
	}

	/**
	 * 数据库端分页查询方法,注意原始SQL中不能使用别名SQLS,RR
	 * 
	 * @param sql
	 *            原始SQL
	 * @param curPage
	 *            当前页数
	 * @param linePerPage
	 *            每页显示行数
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List queryPage(String sql, int curPage, int linePerPage)
			throws Exception {
		if (linePerPage == 0) {
			return query(sql);
		}
		sql = " select * from (" + " select sqls.*,rownum rr " + " from ("
				+ sql;
		sql += " ) sqls where rownum <= " + curPage * linePerPage;
		sql += " )";
		sql += " where rr > " + (curPage - 1) * linePerPage;
		List list = query(sql);
		return list;
	}

	/**
	 * 返回分页字符串
	 * 
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 */
	public String queryPageStr(String sql, int page, int rows) {
		sql = " select * from (" + " select sqls.*,rownum rr " + " from ("
				+ sql;
		sql += " ) sqls where rownum <= " + page * rows;
		sql += " )";
		sql += " where rr > " + (page - 1) * rows;
		return sql;
	}

	public String queryPageStr2(String sql, int st, int ed) {
		if(st==0&&ed==0)return sql;
		sql = " select * from (" + " select sqls.*,rownum rr " + " from ("
				+ sql;
		sql += " ) sqls )";
		sql += " where rr between " + st + " and " + ed;
		return sql;
	}
	public String queryPageStrOrder1(String sql, int page, int rows) {
		sql = " select * from (" + " select sqls.*,rownum rr " + " from ("
		+ sql;
		sql += " ) sqls where rownum <= " + page * rows;
		sql += " )";
		sql += " where rr > " + (page - 1) * rows+" order by rr";
        return sql;
	}
	public String queryPageStrOrder(String sql, int st, int ed) {
		sql = " select * from (" + " select sqls.*,rownum rr " + " from ("
				+ sql;
		sql += " ) sqls )";
		sql += " where rr between " + st + " and " + ed + " order by rr";
		return sql;
	}

	/**
	 * 执行更新
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int runSql(String sql) throws Exception {
		int ret = -1;
		try {
			if (autoCommit) {
				connect(dbmode);
			}
			// Log.debug("runsql ==> "+sql);
			ret = excuteUpdate(sql);
		} catch (Exception e) {
			Log.error(" error in DBUtil runSql() ");
			throw e;
		} finally {
			if (autoCommit) {
				disconnect();
			}
		}
		return ret;
	}

	/**
	 * 执行更新
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int update(String sql) throws Exception {
		return runSql(sql);
	}

	/**
	 * 执行更新
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int insert(String sql) throws Exception {
		return runSql(sql);
	}

	/**
	 * 执行更新
	 * 
	 * @param sql
	 * @return int
	 * @throws Exception
	 */
	public int delete(String sql) throws Exception {
		return runSql(sql);
	}

	/**
	 * 带参数执行更新
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int runSql(String sql, Object... o) throws Exception {
		int ret = 0;
		try {
			if (autoCommit) {
				connect(dbmode);
			}
			ret = excuteUpdate(sql, o);
		} catch (Exception e) {
			throw e;
		} finally {
			if (autoCommit) {
				disconnect();
			}
		}
		return ret;
	}

	public int update(String sql, Object... o) throws Exception {
		return runSql(sql, o);
	}

	public int insert(String sql, Object... o) throws Exception {
		return runSql(sql, o);
	}

	public int delete(String sql, Object... o) throws Exception {
		return runSql(sql, o);
	}

	/**
	 * 批量执行更新
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int[] runSqlBatch(String sql) throws Exception {
		int[] ret = null;
		try {
			if (autoCommit) {
				connect(dbmode);
			}
			ret = excuteUpdateBitch(sql, null);
		} catch (Exception e) {
			ret = null;
			throw e;
		} finally {
			if (autoCommit) {
				disconnect();
			}
		}
		return ret;
	}

	/**
	 * 批量执行更新
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public int[] runSqlBatch(String sql, List<List> paras) throws Exception {
		int[] ret = null;
		try {
			if (autoCommit) {
				connect(dbmode);
			}
			ret = excuteUpdateBitch(sql, paras);
		} catch (Exception e) {
			ret = null;
			throw e;
		} finally {
			if (autoCommit) {
				disconnect();
			}
		}
		return ret;
	}

	/**
	 * 执行存储过程
	 * 
	 * @param sql
	 *            “call xxxx”
	 * @param vec
	 *            参数列表
	 * @param inout
	 *            模式列表，只能选择 "IN","OUT","INOUT"
	 * 
	 * @return <pre>
	 * CallableStatement对象，可以从中取出模式为OUT或者INOUT的值，如： 		
	 *     CallableStatement cs = dba.callSql("call PROC_QUERY_TEST(?)", vec,inout);
	 *     System.out.println(">>>>>   " + cs.getString(1));
	 * 也可以取出ResultSet数据集，如：      
	 *    while(cs.getMoreResults()){
	 *      ResultSet rs1 = cs.getResultSet();
	 *      while(rs1.next()){
	 *      System.out.println(">>>>" + rs1.getString(1));
	 *    }
	 * </pre>
	 */
	public CallableStatement callSql(String sql, Object[] vec, String[] inout)
			throws Exception {
		CallableStatement cstmt;
		try {
			cstmt = conn.prepareCall(sql);
			for (int i = 0; i < vec.length; i++) {
				if (inout[i].equals("OUT") || inout[i].equals("INOUT")) {
					if (vec[i] instanceof String) {
						cstmt.registerOutParameter(i + 1, Types.VARCHAR);
					} else {
						cstmt.registerOutParameter(i + 1, Types.NUMERIC);
					}
				} else {
					cstmt.setObject(i + 1, vec[i]);
				}
				if (inout[i].equals("INOUT")) {
					cstmt.setObject(i + 1, vec[i]);
				}
			}
			callret = cstmt.execute();
		} catch (Exception e) {
			callret = false;
			Log.error(" error in DBUtil callSql() " + sql);
			throw e;
		}
		return cstmt;
	}

	/**
	 * 批量执行DML<br>
	 * 需要先执行connect 最后执行disconnect<br>
	 * 自行事务控制需要startTransaction endTransaction
	 * @param sql  "insert into t_table (col1,col2) values (?,?)"
	 * @param it  数据迭代器
	 * @param include 包含的列index的集合
	 * @return ImportResult
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public ImportResult BatDML(String sql,Iterator<?> it,Set<Integer> include) throws Exception{
		
		PreparedStatement cstmt = conn.prepareStatement(sql);
		
		int total=0;int success=0;
		
		
	
		ImportResult importResult=new ImportResult(sql);
		
		while(it.hasNext()){
			List<String> data = (List<String>) it.next();
			int j=1;
			for(int i=0;i<data.size();i++){

				if(include.contains(i)){
					String str=data.get(i);
					cstmt.setString(j, str);
					j++;
				}
				
			}
			total++;
			boolean rs= false;
			int ret =0;
			try{
			ret = cstmt.executeUpdate();
			rs=ret>0;
		
			}catch (Exception e) {
				Log.error("BatDML==>"+sql, e);
			}
			
			importResult.getResult().add(rs);
			if(rs)success++;
		}
		cstmt.close();
		importResult.setSuccess(success);
		importResult.setTotal(total);
		
		return importResult;
	}
	public boolean callSqlflag(String sql, Object[] vec, String[] inout)
			throws Exception {
		CallableStatement cstmt;
		try {
			cstmt = conn.prepareCall(sql);
			for (int i = 0; i < vec.length; i++) {
				if (inout[i].equals("OUT") || inout[i].equals("INOUT")) {
					if (vec[i] instanceof String) {
						cstmt.registerOutParameter(i + 1, Types.VARCHAR);
					} else {
						cstmt.registerOutParameter(i + 1, Types.NUMERIC);
					}
				} else {
					cstmt.setObject(i + 1, vec[i]);
				}
				if (inout[i].equals("INOUT")) {
					cstmt.setObject(i + 1, vec[i]);
				}
			}
			callret = cstmt.execute();
		} catch (Exception e) {
			callret = false;
			Log.error(" error in DBUtil callSql() " + sql);
			throw e;
		}
		return callret;
	}

	@SuppressWarnings("rawtypes")
	private String getPreparedSQL(String sql, Object... params) {
		// 1 如果没有参数，说明是不是动态SQL语句
		int paramNum = 0;
		boolean listFlag = false;
		if (null != params) {
			paramNum = params.length;
			if (paramNum > 0 && params[0] instanceof ArrayList) {
				listFlag = true;
				paramNum = ((List) params[0]).size();
			}
		}
		if (1 > paramNum)
			return sql;
		// 2 如果有参数，则是动态SQL语句
		StringBuffer returnSQL = new StringBuffer();
		String[] subSQL = sql.split("\\?");
		for (int i = 0; i < paramNum; i++) {
			if (!listFlag) {
				if (params[i] instanceof String
						&& ((String) params[i]).startsWith("'")
						&& ((String) params[i]).endsWith("'")) {
					returnSQL.append(subSQL[i]).append(params[i]);
				} else {
					returnSQL.append(subSQL[i]).append(" '").append(params[i])
							.append("' ");
				}
			} else {
				returnSQL.append(subSQL[i]).append(" '")
						.append(((List) params[0]).get(i)).append("' ");
			}
		}

		if (subSQL.length > paramNum) {
			returnSQL.append(subSQL[subSQL.length - 1]);
		}
		return returnSQL.toString();
	}

	// **********************************以下用作页面SQL语句加密使用************************************//

	private static String pwd = "gsxt";

	/**
	 * 加密SQL
	 * 
	 * @param str
	 * @return
	 */
//	public String encrypt(String str) {
//		Security.addProvider(new com.sun.crypto.provider.SunJCE());
//		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
//		String strOut = "";
//		try {
//			// DES算法要求有一个可信任的随机数源
//			SecureRandom sr = new SecureRandom();
//			// 从原始密匙数据创建DESKeySpec对象
//			DESKeySpec dks = new DESKeySpec(pwd.getBytes());
//			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
//			// 一个SecretKey对象
//			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//			SecretKey key = keyFactory.generateSecret(dks);
//			// Cipher对象实际完成加密操作
//			Cipher cipher = Cipher.getInstance("DES");
//			// 用密匙初始化Cipher对象
//			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
//			// 现在，获取数据并加密
//			byte data[] = str.getBytes(); /* 用某种方法获取数据 */
//			// 正式执行加密操作
//			byte encryptedData[] = cipher.doFinal(data);
//			strOut = encoder.encode(encryptedData);
//			return URLEncoder.encode(strOut, "UTF8");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	/**
	 * 解密SQL
	 * 
	 * @param s
	 * @return String
	 */
//	public String decrypt(String s) {
//		Security.addProvider(new com.sun.crypto.provider.SunJCE());
//		String strOut = "";
//		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
//		try {
//			// DES算法要求有一个可信任的随机数源
//			SecureRandom sr = new SecureRandom();
//			// 从原始密匙数据创建一个DESKeySpec对象
//			DESKeySpec dks = new DESKeySpec(pwd.getBytes());
//			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
//			// 一个SecretKey对象
//			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//			SecretKey key = keyFactory.generateSecret(dks);
//			// Cipher对象实际完成解密操作
//			Cipher cipher = Cipher.getInstance("DES");
//			// 用密匙初始化Cipher对象
//			cipher.init(Cipher.DECRYPT_MODE, key, sr);
//			// 现在，获取数据并解密
//			byte encryptedData[] = decoder.decodeBuffer(s); /* 获得经过加密的数据 */
//			// 正式执行解密操作
//			byte decryptedData[] = cipher.doFinal(encryptedData);
//			strOut = new String(decryptedData);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return strOut;
//	}

	// *********************************页面生成用***************************************//
	
	
	
	//***********************************************图片录入*********ST*****************************************//
	
	public void putImgIntoDB(String type,String zoom,String x,String y,String exe,byte[] data,String sn) throws Exception{
		try {
			connect();
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			st.executeUpdate("insert into A_TEST_IMG_TABLE (SN, TYPE, ZOOM,X,Y,IMG,EXE) values ("+sn+", '"+type+"',"+zoom+","+x+","+y+", empty_blob(),'"+exe+"')");
			ResultSet rs = st.executeQuery("select IMG from A_TEST_IMG_TABLE where SN="+sn+" for update");
			if (rs.next())
		    {
				OracleThinBlob blob = (OracleThinBlob) rs.getBlob("IMG");
		        OutputStream outStream = blob.getBinaryOutputStream();
		        outStream.write(data, 0, data.length);
		        outStream.flush();
		        outStream.close();
		    }
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				conn.commit();
				conn.setAutoCommit(true);
				autoCommit = true;
				conn.close();
			}
		}
	}
	public byte[] getImgFromDB(String table_name,String zoom,String x,String y) throws Exception{
		byte[] data = null;
		try {
			connect();
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select IMG,EXE from "+table_name+" where 1=1 and zoom="+zoom+" and x="+x+" and y="+y+" ");
			if (rs.next())
		    {
				Blob blob = rs.getBlob("IMG");
				InputStream inStream = blob.getBinaryStream();
				//data是读出并需要返回的数据，类型是byte[]
				data = new byte[(int)blob.length()];
				inStream.read(data);
				inStream.close();
		    }
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				conn.commit();
				conn.setAutoCommit(true);
				autoCommit = true;
				conn.close();
			}
		}
		return data;
	}
	
	//***********************************************图片录入*********ED*****************************************//
	
	//***********************************************CLOB操作*********ST*****************************************//
	public String getClobFromDB(String sn) throws Exception{
		String returnString = null;
		String sql = "select CLOBCOL from A_CLOB_TABLE where 1=1 and id="+sn;
		try{
			connect();
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				Clob clob = rs.getClob("CLOBCOL");
				Reader r = clob.getCharacterStream();
				returnString = r.toString();
				r.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		finally{
			if(conn!=null){
				conn.commit();
				conn.setAutoCommit(true);
				autoCommit=true;
				conn.close();
			}
		}
		return returnString;
	}
	public void putClobIntoDB(String sn,String clobString) throws Exception{
		//测试库中的clob表是A_CLOB_TABLE
		try {
			connect();
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			st.executeUpdate("insert into A_CLOB_TABLE (ID, CLOBCOL) values ("+sn+", EMPTY_CLOB())");
			ResultSet rs = st.executeQuery("select CLOBCOL from A_CLOB_TABLE where ID="+sn+" for update");
			while (rs.next())
		    {
				OracleThinClob clob = (OracleThinClob) rs.getClob("CLOBCOL");
				Writer out = clob.getCharacterOutputStream();
				out.write(clobString);
				out.flush();
				out.close();
		    }
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				conn.commit();
				conn.setAutoCommit(true);
				autoCommit = true;
				conn.close();
			}
		}
	}
	
	
	
	//***********************************************CLOB操作*********ED*****************************************//

	/**
	 * 取总页数
	 * 
	 * @param countnum
	 *            总记录数
	 * @param list_num
	 *            每页条数
	 * @return
	 */
	public int getPage_num(int countnum, int list_num) {
		if (list_num <= 0) {
			return 1;
		}
		int ret = (countnum + list_num - 1) / list_num;
		return ret == 0 ? 1 : ret;
	}

	/**
	 * 过滤空值或者null字段
	 * 
	 * @param src
	 * @return
	 */
	public String filterNull(String src) {
		if (src == null || src.equals("null")) {
			return "";
		}
		return src;
	}

	/**
	 * 返回当前日期
	 * 
	 * @return
	 */
	public String getDate() {
		java.util.Date now = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("YYYYMMDD");
		return format.format(now);
	}

	/**
	 * 返回连接数
	 * 
	 * @return
	 */
	public int getConn_Num() {
		return conn_num;
	}


	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	/**
	 * @return the serviceTranslation
	 */
	public boolean isServiceTranslation() {
		return serviceTranslation;
	}

	/**
	 * @param serviceTranslation the serviceTranslation to set
	 */
	public void setServiceTranslation(boolean serviceTranslation) {
		this.serviceTranslation = serviceTranslation;
	}
	public PreparedStatement getPrepareStatement(String sql) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = conn.prepareStatement(sql);
		return ps;
	}
}
