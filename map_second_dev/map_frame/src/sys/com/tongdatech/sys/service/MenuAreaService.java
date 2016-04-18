package com.tongdatech.sys.service;

import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.dao.MenuAreaDao;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class MenuAreaService extends BaseService 
{
	MenuAreaDao easyUIMenuAreaDao=new MenuAreaDao();

	public PageUI query(Pagination pagination, String name,String no) throws Exception {
		// TODO Auto-generated method stub
		return easyUIMenuAreaDao.query(pagination,name,no);
	}
	
}
