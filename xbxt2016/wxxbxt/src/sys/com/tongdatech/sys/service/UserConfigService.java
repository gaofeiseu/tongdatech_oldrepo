package com.tongdatech.sys.service;

import java.util.List;

import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.bean.UserConfig;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.dao.UserConfigDao;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class UserConfigService extends BaseService {
	UserConfigDao userConfigDao = new UserConfigDao();

	public PageUI userinfo_query(Pagination pagination, UserConfig userConfig, UserInfo userInfo)
			throws Exception {
		return userConfigDao.userinfo_query(pagination, userConfig,userInfo);
	}

	public PageUI loadPowerDetailForUser(Pagination pagination,
			UserConfig userConfig) throws Exception {
		return userConfigDao.loadPowerDetailForUser(pagination, userConfig);
	}

	public AjaxMsg userinfo_add(Pagination pagination, UserConfig userConfig) throws Exception {
		return userConfigDao.userinfo_add(pagination, userConfig);
	}

	public AjaxMsg checkUserNameUnique(Pagination pagination,
			UserConfig userConfig) throws Exception {
		return userConfigDao.checkUserNameUnique(pagination, userConfig);
	}

	public AjaxMsg userinfo_delete(Pagination pagination, UserConfig userConfig) throws Exception {
		return userConfigDao.userinfo_delete(pagination, userConfig);
	}

	public AjaxMsg userinfo_getUserIDFromUserName(Pagination pagination,
			UserConfig userConfig) throws Exception {
		return userConfigDao.userinfo_getUserIDFromUserName(pagination, userConfig);
	}

	public AjaxMsg userinfo_getBrchNameFromBrchNo(Pagination pagination,
			UserConfig userConfig) throws Exception {
		return userConfigDao.userinfo_getBrchNameFromBrchNo(pagination,userConfig);
	}

	public AjaxMsg userinfo_edit(Pagination pagination, UserConfig userConfig) throws Exception {
		return userConfigDao.userinfo_edit(pagination,userConfig);
	}

	public List<List<String>> initPowerDlgRoleSele(Pagination pagination,
			UserConfig userConfig) throws Exception {
		return userConfigDao.initPowerDlgRoleSele(pagination,userConfig);
	}

	public AjaxMsg userpower_add(Pagination pagination, UserConfig userConfig) throws Exception {
		return userConfigDao.userpower_add(pagination,userConfig);
	}

	public AjaxMsg userpower_delete(Pagination pagination, UserConfig userConfig) throws Exception {
		return userConfigDao.userpower_delete(pagination,userConfig);
	}

	public AjaxMsg userinfo_getBrchNoFromBrchName(Pagination pagination,
			UserConfig userConfig) throws Exception {
		return userConfigDao.userinfo_getBrchNoFromBrchName(pagination,userConfig);
	}

	public AjaxMsg getRoleIDFromRoleName(Pagination pagination,
			UserConfig userConfig) throws Exception {
		return userConfigDao.getRoleIDFromRoleName(pagination,userConfig);
	}

	public AjaxMsg userpower_edit(Pagination pagination, UserConfig userConfig) throws Exception {
		return userConfigDao.userpower_edit(pagination,userConfig);
	}

	public List<List<String>> initMainBrchSele(Pagination pagination,
			UserConfig userConfig) throws Exception {
		return userConfigDao.initMainBrchSele(pagination,userConfig);
	}

	public PageUI excel(Pagination pagination, UserConfig userConfig) throws Exception {
		// TODO Auto-generated method stub
		return userConfigDao.excel(pagination,userConfig);
	}
}
