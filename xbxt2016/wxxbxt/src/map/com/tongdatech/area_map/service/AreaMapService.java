package com.tongdatech.area_map.service;

import com.tongdatech.area_map.bean.AreaMapBean;
import com.tongdatech.area_map.bean.AreaMapReturnBean;
import com.tongdatech.area_map.dao.AreaMapDao;

public class AreaMapService {
	AreaMapDao dao = new AreaMapDao();

	public AreaMapReturnBean insertCLOB(AreaMapBean bean) {
		return dao.insertCLOB(bean);
	}

	public AreaMapReturnBean selectCLOB(AreaMapBean bean) {
		return dao.selectCLOB(bean);
	}
	
}
