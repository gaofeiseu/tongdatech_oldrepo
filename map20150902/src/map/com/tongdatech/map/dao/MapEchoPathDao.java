package com.tongdatech.map.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tongdatech.map.bean.MapEchoPathBean;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.sys.base.BaseDao;

public class MapEchoPathDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public MapReturnBean query(MapEchoPathBean bean) {
		MapReturnBean returnBean = new MapReturnBean();
		String query_type = "";
		query_type = bean.getLjhx_query_type();
		String[] query_array = query_type.split(",");
		String sql = "";
		sql = "";
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
//		boolean if_has_2 = false;
//		for(int i=0;i<query_array.length;i++){
//			if("2".equals(query_array[i])){
//				if_has_2 = true;
//				break;
//			}else{
//				continue;
//			}
//		}
		String st_time = "";
		String ed_time = "";
		
		
		sql = "select a.*,b.user_icon from t_map_address_info a,t_sys_user b where 1=1 ";
		for(int i=0;i<query_array.length;i++){
			if("1".equals(query_array[i])){
				st_time = trans_time_to_format(bean.getLjhx_time_st());
				ed_time = trans_time_to_format(bean.getLjhx_time_ed());
				sql += " and to_number(a.record_time)>="+st_time+" and to_number(a.record_time)<="+ed_time+" ";
			}
			else if("2".equals(query_array[i])){
				sql += " and a.addr_type='2' and b.user_type like '%"+bean.getLjhx_query_role_type()+"%' ";
			}
			else if("3".equals(query_array[i])){
				sql += " and a.user_name='"+bean.getLjhx_query_loginname()+"' ";
			}
			else{
				
			}
		}
		sql += " and a.user_name=b.user_name and b.user_flag='1' and a.status='1'";
		
//		if(if_has_2){
//			
//			
//		}else{
//			sql = "select * from t_map_address_info where 1=1 ";
//			for(int i=0;i<query_array.length;i++){
//				if("1".equals(query_array[i])){
//					st_time = trans_time_to_format(bean.getLjhx_time_st());
//					ed_time = trans_time_to_format(bean.getLjhx_time_ed());
//					sql += " and to_number(record_time)>="+st_time+" and to_number(record_time)<="+ed_time+" ";
//				}else if("3".equals(query_array[i])){
//					sql += " and user_name='"+bean.getLjhx_query_loginname()+"' ";
//				}else{
//					
//				}
//			}
//			sql += " and status='1' and addr_type='2' ";
//		}
		
		System.out.println("query_ljhx_data--------->sql::"+sql);
		
