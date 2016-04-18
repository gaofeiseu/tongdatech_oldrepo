package com.tongdatech.xbxt.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.xbxt.bean.XBMobile;

public class XBMobileDao extends BaseDao{

	public AjaxMsg marketing_record(XBMobile bean, String file_name,UserInfo userinfo) {
		AjaxMsg am = new AjaxMsg();
		try{
			if(userinfo.getMgr().getMgrid().equals(bean.getMgrid())){
				db.connect();
				db.startTransaction();
				try{
					String sql = "";
					sql = "select SEQ_T_XB_VISIT_WRITEPAPER.nextval from dual";
					int visit_sn = 0;
					visit_sn = db.queryInt(sql);
					sql = "insert into t_xb_mgr_visit_record (sn,mgrid,mgrname,custname,connect_name,connect_tel,custtype,comments,cust_feedback,imgname)" +
							" values ("+visit_sn+",'"+bean.getMgrid()+"','"+bean.getMgrname()+"','"+bean.getCustname()+"'," +
							"'"+bean.getConnect_name()+"','"+bean.getConnect_tel()+"','"+bean.getCusttype()+"','"+bean.getComments()+"','"+bean.getCust_feedback()+"','"+file_name+"')";
					int r = db.insert(sql);
					int n = 0;
					if(bean.getCustid()!=null&&!"".equals(bean.getCustid())){
						sql = "insert into T_XB_VISIT_WRITEPAPER (sn,bigcustno,op_id,op_name,data_type,year,month,comments,visit_sn) values" +
								" (SEQ_T_XB_VISIT_WRITEPAPER.nextval,'"+bean.getCustid()+"','"+bean.getMgrid()+"','"+bean.getMgrname()
								+"','2','"+(new SimpleDateFormat("yyyy")).format(new Date())+"','"+(new SimpleDateFormat("MM")).format(new Date())
								+"','客户经理走访预警客户',"+visit_sn+")";
						n = db.insert(sql);
					}else{
						n = 1;
					}
					if(r*n==1){
						db.commit();
						am.setSuccess(true);
						am.setMsg("提交成功~");
					}else{
						db.rollback();
						am.setSuccess(false);
						am.setMsg("很遗憾，数据插入失败。");
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
					db.rollback();
					am.setSuccess(false);
					am.setMsg("很遗憾，数据插入失败。");
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			}else{
				am.setSuccess(false);
				am.setMsg("非法权限。");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg("很遗憾，数据插入失败。");
		}
		return am;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCustType() throws Exception {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql = "";
		sql = "select * from t_xb_mgr_visit_record_param where param_type='custtype' order by order_id";
		list = db.query(sql);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> fee_query(UserInfo userinfo) throws Exception {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int n = 1;//1 TODO
		String sql = "";
		sql = "select a.sn,a.area,a.province,round(nvl(a.province_aver_time,0)/24,1) as province_aver_time," +
				"round(nvl(a.center_aver_time,0)/24,1) as center_aver_time,round(nvl(a.city_aver_time,0)/24,1) as city_aver_time," +
				"round(nvl(a.countryside_aver_time,0)/24,1) as countryside_aver_time,year_month,c.standard_fee,c.discount_fee" +
				" from t_xb_zpjsx a,gnxb_area_province b,T_XB_STANDARDFEE_PARAM c " +
				"where a.year_month=to_char(add_months(trunc(sysdate),-"+n+"),'yyyymm') and a.province=b.province " +
				"and instr(a.province,c.province)>0 order by b.sn";
		//System.out.println(sql);
		list = db.query(sql);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> visit_log_query(UserInfo userinfo) throws Exception{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		String sql = "";
		sql = "select a.sn,a.custname,a.connect_tel,b.param_text,to_char(a.data_time,'yyyy-mm-dd hh24:mi:ss') as visit_time," +
				" (case when a.imgname is not null then '有图片' else '无图片' end) as img_str" +
				" from t_xb_mgr_visit_record a,t_xb_mgr_visit_record_param b " +
				" where a.mgrid='"+userinfo.getMgr().getMgrid()+"' and a.data_time between (sysdate-30) and sysdate" +
				" and a.custtype=B.PARAM_VALUE order by a.sn desc";
		list = db.query(sql);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> cust_warning_query(UserInfo userinfo) throws Exception {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		String sql = "";
		sql = "select param_a||'_'||param_b as param from t_xb_cust_warning_param";
		String[] s_arr = db.queryString(sql).split("_");
		Double a = Double.parseDouble(s_arr[0]);
		Double b = Double.parseDouble(s_arr[1]);
		int n1 = 1;//1  TODO
		int n2 = 2;//2
		String connector = " and ";//or
//		sql = "select a.*,b.phone,b.big_addr,c.mgrid,c.mgrname from (select c.*,d.bigcustname from " +
//				"(select a.cust_id as bigcustno,a.send_num as lastmonth_sendnum,b.send_num as lastlastmonth_sendnum," +
//				"(case when (b.send_num-a.send_num)>"+a+" then '是' else '否' end) as condition_one," +
//				"(case when ((b.send_num-a.send_num)/b.send_num)>"+b+" then '是' else '否' end) as condition_two" +
//				" from (select cust_id,sum(send_num) as send_num from t_xb_cmur where year=to_char(add_months(trunc(sysdate),-"+n1+"),'yyyy')" +
//				"  and month=to_char(add_months(trunc(sysdate),-"+n1+"),'mm') group by cust_id) a," +
//				" (select cust_id,sum(send_num) as send_num from t_xb_cmur where year=to_char(add_months(trunc(sysdate),-"+n2+"),'yyyy')" +
//				" and month=to_char(add_months(trunc(sysdate),-"+n2+"),'mm') group by cust_id) b" +
//				"  where a.cust_id=b.cust_id and b.send_num!=0" +
//				" and ((b.send_num-a.send_num)>"+a+" or ((b.send_num-a.send_num)/b.send_num)>"+b+")) c,t_cdp d where c.bigcustno=d.bigcustno) a," +
//				" t_cdp b,t_cmgr c where a.bigcustno=b.bigcustno and b.mgr_id=c.mgrid and c.mgrid='"+userinfo.getMgr().getMgrid()+"'";
		
//		sql = "select c.*,d.phone,d.big_addr,d.bigcustname,nvl(k.mgrid,'') as mgrid,nvl(k.mgrname,'') as mgrname" +
//				" from (select a.cust_id as bigcustno,a.destination,a.send_num as lastmonth_sendnum,b.send_num as lastlastmonth_sendnum," +
//				"(case when (b.send_num-a.send_num)>"+a+" then '是' else '否' end) as condition_one,(case when ((b.send_num-a.send_num)/b.send_num)>"+b+" " +
//				"then '是' else '否' end) as condition_two from (select e.cust_id,e.destination,sum(e.send_num) as send_num from t_xb_cmur e where " +
//				"e.year=to_char(add_months(trunc(sysdate),-"+n1+"),'yyyy') and e.month=to_char(add_months(trunc(sysdate),-"+n1+"),'mm') and " +
//				"not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.bigcustno=e.cust_id  and m.destination=e.destination" +
//				"  and to_char(add_months(trunc(m.data_time),-"+n1+"),'yyyy')=e.year and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=e.month) group by e.cust_id,e.destination) a," +
//				"  (select r.cust_id,r.destination,sum(r.send_num) as send_num from t_xb_cmur r where r.year=to_char(add_months(trunc(sysdate),-"+n2+"),'yyyy') " +
//				"and r.month=to_char(add_months(trunc(sysdate),-"+n2+"),'mm') and not exists(select 1 from T_XB_VISIT_WRITEPAPER m where m.bigcustno=r.cust_id  " +
//				"and m.destination=r.destination and to_char(add_months(trunc(m.data_time),-"+n2+"),'yyyy')=r.year and to_char(add_months(trunc(m.data_time),-"+n1+"),'mm')=r.month) group by r.cust_id,r.destination) b  where a.cust_id=b.cust_id and a.destination=b.destination and " +
//				"b.send_num!=0  and ((b.send_num-a.send_num)>"+a+" or ((b.send_num-a.send_num)/b.send_num)>"+b+")) c, t_cdp d,t_cmgr k where c.bigcustno=d.bigcustno" +
//				" and d.mgr_id=k.mgrid and k.mgrid='"+userinfo.getMgr().getMgrid()+"' order by c.bigcustno ";
		
		sql = "select c.*,d.phone,d.big_addr,d.bigcustname,nvl(k.mgrid,'') as mgrid,nvl(k.mgrname,'') as mgrname" +
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
				" and d.mgr_id=k.mgrid and k.mgrid='"+userinfo.getMgr().getMgrid()+"' order by c.bigcustno ";
		System.out.println(sql);
		list = db.query(sql);
		return list;
	}

	public AjaxMsg ajax_find_cust(XBMobile bean) throws Exception {
		AjaxMsg am = new AjaxMsg();
		String q = bean.getCustname();
		StringBuffer whereSql=new StringBuffer();
		if(q!=null&&!"".equals(q)){
			if(q.matches("[0-9]*")) {//纯数字
		    	whereSql.append(" and a.bigcustno='"+q+"' ");
		    }else{
		    	whereSql.append(" and a.bigcustname like '%"+q+"%'");
		    }
		}
		String y = bean.getYear();
		String m = bean.getMonth();
		String sql = "";
		sql = "select a.bigcustno,a.bigcustname,a.cust_id,a.mgr_id,a.phone,a.big_addr,'"+y+"' as year,'"+m+"' as month from t_cdp a "+
				"where exists(select 1 from t_xb_cmur b where b.cust_id=a.bigcustno and b.year='"+y+"' and b.month='"+m+"') "+whereSql.toString();
		//System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = db.query(sql);
		am.setSuccess(true);
		am.setBackParam(list);
		return am;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> init_cust_report_detail_section(UserInfo userinfo, XBMobile bean) throws Exception {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String sql = "";
		sql = "select a.destination,to_char(a.send_num) as send_num,a.fee,round(a.mail_aver_time/24,1) as mail_aver_time, "+
				" round(a.total_aver_time/24,1) as total_aver_time "+
				" from t_xb_cmur a,gnxb_area_province b where a.cust_id='"+bean.getCustid()+"' and a.year='"+bean.getYear()+"' and a.month='"+bean.getMonth()+"' "+
				" and instr(a.destination,b.province)>0 order by b.sn";
		list = db.query(sql);
		return list;
	}

	
	
	
}
