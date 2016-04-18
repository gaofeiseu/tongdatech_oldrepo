package com.tongdatech.sys.demo.service;

import java.io.File;

import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.demo.dao.DemoFileDao;
import com.tongdatech.sys.pojo.JspMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
import com.tongdatech.sys.util.FileUtil;

public class DemoFileService extends BaseService {
	DemoFileDao DemoFileDao = new DemoFileDao();

	public JspMsg save(File file, String base_dir, String file_type,
			String file_name) { 
		JspMsg rs = null;
		file_name  = FileUtil.checkFileName(file_name);
		String file_path = FileUtil.createFileName(file_name);
		try{
			file.renameTo(new File(base_dir+"/"+file_path));
			JspMsg tmp=DemoFileDao.save(file_path,file_name,file_type);
			rs=new JspMsg(tmp);
		}catch (Exception e) {
			rs=new JspMsg();
			rs.setMsg("系统内部错误"+e.getMessage());
		}	
		return rs;
	}

	public PageUI list(Pagination pagination) throws Exception {
		return DemoFileDao.list(pagination);
	}

	public File getFile(String name) {
		try{
			File file=new File(name);
			return file;
		}catch (Exception e) {
			return null;
		}
		
	}

}
