package com.tongdatech.map_mobile.service;

import java.io.File;
import java.util.Map;

import com.tongdatech.map_mobile.bean.FileOPBean;
import com.tongdatech.map_mobile.bean.MobileReturnBean;
import com.tongdatech.map_mobile.dao.FileOPDao;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class FileOPService {
	FileOPDao dao = new FileOPDao();

	public MobileReturnBean fileupload(FileOPBean bean, File[] upload, String[] uploadContentType, String[] uploadFileName) {
		return dao.fileupload(bean,upload,uploadContentType,uploadFileName);
	}

	public PageUI getData1(Pagination pagination, FileOPBean bean) {
		return dao.getData1(pagination,bean);
	}
	
	
	public File getFile(String name) {
		try{
			File file=new File(name);
			return file;
		}catch (Exception e) {
			return null;
		}
		
	}

	public Map<String, Object> downloadfile(FileOPBean bean) {
		return dao.downloadfile(bean);
	}

}
