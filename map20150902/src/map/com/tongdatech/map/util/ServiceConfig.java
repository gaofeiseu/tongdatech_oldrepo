/**
 * File:ServiceConfig.java
 * Author:ydh
 * Date:2013-5-23
 */
package com.tongdatech.map.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Web Service ���ʵ�ַ��������Ϣ
 */
public class ServiceConfig {
	
	private static Properties config = new Properties();
	
	static {
		try {
			config.load(ServiceConfig.class.getClassLoader().getResourceAsStream("service.properties"));
		} catch (IOException e) {
			System.err.println("��ȡ�����ļ�service.properties������ȷ�����Ƿ���ڲ��Ҹ�ʽ��ȷ��");
			e.printStackTrace();
		}
	}
	
	public static String get(String key){
		return config.getProperty(key);
	}	

}
