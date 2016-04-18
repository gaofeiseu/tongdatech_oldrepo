package com.tongdatech.sys.service;

import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.bean.Brch;
import com.tongdatech.sys.dao.BrchDao;
import com.tongdatech.sys.pojo.AjaxMsg;

public class BrchService extends BaseService{

	private BrchDao brchDao = new BrchDao();

	public Brch queryOne(String brch_no) throws Exception{
		
		return brchDao.queryOne(brch_no);
		
	}
	
	public AjaxMsg brchSave(Brch brch) throws Exception{
		return brchDao.brchSave(brch);
	}
	
	public AjaxMsg idCheck(Brch brch) throws Exception{
		return brchDao.idCheck(brch);
	}
	
	public AjaxMsg isChild(Brch brch) throws Exception{
		return brchDao.isChild(brch);
	}

	public AjaxMsg brchEdit(Brch brch) throws Exception{
		return brchDao.brchEdit(brch);
	}
}
