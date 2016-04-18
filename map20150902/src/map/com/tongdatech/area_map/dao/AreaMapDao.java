package com.tongdatech.area_map.dao;

import com.tongdatech.area_map.bean.AreaMapBean;
import com.tongdatech.area_map.bean.AreaMapReturnBean;
import com.tongdatech.sys.base.BaseDao;

public class AreaMapDao extends BaseDao{

	public AreaMapReturnBean insertCLOB(AreaMapBean bean) {
		AreaMapReturnBean returnBean = new AreaMapReturnBean();
		System.out.println("clob:"+bean.getInsert_clob());
		try {
			db.putClobIntoDB("1", bean.getInsert_clob());
			returnBean.setIf_success(true);
			returnBean.setMsg("�ɹ��ˣ�");
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("ʧ���ˣ�"+e.getMessage());
		}
		return returnBean;
	}

	public AreaMapReturnBean selectCLOB(AreaMapBean bean) {
		AreaMapReturnBean returnBean = new AreaMapReturnBean();
		try{
			String str_clob = null;
			str_clob = db.getClobFromDB("1");
			returnBean.setIf_success(true);
			returnBean.setMsg("�ɹ��ˣ�");
			returnBean.setReturnString(str_clob);
		}catch(Exception e){
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("ʧ���ˣ�"+e.getMessage());
		}
		return returnBean;
	}

	
	
}
