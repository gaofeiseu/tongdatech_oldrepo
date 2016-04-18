package com.tongdatech.business.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.tongdatech.business.bean.RemindBean;
import com.tongdatech.business.bean.RemindReturnBean;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.pojo.Pagination;

public class RemindDao extends BaseDao{

	public RemindReturnBean getNum(RemindBean bean) throws Exception{
		RemindReturnBean returnBean = new RemindReturnBean();
		String now_time = getNowTimeString();
		String next_time = getNextTimeString();
		String sql = "";
		try{
			if("1".equals(bean.getQuery_type())){
				//定期到期客户提醒
				sql = "SELECT count(*) FROM MAP_DINGQI_ALL LEFT OUTER JOIN ZX_CUST_ZICHAN_ALL_SJ " +
						"ON MAP_DINGQI_ALL.cust_p_id= ZX_CUST_ZICHAN_ALL_SJ.cust_p_id WHERE status='0'" +
						" AND daoqi_date>='"+now_time+"' AND daoqi_date<='"+next_time+"' and open_office_code='"+bean.getUser_root_brch()+"' ";
				String num_str = db.queryString(sql);
				returnBean.setIf_success(true);
				returnBean.setMsg(num_str);
			}else if("2".equals(bean.getQuery_type())){
				//一本通到期客户提醒
				sql = "SELECT count(*) FROM MAP_YBT_REBUILD LEFT OUTER JOIN ZX_CUST_ZICHAN_ALL_SJ" +
						" ON MAP_YBT_REBUILD.cust_p_id= ZX_CUST_ZICHAN_ALL_SJ.cust_p_id WHERE status='0'" +
						" AND daoqi_date>='"+now_time+"' AND daoqi_date<='"+next_time+"' and open_office_code='"+bean.getUser_root_brch()+"' ";
				String num_str = db.queryString(sql);
				returnBean.setIf_success(true);
				returnBean.setMsg(num_str);
			}else if("3".equals(bean.getQuery_type())){
				//保险到期客户提醒
				sql = "SELECT count(*) FROM BAOXIAN_ALL WHERE POL_STAT_FLAG IS NULL AND SHUHUI_TAG IS NULL" +
						" AND POL_FINAL_DATE >='"+now_time+"' and POL_FINAL_DATE <='"+next_time+"' and open_office_code='"+bean.getUser_root_brch()+"' ";
				String num_str = db.queryString(sql);
				returnBean.setIf_success(true);
				returnBean.setMsg(num_str);
			}else if("4".equals(bean.getQuery_type())){
				//理财到期客户提醒
				sql = "SELECT count(*) FROM MAP_LICAI_ALL LEFT OUTER JOIN ZX_CUST_ZICHAN_ALL_SJ" +
						" ON MAP_LICAI_ALL.cust_p_id= ZX_CUST_ZICHAN_ALL_SJ.cust_p_id " +
						"WHERE ENDDATE>='"+now_time+"' AND ENDDATE<='"+next_time+"' and open_office_code='"+bean.getUser_root_brch()+"' ";
				String num_str = db.queryString(sql);
				returnBean.setIf_success(true);
				returnBean.setMsg(num_str);
			}else if("5".equals(bean.getQuery_type())){
				//储蓄式国债到期客户提醒
				sql = "SELECT count(*) FROM MAP_CHUXU_GUOZHAI LEFT OUTER JOIN ZX_CUST_ZICHAN_ALL_SJ" +
						" ON MAP_CHUXU_GUOZHAI.cust_p_id= ZX_CUST_ZICHAN_ALL_SJ.cust_p_id" +
						" WHERE END_DATE>='"+now_time+"' AND END_DATE<='"+next_time+"' and open_office_code='"+bean.getUser_root_brch()+"' ";
				String num_str = db.queryString(sql);
				returnBean.setIf_success(true);
				returnBean.setMsg(num_str);
			}else if("6".equals(bean.getQuery_type())){
				//凭证式国债到期客户提醒
				sql = "SELECT count(*) FROM MAP_PZ_GUOZHAI LEFT OUTER JOIN ZX_CUST_ZICHAN_ALL_SJ" +
						" ON MAP_PZ_GUOZHAI.cust_p_id= ZX_CUST_ZICHAN_ALL_SJ.cust_p_id" +
						" WHERE END_DATE>='"+now_time+"' AND END_DATE<='"+next_time+"' and open_office_code='"+bean.getUser_root_brch()+"' ";
				String num_str = db.queryString(sql);
				returnBean.setIf_success(true);
				returnBean.setMsg(num_str);
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("选中的类型不对！");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("ERROR！");
		}
		return returnBean;
	}
	private String getNowTimeString(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date());
	}
	private String getNextTimeString(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, 14);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(calendar.getTime());
	}
	public RemindReturnBean getDetail(Pagination pagination, RemindBean bean) {
		RemindReturnBean returnBean = new RemindReturnBean();
		String sql = "";
		String now_time = getNowTimeString();
		String next_time = getNextTimeString();
		if("1".equals(bean.getQuery_type())){
			sql = "SELECT MAP_DINGQI_ALL.custname,"+
					"ZX_CUST_ZICHAN_ALL_SJ.sj,"+
					"ZX_CUST_ZICHAN_ALL_SJ.dh,"+
					"MAP_DINGQI_ALL.account_no,"+
					"MAP_DINGQI_ALL.chuxu_type,"+
					"MAP_DINGQI_ALL.money_final,"+
					"MAP_DINGQI_ALL.status,"+
					"MAP_DINGQI_ALL.open_office_code,"+
					"MAP_DINGQI_ALL.trade_date,"+
					"MAP_DINGQI_ALL.daoqi_date,"+
					"MAP_DINGQI_ALL.cust_p_id"+
					" FROM MAP_DINGQI_ALL "+
					" LEFT OUTER JOIN ZX_CUST_ZICHAN_ALL_SJ"+
					" ON MAP_DINGQI_ALL.cust_p_id= ZX_CUST_ZICHAN_ALL_SJ.cust_p_id"+
					" WHERE status               ='0'"+
					" AND daoqi_date            >='"+now_time+"'"+
					//" AND daoqi_date            <='"+next_time+"' ";
					" AND daoqi_date            <='"+next_time+"'  and open_office_code='"+bean.getUser_root_brch()+"' ";
		}else if("2".equals(bean.getQuery_type())){
			sql = "SELECT MAP_YBT_REBUILD.custname,"+
						"ZX_CUST_ZICHAN_ALL_SJ.sj,"+
						"ZX_CUST_ZICHAN_ALL_SJ.dh,"+
						"MAP_YBT_REBUILD.account_no,"+
						"MAP_YBT_REBUILD.chuxu_type,"+
						"MAP_YBT_REBUILD.money,"+
						"MAP_YBT_REBUILD.status,"+
						"MAP_YBT_REBUILD.open_office_code,"+
						"MAP_YBT_REBUILD.trade_date,"+
						"MAP_YBT_REBUILD.daoqi_date,"+
						"MAP_YBT_REBUILD.cust_p_id"+
						" FROM MAP_YBT_REBUILD"+
						" LEFT OUTER JOIN ZX_CUST_ZICHAN_ALL_SJ"+
						" ON MAP_YBT_REBUILD.cust_p_id= ZX_CUST_ZICHAN_ALL_SJ.cust_p_id"+
						" WHERE status                ='0'"+
						" AND daoqi_date             >='"+now_time+"'"+
						//" AND daoqi_date             <='"+next_time+"' ";
						" AND daoqi_date             <='"+next_time+"'  and open_office_code='"+bean.getUser_root_brch()+"' ";
		}else if("3".equals(bean.getQuery_type())){
			sql = "SELECT OPEN_OFFICE_CODE,"+
					"baoxian_no,"+
					"'保险公司' AS BXGS,"+
					"TYPE,"+
					"PASS_NO,"+
					"CUSTNAME,"+
					"'投保人手机号'  AS phone,"+
					"'投保人办公电话' AS tel,"+
					"'投保人通讯地址' AS address,"+
					"BAOFEI,"+
					"ACCOUNT_ORG,"+
					"POL_STAT_FLAG,"+
					"TRADE_DATE,"+
					"POL_FINAL_DATE,"+
					"CUST_P_ID"+
					" FROM BAOXIAN_ALL"+
					" WHERE POL_STAT_FLAG IS NULL"+
					" AND SHUHUI_TAG      IS NULL"+
					" AND POL_FINAL_DATE  >='"+now_time+"'"+
					//" AND POL_FINAL_DATE  <='"+next_time+"'  ";
					" AND POL_FINAL_DATE  <='"+next_time+"'  and open_office_code='"+bean.getUser_root_brch()+"' ";
		}else if("4".equals(bean.getQuery_type())){
			sql = "SELECT MAP_LICAI_ALL.OPEN_OFFICE_CODE,"+
					"MAP_LICAI_ALL.CONTRACTNO,"+
					"MAP_LICAI_ALL.PRODUCT_NAME,"+
					"MAP_LICAI_ALL.PSN_18,"+
					"MAP_LICAI_ALL.CUSTNAME,"+
					"ZX_CUST_ZICHAN_ALL_SJ.sj,"+
					"ZX_CUST_ZICHAN_ALL_SJ.dh,"+
					"MAP_LICAI_ALL.MONEY_FINAL,"+
					"MAP_LICAI_ALL.ACCOUNT_NO,"+
					"MAP_LICAI_ALL.TRADE_DATE,"+
					"MAP_LICAI_ALL.ENDDATE,"+
					"MAP_LICAI_ALL.CUST_P_ID"+
					" FROM MAP_LICAI_ALL"+
					" LEFT OUTER JOIN ZX_CUST_ZICHAN_ALL_SJ"+
					" ON MAP_LICAI_ALL.cust_p_id= ZX_CUST_ZICHAN_ALL_SJ.cust_p_id"+
					" WHERE ENDDATE            >='"+now_time+"'"+
					" AND ENDDATE              <='"+next_time+"'  and open_office_code='"+bean.getUser_root_brch()+"' ";
		}else if("5".equals(bean.getQuery_type())){
			sql = "SELECT MAP_CHUXU_GUOZHAI.OPEN_OFFICE_CODE,"+
					"MAP_CHUXU_GUOZHAI.PRODUCT_NAME,"+
					"MAP_CHUXU_GUOZHAI.PSN_18,"+
					"MAP_CHUXU_GUOZHAI.CUSTNAME ,"+
					"ZX_CUST_ZICHAN_ALL_SJ.sj,"+
					"ZX_CUST_ZICHAN_ALL_SJ.dh,"+
					"MAP_CHUXU_GUOZHAI.MONEY_FINAL,"+
					"MAP_CHUXU_GUOZHAI.ACCOUNT_NO,"+
					"MAP_CHUXU_GUOZHAI.TRADE_DATE,"+
					"MAP_CHUXU_GUOZHAI.END_DATE,"+
					"MAP_CHUXU_GUOZHAI.CUST_P_ID"+
					" FROM MAP_CHUXU_GUOZHAI"+
					" LEFT OUTER JOIN ZX_CUST_ZICHAN_ALL_SJ"+
					" ON MAP_CHUXU_GUOZHAI.cust_p_id= ZX_CUST_ZICHAN_ALL_SJ.cust_p_id"+
					" WHERE END_DATE               >='"+now_time+"'"+
					" AND END_DATE                 <='"+next_time+"'  and open_office_code='"+bean.getUser_root_brch()+"' ";
		}else if("6".equals(bean.getQuery_type())){
			sql = "SELECT MAP_PZ_GUOZHAI.OPEN_OFFICE_CODE,"+
					"MAP_PZ_GUOZHAI.PRODUCT_NAME,"+
					"MAP_PZ_GUOZHAI.PSN_18,"+
					"MAP_PZ_GUOZHAI.CUSTNAME ,"+
					"ZX_CUST_ZICHAN_ALL_SJ.sj,"+
					"ZX_CUST_ZICHAN_ALL_SJ.dh,"+
					"MAP_PZ_GUOZHAI.MONEY_FINAL,"+
					"MAP_PZ_GUOZHAI.ACCOUNT_NO,"+
					"MAP_PZ_GUOZHAI.TRADE_DATE,"+
					"MAP_PZ_GUOZHAI.END_DATE,"+
					"MAP_PZ_GUOZHAI.CUST_P_ID"+
					" FROM MAP_PZ_GUOZHAI"+
					" LEFT OUTER JOIN ZX_CUST_ZICHAN_ALL_SJ"+
					" ON MAP_PZ_GUOZHAI.cust_p_id= ZX_CUST_ZICHAN_ALL_SJ.cust_p_id"+
					" WHERE END_DATE            >='"+now_time+"'"+
					" AND END_DATE              <='"+next_time+"'  and open_office_code='"+bean.getUser_root_brch()+"' ";
		}
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),
				pagination.getEdnum());
		try{
			returnBean.setRows(db.query(listsql, RemindBean.class));
			int total = db.count(sql);
			System.out.println("total:"+total);
			returnBean.setTotal(total);
			pagination.setTotal(total);
		}
		catch(Exception ex){
			ex.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(ex.getMessage());
		}
		return returnBean;
	}

	
	
	
	
}
