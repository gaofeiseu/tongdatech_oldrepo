package com.tongdatech.business.service;

import java.io.File;

import com.tongdatech.business.bean.DataInputBean;
import com.tongdatech.business.bean.ReturnBean;
import com.tongdatech.business.dao.DataInputDao;


public class DataInputService {
	DataInputDao dao = new DataInputDao();

	public ReturnBean getChildClassCombo(DataInputBean bean) {
		return dao.getChildClassCombo(bean);
	}

	public ReturnBean getTemplat(DataInputBean bean) {
		return dao.getTemplat(bean);
	}

	public ReturnBean uploadTemplat(File[] upload,DataInputBean bean) {
		return dao.uploadTemplat(upload,bean);
	}
	
}
