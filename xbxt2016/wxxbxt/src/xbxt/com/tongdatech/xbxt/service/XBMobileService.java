package com.tongdatech.xbxt.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.xbxt.bean.XBMobile;
import com.tongdatech.xbxt.dao.XBMobileDao;

public class XBMobileService {
	private XBMobileDao dao = new XBMobileDao();
	public XBMobileDao getDao() {
		return dao;
	}
	public void setDao(XBMobileDao dao) {
		this.dao = dao;
	}
	public String marketing_record(XBMobile bean, File[] files, String[] filesFileName, String[] filesContentType,String path,UserInfo userinfo) throws Exception {
		String result = "";
		boolean if_upload_success = false;
		String file_name = "";
		if(files!=null){
			if(files[0].length()==0){
				System.out.println("这是奇怪的事情，上传的文件的大小是0,先标识为成功上传吧(⊙o⊙)…");
				if_upload_success = true;
			}else{
				try{
					System.out.println("filesFileName:"+filesFileName[0]+"  filesContentType:"+filesContentType[0]);
					String exe_str = filesFileName[0].substring(filesFileName[0].lastIndexOf("."));
					file_name = UUID.randomUUID().toString()+exe_str;//+".png";
					String save_path = path+"xbmobile_file"+File.separator+file_name;
					System.out.println("save_path:"+save_path);
					files[0].renameTo(new File(save_path));
					if_upload_success = true;
				}
				catch(Exception ex){
					ex.printStackTrace();
					if_upload_success = false;
				}
			}
		}else{
			System.out.println("files是空的！");
			if_upload_success = true;
		}
		if(if_upload_success){
			AjaxMsg am = dao.marketing_record(bean,file_name,userinfo);
			result = "<script>parent.mr_callback('"+am.getMsg()+"')</script>";
		}else{
			result = "<script>parent.mr_callback('很遗憾，图片上传失败。')</script>";
		}
		return result;
	}
	public List<Map<String, Object>> getCustType() throws Exception {
		return dao.getCustType();
	}
	public List<Map<String, Object>> fee_query(UserInfo userinfo) throws Exception {
		return dao.fee_query(userinfo);
	}
	public List<Map<String, Object>> visit_log_query(UserInfo userinfo) throws Exception {
		return dao.visit_log_query(userinfo);
	}
	public List<Map<String, Object>> cust_warning_query(UserInfo userinfo) throws Exception {
		return dao.cust_warning_query(userinfo);
	}
	public AjaxMsg ajax_find_cust(XBMobile bean) throws Exception {
		return dao.ajax_find_cust(bean);
	}
	public List<Map<String, Object>> init_cust_report_detail_section(UserInfo userinfo, XBMobile bean) throws Exception {
		return dao.init_cust_report_detail_section(userinfo,bean);
	}
	
}
