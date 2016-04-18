package com.gaofei.bean;

import java.util.List;

public class TilesBean extends BaseBean{
	public TilesBean(String save_path, List<EnumTilesType> list_map_type, int st_zoom, int ed_zoom, double st_lon,
			double ed_lon, double st_lat, double ed_lat) {
		super(save_path, list_map_type, st_zoom, ed_zoom, st_lon, ed_lon, st_lat, ed_lat);
	}
}