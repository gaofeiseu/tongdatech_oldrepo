package com.gaofei.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
	/**
	 * 根据起始经纬度和结束经纬度以及Zoom计算出瓦片参数：x1,y1,x4,y4
	 * @param st_lon	起始经度
	 * @param st_lat	起始纬度
	 * @param ed_lon	结束经度
	 * @param ed_lat	结束纬度
	 * @param zoom	zoom
	 * @return
	 */
	public static Map<String,Integer> getXtileYtile(double st_lon,double st_lat,double ed_lon,double ed_lat,int zoom){
		Map<String,Integer> m = new HashMap<String,Integer>();
        m = toxy(st_lon, st_lat, zoom);
        int x1 = m.get("x");
        int y1 = m.get("y");
        Map<String,Integer> m2 = new HashMap<String,Integer>();
        m2 = toxy(ed_lon, ed_lat, zoom);
        int x4 = m2.get("x");
        int y4 = m2.get("y");
        Map<String,Integer> m3 = new HashMap<String,Integer>();
        m3.put("x1", x1);
        m3.put("y1", y1);
        m3.put("x4", x4);
        m3.put("y4", y4);
        m3.put("zoom", zoom);
        return m3;
	}
	private static Map<String,Integer> toxy(double vlong,double vlat,int zoom){
		Map<String,Integer> m = new HashMap<String,Integer>();
		double x = long2tile(vlong, zoom);
        double y = lat2tile(vlat, zoom);
        int x_int = (new BigDecimal(Math.floor(x)).setScale(0, BigDecimal.ROUND_HALF_EVEN)).intValue();
        int y_int = (new BigDecimal(Math.floor(y)).setScale(0, BigDecimal.ROUND_HALF_EVEN)).intValue();
        m.put("x", x_int);
        m.put("y", y_int);
        return m;
	}
	private static double long2tile(double vlong,int zoom){
		vlong += 180;
		double longTileSize = 360.0 / (Math.pow(2, zoom));
        double tilex = vlong / longTileSize;
        return tilex;
	}
	private static double lat2tile(double vlat,int zoom){
		double maxlat = Math.PI;
        double lat = vlat;
        if (lat > 90)
        {
            lat = lat - 180;
        }
        if (lat < -90)
        {
            lat = lat + 180;
        }
        double phi = Math.PI * lat / 180;
        double res;
        res = 0.5 * Math.log((1 + Math.sin(phi)) / (1 - Math.sin(phi)));
        double maxTileY = Math.pow(2, zoom);
        int result = (int)(((1 - res / maxlat) / 2) * (maxTileY));
        return (result);
	}
}
