package com.tongdatech.sys.tag;

import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class ResourceOfJSAndCSS extends Component {
	public static String UI_HOME = "/weblib/js/jquery-easyui-1.3.5";
	public static String JS_HOME = "/weblib/js";
	public static String CSS_HOME = "/weblib/css";
	private static Logger log =  Logger.getLogger(ResourceOfJSAndCSS.class);
	String simple;

	public ResourceOfJSAndCSS(ValueStack stack) {
		super(stack);
	}
	
	
	@Override
	public boolean start(Writer writer) {
		StringBuffer sb = new StringBuffer();
	
		
		boolean flag =Boolean.parseBoolean(simple);
		if(flag){
			sb.append("<script type=\"text/javascript\" src=\""+UI_HOME+"/jquery.min.js\"></script>\n");
		}else{
			sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+CSS_HOME+"/main.css\" >\n");
			sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+UI_HOME+"/themes/gray/easyui.css\">\n");
			sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\""+UI_HOME+"/themes/icon.css\">\n");
			
			sb.append("<script type=\"text/javascript\" src=\""+UI_HOME+"/jquery.min.js\"></script>\n");
			sb.append("<script type=\"text/javascript\" src=\""+UI_HOME+"/jquery.easyui.min.js\"></script>\n");			
			sb.append("<script type=\"text/javascript\" src=\""+UI_HOME+"/locale/easyui-lang-zh_CNxl.js\"></script>\n");
			
			sb.append("<script type=\"text/javascript\" src=\""+UI_HOME+"/easyloader.js\"></script>\n");
			sb.append("<script type=\"text/javascript\" src=\""+JS_HOME+"/easyui-common.js\"></script>\n");
			sb.append("<script type=\"text/javascript\" src=\""+JS_HOME+"/validatebox_extend_rules.js\"></script>\n");
		}
		try {
			writer.write(sb.toString());
		} catch (IOException e) {
			log.error("TAG",e);
		}
		return super.start(writer);
	}


	public void setSimple(String simple) {
		this.simple = simple;
	}
}
