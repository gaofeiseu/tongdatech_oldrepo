package com.tongdatech.map.service;

import java.io.File;
import java.lang.reflect.Method;

import com.gaofei.main.MainFunction;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.bean.MapUpdateBean;
import com.tongdatech.map.dao.MapUpdateDao;
import com.tongdatech.map.util.CMDRunner;

public class MapUpdateService {
	MapUpdateDao dao = new MapUpdateDao();
	public MapReturnBean dodownload(MapUpdateBean bean,String webroot_path) {
		MapReturnBean returnBean = new MapReturnBean();
		try{
			/*
			webroot_path.replaceAll("\\\\", "/");
			//java -jar mapdownloader.jar F:/map1234 1#2#3 1 17 118.417774 32.391383 119.243122 31.565489
			String cmd = "java -jar "+webroot_path+"mapdownloader/mapdownloader.jar"+" "+webroot_path+"/maptile "+bean.getMap_type()+" 1 "+bean.getMap_zoom()+" "+bean.getSt_lng()+" "+bean.getSt_lat()+" "+bean.getEd_lng()+" "+bean.getEd_lat();
			System.out.println(cmd);
			CMDRunner cmdRunner = new CMDRunner(cmd);
			Thread t = new Thread(cmdRunner);
			t.start();
			
		*/	
			//////////////////////
			Method startClassMainMethod= MainFunction.class.getMethod("main", String[].class);  
		    startClassMainMethod.invoke(null, (Object)new String[]{""+webroot_path+"/maptile",""+bean.getMap_type()+"","1",""+bean.getMap_zoom()+"",""+bean.getSt_lng()+"",""+bean.getSt_lat()+"",""+bean.getEd_lng()+"",""+bean.getEd_lat()+""});  //备注 
			/////////////////////
			returnBean.setIf_success(true);
			returnBean.setMsg("下载任务注册成功，请耐心等待，不要重复执行下载任务！");
		}
		catch(Exception ex){
			ex.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(ex.getMessage());
		}
		return returnBean;
	}
}
