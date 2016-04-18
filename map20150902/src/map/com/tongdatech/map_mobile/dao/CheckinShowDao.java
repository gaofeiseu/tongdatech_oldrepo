package com.tongdatech.map_mobile.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tongdatech.map_mobile.bean.CheckinShowBean;
import com.tongdatech.map_mobile.bean.MobileReturnBean;
import com.tongdatech.sys.base.BaseDao;

public class CheckinShowDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public MobileReturnBean query(CheckinShowBean bean) {
		long time_st1 = (new Date()).getTime();
		MobileReturnBean returnBean = new MobileReturnBean();
		String query_type = "";
		query_type = bean.getLjhx_query_type();
		String[] query_array = query_type.split(",");
		String sql = "";
		String sql_s = "";
		sql = "";
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		String st_time = "";
		String ed_time = "";
		boolean if_need_brchtable = false;
		for(int i=0;i<query_array.length;i++){
			if("4".equals(query_array[i])&&"1".equals(bean.getIf_include_childbrch())){
				if_need_brchtable = true;
				break;
			}
		}
		sql = "select a.*,b.nick_name,F_PARAMS('MOBILE_VISIT_TYPE',a.visit_type) visit_type_str," +
				"F_PARAMS('MOBILE_VISIT_CLASS',a.visit_class) visit_class_str,b.user_icon,F_PARAMS('CHECK_IN_TYPE',NVL(a.CHECK_IN_TYPE,'0')) CHECK_IN_TYPE_STR";
		sql_s = " from a_wap_check_in_info a,t_sys_user b where 1=1 ";
		//"+((if_need_brchtable)?(",t_sys_brch c "):(""))+"
		boolean if_has_4 = false;
		for(int i=0;i<query_array.length;i++){
			if("1".equals(query_array[i])){
				st_time = trans_time_to_format(bean.getLjhx_time_st());
				ed_time = trans_time_to_format(bean.getLjhx_time_ed());
				sql_s += " and to_number(a.check_in_time)>="+st_time+" and to_number(a.check_in_time)<="+ed_time+" ";
			}
			else if("2".equals(query_array[i])){
				sql_s += " and a.visit_type='"+bean.getLjhx_query_role_type()+"' ";
			}
			else if("3".equals(query_array[i])){
				sql_s += " and a.login_name='"+bean.getLjhx_query_loginname()+"' ";
			}
			else if("4".equals(query_array[i])){
				if_has_4 = true;
				if("1".equals(bean.getIf_include_childbrch())){
					//包含下级机构
					sql_s += " and b.brch_no in (select brch_no from t_sys_brch start with brch_no='"+bean.getLjhx_query_brchno()+"' connect by prior brch_no = brch_up) ";
				}else if("0".equals(bean.getIf_include_childbrch())){
					//不包含下级机构
					sql_s += " and b.brch_no='"+bean.getLjhx_query_brchno()+"' ";
				}
			}
			else if("5".equals(query_array[i])){
				if("0".equals(bean.getLjhx_location_type())){
					sql_s += " and (a.CHECK_IN_TYPE is null or a.CHECK_IN_TYPE='0') ";
				}else{
					sql_s += " and a.CHECK_IN_TYPE='"+bean.getLjhx_location_type()+"' ";
				}
			}
			else if("6".equals(query_array[i])){
				sql_s += " and a.VISIT_CLASS='"+bean.getQuery_zoufang_type()+"' ";
			}
			else{
				System.out.println("好奇怪的事情发生啦");
			}
		}
		if(if_has_4){
			
		}else{
			sql_s += " and b.brch_no in (select brch_no from t_sys_brch start with brch_no='"+bean.getRoot_brch()+"' connect by prior brch_no = brch_up) ";
		}
		sql_s += " and a.login_name=b.user_name and a.login_id != 'null' and a.login_id is not null and a.visit_class is not null and a.visit_class != 'null' and a.login_id=b.user_id and b.user_flag='1' ";
		
		sql = sql+sql_s;
		System.out.println("query_ljhx_data--------->sql::"+sql);
		
