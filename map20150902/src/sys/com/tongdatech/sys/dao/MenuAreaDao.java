package com.tongdatech.sys.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class MenuAreaDao extends BaseDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageUI query(Pagination pagination, String name,String no)
			throws Exception {
		String sql1="select area_no,area_name from t_sys_area where area_parent="+no;
		String sql2="select area_no,area_name from t_sys_area where area_no="+no;
		List<Map> list =db.query(sql1);
		List<Map> list2=db.query(sql2);
		List<Map> li=new ArrayList<Map>();
		for(Map mp:list2)li.add(mp);
		for(Map mp:list)li.add(mp);
		StringBuffer sb=new StringBuffer();
		sb.append("with module as( "+
				"select a.*,rownum rr from( "+
				"select menu_name,menu_id,sys_connect_by_path(menu_name,'>') menu_path from t_sys_menu "+
				"start with menu_id in (select menu_id from t_sys_menu where menu_parent =0) "+
				"connect by prior menu_id=menu_parent "+
				"order siblings by order_id) a) "+
				"select a.menu_name,a.menu_path,");
		
		for(Map mp:li)
		{
			sb.append("DECODE(INSTR(WM_CONCAT(b.area_no),"+mp.get("area_no")+"),0,'¡Á','¡Ì') "+mp.get("area_name")+",");
		}
		
		while (sb.charAt(sb.length() - 1) == ' ' || sb.charAt(sb.length() - 1) == '\t') 
		{
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.deleteCharAt(sb.length() - 1);
		
		sb.append(" from module a,t_sys_area b,t_sys_r_menu_a c "+
				"where c.menu_id=a.menu_id and c.area_no=b.area_no "+
				"group by a.menu_name,a.menu_path,a.rr "+
				"order by a.rr");
		String sql=sb.toString();
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());	

		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql));
		rs.setColInfo(li);
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}


}
