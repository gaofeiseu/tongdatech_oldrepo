package com.tongdatech.sys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.Role;
import com.tongdatech.sys.bean.UserInfo;

public class LoginDao extends BaseDao {
	
	public Map<?,?> getUser(String user_name) throws Exception {
//        String sql="SELECT user_id,a.password,a.user_name,a.nick_name,a.brch_no,F_BrchName(a.brch_no) brch_name,a.user_flag,a.lat,a.lng FROM" +
//        		"  t_sys_user a  where  a.user_name='"+user_name+"'";
		
		
		String sql="SELECT user_id,a.password,a.user_name,a.nick_name,a.brch_no,F_BrchName(a.brch_no) brch_name,a.user_flag,a.lat,a.lng FROM" +
        		"  t_sys_user a  where  a.user_name=?";
		db.connect();
		PreparedStatement ps = db.getPrepareStatement(sql);
		ps.setString(1, user_name);
		ResultSet res = ps.executeQuery();
		Map<String, Object> rs = new HashMap<String, Object>();
		if (res.next()) {
			rs.put("user_id", res.getDouble("user_id"));
			rs.put("password", res.getString("password"));
			rs.put("user_name", res.getString("user_name"));
			rs.put("nick_name", res.getString("nick_name"));
			rs.put("brch_no", res.getString("brch_no"));
			rs.put("brch_name", res.getString("brch_name"));
			rs.put("user_flag", res.getString("user_flag"));
			rs.put("lat", res.getString("lat"));
			rs.put("lng", res.getString("lng"));
		}
		db.disconnect();
		return rs;
		
//		return db.queryOneLine(sql);
	}


	@SuppressWarnings("unchecked")
	public List<Role> getRoles(String user_name) throws Exception {

       String sql="select f.area_no,f.area_name,f.area_parent,b.user_id," +
       		"c.brch_name,c.brch_no,c.brch_mode,c.brch_flag,c.brch_up," +
       		"d.role_group,d.role_id,d.role_name,d.role_flag,d.role_img  " +
       		"from  t_sys_r_user_br a,t_sys_user b,t_sys_brch c,t_sys_role d,t_sys_area f where  a.user_id=b.user_id and b.brch_no=c.brch_no " +
       		" and a.role_id=d.role_id and c.area_no=f.area_no  and b.user_name='"
    	   +user_name+"' order by d.order_id,c.brch_mode,c.order_id";
		return db.query(sql,Role.class);
		
		
	}
	
	public String getLoginLog(UserInfo userInfo) throws Exception
	{
		String sql = "insert into t_sys_log_login(log_date,user_id,nick_name,brch_no,brch_name,area_no," +
				"area_name,role_id,role_name,user_ip,user_client,user_ver)values" +
				"(sysdate,'"+userInfo.getUser_id()+"','"+userInfo.getNick_name()+"','"+userInfo.getBrch_no()+"'," +
				"'"+userInfo.getBrch_name()+"','"+userInfo.getArea_no()+"','"+userInfo.getArea_name()+"'," +
				"'"+userInfo.getRole_id()+"','"+userInfo.getRole_name()+"','"+userInfo.getUser_ip()+"'," +
				"'"+userInfo.getUser_client()+"','"+userInfo.getUser_ver()+"')";
		db.insert(sql);
		return null;
	}
}
