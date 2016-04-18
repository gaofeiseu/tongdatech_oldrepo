package com.tongdatech.sys.dao;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;


import java.util.*;

public class MenuRoleDao extends BaseDao
{
	private PageUI pageui;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageUI query(Pagination pagination)
			throws Exception 
	{
	
		StringBuffer sb= new StringBuffer();
		sb.append("with module as" +
				"(select A.*,rownum r from(select menu_name,menu_id,sys_connect_by_path(menu_name,'>') menu_name_path "+ 
				"from T_SYS_MENU "+
				"START WITH MENU_ID IN (SELECT MENU_ID FROM T_SYS_MENU WHERE MENU_PARENT=0) "+
				"connect by prior MENU_ID=MENU_PARENT " +
				"order siblings by order_id) "+ 
				" A) "+
				"select B.MENU_NAME 菜单, "+
				"D.menu_name_path 菜单层级路径,");
		String sql1="select role_name,role_id from t_sys_role order by role_id";
		List<Map> list = db.query(sql1);
		
		for(Map mp:list){
			sb.append("DECODE(INSTR(WM_CONCAT(C.ROLE_ID),"+mp.get("role_id")+"),0,'×','√') "+mp.get("role_name")+",");
		}
		
		while (sb.charAt(sb.length() - 1) == ' ' || sb.charAt(sb.length() - 1) == '\t') 
		{
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.deleteCharAt(sb.length() - 1);
		
		sb.append(" from T_SYS_ROLE A,T_SYS_MENU B,T_SYS_R_MENU_R C,module D "+
				"where C.ROLE_ID = A.ROLE_ID and C.MENU_ID = B.MENU_ID and C.MENU_ID=D.MENU_ID " +
				"group by B.MENU_NAME,D.menu_name_path,D.r "+
				"order by D.r");
		String sql=sb.toString();
		
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setColInfo(list);
		rs.setRows(db.query(listsql));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	
	}
	public PageUI getPageui() {
		return pageui;
	}
	public void setPageui(PageUI pageui) {
		this.pageui = pageui;
	}
}