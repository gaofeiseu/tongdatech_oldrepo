package com.tongdatech.map_mobile.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.tongdatech.map.util.PubFunc;
import com.tongdatech.map.util.TransformLatLng;
import com.tongdatech.map_mobile.bean.Location;
import com.tongdatech.map_mobile.utils.WgsToMar;
import com.tongdatech.sys.base.BaseDao;

public class MobileDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public String wap_login(String login_name,String pass_word){
		String sql = "";
		sql = "select * from t_sys_user where user_name='"+login_name+"' and user_flag='1' and password='"+pass_word+"' ";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			List<Map<String,Object>> list_map = db.query(sql);
			if(list_map.size()==0){
				resultMap.put("code", "0");//code为0代表失败
				resultMap.put("msg", "用户名或密码不正确！");//
			}else{
				resultMap.put("code", "1");//code为1代表成功
				
				String sql2 = "";
				sql2 = "select user_name,to_char(user_id) user_id,nick_name,to_char(brch_no) as brch_no,lng,lat from t_sys_user where user_name='"+login_name+"' and user_flag='1' ";
				List<Map<String,Object>> list_map2 = new ArrayList<Map<String,Object>>();
				list_map2 = db.query(sql2);
				Map<String,Object> map_1 = new HashMap<String,Object>();
				map_1.put("user_name", list_map2.get(0).get("user_name"));//登录名
				map_1.put("user_id", list_map2.get(0).get("user_id"));//登录ID
				map_1.put("nick_name", list_map2.get(0).get("nick_name"));//昵称
				map_1.put("brch_no", list_map2.get(0).get("brch_no"));//机构号
				//map_1.put("user_type", list_map2.get(0).get("user_type"));//用户类型
				map_1.put("lat", list_map2.get(0).get("lat"));//纬度
				map_1.put("lng", list_map2.get(0).get("lng"));//纬度
				resultMap.put("msg", map_1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "0");//code为0代表失败
			resultMap.put("msg", "数据库异常！");
		}
		String json= "";
		try {
			json = JSONUtil.serialize(resultMap);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}


