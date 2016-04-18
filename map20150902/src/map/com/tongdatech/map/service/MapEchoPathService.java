package com.tongdatech.map.service;

import com.tongdatech.map.bean.MapEchoPathBean;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.dao.MapEchoPathDao;

public class MapEchoPathService {
	private MapEchoPathDao dao = new MapEchoPathDao();
	public MapReturnBean query(MapEchoPathBean bean) {
		return dao.query(bean);
	}
	public MapReturnBean seticons(MapEchoPathBean bean) throws Exception {
		return dao.seticons(bean);
	}
	public MapReturnBean setLineMarkers(MapEchoPathBean bean) throws Exception {
		return dao.setLineMarkers(bean);
	}
	public MapReturnBean setAreaMarkers(MapEchoPathBean bean) throws Exception {
		return dao.setAreaMarkers(bean);
	}

}
