package com.tongdatech.map.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tongdatech.map.bean.MapBean;
import com.tongdatech.map.bean.ReturnBean;
import com.tongdatech.map.dao.MapQueryDao;
import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.bean.UserInfo;

import com.tongdatech.sys.pojo.TreeNode;

public class MapQueryService extends BaseService {
 
	MapQueryDao mapQueryDao=new MapQueryDao();
	
	@SuppressWarnings("rawtypes")
	public List<TreeNode> tableTree(UserInfo userInfo) throws Exception
	{
		List<TreeNode> rs =new ArrayList<TreeNode>();
		List<Map> markerTypeList=mapQueryDao.getParams("MARKER_TYPE");//主类型list
		for(int i=0;i<markerTypeList.size();i++)
		{
			TreeNode tree =new TreeNode();//主类型层面的节点
			tree.setId("x"+String.valueOf(markerTypeList.get(i).get("value")));
			tree.setText(String.valueOf(markerTypeList.get(i).get("text")));
			List<TreeNode> children = new ArrayList<TreeNode>();
			List<Map> snList=mapQueryDao.getMaptables(userInfo.getBrch_no(), markerTypeList.get(i).get("value").toString());//子类型list
			for(int k=0;k<snList.size();k++)
			{
				TreeNode tree2=new TreeNode();//子类型层面的节点
				String children_class_sn = String.valueOf(snList.get(k).get("sn"));
				children_class_sn = String.valueOf((int)(Double.parseDouble(children_class_sn)));
				tree2.setId(children_class_sn);
				tree2.setText(String.valueOf(snList.get(k).get("class_name")));
				
				List<Map> cityList = mapQueryDao.getCityList(userInfo.getBrch_no(),children_class_sn);
				List<TreeNode> list_citynode = new ArrayList<TreeNode>();
				for(int c=0;c<cityList.size();c++){
					TreeNode treeCity = new TreeNode();//市级层面
					String city_sn = String.valueOf(cityList.get(c).get("brch_no"));
					String city_name = String.valueOf(cityList.get(c).get("brch_name"));
					treeCity.setId(children_class_sn+"#"+city_sn);
					treeCity.setText(city_name);
					
					List<TreeNode> list_wdnode = new ArrayList<TreeNode>();
//					List<Map> wdList = mapQueryDao.getWDList(userInfo.getBrch_no(),children_class_sn);
//					for(int w=0;w<wdList.size();w++){
//						TreeNode treeWD = new TreeNode();
//						treeWD.setId(children_class_sn+"#"+String.valueOf(wdList.get(w).get("brch_no")));
//						treeWD.setText(String.valueOf(wdList.get(w).get("brch_name")));
//						treeWD.setState(TreeNode.LEAF);
//						list_wdnode.add(treeWD);
//					}
					if(list_wdnode!=null&&list_wdnode.size()!=0){
						treeCity.setChildren(list_wdnode);
					}else{
						treeCity.setState(TreeNode.LEAF);
						treeCity.setChildren(null);
					}
					list_citynode.add(treeCity);
				}
				
				if(list_citynode!=null&&list_citynode.size()!=0){
					tree2.setChildren(list_citynode);
				}else{
					tree2.setState(TreeNode.LEAF);
					tree2.setChildren(null);
				}
				children.add(tree2);
			}
			if(children!=null&&children.size()!=0){
				tree.setChildren(children);
			}else{
				tree.setState(TreeNode.LEAF);
				tree.setChildren(null);
			}
			rs.add(tree);
		}
		return rs;
	}
	
	public ReturnBean mapDataQuery(MapBean bean, UserInfo userinfo,String type) {
		ReturnBean returnBean = new ReturnBean();
		returnBean = mapQueryDao.mapDataQuery(bean,userinfo,type);
		return returnBean;
	}
	
	public ReturnBean queryOneMarkerInfo(MapBean bean, UserInfo userinfo) {
		ReturnBean returnBean = new ReturnBean();
		returnBean = mapQueryDao.queryOneMarkerInfo(bean,userinfo);
		return returnBean;
	}
	public ReturnBean CopyAndUpdate(MapBean bean,String filename,UserInfo userinfo) {
		ReturnBean returnBean = new ReturnBean();
		returnBean = mapQueryDao.CopyAndUpdate(bean,filename,userinfo);
		return returnBean;
	}
	
	public ReturnBean deleteData(MapBean bean) {
		ReturnBean returnBean = new ReturnBean();
		returnBean = mapQueryDao.deleteData(bean);
		return returnBean;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Map> toQueryColumn(MapBean bean) {
		 
		List<Map> resultMap = mapQueryDao.toQueryColumn(bean);
		return resultMap;
	}
}
