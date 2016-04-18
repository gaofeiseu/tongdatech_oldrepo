package com.tongdatech.sys.demo.dao;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.demo.bean.DemoObj;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
import com.tongdatech.sys.util.SQLUtil;

public class PaginationDao extends BaseDao {

	public PageUI query(Pagination pagination, DemoObj demoObj)
			throws Exception {

		String sql = "select sn," + demoObj.getColint1() + " colint1," + ""
				+ demoObj.getColint2() + " colint2," + "'"
				+ demoObj.getColstr1() + "' colstr1," + "'"
				+ demoObj.getColstr2() + "' colstr2," + ""
				+ SQLUtil.dateSql(demoObj.getColdate1(), "yyyy-MM-dd")
				+ "coldate1," + ""
				+ SQLUtil.dateSql(demoObj.getColdate2(), "yyyy-MM-dd HH:mm:ss")
				+ "coldate2 " + " from t_sys_demo ";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());

		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, DemoObj.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}


}
