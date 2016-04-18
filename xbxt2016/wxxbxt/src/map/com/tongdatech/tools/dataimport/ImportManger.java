package com.tongdatech.tools.dataimport;

import java.io.File;
import java.util.Map;


import com.tongdatech.sys.util.ClassLoaderUtil;
import com.tongdatech.tools.dataimport.config.BeanConfig;
import com.tongdatech.tools.dataimport.config.XmlConfigLoad;

public class ImportManger {
	public static String CONF_PATH = ClassLoaderUtil.getResource("configs", null).getPath();
	public static Map<String, BeanConfig> CONFIGS =new XmlConfigLoad().loadConfiguration(CONF_PATH);

	
    public static BeanConfig getType(String name){
    	BeanConfig bf = CONFIGS.get(name);
    	return bf;
    }
    
    public static boolean checkFile(String filename){
    	return filename.endsWith(".xls")||filename.endsWith(".xlsx")
    	||filename.endsWith(".csv")||filename.endsWith(".txt")||filename.endsWith(".dbf");
    }
    public static boolean saveFile(String filepath,File file,ImportContext context){
    	try{
    		File f=new File(filepath);
    		boolean rs = file.renameTo(f);
    		if(rs)context.setFile(f);
    		return rs;
	
    	}catch (Exception e) {
			return false;
		}
    	
    	
    }
	public static void main(String[] args) {
		System.out.println(CONF_PATH);
		System.out.println(CONFIGS);
	}
}
