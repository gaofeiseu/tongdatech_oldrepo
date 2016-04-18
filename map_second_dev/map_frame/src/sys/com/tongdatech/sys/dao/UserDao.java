package com.tongdatech.sys.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.Role;
import com.tongdatech.sys.bean.User;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
import com.tongdatech.sys.util.SecurityUtil;

public class UserDao extends BaseDao{
	private static Logger log =  Logger.getLogger(UserDao.class);
	private static String def_pwd="1";

	@SuppressWarnings("unchecked")
	public PageUI query(Pagination pagination, User user,UserInfo userInfo) throws Exception{
		StringBuffer whereSql = new  StringBuffer();
		
	
		if(user.getUser_name()!=null&&!"".equals(user.getUser_name())){
			whereSql.append(" and user_name like '%"+user.getUser_name()+"%'");
		}
		if(user.getNick_name()!=null&&!"".equals(user.getNick_name())){
			whereSql.append(" and nick_name like '%"+user.getNick_name()+"%'");
		}
		
		if(user.getBrch_no()!=0){
			whereSql.append(" and brch_no in (select brch_no from t_sys_brch start with brch_no ="+ user.getBrch_no()+" connect by prior brch_no=brch_up)");
		}
		else
		{
		
			whereSql.append("and brch_no in (select brch_no from t_sys_brch  start with brch_no ="+ userInfo.getBrch_no()+" connect by prior brch_no=brch_up)");
		}
		
//		whereSql.append(" and ( ");
//		
//		if(!"".equals(userInfo.getUser_type())&&userInfo.getUser_type()!=null){
//			whereSql.append(" user_type in ("+makeString(userInfo.getUser_type())+")");
//		}else{
//			whereSql.append(" 1=1 ");
//		}
		
//		for(int i=0;i<userInfo.getUser_type().split(",").length;i++)
//		{
//			if(i==0)
//				whereSql.append(" user_type like '%'||"+userInfo.getUser_type().split(",")[i]+"||'%' ");
//			else
//				whereSql.append(" or user_type like '%'||"+userInfo.getUser_type().split(",")[i]+"||'%' ");
//		}
		
//		whereSql.append(" )");
		String sql = "select * from t_sys_user where 1=1 "+whereSql+" ";
		
		String listsql = db.queryPageStrOrder(sql +"  order by brch_no,nick_name,order_id", pagination.getStnum(),pagination.getEdnum());
		
		listsql="select a.*,b.brch_name||F_PARAMS('BRCH_FLAG',brch_flag) as brch_name from("+listsql+") a,t_sys_brch b where a.brch_no=b.brch_no(+) order by b.brch_name";
		PageUI rs = new PageUI();
		System.out.println("-------------------->"+listsql);
		List<User> list = db.query(listsql, User.class);
		rs.setRows(comfirmList(list,userInfo));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	private List<User> comfirmList(List<User> list_user,UserInfo userinfo){
		List<User> list = new ArrayList<User>();
		try{
			for(User u : list_user){
				try{
					String order_id = "";
					order_id = db.queryString("select order_id from t_sys_role where role_id="+userinfo.getRole_id());
					if(order_id==null||"".equals(order_id)){
						order_id="0";
					}
					if(db.queryInt("select count(1) from t_sys_r_user_br where user_id="+u.getUser_id())==0||db.queryInt("select count(1) from t_sys_r_user_br a,t_sys_role b where a.user_id="+u.getUser_id()+" and a.role_id=b.role_id and b.order_id>="+order_id)>0){
						list.add(u);
					}
				}
				catch(Exception ex){}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}

	public PageUI roles(Pagination pagination, User user,UserInfo userinfo) throws Exception {
		String order_id = db.queryString("select order_id from t_sys_role where role_id="+userinfo.getRole_id());
		if(order_id==null||"".equals(order_id)){
			order_id="0";
		}
        String sql = "select a.*,b.brch_name||F_PARAMS('BRCH_FLAG',brch_flag) as brch_name," +
        		" c.role_name||F_PARAMS('ROLE_FLAG',role_flag) as role_name " +
        		" from T_SYS_R_USER_BR a,t_sys_brch b,t_sys_role c " +
        		" where a.brch_no=b.brch_no(+) and a.role_id=c.role_id(+) and a.user_id="+user.getUser_id()+" and c.order_id>="+order_id+" " +
        		" order by b.brch_mode,b.area_no,b.order_id,c.order_id";
		String listsql = db.queryPageStrOrder(sql , pagination.getStnum(),pagination.getEdnum());

		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, Role.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	private static String makeString(String ass){
		String returnStr = "";
		List<String> l = new ArrayList<String>();
		String[] ssa = ass.split(",");
		for (String sss : ssa) {
			if("".equals(sss)||sss==null){
				continue;
			}else{
				l.add(sss);
			}
		}
		String s="";
		List<String> returnList = new ArrayList<String>();
		f(s,l,returnList);
		List<String> list_a = new ArrayList<String>();
		for (String a : returnList) {
			char[] ac = a.toCharArray();
			if(ac.length==1){
				list_a.add(String.valueOf(ac));
				continue;
			}else{
				String ss = "";
				for(int i=0;i<ac.length;i++){
					if(i==0){
						ss += ac[i];
					}else{
						ss += ","+ac[i];
					}
				}
				list_a.add(ss);
			}
		}
		for(int i=0;i<list_a.size();i++){
			if(i==0){
				returnStr += "'"+list_a.get(i)+"'";
			}else{
				returnStr += ",'"+list_a.get(i)+"'";
			}
		}
		return returnStr;
	}

	public static void main(String...args){
		String s = "1,2,3";
		System.out.println("----->"+makeString(s));
	}
	private static void f(String s,List<String> l,List<String> returnList){
		if(s.isEmpty()){
			for(int i=0;i<l.size();i++){
				returnList.add(s+l.get(i));
//				System.out.println("------->"+s+l.get(i));
				if(i==l.size()-1){
					return;
				}
				f(s+l.get(i),l,returnList);
			}
		}else{
		   char[]c=new char[1];
		   c[0]=s.charAt(s.length()-1);
		   String str=new String(c);
		   for(int i=l.lastIndexOf(str);i<l.size();i++){
			   returnList.add(s+l.get(i+1));
//			   System.out.println("------->"+s+l.get(i+1));
			   if(i==l.size()-2){
				   return;
			   }
			   f(s+l.get(i+1),l,returnList);
		   }
		}
	}
	
	public AjaxMsg resetPwd(User user, UserInfo userInfo) {
		AjaxMsg rs = new AjaxMsg();
		String password=SecurityUtil.md5(def_pwd);
		String sql ="update t_sys_user set password='"+password+"' where user_id="+user.getUser_id();
		try{
			int r=db.update(sql);
			if(r==0){
				rs.setMsg(user.getUser_name()+"重置失败");		
			}else {
				rs.setMsg(user.getUser_name()+"密码成功重置为"+def_pwd);rs.setSuccess(true);
				log.info("重置密码:"+user+"操作人员:"+userInfo);
			
			}
		}catch (Exception e) {
			rs.setMsg("内部错误:"+e.getMessage());
		}	
		return rs;

	}

	public AjaxMsg nameCheck(User user) {
		AjaxMsg rs = new AjaxMsg();
		try{
			String sql = "select count(*) from t_sys_user where user_id <> "+user.getUser_id()+"" +
					" and user_name ='"+user.getUser_name()+"' ";
			int num = db.queryInt(sql);
			if(num == 0){
				rs.setSuccess(true);
			}else rs.setMsg("用户名"+user.getUser_name()+"已被占用");
		}catch (Exception e) {
			rs.setMsg("内部错误"+e.getMessage());
		}
		return rs;
	}

	public AjaxMsg saveUser(User user, UserInfo userInfo) {
		AjaxMsg rs = new AjaxMsg();
		String password=SecurityUtil.md5(def_pwd);
        
		try{
			boolean isAdd=false;
            if( user.getUser_id()== 0){
            	int user_id = db.queryInt("select SEQ_T_SYS_USER.nextval from dual");
            	user.setUser_id(user_id);
            	isAdd=true;
            }
            String lat="'"+user.getLat()+"'";
            String lng="'"+user.getLng()+"'";
            if("''".equals(lat))
            {
            	lat="F_MARKER_LAT('"+user.getBrch_no()+"')";
            }
            if("''".equals(lng))
            {
            	lng="F_MARKER_LNG('"+user.getBrch_no()+"')";
            }
			String sql ="merge into t_sys_user a using (select "+user.getUser_id()+" user_id,'"+user.getUser_name()+"'user_name,'"+user.getNick_name()+"'nick_name,'"+user.getBrch_no()+"'brch_no,"+lat+" lat,"+lng+" lng,'"+password+"'password from dual) b" +
					" on (a.user_id=b.user_id) " +
					" when matched then update set a.user_name=b.user_name,a.nick_name=b.nick_name,a.brch_no=b.brch_no,a.lat=b.lat,a.lng=b.lng " +
					" when not matched then insert (user_id, user_name, nick_name, password, brch_no,lat,lng, user_flag)" +
					" values(b.user_id, b.user_name, b.nick_name, b.password, b.brch_no,b.lat,b.lng,'1')";
			int r=db.update(sql);
			if(r==0){
				rs.setMsg(user.getUser_name()+"保存失败");		
			}else {
				rs.setMsg(user.getUser_name()+"保存成功");rs.setSuccess(true);
				Map<String, Object> mp =new HashMap<String, Object>();
				mp.put("user_id", user.getUser_id());mp.put("isAdd", isAdd);
				rs.setBackParam(mp);
				log.info("用户保存:"+user+"操作人员:"+userInfo);
			}
		}catch (Exception e) {
			rs.setMsg("内部错误:"+e.getMessage());
		}	
		return rs;
	}
	private String changeSort(String s){
		String returnStr = "";
		try {
			System.out.println("s:"+s);
			s.replaceAll(" ", "");
			String[] s_a = s.split(",");
//			for (String bb : s_a) {
//				System.out.println("bb---->"+bb);
//			}
			Integer[] i_a = new Integer[s_a.length];
			for(int i=0;i<s_a.length;i++){
				i_a[i] = Integer.parseInt(s_a[i].trim());
			}
			if(i_a.length>1){
				Arrays.sort(i_a, new Comparator<Integer>() {
					public int compare(Integer o1, Integer o2) {
						return o1-o2;
					} 
				});
			}
			for(int i=0;i<i_a.length;i++){
				if(i==0){
					returnStr += i_a[i];
				}else{
					returnStr += ","+i_a[i];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-----------"+returnStr);
		return returnStr;
	}

	public AjaxMsg saveRoles(Role role, UserInfo userInfo) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		String sql_del="delete T_SYS_R_USER_BR where sn="+role.getSn()+" " +
				"or (user_id="+role.getUser_id()+" and role_id="+role.getRole_id()+ ")";
				//+" and brch_no="role.getBrch_no()+ ")";
		String sql_in = " insert into T_SYS_R_USER_BR (sn, user_id, role_id) " +
				"VALUES (SEQ_T_SYS_R_USER_BR.nextval,"+role.getUser_id()+","+role.getRole_id()+")";
		try{
			db.connect();
			db.startTransaction();
			int ret_del=db.delete(sql_del);
			int ret_in =db.insert(sql_in);
			if(ret_del>=0&&ret_in==1){
				db.commit();
				rs.setSuccess(true);
				rs.setMsg("角色权限保存成功");
				rs.setBackParam(role.getUser_id());
				log.info("权限配置保存:"+role+"操作人员:"+userInfo);
			}else{
				db.rollback();
				rs.setMsg("角色权限保存失败");
			}
		}catch (Exception e) {
			rs.setMsg("内部错误"+e.getMessage());
			db.rollback();
		}finally{
			db.endTransaction();
			db.disconnect();
		}
		
		return rs;
	}

	public AjaxMsg delRoles(Role role, UserInfo userInfo) {
		AjaxMsg rs = new AjaxMsg();
		String sql="delete T_SYS_R_USER_BR where sn="+role.getSn();
		try{
			db.delete(sql);
			rs.setSuccess(true);
			rs.setMsg("权限配置删除成功");
			log.info("权限配置删除:"+role);
		}catch (Exception e) {
			rs.setMsg("内部错误"+e.getMessage());
		}
		return rs;
	}

	public AjaxMsg delUser(User user, UserInfo userInfo) throws Exception {
		AjaxMsg rs = new AjaxMsg();
		String sql_del1="delete T_SYS_R_USER_BR where user_id="+user.getUser_id()+" " ;
				
		String sql_del2 = " delete T_SYS_USER where user_id="+user.getUser_id()+" " ;
		
		String sql_in ="INSERT INTO t_sys_user_del(user_id, user_name, nick_name, PASSWORD, brch_no,order_id, user_flag, oper_time, oper_user)" +
				" select user_id, user_name, nick_name, PASSWORD, brch_no,order_id, user_flag, sysdate,"+userInfo.getUser_id()+" " +
				" from t_sys_user  where user_id="+user.getUser_id()+" " ;
		try{
			db.connect();
			db.startTransaction();
			int ret_in =db.insert(sql_in);
			int ret_del1=db.delete(sql_del1);
			int ret_del2 =db.delete(sql_del2);
			
			if(ret_del1>=0&&ret_del2==1&&ret_in==1){
				db.commit();
				rs.setSuccess(true);
				rs.setMsg("用户删除成功");
				log.info("用户删除:"+user+"操作人员:"+userInfo);
			}else{
				db.rollback();
				rs.setMsg("用户删除失败");
			}
		}catch (Exception e) {
			rs.setMsg("内部错误"+e.getMessage());
			db.rollback();
		}finally{
			db.endTransaction();
			db.disconnect();
		}
		return rs;
	}

	public AjaxMsg savePWD(String old_pwd, String password, String repeat_pwd,
			UserInfo userInfo) {
		AjaxMsg rs = new AjaxMsg();
		
		try{
			if(!password.equals(repeat_pwd)){
				rs.setMsg("2次新密码不一致");
				return rs;
			}
			String pwd=db.queryString("select password from t_sys_user where user_id="+userInfo.getUser_id());
			old_pwd=SecurityUtil.md5(old_pwd);
			if(!pwd.equals(old_pwd)){
				rs.setMsg("旧密码不正确");
				return rs;
			}
			
			String sql="update t_sys_user set password = '"+SecurityUtil.md5(password)+"' where user_id="+userInfo.getUser_id();
			int ret =db.update(sql);
			if(ret==0){
				rs.setMsg("密码修改失败");
			}else{			
				rs.setSuccess(true);
				rs.setMsg("密码修改成功");
			}
		}catch (Exception e) {
			rs.setMsg("系统内部错"+e.getMessage());
		}
		return rs;
	}


}
