package com.tongdatech.op.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tongdatech.op.bean.OpDataGridBean;
import com.tongdatech.op.bean.OpMaintanceOpDataBaseBean;
import com.tongdatech.op.bean.OpReturnBean;
import com.tongdatech.sys.base.BaseDao;

public class OpMaintanceOpDataBaseDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public OpDataGridBean query(OpMaintanceOpDataBaseBean bean) throws Exception {
		OpDataGridBean returnBean = new OpDataGridBean();
		String sql = "select sn," +
				"marker_class as marker_class_num," +
				"post_class as dept_class_num," +
				"F_PARAMS('MARKER_TYPE',marker_class) as marker_class," +
				"F_PARAMS('USER_TYPE',post_class) as dept_class," +
				"class_name,class_table_name as class_tablename" +
				" from t_map_class_info where post_class='"
			+bean.getDept_class()+"' and marker_class='"
			+bean.getMarker_class()+"' and class_status='2' ";
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		list_map = db.query(sql);
		int total = list_map.size();
		returnBean.setTotal(total);
		returnBean.setRows(list_map);
		return returnBean;
	}

	public OpReturnBean dodelete(OpMaintanceOpDataBaseBean bean) {
		OpReturnBean returnBean = new OpReturnBean();
		String sn = bean.getS_sn();
		String marker_class = bean.getS_marker_class();
		String dept_class = bean.getS_dept_class();
		String class_name = bean.getS_class_name();
		String class_tablename = bean.getS_class_tablename();
		System.out.println("即将删除的数据----->sn:"+sn+" marker_class:"
				+marker_class+" dept_class:"+dept_class+" class_name:"
				+class_name+" class_tablename:"+class_tablename);
		try {
			db.connect();
			db.startTransaction();
			String sql1 = "delete from t_map_class_info where sn="+sn+" ";//删除t_map_class_info表中的相关数据
			if(db.delete(sql1)==1){
				String sql2 = "drop table "+class_tablename+" ";//删除这个表本身的数据表
				db.runSql(sql2);
				String sql3 = "delete from t_map_class_status where class_tablename='"+class_tablename+"' ";//删除t_map_class_status表中的相关数据
				if(db.delete(sql3)>0){
					String sql4 = "select class_seq_name from t_map_markerclass_seq where class_sn="+sn+" ";//选出SEQ名称
					String seqStr = "";
					seqStr = db.queryString(sql4);
					String sql5 = "delete from t_map_markerclass_seq where class_sn="+sn+" ";//删除表t_map_markerclass中的对应SEQ信息
					if(db.delete(sql5)==1){
						String sql6 = "DROP SEQUENCE "+seqStr+" ";//删除对应的SEQUENCE
						db.runSql(sql6);
						db.commit();
						returnBean.setIf_success(true);
						returnBean.setMsg("删除操作成功！");
					}else{
						db.rollback();
						returnBean.setIf_success(false);
						returnBean.setMsg("删除操作失败，数据库发生异常！3");
					}
				}else{
					db.rollback();
					returnBean.setIf_success(false);
					returnBean.setMsg("删除操作失败，数据库发生异常！2");
				}
			}else{
				db.rollback();
				returnBean.setIf_success(false);
				returnBean.setMsg("删除操作失败，数据库发生异常！1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("删除操作失败，数据库发生异常！"+e.getMessage());
			try {
				db.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg("数据库发生严重错误，请联系系统管理员！2");
			}
		}
		finally{
			try {
				db.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg("数据库发生严重错误，请联系系统管理员！1");
			}
			db.disconnect();
		}
		return returnBean;
	}

}
