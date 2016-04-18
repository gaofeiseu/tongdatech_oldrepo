/**
 * File name:VisitDao.java
 * Create author:
 * Create date:
 */
package com.tongdatech.business.dao;

 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tongdatech.business.bean.CustZcBean;
import com.tongdatech.business.bean.CustZcMxBean;
import com.tongdatech.sys.base.BaseDao;

import com.tongdatech.sys.bean.UserInfo;

import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;


/**
 * @author 
 * 
 */
public class CustZcDao extends BaseDao {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(CustZcDao.class);
	public PageUI query(Pagination pagination, CustZcBean custZcBean,UserInfo userInfo) throws Exception {

		
		StringBuffer whereSql = new  StringBuffer();
		String brch_no="";
		if (custZcBean.getSex() != null && custZcBean.getSex().length() > 0) {
			whereSql.append(" and a.sex ='" + custZcBean.getSex()+"'");
		}
		
		if (custZcBean.getPass_no() != null && custZcBean.getPass_no().length() > 0) {
			whereSql.append(" and a.pass_no like '%" + custZcBean.getPass_no()+"%'");
		}
		
		if (custZcBean.getCustname() != null && custZcBean.getCustname().length() > 0) {
			whereSql.append(" and a.custname like '%" + custZcBean.getCustname()+"%'");
		}
		if (custZcBean.getManager_name() != null && custZcBean.getManager_name().length() > 0) {
			whereSql.append(" and b.manager_name = '" + custZcBean.getManager_name()+"'");
		}
		
		if (custZcBean.getJiesuan_date() != null && custZcBean.getJiesuan_date().length() > 0) {
			whereSql.append(" and a.jiesuan_date = '" + custZcBean.getJiesuan_date()+"'");
		}
		if (custZcBean.getAge_min() != null && custZcBean.getAge_min().length() > 0) {
			whereSql.append(" and a.age >= " + custZcBean.getAge_min());
		}
		if (custZcBean.getAge_max() != null && custZcBean.getAge_max().length() > 0) {
			whereSql.append(" and a.age <= " + custZcBean.getAge_max());
		}
		if (custZcBean.getDingqi_value_min() != null && custZcBean.getDingqi_value_min().length() > 0) {
			whereSql.append(" and a.dingqi_value >= " + custZcBean.getDingqi_value_min());
		}
		if (custZcBean.getDingqi_value_max() != null && custZcBean.getDingqi_value_max().length() > 0) {
			whereSql.append(" and a.dingqi_value <=" + custZcBean.getDingqi_value_max());
		}
		if (custZcBean.getHuoqi_value_min() != null && custZcBean.getHuoqi_value_min().length() > 0) {
			whereSql.append(" and a.huoqi_value >= " + custZcBean.getHuoqi_value_min());
		}
		if (custZcBean.getHuoqi_value_max() != null && custZcBean.getHuoqi_value_max().length() > 0) {
			whereSql.append(" and a.huoqi_value <= " + custZcBean.getHuoqi_value_max());
		}
		if (custZcBean.getBaofei_min() != null && custZcBean.getBaofei_min().length() > 0) {
			whereSql.append(" and a.baofei >= " + custZcBean.getBaofei_min());
		}
		if (custZcBean.getBaofei_max() != null && custZcBean.getBaofei_max().length() > 0) {
			whereSql.append(" and a.baofei <= " + custZcBean.getBaofei_max());
		}
		if (custZcBean.getLicai_min() != null && custZcBean.getLicai_min().length() > 0) {
			whereSql.append(" and a.licai >= " + custZcBean.getLicai_min());
		}
		if (custZcBean.getLicai_max() != null && custZcBean.getLicai_max().length() > 0) {
			whereSql.append(" and a.licai <= " + custZcBean.getLicai_max());
		}
		
		if (custZcBean.getZichan_all_min() != null && custZcBean.getZichan_all_min().length() > 0) {
			whereSql.append(" and a.zichan_all >= " + custZcBean.getZichan_all_min());
		}
		if (custZcBean.getZichan_all_max() != null && custZcBean.getZichan_all_max().length() > 0) {
			whereSql.append(" and a.zichan_all <= " + custZcBean.getZichan_all_max());
		}
		if (custZcBean.getQudao_weidu_sum_min() != null && custZcBean.getQudao_weidu_sum_min().length() > 0) {
			whereSql.append(" and a.qudao_weidu_sum >= " + custZcBean.getQudao_weidu_sum_min());
		}
		if (custZcBean.getQudao_weidu_sum_max() != null && custZcBean.getQudao_weidu_sum_max().length() > 0) {
			whereSql.append(" and a.qudao_weidu_sum <= " + custZcBean.getQudao_weidu_sum_max());
		}
		
		if (custZcBean.getGuozhai_min() != null && custZcBean.getGuozhai_min().length() > 0) {
			whereSql.append(" and a.Guozhai >= " + custZcBean.getGuozhai_min());
		}
		if (custZcBean.getGuozhai_max() != null && custZcBean.getGuozhai_max().length() > 0) {
			whereSql.append(" and a.Guozhai <= " + custZcBean.getGuozhai_max());
		}
		
		if (custZcBean.getFeiying_tag() != null && custZcBean.getFeiying_tag().length() > 0) {
			whereSql.append(" and a.feiying_tag = '" + custZcBean.getFeiying_tag()+"'");
		}else
		{
			whereSql.append(" and a.feiying_tag  is null " );
		}
		
		String sql ="select  "
			+"a.CUST_P_ID           ,"
			+"a.OFFICE_CODE         ,"
			+"a.CUST_P_ID_OLD       ,"
			+"decode(a.DINGQI_VALUE,0,to_char(a.DINGQI_VALUE),null,to_char(a.DINGQI_VALUE),'*'||substr(a.DINGQI_VALUE,2))       DINGQI_VALUE           ,"
			+"decode(a.HUOQI_VALUE,0,to_char(a.HUOQI_VALUE),null,to_char(a.HUOQI_VALUE),'*'||substr(a.HUOQI_VALUE,2))       HUOQI_VALUE         ,"
			+"decode(a.BAOFEI,0,to_char(a.baofei),null,to_char(a.baofei),'*'||substr(a.baofei,2))       baofei       ,"
			+"decode(a.LICAI,0,to_char(a.LICAI),null,to_char(a.LICAI),'*'||substr(a.LICAI,2))       LICAI       ,        "     
			+"decode(a.PZ_GUOZHAI,0,to_char(a.PZ_GUOZHAI),null,to_char(a.PZ_GUOZHAI),'*'||substr(a.PZ_GUOZHAI,2))       PZ_GUOZHAI       , "  
			+"decode(a.CHUXU_GUOZHAI,0,to_char(a.CHUXU_GUOZHAI),null,to_char(a.CHUXU_GUOZHAI),'*'||substr(a.CHUXU_GUOZHAI,2))       CHUXU_GUOZHAI       ,   "
			+"decode(a.GUOZHAI,0,to_char(a.GUOZHAI),null,to_char(a.GUOZHAI),'*'||substr(a.GUOZHAI,2))       GUOZHAI       ,   "
			+"decode(a.ZICHAN_ALL,0,to_char(a.ZICHAN_ALL),null,to_char(a.ZICHAN_ALL),'*'||substr(a.ZICHAN_ALL,2))       ZICHAN_ALL       ,  "
			+"decode(a.DK_POWER,1,'已加办','未加办' )          DK_POWER,"
			+"decode(a.DK_TV ,1,'已加办','未加办' )            DK_TV    ,"
			+"decode(a.DK_WATER  ,1,'已加办','未加办' )        DK_WATER    ,"
			+"decode(a.DK_GAS   ,1,'已加办','未加办' )         DK_GAS    ,"
			+"decode(a.DK_MED   ,1,'已加办','未加办' )         DK_MED    ,"
			+"decode(a.DK_TELEC ,1,'已加办','未加办' )         DK_TELEC    ,"
			+"decode(a.DK_MPHONE ,1,'已加办','未加办' )        DK_MPHONE    ,"
			+"decode(a.DF      ,1,'已加办','未加办' )          DF    ,"
			+"decode(a.NETBANK  ,1,'已加办','未加办' )         NETBANK     ,"
			+"decode(a.QUDAO_WEIDU_SUM  ,0,'未加办',null,'未加办', a.QUDAO_WEIDU_SUM)   QUDAO_WEIDU_SUM    ,"
			+"'***'||substr(a.PASS_NO,4)           PASS_NO  ,"
			+"a.OPEN_OFFICE_CODE    ,"
			+"a.ZICHAN_GRADE        ,"
			+"a.SJ                  ,"
			+"a.DH                  ,"
			+"a.CUSTNAME            ,"
			+"decode(a.FEIYING_TAG,'1','非营客户','营业客户'  )    FEIYING_TAG   ,"
			+"a.RANK                ,"
			+"a.JIESUAN_DATE        ,"
			+"a.AGE                 ,"
			+"a.SEX                 ,"
			+"b.manager_name,"
			+"F_BrchName(a.OPEN_OFFICE_CODE) brch_name"
			
			+" from ZX_CUST_ZICHAN_ALL a left join ZX_CUST_ZICHAN_ALL_BR b  on a.cust_p_id=b.cust_p_id  where a.OPEN_OFFICE_CODE='"+userInfo.getBrch_no()+"'"+whereSql+" order by a.zichan_all desc";
		
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		System.out.println(listsql);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

		rs.setRows(db.query(listsql, CustZcBean.class));
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

		int total = db.count(sql);
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	public List<Map> queryManager(UserInfo userInfo) throws Exception {
		
		String sql="select user_name from t_sys_user where brch_no='"+userInfo.getBrch_no()+"'";
		return db.query(sql);
	}
	public int save(CustZcBean custZcBean,UserInfo userInfo) throws Exception{
		
		int r = 0;
		String cust_p_id[]=custZcBean.getCust_p_id().split(",");
		for(int i=0;i<cust_p_id.length;i++)
		{
			if(!"".equals(cust_p_id[i])&&cust_p_id[i]!=null)
			{
				String sql ="select * from ZX_CUST_ZICHAN_ALL_BR where CUST_P_ID='"+cust_p_id[i]+"'";
				int total = db.count(sql);
				if(total==0)
				{
					sql = "insert into ZX_CUST_ZICHAN_ALL_BR(CUST_P_ID,BRCH_NO,MANAGER_NAME,CUSTNAME,STATUS) " +
						"values('"+cust_p_id[i]+"','"+userInfo.getBrch_no()+"','"+custZcBean.getManager_name()+"','','1')";
					r = db.insert(sql);
				}
				else
				{
					sql = "update  ZX_CUST_ZICHAN_ALL_BR set MANAGER_NAME='"+custZcBean.getManager_name()+"' where CUST_P_ID='"+cust_p_id[i]+"'";
					r = db.update(sql);
				}
				db.commit();
			}
		}
		
		return r;
		
	}
	
	public PageUI queryDingqi(Pagination pagination,String cust_p_id) throws Exception {
		String sql="Select substr(account_no,1,length(account_no)-4)||'****' account_no, " +
				"decode(sign(money_final-0),0,to_char(money_final),1,'*'||substr(money_final,2),-1,substr(money_final,1,1)||'*'||substr(money_final,3)) money_final," +
				"trade_date," +
				"daoqi_date ,F_PARAMS('CHUXU_TYPE',chuxu_type) chuxu_type " +
				" from MAP_DINGQI_ALL Where (status='0' or status='db') and cust_p_id='"+cust_p_id+"'";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, CustZcMxBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	public PageUI queryYibentong(Pagination pagination,String cust_p_id) throws Exception {
		String sql="Select substr(account_no,1,length(account_no)-4)||'****' account_no, " +
				"decode(sign(money-0),0,to_char(money),1,'*'||substr(money,2),-1,substr(money,1,1)||'*'||substr(money,3)) money," +
				"trade_date," +
				"daoqi_date,F_PARAMS('CHUXU_TYPE',chuxu_type) chuxu_type " +
				" from MAP_YBT_REBUILD Where ( status='0' or status='db') and cust_p_id='"+cust_p_id+"'";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, CustZcMxBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	public PageUI queryHuoqi(Pagination pagination,String cust_p_id) throws Exception {
		String sql="Select substr(account_no,1,length(account_no)-4)||'****' account_no, " +
				"decode(sign(money-0),0,to_char(money),1,'*'||substr(money,2),-1,substr(money,1,1)||'*'||substr(money,3)) money," +
				"weidu_sum,dk_power,dk_tv,dk_water,dk_gas,dk_med,dk_telec,dk_mphone,"+
				"df,netbank"+
				" from MAP_HUOQI_HZ_WEIDU Where cust_p_id='"+cust_p_id+"'";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, CustZcMxBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	public PageUI queryBaoXian(Pagination pagination,String cust_p_id) throws Exception {
		String sql="Select substr(baoxian_no,1,length(baoxian_no)-4)||'****' baoxian_no, " +
				"decode(sign(baofei-0),0,to_char(baofei),1,'*'||substr(baofei,2),-1,substr(baofei,1,1)||'*'||substr(baofei,3)) baofei," +
				"trade_date,pol_final_date,type"+
				
				" from BAOXIAN_ALL Where cust_p_id='"+cust_p_id+"' and  shuhui_tag is null and pol_stat_flag is null";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, CustZcMxBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	public PageUI queryLicai(Pagination pagination,String cust_p_id) throws Exception {
		String sql="Select substr(contractno,1,length(contractno)-4)||'****' contractno, " +
				"decode(sign(money_final-0),0,to_char(money_final),1,'*'||substr(money_final,2),-1,substr(money_final,1,1)||'*'||substr(money_final,3)) money_final," +
				"trade_date,enddate,product_name"+
				
				" from MAP_LICAI_ALL Where cust_p_id='"+cust_p_id+"'  and enddate>=to_char(sysdate,'yyyymmdd')";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, CustZcMxBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	public PageUI queryCxguozhai(Pagination pagination,String cust_p_id) throws Exception {
		String sql="Select substr(account_no,1,length(account_no)-4)||'****' account_no, " +
				"decode(sign(money_final-0),0,to_char(money_final),1,'*'||substr(money_final,2),-1,substr(money_final,1,1)||'*'||substr(money_final,3)) money_final," +
				"trade_date,end_date,product_name"+
				
				" from MAP_CHUXU_GUOZHAI Where  money_final  is not null  and cust_p_id='"+cust_p_id+"'  and end_date>=to_char(sysdate,'yyyymmdd')";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, CustZcMxBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	public PageUI queryPzguozhai(Pagination pagination,String cust_p_id) throws Exception {
		String sql="Select substr(accountcode,1,length(accountcode)-4)||'****' accountcode, " +
				"decode(sign(money_final-0),0,to_char(money_final),1,'*'||substr(money_final,2),-1,substr(money_final,1,1)||'*'||substr(money_final,3)) money_final," +
				"trade_date,end_date,product_name"+
				
				" from MAP_PZ_GUOZHAI Where  money_final  is not null  and cust_p_id='"+cust_p_id+"'  and end_date>=to_char(sysdate,'yyyymmdd')";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, CustZcMxBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	
 
}
