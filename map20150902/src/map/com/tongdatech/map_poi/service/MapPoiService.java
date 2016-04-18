package com.tongdatech.map_poi.service;

import com.tongdatech.map_poi.bean.MapPoiBean;
import com.tongdatech.map_poi.bean.MapPoiReturnBean;
import com.tongdatech.map_poi.dao.MapPoiDao;
import com.tongdatech.sys.pojo.AjaxMsg;

public class MapPoiService {
	MapPoiDao dao = new MapPoiDao();
	public MapPoiReturnBean getData(MapPoiBean bean) {
		return dao.getData(bean);
	}
	public AjaxMsg query(MapPoiBean bean) {
		return dao.query(bean);
	}
	public AjaxMsg runsql(MapPoiBean bean) {
		return dao.runsql(bean);
	}

}
