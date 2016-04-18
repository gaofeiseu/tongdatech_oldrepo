package com.tongdatech.map_mobile.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

public class JsonUtils {
	public static void ToJsonStr(Object obj, HttpServletResponse response) throws IOException, JSONException {
		String result = JSONUtil.serialize(obj);
		response.setContentType("text/html; charset=GBK");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
	public static void sendString(String str,HttpServletResponse response) throws Exception{
		String result = str;
		response.setContentType("text/html; charset=GBK");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
}
