package com.tongdatech.op.service;

import com.tongdatech.op.bean.OpMaintanceOpJsBean;
import com.tongdatech.op.bean.OpReturnBean;
import com.tongdatech.op.dao.OpMaintanceOpJsDao;

public class OpMaintanceOpJsService {
	private OpMaintanceOpJsDao dao = new OpMaintanceOpJsDao();

	public OpReturnBean dosubmit(OpMaintanceOpJsBean bean) {
		return dao.dosubmit(bean);
	}
}
