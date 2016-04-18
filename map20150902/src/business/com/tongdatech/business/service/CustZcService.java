/**
 * File name:
 * Create author:
 * Create date:
 */
package com.tongdatech.business.service;


import java.util.List;
import java.util.Map;

import com.tongdatech.business.bean.CustZcBean;
import com.tongdatech.business.dao.CustZcDao;


import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;



/**
 * @author 
 *
 */
public class CustZcService {
	CustZcDao custZcDao =new CustZcDao();
	public PageUI query(Pagination pagination, CustZcBean custZcBean,UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		
		return custZcDao.query(pagination,custZcBean, userInfo);
	}
	public List<Map> queryManager(UserInfo userInfo)throws Exception
	{
		return custZcDao.queryManager(userInfo);
	}
	public int save( CustZcBean custZcBean,UserInfo userInfo) throws Exception{
		
		return custZcDao.save(custZcBean,userInfo);
		
	}
	public PageUI queryDingqi(Pagination pagination,String cust_p_id)throws Exception{
		return custZcDao.queryDingqi(pagination,cust_p_id);
	}
	public PageUI queryYibentong(Pagination pagination,String cust_p_id)throws Exception{
		return custZcDao.queryYibentong(pagination,cust_p_id);
	}
	public PageUI queryHuoqi(Pagination pagination,String cust_p_id)throws Exception{
		return custZcDao.queryHuoqi(pagination,cust_p_id);
	}
	public PageUI queryBaoXian(Pagination pagination,String cust_p_id)throws Exception{
		return custZcDao.queryBaoXian(pagination,cust_p_id);
	}
	public PageUI queryLicai(Pagination pagination,String cust_p_id)throws Exception{
		return custZcDao.queryLicai(pagination,cust_p_id);
	}
	public PageUI queryCxguozhai(Pagination pagination,String cust_p_id)throws Exception{
		return custZcDao.queryCxguozhai(pagination,cust_p_id);
	}
	public PageUI queryPzguozhai(Pagination pagination,String cust_p_id)throws Exception{
		return custZcDao.queryPzguozhai(pagination,cust_p_id);
	}
 
	
}
