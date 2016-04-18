package com.tongdatech.xbxt.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.Brch;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
import com.tongdatech.sys.util.SecurityUtil;
import com.tongdatech.xbxt.bean.BigCustInfo;
import com.tongdatech.xbxt.bean.CopyBean;
import com.tongdatech.xbxt.bean.CustHZReport;
import com.tongdatech.xbxt.bean.CustMonthUsageReport;
import com.tongdatech.xbxt.bean.CustWarning;
import com.tongdatech.xbxt.bean.CustWarningWritePaper;
import com.tongdatech.xbxt.bean.DS_GNXB_JSCB_BJ;
import com.tongdatech.xbxt.bean.Gnxb_htje;
import com.tongdatech.xbxt.bean.InterfaceMailInfo;
import com.tongdatech.xbxt.bean.Khyyqkjb;
import com.tongdatech.xbxt.bean.LogBean;
import com.tongdatech.xbxt.bean.MgrSettingsQuery;
import com.tongdatech.xbxt.bean.MgrVisitShowQuery;
import com.tongdatech.xbxt.bean.Overtime;
import com.tongdatech.xbxt.bean.OvertimeDetail;
import com.tongdatech.xbxt.bean.ParamBean;
import com.tongdatech.xbxt.bean.RealFeeQuery;
import com.tongdatech.xbxt.bean.StandardFeeQuery;
import com.tongdatech.xbxt.bean.TotalAverTime;
import com.tongdatech.xbxt.bean.TotalAverTimeWarningTime;
import com.tongdatech.xbxt.bean.XBBean;
import com.tongdatech.xbxt.bean.XB_Settings;
import com.tongdatech.xbxt.utils.ThreadFactory;
import com.tongdatech.xbxt.utils.XBUtils;

public class XBDao extends BaseDao{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(XBDao.class);
	private static final String[] provs = new String[]{"江苏","浙江","上海","北京","广东","天津","福建","安徽","山东","河南",
														"山西","湖南","湖北","江西","河北","陕西","四川",
														"重庆","辽宁","吉林","黑龙江","广西","贵州","云南","海南","甘肃","内蒙古","宁夏","青海","新疆","西藏"};

	public List<CustMonthUsageReport> getCMUR(String cust_id, String year, String month) throws Exception {
		//String sql = "select * from t_xb_cmur where cust_id='"+cust_id+"' and year='"+year+"' and month='"+month+"' order by sn ";
		//System.out.println(sql);
		String sql = "select a.sn,a.destination,a.send_num,a.fee,round(a.mail_aver_time/24,1) as mail_aver_time, "+
				" round(a.total_aver_time/24,1) as total_aver_time," +
				"(case when a.standard_fee=0 then 0.0 else round((a.fee/a.standard_fee)*100,1) end) as discount_persent" +
				" from t_xb_cmur a,gnxb_area_province b  "+
				" where instr(a.destination,b.province)>0 and a.cust_id='"+cust_id+"' and a.year='"+year+"' "+
				" and a.month='"+month+"' order by b.sn";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<CustMonthUsageReport> list = db.query(sql,CustMonthUsageReport.class);
		return list;
	}

