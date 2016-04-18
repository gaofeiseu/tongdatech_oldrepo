package com.tongdatech.sys.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.UserConfig;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.service.UserConfigService;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.excel.ExcelForReport;
/**
 * �ڵ������֮ǰ������ο�
 * */
public class UserConfigAction extends PaginationAction implements ModelDriven<UserConfig> {
	
	private static final long serialVersionUID = -2846633623333082266L;
	private String title;
	private UserConfig userConfig = new UserConfig();
	private UserConfigService userConfigService = new UserConfigService();
	private static Logger log =  Logger.getLogger(UserConfigAction.class);
	public String init(){
		/**����stuts-configs �����stuts-*.xml ����*/
		return "init";
	}
	@Pageable
	public String excel() throws Exception{
		
		//ExcelBean 
		excelBean=new ExcelForReport(excelEdition);
		
		excelBean.setFliename(userConfig.getExcelFileName());//���õ���excel����
		excelBean.setHeadtext(userConfig.getExcelTitleName());//���ñ�ͷ
   
		String[] titletext={"�û���","�ǳ�","��������"};//˫�б�ͷ �ڶ���   û��Ԫ��ռ��1��
		excelBean.setTitletext(titletext);
		String[] datakey={"user_name","user_nick_name","user_brch_name"};//���ö�Ӧ���ݼ����е� �������� key
		excelBean.setDatakey(datakey);

		/**���÷����*/
		PageUI p=userConfigService.excel(pagination,userConfig);
		
		excelBean.setData(p.getRows());                               //���ö�Ӧ���ݼ���
		excelBean.create();
//		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("wb",excelBean.getWb());
//		request.setAttribute("fileName",excelBean.getFliename());
		
		/**����excel����*/
		return "excel";
	}
	
	@Pageable
	public String initMainBrchSele() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		
		PrintWriter out = null;
		response.setContentType("text/html;charset=GBK");
		
		List<List<String>> list_result = new ArrayList<List<String>>();
		list_result = userConfigService.initMainBrchSele(pagination,userConfig);
		
		List<String> list_brch_no = list_result.get(0);
		List<String> list_brch_name = list_result.get(1);
		
		String str_json = "";
		str_json += "[";
		for(int i=0;i<list_brch_no.size()-1;i++){
			str_json += "{";
			str_json += "\"brch_no\"";
			str_json += ":";
			str_json += "\""+list_brch_no.get(i).toString()+"\",";
			str_json += "\"brch_name\"";
			str_json += ":";
			str_json += "\""+list_brch_name.get(i)+"\"";
			str_json += "},";
		}
		str_json += "{";
		str_json += "\"brch_no\"";
		str_json += ":";
		str_json += "\""+list_brch_no.get(list_brch_no.size()-1).toString()+"\",";
		str_json += "\"brch_name\"";
		str_json += ":";
		str_json += "\""+list_brch_name.get(list_brch_no.size()-1)+"\"";
		str_json += "}";
		
