package com.tongdatech.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserInfo;

public class SearchDao extends BaseDao {
	private static Logger log =  Logger.getLogger(SearchDao.class);
	
	public List<?> brch(String q, UserInfo userInfo) throws Exception {
		
		StringBuffer whereSql=new StringBuffer();
		if(q!=null&&!"".equals(q)){
			if(q.matches("[0-9]*")) {//´¿Êý×Ö
		    	whereSql.append(" AND a.brch_code like '"+q+"%'");
		    }else if(q.matches("[A-Za-z]*")){//´¿×ÖÄ¸
		    	whereSql.append(" AND (a.SPELL_FULL like '"+q.toUpperCase()+"%' or a.SPELL_SHORT like '"+q.toUpperCase()+"%')");
		    }else{
		    	whereSql.append(" AND a.brch_name like '%"+q+"%'");
		    }
		    //whereSql.append(" AND a.brch_no in (select leaf_no from t_sys_brch_path where root_no="+userInfo.getBrch_no()+")");
		}else   whereSql.append(" AND c.lvl<=2");
		    
		String sql=" select a.brch_no,a.brch_name,a.brch_code,b.area_name,c.brch_path from t_sys_brch a,t_sys_area b,t_sys_brch_path c where " +
				" a.brch_no=c.leaf_no" +
				//" and c.root_up=0"+
				" and c.root_no="+userInfo.getBrch_no()+
		        " and a.area_no=b.area_no(+)" +
		        " and a.brch_flag='1' " +whereSql.toString()+
		    	" order by lpad(a.area_no,8,'0'),a.brch_mode,length(c.brch_path),length(a.brch_name) ";
		sql=db.queryPageStrOrder(sql, 1, 30);
		return db.query(sql);
	}

	@SuppressWarnings("rawtypes")
	public List<?> menu(String q, UserInfo userInfo) throws Exception {
	
		if(q==null||"".equals(q))return new ArrayList();
		String sql="select a.menu_id,a.menu_name from T_SYS_MENU a " +
		" where 1=1"+
		" and regexp_like(a.menu_name,'"+q+"','i')"+
		" and a.menu_flag ='1'";

        return db.query(sql);
	}
	
	/**
	 * µØÇøËÑË÷
	 * @author cly 
	 * @param q
	 * @param userinfo
	 * @return
	 * @throws Exception
	 */
	public List<?> area(String q,UserInfo userinfo) throws Exception{
		StringBuffer whereSql = new StringBuffer();
		if(q!=null && !"".equals(q)){
			if(q.matches("[0-9]*")){
				whereSql.append(" and a.area_no like '"+q+"%'");
			}else{
				whereSql.append(" and a.area_name like '%"+q+"%' and b.root_lvl='2'  order by b.leaf_lvl ");
			}
		}else{
			whereSql.append(" and b.root_no = '"+userinfo.getArea_no()+"'");
		}
		
		String sql = "select  a.area_no,a.area_name,a.gov_code,b.area_path from t_sys_area a,t_sys_area_path b " +
				"where a.area_no=b.leaf_no(+) ";
		sql = sql + whereSql;
		log.info(sql);
		sql = db.queryPageStrOrder(sql, 1, 30);
		
		return db.query(sql);
	}

	
	
	


}
