package com.tongdatech.sys.service;

import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.dao.MenuRoleDao;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class MenuRoleService extends BaseService 
{
	MenuRoleDao easyUIMenuRoleDao=new MenuRoleDao();

	public PageUI query(Pagination pagination) throws Exception {
		// TODO Auto-generated method stub
		return easyUIMenuRoleDao.query(pagination);
	}
	
}
