/**
 * File:ServiceConfig.java
 * Author:ydh
 * Date:2013-5-23
 */
package com.tongdatech.map.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Web Service 访问地址的配置信息
 */
public class ServiceConfig {
	
	private static Properties config = new Properties();
	
	static {
		try {
			config.load(ServiceConfig.class.getClassLoader().getResourceAsStream("service.properties"));
		} catch (IOException e) {
			System.err.println("读取配置文件service.properties出错，请确认其是否存在并且格式正确。");
			e.printStackTrace();
		}
	}
	
	public static String get(String key){
		return config.getProperty(key);
	}	

}
