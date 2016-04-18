package com.tongdatech.sys.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*  
 * ���ؿͻ�����Ϣ������  
 */
public class ClientInfo {

	private String info = "";
	private String explorerVer = "δ֪";
	private String OSVer = "δ֪";

	/*
	 * ���캯�� ������String request.getHeader("user-agent")
	 * 
	 * IE7:Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0;
	 * SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media
	 * Center PC 6.0; .NET4.0C) IE8:Mozilla/4.0 (compatible; MSIE 8.0; Windows
	 * NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET
	 * CLR 3.0.30729; Media Center PC 6.0; .NET4.0C) Maxthon:Mozilla/4.0
	 * (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR
	 * 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0;
	 * .NET4.0C; Maxthon 2.0) firefox:Mozilla/5.0 (Windows; U; Windows NT 6.1;
	 * zh-CN; rv:1.9.2.11) Gecko/20101012 Firefox/3.6.11 Chrome:Mozilla/5.0
	 * (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.7 (KHTML, like Gecko)
	 * Chrome/7.0.517.44 Safari/534.7 Opera:Opera/9.80 (Windows NT 6.1; U;
	 * zh-cn) Presto/2.6.30 Version/10.63
	 * 
	 * ����ϵͳ�� Win7 : Windows NT 6.1 WinXP : Windows NT 5.1
	 */
	public ClientInfo(String info) {
		this.info = info;
	}

	/*
	 * ��ȡ�������������
	 */
	public String getExplorerName() {
		String str = "δ֪";
		Pattern pattern = Pattern.compile("");
		Matcher matcher;
		if (info.indexOf("MSIE") != -1) {
			str = "MSIE"; // ΢��IE
			pattern = Pattern.compile(str + "\\s([0-9.]+)");
		} else if (info.indexOf("Firefox") != -1) {
			str = "Firefox"; // ���
			pattern = Pattern.compile(str + "\\/([0-9.]+)");
		} else if (info.indexOf("Chrome") != -1) {
			str = "Chrome"; // Google
			pattern = Pattern.compile(str + "\\/([0-9.]+)");
		} else if (info.indexOf("Opera") != -1) {
			str = "Opera"; // Opera
			pattern = Pattern.compile("Version\\/([0-9.]+)");
		} else if (info.indexOf("Safari") != -1) {
			str = "Safari"; // Safari
			pattern = Pattern.compile(str + "\\/([0-9.]+)");
		} 
		
		if (pattern.pattern().equals("")) {
			return "Others";
		}
		
		matcher = pattern.matcher(info);
		if (matcher.find()) {
			explorerVer = matcher.group(1);
		} else {
			explorerVer = "";
		}
			
		return str;
	}

	/*
	 * ��ȡ����������汾
	 */
	public String getExplorerVer() {
		return this.explorerVer;
	}

	/*
	 * ��ȡ�����������ƣ����磺���Ρ�����֮���ȣ�
	 */
	public String getExplorerPlug() {
		String str = "��";
		if (info.indexOf("Maxthon") != -1)
			str = "Maxthon"; // ����
		return str;
	}

	/*
	 * ��ȡ����ϵͳ����
	 */
	public String getOSName() {
		String str = "δ֪";
		Pattern pattern = Pattern.compile("");
		Matcher matcher;
		if (info.indexOf("Windows") != -1) {
			str = "Windows"; // Windows NT 6.1
			pattern = Pattern.compile(str + "\\s([a-zA-Z0-9]+\\s[0-9.]+)");
		}
		matcher = pattern.matcher(info);
		if (matcher.find())
			OSVer = matcher.group(1);
		return str;
	}

	public String getOSNameAll() {
		String str = "δ֪";
		System.out.println(info);
		if (info.indexOf("Windows") != -1) {
			str = "Windows";
		} else if (info.indexOf("Mac") != -1) {
			str = "Mac";
		} else if (info.indexOf("Unix") != -1) {
			str = "UNIX";
		} else if (info.indexOf("Linux") != -1) {
			str = "Linux";
		} else if (info.indexOf("SunOS") != -1) {
			str = "SunOS";
		} else {
			str = "Others";
		}
		return str;
	}

	/*
	 * ��ȡ����ϵͳ�汾
	 */
	public String getOSVer() {
		return this.OSVer;
	}
}