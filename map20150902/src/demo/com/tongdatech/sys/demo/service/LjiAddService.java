/**
 * File name:ParamService.java
 * Create author:XY
 * Create date:2014-3-18
 */
package com.tongdatech.sys.demo.service;

import java.util.Map;

import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
import com.tongdatech.sys.demo.dao.LjiAddDao;
import com.tongdatech.sys.demo.bean.LjiAddObj;

/**
 * @author XY
 *
 */
public class LjiAddService {
	private LjiAddDao ljiDao = new LjiAddDao();
	public PageUI query(Pagination pagination, LjiAddObj ljiAddObj) throws Exception {
		// TODO Auto-generated method stub
		
		return ljiDao.query(pagination,ljiAddObj);
	}
	
	public AjaxMsg deleteParam(String sns) throws Exception{

		return ljiDao.deleteParam(sns);
	}
	
	public AjaxMsg addParam(LjiAddObj ljiAddObj) throws Exception{

		return ljiDao.addParam(ljiAddObj);
	}
	public AjaxMsg editParam(LjiAddObj ljiAddObj) throws Exception{

		return ljiDao.editParam(ljiAddObj);
	}
	public  Map<?, ?> load() throws Exception{

		return ljiDao.load();
	}

}
