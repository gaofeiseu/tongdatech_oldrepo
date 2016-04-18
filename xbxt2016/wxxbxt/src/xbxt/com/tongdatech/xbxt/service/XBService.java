package com.tongdatech.xbxt.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import com.tongdatech.sys.bean.Brch;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
import com.tongdatech.sys.pojo.TreeNode;
import com.tongdatech.sys.util.ParamsUtil;
import com.tongdatech.xbxt.bean.CustHZReport;
import com.tongdatech.xbxt.bean.CustMonthUsageReport;
import com.tongdatech.xbxt.bean.Khyyqkjb;
import com.tongdatech.xbxt.bean.TotalAverTime;
import com.tongdatech.xbxt.bean.XBBean;
import com.tongdatech.xbxt.dao.XBDao;
import com.tongdatech.xbxt.utils.ExcelUtils;

public class XBService {
	private XBDao dao = new XBDao();

	public List<CustMonthUsageReport> getCMUR(String cust_id,String year,String month) throws Exception {
		return dao.getCMUR(cust_id,year,month);
	}

	public PageUI query(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query(pagination,bean,userInfo);
	}

	public Map<String, Object> getTotalAverTime(XBBean bean) throws Exception {
		return dao.getTotalAverTime(bean);
	}

	public List<TreeNode> getBrchTree(String node, String type, String mode, ParamsUtil p) throws Exception {
		List<TreeNode> rs =new  ArrayList<TreeNode>();
        if("root".equals(type)){
        	getBrchRoot(node, type, mode, p, rs);
        	return rs; 
        }else if("one".equals(type)){
        	TreeNode root=getBrchRoot(node, type, mode, p, rs);
        	List<TreeNode> children =new  ArrayList<TreeNode>();
        	getBrchChildren(root.getId(), type, mode, p,children);
        	root.setChildren(children);
        	return rs; 
        }else{
        	getBrchChildren(node, type, mode, p,rs);
        	return rs;
        }
	}
	public List<TreeNode> getBrchTree2(String node, String type, String mode, ParamsUtil p) throws Exception {
		List<TreeNode> rs =new  ArrayList<TreeNode>();
        if("root".equals(type)){
        	getBrchRoot2(node, type, mode, p, rs);
        	return rs; 
        }else if("one".equals(type)){
        	TreeNode root=getBrchRoot2(node, type, mode, p, rs);
        	List<TreeNode> children =new  ArrayList<TreeNode>();
        	getBrchChildren2(root.getId(), type, mode, p,children);
        	root.setChildren(children);
        	return rs; 
        }else{
        	getBrchChildren2(node, type, mode, p,rs);
        	return rs;
        }
	}
	private TreeNode getBrchRoot2(String node, String type, String mode, ParamsUtil p,List<TreeNode> rs) throws Exception{
		Brch brch = dao.getBrchRoot2(node);
		if(brch==null)return null;
		brch.setBrch_name(brch.getBrch_name()+p.getValue("BRCH_FLAG", brch.getBrch_flag()));
		TreeNode treeNode = brch.TreeTrans();
		rs.add(treeNode);
		return treeNode;
	}
	private void getBrchChildren2(String node, String type, String mode, ParamsUtil p,List<TreeNode> rs) throws Exception{
		List<Brch> children=dao.getBrchChildren2(node,mode);
    	for(Brch brch:children){
    		brch.setBrch_name(brch.getBrch_name()+p.getValue("BRCH_FLAG", brch.getBrch_flag()));
    		rs.add(brch.TreeTrans());
    	}
	}
	private TreeNode getBrchRoot(String node, String type, String mode, ParamsUtil p,List<TreeNode> rs) throws Exception{
		Brch brch = dao.getBrchRoot(node);
		if(brch==null)return null;
		brch.setBrch_name(brch.getBrch_name()+p.getValue("BRCH_FLAG", brch.getBrch_flag()));
		TreeNode treeNode = brch.TreeTrans();
		rs.add(treeNode);
		return treeNode;
	}
	private void getBrchChildren (String node, String type, String mode, ParamsUtil p,List<TreeNode> rs) throws Exception{
		List<Brch> children=dao.getBrchChildren(node,mode);
    	for(Brch brch:children){
    		brch.setBrch_name(brch.getBrch_name()+p.getValue("BRCH_FLAG", brch.getBrch_flag()));
    		rs.add(brch.TreeTrans());
    	}
	}
	public Stack<Brch> getExpandBrchStack(String node,UserInfo userInfo) throws Exception{
		Stack<Brch> rs = new Stack<Brch>();
		List<Brch> brchs = dao.getParents2(node);
		for(Brch brch:brchs){
			rs.push(brch);
			if(brch.getBrch_no()==userInfo.getBrch_no())break; //超出权限的机构不入栈
			
		}
		return rs;
	}
	public List<TreeNode>  recursionBrch(String node, String type, String mode,ParamsUtil p,Stack<Brch> expand) throws Exception{
		List<TreeNode> rs =new ArrayList<TreeNode>();
		List<Brch> brchs=dao.getBrchChildren2(node,mode);
		for(Brch b:brchs){
			b.setBrch_name(b.getBrch_name()+p.getValue("BRCH_FLAG", b.getBrch_flag()));
			TreeNode tree = b.TreeTrans();
			
			List<TreeNode>  children= null;
			if(expand!=null&&!expand.isEmpty()&&expand.peek().getBrch_no()==b.getBrch_no()){
				expand.pop();
				children=recursionBrch(String.valueOf(b.getBrch_no()),type,mode,p,expand);
			}
			if(expand==null){
				children=recursionBrch(String.valueOf(b.getBrch_no()),type,mode,p,null);//递归调用
			}		
			
			if(children!=null&&children.size()!=0){
				tree.setChildren(children);
			}
			rs.add(tree);
		}
		return rs;
		
	}
	public PageUI query2(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query2(pagination,bean,userInfo);
	}

