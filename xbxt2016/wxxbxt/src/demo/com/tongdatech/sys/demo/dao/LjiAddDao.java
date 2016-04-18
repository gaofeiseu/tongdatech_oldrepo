/**
 * File name:ParamDao.java
 * Create author:XY
 * Create date:2014-3-18
 */
package com.tongdatech.sys.demo.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.demo.bean.LjiAddObj;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
import com.tongdatech.sys.util.ParamsUtil;
import com.tongdatech.sys.util.SQLUtil;

/**
 * @author Lji
 *
 */
public class LjiAddDao extends BaseDao{
	

	@SuppressWarnings("unused")
	private static Logger log =  Logger.getLogger(LjiAddDao.class);

	public PageUI query(Pagination pagination, LjiAddObj ljiAddObj) throws Exception {
		String sql = "select * from t_sys_demo order by sn desc";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, LjiAddObj.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	public AjaxMsg deleteParam(String sns) throws Exception{
		AjaxMsg rs = new AjaxMsg();
		try{
			sns = sns.replace(",", "','");
			String sql = "delete from t_sys_demo where sn in ('"+sns+"')";
			db.delete(sql);
			
			rs.setMsg("删除成功");rs.setSuccess(true);
			
		}catch (Exception e) {
			rs.setMsg("内部错误:"+e.getMessage());
		}	
		return rs;
	}
	

	public AjaxMsg addParam(LjiAddObj ljiAddObj) {
		AjaxMsg rs = new AjaxMsg();
		
        try{
        	String sql = "select * from t_sys_demo where sn="+ljiAddObj.getSn()
        			+ " and sn not in ('"+ljiAddObj.getSn()+"')";
        	List<?> list = db.query(sql);
    		if(list.size()>0){
    			rs.setMsg("数据增加失败，同一参数类型sn值存在冲突,请在参数标志[全部]条件下查询");
    			return rs;
    		}
    		sql = "insert into t_sys_demo(sn,colint1,colint2,colstr1,colstr2,coldate1,coldate2)"+
    		"values ("
    					+ "SEQ_T_SYS_DEMO.nextval"
    					+ ","
    					+ ljiAddObj.getColint1()
    					+ ","
    					+ ljiAddObj.getColint2()
    					+ ","
    					+ "'"+ljiAddObj.getColstr1()+"'"
    					+ ","
    					+ "'"+ljiAddObj.getColstr2()+"'"
    					+ ","
    					+ SQLUtil.dateSql(ljiAddObj.getColdate1(), "yyyy-MM-dd")
    					+ ","
    					+ SQLUtil.dateSql(ljiAddObj.getColdate2(), "yyyy-MM-dd")
    					+ ")";
    		db.insert(sql);
    		rs.setMsg("新增成功");rs.setSuccess(true);
    		
        }catch (Exception e) {
        	rs.setMsg("内部错误:"+e.getMessage());
        }
		
		return rs;
		
	}

	public AjaxMsg editParam(LjiAddObj ljiAddObj) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		String sql = "select * from t_sys_demo where sn="+ ljiAddObj.getSn()
				+ " and sn not in ('"+ljiAddObj.getSn()+"')";
		try{
        	List<?> list = db.query(sql);
    		if(list.size()>0){
    			rs.setMsg("数据更新失败，同一参数类型sn值存在冲突,请在参数标志[全部]条件下查询");
    			return rs;
    		}
    		sql = "update t_sys_demo set colint1="+ ljiAddObj.getColint1()  
    			+ ",colint2="+ljiAddObj.getColint2()
    			+ ",colstr1=" + "'"+ljiAddObj.getColstr1()+ "'"
    			+ ",colstr2=" + "'"+ljiAddObj.getColstr2()+ "'"
    			+ ",coldate1=" +SQLUtil.dateSql(ljiAddObj.getColdate1(), "yyyy-MM-dd")
    			+ ",coldate2=" +SQLUtil.dateSql(ljiAddObj.getColdate2(), "yyyy-MM-dd")
				+ " where sn = "+ ljiAddObj.getSn();
		    db.update(sql);
    		rs.setMsg("更新成功");rs.setSuccess(true);
    		
        }catch (Exception e) {
        	rs.setMsg("内部错误:"+e.getMessage());
        }
		return rs;
		

	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<?, ?> load() throws Exception{
		Map mapSys  = new HashMap();
		Map mapJson  = new HashMap();
		String sql_type = "select distinct sn from t_sys_demo";
		List<Map> list = db.query(sql_type);
		Gson gson = new Gson();

		
		for (Map sn:list) {
			String sql_val = "select sn from t_sys_demo where sn='"+ sn.get("sn") + "'";
			List<Map> list1 = db.query(sql_val);
			Map tmpParam = new LinkedHashMap();
			for (Map params:list1) {
				String key=(String) params.get("value");
				String val=(String) params.get("text");
				tmpParam.put(key,val);
				
			}
			mapSys.put(sn.get("sn"), tmpParam);
			String jsonStr=gson.toJson(list1);jsonStr=jsonStr.replaceAll("\"", "'");
			mapJson.put(sn.get("sn"), jsonStr);
		}
		Map mp = new HashMap(); 
		mp.put(ParamsUtil.ParamSys , mapSys);
		mp.put(ParamsUtil.ParamJson, mapJson);
		return mp;
	}
}