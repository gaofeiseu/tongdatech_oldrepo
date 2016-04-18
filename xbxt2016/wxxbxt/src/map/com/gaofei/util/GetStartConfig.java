package com.gaofei.util;

import java.util.ArrayList;
import java.util.List;

import com.gaofei.bean.EnumTilesType;
import com.gaofei.bean.BaseBean;

public class GetStartConfig {
	public static BaseBean getStartConfigBean(){
		String save_path = "F:/map1234";
		List<EnumTilesType> list_map_type = new ArrayList<EnumTilesType>();
		list_map_type.add(EnumTilesType.MAPABC);
		list_map_type.add(EnumTilesType.MAPSA);
		list_map_type.add(EnumTilesType.MAPSADL);
		int st_zoom = Params.start_zoom;
		int ed_zoom = 15;
		double st_lon = 118.417774;
		double st_lat = 32.391383;
		double ed_lon = 119.243122;
		double ed_lat = 31.565489;
		return new BaseBean(save_path, list_map_type, st_zoom, ed_zoom, st_lon, ed_lon, st_lat, ed_lat);
	}
}
