package com.tongdatech.echarts_front.service;

import java.util.List;
import java.util.Map;

import com.tongdatech.echarts_front.bean.EchartsDemoBean;
import com.tongdatech.echarts_front.bean.ReturnBean;
import com.tongdatech.echarts_front.dao.EchartsDemoDao;

public class EchartsDemoService {
	private EchartsDemoDao dao = new EchartsDemoDao();

	public ReturnBean loaddata(EchartsDemoBean bean) {
		return dao.loaddata(bean);
	}
	
	public List<Map> zhanbi() throws Exception {
		return dao.zhanbi();
	}
}
