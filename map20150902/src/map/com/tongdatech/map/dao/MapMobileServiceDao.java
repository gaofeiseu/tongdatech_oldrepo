package com.tongdatech.map.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tongdatech.sys.base.BaseDao;

public class MapMobileServiceDao extends BaseDao {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(MapMobileServiceDao.class);
	
	@SuppressWarnings("unchecked")
	public String map_phone_checkuser(String username,String password){
		String sql = "";
		sql = "select * from t_sys_user where user_name='"+username+"' and user_flag='1' ";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			List<Map<String,Object>> list_map = db.query(sql);
			if(list_map.size()==0){
				resultMap.put("code", "0");//code为0代表失败
				resultMap.put("msg", "该用户名不存在！");
			}else{
				String sql1 = "";
				sql1 = "select * from t_sys_user where user_name='"+username+"' and user_flag='1' and password='"+password+"' ";
				List<Map<String,Object>> list_map1 = new ArrayList<Map<String,Object>>();
				list_map1 = db.query(sql1);
				if(list_map1.size()==0){
					resultMap.put("code", "0");//code为0代表失败
					resultMap.put("msg", "密码错误！");
				}else{
					resultMap.put("code", "1");//code为1代表成功
					resultMap.put("msg", "T");
					String sql2 = "";
					sql2 = "select brch_no as user_type,user_type as user_level,nick_name as user_nickname,F_PARAMS('USER_TYPE',user_type) as user_comment from t_sys_user where user_name='"+username+"' and user_flag='1' ";
					List<Map<String,Object>> list_map2 = new ArrayList<Map<String,Object>>();
					list_map2 = db.query(sql2);
					
					resultMap.put("user_name", list_map.get(0).get("user_name"));//登录名
					resultMap.put("user_type", list_map2.get(0).get("user_type"));//brch_no
					resultMap.put("user_level", list_map2.get(0).get("user_level"));//user_type----1 邮政 2宜家（数字）
					resultMap.put("user_nickname", list_map2.get(0).get("user_nickname"));//昵称
					resultMap.put("user_comment", list_map2.get(0).get("user_comment"));//user_type----邮政宜家(汉字)
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "0");//code为0代表失败
			resultMap.put("msg", "DB-ERROR");
		}
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<HashMap<String, Object>>() {}.getType();  
		String json= "";
		json = gson.toJson(resultMap, type);
		return json;
	}

	public String map_phone_submitdata(String params, String params2,String params3, String params4, String params5) {
		String sql = "";
		sql = "select SEQ_T_MAP_ADDR.nextval from dual";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String sn = db.queryString(sql);
			String insertSQL = "";
			insertSQL = "insert into t_map_address_info " +
					"(sn,address,lat,lon,addr_type,status,user_name,record_time)" +
					" values " +
					"("+sn+",'"+params3+"','"+params4+"','"+params5+"','2','1','"+params+"','"+params2+"')";
			int insertNum = 0;
			insertNum = db.insert(insertSQL);
			if(insertNum==1){ //add by zl 20140630 insertNum!=1
				//true
				resultMap.put("code", "1");//code为0代表失败
				resultMap.put("msg", "DB-SUCCESS");
			}else{
				//false
				resultMap.put("code", "0");//code为0代表失败
				resultMap.put("msg", "DB-ERROR");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "0");//code为0代表失败
			resultMap.put("msg", "DB-ERROR");
		}
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<HashMap<String, Object>>() {}.getType();  
		String json= "";
		json = gson.toJson(resultMap, type);
		return json;
	}
	
	
}
