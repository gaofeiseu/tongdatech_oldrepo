/**
 * File name:ParamService.java
 * Create author:XY
 * Create date:2014-3-18
 */
package com.tongdatech.sys.service;

import java.util.List;
import java.util.Map;

import com.tongdatech.sys.bean.Param;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.dao.ParamDao;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

/**
 * @author XY
 *
 */
public class ParamService {
	private ParamDao paramDao = new ParamDao();
	public PageUI query(Pagination pagination, Param param) throws Exception {
		// TODO Auto-generated method stub
		
		return paramDao.query(pagination,param);
	}
	
	public AjaxMsg deleteParam(String sns) throws Exception{

		return paramDao.deleteParam(sns);
	}
	
	public AjaxMsg addParam(Param param) throws Exception{

		return paramDao.addParam(param);
	}
	public AjaxMsg editParam(Param param) throws Exception{

		return paramDao.editParam(param);
	}
	
	public List<Map> getUserType(UserInfo userInfo) throws Exception{

		return paramDao.getUserType(userInfo);
	}
	public  Map<?, ?> load() throws Exception{

		return paramDao.load();
	}

}