	public PageUI query(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String[] s = bean.getQuery_time().split("-");
//		String sql = "select * from t_xb_cmur where cust_id='"+bean.getCust_no()+"' and year='"+s[0]+"' and month='"+s[1]+"' order by sn ";
		String sql = "select a.sn,a.destination,a.send_num,a.fee,round(a.mail_aver_time/24,1) as mail_aver_time, "+
				" round(a.total_aver_time/24,1) as total_aver_time," +
				"(case when a.standard_fee=0 then 0.0 else round((a.fee/a.standard_fee)*100,1) end) as discount_persent" +
				" from t_xb_cmur a,gnxb_area_province b  "+
				" where instr(a.destination,b.province)>0 and a.cust_id='"+bean.getCust_no()+"' and a.year='"+s[0]+"' "+
				" and a.month='"+s[1]+"' order by b.sn";
		System.out.println(sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, CustMonthUsageReport.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getTotalAverTime(XBBean bean) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String sql = "";
		String[] s = bean.getQuery_time().split("-");
		String year_month = s[0]+s[1];//"201511";
//		sql = "select a.sn,a.area,a.province,round(a.province_aver_time/24,1) as province_aver_time, "+
//				" round(a.center_aver_time/24,1) as center_aver_time, "+
//				" round(a.city_aver_time/24,1) as city_aver_time, "+
//				" round(a.countryside_aver_time/24,1) as countryside_aver_time,a.year_month "+
//				" from t_xb_zpjsx a,gnxb_area_province b  "+
//				" where a.year_month='"+year_month+"' and a.province=b.province order by b.sn";
		sql = "select a.sn,a.area,a.province,round(a.province_aver_time/24,1) as province_aver_time, "+
				" round(a.center_aver_time/24,1) as center_aver_time, "+
				" round(a.city_aver_time/24,1) as city_aver_time, "+
				" round(a.countryside_aver_time/24,1) as countryside_aver_time,a.year_month," +
				" c.center_time,c.city_time,c.town_time "+
				" from t_xb_zpjsx a,gnxb_area_province b,T_XBXT_TAT_WARNINGTIME c  "+
				" where a.year_month='"+year_month+"' and a.province=b.province and a.province=c.destination order by b.sn";
		System.out.println("sql:"+sql);
		List<TotalAverTime> list_tat = new ArrayList<TotalAverTime>();
		list_tat = db.query(sql,TotalAverTime.class);
		map.put("list_tat", list_tat);
		List<String> list_area = new ArrayList<String>();
		for(TotalAverTime tat : list_tat){
			boolean if_contain = false;
			for(String b : list_area){
				if(b.equals(tat.getArea())){
					if_contain = true;
					break;
				}
			}
			if(!if_contain){
				list_area.add(tat.getArea());
			}
		}
		map.put("list_area", list_area);
		return map;
	}

	public Brch getBrchRoot(String node) throws Exception {
		String sql = "select brch_code as brch_no,brch_name,brch_code_up as brch_up from gnxb_sj_brch where brch_code="+node+" and brch_mode<=3";
		return (Brch) db.queryOneLine(sql, Brch.class);
	}
	public Brch getBrchRoot2(String node) throws Exception {
		String sql = "select sn as brch_no,dept_name as brch_name,dept_no as brch_code,parent_sn as brch_up from t_dept where sn="+node+" ";
		return (Brch) db.queryOneLine(sql, Brch.class);
	}

	@SuppressWarnings("unchecked")
	public List<Brch> getBrchChildren(String node, String mode) throws Exception {
		String sql = "select brch_code as brch_no,brch_name,brch_code_up as brch_up from gnxb_sj_brch where brch_code_up="+node+" and brch_mode<=3";
		return db.query(sql, Brch.class);
	}
	@SuppressWarnings("unchecked")
	public List<Brch> getBrchChildren2(String node, String mode) throws Exception {
		String sql = "select sn as brch_no,dept_name as brch_name,dept_no as brch_code,parent_sn as brch_up from t_dept where parent_sn="+node+" ";
		return db.query(sql, Brch.class);
	}
	public PageUI query2(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "select * from ds_gnxb_jscb_bj where brch_code='"+bean.getBrch_code()+"'";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		@SuppressWarnings("unchecked")
		List<DS_GNXB_JSCB_BJ> list_d = db.query(listsql, DS_GNXB_JSCB_BJ.class);
		for(int i=0;i<list_d.size();i++){
			if(list_d.get(i).getV_jdjsmgm()!=null&&list_d.get(i).getV_jdjsmgm().indexOf("黑龙")>-1){
				list_d.get(i).setV_jdjsmgm("黑龙江");
			}
		}
		rs.setRows(list_d);
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg editSettings(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		String sql = "update ds_gnxb_jscb_bj set hcys="+bean.getHcys()+" ,qcys="+bean.getQcys()+" ,nbcl="+bean.getNbcl()+" ,jz="+bean.getJz()+" ,td="+bean.getTd()+" where brch_code='"+bean.getBrch_code()+"' and v_jdjsmgm='"+bean.getV_jdjsmgm()+"'";
		try{
			db.connect();
			db.startTransaction();
			int r = db.update(sql);
			if(r==1){
				db.commit();
				rs.setSuccess(true);
				rs.setMsg("更新成功！");
			}else{
				db.rollback();
				rs.setSuccess(false);
				rs.setMsg("更新失败！");
			}
		}
		catch(Exception ex){
			db.rollback();
			rs.setSuccess(false);
			rs.setMsg("更新失败！"+ex.getMessage());
		}
		finally{
			db.endTransaction();
			db.disconnect();
		}
		return rs;
	}

	public PageUI query3(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "";
		if(bean.getBigcustnameorbigcustno()!=null&&!"".equals(bean.getBigcustnameorbigcustno().trim())){
			try{
				long bigcustno = Long.parseLong(bean.getBigcustnameorbigcustno());
//				sql = "select f.*,decode(f.flag,1,'存在',0,'缺失') as flag_str  "
//						+" from (select a.SSKHDM,a.JJRDWMC,(case when exists(select 1 from gnxb_htje e where e.cust_id=a.sskhdm) "
//						+" then 1 else 0 end) as flag  from (select sskhdm,JJRDWMC from gnxb_ord_peo_prtcin where SSKHDM='"+bigcustno+"' group by sskhdm,JJRDWMC) a) f";
				sql = "select f.*,(case f.flag when 1 then '存在' when 0 then '缺失' else '错误数据' end) as flag_str "
						+" from (select '"+bigcustno+"' as SSKHDM,b.bigcustname as JJRDWMC,(case when exists(select 1 from gnxb_htje e where e.cust_id='"+bigcustno+"') "
						+" then 1 else 0 end) as flag  from t_cdp b where b.bigcustno='"+bigcustno+"' "
						+" and exists(select 1 from gnxb_ord_peo_prtcin where sskhdm='"+bigcustno+"')) f";
			}catch(Exception ex){
				String bigcustname = bean.getBigcustnameorbigcustno();
//				sql = "select f.*,decode(f.flag,1,'存在',0,'缺失') as flag_str  "
//						+" from (select a.SSKHDM,a.JJRDWMC,(case when exists(select 1 from gnxb_htje e where e.cust_id=a.sskhdm) "
//						+" then 1 else 0 end) as flag  from (select sskhdm,JJRDWMC from gnxb_ord_peo_prtcin where instr(JJRDWMC,'"+bigcustname+"')>0 group by sskhdm,JJRDWMC) a) f";
				sql = "select f.*,(case f.flag when 1 then '存在' when 0 then '缺失' else '错误数据' end) as flag_str "
						+" from (select a.SSKHDM,a.JJRDWMC,(case when exists(select 1 from gnxb_htje e where e.cust_id=a.sskhdm) then 1 else 0 end) as flag "
						+" from (select b.bigcustno as sskhdm,b.bigcustname as jjrdwmc from t_cdp b where instr(b.bigcustname,'"+bigcustname+"')>0 "
						+" and exists(select 1 from gnxb_ord_peo_prtcin where sskhdm=b.bigcustno)) a) f";
			}
		}else{
			if("1".equals(bean.getBigcust_type())){
//				sql = "select f.*,decode(f.flag,1,'存在',0,'缺失') as flag_str "
//						+" from (select a.SSKHDM,a.JJRDWMC,(case when exists(select 1 from gnxb_htje e where e.cust_id=a.sskhdm) then 1 else 0 end) as flag "
//						+" from (select sskhdm,JJRDWMC from gnxb_ord_peo_prtcin group by sskhdm,JJRDWMC) a) f";
				sql = "select f.*,(case f.flag when 1 then '存在' when 0 then '缺失' else '错误数据' end) as flag_str "
						+" from (select a.SSKHDM,a.JJRDWMC,(case when exists(select 1 from gnxb_htje e where e.cust_id=a.sskhdm) then 1 else 0 end) as flag "
						+" from (select b.bigcustno as sskhdm,b.bigcustname as jjrdwmc from t_cdp b where exists(select 1 from gnxb_ord_peo_prtcin where sskhdm=b.bigcustno)) a ) f";
			}else{
//				sql = "select f.*,'缺失' as flag_str from (select a.SSKHDM,a.JJRDWMC "
//						+" from (select t.sskhdm,t.JJRDWMC from gnxb_ord_peo_prtcin t "
//						+" where not exists(select 1 from gnxb_htje e where e.cust_id=t.sskhdm) group by t.sskhdm,t.JJRDWMC) a) f";
				sql = "select f.*,'缺失' as flag_str from (select a.SSKHDM,a.JJRDWMC "
						+" from (select b.bigcustno as sskhdm,b.bigcustname as jjrdwmc from t_cdp b where exists(select 1 from gnxb_ord_peo_prtcin where sskhdm=b.bigcustno) "
						+" and not exists(select 1 from gnxb_htje e where e.cust_id=b.bigcustno)) a ) f";
			}
		}
		
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, BigCustInfo.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public PageUI query4(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "select * from gnxb_htje where cust_id='"+bean.getCust_no()+"'";
		System.out.println("query4:"+sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, Gnxb_htje.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg editSettings2(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		String sql = "update gnxb_htje set fee="+bean.getFee()+" where sn in ("+bean.getParam_value()+") ";
		try{
			db.connect();
			db.startTransaction();
			int r = db.update(sql);
			if(r>0){
				db.commit();
				rs.setSuccess(true);
				rs.setMsg("更新成功！");
			}else{
				db.rollback();
				rs.setSuccess(false);
				rs.setMsg("更新失败！");
			}
		}
		catch(Exception ex){
			db.rollback();
			rs.setSuccess(false);
			rs.setMsg("更新失败！"+ex.getMessage());
		}
		finally{
			db.endTransaction();
			db.disconnect();
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	public AjaxMsg dobuquan(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String provinces_str = bean.getProvinces_str();
		String[] provinces = provinces_str.split(",");
		List<String> wait_insert_province = new ArrayList<String>();
		for(String s : provs){
			boolean if_contains = false;
			for(String a : provinces){
				if(s.equals(a)){
					if_contains = true;
					break;
				}
			}
			if(!if_contains){
				wait_insert_province.add(s);
			}
		}
		List<Gnxb_htje> list_m = new ArrayList<Gnxb_htje>();
		list_m = db.query("select * from gnxb_htje where cust_id='"+bean.getCust_id()+"'",Gnxb_htje.class);
		Gnxb_htje b = null;
		if(list_m.size()<31){
			if(list_m.size()>0){
				b = list_m.get(0);
			}
			try{
				db.connect();
				db.startTransaction();
				String sql = "";
				if(b!=null&&ifNotEmptyAndNotNull(b.getCust_name())&&ifNotEmptyAndNotNull(b.getBind_dept())&&ifNotEmptyAndNotNull(b.getIndustry())&&ifNotEmptyAndNotNull(b.getCust_id())){
					int i = 0;
					for(String p : wait_insert_province){
						sql = "insert into gnxb_htje (sn,cust_name,province,fee,bind_dept,industry,cust_id) " +
								" values (SEQ_GNXB_HTJE.nextval,'"+b.getCust_name()+"','"+p+"',0,'"+b.getBind_dept()+"','"+b.getIndustry()+"','"+b.getCust_id()+"')";
						i += db.insert(sql);
					}
					if(i==wait_insert_province.size()){
						am.setSuccess(true);
						am.setMsg("补全成功！完全补全"+String.valueOf(i)+"条数据");
						db.commit();
					}else{
						am.setSuccess(false);
						am.setMsg("补全失败！");
						db.rollback();
					}
				}else{
					int i = 0;
					for(String p : wait_insert_province){
						sql = "insert into gnxb_htje (sn,cust_name,province,fee,bind_dept,industry,cust_id) " +
								" values (SEQ_GNXB_HTJE.nextval,'"+bean.getCust_name()+"','"+p+"',0,'','','"+bean.getCust_id()+"')";
						i += db.insert(sql);
					}
					if(i==wait_insert_province.size()){
						am.setSuccess(true);
						am.setMsg("补全成功！初步补全"+String.valueOf(i)+"条数据");
						db.commit();
					}else{
						am.setSuccess(false);
						am.setMsg("补全失败！");
						db.rollback();
					}
				}
			}
			catch(Exception ex){
				am.setSuccess(false);
				am.setMsg("补全失败！（"+ex.getMessage()+"）");
				db.rollback();
			}
			finally{
				db.endTransaction();
				db.disconnect();
			}
		}else{
			am.setSuccess(false);
			am.setMsg("已经不需要进行补全！");
		}
		return am;
	}
	
	private boolean ifNotEmptyAndNotNull(String s){
		return (s!=null&&!"".equals(s.trim())&&!"null".equalsIgnoreCase(s));
	}

	public PageUI settings_query(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "select sn,param_name,param_value,param_comments,param_flag,decode(param_flag,'1','正常使用','0','备用') as param_flag_str from t_xb_sys_settings order by param_name,sn ";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, XB_Settings.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg saveInterfaceSettings(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		if(bean.getSn()!=null&&bean.getSn()!=0){
			String sql = "update t_xb_sys_settings set param_name='"+bean.getParam_name()+"',param_value='"+bean.getParam_value()
					+"',param_comments='"+bean.getParam_comments()+"',param_flag='"+bean.getParam_flag()+"' where sn="+bean.getSn();
			try{
				db.connect();
				db.startTransaction();
				int i = 0;
				i = db.update(sql);
				if(i==1){
					am.setSuccess(true);
					am.setMsg("修改成功！");
					db.commit();
				}else{
					am.setSuccess(false);
					am.setMsg("修改失败！");
					db.rollback();
				}
			}
			catch(Exception ex){
				am.setSuccess(false);
				am.setMsg("修改失败！");
				db.rollback();
			}
			finally{
				db.endTransaction();
				db.disconnect();
			}
		}else{
			String sql = "insert into t_xb_sys_settings (sn,param_name,param_value,param_comments,param_flag) " +
					"values (SEQ_T_XB_SYS_SETTINGS.nextval,'"+bean.getParam_name()+"','"+bean.getParam_value()+"','"+bean.getParam_comments()+"','"+bean.getParam_flag()+"')";
			int i = 0;
			i = db.insert(sql);
			if(i==1){
				am.setSuccess(true);
				am.setMsg("新增成功！");
			}else{
				am.setSuccess(false);
				am.setMsg("新增失败！");
			}
		}
		return am;
	}

	public AjaxMsg deleteInterfaceSettings(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String sql = "delete from t_xb_sys_settings where sn="+bean.getSn();
		try{
			db.connect();
			db.startTransaction();
			int i = 0;
			i = db.delete(sql);
			if(i==1){
				am.setSuccess(true);
				am.setMsg("删除成功！");
				db.commit();
			}else{
				am.setSuccess(false);
				am.setMsg("删除失败！");
				db.rollback();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg("删除失败！");
			db.rollback();
		}
		finally{
			db.endTransaction();
			db.disconnect();
		}
		return am;
	}

	public PageUI log_query(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "";
		if(bean.getQuery_time()!=null&&!"".equals(bean.getQuery_time())){
			sql = "select sn,log_type,log_str,log_date,to_char(log_date,'yyyy-mm-dd hh24:mi:ss') as log_date_str from t_xb_interface_log where to_char(log_date,'yyyy-mm-dd')='"+bean.getQuery_time()+"' order by sn desc";
		}else{
			sql = "select sn,log_type,log_str,log_date,to_char(log_date,'yyyy-mm-dd hh24:mi:ss') as log_date_str from t_xb_interface_log order by sn desc";
		}
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, LogBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public AjaxMsg manual_run(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String sql = "select * from t_xb_sys_settings where param_flag='1' ";
		List<ParamBean> list_param = db.query(sql,ParamBean.class);
		String serKind="",timeout="0",serSign="",url="",status="";
		int thread_num = 10;
		for(ParamBean param : list_param){
			if("interface_serKind".equals(param.getParam_name())){
				serKind = param.getParam_value();
			}else if("interface_timeout".equals(param.getParam_name())){
				timeout = param.getParam_value();
			}else if("interface_serSign".equals(param.getParam_name())){
				serSign = param.getParam_value();
			}else if("interface_url".equals(param.getParam_name())){
				url = param.getParam_value();
			}else if("interface_status".equals(param.getParam_name())){
				status = param.getParam_value();
			}else if("interface_threadnum".equals(param.getParam_name())){
				thread_num = Integer.parseInt(param.getParam_value());
			}
		}
		if("working".equals(status)){
			am.setSuccess(false);
			am.setMsg("当前任务状态已经是working状态，不能再启动");
			log_interface_warning("当前任务状态已经是working状态，不能再启动");
		}else if("finish".equals(status)){
			if(url!=null&&!"".equals(url.trim())){
				XBUtils.init(url, serKind, serSign, Integer.parseInt(timeout));
				try{
					String[] time_arr = bean.getQuery_time().split("-");
					String time_str = time_arr[0]+time_arr[1]+time_arr[2];
					String[] time_arr_ed = bean.getQuery_time_ed().split("-");
					String time_str_ed = time_arr_ed[0]+time_arr_ed[1]+time_arr_ed[2];
//					sql = "select a.yjhm from gnxb_ord_peo_prtcin a where a.sjrq='"+time_str+"' and "
//							+" not exists(select 1 from t_xb_interface_detail b where a.yjhm=b.mail_code) and a.ttrq is null and a.ttsj is null group by yjhm";
//					sql = "select a.yjhm from gnxb_ord_peo_prtcin a where a.sjrq>'"+time_str+"' and a.sjrq<'"+time_str_ed+"' and "
//							+" not exists(select 1 from t_xb_interface_detail b where a.yjhm=b.mail_code) and a.ttrq is null and a.ttsj is null group by yjhm";
					sql = "select a.yjhm from gnxb_ord_peo_prtcin a where a.sjrq>to_char(to_date('"+time_str+"','yyyymmdd')-1,'yyyymmdd') " +
							" and a.sjrq<to_char(to_date('"+time_str_ed+"','yyyymmdd')+1,'yyyymmdd') and "
							+" not exists(select 1 from t_xb_interface_detail b where a.yjhm=b.mail_code) and a.ttrq is null and a.ttsj is null group by yjhm";
					System.out.println("===>"+sql);
					sql = db.queryPageStrOrder(sql, 0, bean.getQuery_num_ed());
					System.out.println("===>"+sql);
					List<Map<String,Object>> l = db.query(sql);
					if(l.size()>0){
						List<String> list_mailno = new ArrayList<String>();
						for(Map<String,Object> m : l){
							list_mailno.add(String.valueOf(m.get("yjhm")));
						}
						log_interface_info("准备开始任务添加。");
						ThreadFactory fac = new ThreadFactory(thread_num);
						fac.start(list_mailno);
						log_interface_info("任务添加成功，准备开始。时间【"+(new Date()).toString()+"】，任务数【"+list_mailno.size()+"】");
						am.setSuccess(true);
						am.setMsg("任务已经添加！");
					}else{
						am.setSuccess(false);
						am.setMsg("无法获取任务需要的邮件号");
						log_interface_error("无法获取任务需要的邮件号，当前查询区间的可用数据为0");
					}
				}
				catch(Exception ex){
					log_interface_error(ex.getMessage());
				}
			}else{
				am.setSuccess(false);
				am.setMsg("接口URL获取不到，不能日终了");
				log_interface_error("接口URL获取不到，不能日终了");
			}
		}else{
			am.setSuccess(false);
			am.setMsg("当前任务状态异常，请查看t_xb_sys_settings表中的interface_status对应的值，任务不能启动");
			log_interface_error("当前任务状态异常，请查看t_xb_sys_settings表中的interface_status对应的值，任务不能启动");
		}
		return am;
	}
	
	public void setXBInterfaceStatus(String status) {
		try{
			String sql = "update t_xb_sys_settings set param_value='"+status+"' where param_name='interface_status' and param_flag='1'";
			db.update(sql);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void insertInterfaceDetail(List<AjaxMsg> list_result){
		log_interface_info("开始数据插入。");
		try{
			db.connect();
			db.startTransaction();
			try{
				int s = 0;
				int e = 0;
				for(AjaxMsg am : list_result){
					try{
						InterfaceMailInfo mail = (InterfaceMailInfo)am.getBackParam();
						if(mail.getAction_info_out().indexOf("已妥投")>-1){
							String sql = "insert into t_xb_interface_detail (sn,mail_code,action_info_out,office_name,relation_office_desc,year,month,day,hour,minute,second) " +
									" values (SEQ_T_XB_INTERFACE_DETAIL.nextval,'"+mail.getMail_code()+"','"+mail.getAction_info_out()+"','"+mail.getOffice_name()+"','"
									+handleSQLStr(mail.getRelation_office_desc())+"','"+mail.getYear()+"','"+mail.getMonth()+"','"+mail.getDay()+"','"
									+mail.getHour()+"','"+mail.getMinute()+"','"+mail.getSecond()+"')";
							db.insert(sql);
							//URLEncoder.encode(mail.getRelation_office_desc(),"gbk")
							sql = "update gnxb_ord_peo_prtcin set ttrq='"+mail.getYear()+mail.getMonth()+mail.getDay()+"',ttsj='"+mail.getHour()+mail.getMinute()+mail.getSecond()+"' where yjhm='"+mail.getMail_code()+"' ";
							db.update(sql);
							s++;
						}else{
							String sql = "insert into t_xb_interface_detail_fail (sn,mail_code,action_info_out,office_name,relation_office_desc,year,month,day,hour,minute,second) " +
									" values (SEQ_T_XB_INTERFACE_DETAIL_FAIL.nextval,'"+mail.getMail_code()+"','"+mail.getAction_info_out()+"','"+mail.getOffice_name()+"','"
									+handleSQLStr(mail.getRelation_office_desc())+"','"+mail.getYear()+"','"+mail.getMonth()+"','"+mail.getDay()+"','"
									+mail.getHour()+"','"+mail.getMinute()+"','"+mail.getSecond()+"')";
							db.insert(sql);
							e++;
						}
					}
					catch(Exception ex){
						System.err.println(ex.getMessage());
					}
				}
				db.commit();
				log_interface_info("数据操作完毕，接口总共获取到数据【"+(s+e)+"】条，其中已妥投数据【"+s+"】条，未妥投数据【"+e+"】条");
			}
			catch(Exception ex){
				db.rollback();
				throw ex;
			}
			finally{
				db.endTransaction();
				db.disconnect();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private String handleSQLStr(String str){
		return str.replace("'", " ").replace("-", "->");
	}
	
	private void log_interface_error(String s) {
		log_interface(s,"error");
	}
	private void log_interface_warning(String s) {
		log_interface(s,"warning");
	}
	public void log_interface_info(String s) {
		log_interface(s,"info");
	}
	private void log_interface(String s,String type) {
		try{
			String sql = "insert into t_xb_interface_log (sn,log_type,log_str) values (SEQ_T_XB_INTERFACE_LOG.nextval,'"+type+"','"+s+"')";
			db.insert(sql);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public AjaxMsg manual_copy(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String sql = "select * from t_xb_sys_settings where param_flag='1' ";
		List<ParamBean> list_param = db.query(sql,ParamBean.class);
		String status="";
		Integer copynum = 0;
		for(ParamBean param : list_param){
			if("interface_status".equals(param.getParam_name())){
				status = param.getParam_value();
			}else if("interface_copynum".equals(param.getParam_name())){
				copynum = Integer.parseInt(param.getParam_value());
			}
		}
		if("working".equals(status)){
			am.setSuccess(false);
			am.setMsg("当前任务状态已经是working状态，不能再启动");
			log_interface_warning("当前任务状态已经是working状态，不能再启动");
		}else if("finish".equals(status)){
			setXBInterfaceStatus("working");
			log_interface_info("COPY任务开始。");
			log_interface_info("开始数据组装。");
			sql = "select a.mail_code,a.year||a.month||a.day as ttrq,a.hour||a.minute||a.second as ttsj " +
					"from t_xb_interface_detail a where a.action_info_out='已妥投' and " +
					"not exists (select 1 from gnxb_ord_peo_prtcin b where to_char(b.business_date,'yyyy-mm-dd')='"+bean.getQuery_time()+"' and b.yjhm=a.mail_code and b.ttrq > '' and b.ttsj > '')";
			sql = "select * from ("+sql+") where rownum<="+copynum;
			System.out.println(sql);
			List<CopyBean> list_bean = db.query(sql,CopyBean.class);
			if(list_bean.size()!=0){
				log_interface_info("开始数据更新。共【"+list_bean.size()+"条】");
				db.connect();
				db.startTransaction();
				int i=0;
				try{
					for(CopyBean b : list_bean){
						sql = "update gnxb_ord_peo_prtcin set ttrq='"+b.getTtrq()+"',ttsj='"+b.getTtsj()+"' where yjhm='"+b.getMail_code()+"' and to_char(business_date,'yyyy-mm-dd')='"+bean.getQuery_time()+"'";
						i += db.update(sql);
					}
					db.commit();
				}
				catch(Exception ex){
					System.err.println(ex.getMessage());
					db.rollback();
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
				log_interface_info("COPY任务结束。总数【"+list_bean.size()+"】成功【"+i+"】");
				am.setSuccess(true);
				am.setMsg("COPY任务结束。总数【"+list_bean.size()+"】成功【"+i+"】");
			}
			setXBInterfaceStatus("finish");
		}else{
			am.setSuccess(false);
			am.setMsg("当前任务状态异常，请查看t_xb_sys_settings表中的interface_status对应的值，任务不能启动");
			log_interface_error("当前任务状态异常，请查看t_xb_sys_settings表中的interface_status对应的值，任务不能启动");
		}
		return am;
	}

	public PageUI query_khyyqkjb(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "";
		String[] s = bean.getQuery_time().split("-");
		double persent = 0.3;
		sql = "select value from t_sys_param where type='XBXT_GDFT'";
		String sa = db.queryString(sql);
		try{
			persent = Double.parseDouble(sa);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		if(bean.getCust_id()!=null&&!"".equals(bean.getCust_id().trim())){
//			sql = "select * from t_xb_cmur where cust_id='"+bean.getCust_id().trim()+"' and year='"+s[0]+"' and month='"+s[1]+"'";
//			sql = "select a.sn,a.cust_id,a.destination,a.send_num,a.standard_fee,a.fee,a.mail_weight,a.direct_mail_cost," +
//					" a.last_month_send_num,round((a.direct_mail_cost+a.send_num*0.3),1) as real_direct_mail_cost, "+
//					" round(a.mail_aver_time/24,1) as mail_aver_time, "+
//					" round(a.total_aver_time,1) as total_aver_time," +
//					"(case when a.standard_fee=0 then 0.0 else round((a.fee/a.standard_fee)*100,1) end) as discount_persent" +
//					" from t_xb_cmur a,gnxb_area_province b  "+
//					" where instr(a.destination,b.province)>0 and a.cust_id='"+bean.getCust_id().trim()+"' and a.year='"+s[0]+"' "+
//					" and a.month='"+s[1]+"' order by b.sn";
			
			
//			sql = "select a.sn,a.cust_id,a.destination,a.send_num,a.standard_fee,a.fee,a.mail_weight, "+
//					" a.direct_mail_cost, round((a.direct_mail_cost+a.send_num*"+persent+"),1) as real_direct_mail_cost, "+
//					" (a.fee-round((a.direct_mail_cost+a.send_num*"+persent+"),1)) as maoli, " +
//					"   round(a.mail_aver_time/24,1) as mail_aver_time,  round(a.total_aver_time/24,1) as total_aver_time, "+
//					"   (case when a.standard_fee=0 then 0.0 else round((a.fee/a.standard_fee)*100,1) end) as discount_persent, "+
//					"   nvl(c.send_num,0) as last_month_send_num "+
//					"    from t_xb_cmur a,gnxb_area_province b,t_xb_cmur c   where instr(a.destination,b.province)>0 and  "+
//					"    a.cust_id='"+bean.getCust_id().trim()+"' and a.year='"+s[0]+"'  and a.month='"+s[1]+"' "+
//					"    and a.cust_id=c.cust_id(+) and a.destination=c.destination(+)  "+
//					"    and c.year=to_char(add_months(trunc(to_date('"+s[0]+s[1]+"','yyyymm')),-1),'yyyy') "+
//					"    and c.month=to_char(add_months(trunc(to_date('"+s[0]+s[1]+"','yyyymm')),-1),'mm')  order by b.sn";
			
			sql = "select a.sn,nvl(a.cust_id,'"+bean.getCust_id().trim()+"') as cust_id,a.destination,nvl(a.send_num,0) as send_num,nvl(a.standard_fee,0) as standard_fee, " +
					" nvl(a.fee,0) as fee,nvl(a.mail_weight,0) as mail_weight,nvl(a.direct_mail_cost,0) as direct_mail_cost, " +
					"  (case when round((a.direct_mail_cost+a.send_num*0.3),1) is not null then round((a.direct_mail_cost+a.send_num*0.3),1) else 0 end) as real_direct_mail_cost, " +
					"    (case when (a.fee-round((a.direct_mail_cost+a.send_num*0.3),1)) is not null then (a.fee-round((a.direct_mail_cost+a.send_num*0.3),1)) else 0 end) as maoli,  " +  
					"     (case when round(a.mail_aver_time/24,1) is not null then round(a.mail_aver_time/24,1) else 0 end) as mail_aver_time, " +
					"     (case when round(a.total_aver_time/24,1) is not null then round(a.total_aver_time/24,1) else 0 end) as total_aver_time,    " +
					"      (case when a.standard_fee=0 then 0.0 when round((a.fee/a.standard_fee)*100,1) is not null then round((a.fee/a.standard_fee)*100,1) else 0 end) as discount_persent, " +  
					"        nvl(b.send_num,0) as last_month_send_num    " +
					" from (select d.sn,d.cust_id,e.province as destination,d.send_num,d.standard_fee,d.fee,d.mail_weight,d.direct_mail_cost,d.mail_aver_time, " +
					" d.total_aver_time from t_xb_cmur d,gnxb_area_province e where d.destination(+)=e.province and  " +
					" d.cust_id(+)='"+bean.getCust_id().trim()+"' and d.year(+)='"+s[0]+"' and d.month(+)='"+s[1]+"' order by e.sn) a, " +
					" (select d.sn,d.cust_id,e.province as destination,d.send_num,d.standard_fee,d.fee,d.mail_weight,d.direct_mail_cost,d.fee,d.mail_aver_time, " +
					" d.total_aver_time from t_xb_cmur d,gnxb_area_province e where d.destination(+)=e.province and  " +
					" d.cust_id(+)='"+bean.getCust_id().trim()+"' and d.year(+)=to_char(add_months(trunc(to_date('"+s[0]+s[1]+"','yyyymm')),-1),'yyyy') and " +
					"  d.month(+)=to_char(add_months(trunc(to_date('"+s[0]+s[1]+"','yyyymm')),-1),'mm') order by e.sn) b " +
					" where a.destination=b.destination";
			
			
			
			
		}else{
//			sql = "select * from t_xb_cmur where year='"+s[0]+"' and month='"+s[1]+"'";
			System.out.println("这里不应该是空的！！！！！！！！！！！！！！！！");
		}
		System.out.println("query_khyyqkjb:"+sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, Khyyqkjb.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> ajax_custinfo(String cust_no) throws Exception {
		Map<String,Object> m = new HashMap<String,Object>();
		List<Map<String,Object>> list_cdp = new ArrayList<Map<String,Object>>();
		list_cdp = db.query("select * from t_cdp where bigcustno='"+cust_no+"'");
		List<Map<String,Object>> list_cmgr = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> list_dept = new ArrayList<Map<String,Object>>();
		if(list_cdp.size()>0){
			Map<String,Object> cdp = list_cdp.get(0);
			list_cmgr = db.query("select * from t_cmgr where mgrid='"+String.valueOf(cdp.get("mgr_id"))+"'");
			list_dept = db.query("select * from t_dept where sn="+cdp.get("dept_no"));
			m.put("bigcustno", cdp.get("bigcustno"));
			m.put("bigcustname", cdp.get("bigcustname"));
			m.put("mgrid", cdp.get("mgr_id"));
			m.put("dept_no", cdp.get("dept_no"));
			m.put("dept_sn", cdp.get("dept_sn"));
			m.put("addr", cdp.get("big_addr"));
			m.put("postcode", cdp.get("postcode"));
			
			if(list_cmgr.size()>0){
				Map<String,Object> cmgr = list_cmgr.get(0);
				m.put("mgrname", cmgr.get("mgrname"));
			}
			if(list_dept.size()>0){
				Map<String,Object> dept = list_dept.get(0);
				m.put("deptname", dept.get("forshort"));
			}
			String industry = db.queryString("select industry from gnxb_htje where cust_id='"+cdp.get("bigcustno")+"'");
			if(industry==null||"".equals(industry.trim())){
				industry="";
			}
			m.put("industry", industry);
		}
		return m;
	}

	public List<?> brch(String q, UserInfo userInfo) throws Exception {
		StringBuffer whereSql=new StringBuffer();
		if(q.matches("[0-9]*")) {//纯数字
	    	whereSql.append(" and a.bigcustno='"+q+"' ");
	    }else{
	    	whereSql.append(" and a.bigcustname like '%"+q+"%'");
	    }
		    
		String sql="select a.bigcustno as cust_no,a.bigcustname as cust_name,nvl(b.forshort,'') as cust_brch from t_cdp a,t_dept b where a.dept_no=b.sn(+) "+whereSql.toString();
		sql=db.queryPageStrOrder(sql, 1, 30);
		return db.query(sql);
	}

	@SuppressWarnings("unchecked")
	public List<Brch> getParents(String node) throws Exception {
		String sql = "select * from t_sys_brch start with brch_no="+node+" connect by prior brch_up=brch_no";
		return db.query(sql,Brch.class);
	}
	@SuppressWarnings("unchecked")
	public List<Brch> getParents2(String node) throws Exception {
		String sql = "select sn as brch_no,parent_sn as brch_up,dept_name as brch_name,dept_no as brch_code,"
					+" area_no,spell_s as spell_short,spell_a as spell_full from t_dept "
					+" start with sn="+node+" connect by prior parent_sn=sn";
		System.out.println(sql);
		return db.query(sql,Brch.class);
	}

	@SuppressWarnings("unchecked")
	public AjaxMsg getinfo_forsetcustinfo(XBBean bean, UserInfo userinfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		//获取行业和绑定机构
		String sql = "";
		sql = "select industry from gnxb_htje where cust_id='"+bean.getBigcustno()+"'";
		String industry = db.queryString(sql);
		sql = "select a.dept_no,b.dept_name from t_cdp a,t_dept b where a.dept_no=b.sn and a.bigcustno='"+bean.getBigcustno()+"'";
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> list = db.query(sql);
		if(list.size()>0){
			map.put("dept_no", list.get(0).get("dept_no"));
			map.put("dept_name", list.get(0).get("dept_name"));
		}
		map.put("industry", industry);		
		am.setSuccess(true);
		am.setBackParam(map);
		return am;
	}

	public AjaxMsg savecustinfo(XBBean bean, UserInfo userinfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		
		String sql = "";
		sql = "select forshort from t_dept where sn="+bean.getBigcustbrch();
		String deptname = db.queryString(sql);
		sql = "update gnxb_htje set industry='"+bean.getBigcusthy()+"',bind_dept='"+deptname+"' where cust_id='"+bean.getSskhdm()+"'";
		db.update(sql);
		sql = "update t_cdp set dept_no='"+bean.getBigcustbrch()+"' where bigcustno='"+bean.getSskhdm()+"'";
		db.update(sql);
		sql = "update t_ctp set dept_no='"+bean.getBigcustbrch()+"' where bigcustno='"+bean.getSskhdm()+"'";
		db.update(sql);
		am.setSuccess(true);
		am.setMsg("处理完毕！");
		return am;
	}

	public List<?> mgr_search(String q, UserInfo userInfo) throws Exception {
		StringBuffer whereSql=new StringBuffer();
		if(q.matches("[0-9]*")) {//纯数字
	    	whereSql.append(" and mgrid='"+q+"' ");
	    }else{
	    	whereSql.append(" and mgrname like '%"+q+"%'");
	    }
		
		String sql="select mgrid as mgr_no,mgrname as mgr_name,department from t_cmgr where 1=1 "+whereSql.toString();
		sql=db.queryPageStrOrder(sql, 1, 30);
		return db.query(sql);
	}

	@SuppressWarnings("unchecked")
	public AjaxMsg getinfo_forsetcustmgrinfo(XBBean bean, UserInfo userinfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String sql = "";
		sql = "select b.mgrid,b.mgrname from t_ctp a,t_cmgr b where a.mgr_id=b.mgrid and a.bigcustno='"+bean.getBigcustno()+"'";
		List<Map<String,Object>> list = db.query(sql);
		Map<String,Object> map = new HashMap<String,Object>();
		if(list!=null&&list.size()>0){
			map.put("mgrid", list.get(0).get("mgrid"));
			map.put("mgrname", list.get(0).get("mgrname"));
		}
		am.setSuccess(true);
		am.setBackParam(map);
		return am;
	}

	public AjaxMsg savecustmgrinfo(XBBean bean, UserInfo userinfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String sql = "";
		sql = "update t_ctp set mgr_id='"+bean.getCust_mgrno()+"' where bigcustno='"+bean.getSskhdm()+"'";
		db.update(sql);
		am.setSuccess(true);
		am.setMsg("修改完毕！");
		return am;
	}

	public PageUI query5(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "select * from ds_gnxb_jscb_bj where brch_code='"+bean.getBrch_code()+"'";
		@SuppressWarnings("unchecked")
		List<DS_GNXB_JSCB_BJ> list = db.query(sql,DS_GNXB_JSCB_BJ.class);
		sql = "select count(1) from T_XB_REALFEE_PARAM where brch_code='"+bean.getBrch_code()+"'";
		Integer c = db.queryInt(sql);
		if(list!=null&&list.size()>0&&(c==null||c==0)){
			for(DS_GNXB_JSCB_BJ b : list){
				sql = "insert into T_XB_REALFEE_PARAM (sn,province,brch_code,param_a,param_b) values (SEQ_T_XB_REALFEE_PARAM.nextval,'"+b.getV_jdjsmgm()+"','"+b.getBrch_code()+"',1,1)";
				db.insert(sql);
			}
		}
//		sql = "select b.brch_code,b.province,b.param_a,b.param_b,b.sn as param_sn,round(((a.hcys+a.qcys+a.nbcl+a.jz+a.td)*b.param_a+b.param_b),2) as real_fee from ds_gnxb_jscb_bj a,T_XB_REALFEE_PARAM b where a.brch_code=b.brch_code and a.v_jdjsmgm=b.province";
		sql = "select round(c.province_aver_time/24,1) as province_aver_time,b.brch_code,b.province,b.param_a,b.param_b,b.sn as param_sn,round(((a.hcys+a.qcys+a.nbcl+a.jz+a.td)*b.param_a+b.param_b),2) as real_fee "+
				" from ds_gnxb_jscb_bj a,T_XB_REALFEE_PARAM b,t_xb_zpjsx c where a.brch_code=b.brch_code "+
				"  and instr(c.province,b.province)>0 and c.year_month=to_char(add_months(trunc(sysdate),-1),'yyyymm') "+
				"   and a.v_jdjsmgm=b.province ";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		@SuppressWarnings("unchecked")
		List<RealFeeQuery> list_r = db.query(listsql, RealFeeQuery.class);
		for(int i=0;i<list_r.size();i++){
			if(list_r.get(i).getProvince()!=null&&list_r.get(i).getProvince().indexOf("黑龙")>-1){
				list_r.get(i).setProvince("黑龙江");
			}
		}
		rs.setRows(list_r);
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg editSettings4(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		String sql = "update T_XB_REALFEE_PARAM set param_a="+bean.getParam_a()+",param_b="+bean.getParam_b()+" where sn in ("+bean.getParam_sn()+") ";
		try{
			db.connect();
			db.startTransaction();
			int r = db.update(sql);
			if(r>0){
				db.commit();
				rs.setSuccess(true);
				rs.setMsg("更新成功！");
			}else{
				db.rollback();
				rs.setSuccess(false);
				rs.setMsg("更新失败！");
			}
		}
		catch(Exception ex){
			db.rollback();
			rs.setSuccess(false);
			rs.setMsg("更新失败！"+ex.getMessage());
		}
		finally{
			db.endTransaction();
			db.disconnect();
		}
		return rs;
	}

	public PageUI cust_warning_query(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		StringBuffer whereSql=new StringBuffer();
		String q = bean.getBigcustnameorbigcustno();
		if(q!=null&&!"".equals(q)){
			if(q.matches("[0-9]*")) {//纯数字
		    	whereSql.append(" and c.bigcustno='"+q+"' ");
		    }else{
		    	whereSql.append(" and c.bigcustname like '%"+q+"%'");
		    }
		}
		String sql = "";
		sql = "select param_a||'_'||param_b as param from t_xb_cust_warning_param";
		String[] s_arr = db.queryString(sql).split("_");
		Double a = Double.parseDouble(s_arr[0]);
		Double b = Double.parseDouble(s_arr[1]);
		int n1 = 1;//1  TODO
		int n2 = n1 + 1;//2
		String connector = " and ";//or
//		sql = "select c.*,d.bigcustname from (select a.cust_id as bigcustno,a.destination,a.send_num as lastmonth_sendnum,b.send_num as lastlastmonth_sendnum," +
//				"(case when (b.send_num-a.send_num)>"+a+" then '是' else '否' end) as condition_one," +
//				"(case when ((b.send_num-a.send_num)/b.send_num)>"+b+" then '是' else '否' end) as condition_two" +
//				" from (select cust_id,destination,sum(send_num) as send_num from t_xb_cmur where year=to_char(add_months(trunc(sysdate),-"+n1+"),'yyyy')" +
//				" and month=to_char(add_months(trunc(sysdate),-"+n1+"),'mm') group by cust_id,destination) a," +
//				" (select cust_id,destination,sum(send_num) as send_num from t_xb_cmur where year=to_char(add_months(trunc(sysdate),-"+n2+"),'yyyy')" +
//				" and month=to_char(add_months(trunc(sysdate),-"+n2+"),'mm') group by cust_id,destination) b" +
//				" where a.cust_id=b.cust_id and a.destination=b.destination and b.send_num!=0" +
//				" and ((b.send_num-a.send_num)>"+a+" or ((b.send_num-a.send_num)/b.send_num)>"+b+")) c,t_cdp d where c.bigcustno=d.bigcustno order by c.bigcustno" +
//				" "+whereSql.toString();
		
		
		/*sql = "select c.*,d.bigcustname,nvl(k.mgrid,'') as mgrid,nvl(k.mgrname,'') as mgrname" +
				" from (select a.cust_id as bigcustno,a.destination,a.send_num as lastmonth_sendnum,b.send_num as lastlastmonth_sendnum," +
				"(case when (b.send_num-a.send_num)>"+a+" then '是' else '否' end) as condition_one,(case when ((b.send_num-a.send_num)/b.send_num)>"+b+" " +
				"then '是' else '否' end) as condition_two from (select e.cust_id,e.destination,sum(e.send_num) as send_num from t_xb_cmur e where " +
				"e.year=to_char(add_months(trunc(sysdate),-"+n1+"),'yyyy') and e.month=to_char(add_months(trunc(sysdate),-"+n1+"),'mm') and " +
				"not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.data_type='1' and m.bigcustno=e.cust_id  and m.destination=e.destination" +
				"  and to_char(add_months(trunc(m.data_time),-"+n1+"),'yyyy')=e.year and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=e.month)" +
				" and not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.data_type='2' and m.bigcustno=e.cust_id and to_char(add_months(trunc(m.data_time),-"+n1+"),'yyyy')=e.year and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=e.month) " +
				" group by e.cust_id,e.destination) a," +
				"  (select r.cust_id,r.destination,sum(r.send_num) as send_num from t_xb_cmur r where r.year=to_char(add_months(trunc(sysdate),-"+n2+"),'yyyy') " +
				"and r.month=to_char(add_months(trunc(sysdate),-"+n2+"),'mm') and not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.data_type='1' and m.bigcustno=r.cust_id  " +
				"and m.destination=r.destination and to_char(add_months(trunc(m.data_time),-"+n2+"),'yyyy')=r.year and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=r.month)" +
				" and not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.data_type='2' and m.bigcustno=r.cust_id and to_char(add_months(trunc(m.data_time),-"+n1+"),'yyyy')=r.year and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=r.month) " +
				" group by r.cust_id,r.destination) b  where a.cust_id(+)=b.cust_id and a.destination(+)=b.destination and " +
				"b.send_num!=0  and ((b.send_num-a.send_num)>"+a+" "+connector+" ((b.send_num-a.send_num)/b.send_num)>"+b+")) c, t_cdp d,t_cmgr k where c.bigcustno=d.bigcustno" +
				" and d.mgr_id=k.mgrid(+) "+whereSql.toString()+" " +
				"order by c.bigcustno ";*/
		
		sql = "select c.*,d.bigcustname,nvl(k.mgrid,'') as mgrid,nvl(k.mgrname,'') as mgrname from "+
				" ( select * from ( "+
				" select b.cust_id as bigcustno,b.destination,nvl(a.send_num,0) as lastmonth_sendnum,nvl(b.send_num,0) as lastlastmonth_sendnum," +
				"(case when (nvl(b.send_num,0)-nvl(a.send_num,0))>"+a+" then '是' else '否' end) as condition_one, "+
				" (case when ((nvl(b.send_num,0)-nvl(a.send_num,0))/nvl(b.send_num,0))>"+b+" then '是' else '否' end) as condition_two  "+
				" from   "+
				" (select r.cust_id,r.destination,sum(r.send_num) as send_num from t_xb_cmur r where r.year=to_char(add_months(trunc(sysdate),-"+n2+"),'yyyy') " +
				" and r.month=to_char(add_months(trunc(sysdate),-"+n2+"),'mm') "+
				" and not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.data_type='1' "+
				" and m.bigcustno=r.cust_id  and m.destination=r.destination and to_char(add_months(trunc(m.data_time),-"+n2+"),'yyyy')=r.year " +
				"and to_char(add_months(trunc(m.data_time),-"+n2+"),'mm')=r.month) "+
				" and not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.data_type='2'  "+
				" and m.bigcustno=r.cust_id and to_char(add_months(trunc(m.data_time),-"+n1+"),'yyyy')=r.year and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=r.month)   "+
				" group by r.cust_id,r.destination) b  "+
				"  left outer join  "+
				" ( "+
				" select e.cust_id,e.destination,sum(e.send_num) as send_num  "+
				" from t_xb_cmur e  "+
				" where e.year=to_char(add_months(trunc(sysdate),-"+n1+"),'yyyy') and e.month=to_char(add_months(trunc(sysdate),-"+n1+"),'mm')  "+
				" and not exists(select 1 from T_XB_VISIT_WRITEPAPER m  "+
				" where m.data_type='1' and m.bigcustno=e.cust_id  and m.destination=e.destination  and to_char(add_months(trunc(m.data_time),-"+n1+"),'yyyy')=e.year " +
				"and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=e.month)  "+
				" and not exists(select 1 from T_XB_VISIT_WRITEPAPER m  "+
				" where m.data_type='2' and m.bigcustno=e.cust_id and to_char(add_months(trunc(m.data_time),-"+n1+"),'yyyy')=e.year " +
				"and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=e.month)  group by e.cust_id,e.destination "+
				" ) a "+
				" on b.cust_id||b.destination=a.cust_id|| a.destination and b.send_num!=0   "+
				" ) where  ((nvl(lastlastmonth_sendnum,0)-nvl(lastmonth_sendnum,0))>"+a+"  and " +
				" ((nvl(lastlastmonth_sendnum,0)-nvl(lastmonth_sendnum,0))/nvl(lastlastmonth_sendnum,0)>"+b+"))) "+
				" c, t_cdp d,t_cmgr k where c.bigcustno=d.bigcustno(+) and d.mgr_id=k.mgrid(+)   "+whereSql.toString()+" "+
				" order by c.bigcustno "+
				" ";
		
		
		
		
		System.out.println(sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, CustWarning.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg find_cust_warning_param(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String sql = "";
		sql = "select to_char(sn) as sn,param_a,param_b from t_xb_cust_warning_param";
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = db.query(sql);
		String s = Integer.parseInt(list.get(0).get("sn").toString())+"_"+Double.parseDouble(list.get(0).get("param_a").toString())+"_"+Double.parseDouble(list.get(0).get("param_b").toString());
		am.setSuccess(true);
		am.setMsg(s);
		return am;
	}

	public AjaxMsg update_cust_warning_param(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String sql = "";
		sql = "update t_xb_cust_warning_param set param_a="+bean.getParam_a()+",param_b="+bean.getParam_b()+" where sn="+bean.getParam_sn();
		db.connect();
		db.startTransaction();
		try{
			int u = db.update(sql);
			if(u==1){
				am.setSuccess(true);
				am.setMsg("修改成功！");
				db.commit();
			}else{
				am.setSuccess(false);
				am.setMsg("修改失败！");
				db.rollback();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
			db.rollback();
		}
		finally{
			db.endTransaction();
			db.disconnect();
		}
		return am;
	}

	public PageUI query7(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "select * from ds_gnxb_jscb_bj where brch_code='"+bean.getBrch_code()+"'";
		@SuppressWarnings("unchecked")
		List<DS_GNXB_JSCB_BJ> list = db.query(sql,DS_GNXB_JSCB_BJ.class);
		sql = "select count(1) from T_XB_STANDARDFEE_PARAM where brch_code='"+bean.getBrch_code()+"'";
		Integer c = db.queryInt(sql);
		if(list!=null&&list.size()>0&&(c==null||c==0)){
			for(DS_GNXB_JSCB_BJ b : list){
				sql = "insert into T_XB_STANDARDFEE_PARAM (sn,province,brch_code,STANDARD_FEE,DISCOUNT_FEE) values (SEQ_T_XB_STANDARDFEE_PARAM.nextval,'"+b.getV_jdjsmgm()+"','"+bean.getBrch_code()+"',0,0)";
				db.insert(sql);
			}
		}
		sql = "select sn as param_sn,province,brch_code,standard_fee,discount_fee from T_XB_STANDARDFEE_PARAM where brch_code='"+bean.getBrch_code()+"'";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		@SuppressWarnings("unchecked")
		List<StandardFeeQuery> l = db.query(listsql, StandardFeeQuery.class);
		for(int i=0;i<l.size();i++){
			if(l.get(i)!=null&&l.get(i).getProvince().indexOf("黑龙")>-1){
				l.get(i).setProvince("黑龙江");
			}
		}
		rs.setRows(l);
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg editSettings7(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		String sql = "update T_XB_STANDARDFEE_PARAM set standard_fee="+bean.getStandard_fee()+",discount_fee="+bean.getDiscount_fee()+" where sn in ("+bean.getParam_sn()+") ";
		try{
			db.connect();
			db.startTransaction();
			int r = db.update(sql);
			if(r>0){
				db.commit();
				rs.setSuccess(true);
				rs.setMsg("更新成功！");
			}else{
				db.rollback();
				rs.setSuccess(false);
				rs.setMsg("更新失败！");
			}
		}
		catch(Exception ex){
			db.rollback();
			rs.setSuccess(false);
			rs.setMsg("更新失败！"+ex.getMessage());
		}
		finally{
			db.endTransaction();
			db.disconnect();
		}
		return rs;
	}

	public AjaxMsg addinto_writepage(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String[] bigcustno_arr = bean.getBigcustno_arr();
		String[] destination_arr = bean.getDestination_arr();
		try{
			String year = "";
			String month = "";
			SimpleDateFormat year_sdf=new SimpleDateFormat("yyyy");
			SimpleDateFormat month_sdf=new SimpleDateFormat("MM");
			year = year_sdf.format(new Date());
			month = month_sdf.format(new Date());
			db.connect();
			db.startTransaction();
			String sql = "";
			for(int i=0;i<bigcustno_arr.length;i++){
				sql = "insert into T_XB_VISIT_WRITEPAPER (sn,bigcustno,destination,op_id,op_name,data_type,year,month)" +
						" values (SEQ_T_XB_VISIT_WRITEPAPER.nextval,'"+bigcustno_arr[i]+"','"+destination_arr[i]+"'," +
						"'"+userInfo.getUser_id()+"','"+userInfo.getUser_name()+"','1','"+year+"','"+month+"')";
				if(db.insert(sql)!=1){
					throw new Exception ("数据插入失败！");
				}
			}
			db.commit();
			am.setSuccess(true);
			am.setMsg("修改成功！");
		}
		catch(Exception ex){
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
			db.rollback();
		}
		finally{
			db.endTransaction();
			db.disconnect();
		}
		return am;
	}

	public PageUI cust_warning_querywritepaper(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
//		StringBuffer whereSql=new StringBuffer();
//		String q = bean.getBigcustnameorbigcustno();
//		if(q!=null&&!"".equals(q)){
//			if(q.matches("[0-9]*")) {//纯数字
//		    	whereSql.append(" and c.bigcustno='"+q+"' ");
//		    }else{
//		    	whereSql.append(" and c.bigcustname like '%"+q+"%'");
//		    }
//		}
		String sql = "";
		sql = "select a.sn,a.bigcustno,a.comments,b.bigcustname,a.destination,a.op_id,a.op_name,to_char(a.data_time,'yyyy-mm-dd') as op_time," +
				"a.year,a.month from T_XB_VISIT_WRITEPAPER a,t_cdp b where a.bigcustno=b.bigcustno and a.data_type='1' order by a.year desc,a.month desc,a.bigcustno";
		
		System.out.println(sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, CustWarningWritePaper.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg removefrom_writepage(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String[] sn_arr = bean.getSn_arr();
		String sn_str = StringUtils.join(sn_arr, ",");
		String sql = "delete from T_XB_VISIT_WRITEPAPER where sn in ("+sn_str+")";
		try{
			db.connect();
			db.startTransaction();
			if(db.delete(sql)!=sn_arr.length){
				throw new Exception ("移出失败！");
			}else{
				am.setSuccess(true);
				am.setMsg("移出成功！");
				db.commit();
			}
			
		}catch(Exception ex){
			db.rollback();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		finally{
			db.endTransaction();
			db.disconnect();
		}
		return am;
	}

	public AjaxMsg update_writepage_comments(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String sql = "update t_xb_visit_writepaper set comments='"+bean.getComments().trim()+"' where sn in ("+bean.getSns()+")";
		try{
			db.connect();
			db.startTransaction();
			
			if(db.update(sql)!=bean.getSns().split(",").length){
				throw new Exception ("操作失败！");
			}else{
				am.setSuccess(true);
				am.setMsg("操作成功！");
				db.commit();
			}
		}
		catch(Exception ex){
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
			db.rollback();
		}
		finally{
			db.endTransaction();
			db.disconnect();
		}
		return am;
	}

	public PageUI mgr_settings_query(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		StringBuffer whereSql=new StringBuffer();
		String q = bean.getMgrnameormgrno();
		if(q!=null&&!"".equals(q)&&!"null".equals(q)){
			if(q.matches("[0-9]*")) {//纯数字
		    	whereSql.append(" and a.mgrid='"+q+"' ");
		    }else{
		    	whereSql.append(" and a.mgrname like '%"+q+"%'");
		    }
		}else{
			q = "";
		}
		String sql = "";
		if("1".equals(bean.getIf_onlyinitmgr())){
			sql = "select a.mgrid,a.mgrname,a.department,b.user_id,(case when b.user_id is not null then '已初始' else '未初始' end) as if_init,"
					+" b.mgr_login_str from t_cmgr a,t_sys_user b where a.mgrid=b.user_name and a.mgrname=b.nick_name "+whereSql.toString();
		}else{
			sql = "select a.mgrid,a.mgrname,a.department,b.user_id,(case when b.user_id is not null then '已初始' else '未初始' end) as if_init,"
					+" b.mgr_login_str from t_cmgr a,t_sys_user b where a.mgrid=b.user_name(+) and a.mgrname=b.nick_name(+) "+whereSql.toString();
		}
		System.out.println(sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, MgrSettingsQuery.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg merger_mobileinfo_formgr(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		
		try{
			String mgrid = bean.getCust_no();
			String login_str = bean.getLogin_str();
			String login_pwd = bean.getLogin_pwd();
			String login_pwd_md5 = SecurityUtil.md5(login_pwd);
			String sql = "";
			
			sql = "select count(1) from t_sys_user where mgr_login_str='"+login_str+"'";
			if(db.queryInt(sql)!=0){
				am.setSuccess(false);
				am.setMsg("该登录字符【"+login_str+"】已经被其他用户占用了，请换一个。");
			}else{
				sql = "select user_id from t_sys_user where user_name='"+mgrid+"'";
				String u_id = db.queryString(sql);
				if(u_id!=null&&!"".equals(u_id)){
					//edit
					Integer user_id = Integer.parseInt(u_id);
					sql = "update t_sys_user set mgr_login_str='"+login_str+"',password='"+login_pwd_md5+"' where user_id="+user_id;
					db.update(sql);
					am.setSuccess(true);
					am.setMsg("初始化成功！");
				}else{
					//add
					sql = "select * from t_cmgr where mgrid='"+mgrid+"'";
					@SuppressWarnings("unchecked")
					List<Map<String,Object>> list = db.query(sql);
					Map<String,Object> map = list.get(0);
					sql = "insert into t_sys_user (user_id,user_name,nick_name,password,mgr_login_str) values (SEQ_T_SYS_USER.nextval,'"+map.get("mgrid")+"','"+map.get("mgrname")+"','"+login_pwd_md5+"','"+login_str+"')";
					db.insert(sql);
					am.setSuccess(true);
					am.setMsg("新增成功！");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg("内部错误："+ex.getMessage());
		}
		
		return am;
	}

	public PageUI query_cust_hz_report(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "";
		String year = "";
		String month = "";
		StringBuffer wheresql = new StringBuffer();
		if(bean.getCust_id()!=null&&!"".equals(bean.getCust_id())){
			wheresql.append(" and a.bigcustno='"+bean.getCust_id()+"' ");
		}
		if(bean.getCust_mgrno()!=null&&!"".equals(bean.getCust_mgrno())){
			wheresql.append(" and b.mgr_id='"+bean.getCust_mgrno()+"' and b.mgr_id=c.mgrid ");
		}else{
			wheresql.append(" and b.mgr_id=c.mgrid(+)");
		}
		if(bean.getQuery_time()!=null&&!"".equals(bean.getQuery_time())&&!"null".equalsIgnoreCase(bean.getQuery_time())){
			String[] s = bean.getQuery_time().split("-");
			year = s[0];
			month = s[1];
		}else{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			year = String.valueOf(cal.get(Calendar.YEAR));
			month = String.format("%02d", cal.get(Calendar.MONTH)+1);
			System.out.println("default search:year="+year+"  month="+month);
		}
		double persent = 0.0;
		try{
			sql = "select value from t_sys_param where type='XBXT_GDFT'";
			String a = db.queryString(sql);
			persent = Double.parseDouble(a);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		sql = "select a.*,(a.fee-a.real_direct_mail_cost) as maoli,b.bigcustname,c.mgrname,c.mgrid,c.department from "+
				"(select cust_id as bigcustno,sum(send_num) as send_num,sum(standard_fee) as standard_fee,sum(fee) as fee," +
				"round(avg(discount_persent),1) as discount_persent,round(sum(real_direct_mail_cost),1) as real_direct_mail_cost," +
				"sum(last_month_send_num) as last_month_send_num,sum(direct_mail_cost) as direct_mail_cost from "+
				"(select a.sn,a.cust_id,a.destination,a.send_num,a.standard_fee,a.fee,a.mail_weight, "+
				" a.direct_mail_cost, (a.direct_mail_cost+a.send_num*"+persent+") as real_direct_mail_cost, "+
				"   round(a.mail_aver_time/24,1) as mail_aver_time,  round(a.total_aver_time/24,1) as total_aver_time, "+
				"   (case when a.standard_fee=0 then 0.0 else round((a.fee/a.standard_fee)*100,1) end) as discount_persent, "+
				"   nvl(c.send_num,0) as last_month_send_num "+
				"    from t_xb_cmur a,gnxb_area_province b,t_xb_cmur c   where instr(a.destination,b.province)>0 "+
				"    and a.year='"+year+"'  and a.month='"+month+"' "+
				"    and a.cust_id=c.cust_id(+) and a.destination=c.destination(+)  "+
				"    and c.year=to_char(add_months(trunc(to_date('"+year+month+"','yyyymm')),-1),'yyyy') "+
				"    and c.month=to_char(add_months(trunc(to_date('"+year+month+"','yyyymm')),-1),'mm')) group by cust_id) a," +
				"t_cdp b,t_cmgr c where b.bigcustno=a.bigcustno "+wheresql.toString();
		System.out.println(sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, CustHZReport.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public Double getGDFT() {
		double f = 0.0;
		try{
			String sql = "select value from t_sys_param where type='XBXT_GDFT'";
			String a = db.queryString(sql);
			f = Double.parseDouble(a);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return f;
	}

	public PageUI query_mgr_visit_show(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "";
		//String[] s = bean.getQuery_time().split("-");
		StringBuffer wheresql = new StringBuffer();
		if(bean.getCust_mgrno()!=null&&!"".equals(bean.getCust_mgrno())){
			wheresql.append(" and a.mgrid='"+bean.getCust_mgrno()+"'");
		}
		if(bean.getQuery_time()!=null&&!"".equals(bean.getQuery_time())){
			wheresql.append(" and to_char(a.data_time,'yyyy-mm-dd')='"+bean.getQuery_time()+"'");
		}
		
		sql = "select a.sn,a.mgrid,a.mgrname,a.custname,a.connect_name,a.connect_tel,a.custtype," +
				"c.param_text as custtype_text,a.comments,a.cust_feedback,a.imgname," +
				"to_char(a.data_time,'yyyy-mm-dd') as visit_time," +
				"(case when exists(select 1 from T_XB_VISIT_WRITEPAPER b where b.data_type='2' and b.visit_sn=a.sn)" +
				" then '1' else '0' end) as if_warning_visit " +
				" from T_XB_MGR_VISIT_RECORD a,T_XB_MGR_VISIT_RECORD_PARAM c " +
				" where a.custtype=c.param_value "+wheresql.toString()+" " +
				" order by a.data_time desc";
		System.out.println(sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, MgrVisitShowQuery.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg expexcel_hz_report(XBBean bean, UserInfo userInfo) {
		AjaxMsg am = new AjaxMsg();
		try{
			String sql = "";
			String year = "";
			String month = "";
			StringBuffer wheresql = new StringBuffer();
			if(bean.getCust_id()!=null&&!"".equals(bean.getCust_id())){
				wheresql.append(" and a.bigcustno='"+bean.getCust_id()+"' ");
			}
			if(bean.getCust_mgrno()!=null&&!"".equals(bean.getCust_mgrno())){
				wheresql.append(" and b.mgr_id='"+bean.getCust_mgrno()+"' and b.mgr_id=c.mgrid ");
			}else{
				wheresql.append(" and b.mgr_id=c.mgrid(+)");
			}
			if(bean.getQuery_time()!=null&&!"".equals(bean.getQuery_time())&&!"null".equalsIgnoreCase(bean.getQuery_time())){
				String[] s = bean.getQuery_time().split("-");
				year = s[0];
				month = s[1];
			}else{
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, -1);
				year = String.valueOf(cal.get(Calendar.YEAR));
				month = String.format("%02d", cal.get(Calendar.MONTH)+1);
				System.out.println("default search:year="+year+"  month="+month);
			}
			double persent = 0.0;
			try{
				sql = "select value from t_sys_param where type='XBXT_GDFT'";
				String a = db.queryString(sql);
				persent = Double.parseDouble(a);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			sql = "select a.*,(a.fee-a.real_direct_mail_cost) as maoli,b.bigcustname,c.mgrname,c.mgrid,c.department from "+
					"(select cust_id as bigcustno,sum(send_num) as send_num,sum(standard_fee) as standard_fee,sum(fee) as fee," +
					"round(avg(discount_persent),1) as discount_persent,sum(real_direct_mail_cost) as real_direct_mail_cost," +
					"sum(last_month_send_num) as last_month_send_num,sum(direct_mail_cost) as direct_mail_cost from "+
					"(select a.sn,a.cust_id,a.destination,a.send_num,a.standard_fee,a.fee,a.mail_weight, "+
					" a.direct_mail_cost, round((a.direct_mail_cost+a.send_num*"+persent+"),1) as real_direct_mail_cost, "+
					"   round(a.mail_aver_time/24,1) as mail_aver_time,  round(a.total_aver_time/24,1) as total_aver_time, "+
					"   (case when a.standard_fee=0 then 0.0 else round((a.fee/a.standard_fee)*100,1) end) as discount_persent, "+
					"   nvl(c.send_num,0) as last_month_send_num "+
					"    from t_xb_cmur a,gnxb_area_province b,t_xb_cmur c   where instr(a.destination,b.province)>0 "+
					"    and a.year='"+year+"'  and a.month='"+month+"' "+
					"    and a.cust_id=c.cust_id(+) and a.destination=c.destination(+)  "+
					"    and c.year=to_char(add_months(trunc(to_date('"+year+month+"','yyyymm')),-1),'yyyy') "+
					"    and c.month=to_char(add_months(trunc(to_date('"+year+month+"','yyyymm')),-1),'mm')) group by cust_id) a," +
					"t_cdp b,t_cmgr c where b.bigcustno=a.bigcustno "+wheresql.toString();
			System.out.println(sql);
			@SuppressWarnings("unchecked")
			List<CustHZReport> list_data = db.query(sql, CustHZReport.class);
			am.setSuccess(true);
			am.setBackParam(list_data);
		}catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	public AjaxMsg expexcel_cmur_report(XBBean bean, UserInfo userInfo) {
		AjaxMsg am = new AjaxMsg();
		try{
			String[] s = bean.getQuery_time().split("-");
			String sql = "select a.sn,a.destination,a.send_num,a.fee,round(a.mail_aver_time/24,1) as mail_aver_time, "+
					" round(a.total_aver_time/24,1) as total_aver_time," +
					"(case when a.standard_fee=0 then 0.0 else round((a.fee/a.standard_fee)*100,1) end) as discount_persent" +
					" from t_xb_cmur a,gnxb_area_province b  "+
					" where instr(a.destination,b.province)>0 and a.cust_id='"+bean.getCust_no()+"' and a.year='"+s[0]+"' "+
					" and a.month='"+s[1]+"' order by b.sn";
			System.out.println(sql);
			@SuppressWarnings("unchecked")
			List<CustMonthUsageReport> list = db.query(sql, CustMonthUsageReport.class);
			am.setSuccess(true);
			am.setBackParam(list);
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	@SuppressWarnings("unchecked")
	public AjaxMsg expexcel_totalavertime_report(XBBean bean, UserInfo userInfo) {
		AjaxMsg am = new AjaxMsg();
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			String sql = "";
			String[] s = bean.getQuery_time().split("-");
			String year_month = s[0]+s[1];//"201511";
			sql = "select a.sn,a.area,a.province,round(a.province_aver_time/24,1) as province_aver_time, "+
					" round(a.center_aver_time/24,1) as center_aver_time, "+
					" round(a.city_aver_time/24,1) as city_aver_time, "+
					" round(a.countryside_aver_time/24,1) as countryside_aver_time,a.year_month "+
					" from t_xb_zpjsx a,gnxb_area_province b  "+
					" where a.year_month='"+year_month+"' and a.province=b.province order by b.sn";
			System.out.println("sql:"+sql);
			List<TotalAverTime> list_tat = new ArrayList<TotalAverTime>();
			list_tat = db.query(sql,TotalAverTime.class);
			map.put("list_tat", list_tat);
			List<String> list_area = new ArrayList<String>();
			for(TotalAverTime tat : list_tat){
				boolean if_contain = false;
				for(String b : list_area){
					if(b.equals(tat.getArea())){
						if_contain = true;
						break;
					}
				}
				if(!if_contain){
					list_area.add(tat.getArea());
				}
			}
			map.put("list_area", list_area);
			am.setSuccess(true);
			am.setBackParam(map);
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	@SuppressWarnings("unchecked")
	public AjaxMsg expexcel_khyyqkjb_report(XBBean bean, UserInfo userInfo) {
		AjaxMsg am = new AjaxMsg();
		try{
			String sql = "";
			String[] s = bean.getQuery_time().split("-");
			double persent = 0.3;
			if(bean.getCust_id()!=null&&!"".equals(bean.getCust_id().trim())){
				sql = "select a.sn,a.cust_id,a.destination,a.send_num,a.standard_fee,a.fee,a.mail_weight, "+
						" a.direct_mail_cost, round((a.direct_mail_cost+a.send_num*"+persent+"),1) as real_direct_mail_cost, "+
						"   round(a.mail_aver_time/24,1) as mail_aver_time,  round(a.total_aver_time/24,1) as total_aver_time, "+
						"   (case when a.standard_fee=0 then 0.0 else round((a.fee/a.standard_fee)*100,1) end) as discount_persent, "+
						"   nvl(c.send_num,0) as last_month_send_num "+
						"    from t_xb_cmur a,gnxb_area_province b,t_xb_cmur c   where instr(a.destination,b.province)>0 and  "+
						"    a.cust_id='"+bean.getCust_id().trim()+"' and a.year='"+s[0]+"'  and a.month='"+s[1]+"' "+
						"    and a.cust_id=c.cust_id(+) and a.destination=c.destination(+)  "+
						"    and c.year=to_char(add_months(trunc(to_date('"+s[0]+s[1]+"','yyyymm')),-1),'yyyy') "+
						"    and c.month=to_char(add_months(trunc(to_date('"+s[0]+s[1]+"','yyyymm')),-1),'mm')  order by b.sn";
			}else{
				System.out.println("这里不应该是空的！！！！！！！！！！！！！！！！");
			}
			System.out.println(sql);
			List<Khyyqkjb> list = new ArrayList<Khyyqkjb>();
			list = db.query(sql, Khyyqkjb.class);
			am.setSuccess(true);
			am.setBackParam(list);
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	public AjaxMsg expexcel_custwarning_report(XBBean bean, UserInfo userInfo) {
		AjaxMsg am = new AjaxMsg();
		try{
			StringBuffer whereSql=new StringBuffer();
			String q = bean.getBigcustnameorbigcustno();
			if(q!=null&&!"".equals(q)){
				if(q.matches("[0-9]*")) {//纯数字
			    	whereSql.append(" and c.bigcustno='"+q+"' ");
			    }else{
			    	whereSql.append(" and c.bigcustname like '%"+q+"%'");
			    }
			}
			String sql = "";
			sql = "select param_a||'_'||param_b as param from t_xb_cust_warning_param";
			String[] s_arr = db.queryString(sql).split("_");
			Double a = Double.parseDouble(s_arr[0]);
			Double b = Double.parseDouble(s_arr[1]);
			int n1 = 1;//1  TODO
			int n2 = n1 + 1;//2
			String connector = " and ";//or
			sql = "select c.*,d.bigcustname,nvl(k.mgrid,'') as mgrid,nvl(k.mgrname,'') as mgrname" +
					" from (select a.cust_id as bigcustno,a.destination,a.send_num as lastmonth_sendnum,b.send_num as lastlastmonth_sendnum," +
					"(case when (b.send_num-a.send_num)>"+a+" then '是' else '否' end) as condition_one,(case when ((b.send_num-a.send_num)/b.send_num)>"+b+" " +
					"then '是' else '否' end) as condition_two from (select e.cust_id,e.destination,sum(e.send_num) as send_num from t_xb_cmur e where " +
					"e.year=to_char(add_months(trunc(sysdate),-"+n1+"),'yyyy') and e.month=to_char(add_months(trunc(sysdate),-"+n1+"),'mm') and " +
					"not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.data_type='1' and m.bigcustno=e.cust_id  and m.destination=e.destination" +
					"  and to_char(add_months(trunc(m.data_time),-"+n1+"),'yyyy')=e.year and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=e.month)" +
					" and not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.data_type='2' and m.bigcustno=e.cust_id and to_char(add_months(trunc(m.data_time),-"+n1+"),'yyyy')=e.year and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=e.month) " +
					" group by e.cust_id,e.destination) a," +
					"  (select r.cust_id,r.destination,sum(r.send_num) as send_num from t_xb_cmur r where r.year=to_char(add_months(trunc(sysdate),-"+n2+"),'yyyy') " +
					"and r.month=to_char(add_months(trunc(sysdate),-"+n2+"),'mm') and not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.data_type='1' and m.bigcustno=r.cust_id  " +
					"and m.destination=r.destination and to_char(add_months(trunc(m.data_time),-"+n2+"),'yyyy')=r.year and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=r.month)" +
					" and not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.data_type='2' and m.bigcustno=r.cust_id and to_char(add_months(trunc(m.data_time),-"+n1+"),'yyyy')=r.year and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=r.month) " +
					" group by r.cust_id,r.destination) b  where a.cust_id=b.cust_id and a.destination=b.destination and " +
					"b.send_num!=0  and ((b.send_num-a.send_num)>"+a+" "+connector+" ((b.send_num-a.send_num)/b.send_num)>"+b+")) c, t_cdp d,t_cmgr k where c.bigcustno=d.bigcustno" +
					" and d.mgr_id=k.mgrid(+) "+whereSql.toString()+" " +
					"order by c.bigcustno ";
			
			System.out.println(sql);
			@SuppressWarnings("unchecked")
			List<CustWarning> list = db.query(sql, CustWarning.class);
			System.out.println(sql);
			am.setSuccess(true);
			am.setBackParam(list);
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	public AjaxMsg get_FTParam(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		
		String sql = "";
		sql = "select value from t_sys_param where type='XBXT_GDFT'";
		String value = db.queryString(sql);
		am.setSuccess(true);
		am.setMsg(value);
		return am;
	}

	public AjaxMsg set_FTParam(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		
		String sql = "";
		try{
			db.connect();
			db.startTransaction();
			sql = "update t_sys_param set value='"+bean.getFtparam()+"' where type='XBXT_GDFT'";
			if(db.update(sql)==1){
				db.commit();
				am.setSuccess(true);
				am.setMsg("参数更新成功！");
			}else{
				db.rollback();
				am.setSuccess(false);
				am.setMsg("参数更新失败！");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			db.rollback();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		finally{
			db.endTransaction();
			db.disconnect();
		}
		return am;
	}

	public PageUI query_warningtime(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "";
		
//		StringBuffer wheresql = new StringBuffer();
		
		sql = "select * from T_XBXT_TAT_WARNINGTIME";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, TotalAverTimeWarningTime.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg save_time_settings(XBBean bean, UserInfo userInfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		try{
			String sns = bean.getSns();
			Double center_time = bean.getCenter_time();
			Double city_time = bean.getCity_time();
			Double town_time = bean.getTown_time();
			if(sns!=null&&!"".equals(sns)){
				db.connect();
				db.startTransaction();
				try{
					String sql = "";
					if(sns.contains(",")){
						//multiple
						sql = "update T_XBXT_TAT_WARNINGTIME set center_time="+center_time+",city_time="+city_time+",town_time="+town_time+" where sn in ("+sns+")";
						if(db.update(sql)!=sns.split(",").length){
							am.setSuccess(false);
							am.setMsg("数据库更新失败！");
							db.rollback();
						}else{
							am.setSuccess(true);
							am.setMsg("数据库更新成功！");
							db.commit();
						}
					}else{
						//single
						sql = "update T_XBXT_TAT_WARNINGTIME set center_time="+center_time+",city_time="+city_time+",town_time="+town_time+" where sn="+sns;
						if(db.update(sql)!=1){
							am.setSuccess(false);
							am.setMsg("数据库更新失败！");
							db.rollback();
						}else{
							am.setSuccess(true);
							am.setMsg("数据库更新成功！");
							db.commit();
						}
					}
				}
				catch(Exception ex){
					db.rollback();
					am.setSuccess(false);
					am.setMsg("数据库更新失败！");
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			}
			else{
				throw new Exception ("参数传递有误！");
			}
		}
		catch(Exception ex){
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	public PageUI query_overtime1(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "";
		
		StringBuffer wheresql = new StringBuffer();
		
		if(bean.getCust_id()!=null&&!"".equals(bean.getCust_id())){
			wheresql.append(" and a.sskhdm='"+bean.getCust_id()+"' ");
		}
		if(bean.getQuery_time()!=null&&!"".equals(bean.getQuery_time())){
			String[] s = bean.getQuery_time().split("-");
			wheresql.append(" and a.sjrq='"+s[0]+s[1]+s[2]+"' ");
		}
		if(bean.getArea()!=null&&!"".equals(bean.getArea())){
			wheresql.append(" and a.sjr_shengfen='"+bean.getArea()+"' ");
		}
		if(bean.getSjj()!=null&&!"".equals(bean.getSjj())){
			wheresql.append(" and a.sjjdm='"+bean.getSjj()+"' ");
		}
		System.out.println(wheresql.toString());
		int number = 5001;
		
		/*sql = "select * from (select a.yjhm as mailno,a.sjr_shengfen as destination,a.sjrq||a.sjsj as sj_time, "
				+" a.jjrdwmc as custname,a.sskhdm as custno,(case when b.sn>0 then '已处理' else '未处理' end) as if_handle, "
				+" a.sjjdm as sjj_no,(case when a.sjjdm='21446500' then '江阴局' when a.sjjdm='21402800' then '无锡局' " 
				+" when a.sjjdm='21426600' then '宜兴局' else '未识别' end) as sjj "
				+" from GNXB_ORD_PEO_PRTCIN a,T_XB_OVERTIME_HANDLE b,T_XBXT_TAT_WARNINGTIME c  "
				+" where a.yjhm=b.mailno(+) and a.sjr_shengfen=c.destination "
				+" and (" +
				"     ((a.area_grade='省内农村') and ((a.ttrq is null and a.ttsj is null and (sysdate-to_date(a.sjrq||a.sjsj,'yyyymmddhh24miss'))>c.town_time) or ((to_date(a.ttrq||a.ttsj,'yyyymmddhh24miss')-to_date(a.sjrq||a.sjsj,'yyyymmddhh24miss'))>c.town_time)))  "
				+" or ((a.area_grade='省内市区') and ((a.ttrq is null and a.ttsj is null and (sysdate-to_date(a.sjrq||a.sjsj,'yyyymmddhh24miss'))>c.city_time) or ((to_date(a.ttrq||a.ttsj,'yyyymmddhh24miss')-to_date(a.sjrq||a.sjsj,'yyyymmddhh24miss'))>c.city_time)))  "
				+" or ((a.area_grade='省会核心区') and ((a.ttrq is null and a.ttsj is null and (sysdate-to_date(a.sjrq||a.sjsj,'yyyymmddhh24miss'))>c.center_time) or ((to_date(a.ttrq||a.ttsj,'yyyymmddhh24miss')-to_date(a.sjrq||a.sjsj,'yyyymmddhh24miss'))>c.center_time)))  " +
				") "+wheresql.toString()+") where rownum < "+number;*/
		
		//a.sjr_shengfen as destination ====> substr(a.sjrdz, 0, 20) as destination
		
		sql = "select * from (select a.yjhm as mailno,substr(a.sjrdz, 0, 20) as destination,a.sjrq||a.sjsj as sj_time, "
				+" a.jjrdwmc as custname,a.sskhdm as custno,(case when b.sn>0 then '已处理' else '未处理' end) as if_handle, "
				+" a.sjjdm as sjj_no,(case when a.sjjdm='21446500' then '江阴局' when a.sjjdm='21402800' then '无锡局' " 
				+" when a.sjjdm='21426600' then '宜兴局' else '未识别' end) as sjj "
				+" from GNXB_ORD_PEO_PRTCIN a,T_XB_OVERTIME_HANDLE b,T_XBXT_TAT_WARNINGTIME c  "
				+" where a.yjhm=b.mailno(+) and a.sjr_shengfen=c.destination "
				+" and (" +
				"     ((a.area_grade='省内农村') and ((a.ttrq is null and a.ttsj is null and (sysdate-to_date(a.sjrq||a.sjsj,'yyyymmddhh24miss'))>c.town_time)))  "
				+" or ((a.area_grade='省内市区') and ((a.ttrq is null and a.ttsj is null and (sysdate-to_date(a.sjrq||a.sjsj,'yyyymmddhh24miss'))>c.city_time)))  "
				+" or ((a.area_grade='省会核心区') and ((a.ttrq is null and a.ttsj is null and (sysdate-to_date(a.sjrq||a.sjsj,'yyyymmddhh24miss'))>c.center_time)))  " +
				") "+wheresql.toString()+") where rownum < "+number;
		
		
		System.out.println("sql===>"+sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		System.out.println("listsql===>"+listsql);
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, Overtime.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	public AjaxMsg query_overtime_mail_detail(XBBean bean) throws Exception {
		AjaxMsg am = new AjaxMsg();
		try{
			String sql = "";
			sql = "select a.yjhm as mailno,a.sjrq,a.sjsj,a.jjrxm as sender_name, "
					+ " a.jjrdz as sender_address,a.jjryzbm as sender_postcode, "
					+ " a.jjrdwmc as sender_dwmc,a.jjrdhhm as sender_phone, "
					+ " a.sskhdm as custno,a.sjrxm as receiver_name,a.sjrdz as receiver_address, "
					+ " a.sjryzbm as receiver_postcode,a.sjrdwmc as receiver_dwmc,a.sjrdhhm as receiver_phone, "
					+ " a.ttrq,a.ttsj,a.area_grade as area_type,a.sjr_shengfen as destination,c.handle_comments "
					+ " from GNXB_ORD_PEO_PRTCIN a,T_XB_OVERTIME_HANDLE c where a.yjhm=c.mailno(+) and a.yjhm='"+bean.getMailno()+"'";
			System.out.println(sql);
			@SuppressWarnings("unchecked")
			List<OvertimeDetail> list = db.query(sql,OvertimeDetail.class);
			if(list.size()>0){
				am.setSuccess(true);
				am.setMsg("success");
				am.setBackParam(list.get(0));
			}else{
				am.setSuccess(false);
				am.setMsg("失败");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	public AjaxMsg save_handle_comments(XBBean bean,UserInfo userInfo) {
		AjaxMsg am = new AjaxMsg();
		try{
			String sql = "";
			db.connect();
			db.startTransaction();
			try{
				String[] mailno_arr = bean.getMailnos().split(",");
				boolean if_success = true;
				String error_msg = "";
				for(String mailno : mailno_arr){
					if(if_success){
						sql = "select count(1) from T_XB_OVERTIME_HANDLE where mailno='"+mailno+"' ";
						if(db.queryInt(sql)==1){
							sql = "update T_XB_OVERTIME_HANDLE set handle_comments='"+bean.getHandle_comments()+"' where mailno='"+mailno+"'";
							if(db.update(sql)==1){
								
							}else{
								if_success = false;
								error_msg = "邮件号【"+mailno+"】不唯一";
							}
						}else if (db.queryInt(sql)==0){
							sql = "insert into T_XB_OVERTIME_HANDLE (sn,handle_comments,handle_user,mailno) values (SEQ_T_XB_OVERTIME_HANDLE.nextval,'"+bean.getHandle_comments()+"','"+userInfo.getUser_name()+"','"+mailno+"')";
							if(db.insert(sql)==1){
								
							}else{
								if_success = false;
								error_msg = "插入值失败";
							}
						}else{
							if_success = false;
							error_msg = "邮件号【"+mailno+"】不唯一";
						}
					}else{
						break;
					}
				}
				
				if(if_success){
					db.commit();
					am.setSuccess(true);
					am.setMsg("success");
				}else{
					db.rollback();
					am.setSuccess(false);
					am.setMsg(error_msg);
				}
			}
			catch(Exception ex){
				db.rollback();
				ex.printStackTrace();
				am.setSuccess(false);
				am.setMsg(ex.getMessage());
			}
			finally{
				db.endTransaction();
				db.disconnect();
			}
		}catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAreaComboData() {
		List<Map<String, Object>> list_map = new ArrayList<Map<String,Object>>();
		try{
			String sql = "select province as id,province as text from GNXB_AREA_PROVINCE";
			list_map = db.query(sql);
		}
		catch(Exception ex){
			ex.printStackTrace();
			list_map = new ArrayList<Map<String,Object>>();
		}
		return list_map;
	}

	public PageUI query_overtime2(Pagination pagination, XBBean bean, UserInfo userInfo) throws Exception {
		String sql = "";
		
		StringBuffer wheresql = new StringBuffer();
		
		if(bean.getCust_id()!=null&&!"".equals(bean.getCust_id())){
			wheresql.append(" and a.sskhdm='"+bean.getCust_id()+"' ");
		}
		if(bean.getQuery_time()!=null&&!"".equals(bean.getQuery_time())){
			String[] s = bean.getQuery_time().split("-");
			wheresql.append(" and a.sjrq='"+s[0]+s[1]+s[2]+"' ");
		}
		if(bean.getArea()!=null&&!"".equals(bean.getArea())){
			wheresql.append(" and a.sjr_shengfen='"+bean.getArea()+"' ");
		}
		if(bean.getSjj()!=null&&!"".equals(bean.getSjj())){
			wheresql.append(" and a.sjjdm='"+bean.getSjj()+"' ");
		}
		System.out.println(wheresql.toString());
		int number = 5001;
		sql = "select * from (select a.yjhm as mailno,a.sjr_shengfen as destination,a.sjrq||a.sjsj as sj_time,a.ttrq||a.ttsj as tt_time, "
				+" a.jjrdwmc as custname,a.sskhdm as custno,(case when b.sn>0 then '已处理' else '未处理' end) as if_handle, "
				+" a.sjjdm as sjj_no,(case when a.sjjdm='21446500' then '江阴局' when a.sjjdm='21402800' then '无锡局' " 
				+" when a.sjjdm='21426600' then '宜兴局' else '未识别' end) as sjj,to_char(b.handle_date,'yyyymmdd hh24:mi:ss') as handle_time," +
				" b.handle_comments,b.handle_user "
				+" from GNXB_ORD_PEO_PRTCIN a,T_XB_OVERTIME_HANDLE b,T_XBXT_TAT_WARNINGTIME c  "
				+" where a.yjhm=b.mailno  and a.sjr_shengfen=c.destination "
				+"  "+wheresql.toString()+") where rownum < "+number;
		System.out.println(sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, Overtime.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
}
