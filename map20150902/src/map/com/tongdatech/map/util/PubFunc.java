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
	 * ʡ������������
	 */
	//����Ϊ350000������Ϊ610000
//	public static final String PROV_CODE="350000";
	public static final String PROV_CODE="610000";
	/**
	 * @return true---��Windows����ϵͳ
	 */
	public static boolean isWindowsOS() {
		// ϵͳ����
		// System.getProperties().list(System.out);
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * ����utf-8��׼�ַ���
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String outStrByUtf(String str) {
		String str_tmp = str;
		if (PubFunc.isWindowsOS()) { // Ĭ�� iso8859-1
//			try {
//				str_tmp = new String(str.getBytes("iso8859-1"), "UTF-8");
//			} catch (java.io.UnsupportedEncodingException e) {
//				return str;
//			}
		} else { // linux�ַ���
			// �õ�ϵͳĬ�ϵ�encoding��
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
		// ����ʹ��jquery.json���ɵ�json��������null���֣���Ҫ�滻��\"\"
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
	 * ��ȡmap��ֵ
	 * 
	 * @param map
	 * @param path
	 *            ʹ��.�ָ��
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
	 * ��mapת����Json�ַ���
	 * 
	 * @param map
	 * @return �ַ���
	 */
	@SuppressWarnings("unchecked")
	public static String map2Json(Map map) {
		return JSONValue.toJSONString(map);
	}


	/**
	 * ��json�ַ�������ǰ̨Jspҳ��
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
	 * �������ݵ�ǰ̨
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
