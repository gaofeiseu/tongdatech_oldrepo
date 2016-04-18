package com.tongdatech.business.service;

import com.tongdatech.business.bean.RemindBean;
import com.tongdatech.business.bean.RemindReturnBean;
import com.tongdatech.business.dao.RemindDao;
import com.tongdatech.sys.pojo.Pagination;

public class RemindService {
	public RemindDao dao = new RemindDao();
	public RemindReturnBean getNum(RemindBean bean) throws Exception {
		return dao.getNum(bean);
	}
	public RemindReturnBean getDetail(Pagination pagination, RemindBean bean) {
		return dao.getDetail(pagination,bean);
	}

}
