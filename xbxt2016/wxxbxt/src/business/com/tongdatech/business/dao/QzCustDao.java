
package com.tongdatech.business.dao;



import org.apache.log4j.Logger;



import com.tongdatech.business.bean.QzCustBean;

import com.tongdatech.sys.base.BaseDao;

import com.tongdatech.sys.bean.UserInfo;

import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;


/**
 * @author 
 * 
 */
public class QzCustDao extends BaseDao {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(QzCustDao.class);
	
	public PageUI qzCust(Pagination pagination, QzCustBean qzCustBean,UserInfo userInfo) throws Exception {
		
		StringBuffer whereSql = new  StringBuffer();
		if (qzCustBean.getAge_min() != null && qzCustBean.getAge_min().length() > 0) {
			whereSql.append(" and age >= " + qzCustBean.getAge_min());
		}
		if (qzCustBean.getAge_max() != null && qzCustBean.getAge_max().length() > 0) {
			whereSql.append(" and age <= " + qzCustBean.getAge_max());
		}
		
		if (qzCustBean.getDz() != null && qzCustBean.getDz().length() > 0) {
			whereSql.append(" and dz like '%" + qzCustBean.getDz()+"%'");
		}
		if (qzCustBean.getSex() != null && qzCustBean.getSex().length() > 0) {
			whereSql.append(" and sex = '" + qzCustBean.getSex()+"'");
		}
		String sql="Select substr(name,1,1)||'xx' name,age,sex,     REGEXP_REPLACE (dz,'[0-9]+','xx')  dz,sj,dh from ZOUFANG_JIANKU_ALL "+
			
				" where zichan_all_0930 is null  and new_office_code='"+userInfo.getBrch_no()+"'" +whereSql;
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, QzCustBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
}
