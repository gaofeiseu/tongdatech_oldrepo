package com.tongdatech.business.service;

 
import java.util.List;
import java.util.Map;

 
import com.tongdatech.business.bean.DataBean;
import com.tongdatech.business.bean.ReturnBean;
import com.tongdatech.business.dao.DataManageDao;


import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
 

public class DataManageService extends BaseService {
 
	DataManageDao dataManageDao=new DataManageDao();
	
 
	public List<Map> getSNINFO(UserInfo userinfo) throws Exception
	{
		return dataManageDao.getSNINFO(userinfo);
	}
	public PageUI doquery(Pagination pagination,DataBean bean) throws Exception
	{
		return dataManageDao.doquery(pagination,bean);
	}
	public List<Map> queryColumn(DataBean bean) throws Exception
	{
		return dataManageDao.queryColumn(bean);
	}
	public ReturnBean doSave(DataBean bean,String filename) {
		ReturnBean returnBean = new ReturnBean();
		returnBean = dataManageDao.doSave(bean,filename);
		return returnBean;
	}
	public List<Map> toQueryColumn(DataBean bean) {
		 
		List<Map> resultMap = dataManageDao.toQueryColumn(bean);
		return resultMap;
	}

}
