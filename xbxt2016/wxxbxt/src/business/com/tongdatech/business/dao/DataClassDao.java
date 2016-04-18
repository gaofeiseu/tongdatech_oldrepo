/**
 * File name:VisitDao.java
 * Create author:
 * Create date:
 */
package com.tongdatech.business.dao;

 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.tongdatech.business.bean.DataClassBean;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

 
/**
 * @author 
 * 
 */
public class DataClassDao extends BaseDao {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(DataClassDao.class);
 
	public PageUI loaddata1(Pagination pagination,
			DataClassBean bean) throws Exception {
		String addsql="";
		if(!"".equals(bean.getChildclass_status())&&bean.getChildclass_status()!=null)
		{
			addsql+=" and class_status='"+bean.getChildclass_status()+"'";
		}
		if(!"".equals(bean.getChildclass_name())&&bean.getChildclass_name()!=null)
		{
			addsql+=" and class_name like '%"+bean.getChildclass_name()+"%'";
		}
		//System.out.println("t_user_type:"+bean.getT_user_type());
		//addsql += " and post_class in ("+stringFunction(bean.getT_user_type())+") ";
		String sql = "select sn as childclass_sn,class_table_name as table_name,class_name as childclass_name,class_status as childclass_status,post_class as childclass_brchno,F_BrchName(post_class) as childclass_brchstr,F_PARAMS('FIRST_CLASS_STATUS',class_status) as childclass_status_str from T_Data_CLASS_INFO where 1=1 "+addsql+" order by sn ";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, DataClassBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	private String stringFunction(String str){
		String returnStr = "";
		String[] a = str.split(",");
		int c = 0;
		for (String b : a) {
			if("".equals(b)||b==null){
				continue;
			}else{
				if(c==0){
					returnStr += "'"+b+"'";
					c++;
				}else{
					returnStr += ","+"'"+b+"'";
					c++;
				}
			}
		}
		return returnStr;
	}

	public int addchildclass(DataClassBean bean,UserInfo userInfo) throws Exception{
		int r = 0;
		
		System.out.println("��������:"+bean.getChildclass_add_brch()+" ����ע����:"+bean.getChildclass_add_for_parentclassid());
		System.out.println("������������:"+bean.getChildclass_add_classname()+" ��������״̬:"+bean.getChildclass_add_status());
		try {
				db.connect();
				db.startTransaction();
				String sql0 = "select SEQ_T_DATA_CLASS_INFO.nextval from dual ";
				String second_sn = "";
				second_sn = db.queryString(sql0);
				String add_tablename = bean.getTable_name().toUpperCase()+"";
				String sql = "insert into t_data_class_info" +
						" (sn,post_class,marker_class,class_name,class_table_name,CLASS_TYPE1,CLASS_TYPE2,class_status)" +
						" values " +
						"("+second_sn+",'"+userInfo.getBrch_no()+"','"+""+"','"
						+bean.getChildclass_add_classname()+"','"+add_tablename+"','1','1','"+bean.getChildclass_add_status()+"') ";
				int r1 = 0;
				r1 = db.insert(sql);
				if(r1==1){//�����������ͱ�t_data_markerclass_second�ɹ�
					//׼�������ض����������ͱ�
					String sql1 = "";
					sql1 += "CREATE TABLE "+add_tablename+" ( ";
					sql1 += "\"SN\" NUMBER NOT NULL ENABLE,";
					sql1 += "\"STATUS_FLAG\" VARCHAR2(5 BYTE),\"LAT\" VARCHAR2(4000 BYTE),\"LNG\" VARCHAR2(4000 BYTE),\"MC_MARKERS\" VARCHAR2(500 BYTE),\"POST_FLAG\" VARCHAR2(10 BYTE)";
					sql1 += ")";
					db.runSql(sql1);
					
					//��ʼ����ע��
					String sql2 = "";
					sql2 = "COMMENT ON COLUMN "+add_tablename+".SN IS '���' ";
					db.runSql(sql2);
					sql2 = "COMMENT ON COLUMN "+add_tablename+".STATUS_FLAG IS '��־λ' ";//ϵͳ��Ҫ��־λ 1��ʾ���� 2��ʾɾ��
					db.runSql(sql2);
					sql2 = "COMMENT ON COLUMN "+add_tablename+".LAT IS 'γ��' ";
					db.runSql(sql2);
					sql2 = "COMMENT ON COLUMN "+add_tablename+".LNG IS '����' ";
					db.runSql(sql2);
					sql2 = "COMMENT ON COLUMN "+add_tablename+".MC_MARKERS IS '��������' ";//�ñ�ע��Ӧ����������������ǵ㣬�Ǿ��ǵ�ͼ���ӦͼƬ��URL��������ߣ��Ǿ�����ɫ�������͸�������ߴֶȵ���ϣ���#8b00ff||0.3||30����һ����ʾ�ߵ���ɫ��0.3��ʾ͸���ȣ�30��ʾ�ߵĴֶȣ�������棬����ʱ������ɫ����#FF0000������
					db.runSql(sql2);
					sql2 = "COMMENT ON COLUMN "+add_tablename+".POST_FLAG IS '��������' ";//��ע����״̬λ��1-������2-�˼�
					db.runSql(sql2);
					
					String[] col_arr = {"SN","STATUS_FLAG","LAT","LNG","MC_MARKERS","POST_FLAG"};
					String[] col_comments_arr = {"���","��־λ","γ��","����","��������","��������"};
					String[] col_type_arr = {"2","1","1","1","1","1"};
					String[] col_type_ifnull = {"2","2","1","1","1","1"};
					String[] col_type_size = {"NULL","5","4000","4000","500","10"};
					int c_success = 0;
					for(int i=0;i<col_arr.length;i++){
						String sql5 = "";
						sql5 = "insert into t_data_class_status " +
								"(sn,CLASS_SN,class_tablename,class_columnname," +
								"class_comments,class_columntype,class_columnstatus," +
								"class_bakstatus1,class_bakstatus2," +
								"class_nullable,class_columnsize,COLUMN_TYPE) values " +
								"(SEQ_T_DATA_CLASS_STATUS.nextval,"
								+second_sn+",'"+add_tablename+"','"
								+col_arr[i]+"','"+col_comments_arr[i]+"','"
								+col_type_arr[i]+"','1','1','1','"+col_type_ifnull[i]
								+"','"+col_type_size[i]+"','1')";
						int insertNum = db.insert(sql5);
						if(insertNum==1){
							c_success+=1;
						}
					}
					
					
					//��������
					String sql3 = "";
					sql3 = "CREATE SEQUENCE \"SEQ_"+add_tablename+"\"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ";
					db.runSql(sql3);
					
					//���������������б�
					String sql4 = "";
					sql4 = "insert into t_data_markerclass_seq (sn,class_sn,class_seq_name,class_seq_status) values (SEQ_T_Data_MARKERCLASS_SEQ.nextval,"+second_sn+",'SEQ_"+add_tablename+"','1')";
					int i2 = 0;
					i2 = db.insert(sql4);
					if(i2==1&&c_success==6){//����SEQ��Ĳ���
						//SEQ�����ɹ�
						//�������Ա�ľ���ͻ����Ի����ԵĲ��������һ��ģ��
						r = 1;
						db.commit();
					}else{
						r = 0;
						db.rollback();
					}
				}else{
					r = 0;
					db.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				r = 0;
				db.rollback();
			}
			finally{
				db.endTransaction();
				db.disconnect();
			}

		return r;
	}

	public int deletechildclass(DataClassBean bean) throws Exception {
		int r = 0;
		String sql = "update t_data_class_info set class_status='2' where sn="+bean.getSn_for_deletechildclass();
		r = db.update(sql);
		return r;
	}

	public int editchildclass(DataClassBean bean) throws Exception {
		int r = 0;
		String sql = "update t_data_class_info set class_name='"+bean.getChildclass_edit_classname()+"',class_status='"+bean.getChildclass_edit_status()+"' where sn="+bean.getChildclasssn_edit();
		r = db.update(sql);
		return r;
	}

	public PageUI loaddata2(Pagination pagination,
			DataClassBean bean) throws Exception {
		String sql = "select sn as classproperty_id,class_sn as classproperty_parentclass_id," +
				"class_tablename as classproperty_parentclass_tn,CLASS_COLUMNNAME as classproperty_parentclass_cn," +
				"CLASS_COMMENTS as classproperty_name,CLASS_COLUMNTYPE as classproperty_type," +
				"F_PARAMS('CLASS_COLUMNTYPE',CLASS_COLUMNTYPE) as classproperty_type_str,CLASS_NULLABLE as classproperty_ifnull," +
				"F_PARAMS('CLASS_NULLABLE',CLASS_NULLABLE) as classproperty_ifnull_str,CLASS_COLUMNSIZE as classproperty_size," +
				"CLASS_COLUMNSTATUS as classproperty_status,F_PARAMS('FIRST_CLASS_STATUS',CLASS_COLUMNSTATUS) as classproperty_status_str " +
				" from T_DATA_CLASS_STATUS where CLASS_SN="+bean.getChildclass_sn()+" and column_type='2' order by sn ";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, DataClassBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	
	public int checkTableName(
			DataClassBean bean) throws Exception {
		String sql = "select * from user_tables where table_name =UPPER('"+bean.getTable_name()+"')";
		int total = db.count(sql);
		return total;
	}
	public int checkColName(
			DataClassBean bean) throws Exception {
		String sql = "select * from user_col_comments where table_name =(select upper(class_table_name) from t_data_class_info where  sn='"+bean.getClassproperty_add_sn()+"') and column_name =UPPER('"+bean.getColumn_name()+"')";
		int total = db.count(sql);
		return total;
	}
	
	@SuppressWarnings("unchecked")
	public int submit_classproperty_add(DataClassBean bean) throws Exception {
		int r = 0;
		
//		System.out.println("��������SN:"+bean.getClassproperty_add_sn());
//		System.out.println("������������:"+bean.getClassproperty_add_name());
//		System.out.println("������������:"+bean.getClassproperty_add_type());
//		System.out.println("���������Ƿ�Ϊ��:"+bean.getClassproperty_add_ifnull());
//		System.out.println("�������ͳ���:"+bean.getClassproperty_add_size());
//		System.out.println("��������״̬:"+bean.getClassproperty_add_status());
		db.connect();
		db.startTransaction();
		try {
			String sql1 = "";
			sql1 = "select class_table_name from t_data_class_info where sn="+bean.getClassproperty_add_sn();
			String table_name = "";
			table_name = db.queryString(sql1);//�������Ͷ�Ӧ�ı���
			String sql2 = "";
			sql2 = "select column_name from USER_COL_COMMENTS where table_name='"+table_name+"' ";
			List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
			list_map = db.query(sql2);
			if(list_map.size()==0){
				r = 0;
			}else{
				String col_name = "";
				List<String> list = new ArrayList<String>();
				for(int i=0;i<list_map.size();i++){
					list.add(String.valueOf(list_map.get(i).get("column_name")));
				}
				for(int i=1;i<999;i++){
					String tmp_name = "";
					tmp_name = "MC_FIELD"+i;
					boolean f1 = true;
					for(int j=0;j<list.size();j++){
						if(tmp_name.equals(list.get(j))){
							f1=false;
							break;
						}
					}
					if(f1){
						col_name=tmp_name;
						break;
					}else{
						continue;
					}
				}
				if(!"".equals(col_name)){
					String typeStr = "";
					if("1".equals(bean.getClassproperty_add_type())){
						typeStr="VARCHAR2("+bean.getClassproperty_add_size()+" CHAR)";
					}else if("2".equals(bean.getClassproperty_add_type())){
						typeStr="NUMBER";
					}else if("3".equals(bean.getClassproperty_add_type())){
						typeStr="NUMBER(*,3)";
					}else if("4".equals(bean.getClassproperty_add_type())){
						typeStr="VARCHAR2(200 CHAR)";
					}
					String if_null = "";
					if("1".equals(bean.getClassproperty_add_ifnull())){
						if_null="";//���Կ�
					}else if("2".equals(bean.getClassproperty_add_ifnull())){
						if_null="NOT NULL ENABLE";
					}
					String sql3 = "";
					sql3 = "alter table "+table_name+" add "+bean.getColumn_name()+" "+typeStr+" "+if_null+" ";
					String sql4 = "";
					sql4 = "COMMENT ON COLUMN "+table_name+"."+bean.getColumn_name()+" IS '"+bean.getClassproperty_add_name()+"' ";
					db.runSql(sql3);//����
					db.runSql(sql4);//Ϊ�������мӱ�ע
					String sql5 = "";
					sql5 = "insert into t_data_class_status " +
							"(sn,CLASS_SN,class_tablename,class_columnname," +
							"class_comments,class_columntype,class_columnstatus," +
							"class_bakstatus1,class_bakstatus2," +
							"class_nullable,class_columnsize,column_type) values " +
							"(SEQ_T_Data_CLASS_STATUS.nextval,"
							+bean.getClassproperty_add_sn()+",'"+table_name+"','"
							+bean.getColumn_name().toUpperCase()+"','"+bean.getClassproperty_add_name()+"','"
							+bean.getClassproperty_add_type()+"','"+bean.getClassproperty_add_status()+"','1','1','"+bean.getClassproperty_add_ifnull()
							+"','"+(("1".equals(bean.getClassproperty_add_type()))?(bean.getClassproperty_add_size()):(("4".equals(bean.getClassproperty_add_type()))?("200"):("NULL")))+"','2')";
					int insertNum = db.insert(sql5);
					if(insertNum==0){
						r = 0;
					}else{
						r = 1;
					}
				}else{
					r = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			r = 0;
		}finally{
			if(r==1){
				db.commit();
			}else{
				db.rollback();
			}
			db.endTransaction();
			db.disconnect();
		}
		return r;
	}

	public int deleteclassproperty(DataClassBean bean) throws Exception {
		int r = 0;
		String sql = "update t_data_class_status set class_columnstatus='2' where column_type='2' and sn="+bean.getSn_for_deleteclassproperty();
		r = db.update(sql);
		return r;
	}

	public int editclassproperty(DataClassBean bean) throws Exception {
		int r = 0;
		int i1 = 0;
		db.connect();
		db.startTransaction();
		try {
			String sql = "update t_data_class_status set class_comments='"+bean.getName_for_editclassproperty()+"',class_columnstatus='"+bean.getStatus_for_editclassproperty()+"' where column_type='2' and sn="+bean.getSn_for_editclassproperty();
			i1 = db.update(sql);
			String table_name = "";
			String col_name = "";
			String sql1 = "select class_tablename from t_data_class_status where column_type='2' and sn="+bean.getSn_for_editclassproperty();
			table_name = db.queryString(sql1);
			String sql2 = "select class_columnname from t_data_class_status where column_type='2' and sn="+bean.getSn_for_editclassproperty();
			col_name = db.queryString(sql2);
			String sql3 = "COMMENT ON COLUMN \""+table_name+"\".\""+col_name+"\" IS '"+bean.getName_for_editclassproperty()+"' ";
			db.runSql(sql3);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(i1==1){
				r = 1;
				db.commit();
			}else{
				r = 0;
				db.rollback();
			}
			db.endTransaction();
			db.disconnect();
		}
		return r;
	}
}
