package com.tongdatech.map.download;

import java.util.ArrayList;
import java.util.List;
/*
 * @author gaofei
 * 地图碎片参数计算集成类
 */
public class TileFactory {
	public static double tile2long(double x,double z){
		return (x/Math.pow(2, z)*360-180);
	}
	public static double tile2lat(double y,double z){
		double n = Math.PI - 2*Math.PI*y/Math.pow(2, z);
		return (180/Math.PI*Math.atan(0.5*(Math.exp(n)-Math.exp(-n))));
	}
	public static double long2tileX(double lon,int zoom1){
		return (Math.floor((lon+180)/360*Math.pow(2, zoom1)));
	}
	public static double lat2tileY(double lat,int zoom2){
		return ((1 << zoom2)
				- (Math.floor((1 - Math.log(Math.tan(lat * Math.PI / 180) + 1
						/ Math.cos(lat * Math.PI / 180))
						/ Math.PI)
						/ 2 * Math.pow(2, zoom2))) - 1);
	}
	public static double long2tile(double lon,int zoom1){
		return (Math.floor((lon + 180) / 360 * Math.pow(2, zoom1)));
	}
	public static double lat2tile(double lat,int zoom2){
		return (Math.floor((1 - Math.log(Math.tan(lat * Math.PI / 180) + 1/ Math.cos(lat * Math.PI / 180))/ Math.PI)/ 2 * Math.pow(2, zoom2)));
	}
	public static List<Double> totilexy(double vlong,double vlat,int zoom){
		List<Double> double_list = new ArrayList<Double>();
		double x = long2tileX(vlong, zoom);
		double y = lat2tileY(vlat, zoom);
		double_list.add(x);
		double_list.add(y);
		return double_list;
	}
	public static void tolonlat(double xlong,double ylat,int zoom){
		@SuppressWarnings("unused")
		double x = tile2long(xlong, zoom);
		@SuppressWarnings("unused")
		double y = tile2lat(ylat, zoom);
	}
	public static List<Integer> toxy(double vlong,double vlat,int zoom){
		List<Integer> double_list = new ArrayList<Integer>();
		double x = long2tile(vlong, zoom);
		double y = lat2tile(vlat, zoom);
		double_list.add(Integer.parseInt(new java.text.DecimalFormat("0").format(x)));
		double_list.add(Integer.parseInt(new java.text.DecimalFormat("0").format(y)));
		return double_list;
	}
	/**
	 * 需要参数：初始经度，初始纬度，结束经度，结束纬度，图层参数
	 * 返回的参数：List<Integer> list 其中list从0-5以此为x1,y1,x4,y4,zoom
	 * @param long1
	 * @param lat1
	 * @param long2
	 * @param lat2
	 * @param zoom
	 * @return
	 */
	public static List<Integer> getAllXtileYtile(double long1,double lat1,double long2,double lat2,int zoom){
		List<Integer> list1 = new ArrayList<Integer>();
		list1 = toxy(long1, lat1, zoom);
		int x1 = list1.get(0);
		int y1 = list1.get(1);
		List<Integer> list2 = new ArrayList<Integer>();
		list2 = toxy(long2, lat2, zoom);
		int x4 = list2.get(0);
		int y4 = list2.get(1);
		List<Integer> resultList = new ArrayList<Integer>();
		resultList.add(x1);//起始X
		resultList.add(y1);//起始Y
		resultList.add(x4);//结束X
		resultList.add(y4);//结束Y
		resultList.add(zoom);
		return resultList;
	}
	public static void main(String[] args) {
//		double vlong = 118.817297;
//		double vlat = 31.886887;
//		int zoom = 14;
//		List<Integer> double_list = toxy(vlong, vlat, zoom);
//		int xtile = double_list.get(0);
//		int ytile = double_list.get(1);
//		System.out.println("xtile:"+xtile);
//		System.out.println("ytile:"+ytile);
		//13599+6659---z14---------yes
		//27199+13318---z15--------yes
		//54398+26637---z16--------yes
		
		
		double lng_st = 108.268616;
		double lat_st = 34.948991;
		double lng_ed = 110.663635;
		double lat_ed = 33.541395;
		int zoom = 7;
		List<Integer> list = new ArrayList<Integer>();
		list = getAllXtileYtile(lng_st,lat_st,lng_ed,lat_ed,zoom);
		System.out.println("起始X："+list.get(0));
		System.out.println("起始Y："+list.get(1));
		System.out.println("结束X："+list.get(2));
		System.out.println("结束Y："+list.get(3));
	}
}