		str_json += "]";
		try {
			out = response.getWriter();
			out.print(str_json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		
		return null;
	}
	@Pageable
	public String initPowerDlgRoleSele() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		//HttpServletRequest request = ServletActionContext.getRequest();
		
		//String testParam = request.getParameter("testParam");
		//System.out.println("testParam:"+testParam);
		
		PrintWriter out = null;
		response.setContentType("text/html;charset=GBK");
		
		List<List<String>> list_result = new ArrayList<List<String>>();
		list_result = userConfigService.initPowerDlgRoleSele(pagination,userConfig);
		
		List<String> list_id = list_result.get(0);
		List<String> list_value = list_result.get(1);
		
		String str_json = "";
		str_json += "[";
		for(int i=0;i<list_id.size()-1;i++){
			str_json += "{";
			str_json += "\"role_id\"";
			str_json += ":";
			str_json += "\""+list_id.get(i).toString()+"\",";
			str_json += "\"role_name\"";
			str_json += ":";
			str_json += "\""+list_value.get(i)+"\"";
			str_json += "},";
		}
		str_json += "{";
		str_json += "\"role_id\"";
		str_json += ":";
		str_json += "\""+list_id.get(list_id.size()-1).toString()+"\",";
		str_json += "\"role_name\"";
		str_json += ":";
		str_json += "\""+list_value.get(list_id.size()-1)+"\"";
		str_json += "}";
		
		str_json += "]";
		try {
			out = response.getWriter();
			out.print(str_json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		
		return null;
	}
	
	@Pageable
	public String loadPowerDetailForUser() throws Exception{
//		Map<String, Object> session = ActionContext.getContext().getSession();
//		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		/**���÷����*/
		PageUI p=userConfigService.loadPowerDetailForUser(pagination,userConfig);

   	    /**����JSON��*/
    	JsonUtil.ToJson(p,response);
		return null;
	}
	
	@Pageable
	public String userinfo_delete() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg msg = new AjaxMsg();
		msg = userConfigService.userinfo_delete(pagination,userConfig);
		JsonUtil.ToJson(msg, response);
		return null;
	}
	
	@Pageable
	public String userpower_delete() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg msg = new AjaxMsg();
		msg = userConfigService.userpower_delete(pagination,userConfig);
		JsonUtil.ToJson(msg, response);
		return null;
	}
	
	@Pageable
	public String userpower_add() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg msg = new AjaxMsg();
		msg=userConfigService.userpower_add(pagination,userConfig);
		JsonUtil.ToJson(msg, response);
		return null;
	}
	
	@Pageable
	public String userinfo_add() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		AjaxMsg msg = new AjaxMsg();
		/**���÷����*/
		msg = userConfigService.userinfo_add(pagination,userConfig);
		
   	    /**����JSON��*/
    	JsonUtil.ToJson(msg,response);
		return null;
	}
	
	@Pageable
	public String checkUserNameUnique() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg msg = new AjaxMsg();
		/**���÷����*/
		msg=userConfigService.checkUserNameUnique(pagination,userConfig);
		JsonUtil.ToJson(msg,response);
		return null;
	}
	
	@Pageable
	public String userinfo_getBrchNameFromBrchNo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg msg = new AjaxMsg();
		msg = userConfigService.userinfo_getBrchNameFromBrchNo(pagination,userConfig);
		JsonUtil.ToJson(msg, response);
		return null;
	}
	
	@Pageable
	public String userinfo_getBrchNoFromBrchName() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg msg = new AjaxMsg();
		msg=userConfigService.userinfo_getBrchNoFromBrchName(pagination,userConfig);
		JsonUtil.ToJson(msg, response);
		return null;
	}
	
	@Pageable
	public String userpower_edit() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg msg = new AjaxMsg();
		msg = userConfigService.userpower_edit(pagination,userConfig);
		JsonUtil.ToJson(msg, response);
		return null;
	}
	
	@Pageable
	public String userinfo_edit() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg msg = new AjaxMsg();
		msg = userConfigService.userinfo_edit(pagination,userConfig);
		JsonUtil.ToJson(msg, response);
		return null;
	}
	@Pageable
	public String getRoleIDFromRoleName() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg msg = new AjaxMsg();
		msg = userConfigService.getRoleIDFromRoleName(pagination,userConfig);
		JsonUtil.ToJson(msg, response);
		return null;
	}
	@Pageable
	public String userinfo_getUserIDFromUserName() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg msg = new AjaxMsg();
		msg = userConfigService.userinfo_getUserIDFromUserName(pagination,userConfig);
		JsonUtil.ToJson(msg, response);
		return null;
	}
	
	@Pageable
	public String userinfo_query() throws Exception{
//		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo=(UserInfo)session.get(UserInfo.USER_INFO);

		/**���÷����*/
		PageUI p=userConfigService.userinfo_query(pagination,userConfig,userInfo);

   	    /**����JSON��*/
    	JsonUtil.ToJson(p,response);
		return null;
	}
	
	
	
	
	/** 
	 * ģʽ����  ����ʵ�ֵķ���
	 */
	@Override
	public UserConfig getModel() {
		return userConfig;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


}
