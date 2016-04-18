package com.tongdatech.sys.demo.service;

import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.demo.bean.DemoObj;
import com.tongdatech.sys.demo.dao.PaginationDao;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class PaginationService extends BaseService {
	PaginationDao easyUIPaginationDao=new PaginationDao();

	public PageUI query(Pagination pagination, DemoObj demoObj) throws Exception {
		// TODO Auto-generated method stub
		return easyUIPaginationDao.query(pagination,demoObj);
	}
	
}
