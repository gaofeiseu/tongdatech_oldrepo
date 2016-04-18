package com.tongdatech.sys.dao;

import org.apache.log4j.Logger;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.Role;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class RoleDao extends BaseDao{

	private static Logger log =  Logger.getLogger(RoleDao.class);
	
	public PageUI query(Pagination pagination,Role role,UserInfo userinfo) throws Exception{
		
		PageUI rs = new PageUI();
		StringBuffer whereSql = new StringBuffer();
		if(role.getRole_flag()!=null&&!"".equals(role.getRole_flag())){
			whereSql.append( " and role_flag='"+role.getRole_flag()+"'" );
			
		}
		String order_id = db.queryString("select order_id from t_sys_role where role_id="+userinfo.getRole_id());
		if(order_id!=null&&!"".equals(order_id)){
			whereSql.append(" and order_id>="+order_id);
		}
		String sql = "select * from T_SYS_ROLE t where 1=1 "+whereSql+" order by order_id ";
		//log.info(sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(), pagination.getEdnum());
		rs.setRows(db.query(listsql, Role.class));
		int total = db.count(sql);
		rs.setTotal(total);	
		pagination.setTotal(total);
		
		return rs;
	}
	
	public int add(Role role) throws Exception{
		
		int r = 0;
		
		String sql = "insert into t_sys_role(role_id,role_name,order_id,role_img,role_flag) " +
				"values(SEQ_T_SYS_ROLE.nextval,'"+role.getRole_name()+"','"+role.getOrder_id()+"','"+role.getRole_img()+"','1')";
		
		r = db.insert(sql);
		
		return r;
		
	}
	
	public int edit(Role role) throws Exception{
		
		int r = 0;
		
		String sql = "update t_sys_role set role_name='"+role.getRole_name()+"'," +
				"order_id='"+role.getOrder_id()+"'," +
				"role_img='"+role.getRole_img()+"'," +
				"role_flag='"+role.getRole_flag()+"' " +
				"where role_id='"+role.getRole_id()+"'";
		//log.info(sql);
		r = db.update(sql);
		
		return r;
	}
	
	public int remove(String sns,Role role) throws Exception{
		
		int r = 0;
		
		String sql = "update t_sys_role set role_flag='0' where role_id in('"+sns.replace(",", "','")+"')";
		//log.info(sql);
		r = db.delete(sql);
		
		return r;
	}
	
	public int savemenu(String sns,int role_id) throws Exception{
		
		int r = 0 ;
		String sql = "";
		sql = "delete from t_sys_r_menu_r where role_id="+role_id+" ";
		db.delete(sql);
		
		String[] array =  sns.split(",");
		for(int i = 0;i<array.length;i++){
				//this.insertMenuParent(role_id,array[i]);
				if(!this.isMenuId(role_id, array[i])){
					sql = "insert into t_sys_r_menu_r(sn,role_id,menu_id) values('',"+role_id+",'"+array[i]+"')";
					db.insert(sql);
					r++;
				}
		}
		
		return r;
	}
	
	public void insertMenuParent(int role_id ,String menu_id) throws Exception{
		
		String sql = "select count(*) cnt from t_sys_r_menu_r where role_id="+role_id+" " +
				"and menu_id in(select menu_parent from t_sys_menu where menu_id='"+menu_id+"')";
		int r = db.queryInt(sql);
		log.info("insertMenuParentr=" + r + "---" + sql);
		if(r==0){
			sql = "insert into t_sys_r_menu_r select '',"+role_id+",(select menu_parent from t_sys_menu where menu_id='"+menu_id+"') from dual";
			db.insert(sql);
		}
	}
	
	public boolean isMenuId(int role_id,String menu_id) throws Exception{
		boolean flag = false;
		
		String sql = "select count(*) cnt from t_sys_r_menu_r where role_id="+role_id+" and menu_id ='"+menu_id+"'";
		int r = db.queryInt(sql);
		//log.info("isMenuIdr=" + r + "---" + sql);
		if(r>0){
			flag = true;
		}else{
			flag = false;
		}
		
		return flag;
	}
}
