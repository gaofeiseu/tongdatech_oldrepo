/**
 * File name:
 * Create author:
 * Create date:
 */
package com.tongdatech.business.service;



import com.tongdatech.business.bean.QzCustBean;

import com.tongdatech.business.dao.QzCustDao;



import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;



/**
 * @author 
 *
 */
public class QzCustService {
	QzCustDao qzCustDao =new QzCustDao();

	
	public PageUI qzCust(Pagination pagination, QzCustBean qzCustBean,UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		
		return qzCustDao.qzCust(pagination,qzCustBean, userInfo);
	}
	
}
