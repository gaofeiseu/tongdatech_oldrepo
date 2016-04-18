package com.tongdatech.map.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map.bean.MapBean;
import com.tongdatech.map.bean.ReturnBean;
import com.tongdatech.map.service.MapQueryService;
import com.tongdatech.map.util.FileUpload;
import com.tongdatech.map.util.PubFunc;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.TreeNode;
import com.tongdatech.sys.util.JsonUtil;

public class MapQueryAction extends ActionSupport implements  ModelDriven<MapBean>
{
	private MapBean bean = new MapBean();
	private static final long serialVersionUID = -12287761761261387L;
	private MapQueryService mapQueryService=new MapQueryService();
	private File pic;
	private String filename="";
	
	@Override
	public MapBean getModel() {
	 
		return bean;
	}
	
	public MapBean getBean() {
		return bean;
	}

	public void setBean(MapBean bean) {
		this.bean = bean;
	}

	public MapQueryService getMapQueryService() {
		return mapQueryService;
	}

	public void setMapQueryService(MapQueryService mapQueryService) {
		this.mapQueryService = mapQueryService;
	}
	 
	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	/*
	 * 
	 * 点线面小类的树
	 * 6
	 */
	public String tree() throws Exception
	{
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	 	UserInfo userInfo = (UserInfo)sessionMap.get(UserInfo.USER_INFO);
    	List<TreeNode> rs=mapQueryService.tableTree(userInfo);
    	HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs, response);
    	return null;
	}
	/*
	 * 
	 * 查询点，线，面坐标
	 */
	public String data() throws Exception
	{
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	 	UserInfo userInfo = (UserInfo)sessionMap.get(UserInfo.USER_INFO);
		ReturnBean returnBean = new ReturnBean();
		returnBean=mapQueryService.mapDataQuery(bean, userInfo, "query");
		
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> map=new HashMap<String, Object>();
 
		map.put("list_map", returnBean.getList_map());
		map.put("if_success", returnBean.isIf_success());
 
		map.put("msg", returnBean.getMsg());
		PubFunc.jsonOutput(response, map);
    	return null;
	}
	/**
	 * 点击地图maker弹出框内容查询
	 * 
	 */
	public String queryOneMarkerInfo() throws Exception
	{
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
	 	UserInfo userInfo = (UserInfo)sessionMap.get(UserInfo.USER_INFO);
		ReturnBean returnBean = new ReturnBean();
		returnBean=mapQueryService.queryOneMarkerInfo(bean, userInfo);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("if_success", returnBean.isIf_success());
 
		map.put("msg", returnBean.getMsg());
		map.put("colList", returnBean.getColLists());
		map.put("dataMap", returnBean.getDataMaps());
		map.put("ifcreate", (returnBean.getCreateBrch()).equals(userInfo.getBrch_no())?true:false);//判断是登录机构是否为创建机构
		PubFunc.jsonOutput(response, map);
    	return null;
	}
	

	/*
	 * 更新或复制
	 */
	
	public String CopyAndUpdate(){
		System.out.print("");
		ReturnBean returnBean = new ReturnBean();
		
		try {
			String name="";
			if(pic!=null)
			{
				name=FileUpload.upload(pic,filename);
			 
			}
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		 	UserInfo userInfo = (UserInfo)sessionMap.get(UserInfo.USER_INFO);
			returnBean = mapQueryService.CopyAndUpdate(bean,name,userInfo);
			
			Map<String, Object> map=new HashMap<String, Object>();
	 
			map.put("if_success", returnBean.isIf_success());
			map.put("msg", returnBean.getMsg());
			HttpServletResponse response = ServletActionContext.getResponse();
			PubFunc.jsonOutput(response, map);
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return null;
	}
	/*
	 *点线面删除
	 */
	public String deleteData()
	{
		ReturnBean returnBean = new ReturnBean();
		try {
			returnBean = mapQueryService.deleteData(bean);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("if_success", returnBean.isIf_success());
			map.put("msg", returnBean.getMsg());
			HttpServletResponse response = ServletActionContext.getResponse();
			PubFunc.jsonOutput(response, map);
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return null;
	}

	/*
	 * 查询数据表的字段
	 */
	public String toQueryColumn(){
		List<Map> resultList=new ArrayList<Map>();
		resultList=mapQueryService.toQueryColumn(bean);
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("resultList", resultList);
		return "success";
	}
	
	
	
}