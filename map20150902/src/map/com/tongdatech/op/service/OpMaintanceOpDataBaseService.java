package com.tongdatech.op.service;

import com.tongdatech.op.bean.OpDataGridBean;
import com.tongdatech.op.bean.OpMaintanceOpDataBaseBean;
import com.tongdatech.op.bean.OpReturnBean;
import com.tongdatech.op.dao.OpMaintanceOpDataBaseDao;

public class OpMaintanceOpDataBaseService {

	private OpMaintanceOpDataBaseDao dao = new OpMaintanceOpDataBaseDao();

	public OpDataGridBean query(OpMaintanceOpDataBaseBean bean) throws Exception {
		return dao.query(bean);
	}

	public OpReturnBean dodelete(OpMaintanceOpDataBaseBean bean) {
		return dao.dodelete(bean);
	}
	
}