		try {
			list_map = db.query(sql);
			System.out.println("list_map----->"+list_map.toString());
			if(list_map.size()==0){
				returnBean.setIf_success(false);
				returnBean.setMsg("没有对应的查询数据！");
			}else{
				returnBean.setIf_success(true);
				returnBean.setMsg("查询成功！");
				returnBean.setList_map(list_map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return returnBean;
	}
	
	private String trans_time_to_format(String time){
		System.out.println("time:"+time);
//		String[] str_arr1 = time.split(" ");
//		//月str_arr1[0] 日str_arr1[1]
//		String[] str_arr2 = str_arr1[0].split("-");
//		//年str_arr2[0]
//		String[] str_arr3 = str_arr2[1].split(":");
//		//时str_arr3[0] 分str_arr3[1] 秒str_arr3[2]
//		String m = "";
//		String d = "";
//		String h = "";
//		String mm = "";
//		if(Integer.parseInt(str_arr1[0])<10){
//			m = "0"+str_arr1[0];
//		}else{
//			m = str_arr1[0];
//		}
//		if(Integer.parseInt(str_arr1[1])<10){
//			d = "0"+str_arr1[1];
//		}else{
//			d = str_arr1[1];
//		}
//		if(Integer.parseInt(str_arr3[0])<10){
//			h = "0"+str_arr3[0];
//		}else{
//			h = str_arr3[0];
//		}
//		if(Integer.parseInt(str_arr3[1])<10){
//			mm = "0"+str_arr3[1];
//		}else{
//			mm = str_arr3[1];
//		}
//		
//		
//		String return_time = "";
//		return_time = str_arr2[0]+m+d+h+mm+"00";
//		System.out.println("time："+return_time);
		String return_time = "";
		String[] s1 = time.split(" ");
		String[] s2 = s1[0].split("-");
		String[] s3 = s1[1].split(":");
		for (int i = 0; i < s2.length; i++) {
			return_time+=s2[i];
		}
		for (int i = 0; i < s3.length; i++) {
			return_time+=s3[i];
		}
		return return_time;
	}

	public MapReturnBean seticons(MapEchoPathBean bean) throws Exception {
		MapReturnBean returnBean = new MapReturnBean();
		
		String mainclass_sn = bean.getU_mainclass_sn();
		String childclass_sn = bean.getU_childclass_sn();
		String img_url = bean.getU_img_url();
		String selected_sns = bean.getU_selected_sns();
		
		System.out.println("主类型SN："+mainclass_sn);
		System.out.println("子类型SN："+childclass_sn);
		System.out.println("图片URL："+img_url);
		System.out.println("选中的SN字符串："+selected_sns);
		
		String table_name = "";
		try {
			table_name = db.queryString("select class_table_name from t_map_class_info where sn='"+childclass_sn+"' and marker_class='"+mainclass_sn+"' and class_status='1' ");
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("设置特征量失败！数据库异常！");
		}
		
		
		String[] sn_arr = selected_sns.split(",");
		boolean if_error = false;
		try {
			db.connect();
			db.startTransaction();
			
			for(int i=0;i<sn_arr.length;i++){
				String sql = "update t_map_class_basics  set mc_markers='"+img_url+"' where marker_sn="+sn_arr[i]+" and table_name='"+table_name+"'";
				int ii = 0;
				ii = db.update(sql);
				if(ii==1){
					continue;
				}else{
					if_error = true;
					break;
				}
			}
			if(if_error){
				db.rollback();
				returnBean.setIf_success(false);
				returnBean.setMsg("特征量设置失败！数据库异常！");
			}else{
				db.commit();
				returnBean.setIf_success(true);
				returnBean.setMsg("特征量设置成功！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("设置特征量失败！数据库异常！");
		}finally{
			db.endTransaction();
			db.disconnect();
		}
		return returnBean;
	}

	public MapReturnBean setLineMarkers(MapEchoPathBean bean) throws Exception {
		MapReturnBean returnBean = new MapReturnBean();
		
		String mainclass_sn = bean.getL_mainclass_sn();
		String childclass_sn = bean.getL_childclass_sn();
		String line_markers = bean.getL_line_marker();
		line_markers = line_markers.toUpperCase();
		String selected_sns = bean.getL_selected_sns();
		
		String table_name = "";
		try {
			table_name = db.queryString("select class_table_name from t_map_class_info where sn='"+childclass_sn+"' and marker_class='"+mainclass_sn+"' and class_status='1' ");
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("设置特征量失败！数据库异常！");
		}
		
		
		String[] sn_arr = selected_sns.split(",");
		boolean if_error = false;
		try {
			db.connect();
			db.startTransaction();
			
			for(int i=0;i<sn_arr.length;i++){

				String sql = "update t_map_class_basics  set mc_markers='"+line_markers+"' where marker_sn="+sn_arr[i]+" and table_name='"+table_name+"'";
				
				int ii = 0;
				ii = db.update(sql);
				if(ii==1){
					continue;
				}else{
					if_error = true;
					break;
				}
			}
			if(if_error){
				db.rollback();
				returnBean.setIf_success(false);
				returnBean.setMsg("特征量设置失败！数据库异常！");
			}else{
				db.commit();
				returnBean.setIf_success(true);
				returnBean.setMsg("特征量设置成功！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("设置特征量失败！数据库异常！");
		}finally{
			db.endTransaction();
			db.disconnect();
		}
		
		
		
		
		
		return returnBean;
	}

	public MapReturnBean setAreaMarkers(MapEchoPathBean bean) throws Exception {
		MapReturnBean returnBean = new MapReturnBean();
		
		
		String mainclass_sn = bean.getA_mainclass_sn();
		String childclass_sn = bean.getA_childclass_sn();
		String area_markers = bean.getA_area_marker();
		area_markers = area_markers.toUpperCase();
		String selected_sns = bean.getA_selected_sns();
		
		String table_name = "";
		try {
			table_name = db.queryString("select class_table_name from t_map_class_info where sn='"+childclass_sn+"' and marker_class='"+mainclass_sn+"' and class_status='1' ");
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("设置特征量失败！数据库异常！");
		}
		
		
		String[] sn_arr = selected_sns.split(",");
		boolean if_error = false;
		try {
			db.connect();
			db.startTransaction();
			
			for(int i=0;i<sn_arr.length;i++){

				String sql = "update t_map_class_basics  set mc_markers='"+area_markers+"' where marker_sn="+sn_arr[i]+" and table_name='"+table_name+"'";
				
				int ii = 0;
				ii = db.update(sql);
				if(ii==1){
					continue;
				}else{
					if_error = true;
					break;
				}
			}
			if(if_error){
				db.rollback();
				returnBean.setIf_success(false);
				returnBean.setMsg("特征量设置失败！数据库异常！");
			}else{
				db.commit();
				returnBean.setIf_success(true);
				returnBean.setMsg("特征量设置成功！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("设置特征量失败！数据库异常！");
		}finally{
			db.endTransaction();
			db.disconnect();
		}
		
		
		
		return returnBean;
	}

}
