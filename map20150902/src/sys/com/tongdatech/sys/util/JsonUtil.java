package com.tongdatech.sys.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServletResponse;

/**
 * JSON工具类      <br>
 * @author xl 
 * @version   2014-4-10 下午05:01:20
 */
public class JsonUtil {
	
	/**
	 * ToJson 方法
	 * @param obj         待转化对象
	 * @param response    HttpServletResponse对象
	 * @param isNoCache   是否缓存
	 * @param isExpose    是否排除Expose标注字段
	 * @param dateformat  日期类型
	 * @throws IOException
	 */
	public static void ToJson(Object obj, HttpServletResponse response,
			boolean isNoCache,boolean isExpose,String dateformat) throws IOException {
		// 对象转化为json struts2默认 application/json; 但是jquery-easyui 的ajaxform 不能正常使用
		GsonBuilder gb = new GsonBuilder();
		gb.setDateFormat(dateformat);
		if(isExpose)gb.excludeFieldsWithoutExposeAnnotation();
		Gson gson = gb.create();
		String result = gson.toJson(obj);

		response.setContentType("text/html; charset=GBK");
		if (isNoCache) {
			response.setHeader("cache-control", "no-cache");
		} else
			response.setHeader("cache-control", "private");

		PrintWriter out = response.getWriter();

		out.print(result);

		out.flush();
		out.close();
	}

	public static void ToJson(Object obj, HttpServletResponse response,
			boolean isNoCache) throws IOException {
		ToJson(obj, response, isNoCache,false,"yyyy-MM-dd");
	}

	public static void ToJson(Object obj, HttpServletResponse response)
			throws IOException {
		ToJson(obj, response, true,false,"yyyy-MM-dd");
	}
	public static void ToJsonExclude(Object obj, HttpServletResponse response)
	throws IOException {
       ToJson(obj, response, true,true,"yyyy-MM-dd");
    }
	public static List<Map<String, String>> toList(String json) {
		return toList(json, "yyyy-MM-dd");
	}

	public static List<Map<String, String>> toList(String json,
			String dateformat) {

		GsonBuilder gb = new GsonBuilder();
		gb.setDateFormat(dateformat);
		Gson gson = gb.create();
		// List<?> rs = (List<?>) (gson.fromJson(json, Map.class));
		Type typeOfT = new TypeToken<List<Map<String, String>>>() {
		}.getType();
		List<Map<String, String>> rs = gson.fromJson(json, typeOfT);
		return rs;
	}

	/**
	 * @param json
	 * @param typeOfT
	 *            e.g. Type typeOfT=new TypeToken<List<bean> >() {}.getType();
	 * @return List<?>
	 */
	public static List<?> toList(String json, Type typeOfT) {

		return toList(json, typeOfT, "yyyy-MM-dd");
	}

	public static List<?> toList(String json, Type typeOfT, String dateformat) {
		GsonBuilder gb = new GsonBuilder();
		gb.setDateFormat(dateformat);
		Gson gson = gb.create();
		List<?> rs = gson.fromJson(json, typeOfT);
		return rs;
	}

	public static void main(String[] args) {
		String json = "["
				+ "		        {\"productid\":\"FI-SW-01\",\"productname\":\"Koi\",\"unitcost\":10.00,\"status\":\"P\",\"listprice\":36.50,\"attr1\":\"Large\",\"itemid\":\"EST-1\"},"
				+ "				{\"productid\":\"K9-DL-01\",\"productname\":\"Dalmation\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":18.50,\"attr1\":\"Spotted Adult Female\",\"itemid\":\"EST-10\"},"
				+ "				{\"productid\":\"RP-SN-01\",\"productname\":\"Rattlesnake\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":38.50,\"attr1\":\"Venomless\",\"itemid\":\"EST-11\"},"
				+ "				{\"productid\":\"RP-SN-01\",\"productname\":\"Rattlesnake\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":26.50,\"attr1\":\"Rattleless\",\"itemid\":\"EST-12\"},"
				+ "				{\"productid\":\"RP-LI-02\",\"productname\":\"Iguana\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":35.50,\"attr1\":\"Green Adult\",\"itemid\":\"EST-13\"},"
				+ "				{\"productid\":\"FL-DSH-01\",\"productname\":\"Manx\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":158.50,\"attr1\":\"Tailless\",\"itemid\":\"EST-14\"},"
				+ "				{\"productid\":\"FL-DSH-01\",\"productname\":\"Manx\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":83.50,\"attr1\":\"With tail\",\"itemid\":\"EST-15\"},"
				+ "				{\"productid\":\"FL-DLH-02\",\"productname\":\"Persian\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":23.50,\"attr1\":\"Adult Female\",\"itemid\":\"EST-16\"},"
				+ "				{\"productid\":\"FL-DLH-02\",\"productname\":\"Persian\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":89.50,\"attr1\":\"Adult Male\",\"itemid\":\"EST-17\"},"
				+ "				{\"productid\":\"AV-CB-01\",\"productname\":\"Amazon Parrot\",\"unitcost\":92.00,\"status\":\"P\",\"listprice\":63.50,\"attr1\":\"Adult Male\",\"itemid\":\"EST-18\"}"
				+ "			]";

		System.out.println(toList(json));
	}
}
