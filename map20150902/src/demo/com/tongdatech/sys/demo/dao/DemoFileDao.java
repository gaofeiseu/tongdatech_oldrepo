package com.tongdatech.sys.demo.dao;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.pojo.JspMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class DemoFileDao extends BaseDao {

	public JspMsg save(String file_path, String file_name, String file_type)
			throws Exception {
		JspMsg rs = new JspMsg();
		String sql = "INSERT INTO t_sys_demo_file(file_path, file_name, file_type)"
				+ "VALUES ('"
				+ file_path
				+ "','"
				+ file_name
				+ "','"
				+ file_type + "')";
		int ret = db.insert(sql);
		if (ret == 0) {
			rs.setMsg("´æ´¢´íÎó");
		} else {
			rs.setSuccess(true);
		}
		return rs;
	}

	public PageUI list(Pagination pagination) throws Exception {
		String sql = "select * from t_sys_demo_file";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());

		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

}
