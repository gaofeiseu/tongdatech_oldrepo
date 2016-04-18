package com.tongdatech.map.util;

public class TransformLatLng {
private static final double x_pi = 3.14159265358979323846264338327950288 * 3000.0 / 180.0;
	
	public static String Convert_GCJ02_To_BD09(double lat,double lng)
	{
		String s = "";
		double x = lng, y = lat;
		double z =Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		lng = z * Math.cos(theta) + 0.0065;
		lat = z * Math.sin(theta) + 0.006;
		s = lat+"#"+lng;
		return s;
	}
	
	public static String Convert_BD09_To_GCJ02(double lat, double lng)
	{
		String s = "";
		double x = lng - 0.0065, y = lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		lng = z * Math.cos(theta);
		lat = z * Math.sin(theta);
		s = lat+"#"+lng;
		return s;
	}
	public static void main(String[] args) {
		System.out.println(Convert_BD09_To_GCJ02(32.0572355,118.77807441));
		System.out.println(Convert_GCJ02_To_BD09(31.85726417374392,120.33973115723677));
		
	}
}
