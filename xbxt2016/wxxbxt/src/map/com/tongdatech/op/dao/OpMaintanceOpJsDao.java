package com.tongdatech.op.dao;

import java.util.HashMap;
import java.util.Map;

import com.tongdatech.op.bean.OpMaintanceOpJsBean;
import com.tongdatech.op.bean.OpReturnBean;
import com.tongdatech.op.utils.FileUtils;
import com.tongdatech.sys.base.BaseDao;

public class OpMaintanceOpJsDao extends BaseDao{

	public OpReturnBean dosubmit(OpMaintanceOpJsBean bean) {
		OpReturnBean returnBean = new OpReturnBean();
		
		String server_url = bean.getServer_url();
		String max_zoom = bean.getMax_zoom();
		String min_zoom = bean.getMin_zoom();
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!"".equals(server_url)&&server_url!=null){
			map.put("url", server_url);
		}else{
			map.put("url", "NULL");
		}
		if(!"".equals(max_zoom)&&max_zoom!=null){
			map.put("maxZoon", max_zoom);
		}else{
			map.put("maxZoon", "NULL");
		}
		if(!"".equals(min_zoom)&&min_zoom!=null){
			map.put("minZoon", min_zoom);
		}else{
			map.put("minZoon", "NULL");
		}
		if(FileUtils.writeTxtFile(map)){
			returnBean.setIf_success(true);
			returnBean.setMsg("修改配置文件成功！");
		}else{
			returnBean.setIf_success(false);
			returnBean.setMsg("修改配置文件失败！");
		}
		return returnBean;
	}

}