	public String wap_check_in(String login_name, String login_id,
			String check_in_lat, String check_in_lng, String visit_type,
			String visit_custom_name, String visit_content, String visit_note,String visit_class,String img_db_sn,String if_wap,String custom_type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String now_time = sdf.format(new Date());
		String addr="";
		if(check_in_lat!=null&&!"".equals(check_in_lat)&&check_in_lng!=null&&!"".equals(check_in_lng)){
			Location location = new Location();
			location.setLatitude(Double.parseDouble(check_in_lat));
			location.setLongitude(Double.parseDouble(check_in_lng));
			Location location2 = new Location();
			location2 = WgsToMar.transform(location,if_wap);
			check_in_lat = String.valueOf(location2.getLatitude());
			check_in_lng = String.valueOf(location2.getLongitude());
			addr=getAddr(TransformLatLng.Convert_GCJ02_To_BD09(location2.getLatitude(),location2.getLongitude()));
		   
		}
		
		String sql = "";
		sql = "insert into A_WAP_CHECK_IN_INFO" +
				" (sn,login_name,login_id,check_in_lat,check_in_lng," +
				"visit_type,visit_custom_name,visit_content," +
				"visit_note,check_in_time,visit_class,img_id,CHECK_IN_TYPE,check_in_addr,custom_type) values (SEQ_A_WAP_CHECK_IN_INFO.nextval," +
				"'"+login_name+"','"+login_id+"','"+check_in_lat+"','"+check_in_lng
				+"','"+visit_type+"','"+visit_custom_name+"','"+visit_content
				+"','"+visit_note+"','"+now_time+"','"+visit_class+"','"+img_db_sn+"','"+(("".equals(if_wap)||if_wap==null||"null".equals(if_wap))?("0"):(if_wap))+"','"+addr+"','"+custom_type+"') ";
		System.out.println("custom_type---->"+custom_type+"sql---->"+addr);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if(db.insert(sql)==1){
				resultMap.put("code", "1");//code为0代表失败
				resultMap.put("msg", "签到成功！");
			}else{
				resultMap.put("code", "0");//code为0代表失败
				resultMap.put("msg", "签到数据插入失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "0");//code为0代表失败
			resultMap.put("msg", "数据库异常！");
		}
		String json= "";
		try {
			json = JSONUtil.serialize(resultMap);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	public String getAddr(String latlng) {  
        //创建HttpClientBuilder  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        //HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
  
        HttpGet httpGet = new HttpGet("http://api.map.baidu.com/geocoder?location="+latlng.replace("#", ",")+"&output=json");  
       
        System.out.println("http://api.map.baidu.com/geocoder?location="+latlng.replace("#", ",")+"&output=json");  
        String addr="";
        try {  
            //执行get请求  
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);  
            //获取响应消息实体  
            HttpEntity entity = httpResponse.getEntity();  
            //响应状态  
            System.out.println("status:" + httpResponse.getStatusLine());  
            //判断响应实体是否为空  
            if (entity != null) {  
            
              //  System.out.println("contentEncoding:" + entity.getContentEncoding());  
              //  System.out.println("response content:" + EntityUtils.toString(entity));  
                String json=EntityUtils.toString(entity);
                Map map=PubFunc.json2Map(json);
                if(map!=null&&"OK".equals(map.get("status")))
                {
                	Map result=(Map)map.get("result");
                  	addr=result.get("formatted_address")+"";
                   System.out.println(addr);
                 
                }
           }  
        } catch (IOException e) {  
        
            return addr;
        } finally {  
            try {  
            	 //关闭流并释放资源  
                closeableHttpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
   
        return addr;
    }  

	@SuppressWarnings("unchecked")
	public String wap_get_history(String login_name, String login_id,
			String history_time_st, String history_time_ed,String history_now_num,String history_increace_num) {
		String returnStr = "";
		
		String sql = "";
		sql = "select to_char(sn) sn,login_name,login_id,check_in_lat,check_in_lng,visit_type," +
				"visit_custom_name,visit_content,visit_note,check_in_time," +
				"F_PARAMS('MOBILE_VISIT_TYPE',visit_type) visit_type_str,visit_class," +
				"F_PARAMS('MOBILE_VISIT_CLASS',visit_class) visit_class_str" +
				" from a_wap_check_in_info where login_name='"+login_name+"' and login_id='"+login_id+"' " +
				"and to_number(check_in_time)>="+formatTimeStyle(history_time_st,1)+" and to_number(check_in_time)<="+formatTimeStyle(history_time_ed,2)+" order by to_number(sn) desc ";
		int st_row_num = 0;
		int ed_row_num = 0;
		st_row_num = Integer.parseInt(history_now_num) + 1;
		ed_row_num = Integer.parseInt(history_now_num) + Integer.parseInt(history_increace_num);
		sql = "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM ("+sql+") A WHERE ROWNUM <= "+ed_row_num+" ) WHERE RN >= "+st_row_num+" ";
		System.out.println("sql---->"+sql);
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			list_map = db.query(sql);
			if(list_map.size()>100){
				resultMap.put("code", "0");
				resultMap.put("msg", "查询数据过多，请重新选择较短的时间间隔！");
			}else if(list_map.size()==0){
				resultMap.put("code", "0");
				resultMap.put("msg", "无对应的查询结果！");
			}else{
				resultMap.put("code", "1");
				resultMap.put("msg", list_map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "0");
			resultMap.put("msg", "数据库异常！");
		}
		try {
			returnStr = JSONUtil.serialize(resultMap);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return returnStr;
	}
	
	private String formatTimeStyle(String timeStr,int m){
		//timeStr			2014-09-01
		//destStr			20140919114755554
		String returnStr = "";
		if(m==1){
			if(timeStr!=null&&!"".equals(timeStr)){
				String[] str_Arr = timeStr.split("-");
				for (String a : str_Arr) {
					returnStr += a;
				}
			}
			returnStr += "000000000";
		}else if(m==2){
			if(timeStr!=null&&!"".equals(timeStr)){
				String[] str_Arr = timeStr.split("-");
				for (String a : str_Arr) {
					returnStr += a;
				}
			}
			returnStr += "235959999";
		}
		return returnStr;
	}


	public String wap_img_keepindb(String old_filename, String now_filepath,
			String filesize, String filetype) {
		String returnStr = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String now_time = sdf.format(new Date());
		String sql0 = "";
		sql0 = "select SEQ_A_IMG_CHECK_IN_INFO.nextval from dual ";
		String sn = "";
		try {
			sn = db.queryString(sql0);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String sql = "";
		sql = "insert into a_img_check_in_info " +
				"(sn,img_path,img_size,img_type,status,input_time,old_img_name)" +
				" values ("+sn+",'"+now_filepath+"','"
				+filesize+"','"+filetype+"','1','"+now_time+"','"+old_filename+"') ";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			int i=0;
			i = db.insert(sql);
			if(i==1){
				//success
				resultMap.put("code", "1");
				resultMap.put("msg", sn);
			}else{
				//error
				resultMap.put("code", "0");
				resultMap.put("msg", "数据库异常！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "0");
			resultMap.put("msg", "数据库异常！");
		}
		try {
			returnStr = JSONUtil.serialize(resultMap);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return returnStr;
	}


	public String wap_get_history_total_num(String login_name, String login_id,
			String history_time_st, String history_time_ed) {
		String returnStr = "";
		
		String sql = "";
		sql = "select count(*) as total_num " +
				" from a_wap_check_in_info where login_name='"+login_name+"' and login_id='"+login_id+"' " +
				"and to_number(check_in_time)>="+formatTimeStyle(history_time_st,1)+" and to_number(check_in_time)<="+formatTimeStyle(history_time_ed,2)+" order by to_number(sn) desc ";
//		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int total_num = 0;
		try {
			total_num = Integer.parseInt(db.queryString(sql));
//			list_map = db.query(sql);
			if(total_num!=0){
				resultMap.put("code", "1");
				resultMap.put("msg", total_num);
				resultMap.put("login_name", login_name);
				resultMap.put("login_id", login_id);
				resultMap.put("history_time_st", history_time_st);
				resultMap.put("history_time_ed", history_time_ed);
			}else if(total_num==0){
				resultMap.put("code", "0");
				resultMap.put("msg", "无对应的查询结果！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("code", "0");
			resultMap.put("msg", "数据库异常！");
		}
		try {
			returnStr = JSONUtil.serialize(resultMap);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return returnStr;
	}

}
