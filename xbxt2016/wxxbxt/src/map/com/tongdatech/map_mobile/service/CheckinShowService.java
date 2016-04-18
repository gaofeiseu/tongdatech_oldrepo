package com.tongdatech.map_mobile.service;

import com.tongdatech.map_mobile.bean.CheckinShowBean;
import com.tongdatech.map_mobile.bean.MobileReturnBean;
import com.tongdatech.map_mobile.dao.CheckinShowDao;

public class CheckinShowService {
	CheckinShowDao dao = new CheckinShowDao();

	public MobileReturnBean query(CheckinShowBean bean) {
		return dao.query(bean);
	}

	public MobileReturnBean getTableWithAjax(CheckinShowBean bean) throws Exception {
		return dao.getTableWithAjax(bean);
	}
	
	
	
}
