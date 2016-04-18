package com.tongdatech.map_mobile.utils;

import com.tongdatech.map_mobile.bean.Location;

public class WgsToMar {
	public static double pi = 3.14159265358979324;
	private static final double x_pi = 3.14159265358979323846264338327950288 * 3000.0 / 180.0;
	//
	// Krasovsky 1940
	//
	// a = 6378245.0, 1/f = 298.3
	// b = a * (1 - f)
	// ee = (a^2 - b^2) / a^2;
	public static double a = 6378245.0;
	public static double ee = 0.00669342162296594323;

	// World Geodetic System ==> Mars Geodetic System
	public static Location transform(Location location, String if_wap)

	{
		Location rs = new Location();
		double wgLat = location.getLatitude();
		double wgLon = location.getLongitude();
		System.out.println("before++++++++++" + wgLat + "---" + wgLon);
		double mgLat = 0;
		double mgLon = 0;
		if ("1".equals(if_wap)) {
			//wap 百度定位
			double x = wgLon - 0.0065, y = wgLat - 0.006;
			double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
			double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
			mgLon = z * Math.cos(theta);
			mgLat = z * Math.sin(theta);
			System.out.println("after++++++++++" + mgLat + "---" + mgLon);
			rs.setLatitude(mgLat);
			rs.setLongitude(mgLon);
		}
		else if("2".equals(if_wap)){
			//wap html5定位
			if (outOfChina(wgLat, wgLon)) {
				mgLat = wgLat;
				mgLon = wgLon;
				return null;
			}
			double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
			double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
			double radLat = wgLat / 180.0 * pi;
			double magic = Math.sin(radLat);
			magic = 1 - ee * magic * magic;
			double sqrtMagic = Math.sqrt(magic);
			dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
			dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
			mgLat = wgLat + dLat;
			mgLon = wgLon + dLon;
			System.out.println("after++++++++++" + mgLat + "---" + mgLon);
			rs.setLatitude(mgLat);
			rs.setLongitude(mgLon);
		}
		else if("3".equals(if_wap)){
			//android百度定位
			rs.setLatitude(wgLat);
			rs.setLongitude(wgLon);
		}
		else if("4".equals(if_wap)){
			//GPS 需要转换
			if (outOfChina(wgLat, wgLon)) {
				mgLat = wgLat;
				mgLon = wgLon;
				return null;
			}
			double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
			double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
			double radLat = wgLat / 180.0 * pi;
			double magic = Math.sin(radLat);
			magic = 1 - ee * magic * magic;
			double sqrtMagic = Math.sqrt(magic);
			dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
			dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
			mgLat = wgLat + dLat;
			mgLon = wgLon + dLon;
			System.out.println("after++++++++++" + mgLat + "---" + mgLon);
			rs.setLatitude(mgLat);
			rs.setLongitude(mgLon);
		}
		else if("5".equals(if_wap)){
			//网络定位  需要转换
			if (outOfChina(wgLat, wgLon)) {
				mgLat = wgLat;
				mgLon = wgLon;
				return null;
			}
			double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
			double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
			double radLat = wgLat / 180.0 * pi;
			double magic = Math.sin(radLat);
			magic = 1 - ee * magic * magic;
			double sqrtMagic = Math.sqrt(magic);
			dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
			dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
			mgLat = wgLat + dLat;
			mgLon = wgLon + dLon;
			System.out.println("after++++++++++" + mgLat + "---" + mgLon);
			rs.setLatitude(mgLat);
			rs.setLongitude(mgLon);
		}
		else {
			//android其他需要转换的定位//应该不会出现了
			if (outOfChina(wgLat, wgLon)) {
				mgLat = wgLat;
				mgLon = wgLon;
				return null;
			}
			double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
			double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
			double radLat = wgLat / 180.0 * pi;
			double magic = Math.sin(radLat);
			magic = 1 - ee * magic * magic;
			double sqrtMagic = Math.sqrt(magic);
			dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
			dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
			mgLat = wgLat + dLat;
			mgLon = wgLon + dLon;
			System.out.println("after++++++++++" + mgLat + "---" + mgLon);
			rs.setLatitude(mgLat);
			rs.setLongitude(mgLon);
		}
		return rs;
	}

	static boolean outOfChina(double lat, double lon) {
		if (lon < 72.004 || lon > 137.8347)
			return true;
		if (lat < 0.8293 || lat > 55.8271)
			return true;
		return false;
	}

	static double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
				+ 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	static double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
				* Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
				* pi)) * 2.0 / 3.0;
		return ret;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
