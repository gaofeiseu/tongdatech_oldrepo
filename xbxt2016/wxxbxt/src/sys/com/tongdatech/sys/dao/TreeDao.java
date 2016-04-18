package com.tongdatech.sys.dao;

import java.util.List;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.Brch;

public class TreeDao extends BaseDao{

	public Brch getBrchRoot(String node) throws Exception {
		String sql = "select * from t_sys_brch where brch_no="+node;
		return (Brch) db.queryOneLine(sql, Brch.class);
	}

	@SuppressWarnings("unchecked")
	public List<Brch> getBrchChildren(String node, String mode) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if("all".equals(mode)){
		}else{
			whereSql.append(" and brch_flag='1'");
		}
		String sql = "select * from t_sys_brch where brch_up="+node+whereSql+" order by order_id";
		return db.query(sql, Brch.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Brch> getParents(String node) throws Exception {
		String sql = "select * from t_sys_brch start with brch_no="+node+" connect by prior brch_up=brch_no";
		return db.query(sql,Brch.class);
	}

}
