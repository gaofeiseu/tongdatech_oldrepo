package com.tongdatech.map.service;

import java.io.File;

import com.tongdatech.map.bean.MapDataInputBean;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.dao.MapDataInputDao;
import com.tongdatech.sys.bean.UserInfo;

public class MapDataInputService {
	MapDataInputDao dao = new MapDataInputDao();

	public MapReturnBean getChildClassCombo(MapDataInputBean bean,UserInfo userinfo) {
		return dao.getChildClassCombo(bean,userinfo);
	}

	public MapReturnBean getTemplat(MapDataInputBean bean) {
		return dao.getTemplat(bean);
	}

	public MapReturnBean uploadTemplat(File[] upload,MapDataInputBean bean,UserInfo userinfo) {
		return dao.uploadTemplat(upload,bean,userinfo);
	}

	public File getFile(String total_path) {
		try{
			File file=new File(total_path);
			return file;
		}catch (Exception e) {
			return null;
		}
	}
	
}
