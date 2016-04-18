package com.tongdatech.map.MobileServiceImpl;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;

import com.tongdatech.map.MobileService.MobileService;
import com.tongdatech.map.dao.MapMobileServiceDao;
import com.tongdatech.map_mobile.dao.MobileDao;



public class MobileServiceImpl implements MobileService {


	public String map_phone_checkuser(String params,String params2) {
		System.out.println("--------------->map_phone_checkuser");
		System.out.println("map_phone_checkuser----->param1:"+params+"  param2:"+params2);
		MapMobileServiceDao mapDao = new MapMobileServiceDao();
		String returnString = "";
		returnString = mapDao.map_phone_checkuser(params, params2);
		////////////// add by zl 20140630////////////////////
		JSONObject resultJso = null;
		try {
		resultJso = new JSONObject(returnString);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Map<String,Object> msgMap = new HashMap<String, Object>();			
		
		String code = resultJso.getString("code");
		resultMap.put("code", code);
		
		
		if("0".equals(code)){
			msgMap.put("msg", resultJso.getString("msg"));
			resultMap.put("msg", new JSONObject(msgMap).toString());
		}else{
			String user_name=resultJso.getString("user_name");//登录名
		//	String user_type=resultJso.getString("user_type");//部门类型
			String user_level=resultJso.getString("user_level");//角色类型（数字）
			String user_nickname=resultJso.getString("user_nickname");//昵称
		
			msgMap.put("user_name", user_name);
			msgMap.put("user_nickname", user_nickname);	
		//	msgMap.put("user_type", user_type);	
			msgMap.put("user_level", user_level);
			resultMap.put("msg", new JSONObject(msgMap).toString());
		}
		
	
		
		returnString = new JSONObject(resultMap).toString();
		}catch (Exception e) {
			e.printStackTrace();
	
		}
		return returnString;
	}

	public String map_phone_submitdata(String params, String params2,
			String params3, String params4, String params5) {
		System.out.println("--------------------->map_phone_submitdata");
		MapMobileServiceDao mapDao = new MapMobileServiceDao();
		String returnString = "";
		returnString = mapDao.map_phone_submitdata(params,params2,params3,params4,params5);
		return returnString;
	}

	@Override
	public String wap_login(String param1, String param2) {
		String returnStr = "";
		MobileDao dao = new MobileDao();
		returnStr = dao.wap_login(param1,param2);
		return returnStr;
	}

	@Override
	public String wap_check_in(String login_name, String login_id,
			String check_in_lat, String check_in_lng, String visit_type,
			String visit_custom_name, String visit_content, String visit_note,String visit_class,String img_db_sn,String if_wap,String custom_type) {
		String returnStr = "";
		MobileDao dao = new MobileDao();
		returnStr = dao.wap_check_in(login_name,login_id,check_in_lat,check_in_lng,visit_type,visit_custom_name,visit_content,visit_note,visit_class,img_db_sn,if_wap,custom_type);
		return returnStr;
	}

	@Override
	public String wap_get_history(String login_name, String login_id,
			String history_time_st, String history_time_ed,String history_now_num,String history_increace_num) {
		String returnStr = "";
		MobileDao dao = new MobileDao();
		returnStr = dao.wap_get_history(login_name,login_id,history_time_st,history_time_ed,history_now_num,history_increace_num);
		return returnStr;
	}

	@Override
	public String wap_img_keepindb(String old_filename, String now_filepath,
			String filesize, String filetype) {
		String returnStr = "";
		MobileDao dao = new MobileDao();
		returnStr = dao.wap_img_keepindb(old_filename,now_filepath,filesize,filetype);
		return returnStr;
	}

	@Override
	public String wap_get_history_total_num(String login_name, String login_id,
			String history_time_st, String history_time_ed) {
		String returnStr = "";
		MobileDao dao = new MobileDao();
		returnStr = dao.wap_get_history_total_num(login_name,login_id,history_time_st,history_time_ed);
		return returnStr;
	}
	
}
