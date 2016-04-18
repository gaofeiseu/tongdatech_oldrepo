package com.tongdatech.sys.dao;

 
import com.tongdatech.sys.base.BaseDao;
 
import com.tongdatech.sys.bean.MenuIpBean;
 
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
 
public class MenuIpDao extends BaseDao {
	
  
 
	public  PageUI  getMenuIp(Pagination pagination,MenuIpBean bean) throws Exception {
		StringBuffer whereSql = new  StringBuffer();
		if(bean.getMenu_id()!=null&&!"".equals(bean.getMenu_id()))
		{
			whereSql.append( " and menu_id="+bean.getMenu_id());
		}
		if(bean.getIp()!=null&&!"".equals(bean.getIp()))
		{
			whereSql.append( " and ip='"+bean.getIp()+"'");
		}
		if(bean.getDetail()!=null&&!"".equals(bean.getDetail()))
		{
			whereSql.append( " and detail='"+bean.getDetail()+"'");
		}
        String sql="select sn,menu_id,ip,detail,f_menu_name(menu_id) menu_name from T_SYS_MENU_IP where 1=1 "+whereSql; 
		String listsql = db.queryPageStrOrder(sql +"  order by sn desc", pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		System.out.println("-------------------->"+listsql);
		rs.setRows(db.query(listsql, MenuIpBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs; 
	}
	
	public  int  getMenuIpNum(MenuIpBean bean) throws Exception {
 
	 
        String sql="select F_menu_ip('"+bean.getIp()+"','"+bean.getMenu_id()+"') from dual "; 
  
		return  db.queryInt(sql);
	}
	
	public AjaxMsg menuipSave(MenuIpBean bean) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		try{
		    db.connect();
		    db.startTransaction();
 
			if(bean.getSn()==null||"".equals(bean.getSn())){//新增
				String sn_sql  = "select SEQ_T_SYS_menu_ip.nextval from  dual "; 
				int sn=db.queryInt(sn_sql);
				String sql_in = "insert into t_sys_menu_ip(sn,menu_id,menu_name,ip,detail) values("  +   
					"" +sn+",replace('"+
	        		 bean.getMenu_id()+
	        		"',' ',''),F_menu_name('" +bean.getMenu_id()+
	        		"'),'" +bean.getIp()+
	        		"','" +bean.getDetail()+
	        		"')";  
				int ret1 = db.insert(sql_in); 
				if(ret1 == 1 ){ 
					rs.setMsg("绑定IP成功");
					rs.setSuccess(true); 
				}  
			}else{//更新 
				String sql_up = "UPDATE t_sys_menu_ip" +
						"  SET menu_name = f_menu_name('"+bean.getMenu_id()+"')," +
						"       menu_id = replace('"+bean.getMenu_id()+"',' ','')," +
						"       detail = '"+bean.getDetail()+"'," +
						"       ip = '"+bean.getIp()+"'" +
						" where sn = "+bean.getSn();
				int ret=db.update(sql_up);
				if(ret == 1){ 
					rs.setMsg("更新IP绑定关系成功");
					rs.setSuccess(true); 
				}  
			}  
		}catch (Exception e) {
			db.rollback();
			rs.setMsg("内部错误"+e.getMessage());
		}finally{
			db.endTransaction();
			db.disconnect();
		}	
		return rs;
	}
	
	
	public AjaxMsg menuipDel(MenuIpBean bean) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		try{
		    db.connect();
		    db.startTransaction();
	 
		    String sql_del = "delete from t_sys_menu_ip" +
						 
						" where sn in ("+bean.getSn()+")";
		    int ret=db.delete(sql_del);
			if(ret >= 1){ 
				rs.setMsg("删除IP绑定关系成功");
				rs.setSuccess(true); 
			}  
		  
		}catch (Exception e) {
			db.rollback();
			rs.setMsg("内部错误"+e.getMessage());
		}finally{
			db.endTransaction();
			db.disconnect();
		}	
		return rs;
	}
}
