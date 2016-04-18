package com.tongdatech.sys.dao;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.tongdatech.sys.action.RoleAction;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.Brch;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.util.PinYinUtil;


public class BrchDao extends BaseDao{
	@SuppressWarnings("unused")
	private static Logger log =  Logger.getLogger(RoleAction.class);

	
	public Brch queryOne(String brch_no) throws Exception{
		
		String sql = "select a.* ,b.brch_name brch_up_name,c.area_name area_name from t_sys_brch a,t_sys_brch b,t_sys_area c " +
				"where a.brch_no="+brch_no+" and b.brch_no=a.brch_up and a.area_no=c.area_no and a.brch_flag='1'";
		//log.info(sql);
		return (Brch)db.queryOneLine(sql, Brch.class);
	}
	
	public AjaxMsg brchSave(Brch brch) throws Exception{
		AjaxMsg am = new AjaxMsg();
		String sql = "";
		/*log.info("brch_no="+brch.getBrch_no()+",brch_code=" + brch.getBrch_code()+",brch_up="+brch.getBrch_up()+",brch_up_name="+brch.getBrch_up_name()
				+",area_no="+brch.getArea_no()+",area_name="+brch.getArea_name()+",brch_mode=" + brch.getBrch_mode()+",brch_flag=" + brch.getBrch_flag()+",order_id="+brch.getOrder_id());*/
		
		try{
			db.connect();
			db.startTransaction();
			
			if("".equals(brch.getBrch_no())){
				 sql = "insert into t_sys_brch(brch_no,brch_up,brch_name,brch_code,brch_mode,area_no,brch_flag,order_id,spell_short,spell_full) values(" +
				 		"seq_t_sys_brch.nextval,"+brch.getBrch_up()+"," +
				 		"'"+brch.getBrch_name()+"'," +
				 		"'"+brch.getBrch_code()+"'," +
				 		"'"+brch.getBrch_mode()+"'," +
				 		"'"+brch.getArea_no()+"'," +
				 		"'"+brch.getBrch_flag()+"'," +
				 		""+brch.getOrder_id()+"," +
				 		"'"+PinYinUtil.getHeadPinYin(brch.getBrch_name())+"'," +
				 		"'"+PinYinUtil.getFullPinYin(brch.getBrch_name())+"')";
				 //log.info("insert----" + sql);
				 int m = db.insert(sql);
				 if(m>0){
					 db.commit();
					 am.setMsg("新增部门成功!");
					 am.setSuccess(true);
				 }else{
					 db.rollback();
					 am.setMsg("新增部门失败!");
					 am.setSuccess(false);
				 }
			}else{
				 sql = "update t_sys_brch set brch_up="+brch.getBrch_up()+"," +
				 		"brch_name='"+brch.getBrch_name()+"'," +
				 		"brch_code='"+brch.getBrch_code()+"'," +
				 		"area_no='"+brch.getArea_no()+"'," +
				 		"brch_mode='"+brch.getBrch_mode()+"'," +
				 		"brch_flag='"+brch.getBrch_flag()+"'," +
				 		"order_id="+brch.getOrder_id()+" " +
				 		"where brch_no="+brch.getBrch_no()+"";
				 //log.info("update------" + sql);
				 int n = db.update(sql);
				 if(n>0){
					 db.commit();
					 am.setMsg("更新部门成功!");
					 am.setSuccess(true);
					// am.setBackParam(brch.getBrch_no());
				 }else{
					 db.rollback();
					 am.setMsg("更新部门失败!");
					 am.setSuccess(false);
				 }
			}
			
		}catch(Exception e){
			db.rollback();
			e.printStackTrace();
		}finally{
			db.endTransaction();
			db.disconnect();
		}
		
		
		return am;
	}
	
	public AjaxMsg idCheck(Brch brch) throws Exception{
		AjaxMsg am = new AjaxMsg();
		
		String sql = "select count(*) from t_sys_brch where brch_code='"+brch.getBrch_code()+"' and brch_flag='1'";
		//log.info(sql);
		int cnt = db.queryInt(sql);
		if(cnt == 0){
			am.setSuccess(true);
		}else{
			am.setMsg("该部门号"+brch.getBrch_code()+"已被占用!");
		}
		
		return am;
	}
	
	public AjaxMsg isChild(Brch brch) throws Exception{
		AjaxMsg am = new AjaxMsg();
		
		String sql = "select count(*) from t_sys_brch connect by prior brch_no=brch_up start with brch_up="+brch.getBrch_no()+"";
		int cnt = db.queryInt(sql);
		if(cnt > 0 ){
			am.setSuccess(true);
		}else{
			am.setSuccess(false);
		}
		
		return am;
	}

	public AjaxMsg brchEdit(Brch brch) throws Exception {
		AjaxMsg am = new AjaxMsg();
		try{
			String sql = "";
			if(brch.getBrch_no()!=null&&!"".equals(brch.getBrch_no())){
				db.connect();
				db.startTransaction();
				try{
					try{
						sql = "update t_sys_brch set brch_up="+(new BigDecimal(brch.getBrch_up())).intValue()+",";
					}
					catch(Exception ex){
						sql = "update t_sys_brch set ";
					}
					sql = sql +
					 		"brch_name='"+brch.getBrch_name()+"'," +
					 		"brch_code='"+brch.getBrch_code().split("###")[0]+"'," +
					 		"area_no='"+brch.getArea_no()+"'," +
					 		"brch_mode='"+brch.getBrch_mode()+"'," +
					 		"brch_flag='"+brch.getBrch_flag()+"'," +
					 		"order_id="+brch.getOrder_id()+",spell_short='"+PinYinUtil.getHeadPinYin(brch.getBrch_name())+"',spell_full='"+PinYinUtil.getFullPinYin(brch.getBrch_name())+"' " +
					 		"where brch_no="+brch.getBrch_no()+"";
					 int n = db.update(sql);
					 if(n==1){
						 db.commit();
						 am.setMsg("更新部门成功!");
						 am.setSuccess(true);
					 }else if(n>1){
						 db.rollback();
						 am.setMsg("更新部门失败!部门号【"+brch.getBrch_no()+"】不唯一，请联系系统管理员！");
						 am.setSuccess(false);
					 }else{
						 db.rollback();
						 am.setMsg("更新部门失败!");
						 am.setSuccess(false);
					 }
				}
				catch(Exception ex){
					db.rollback();
					throw ex;
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			}else{
				throw new Exception ("需要更新的部门号为空，无法进行更新！");
			}
		}
		catch(Exception ex){
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}
	
}