		try {
			list_map = db.query(sql);
			//System.out.println("list_map----->"+list_map.toString());
			long time_ed1 = (new Date()).getTime();
			System.out.println("步骤1耗时:"+(time_ed1-time_st1)+"毫秒");
			if(list_map.size()==0){
				returnBean.setIf_success(false);
				returnBean.setMsg("没有对应的查询数据！");
			}else{
				returnBean.setIf_success(true);
				returnBean.setMsg("查询成功！");
				returnBean.setList_map(list_map);
				returnBean.setReturnString(sql_s);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return returnBean;
	}
	
	@SuppressWarnings("unchecked")
	public MobileReturnBean getTableWithAjax(CheckinShowBean bean){
		MobileReturnBean returnBean = new MobileReturnBean();
		try{
			String sql_s = bean.getSql();
			long time_st = (new Date()).getTime();
			String sql1 = "SELECT F_PARAMS('MOBILE_VISIT_CLASS',a.visit_class) visit_class_str,count(*) as num"
				+sql_s+" group by a.visit_class ";
			List<Map<String,Object>> list_detail_map = new ArrayList<Map<String,Object>>();
			list_detail_map = db.query(sql1);
			returnBean.setList_detail_map(list_detail_map);
			String sql2 = "select a.login_name as login_name,b.nick_name as nick_name from (SELECT a.login_name "+sql_s +" group by a.login_name) a,t_sys_user b where a.login_name=b.user_name ";
			List<Map<String,Object>> list_distinct_user = new ArrayList<Map<String,Object>>();
			list_distinct_user = db.query(sql2);
			List<Map<String,Object>> list_more_detail_map = new ArrayList<Map<String,Object>>();
			List<String> list_more_detail_title = new ArrayList<String>();
			list_more_detail_title.add("姓名");
			list_more_detail_title.add("昵称");
			list_more_detail_title.add("小计");
			String sql12 = "select F_PARAMS('MOBILE_VISIT_CLASS',visit_class) visit_class_str,visit_class from" +
					" (select distinct(visit_class) from a_wap_check_in_info where visit_class is not null and visit_class !='null') ";
			//System.out.println("@@@title_sql---->"+sql12);
			List<Map<String,Object>> list_tmp12 = new ArrayList<Map<String,Object>>();
			list_tmp12 = db.query(sql12);
			List<String> list_title_tt = new ArrayList<String>();
			for(int i=0;i<list_tmp12.size();i++){
				list_more_detail_title.add(String.valueOf(list_tmp12.get(i).get("visit_class_str")));
				list_title_tt.add(String.valueOf(list_tmp12.get(i).get("visit_class")));
			}
			String ss = "tmp_";
			for(int i=0;i<list_distinct_user.size();i++){
				Map<String,Object> map1 = new HashMap<String,Object>();
				String user_name = String.valueOf(list_distinct_user.get(i).get("login_name"));
				String nick_name = String.valueOf(list_distinct_user.get(i).get("nick_name"));
				map1.put("name", user_name);
				map1.put("nick_name", nick_name);
				int nn = 0;
				for(int j=0;j<list_title_tt.size();j++){
					String sql3 = "SELECT count(*) as num,F_PARAMS('MOBILE_VISIT_CLASS',a.visit_class) visit_class_str "
						+sql_s+" and a.login_name='"+user_name+"' and a.visit_class='"+list_title_tt.get(j)
						+"' group by a.visit_class ";
					//System.out.println("@@@detail_sql---->"+sql3);
					List<Map<String,Object>> list_map_tmp = new ArrayList<Map<String,Object>>();
					list_map_tmp = db.query(sql3);
					Object num_nn = "";
					if(list_map_tmp.size()==0){
						num_nn = "0";
					}else{
						num_nn = list_map_tmp.get(0).get("num");
						nn += Integer.parseInt(String.valueOf(num_nn).substring(0, String.valueOf(num_nn).length()-2));
					}
					map1.put(ss+String.valueOf(j), num_nn);
					
				}
				map1.put("xiao_ji", nn);
				list_more_detail_map.add(map1);
			}
			returnBean.setList_more_detail_map(list_more_detail_map);
			returnBean.setList_more_detail_title(list_more_detail_title);
			
			returnBean.setIf_success(true);
			returnBean.setMsg("查询成功！");
			long time_ed = (new Date()).getTime();
			System.out.println("步骤2耗时："+(time_ed-time_st)+"毫秒");
		}
		catch(Exception ex){
			ex.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(ex.getMessage());
		}
		return returnBean;
	}

	private String trans_time_to_format(String time){
		System.out.println("time:"+time);
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
		return_time += "000";
		System.out.println("处理之后的time:"+return_time);
		return return_time;
	}
	
}
