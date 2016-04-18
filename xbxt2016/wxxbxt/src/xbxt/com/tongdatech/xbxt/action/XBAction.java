package com.tongdatech.xbxt.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.Brch;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.TreeNode;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.ParamsUtil;
import com.tongdatech.xbxt.bean.CustMonthUsageReport;
import com.tongdatech.xbxt.bean.XBBean;
import com.tongdatech.xbxt.service.XBService;

public class XBAction extends PaginationAction implements ModelDriven<XBBean>{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(XBAction.class);
	private XBBean bean = new XBBean();
	private XBService service = new XBService();
	private String node;
	private String expnode;
	private String type;
	private String mode;
	private String q;
	
	private String file_name;
	private InputStream inputStream;
	
	public String init(){
		return "init";
	}
	
	public String init2(){
		return "init2";
	}
	
	public String init3(){
		return "init3";
	}
	
	public String init_cust_warning(){
		return "init_cust_warning";
	}
	
	public String init_mgr_setting(){
		return "init_mgr_setting";
	}
	
	public String init_interface(){
		return "init_interface";
	}
	
	public String init_khyyqkjb(){
		System.out.println("===>"+bean.getBigcustno()+"   "+bean.getQuery_time());
		return "init_khyyqkjb";
	}
	
	public String expexcel_hz_report() throws Exception {
		AjaxMsg am = new AjaxMsg();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		String path = ServletActionContext.getServletContext().getRealPath("/");
		am = service.expexcel_hz_report(bean,userInfo,path);//generate the excel file
		if(am.isSuccess()){
			String file_path = path+"xbxt_excel/tmp_file"+File.separator+am.getMsg();
			File file = new File(file_path);
			inputStream=new FileInputStream(file); 
			String name = "客户用邮情况汇总表.xls";
	    	file_name=new String(name.getBytes("GBK"), "ISO-8859-1");
	    	return "download";
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			JsonUtil.ToJson(am,response);
			return null;
		}
	}
	
	public String expexcel_cmur_report() throws Exception {
		AjaxMsg am = new AjaxMsg();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		String path = ServletActionContext.getServletContext().getRealPath("/");
		am = service.expexcel_cmur_report(bean,userInfo,path);//generate the excel file
		if(am.isSuccess()){
			String file_path = path+"xbxt_excel/tmp_file"+File.separator+am.getMsg();
			File file = new File(file_path);
			inputStream=new FileInputStream(file); 
			String name = "客户月度用邮报告.xls";
	    	file_name=new String(name.getBytes("GBK"), "ISO-8859-1");
	    	return "download";
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			JsonUtil.ToJson(am,response);
			return null;
		}
	}
	
	public String expexcel_totalavertime_report() throws Exception {
		AjaxMsg am = new AjaxMsg();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		String path = ServletActionContext.getServletContext().getRealPath("/");
		am = service.expexcel_totalavertime_report(bean,userInfo,path);//generate the excel file
		if(am.isSuccess()){
			String file_path = path+"xbxt_excel/tmp_file"+File.separator+am.getMsg();
			File file = new File(file_path);
			inputStream=new FileInputStream(file); 
			String name = "总平均时限情况表.xls";
	    	file_name=new String(name.getBytes("GBK"), "ISO-8859-1");
	    	return "download";
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			JsonUtil.ToJson(am,response);
			return null;
		}
	}
	
	public String expexcel_khyyqkjb_report() throws Exception {
		AjaxMsg am = new AjaxMsg();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		String path = ServletActionContext.getServletContext().getRealPath("/");
		am = service.expexcel_khyyqkjb_report(bean,userInfo,path);//generate the excel file
		if(am.isSuccess()){
			String file_path = path+"xbxt_excel/tmp_file"+File.separator+am.getMsg();
			File file = new File(file_path);
			inputStream=new FileInputStream(file); 
			String name = "客户用邮情况简表.xls";
	    	file_name=new String(name.getBytes("GBK"), "ISO-8859-1");
	    	return "download";
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			JsonUtil.ToJson(am,response);
			return null;
		}
	}
	
	public String expexcel_custwarning_report() throws Exception {
		AjaxMsg am = new AjaxMsg();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		String path = ServletActionContext.getServletContext().getRealPath("/");
		am = service.expexcel_custwarning_report(bean,userInfo,path);//generate the excel file
		if(am.isSuccess()){
			String file_path = path+"xbxt_excel/tmp_file"+File.separator+am.getMsg();
			File file = new File(file_path);
			inputStream=new FileInputStream(file); 
			String name = "预警客户列表.xls";
	    	file_name=new String(name.getBytes("GBK"), "ISO-8859-1");
	    	return "download";
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			JsonUtil.ToJson(am,response);
			return null;
		}
	}
	
