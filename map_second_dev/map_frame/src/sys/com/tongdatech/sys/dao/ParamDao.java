/**
 * File name:ParamDao.java
 * Create author:XY
 * Create date:2014-3-18
 */
package com.tongdatech.sys.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.Param;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
import com.tongdatech.sys.util.ParamsUtil;

/**
 * @author XY
 *
 */
public class ParamDao extends BaseDao{
	

	@SuppressWarnings("unused")
	private static Logger log =  Logger.getLogger(ParamDao.class);

	public PageUI query(Pagination pagination, Param param) throws Exception {

		
		StringBuffer whereSql = new  StringBuffer();
		
		String flag = param.getFlag();
		if(flag!=null&&!flag.equals("2")){
			whereSql.append(" and flag='"+flag+"'");
		}
		if (param.getType() != null && param.getType().length() > 0) {
			whereSql.append("and type like '%" + param.getType().toUpperCase()+ "%'");
		}
		if (param.getText() != null && param.getText().length() > 0) {
			whereSql.append(" and text like '%" + param.getText() + "%'");
		}
		String sql = "select * from t_sys_param where 1=1"+whereSql+"order by type,order_id";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, Param.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	public AjaxMsg deleteParam(String sns) throws Exception{
		AjaxMsg rs = new AjaxMsg();
		try{

			
			sns = sns.replace(",", "','");
			String sql = "update t_sys_param set flag = '0' where sn in ('"+sns+"')";
			db.delete(sql);
			
			rs.setMsg("删除成功");rs.setSuccess(true);
			
		}catch (Exception e) {
			rs.setMsg("内部错误:"+e.getMessage());
		}	
		return rs;
	}
	

	public AjaxMsg addParam(Param param) {
		AjaxMsg rs = new AjaxMsg();
		
        try{
        	String sql = "select * from t_sys_param where type='"+param.getType().toUpperCase()+"' and value='"+param.getValue()+"'";
        	List<?> list = db.query(sql);
    		if(list.size()>0){
    			rs.setMsg("数据增加失败，同一参数类型value值存在冲突,请在参数标志[全部]条件下查询");
    			return rs;
    		}
    		sql = "insert into t_sys_param(sn,type,value,text,flag,order_id) values (SEQ_T_SYS_PARAM.NEXTVAL,'"
    					+ param.getType().toUpperCase()
    					+ "','"
    					+ param.getValue()
    					+ "','"
    					+ param.getText()
    					+ "','"
    					+ param.getFlag()
    					+ "','"
    					+ param.getOrder_id() + "')";
    		db.insert(sql);
    		rs.setMsg("新增成功");rs.setSuccess(true);
    		
        }catch (Exception e) {
        	rs.setMsg("内部错误:"+e.getMessage());
        }
		
		return rs;
		
	}

	public AjaxMsg editParam(Param param) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		String sql = "select * from t_sys_param where type='"
				+ param.getType().toUpperCase() + "' and value='"
				+ param.getValue() + "' and sn not in ('"+param.getSn()+"')";
		try{
        	List<?> list = db.query(sql);
    		if(list.size()>0){
    			rs.setMsg("数据更新失败，同一参数类型value值存在冲突,请在参数标志[全部]条件下查询");
    			return rs;
    		}
    		sql = "update t_sys_param set type='"
				+ param.getType().toUpperCase() + "'" + ",value='"
				+ param.getValue() + "',text='" + param.getText()
				+ "',flag='" + param.getFlag() + "',order_id='"
				+ param.getOrder_id() + "' where sn = '"
				+ param.getSn() + "'";
		    db.update(sql);
    		rs.setMsg("更新成功");rs.setSuccess(true);
    		
        }catch (Exception e) {
        	rs.setMsg("内部错误:"+e.getMessage());
        }
		return rs;
		

	}
	@SuppressWarnings("unchecked")
	public List<Map> getUserType(UserInfo userInfo) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		String sql_val = "select value,text from t_sys_param where type='USER_TYPE' and '"+userInfo.getUser_type()+"' like '%'||value||'%'";
		List<Map> list1 = db.query(sql_val);
	
		return list1;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<?, ?> load() throws Exception{
		Map mapSys  = new HashMap();
		Map mapJson  = new HashMap();
		String sql_type = "select distinct type from t_sys_param";
		List<Map> list = db.query(sql_type);
		Gson gson = new Gson();

		
		for (Map types:list) {
			String sql_val = "select value,text from t_sys_param where type='"+ types.get("type") + "'";
			List<Map> list1 = db.query(sql_val);
			Map tmpParam = new LinkedHashMap();
			for (Map params:list1) {
				String key=(String) params.get("value");
				String val=(String) params.get("text");
				tmpParam.put(key,val);
				
			}
			mapSys.put(types.get("type"), tmpParam);
			String jsonStr=gson.toJson(list1);jsonStr=jsonStr.replaceAll("\"", "'");
			mapJson.put(types.get("type"), jsonStr);
		}
		Map mp = new HashMap(); 
		mp.put(ParamsUtil.ParamSys , mapSys);
		mp.put(ParamsUtil.ParamJson, mapJson);
		return mp;
	}
}