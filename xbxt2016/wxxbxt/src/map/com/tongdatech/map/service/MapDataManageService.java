package com.tongdatech.map.service;

 
import java.util.List;
import java.util.Map;

 
import com.tongdatech.map.bean.MapBean;
import com.tongdatech.map.bean.ReturnBean;
import com.tongdatech.map.dao.MapDataManageDao;
 
import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
 

public class MapDataManageService extends BaseService {
 
	MapDataManageDao mapDataManageDao=new MapDataManageDao();
	
 
	public List<Map> getSNINFO(UserInfo userinfo,MapBean bean) throws Exception
	{
		return mapDataManageDao.getSNINFO(userinfo, bean);
	}
	public PageUI doquery(Pagination pagination,MapBean bean,UserInfo userinfo) throws Exception
	{
		return mapDataManageDao.doquery(pagination,bean,userinfo);
	}
	public List<Map> queryColumn(MapBean bean) throws Exception
	{
		return mapDataManageDao.queryColumn(bean);
	}
	public ReturnBean doSave(MapBean bean,String filename,UserInfo userInfo) throws Exception {
		ReturnBean returnBean = new ReturnBean();
		returnBean = mapDataManageDao.doSave(bean,filename,userInfo);
		return returnBean;
	}
}
