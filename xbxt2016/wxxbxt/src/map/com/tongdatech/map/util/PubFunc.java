package com.tongdatech.map.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * BDM
 * 
 * @author wyk
 * @since 2012.4.20
 */
public class PubFunc {

	/**
	 * 省行政区划代码
	 */
	//福建为350000，陕西为610000
//	public static final String PROV_CODE="350000";
	public static final String PROV_CODE="610000";
	/**
	 * @return true---是Windows操作系统
	 */
	public static boolean isWindowsOS() {
		// 系统变量
		// System.getProperties().list(System.out);
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 返回utf-8标准字符串
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String outStrByUtf(String str) {
		String str_tmp = str;
		if (PubFunc.isWindowsOS()) { // 默认 iso8859-1
//			try {
//				str_tmp = new String(str.getBytes("iso8859-1"), "UTF-8");
//			} catch (java.io.UnsupportedEncodingException e) {
//				return str;
//			}
		} else { // linux字符集
			// 得到系统默认的encoding码
//			String fileEncode = System.getProperty("file.encoding");
//			System.out.println("linux file.encoding is :"+fileEncode.toUpperCase());
//			if(!"UTF-8".equals(fileEncode.toUpperCase()))
//			try {
//				str_tmp = new String(str.getBytes(fileEncode), "UTF-8");
//			} catch (java.io.UnsupportedEncodingException e) {
//				return str;
//			}
		}
		return str_tmp;
	}

	public static Map json2Map(String json) {
		// 由于使用jquery.json生成的json数据中有null出现，需要替换成\"\"
		json = json.replaceAll("null", "\"\"");
		JSONParser parser = new JSONParser();
		ContainerFactory containerFactory = new ContainerFactory() {
			public List creatArrayContainer() {
				return new LinkedList();
			}

			public Map createObjectContainer() {
				return new LinkedHashMap();
			}

		};

		try {
			Map map = (Map) parser.parse(json, containerFactory);
			return map;
		} catch (ParseException pe) {
			System.out.println(pe);
		}
		return null;
	}

	/**
	 * 获取map的值
	 * 
	 * @param map
	 * @param path
	 *            使用.分割级别
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getMapValue(Map map, String path) {

		Object obj = null;
		String[] keys = path.split("\\.");
		for (String key : keys) {
			if (map == null)
				return null;
			obj = map.get(key);
			if (obj == null)
				return null;

			if (obj.getClass().getName().equals("java.util.LinkedHashMap"))
				map = (Map) obj;
		}
		return obj;
	}

	/**
	 * 将map转换成Json字符串
	 * 
	 * @param map
	 * @return 字符串
	 */
	@SuppressWarnings("unchecked")
	public static String map2Json(Map map) {
		return JSONValue.toJSONString(map);
	}


	/**
	 * 将json字符串发送前台Jsp页面
	 * 
	 * @param response
	 * @param json
	 */
	public static void jsonOutput(HttpServletResponse response, String json) {
		PrintWriter out = null;
		response.setContentType("text/htm;charset=utf-8");
		try {
			out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 发送数据到前台
	 * 
	 * @param response
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public static void jsonOutput(HttpServletResponse response, Map map) {
		PrintWriter out = null;
		response.setContentType("text/html;charset=gb2312");
		try {
			out = response.getWriter();
			out.print(map2Json(map));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
