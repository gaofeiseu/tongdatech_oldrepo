package com.tongdatech.map_tile.service;

import java.util.Map;

import com.tongdatech.map_tile.bean.MapTileBean;
import com.tongdatech.map_tile.bean.MapTileReturnBean;
import com.tongdatech.map_tile.dao.MapTileDao;

public class MapTileService {
	MapTileDao dao = new MapTileDao();
	public MapTileReturnBean doop(MapTileBean bean) {
		return dao.doop(bean);
	}
	public MapTileReturnBean foop(MapTileBean bean) {
		return dao.foop(bean);
	}
	public Map<String,Object> doo(MapTileBean bean) {
		return dao.doo(bean);
	}

}
