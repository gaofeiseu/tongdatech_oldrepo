package com.gaofei.util;

public class Params {
	public static double per_lon = 0.1;
	public static double per_lat = 0.1;
	public static int start_zoom = 1;//start download maptile level
	public static int start_split_zoom = 15;//end download maptile level
	public static String mapabc_folder = "mapabc";
	public static String mapSa_folder = "mapSa";
	public static String mapSaDL_folder = "mapSaDL";
	public static int per_second = 5;
	public static int last_num = 0;
	public static int mapsa_num = 180;//the param use in satellite's maptile,like '156' in http://mt1.google.cn/vt/lyrs=s@156&hl=zh-CN&gl=CN&src=app&rlbl=1&x="+x+"&y="+y+"&z="+z+"&s=Galileo
	static{
		try{
			mapsa_num = Integer.parseInt(PropertyUtils.get("mapsa_num"));
		}
		catch(Exception ex){
			
		}
	}
}
