package com.gaofei.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.gaofei.bean.BaseBean;

public class PropertyUtils {
	private static String proFilePath = System.getProperty("user.dir") + "/map.properties";
	public static boolean validate() {
		boolean flag = false;
		try{
			File f = new File(proFilePath);
			if(f!=null&&f.exists()&&f.isFile()){
				flag = true;
			}else{
				flag = false;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			flag = false;
		}
		return flag;
	}
	public static BaseBean getBean() throws Exception{
		BaseBean bean = new BaseBean();
		try{
			String[] args = new String[8];
			args[0] = get("save_path");
			args[1] = get("list_map_type");
			args[2] = get("st_zoom");
			args[3] = get("ed_zoom");
			args[4] = get("st_lon");
			args[5] = get("st_lat");
			args[6] = get("ed_lon");
			args[7] = get("ed_lat");
			bean = new BaseBean(args);
			ValidationUtil.validate(args);
			bean = new BaseBean(args);
		}
		catch(Exception ex){
			throw ex;
		}
		return bean;
	}
	public static String get(String str) throws Exception{
		try{
			InputStream in = new BufferedInputStream(new FileInputStream(proFilePath));
			ResourceBundle resourceBundle = new PropertyResourceBundle(in);
			return resourceBundle.getString(str);
		}
		catch(Exception ex){
			if(ex instanceof MissingResourceException){
				throw new Exception("没有找到KEY为"+str+"的键值对");
			}else{
				throw ex;
			}
		}
	}
}
