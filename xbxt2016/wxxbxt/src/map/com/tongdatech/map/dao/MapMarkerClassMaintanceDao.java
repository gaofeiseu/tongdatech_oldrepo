package com.tongdatech.map.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tongdatech.map.bean.MapMarkerClassMaintanceBean;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.Brch;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;
/**
 * 
 * @author Mr.GaoFei
 *
 */
public class MapMarkerClassMaintanceDao extends BaseDao {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(MapMarkerClassMaintanceDao.class);

	public PageUI loaddata1(Pagination pagination,
			MapMarkerClassMaintanceBean bean,UserInfo userInfo) throws Exception {
		String addsql="";
		if(!"".equals(bean.getChildclass_status())&&bean.getChildclass_status()!=null)
		{
			addsql+=" and class_status='"+bean.getChildclass_status()+"'";
		}
//		System.out.println("t_user_type:"+bean.getT_user_type());
	//	addsql += " and post_class in ("+stringFunction(bean.getT_user_type())+") ";
		addsql += " and brch_no='"+userInfo.getBrch_no()+"' ";
		String sql = "select sn as childclass_sn,class_name as childclass_name,class_status as childclass_status,f_brchname(brch_no) as childclass_add_brch,read_brch_no as childclass_brchno,F_PARAMS('FIRST_CLASS_STATUS',class_status) as childclass_status_str from T_MAP_CLASS_INFO where marker_class='"+bean.getParentclass_sn()+"'"+addsql+" order by sn ";
//		System.out.println(sql);
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(this.childclass_brchstr(db.query(listsql, MapMarkerClassMaintanceBean.class)));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}
	private List<MapMarkerClassMaintanceBean> childclass_brchstr(List<MapMarkerClassMaintanceBean> list) throws Exception
	{
		String sql = "select brch_no,brch_name from t_sys_brch ";	
		List<Brch> b_list = db.query(sql,Brch.class); 
		List<MapMarkerClassMaintanceBean> l=new LinkedList<MapMarkerClassMaintanceBean>();
		for(MapMarkerClassMaintanceBean m:list )
		{
			String brch_str="";
		
			if(m.getChildclass_brchno()!=null&&!"null".equals(m.getChildclass_brchno())&&!"".equals(m.getChildclass_brchno()))
			{
				String br[]=m.getChildclass_brchno().split(",");
				 
				for(int i=0;i<br.length;i++)
				{
					String[] s_arr = br[i].split("->");
					if(s_arr.length==2){
						String chakan_brch_no = "";
						String beichakan_brch_no = "";
						chakan_brch_no = s_arr[0];
						beichakan_brch_no = s_arr[1];
						String chakan_brch_str = "";
						String beichakan_brch_str = "";
						for(Brch b:b_list)
						{
							if(b.getBrch_no().equals(chakan_brch_no))
							{
								chakan_brch_str = b.getBrch_name();
								break;
							}
						}
						for(Brch b:b_list)
						{
							if(b.getBrch_no().equals(beichakan_brch_no))
							{
								beichakan_brch_str = b.getBrch_name();
								break;
							}
						}
						if(!"".equals(chakan_brch_str)&&!"".equals(beichakan_brch_str)){
							if(!"".equals(brch_str))
							{
								brch_str+=","+chakan_brch_str+"->"+beichakan_brch_str;
							}else
							{
								brch_str+=chakan_brch_str+"->"+beichakan_brch_str;
							}
						}
					}
				}
			}
			m.setChildclass_brchstr(brch_str);
			l.add(m);
		}
		
		return l;
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

	public int addchildclass(MapMarkerClassMaintanceBean bean,UserInfo userinfo) throws Exception{
		int r = 0;
		
		System.out.println("部门类型:"+bean.getChildclass_add_brch()+" 主标注类型:"+bean.getChildclass_add_for_parentclassid());
		System.out.println("子类型名称:"+bean.getChildclass_add_classname()+" 子类型状态:"+bean.getChildclass_add_status());
		String childclass_add_brch = "";
		if(bean.getChildclass_add_brch()!=null&&!"".equals(bean.getChildclass_add_brch())){
			childclass_add_brch = bean.getChildclass_add_brch().replace("&gt;", ">");
		}
		if("1".equals(bean.getChildclass_add_for_parentclassid())||"2".equals(bean.getChildclass_add_for_parentclassid())||"3".equals(bean.getChildclass_add_for_parentclassid())){
			try {
				db.connect();
				db.startTransaction();
				String sql0 = "select SEQ_T_MAP_CLASS_INFO.nextval from dual ";
				String second_sn = "";
				second_sn = db.queryString(sql0);
				String add_tablename = "T_MAP_CLASS_MC"+second_sn;
				String sql = "insert into t_map_class_info" +
						" (sn,brch_no,marker_class,class_name,class_table_name,create_date,user_id,class_status,read_brch_no)" +
						" values " +
						"("+second_sn+",'"+userinfo.getBrch_no()+"','"+bean.getChildclass_add_for_parentclassid()+"','"
						+bean.getChildclass_add_classname()+"','"+add_tablename+"',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'"+userinfo.getUser_id()+"','1',replace('"+childclass_add_brch+"',' ','')) ";
				int r1 = 0;
				r1 = db.insert(sql);
				if(r1==1){//插入子类型表t_map_markerclass_second成功
					//准备创建特定的子类型表
					String sql1 = "";
					sql1 += "CREATE TABLE \""+add_tablename+"\"( ";
					sql1 += "\"SN\" NUMBER NOT NULL ENABLE";
//					sql1 += "\"STATUS_FLAG\" VARCHAR2(5 BYTE),\"LAT\" VARCHAR2(4000 BYTE) NOT NULL,\"LNG\" VARCHAR2(4000 BYTE) NOT NULL,\"MC_MARKERS\" VARCHAR2(500 BYTE),\"POST_FLAG\" VARCHAR2(5 BYTE)";
					sql1 += ")";
					db.runSql(sql1);
					
					//开始加入注释
					String sql2 = "";
					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"SN\" IS '编号' ";
					db.runSql(sql2);
					//2015-01-05注释
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"STATUS_FLAG\" IS '标志位' ";//系统重要标志位 1表示正常 2表示删除
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"LAT\" IS '纬度' ";
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"LNG\" IS '经度' ";
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"MC_MARKERS\" IS '特征变量' ";//该标注对应的特征变量：如果是点，那就是点图标对应图片的URL；如果是线，那就是颜色代码和线透明度与线粗度的组合，如#8b00ff||0.3||30，第一个表示线的颜色，0.3表示透明度，30表示线的粗度；如果是面，那暂时就是颜色代码#FF0000这样的
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"POST_FLAG\" IS '部门类型' ";//标注类型状态位：1-邮政；2-宜家
//					db.runSql(sql2);
					
//					String[] col_arr = {"SN","STATUS_FLAG","LAT","LNG","MC_MARKERS","POST_FLAG"};
//					String[] col_comments_arr = {"编号","标志位","纬度","经度","特征变量","部门类型"};
//					String[] col_type_arr = {"2","1","1","1","1","1"};
//					String[] col_type_ifnull = {"2","2","2","2","2","2"};
//					String[] col_type_size = {"NULL","5","4000","4000","500","5"};
					String[] col_arr = {"SN"};
					String[] col_comments_arr = {"编号"};
					String[] col_type_arr = {"2"};
					String[] col_type_ifnull = {"2"};
					String[] col_type_size = {"NULL"};
					int c_success = 0;
					for(int i=0;i<col_arr.length;i++){
						String sql5 = "";
						sql5 = "insert into t_map_class_status " +
								"(sn,CLASS_SN,class_tablename,class_columnname," +
								"class_comments,class_columntype,class_columnstatus," +
								"class_bakstatus1,class_bakstatus2," +
								"class_nullable,class_columnsize,COLUMN_TYPE) values " +
								"(SEQ_T_MAP_CLASS_STATUS.nextval,"
								+second_sn+",'"+add_tablename+"','"
								+col_arr[i]+"','"+col_comments_arr[i]+"','"
								+col_type_arr[i]+"','1','1','1','"+col_type_ifnull[i]
								+"','"+col_type_size[i]+"','1')";
						int insertNum = db.insert(sql5);
						if(insertNum==1){
							c_success+=1;
						}
					}
					
					
					//创建序列
					String sql3 = "";
					sql3 = "CREATE SEQUENCE \"SEQ_"+add_tablename+"\"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ";
					db.runSql(sql3);
					
					//插入子类型序列表
					String sql4 = "";
					sql4 = "insert into t_map_markerclass_seq (sn,class_sn,class_seq_name,class_seq_status) values (SEQ_T_MAP_MARKERCLASS_SEQ.nextval,"+second_sn+",'SEQ_"+add_tablename+"','1')";
					int i2 = 0;
					i2 = db.insert(sql4);
					if(i2==1&&c_success==1){//进行SEQ表的插入
						//SEQ表插入成功
						//子属性表的具体客户个性化属性的插入放在另一个模块
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
		}else if("4".equals(bean.getChildclass_add_for_parentclassid())){
			//圆形标注
			try {
				db.connect();
				db.startTransaction();
				String sql0 = "select SEQ_T_MAP_CLASS_INFO.nextval from dual ";
				String second_sn = "";
				second_sn = db.queryString(sql0);
				String add_tablename = "T_MAP_CLASS_MC"+second_sn;
				String sql = "insert into t_map_class_info" +
					" (sn,brch_no,marker_class,class_name,class_table_name,create_date,user_id,class_status,read_brch_no)" +
					" values " +
					"("+second_sn+",'"+userinfo.getBrch_no()+"','"+bean.getChildclass_add_for_parentclassid()+"','"
					+bean.getChildclass_add_classname()+"','"+add_tablename+"',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'"+userinfo.getUser_id()+"','1',replace('"+childclass_add_brch+"',' ','')) ";
				int r1 = 0;
				r1 = db.insert(sql);
				if(r1==1){//插入子类型表t_map_markerclass_second成功
					//准备创建特定的子类型表
					String sql1 = "";
					sql1 += "CREATE TABLE \""+add_tablename+"\"( ";
					sql1 += "\"SN\" NUMBER NOT NULL ENABLE";
//					sql1 += "\"STATUS_FLAG\" VARCHAR2(5 BYTE),\"LAT\" VARCHAR2(4000 BYTE),\"LNG\" VARCHAR2(4000 BYTE),\"RADIUS\" VARCHAR2(1000 BYTE),\"MC_MARKERS\" VARCHAR2(500 BYTE),\"POST_FLAG\" VARCHAR2(5 BYTE)";
					sql1 += ")";
					db.runSql(sql1);
					
					//开始加入注释
					String sql2 = "";
					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"SN\" IS '编号' ";
					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"STATUS_FLAG\" IS '标志位' ";//系统重要标志位 1表示正常 2表示删除
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"LAT\" IS '纬度' ";//圆形标注的圆心位置的纬度
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"LNG\" IS '经度' ";//圆形标注的圆心位置的经度
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"RADIUS\" IS '半径' ";//圆形标注的半径
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"MC_MARKERS\" IS '特征变量' ";//该标注对应的特征变量：如果是点，那就是点图标对应图片的URL；如果是线，那就是颜色代码和线透明度与线粗度的组合，如#8b00ff||0.3||30，第一个表示线的颜色，0.3表示透明度，30表示线的粗度；如果是面，那暂时就是颜色代码#FF0000这样的
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"POST_FLAG\" IS '部门类型' ";//标注类型状态位：1-邮政；2-宜家
//					db.runSql(sql2);
					
//					String[] col_arr = {"SN","STATUS_FLAG","LAT","LNG","RADIUS","MC_MARKERS","POST_FLAG"};
//					String[] col_comments_arr = {"编号","标志位","纬度","经度","半径","特征变量","部门类型"};
//					String[] col_type_arr = {"2","1","1","1","1","1","1"};
//					String[] col_type_ifnull = {"2","2","2","2","2","2","2"};
//					String[] col_type_size = {"NULL","5","4000","4000","1000","500","5"};
					
					String[] col_arr = {"SN"};
					String[] col_comments_arr = {"编号"};
					String[] col_type_arr = {"2"};
					String[] col_type_ifnull = {"2"};
					String[] col_type_size = {"NULL"};
					
					int c_success = 0;
					for(int i=0;i<col_arr.length;i++){
						String sql5 = "";
						sql5 = "insert into t_map_class_status " +
								"(sn,CLASS_SN,class_tablename,class_columnname," +
								"class_comments,class_columntype,class_columnstatus," +
								"class_bakstatus1,class_bakstatus2," +
								"class_nullable,class_columnsize,COLUMN_TYPE) values " +
								"(SEQ_T_MAP_CLASS_STATUS.nextval,"
								+second_sn+",'"+add_tablename+"','"
								+col_arr[i]+"','"+col_comments_arr[i]+"','"
								+col_type_arr[i]+"','1','1','1','"+col_type_ifnull[i]
								+"','"+col_type_size[i]+"','1')";
						int insertNum = db.insert(sql5);
						if(insertNum==1){
							c_success+=1;
						}
					}
					
					
					//创建序列
					String sql3 = "";
					sql3 = "CREATE SEQUENCE \"SEQ_"+add_tablename+"\"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ";
					db.runSql(sql3);
					
					//插入子类型序列表
					String sql4 = "";
					sql4 = "insert into t_map_markerclass_seq (sn,class_sn,class_seq_name,class_seq_status) values (SEQ_T_MAP_MARKERCLASS_SEQ.nextval,"+second_sn+",'SEQ_"+add_tablename+"','1')";
					int i2 = 0;
					i2 = db.insert(sql4);
					if(i2==1&&c_success==1){//进行SEQ表的插入
						//SEQ表插入成功
						//子属性表的具体客户个性化属性的插入放在另一个模块
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
		}else if("5".equals(bean.getChildclass_add_for_parentclassid())){
			//文本框标注
			try {
				db.connect();
				db.startTransaction();
				String sql0 = "select SEQ_T_MAP_CLASS_INFO.nextval from dual ";
				String second_sn = "";
				second_sn = db.queryString(sql0);
				String add_tablename = "T_MAP_CLASS_MC"+second_sn;
				String sql = "insert into t_map_class_info" +
				" (sn,brch_no,marker_class,class_name,class_table_name,create_date,user_id,class_status,read_brch_no)" +
						" values " +
						"("+second_sn+",'"+userinfo.getBrch_no()+"','"+bean.getChildclass_add_for_parentclassid()+"','"
						+bean.getChildclass_add_classname()+"','"+add_tablename+"',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'"+userinfo.getUser_id()+"','1',replace('"+childclass_add_brch+"',' ','')) ";
				int r1 = 0;
				r1 = db.insert(sql);
				if(r1==1){//插入子类型表t_map_markerclass_second成功
					//准备创建特定的子类型表
					String sql1 = "";
					sql1 += "CREATE TABLE \""+add_tablename+"\"( ";
					sql1 += "\"SN\" NUMBER NOT NULL ENABLE";
//					sql1 += "\"STATUS_FLAG\" VARCHAR2(5 BYTE),\"LAT\" VARCHAR2(4000 BYTE),\"LNG\" VARCHAR2(4000 BYTE),\"TEXT_CONTENT\" VARCHAR2(1000 BYTE),\"ZOOM\" VARCHAR2(3 BYTE),\"MC_MARKERS\" VARCHAR2(500 BYTE),\"POST_FLAG\" VARCHAR2(5 BYTE)";
					sql1 += ")";
					db.runSql(sql1);
					
					//开始加入注释
					String sql2 = "";
					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"SN\" IS '编号' ";
					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"STATUS_FLAG\" IS '标志位' ";//系统重要标志位 1表示正常 2表示删除
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"LAT\" IS '纬度' ";//圆形标注的圆心位置的纬度
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"LNG\" IS '经度' ";//圆形标注的圆心位置的经度
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"TEXT_CONTENT\" IS '文本内容' ";//文本框标注的显示的文本内容
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"ZOOM\" IS '地图层级' ";//文本框标注的所属地图层级
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"MC_MARKERS\" IS '特征变量' ";//该标注对应的特征变量：如果是点，那就是点图标对应图片的URL；如果是线，那就是颜色代码和线透明度与线粗度的组合，如#8b00ff||0.3||30，第一个表示线的颜色，0.3表示透明度，30表示线的粗度；如果是面，那暂时就是颜色代码#FF0000这样的
//					db.runSql(sql2);
//					sql2 = "COMMENT ON COLUMN \""+add_tablename+"\".\"POST_FLAG\" IS '部门类型' ";//标注类型状态位：1-邮政；2-宜家
//					db.runSql(sql2);
					
//					String[] col_arr = {"SN","STATUS_FLAG","LAT","LNG","TEXT_CONTENT","ZOOM","MC_MARKERS","POST_FLAG"};
//					String[] col_comments_arr = {"编号","标志位","纬度","经度","文本内容","地图层级","特征变量","部门类型"};
//					String[] col_type_arr = {"2","1","1","1","1","1","1","1"};
//					String[] col_type_ifnull = {"2","2","2","2","2","2","2","2"};
//					String[] col_type_size = {"NULL","5","4000","4000","1000","3","500","5"};
					
					String[] col_arr = {"SN"};
					String[] col_comments_arr = {"编号"};
					String[] col_type_arr = {"2"};
					String[] col_type_ifnull = {"2"};
					String[] col_type_size = {"NULL"};
					int c_success = 0;
					for(int i=0;i<col_arr.length;i++){
						String sql5 = "";
						sql5 = "insert into t_map_class_status " +
								"(sn,CLASS_SN,class_tablename,class_columnname," +
								"class_comments,class_columntype,class_columnstatus," +
								"class_bakstatus1,class_bakstatus2," +
								"class_nullable,class_columnsize,COLUMN_TYPE) values " +
								"(SEQ_T_MAP_CLASS_STATUS.nextval,"
								+second_sn+",'"+add_tablename+"','"
								+col_arr[i]+"','"+col_comments_arr[i]+"','"
								+col_type_arr[i]+"','1','1','1','"+col_type_ifnull[i]
								+"','"+col_type_size[i]+"','1')";
						int insertNum = db.insert(sql5);
						if(insertNum==1){
							c_success+=1;
						}
					}
					
					
					//创建序列
					String sql3 = "";
					sql3 = "CREATE SEQUENCE \"SEQ_"+add_tablename+"\"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ";
					db.runSql(sql3);
					
					//插入子类型序列表
					String sql4 = "";
					sql4 = "insert into t_map_markerclass_seq (sn,class_sn,class_seq_name,class_seq_status) values (SEQ_T_MAP_MARKERCLASS_SEQ.nextval,"+second_sn+",'SEQ_"+add_tablename+"','1')";
					int i2 = 0;
					i2 = db.insert(sql4);
					if(i2==1&&c_success==1){//进行SEQ表的插入
						//SEQ表插入成功
						//子属性表的具体客户个性化属性的插入放在另一个模块
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
		}
		
		return r;
	}

	public int deletechildclass(MapMarkerClassMaintanceBean bean) throws Exception {
		int r = 0;
		String sql = "update t_map_class_info set class_status='2' where sn="+bean.getSn_for_deletechildclass();
		r = db.update(sql);
		return r;
	}

	public int editchildclass(MapMarkerClassMaintanceBean bean) throws Exception {
		int r = 0;
		String sql = "";
		String read_brch_no = "";
		if("1".equals(bean.getChildclass_edit_brch_type())){
			//覆盖
			read_brch_no = bean.getChildclass_edit_brch();
		}else if("0".equals(bean.getChildclass_edit_brch_type())){
			//新增
			read_brch_no = db.queryString("select read_brch_no from t_map_class_info where sn="+bean.getChildclasssn_edit());
			if(read_brch_no==null||"".equals(read_brch_no.trim())){
				read_brch_no = bean.getChildclass_edit_brch();
			}else{
				read_brch_no = read_brch_no + "," + bean.getChildclass_edit_brch();
			}
		}
		read_brch_no = read_brch_no.replace("&gt;", ">");
		sql = "update t_map_class_info set class_name='"+bean.getChildclass_edit_classname()+"',READ_BRCH_NO=replace('"+read_brch_no+"',' ',''),class_status='"+bean.getChildclass_edit_status()+"' where sn="+bean.getChildclasssn_edit();
		r = db.update(sql);
		return r;
	}

	public PageUI loaddata2(Pagination pagination,
			MapMarkerClassMaintanceBean bean,UserInfo userInfo) throws Exception {
		String sql = "select sn as classproperty_id,class_sn as classproperty_parentclass_id," +
				"class_tablename as classproperty_parentclass_tn,CLASS_COLUMNNAME as classproperty_parentclass_cn," +
				"CLASS_COMMENTS as classproperty_name,CLASS_COLUMNTYPE as classproperty_type," +
				"F_PARAMS('CLASS_COLUMNTYPE',CLASS_COLUMNTYPE) as classproperty_type_str,CLASS_NULLABLE as classproperty_ifnull," +
				"F_PARAMS('CLASS_NULLABLE',CLASS_NULLABLE) as classproperty_ifnull_str,CLASS_COLUMNSIZE as classproperty_size," +
				"CLASS_COLUMNSTATUS as classproperty_status,F_PARAMS('FIRST_CLASS_STATUS',CLASS_COLUMNSTATUS) as classproperty_status_str " +
				" from T_MAP_CLASS_STATUS where CLASS_SN="+bean.getChildclass_sn()+" and column_type='2' order by sn ";
		String listsql = db.queryPageStrOrder(sql, pagination.getStnum(),pagination.getEdnum());
		PageUI rs = new PageUI();
		rs.setRows(db.query(listsql, MapMarkerClassMaintanceBean.class));
		int total = db.count(sql);
		rs.setTotal(total);
		pagination.setTotal(total);
		return rs;
	}

	@SuppressWarnings("unchecked")
	public int submit_classproperty_add(MapMarkerClassMaintanceBean bean) throws Exception {
		int r = 0;
		
//		System.out.println("子类型SN:"+bean.getClassproperty_add_sn());
//		System.out.println("子类型名称:"+bean.getClassproperty_add_name());
//		System.out.println("子类型类型:"+bean.getClassproperty_add_type());
//		System.out.println("子类型是否为空:"+bean.getClassproperty_add_ifnull());
//		System.out.println("子类型长度:"+bean.getClassproperty_add_size());
//		System.out.println("子类型状态:"+bean.getClassproperty_add_status());
		db.connect();
		db.startTransaction();
		try {
			String sql1 = "";
			sql1 = "select class_table_name from t_map_class_info where sn='"+bean.getClassproperty_add_sn()+"'";
			String table_name = "";
			table_name = db.queryString(sql1);//子类型对应的表名
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
						if_null="";//可以空
					}else if("2".equals(bean.getClassproperty_add_ifnull())){
						if_null="NOT NULL ENABLE";
					}
					String sql3 = "";
					sql3 = "alter table "+table_name+" add "+col_name+" "+typeStr+" "+if_null+" ";//为子类型添加新属性对应的列
					String sql4 = "";
					sql4 = "COMMENT ON COLUMN \""+table_name+"\".\""+col_name+"\" IS '"+bean.getClassproperty_add_name()+"' ";
					db.runSql(sql3);//加列
					db.runSql(sql4);//为新增的列加备注
					String sql5 = "";
					sql5 = "insert into t_map_class_status " +
							"(sn,CLASS_SN,class_tablename,class_columnname," +
							"class_comments,class_columntype,class_columnstatus," +
							"class_bakstatus1,class_bakstatus2," +
							"class_nullable,class_columnsize,column_type) values " +
							"(SEQ_T_MAP_CLASS_STATUS.nextval,"
							+bean.getClassproperty_add_sn()+",'"+table_name+"','"
							+col_name+"','"+bean.getClassproperty_add_name()+"','"
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

	public int deleteclassproperty(MapMarkerClassMaintanceBean bean) throws Exception {
		int r = 0;
		String sql = "update t_map_class_status set class_columnstatus='2' where column_type='2' and sn="+bean.getSn_for_deleteclassproperty();
		r = db.update(sql);
		return r;
	}

	public int editclassproperty(MapMarkerClassMaintanceBean bean) throws Exception {
		int r = 0;
		int i1 = 0;
		db.connect();
		db.startTransaction();
		try {
			String sql = "update t_map_class_status set class_comments='"+bean.getName_for_editclassproperty()+"',class_columnstatus='"+bean.getStatus_for_editclassproperty()+"' where column_type='2' and sn="+bean.getSn_for_editclassproperty();
			i1 = db.update(sql);
			String table_name = "";
			String col_name = "";
			String sql1 = "select class_tablename from t_map_class_status where column_type='2' and sn="+bean.getSn_for_editclassproperty();
			table_name = db.queryString(sql1);
			String sql2 = "select class_columnname from t_map_class_status where column_type='2' and sn="+bean.getSn_for_editclassproperty();
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
	public int copychildclass(MapMarkerClassMaintanceBean bean,UserInfo userinfo) throws Exception {
		int r = 0;
		String sql = "select SEQ_T_MAP_CLASS_INFO.nextval from dual ";
		String sn = "";
		sn = db.queryString(sql);
		String add_tablename = "T_MAP_CLASS_MC"+sn;

		
		sql = "insert into t_map_class_info" +
		" (sn,brch_no,marker_class,class_name,class_table_name,create_date,user_id,class_status,read_brch_no)" +
		" select "+sn+",'"+userinfo.getBrch_no()+"',marker_class,'"
		+bean.getChildclass_name()+"','"+add_tablename+"',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'"+userinfo.getUser_id()+"',class_status,read_brch_no  from t_map_class_info where sn="+bean.getChildclass_sn();
		r = db.update(sql);
		if(r==1)
		{
			sql = "insert into t_map_class_status " +
					"(sn,CLASS_SN,class_tablename,class_columnname," +
					"class_comments,class_columntype,class_columnstatus," +
					"class_bakstatus1,class_bakstatus2," +
					"class_nullable,class_columnsize,COLUMN_TYPE)   " +
					" select SEQ_T_MAP_CLASS_STATUS.nextval,"
					+sn+",'"+add_tablename+"',class_columnname,"+
					"class_comments,class_columntype,class_columnstatus," +
					"class_bakstatus1,class_bakstatus2," +
					"class_nullable,class_columnsize,COLUMN_TYPE from t_map_class_status where class_sn="+bean.getChildclass_sn();
			r =0;
			r = db.insert(sql);
			
			if(r>0)
			{
				sql = "create table  " +add_tablename +"  as select * from T_MAP_CLASS_MC"+bean.getChildclass_sn()+" where 1=2";
			
				r = db.runSql(sql);
			
				//创建序列
				String sql3 = "";
				sql3 = "CREATE SEQUENCE \"SEQ_"+add_tablename+"\"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ";
				db.runSql(sql3);
				String sql4 = "";
				sql4 = "insert into t_map_markerclass_seq (sn,class_sn,class_seq_name,class_seq_status) values (SEQ_T_MAP_MARKERCLASS_SEQ.nextval,"+sn+",'SEQ_"+add_tablename+"','1')";
				r= 0;
				r = db.insert(sql4); 
				if(r>0){ 
					db.commit();
				}else{
					r = 0;
					db.rollback();
				}
			}
			else{
				r = 0;
				db.rollback();
			}
		}
		else{
			r = 0;
			db.rollback();
		}
		return r;
	}
	
	public List<Brch> brchTree(UserInfo userinfo) throws Exception {
		/*********机构选择从最高级开始 2015.5.22 ************************************/
		//String sql = "select brch_no,brch_up,brch_name,brch_mode from t_sys_brch start with brch_no='"+userinfo.getBrch_no()+"'   connect by prior brch_no=brch_up";	
		String sql = "select brch_no,brch_up,brch_name,brch_mode from t_sys_brch start with brch_no='113080'   connect by prior brch_no=brch_up";	
	
		List<Brch> list = db.query(sql,Brch.class); 
		return list;
	}
}
