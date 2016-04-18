package com.tongdatech.sys.service;

import java.util.List;

import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.dao.SearchDao;

public class SearchService extends BaseService{

	private SearchDao searchDao = new SearchDao();
	public List<?> brch(String q, UserInfo userInfo) throws Exception {
		return searchDao.brch(q,userInfo);
	}
	public List<?> menu(String q, UserInfo userInfo) throws Exception {
		return searchDao.menu(q,userInfo);
	}
	
	public List<?> area(String q, UserInfo userinfo) throws Exception{
		return searchDao.area(q, userinfo);
	}
	
	

		
}
