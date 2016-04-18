package com.tongdatech.sys.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.Area;
import com.tongdatech.sys.bean.Menu;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;

public class MenuDao extends BaseDao {

	@SuppressWarnings("unused")
	private static Logger log =  Logger.getLogger(MenuDao.class);
	@SuppressWarnings("unchecked")
	public List<Menu> getMainMenu(UserInfo userInfo) throws Exception {

        String sql="select a.* from T_SYS_MENU a,T_SYS_R_MENU_R b,T_SYS_R_MENU_A c " +
        		" where a.menu_id=b.menu_id and a.menu_id=c.menu_id " +
        		" and a.menu_parent=0 " +
        		" and b.role_id="+userInfo.getRole_id()+" " +
        		" and c.area_no='"+userInfo.getArea_no()+"'" +
        		" and a.menu_flag ='1' order by a.order_id";
		return (List<Menu>)db.query(sql, Menu.class);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getChildMenu(UserInfo userInfo, int menu_id) throws Exception {
		String sql="select a.* from T_SYS_MENU a,T_SYS_R_MENU_R b,T_SYS_R_MENU_A c " +
		" where a.menu_id=b.menu_id and a.menu_id=c.menu_id " +
		" and a.menu_parent="+menu_id+" " +
		" and b.role_id="+userInfo.getRole_id()+" " +
		" and c.area_no='"+userInfo.getArea_no()+"'"+
		" and a.menu_flag ='1' order by a.order_id";
        return (List<Menu>)db.query(sql, Menu.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> getChildMenuNoAuth(UserInfo userInfo, int menu_id) throws Exception {
		String sql="select a.* from T_SYS_MENU a,T_SYS_R_MENU_A c " +
		" where  a.menu_id=c.menu_id " +
		" and a.menu_parent="+menu_id+" " +
		" and c.area_no='"+userInfo.getArea_no()+"'"+
		" and a.menu_flag ='1' order by a.order_id";
        return (List<Menu>)db.query(sql, Menu.class);
	}
	public int getMenuLog(UserInfo userInfo,Menu menu) throws Exception{
		String sql = "insert into T_SYS_LOG_MENU(log_date,user_id,nick_name,menu_id,menu_name,brch_no,"
				+ "brch_name,area_no,area_name,role_id,role_name) values("
				+"sysdate"
				+ ",'"
				+ userInfo.getUser_id()
				+ "','"
				+ userInfo.getNick_name()
				+ "','"
				+ menu.getMenu_id()
				+ "','"
				+ menu.getMenu_name()
				+ "','"
				+ userInfo.getBrch_no()
				+ "','"
				+ userInfo.getBrch_name()
				+ "','"
				+ userInfo.getArea_no()
				+ "','"
				+ userInfo.getArea_name()
				+ "','"
				+ userInfo.getRole_id()
				+ "','"
				+ userInfo.getRole_name()
				+ "')";
	    return db.insert(sql);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getChildMenuChecked(UserInfo userInfo, int menu_id, int role_id) throws Exception {
		String sql="select a.*,decode(d.menu_id,null,0,1) check_flag from T_SYS_MENU a,T_SYS_R_MENU_A c ,T_SYS_R_MENU_R d" +
		" where 1 = 1 and a.menu_id=c.menu_id " +
		" and a.menu_id=d.menu_id(+) and d.role_id(+)= "+role_id+
		" and a.menu_parent="+menu_id+" " +
		" and c.area_no='"+userInfo.getArea_no()+"'"+
		" and a.menu_flag ='1' order by a.order_id";
        return (List<Menu>)db.query(sql, Menu.class);
	}

	public AjaxMsg menuSave(Menu menu) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		try{
		    db.connect();
		    db.startTransaction();
		    int menu_id =menu.getMenu_id();
			if(menu_id==0){//新增
				String sql_id  = "select SEQ_T_SYS_MENU.nextval from  dual ";
				menu_id = db.queryInt(sql_id);
				
				String sql_in1 = "INSERT INTO t_sys_menu" +
				"            (menu_id, menu_name, menu_url, menu_flag, menu_parent,menu_style, order_id,ip_flag)" +
				"     VALUES ("+menu_id+", '"+menu.getMenu_name()+"', '"+menu.getMenu_url()+"'," +
						" '1', 0, '"+menu.getMenu_style()+"', 0,'"+menu.getIp_flag()+"')";
				
				String sql_in2 = "INSERT INTO t_sys_r_menu_a(area_no, menu_id)" +
						"select area_no,"+menu_id+" from t_sys_area";
				
				int ret1 = db.insert(sql_in1);
				int ret2 = db.insert(sql_in2);
				if(ret1 == 1 && ret2 > 1){
					db.commit();
					rs.setMsg("新增菜单成功");
					rs.setSuccess(true);
					
				}else{
					db.rollback();
					rs.setMsg("数据数目不一致"+ret1+"≠1 "+ret1+"≤1");
				}
				
			}else{//更新
				
				String sql_up = "UPDATE t_sys_menu" +
						"  SET menu_name = '"+menu.getMenu_name()+"'," +
						"       menu_url = '"+menu.getMenu_url()+"'," +
						"       ip_flag = '"+menu.getIp_flag()+"'," +
						"       menu_style = '"+menu.getMenu_style()+"'" +
						
						" where menu_id = "+menu.getMenu_id();
				int ret=db.update(sql_up);
				if(ret == 1){
					rs.setMsg("更新菜单成功");
					rs.setSuccess(true);
					rs.setBackParam(menu_id);
				}else{
					db.rollback();
					rs.setMsg("数据数目不一致"+ret+"≠1");
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

	public AjaxMsg nameCheck(Menu menu) {
		AjaxMsg rs = new AjaxMsg();
		try{
			String sql = "select count(*) from t_sys_menu where menu_id <> "+menu.getMenu_id()+"" +
					" and menu_name ='"+menu.getMenu_name()+"' ";
			int num = db.queryInt(sql);
			if(num == 0){
				rs.setSuccess(true);
			}else rs.setMsg("菜单名"+menu.getMenu_name()+"已被占用");
		}catch (Exception e) {
			rs.setMsg("内部错误"+e.getMessage());
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	public List<Area> getChildAreaChecked(String area_parent, int menu_id) throws Exception {
		 String sql = "select a.*,decode(b.menu_id,null,0,1) check_flag from t_sys_area a,t_sys_r_menu_a b where a.area_no = b.area_no(+) " +
 		" and b.menu_id(+)="+menu_id +" and a.area_parent ='"+area_parent+"'";
	return (List<Area>) db.query(sql,Area.class);
	}

	public Area getRootAreaChecked(String area_no, int menu_id) throws Exception {
        String sql = "select a.*,decode(b.menu_id,null,0,1) check_flag from t_sys_area a,t_sys_r_menu_a b where a.area_no = b.area_no(+) " +
        		" and b.menu_id(+)="+menu_id +" and a.area_no ='"+area_no+"'";
		return (Area) db.queryOneLine(sql,Area.class);
	}

	public AjaxMsg areaSave(String[] areas, int menu_id) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		try{
		    db.connect();
		    db.startTransaction();
		    int len = areas.length;
		    int ret_up = 0;
		    String sql_del = "delete t_sys_r_menu_a where menu_id="+menu_id;
		    db.delete(sql_del);
            for(String area:areas){
            	String sql = "insert into t_sys_r_menu_a (area_no,menu_id) values ('"+area+"',"+menu_id+")";
            	ret_up+=db.insert(sql);
            }
            if(ret_up==len){
				rs.setMsg("更新菜单地区权限成功");
				rs.setSuccess(true);
			}else{
				db.rollback();
				rs.setMsg("数据数目不一致"+ret_up+"≠"+len+"");
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

	public AjaxMsg menuOrder(int menu_id, int menu_target, int i) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		StringBuffer whereSql = new StringBuffer();
		whereSql.append(" and menu_parent = (select menu_parent from t_sys_menu where menu_id = "+menu_id+" )");
		String sql1 ="UPDATE t_sys_menu a" +
				"   SET a.order_id = (SELECT rr" +
				"                       FROM (SELECT ROWNUM*2 rr, menu_id" +
				"                               FROM (SELECT   menu_id" +
				"                                         FROM t_sys_menu" +
				"                                        WHERE 1=1 " + whereSql+
				"                                     ORDER BY order_id)) b" +
				"                      WHERE b.menu_id = a.menu_id)" +
				" WHERE 1=1 "+whereSql;
		
		String sql2 ="update  t_sys_menu SET (order_id,menu_parent)=(select order_id+"+i+",menu_parent from t_sys_menu where menu_id="+menu_target+" )" +
				" where menu_id="+menu_id+" ";
		
		try{
		    db.connect();
		    db.startTransaction();
            int ret1 = db.update(sql1);
            int ret2 = db.update(sql2);
            if(ret1>=1&&ret2==1){
				rs.setMsg("成功");
				rs.setSuccess(true);
			}else{
				db.rollback();
				rs.setMsg("数据数目不一致");
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

	public AjaxMsg menuChange(Menu menu) {
		AjaxMsg rs = new AjaxMsg();
		try{
            String sql="update t_sys_menu set menu_parent="+menu.getMenu_parent()+"," +
            		"order_id=(select nvl(max(order_id),0)+2 from t_sys_menu where menu_parent="+menu.getMenu_parent()+") where menu_id="+menu.getMenu_id()+"";
			int ret = db.update(sql);
            if(ret==1){
				rs.setMsg("成功!");
				rs.setSuccess(true);
			}else{
				rs.setMsg("数据数目不一致");
			}	
			
		}catch (Exception e) {
			rs.setMsg("内部错误"+e.getMessage());
		}
		return rs;
	}
}