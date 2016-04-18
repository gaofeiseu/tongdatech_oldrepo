package com.tongdatech.map.service;

import java.io.File;

import com.tongdatech.map.bean.MapDataParserBean;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.dao.MapDataParserDao;

public class MapDataParserService {
	private MapDataParserDao dao = new MapDataParserDao();

	public MapReturnBean uploadTXT(File[] upload, MapDataParserBean bean) throws Exception{
		return dao.uploadTXT(upload,bean);
	}
	
	
}
