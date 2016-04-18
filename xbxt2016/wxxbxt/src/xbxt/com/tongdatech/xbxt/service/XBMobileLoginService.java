package com.tongdatech.xbxt.service;

import java.util.Map;

import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.xbxt.bean.XBMobile;
import com.tongdatech.xbxt.dao.XBMobileLoginDao;

public class XBMobileLoginService {
	private XBMobileLoginDao dao = new XBMobileLoginDao();

	public XBMobileLoginDao getDao() {
		return dao;
	}

	public void setDao(XBMobileLoginDao dao) {
		this.dao = dao;
	}
	public AjaxMsg checkPassword(XBMobile bean,Map<String,Object> session) throws Exception {
		AjaxMsg am = new AjaxMsg();
		UserInfo userinfo = dao.getPwdByUsername(bean);
		if(userinfo!=null){
			String md5_pwd = userinfo.getPassword();
			if(md5_pwd.equals(bean.getPassword())){
				am.setSuccess(true);
				am.setMsg("登陆成功！");
				session.put(UserInfo.USER_INFO, userinfo);
			}else{
				am.setSuccess(false);
				am.setMsg("密码不正确！");
			}
		}else{
			am.setSuccess(false);
			am.setMsg("用户名不存在！");
		}
		return am;
	}
}
