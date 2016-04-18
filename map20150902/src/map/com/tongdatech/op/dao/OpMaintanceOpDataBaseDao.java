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
		System.out.println("����ɾ��������----->sn:"+sn+" marker_class:"
				+marker_class+" dept_class:"+dept_class+" class_name:"
				+class_name+" class_tablename:"+class_tablename);
		try {
			db.connect();
			db.startTransaction();
			String sql1 = "delete from t_map_class_info where sn="+sn+" ";//ɾ��t_map_class_info���е��������
			if(db.delete(sql1)==1){
				String sql2 = "drop table "+class_tablename+" ";//ɾ�������������ݱ�
				db.runSql(sql2);
				String sql3 = "delete from t_map_class_status where class_tablename='"+class_tablename+"' ";//ɾ��t_map_class_status���е��������
				if(db.delete(sql3)>0){
					String sql4 = "select class_seq_name from t_map_markerclass_seq where class_sn="+sn+" ";//ѡ��SEQ����
					String seqStr = "";
					seqStr = db.queryString(sql4);
					String sql5 = "delete from t_map_markerclass_seq where class_sn="+sn+" ";//ɾ����t_map_markerclass�еĶ�ӦSEQ��Ϣ
					if(db.delete(sql5)==1){
						String sql6 = "DROP SEQUENCE "+seqStr+" ";//ɾ����Ӧ��SEQUENCE
						db.runSql(sql6);
						db.commit();
						returnBean.setIf_success(true);
						returnBean.setMsg("ɾ�������ɹ���");
					}else{
						db.rollback();
						returnBean.setIf_success(false);
						returnBean.setMsg("ɾ������ʧ�ܣ����ݿⷢ���쳣��3");
					}
				}else{
					db.rollback();
					returnBean.setIf_success(false);
					returnBean.setMsg("ɾ������ʧ�ܣ����ݿⷢ���쳣��2");
				}
			}else{
				db.rollback();
				returnBean.setIf_success(false);
				returnBean.setMsg("ɾ������ʧ�ܣ����ݿⷢ���쳣��1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("ɾ������ʧ�ܣ����ݿⷢ���쳣��"+e.getMessage());
			try {
				db.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg("���ݿⷢ�����ش�������ϵϵͳ����Ա��2");
			}
		}
		finally{
			try {
				db.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg("���ݿⷢ�����ش�������ϵϵͳ����Ա��1");
			}
			db.disconnect();
		}
		return returnBean;
	}

}
