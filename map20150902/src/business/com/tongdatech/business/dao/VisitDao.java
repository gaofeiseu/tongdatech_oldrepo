/**
 * File name:VisitDao.java
 * Create author:
 * Create date:
 */
package com.tongdatech.business.dao;

 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tongdatech.business.bean.Param;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserInfo;

import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;


/**
 * @author 
 * 
 */
public class VisitDao extends BaseDao {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(VisitDao.class);
	public PageUI query(Pagination pagination, Param param,UserInfo userInfo) throws Exception {

		
		StringBuffer whereSql = new  StringBuffer();
		String brch_no=userInfo.getBrch_no();
		if (param.getLogin_name() != null && param.getLogin_name().length() > 0) {
			whereSql.append(" and login_name like '%" + param.getLogin_name()+ "%'");
		}
		if (param.getVisit_type() != null && param.getVisit_type().length() > 0) {
			whereSql.append(" and visit_type = '" + param.getVisit_type()+ "'");
		}
		if (param.getVisit_class() != null && param.getVisit_class().length() > 0) {
			whereSql.append(" and visit_class = '" + param.getVisit_class()+ "'");
		}
		if (param.getCheck_in_time_start() != null && param.getCheck_in_time_start().length() > 0) {
			whereSql.append(" and to_char(to_date(substr(a.CHECK_IN_TIME,1,14),'yyyymmddhh24miss'),'yyyy-mm-dd') >= to_char(to_date('" + param.getCheck_in_time_start()+ "','yyyy-mm-dd'),'yyyy-mm-dd')");
		}
		if (param.getCheck_in_time_end() != null && param.getCheck_in_time_end().length() > 0) {
			whereSql.append(" and to_char(to_date(substr(a.CHECK_IN_TIME,1,14),'yyyymmddhh24miss'),'yyyy-mm-dd') <= to_char(to_date('" + param.getCheck_in_time_end()+ "','yyyy-mm-dd'),'yyyy-mm-dd')");
		}
		if (param.getBrch_no() != null && param.getBrch_no().length() > 0) {
			brch_no=param.getBrch_no();

		}  
	 
		if("Y".equals(param.get_flag()))
		{
			whereSql.append(" and b.brch_no in (  select brch_no from t_sys_brch    start with  brch_no = '"+brch_no+"' connect by  prior brch_no=brch_up ) ");
		}
		else
		{
			whereSql.append(" and b.brch_no ='"+ brch_no+"'");
		}
		String sql ="select a.sn,a.login_name,a.login_id,a.check_in_lat,a.check_in_lng,decode(a.visit_type,'1','跨赛走访','2','转型外拓','3','邮务类走访')  visit_type,"
		+"a.visit_custom_name,a.visit_content,a.visit_note,to_char(to_date(substr(a.CHECK_IN_TIME,1,14),'yyyymmddhh24miss'),'yyyy-mm-dd hh24:mi:ss')  CHECK_IN_TIME,"
		+"F_PARAMS('MOBILE_VISIT_CLASS',a.visit_class)  VISIT_CLASS,img_id,f_pic_url(img_id) img_url "
		+"from A_WAP_CHECK_IN_INFO a left join t_sys_user b on a.login_id=b.user_id||''  where 1=1 "+whereSql+" order by a.CHECK_IN_TIME desc";

		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, Param.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	@SuppressWarnings("unchecked")
	public Map<String, Object> downloadfile(Param param) {
		Map<String,Object> map = new HashMap<String,Object>();
		String sql = "select img_path, old_img_name from A_IMG_CHECK_IN_INFO where sn="+param.getSn();
		System.out.println("sql----->"+sql);
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		try {
			list_map = db.query(sql);
			if(list_map.size()==1){
				map = list_map.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
