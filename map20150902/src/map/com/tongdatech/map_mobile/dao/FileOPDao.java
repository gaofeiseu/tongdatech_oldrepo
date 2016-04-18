package com.tongdatech.map_mobile.dao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.tongdatech.map_mobile.bean.FileOPBean;
import com.tongdatech.map_mobile.bean.MobileReturnBean;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class FileOPDao extends BaseDao{

	public MobileReturnBean fileupload(FileOPBean bean, File[] upload, String[] uploadContentType, String[] uploadFileName) {
		MobileReturnBean returnBean = new MobileReturnBean();
		
		String file_class = "";
		String file_name = "";
		String file_exe = "";
		String user_name = "";
		String user_id = "";
		String file_size = "";
		String file_save_path = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String now_time = sdf.format(new Date());
		file_class = bean.getFile_class();
		//file_name = bean.getFile_name();
		file_name = uploadFileName[0];
		//file_exe = bean.getFile_exe();
		if(uploadFileName!=null){
			file_exe = file_name.substring(file_name.lastIndexOf(".")+1, file_name.length());
		}
		user_name = bean.getUser_name();
		user_id = bean.getUser_id();
		file_save_path = bean.getFile_save_path();
		String file_real_path = "";
		file_size = String.valueOf(upload[0].length());
		file_real_path = file_save_path + UUID.randomUUID()+"."+file_exe;
		String file_msg = "";
		file_msg = bean.getFile_msg();
		if(upload[0].length()!=0){
			//正常的文件
			if(upload[0].renameTo(new File(file_real_path))){
				try {
					String sql = "";
					sql = "insert into a_file_upload_info" +
							" (sn,file_exe,file_class,file_size,old_file_name," +
							"upload_user_name,upload_user_id,file_save_path," +
							"status,input_time,file_msg) values (SEQ_A_FILE_UPLOAD_INFO.nextval," +
							"'"+file_exe+"','"+file_class+"','"+file_size
							+"','"+file_name+"','"+user_name+"','"+user_id
							+"','"+file_real_path+"','1','"+now_time+"','"+file_msg+"')";
					int ii = 0;
					ii = db.insert(sql);
					if(ii==1){
						returnBean.setIf_success(true);
						returnBean.setMsg("文件上传成功！");
					}else{
						returnBean.setIf_success(false);
						returnBean.setMsg("文件上传失败！数据库插入失败！");
					}
				} catch (Exception e) {
					e.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg("文件上传失败！数据库操作失败！");
				}
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("文件上传失败！文件保存失败！");
			}
		}else{
			returnBean.setIf_success(false);
			returnBean.setMsg("文件上传失败！上传的文件是空的！");
		}
		return returnBean;
	}

	@SuppressWarnings("unchecked")
	public PageUI getData1(Pagination pagination, FileOPBean bean) {
		PageUI rs = new PageUI();
		try {
			System.out.println("------>"+bean.getLjhx_brchno_query());
			String brch_no = "";
			if(bean.getLjhx_brchno_query()!=null&&!"".equals(bean.getLjhx_brchno_query())){
				brch_no = bean.getLjhx_brchno_query();
			}else{
				brch_no = bean.getHidden_root();
			}
			String sql = "select a.sn,a.file_exe,a.file_class,a.file_size,a.old_file_name as my_filename," +
					"a.upload_user_name,a.upload_user_id,a.file_save_path,a.status,a.input_time," +
					"a.file_msg,F_PARAMS('M_FILE_TYPE',a.file_class) file_class_str from" +
					" a_file_upload_info a,t_sys_user b where a.file_class='"+bean.getFile_class()+"' " +
					" AND a.upload_user_id=b.user_id "+
					" and b.brch_no in (select brch_no from t_sys_brch start with brch_no='"+brch_no+"' connect by prior brch_no = brch_up) "
					+" order by a.sn desc ";
			System.out.println("---->sql===="+sql);
			String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
			try {
				List<FileOPBean> list_map = new ArrayList<FileOPBean>();
				list_map = db.query(listsql, FileOPBean.class);
				for(int i=0;i<list_map.size();i++){
					list_map.get(i).setFile_save_path("<a href='/fileop_downloadfile.action?file_sn="+list_map.get(i).getSn()+"'>点击下载文件</a>");
				}
				rs.setRows(list_map);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			int total = 0;
			try {
				total = db.count(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs.setTotal(total);
			pagination.setTotal(total);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> downloadfile(FileOPBean bean) {
		Map<String,Object> map = new HashMap<String,Object>();
		String sql = "select file_save_path,F_PARAMS('M_FILE_TYPE',file_class) file_class_str," +
				"file_exe,old_file_name from a_file_upload_info where sn="+bean.getFile_sn();
		System.out.println("sql----->"+sql);
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		try {
			list_map = db.query(sql);
			if(list_map.size()==1){
				map = list_map.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