	public AjaxMsg editSettings(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.editSettings(bean,userInfo);
	}

	public PageUI query3(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query3(pagination,bean,userInfo);
	}

	public PageUI query4(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query4(pagination,bean,userInfo);
	}

	public AjaxMsg editSettings2(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.editSettings2(bean,userInfo);
	}

	public AjaxMsg dobuquan(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.dobuquan(bean,userInfo);
	}

	public PageUI settings_query(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.settings_query(pagination,bean,userInfo);
	}

	public AjaxMsg saveInterfaceSettings(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.saveInterfaceSettings(bean,userInfo);
	}

	public AjaxMsg deleteInterfaceSettings(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.deleteInterfaceSettings(bean,userInfo);
	}

	public PageUI log_query(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.log_query(pagination,bean,userInfo);
	}

	public AjaxMsg manual_run(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.manual_run(bean,userInfo);
	}

	public AjaxMsg manual_copy(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.manual_copy(bean,userInfo);
	}

	public PageUI query_khyyqkjb(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query_khyyqkjb(pagination,bean,userInfo);
	}

	public Map<String, Object> ajax_custinfo(String cust_no) throws Exception {
		return dao.ajax_custinfo(cust_no);
	}

	public List<?> brch(String q, UserInfo userInfo) throws Exception {
		return dao.brch(q,userInfo);
	}

	public AjaxMsg getinfo_forsetcustinfo(XBBean bean, UserInfo userinfo) throws Exception {
		return dao.getinfo_forsetcustinfo(bean,userinfo);
	}

	public AjaxMsg savecustinfo(XBBean bean, UserInfo userinfo) throws Exception {
		return dao.savecustinfo(bean,userinfo);
	}

	public List<?> mgr_search(String q, UserInfo userInfo) throws Exception {
		return dao.mgr_search(q,userInfo);
	}

	public AjaxMsg getinfo_forsetcustmgrinfo(XBBean bean, UserInfo userinfo) throws Exception {
		return dao.getinfo_forsetcustmgrinfo(bean,userinfo);
	}

	public AjaxMsg savecustmgrinfo(XBBean bean, UserInfo userinfo) throws Exception {
		return dao.savecustmgrinfo(bean,userinfo);
	}

	public PageUI query5(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query5(pagination,bean,userInfo);
	}

	public AjaxMsg editSettings4(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.editSettings4(bean,userInfo);
	}

	public PageUI cust_warning_query(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.cust_warning_query(pagination,bean,userInfo);
	}

	public AjaxMsg find_cust_warning_param(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.find_cust_warning_param(bean,userInfo);
	}

	public AjaxMsg update_cust_warning_param(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.update_cust_warning_param(bean,userInfo);
	}

	public PageUI query7(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query7(pagination,bean,userInfo);
	}

	public AjaxMsg editSettings7(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.editSettings7(bean,userInfo);
	}

	public AjaxMsg addinto_writepage(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.addinto_writepage(bean,userInfo);
	}

	public PageUI cust_warning_querywritepaper(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.cust_warning_querywritepaper(pagination,bean,userInfo);
	}

	public AjaxMsg removefrom_writepage(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.removefrom_writepage(bean,userInfo);
	}

	public AjaxMsg update_writepage_comments(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.update_writepage_comments(bean,userInfo);
	}

	public PageUI mgr_settings_query(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.mgr_settings_query(pagination,bean,userInfo);
	}

