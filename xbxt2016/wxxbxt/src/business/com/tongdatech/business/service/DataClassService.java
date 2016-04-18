/**
 * File name:
 * Create author:
 * Create date:
 */
package com.tongdatech.business.service;


import com.tongdatech.business.bean.DataClassBean;
import com.tongdatech.business.dao.DataClassDao;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;





/**
 * @author 
 *
 */
public class DataClassService {
	DataClassDao dao =new DataClassDao();
	public PageUI loaddata1(Pagination pagination,
			DataClassBean bean) throws Exception {
		return dao.loaddata1(pagination,bean);
	}

	public int addchildclass(DataClassBean bean,UserInfo userInfo) throws Exception {
		return dao.addchildclass(bean,userInfo);
	}

	public int deletechildclass(DataClassBean bean) throws Exception {
		return dao.deletechildclass(bean);
	}

	public int editchildclass(DataClassBean bean) throws Exception {
		return dao.editchildclass(bean);
	}

	public PageUI loaddata2(Pagination pagination,
			DataClassBean bean) throws Exception {
		return dao.loaddata2(pagination,bean);
	}
	public int checkTableName(
			DataClassBean bean) throws Exception {
		return dao.checkTableName(bean);
	}
	
	public int checkColName(
			DataClassBean bean) throws Exception {
		return dao.checkColName(bean);
	}
	public int submit_classproperty_add(DataClassBean bean) throws Exception {
		return dao.submit_classproperty_add(bean);
	}

	public int deleteclassproperty(DataClassBean bean) throws Exception {
		return dao.deleteclassproperty(bean);
	}

	public int editclassproperty(DataClassBean bean) throws Exception {
		return dao.editclassproperty(bean);
	}
	
}