	public String init_cust_hz_report(){
		Double f = getGDFT();
		bean.setGdft(f);
		return "init_cust_hz_report";
	}
	
	private Double getGDFT(){
		return service.getGDFT();
	}
	
	public String init_mgr_visit_show(){
		return "init_mgr_visit_show";
	}
	
	public String init_img_show(){
		System.out.println("show===>"+bean.getImg_path());
		return "init_img_show";
	}
	
	public String init_overtime_page(){
		return "init_overtime_page";
	}
	
	public void merger_mobileinfo_formgr() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		AjaxMsg am =service.merger_mobileinfo_formgr(bean,userInfo);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(am,response);
	}
	public void save_time_settings() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		AjaxMsg am =service.save_time_settings(bean,userInfo);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(am,response);
	}
	public void addinto_writepage() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		AjaxMsg am =service.addinto_writepage(bean,userInfo);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(am,response);
	}
	public void removefrom_writepage() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		AjaxMsg am =service.removefrom_writepage(bean,userInfo);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(am,response);
	}
	public void update_writepage_comments() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		AjaxMsg am =service.update_writepage_comments(bean,userInfo);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(am,response);
	}
	
	public void cust_search() throws Exception {
		if(q!=null&&!"".equals(q)&&q.length()>2){
			Map<String, Object> session = ActionContext.getContext().getSession();
			UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
			List<?> rs =service.brch(q,userInfo);
			HttpServletResponse response = ServletActionContext.getResponse();
	    	JsonUtil.ToJson(rs,response);
		}
	}
	public void custmgr_search() throws Exception {
		if(q!=null&&!"".equals(q)&&q.length()>=2){
			Map<String, Object> session = ActionContext.getContext().getSession();
			UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
			List<?> rs =service.mgr_search(q,userInfo);
			HttpServletResponse response = ServletActionContext.getResponse();
	    	JsonUtil.ToJson(rs,response);
		}
	}
	public void ajax_custinfo() throws Exception {
		if(bean.getCust_id()!=null&&!"".equals(bean.getCust_id())&&bean.getQuery_time()!=null&&!"".equals(bean.getQuery_time())){
			//String[] s = bean.getQuery_time().split("-");
			HttpServletResponse response = ServletActionContext.getResponse();
			AjaxMsg am = new AjaxMsg();
			try{
				Map<String,Object> map = service.ajax_custinfo(bean.getCust_id());
				am.setSuccess(true);
				am.setBackParam(map);
			}
			catch(Exception ex){
				ex.printStackTrace();
				am.setSuccess(false);
			}
			JsonUtil.ToJson(am, response);
		}
	}
	public void getTable() throws Exception{
		if(bean.getCust_no()!=null&&!"".equals(bean.getCust_no())&&bean.getQuery_time()!=null&&!"".equals(bean.getQuery_time())){
			String[] s = bean.getQuery_time().split("-");
			HttpServletResponse response = ServletActionContext.getResponse();
			List<CustMonthUsageReport> cmur_list = new ArrayList<CustMonthUsageReport>();
			AjaxMsg am = new AjaxMsg();
			try{
				cmur_list = getCMURList(bean.getCust_no(),s[0],s[1]);
				am.setSuccess(true);
				am.setBackParam(cmur_list);
			}
			catch(Exception ex){
				ex.printStackTrace();
				am.setSuccess(false);
			}
			JsonUtil.ToJson(am, response);
		}
	}
	
	public void query_overtime_mail_detail() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		AjaxMsg am = new AjaxMsg();
		try{
			am = service.query_overtime_mail_detail(bean);
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		JsonUtil.ToJson(am, response);
	}
	
	public void save_handle_comments() throws Exception {
		if(bean.getMailnos()!=null&&!"".equals(bean.getMailnos())&&bean.getHandle_comments()!=null&&!"".equals(bean.getHandle_comments())){
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, Object> session = ActionContext.getContext().getSession();
			UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
			AjaxMsg am = new AjaxMsg();
			try{
				am = service.save_handle_comments(bean,userInfo);
			}
			catch(Exception ex){
				ex.printStackTrace();
				am.setSuccess(false);
				am.setMsg(ex.getMessage());
			}
			JsonUtil.ToJson(am, response);
		}
	}
	public void getAreaComboData() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		try{
			list_map = service.getAreaComboData();
		}
		catch(Exception ex){
			ex.printStackTrace();
			list_map = new ArrayList<Map<String,Object>>();
		}
		JsonUtil.ToJson(list_map, response);
	}
	
	@Pageable
	public void query_overtime1() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.query_overtime1(pagination,bean,userInfo);
		JsonUtil.ToJson(rs,response);
	}
	@Pageable
	public void query_overtime2() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.query_overtime2(pagination,bean,userInfo);
		JsonUtil.ToJson(rs,response);
	}
	@Pageable
	public void query() throws Exception {
		if(bean.getCust_no()!=null&&!"".equals(bean.getCust_no())&&bean.getQuery_time()!=null&&!"".equals(bean.getQuery_time())){
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, Object> session = ActionContext.getContext().getSession();
			UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
			PageUI rs = service.query(pagination,bean,userInfo);
			JsonUtil.ToJson(rs,response);
		}
	}
	@Pageable
	public void query_cust_hz_report() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.query_cust_hz_report(pagination,bean,userInfo);
		JsonUtil.ToJson(rs,response);
	}
	@Pageable
	public void query_mgr_visit_show() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.query_mgr_visit_show(pagination,bean,userInfo);
		JsonUtil.ToJson(rs,response);
	}
	@Pageable
	public void mgr_settings_query() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.mgr_settings_query(pagination,bean,userInfo);
		JsonUtil.ToJson(rs,response);
	}
	@Pageable
	public void cust_warning_query() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.cust_warning_query(pagination,bean,userInfo);
		JsonUtil.ToJson(rs,response);
	}
	@Pageable
	public void cust_warning_querywritepaper() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.cust_warning_querywritepaper(pagination,bean,userInfo);
		JsonUtil.ToJson(rs,response);
	}
	@Pageable
	public void query2() throws Exception {
		if(bean.getBrch_code()!=null&&!"".equals(bean.getBrch_code())){
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, Object> session = ActionContext.getContext().getSession();
			UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
			PageUI rs = service.query2(pagination,bean,userInfo);
			JsonUtil.ToJson(rs,response);
		}
	}
	@Pageable
	public void query5() throws Exception {
		if(bean.getBrch_code()!=null&&!"".equals(bean.getBrch_code())){
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, Object> session = ActionContext.getContext().getSession();
			UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
			PageUI rs = service.query5(pagination,bean,userInfo);
			JsonUtil.ToJson(rs,response);
		}
	}
	@Pageable
	public void query7() throws Exception {
		if(bean.getBrch_code()!=null&&!"".equals(bean.getBrch_code())){
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, Object> session = ActionContext.getContext().getSession();
			UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
			PageUI rs = service.query7(pagination,bean,userInfo);
			JsonUtil.ToJson(rs,response);
		}
	}
	@Pageable
	public void query3() throws Exception {
		if(bean.getBigcust_type()==null||"".equals(bean.getBigcust_type())){
			bean.setBigcust_type("1");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.query3(pagination,bean,userInfo);
		JsonUtil.ToJson(rs,response);
	}
	@Pageable
	public void query4() throws Exception {
		if(bean.getCust_no()!=null&&!"".equals(bean.getCust_no())&&!"null".equalsIgnoreCase(bean.getCust_no())){
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, Object> session = ActionContext.getContext().getSession();
			UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
			PageUI rs = service.query4(pagination,bean,userInfo);
			JsonUtil.ToJson(rs,response);
		}
	}
	@Pageable
	public void query_khyyqkjb() throws Exception {
		if(bean.getCust_id()!=null&&!"".equals(bean.getCust_id())){
			System.out.println("bean.getQuery_time()===>"+bean.getQuery_time());
			if(bean.getQuery_time()==null||"".equals(bean.getQuery_time())||"undefined".equals(bean.getQuery_time())||"null".equals(bean.getQuery_time())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
		        cal.setTime(new Date());
		        cal.add(Calendar.MONTH, -1);
		        bean.setQuery_time(sdf.format(cal.getTime()));
		        System.out.println("bean.getQuery_time()===>"+bean.getQuery_time());
			}
		}
		if(bean.getQuery_time()!=null&&!"".equals(bean.getQuery_time())&&!"null".equalsIgnoreCase(bean.getQuery_time())){
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, Object> session = ActionContext.getContext().getSession();
			UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
			PageUI rs = service.query_khyyqkjb(pagination,bean,userInfo);
			JsonUtil.ToJson(rs,response);
		}
	}
	@Pageable
	public void query_warningtime() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.query_warningtime(pagination,bean,userInfo);
		JsonUtil.ToJson(rs,response);
	}
	
	@Pageable
	public void settings_query() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.settings_query(pagination,bean,userInfo);
		JsonUtil.ToJson(rs,response);
	}
	@Pageable
	public void log_query() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = service.log_query(pagination,bean,userInfo);
		JsonUtil.ToJson(rs,response);
	}
	public void saveInterfaceSettings() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.saveInterfaceSettings(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
	}
	public void find_cust_warning_param() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.find_cust_warning_param(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
	}
	
	public void update_cust_warning_param() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.update_cust_warning_param(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
	}
	public void manual_run() throws Exception {
		if(bean.getQuery_time()!=null&&!"".equals(bean.getQuery_time())&&bean.getQuery_time_ed()!=null&&!"".equals(bean.getQuery_time_ed())&&bean.getQuery_num_ed()!=null&&!"".equals(bean.getQuery_num_ed())){
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, Object> session = ActionContext.getContext().getSession();
			UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
			AjaxMsg rs = service.manual_run(bean,userInfo);
	    	JsonUtil.ToJson(rs,response);
		}
	}
	public void manual_copy() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.manual_copy(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
	}
	public void get_FTParam() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.get_FTParam(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
	}
	public void set_FTParam() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.set_FTParam(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
	}
	public void deleteInterfaceSettings() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.deleteInterfaceSettings(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
	}
	public String editSettings() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.editSettings(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	public String editSettings4() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.editSettings4(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	public String editSettings7() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.editSettings7(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	public String editSettings2()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.editSettings2(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	public String dobuquan()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg rs = service.dobuquan(bean,userInfo);
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	
	public void getinfo_forsetcustinfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg am = service.getinfo_forsetcustinfo(bean,userinfo);
		JsonUtil.ToJson(am, response);
	}
	
	public void getinfo_forsetcustmgrinfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg am = service.getinfo_forsetcustmgrinfo(bean,userinfo);
		JsonUtil.ToJson(am, response);
	}
	
	public void savecustinfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg am = service.savecustinfo(bean,userinfo);
		JsonUtil.ToJson(am, response);
	}
	
	public void savecustmgrinfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo) session.get(UserInfo.USER_INFO);
		AjaxMsg am = service.savecustmgrinfo(bean,userinfo);
		JsonUtil.ToJson(am, response);
	}
	
	public void getTotalAverTime() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		AjaxMsg am = new AjaxMsg();
		try{
			map = service.getTotalAverTime(bean);
			am.setSuccess(true);
			am.setBackParam(map);
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
		}
		JsonUtil.ToJson(am, response);
	}
	
	public void brch() throws Exception{
		ValueStack vs = ActionContext.getContext().getValueStack();//获取值栈
		ParamsUtil p =new ParamsUtil(vs);//参数调用对象
		/** 
		 * mode:normal 缺省值为只展示正常的
		 * mode:all 展示全部节点包括被删除被隐藏的机构
		 * */
		List<TreeNode> tree=service.getBrchTree(node,type,"normal",p);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(tree,response);
	}
	public void brch2() throws Exception{
		ValueStack vs = ActionContext.getContext().getValueStack();//获取值栈
		ParamsUtil p =new ParamsUtil(vs);//参数调用对象
		/** 
		 * mode:normal 缺省值为只展示正常的
		 * mode:all 展示全部节点包括被删除被隐藏的机构
		 * */
		List<TreeNode> tree=service.getBrchTree2(node,type,"normal",p);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(tree,response);
	}
	public String expandToBrch() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo=(UserInfo)session.get(UserInfo.USER_INFO);
		ValueStack vs = ActionContext.getContext().getValueStack();
		ParamsUtil p =new ParamsUtil(vs);
		Stack<Brch> expand= service.getExpandBrchStack(expnode,userInfo);
		List<TreeNode> tree=service.recursionBrch(node, type, mode, p, expand);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(tree,response);
		return null;
	}
	
	private List<CustMonthUsageReport> getCMURList(String cust_id,String year,String month) throws Exception {
		return service.getCMUR(cust_id,year,month);
	}
	
	
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getExpnode() {
		return expnode;
	}
	public void setExpnode(String expnode) {
		this.expnode = expnode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public XBBean getBean() {
		return bean;
	}
	public void setBean(XBBean bean) {
		this.bean = bean;
	}
	public XBService getService() {
		return service;
	}
	public void setService(XBService service) {
		this.service = service;
	}
	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
	

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public XBBean getModel() {
		return bean;
	}
}
