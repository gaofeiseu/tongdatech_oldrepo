package com.gaofei.util;

public class Params {
	public static double per_lon = 0.1;
	public static double per_lat = 0.1;
	public static int start_zoom = 1;
	public static int start_split_zoom = 15;
	public static String mapabc_folder = "mapabc";
	public static String mapSa_folder = "mapSa";
	public static String mapSaDL_folder = "mapSaDL";
	public static int per_second = 5;
	public static int last_num = 0;
	public static int mapsa_num = 180;
	static{
		try{
			mapsa_num = Integer.parseInt(PropertyUtils.get("mapsa_num"));
		}
		catch(Exception ex){
			
		}
	}
}
