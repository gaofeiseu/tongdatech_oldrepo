/**
 * File name:
 * Create author:
 * Create date:
 */
package com.tongdatech.business.service;

import java.io.File;
import java.util.Map;

import com.tongdatech.business.bean.Param;
import com.tongdatech.business.dao.VisitDao;

import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;



/**
 * @author 
 *
 */
public class VisitService {
	VisitDao visitDao =new VisitDao();
	public PageUI query(Pagination pagination, Param param,UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		
		return visitDao.query(pagination,param, userInfo);
	}
	
	public File getFile(String name) {
		try{
			File file=new File(name);
			return file;
		}catch (Exception e) {
			return null;
		}
		
	}

	public Map<String, Object> downloadfile(Param param) {
		return visitDao.downloadfile(param);
	}
}
