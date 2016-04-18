package com.tongdatech.map.service;

import java.util.List;

import com.tongdatech.map.bean.MapMarkerClassMaintanceBean;
import com.tongdatech.map.dao.MapMarkerClassMaintanceDao;
import com.tongdatech.sys.bean.Brch;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
/**
 * 
 * @author Mr.GaoFei
 *
 */
public class MapMarkerClassMaintanceService {
	private MapMarkerClassMaintanceDao dao = new MapMarkerClassMaintanceDao();


	public PageUI loaddata1(Pagination pagination,
			MapMarkerClassMaintanceBean bean,UserInfo userInfo) throws Exception {
		return dao.loaddata1(pagination,bean,userInfo);
	}

	public int addchildclass(MapMarkerClassMaintanceBean bean,UserInfo userinfo) throws Exception {
		return dao.addchildclass(bean,userinfo);
	}

	public int deletechildclass(MapMarkerClassMaintanceBean bean) throws Exception {
		return dao.deletechildclass(bean);
	}

	public int editchildclass(MapMarkerClassMaintanceBean bean) throws Exception {
		return dao.editchildclass(bean);
	}

	public PageUI loaddata2(Pagination pagination,
			MapMarkerClassMaintanceBean bean,UserInfo userInfo) throws Exception {
		return dao.loaddata2(pagination,bean,userInfo);
	}

	public int submit_classproperty_add(MapMarkerClassMaintanceBean bean) throws Exception {
		return dao.submit_classproperty_add(bean);
	}

	public int deleteclassproperty(MapMarkerClassMaintanceBean bean) throws Exception {
		return dao.deleteclassproperty(bean);
	}

	public int editclassproperty(MapMarkerClassMaintanceBean bean) throws Exception {
		return dao.editclassproperty(bean);
	}
	public int copychildclass(MapMarkerClassMaintanceBean bean,UserInfo userinfo) throws Exception {
		return dao.copychildclass(bean,userinfo);
	}
	public List<Brch> brchTree(UserInfo userinfo) throws Exception{
		return dao.brchTree(userinfo);
	}
}