	public AjaxMsg merger_mobileinfo_formgr(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.merger_mobileinfo_formgr(bean,userInfo);
	}

	public PageUI query_cust_hz_report(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query_cust_hz_report(pagination,bean,userInfo);
	}

	public Double getGDFT() {
		return dao.getGDFT();
	}

	public PageUI query_mgr_visit_show(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query_mgr_visit_show(pagination,bean,userInfo);
	}

	public AjaxMsg expexcel_hz_report(XBBean bean, UserInfo userInfo,String path) {
		AjaxMsg am = new AjaxMsg();
		
		am = dao.expexcel_hz_report(bean,userInfo);
		if(am.isSuccess()){
			@SuppressWarnings("unchecked")
			List<CustHZReport> list_data = (List<CustHZReport>) am.getBackParam();
			if(list_data.size()>0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String exp_date = sdf.format(new Date());
				Map<String,Object> data_map = new HashMap<String,Object>();
				data_map.put("list_data", list_data);
				data_map.put("date", exp_date);
				data_map.put("rownum", list_data.size());
				System.out.println("list_data.size():"+list_data.size());
				String save_path = path+"xbxt_excel/tmp_file";
				String excelfilename = UUID.randomUUID().toString()+".xls";
				ExcelUtils.createExcel(data_map, "hz_report_template.ftl", save_path, excelfilename);
				String file_path = save_path+File.separator+excelfilename;
				if((new File(file_path)).exists()){
					am.setSuccess(true);
					am.setMsg(excelfilename);
				}else{
					am.setSuccess(false);
					am.setMsg("excel文件生成失败！");
				}
			}else{
				am.setSuccess(false);
				am.setMsg("获取到的excel数据集是空的");
			}
		}
		return am;
	}

	public AjaxMsg expexcel_cmur_report(XBBean bean, UserInfo userInfo, String path) {
		AjaxMsg am = new AjaxMsg();
		
		am = dao.expexcel_cmur_report(bean,userInfo);
		if(am.isSuccess()){
			@SuppressWarnings("unchecked")
			List<CustHZReport> list_data = (List<CustHZReport>) am.getBackParam();
			if(list_data.size()>0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String exp_date = sdf.format(new Date());
				Map<String,Object> data_map = new HashMap<String,Object>();
				data_map.put("list_data", list_data);
				data_map.put("date", exp_date);
				data_map.put("rownum", list_data.size());
				System.out.println("list_data.size():"+list_data.size());
				String save_path = path+"xbxt_excel/tmp_file";
				String excelfilename = UUID.randomUUID().toString()+".xls";
				System.out.println(excelfilename);
				ExcelUtils.createExcel(data_map, "cmur_report_template.ftl", save_path, excelfilename);
				String file_path = save_path+File.separator+excelfilename;
				if((new File(file_path)).exists()){
					am.setSuccess(true);
					am.setMsg(excelfilename);
				}else{
					am.setSuccess(false);
					am.setMsg("excel文件生成失败！");
				}
			}else{
				am.setSuccess(false);
				am.setMsg("获取到的excel数据集是空的");
			}
		}
		return am;
	}

	public AjaxMsg expexcel_totalavertime_report(XBBean bean, UserInfo userInfo, String path) {
		AjaxMsg am = new AjaxMsg();
		
		am = dao.expexcel_totalavertime_report(bean,userInfo);
		if(am.isSuccess()){
			@SuppressWarnings("unchecked")
			List<TotalAverTime> list_tat = (List<TotalAverTime>) ((Map<String,Object>)am.getBackParam()).get("list_tat");
			@SuppressWarnings("unchecked")
			List<String> list_area = (List<String>) ((Map<String,Object>)am.getBackParam()).get("list_area");
			if(list_tat.size()>0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String exp_date = sdf.format(new Date());
				
				List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
				for(String area : list_area){
					Map<String,Object> m = new HashMap<String,Object>();
					m.put("area", area);
					List<TotalAverTime> list_t = new ArrayList<TotalAverTime>();
					for(TotalAverTime tat :list_tat){
						if(area.equals(tat.getArea())){
							list_t.add(tat);
						}
					}
					m.put("list_tat", list_t);
					m.put("area_rownum", list_t.size()*4);
					list_map.add(m);
				}
				
				int rownum = list_tat.size()*4;
				Map<String,Object> data_map = new HashMap<String,Object>();
				data_map.put("list_data", list_map);
				data_map.put("date", exp_date);
				data_map.put("rownum", rownum);
				System.out.println("list_data.size():"+rownum);
				
				String save_path = path+"xbxt_excel/tmp_file";
				String excelfilename = UUID.randomUUID().toString()+".xls";
				System.out.println(excelfilename);
				ExcelUtils.createExcel(data_map, "totalavertime_report_template.ftl", save_path, excelfilename);
				String file_path = save_path+File.separator+excelfilename;
				if((new File(file_path)).exists()){
					am.setSuccess(true);
					am.setMsg(excelfilename);
				}else{
					am.setSuccess(false);
					am.setMsg("excel文件生成失败！");
				}
			}else{
				am.setSuccess(false);
				am.setMsg("获取到的excel数据集是空的");
			}
		}
		return am;
	}

