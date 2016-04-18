package com.tongdatech.sys.service;

import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.bean.Role;
import com.tongdatech.sys.bean.User;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.dao.UserDao;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class UserService extends BaseService {
	private UserDao userDao = new UserDao();

	public PageUI query(Pagination pagination, User user,UserInfo userInfo) throws Exception {
		return userDao.query(pagination,user,userInfo);
	}

	public PageUI roles(Pagination pagination, User user,UserInfo userInfo) throws Exception {
		return userDao.roles(pagination,user,userInfo);
	}

	public AjaxMsg resetPwd(User user, UserInfo userInfo) {
		return userDao.resetPwd(user,userInfo);
	}

	public AjaxMsg nameCheck(User user)  throws Exception{
		return userDao.nameCheck(user);
	}

	public AjaxMsg saveUser(User user, UserInfo userInfo) {
		return userDao.saveUser(user,userInfo);
	}

	public AjaxMsg saveRoles(Role role, UserInfo userInfo) throws Exception {
		return userDao.saveRoles(role,userInfo);
	}

	public AjaxMsg delRoles(Role role, UserInfo userInfo) {
		return userDao.delRoles(role,userInfo);
	}

	public AjaxMsg delUser(User user, UserInfo userInfo) throws Exception {
		return userDao.delUser(user,userInfo);
	}

	public AjaxMsg savePWD(String old_pwd, String password, String repeat_pwd,
			UserInfo userInfo) {
		return userDao.savePWD(old_pwd,password,repeat_pwd,userInfo);
	}

	public MapReturnBean getMyIconSelectHtml(User user) {
		return userDao.getMyIconSelectHtml(user);
	}

	public MapReturnBean submitUserIcon(User user) throws Exception {
		return userDao.submitUserIcon(user);
	}

}
