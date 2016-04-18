package com.tongdatech.map.service;

import java.io.File;

import com.tongdatech.map.bean.MapMarkersAdditionBean;
import com.tongdatech.map.bean.MapReturnBean;
import com.tongdatech.map.dao.MapMarkersAdditionDao;
import com.tongdatech.sys.bean.UserInfo;
/**
 * 
 * @author Mr.GaoFei
 *
 */
public class MapMarkersAdditionService {
	
	private MapMarkersAdditionDao dao = new MapMarkersAdditionDao();

	public MapReturnBean getUserTypeSelect(MapMarkersAdditionBean bean, UserInfo userinfo) throws Exception {
		return dao.getUserTypeSelect(bean,userinfo);
	}

	public MapReturnBean getChildClassSelect(MapMarkersAdditionBean bean,UserInfo userinfo) throws Exception {
		return dao.getChildClassSelect(bean,userinfo);
	}

	public MapReturnBean getChildClassSelectOptions(MapMarkersAdditionBean bean,UserInfo userinfo) throws Exception {
		return dao.getChildClassSelectOptions(bean,userinfo);
	}

	public MapReturnBean getMyIconSelectHtml(MapMarkersAdditionBean bean) throws Exception {
		return dao.getMyIconSelectHtml(bean);
	}

	public MapReturnBean do_icon_add(File[] upload,MapMarkersAdditionBean bean) {
		return dao.do_icon_add(upload,bean);
	}

	public MapReturnBean giveHiddenDivContent(MapMarkersAdditionBean bean,UserInfo userinfo) {
		return dao.giveHiddenDivContent(bean,userinfo);
	}

	public MapReturnBean addPointMarkerS(File[] upload,MapMarkersAdditionBean bean,UserInfo userinfo) throws Exception {
		return dao.addPointMarkerS(upload,bean,userinfo);
	}

	public MapReturnBean giveHiddenDivContent2(MapMarkersAdditionBean bean,UserInfo userinfo) {
		return dao.giveHiddenDivContent2(bean,userinfo);
	}

	public MapReturnBean addLineMarkerS(File[] upload,MapMarkersAdditionBean bean,UserInfo userinfo) {
		return dao.addLineMarkerS(upload,bean,userinfo);
	}

	public MapReturnBean giveHiddenDivContent3(MapMarkersAdditionBean bean,UserInfo userinfo) {
		return dao.giveHiddenDivContent3(bean,userinfo);
	}

	public MapReturnBean addAreaMarkerS(File[] upload,MapMarkersAdditionBean bean,UserInfo userinfo) {
		return dao.addAreaMarkerS(upload,bean,userinfo);
	}

	public MapReturnBean addtextarea(MapMarkersAdditionBean bean) throws Exception {
		return dao.addtextarea(bean);
	}

	public MapReturnBean getTextarea(MapMarkersAdditionBean bean) throws Exception {
		return dao.getTextarea(bean);
	}

	public MapReturnBean giveHiddenDivContent4(MapMarkersAdditionBean bean,UserInfo userinfo) {
		return dao.giveHiddenDivContent4(bean,userinfo);
	}

	public MapReturnBean addCircleMarkerS(File[] upload,
			MapMarkersAdditionBean bean,UserInfo userinfo) {
		return dao.addCircleMarkerS(upload,bean,userinfo);
	}

	public MapReturnBean giveHiddenDivContent5(MapMarkersAdditionBean bean,UserInfo userinfo) {
		return dao.giveHiddenDivContent5(bean,userinfo);
	}

	public MapReturnBean addTextAreaMarkerS(File[] upload,
			MapMarkersAdditionBean bean,UserInfo userinfo) {
		return dao.addTextAreaMarkerS(upload,bean,userinfo);
	}

}
