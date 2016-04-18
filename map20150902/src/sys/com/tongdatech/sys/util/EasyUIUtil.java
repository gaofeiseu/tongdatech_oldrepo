package com.tongdatech.sys.util;

/**
 *        <br>
 * @author xl 
 * @version    2014-4-11 ÉÏÎç11:17:46
 */

public class EasyUIUtil {
	public static String UI_HOME = "/weblib/js/jquery-easyui-1.3.5";
	public static String JS_HOME = "/weblib/js";
	/**
	 * ÓÉ&lt;xl:resource&gt;&lt;/xl:resource&gt; ´úÌæ
	 * @return String
	 */
	@Deprecated
	public static String Reference(){
		StringBuffer sb = new StringBuffer();
		String path=JS_HOME;
		String uiPath=UI_HOME;
		sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+uiPath+"/themes/gray/easyui.css\">\n");
		sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+uiPath+"/themes/icon.css\">\n");
		
		sb.append("<script type=\"text/javascript\" src=\""+uiPath+"/jquery.min.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\""+uiPath+"/jquery.easyui.min.js\"></script>\n");
		//sb.append("<script type=\"text/javascript\" src=\""+uiPath+"/easyloader.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\""+uiPath+"/locale/easyui-lang-zh_CNxl.js\"></script>\n");
		
		sb.append("<script type=\"text/javascript\" src=\""+uiPath+"/easyloader.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\""+path+"/easyui-common.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\""+path+"/validatebox_extend_rules.js\"></script>\n");
		
		return sb.toString();
	}

}
