package com.tongdatech.sys.util;

import java.util.Arrays;

public class VulnerabilitiesCheck {
    public static String TYPE_HTML="HTML";
    public static String TYPE_SQL="SQL";
    public static String encode(String str){
    	return encode(str,new String[]{TYPE_HTML,TYPE_SQL});
    }
	public static String encode(Object obj,String[] types) {
    	String str=null;
    	if(obj!=null)str=obj.toString();
    	else if(str==null||"".equals(str))return str;
	    StringBuilder encodeStrBuilder = new StringBuilder();
	    for (int i = 0, len = str.length(); i < len; i++) {
	       encodeStrBuilder.append(encodeChar(str.charAt(i),types));
	    }
	    return encodeStrBuilder.toString();
	} 
	private static CharSequence encodeChar(char c,String[] types) {
		if(Arrays.binarySearch(types, TYPE_HTML)>=0){
			switch (c) {
			case '&':
				return "&amp;";
			case '<':
				return "&lt;";
			case '>':
				return "&gt;";
			case '"':
				return "&quot;";
			case '\'':
				return "''''";
			default:
				;
			}
		}
		if(Arrays.binarySearch(types, TYPE_SQL)>=0){
			switch (c) {
			case '\'':
				return "''''";
			default:
				;
			}
		}
		return c+"";
		
	}

	private static String[] LOG_FORGIG={"%0d","\r","\n","%0a"};
	public static String logEncode(String str){
		for(String character:LOG_FORGIG){
			if(str.indexOf(character)!=-1)
				str.replaceAll(character, "");
		}
		return str;
	}

}
