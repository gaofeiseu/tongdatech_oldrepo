package com.tongdatech.map.util;


/**
 * 操作日志记录
 */
public class AppLog {
	String action;	//客户端请求的操作
	String from_client;	//从客户端接收
	String to_server;	//发送给服务端
	String from_server;	//从服务端接收
	String to_client;	//发送给客户端
	String from_client_time;	//从客户端接收时间
	String to_server_time;	//发送给服务端时间
	String from_server_time;	//从服务端接收时间
	String to_client_time; 	//发送给客户端时间
	
	public AppLog() {

	}
	
	public AppLog(String action, String from_client, String to_server,
			String from_server, String to_client, String from_client_time,
			String to_server_time, String from_server_time,
			String to_client_time) {
		super();
		this.action = action;
		this.from_client = from_client;
		this.to_server = to_server;
		this.from_server = from_server;
		this.to_client = to_client;
		this.from_client_time = from_client_time;
		this.to_server_time = to_server_time;
		this.from_server_time = from_server_time;
		this.to_client_time = to_client_time;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFrom_client() {
		return from_client;
	}

	public void setFrom_client(String from_client) {
		this.from_client = from_client;
	}

	public String getTo_server() {
		return to_server;
	}

	public void setTo_server(String to_server) {
		this.to_server = to_server;
	}

	public String getFrom_server() {
		return from_server;
	}

	public void setFrom_server(String from_server) {
		this.from_server = from_server;
	}

	public String getTo_client() {
		return to_client;
	}

	public void setTo_client(String to_client) {
		this.to_client = to_client;
	}

	public String getFrom_client_time() {
		return from_client_time;
	}

	public void setFrom_client_time(String from_client_time) {
		this.from_client_time = from_client_time;
	}

	public String getTo_server_time() {
		return to_server_time;
	}

	public void setTo_server_time(String to_server_time) {
		this.to_server_time = to_server_time;
	}

	public String getFrom_server_time() {
		return from_server_time;
	}

	public void setFrom_server_time(String from_server_time) {
		this.from_server_time = from_server_time;
	}

	public String getTo_client_time() {
		return to_client_time;
	}

	public void setTo_client_time(String to_client_time) {
		this.to_client_time = to_client_time;
	}

	/**
	 * 记录日志到数据库
	 */
	public void writeDb(){	
		/*
		StringBuffer buffer = new StringBuffer("insert into t_app_log(id,action,from_client,to_server,from_server")
			.append(",to_client,from_client_time,to_server_time,from_server_time,to_client_time) select")
			.append(" seq_app_log.nextval")
			.append(",'").append(action).append("'")
			.append(",'").append(from_client).append("'")
			.append(",'").append(to_server).append("'")
			.append(",'").append(from_server).append("'")
			.append(",'").append(to_client).append("'")
			.append(",'").append(from_client_time).append("'")
			.append(",'").append(to_server_time).append("'")
			.append(",'").append(from_server_time).append("'")
			.append(",'").append(to_client_time).append("'")
			.append(" from dual")		
			;		
		String sql = buffer.toString();
		
//		System.out.println(sql);
		
		DBAccess db = new DBAccess();
		db.setConnection();
		db.connect();
		
		try {
			db.startTransaction();
			db.insert(sql);
			db.commit();
		} catch (SQLException e) {
			try {
				db.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("记录日志出错！");
			e.printStackTrace();
		} finally{
			db.disconnect();
		}		
	*/	
	}
	
}
