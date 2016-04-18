package com.tongdatech.xbxt.dao;

import java.util.List;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.xbxt.bean.Mgr;
import com.tongdatech.xbxt.bean.XBMobile;

public class XBMobileLoginDao extends BaseDao{
	public UserInfo getPwdByUsername(XBMobile bean) throws Exception {
		UserInfo userinfo = null;
		@SuppressWarnings("unchecked")
		//List<UserInfo> list = db.query("select * from t_sys_user where user_name='"+bean.getUsername()+"'",UserInfo.class);
		List<UserInfo> list = db.query("select * from t_sys_user where mgr_login_str='"+bean.getUsername()+"'",UserInfo.class);
		if(list.size()>0){
			userinfo = list.get(0);
			@SuppressWarnings("unchecked")
			List<Mgr> list_mgr = db.query("select * from t_cmgr where mgrid='"+userinfo.getUser_name()+"'",Mgr.class);
			if(list_mgr.size()>0){
				Mgr mgr = list_mgr.get(0);
				userinfo.setMgr(mgr);
			}
		}
		return userinfo;
	}
}