	@SuppressWarnings("unchecked")
	public AjaxMsg expexcel_khyyqkjb_report(XBBean bean, UserInfo userInfo, String path) {
		AjaxMsg am = new AjaxMsg();
		
		am = dao.expexcel_khyyqkjb_report(bean,userInfo);
		if(am.isSuccess()){
			List<Khyyqkjb> list = new ArrayList<Khyyqkjb>();
			list = (List<Khyyqkjb>) am.getBackParam();
			if(list.size()>0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String exp_date = sdf.format(new Date());
				
				int rownum = list.size();
				Map<String,Object> data_map = new HashMap<String,Object>();
				data_map.put("list_data", list);
				data_map.put("date", exp_date);
				data_map.put("rownum", rownum);
				System.out.println("list_data.size():"+rownum);
				
				String save_path = path+"xbxt_excel/tmp_file";
				String excelfilename = UUID.randomUUID().toString()+".xls";
				System.out.println(excelfilename);
				ExcelUtils.createExcel(data_map, "khyyqkjb_report_template.ftl", save_path, excelfilename);
				String file_path = save_path+File.separator+excelfilename;
				if((new File(file_path)).exists()){
					am.setSuccess(true);
					am.setMsg(excelfilename);
				}else{
					am.setSuccess(false);
					am.setMsg("excel文件生成失败！");
				}
			}else{
				am.setSuccess(false);
				am.setMsg("获取到的excel数据集是空的");
			}
		}
		return am;
	}

	@SuppressWarnings("unchecked")
	public AjaxMsg expexcel_custwarning_report(XBBean bean, UserInfo userInfo, String path) {
		AjaxMsg am = new AjaxMsg();
		
		am = dao.expexcel_custwarning_report(bean,userInfo);
		if(am.isSuccess()){
			List<Khyyqkjb> list = new ArrayList<Khyyqkjb>();
			list = (List<Khyyqkjb>) am.getBackParam();
			if(list.size()>0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String exp_date = sdf.format(new Date());
				
				int rownum = list.size();
				Map<String,Object> data_map = new HashMap<String,Object>();
				data_map.put("list_data", list);
				data_map.put("date", exp_date);
				data_map.put("rownum", rownum);
				System.out.println("list_data.size():"+rownum);
				
				String save_path = path+"xbxt_excel/tmp_file";
				String excelfilename = UUID.randomUUID().toString()+".xls";
				System.out.println(excelfilename);
				ExcelUtils.createExcel(data_map, "custwarning_report_template.ftl", save_path, excelfilename);
				String file_path = save_path+File.separator+excelfilename;
				if((new File(file_path)).exists()){
					am.setSuccess(true);
					am.setMsg(excelfilename);
				}else{
					am.setSuccess(false);
					am.setMsg("excel文件生成失败！");
				}
			}else{
				am.setSuccess(false);
				am.setMsg("获取到的excel数据集是空的");
			}
		}
		return am;
	}

	public AjaxMsg get_FTParam(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.get_FTParam(bean,userInfo);
	}

	public AjaxMsg set_FTParam(XBBean bean, UserInfo userInfo)throws Exception {
		return dao.set_FTParam(bean,userInfo);
	}

	public PageUI query_warningtime(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query_warningtime(pagination,bean,userInfo);
	}

	public AjaxMsg save_time_settings(XBBean bean, UserInfo userInfo) throws Exception {
		return dao.save_time_settings(bean,userInfo);
	}

	public PageUI query_overtime1(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query_overtime1(pagination,bean,userInfo);
	}

	public AjaxMsg query_overtime_mail_detail(XBBean bean) throws Exception{
		return dao.query_overtime_mail_detail(bean);
	}

	public AjaxMsg save_handle_comments(XBBean bean,UserInfo userInfo) {
		return dao.save_handle_comments(bean,userInfo);
	}

	public List<Map<String, Object>> getAreaComboData() {
		return dao.getAreaComboData();
	}

	public PageUI query_overtime2(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		return dao.query_overtime2(pagination,bean,userInfo);
	}
	
}
