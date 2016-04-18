package com.tongdatech.map.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tongdatech.map.bean.MapBean;
import com.tongdatech.map.bean.ReturnBean;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class MapDataManageDao extends BaseDao {

 
 
	
	public List<Map> getSNINFO(UserInfo userinfo,MapBean bean) throws Exception {
//		String sql = "select SN value,class_name text from T_MAP_CLASS_INFO where  brch_no='"+userinfo.getBrch_no()+"' and  marker_class='"+bean.getMarker_class()+"' and class_status=1" ;
		String sql = "select SN value,class_name text from T_MAP_CLASS_INFO where marker_class='"+bean.getMarker_class()+"' and class_status=1" ;
		return  db.query(sql);
	}
	
	
	public PageUI doquery(Pagination pagination,MapBean bean,UserInfo userinfo) throws Exception
	{	
		if(bean.getSN()!=null&&!"".equals(bean.getSN()))
		{
			String  sql="SELECT SN,brch_no,MARKER_CLASS,CLASS_NAME,CLASS_TABLE_NAME,CLASS_STATUS " +
					" FROM T_MAP_CLASS_INFO WHERE  CLASS_STATUS='1' and sn='"+bean.getSN()+"'" ;
			List<Map<String,Object>> list = db.query(sql);
			Map tmp_map=null;
			String class_table_name="";
			String col_name="";
			String col_names="";
			String col_data_type="";
			@SuppressWarnings("unused")
			String col_comments="";
			String querySql = "";
			String tmpSql = "";
	 
				
				querySql="";
				tmp_map=list.get(0);
				class_table_name=(String)tmp_map.get("class_table_name");
	 	
				sql="select class_tablename table_name,class_columnname column_name ,class_columnsize "+
				"data_length,class_nullable nullable,class_columntype data_type,class_comments comments "+
				" from T_MAP_CLASS_STATUS where  CLASS_TABLENAME='"+class_table_name+"' and CLASS_COLUMNSTATUS='1' order by sn asc";
				List<Map<String,Object>> colList =new ArrayList<Map<String,Object>> ();;
				colList = db.query(sql);
	
				Map colMap=null;
				for(int j=0;j<colList.size();j++){
					tmpSql="";
					colMap=colList.get(j);
					col_name=(String)colMap.get("column_name");
					col_data_type=(String)colMap.get("data_type");
					col_comments=(String)colMap.get("comments");
					if(col_names.equals(""))
						col_names="a."+col_name;
					else
						col_names+=" , " + "a."+col_name;
					if(!"".equals(bean.getQuery_string())&&bean.getQuery_string()!=null)
					{
						tmpSql=" "+"a."+col_name + " like '%" +bean.getQuery_string() +"%' " ;
					}
					if(!tmpSql.equals("")){
						if(querySql.equals(""))
							querySql=tmpSql;
						else
							querySql+=" OR " + tmpSql;
					}
				}
				if(!querySql.equals(""))
				{
					querySql=" and "+querySql;
				}
		 
				sql=" select class_tablename table_name,class_columnname column_name ,class_columnsize "+
				"data_length,class_nullable nullable,class_columntype data_type,class_comments comments from T_MAP_BASIC_COLUMN where marker_class='"+tmp_map.get("marker_class")+"' or marker_class is null" ; 
				List<Map<String,Object>> b_colList =new ArrayList<Map<String,Object>> ();;
				b_colList = db.query(sql);
				String b_col_names="";
				for(int j=0;j<b_colList.size();j++){
					tmpSql="";
					colMap=b_colList.get(j);
					col_name=(String)colMap.get("column_name");
					col_data_type=(String)colMap.get("data_type");
					col_comments=(String)colMap.get("comments");
					if(b_col_names.equals(""))
						b_col_names="b."+col_name;
					else
						b_col_names+=" , " + "b."+col_name; 
				}
				
				sql="select "+col_names+","+b_col_names+" from "+class_table_name+" a , t_map_class_basics b  where b.MARKER_SN=a.sn and b.op_brch='"+userinfo.getBrch_no()
						+"' and b.TABLE_NAME='"+class_table_name+"' "+querySql;
				String listsql = db.queryPageStrOrder(sql +"  order by a.sn", pagination.getStnum(),pagination.getEdnum());
				
				PageUI rs = new PageUI();
				rs.setRows(db.query(listsql));
				int total = db.count(sql);
				rs.setTotal(total);
				pagination.setTotal(total);
				return rs;
		}
		return null;
	 
		 
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> queryColumn(MapBean bean) throws Exception
	{
		String 	sql="";
		 
		sql="select  table_name, column_name ,  data_length, nullable, data_type, comments  from(select class_tablename table_name,class_columnname column_name ,class_columnsize "+
		"data_length,class_nullable nullable,class_columntype data_type,class_comments comments "+
		" from T_MAP_CLASS_STATUS where  CLASS_SN='"+bean.getSN()+"'  and CLASS_COLUMNSTATUS='1' order by sn asc ) " +
		" union all "+	
			" select class_tablename table_name,class_columnname column_name ,class_columnsize "+
		"data_length,class_nullable nullable,class_columntype data_type,class_comments comments from T_MAP_BASIC_COLUMN where marker_class='"+bean.getMarker_class()+"' or marker_class is null";
	
		List<Map> colList = new ArrayList<Map>();
		colList = db.query(sql);
		return colList; 
	}
	
	public ReturnBean deleteData(MapBean bean) {
		ReturnBean returnBean = new ReturnBean();
		try { 
			String sql3 = "";
			sql3 = "delete from "+bean.getQuery_string()+" where sn in("+bean.getSN()+")";
			
			int ret = db.delete(sql3);
			sql3 = "delete from t_map_class_basics  where marker_sn in("+bean.getSN()+") and table_name='"+bean.getQuery_string()+"'";
			int ret1 = db.delete(sql3);
			if(ret==bean.getSN().split(",").length&&ret1==bean.getSN().split(",").length){
				returnBean.setIf_success(true);
				returnBean.setMsg("信息删除成功！");
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("数据库数据异常！9N3N4J2H3L！");
			}
		 
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		
		
		
		return returnBean;
	}
	 
	public ReturnBean doSave(MapBean bean,String filename,UserInfo userInfo) throws Exception {
		if(bean.getQuery_string().equals(""))
		{
			return null;
		}
		ReturnBean returnBean = new ReturnBean();
		String params[]=bean.getQuery_string().split("\\^");
		String sn=bean.getSN();
		String marker_class=db.queryString("select marker_class from T_MAP_CLASS_INFO where class_table_name ='"+params[1]+"'");
		String col_sql="select class_columnname from  T_MAP_BASIC_COLUMN where marker_class='"+marker_class+"' or marker_class is null";
		List<Map> colList = new ArrayList<Map>();
		colList = db.query(col_sql);
		if("".equals(bean.getSN())||bean.getSN()==null)// 复制
		{
			try {
				if(!"".equals(filename))
				{
					params[2]=params[2]+","+bean.getQuery_operator();
					params[3]=params[3]+",'"+filename+"'";
				}
				String colsArray[]=params[2].split(",");
				String valueArray[]=params[3].split(",");
				
				String basci_cols="";
				String cols="";
				String values="";
				String basci_values="";
				String show_text="";
				for(int i=0;i<colsArray.length;i++)
				{
					for(int j=0;j<colList.size();j++)
					{
						if(!colsArray[i].endsWith("SN"))
						{
							if(colsArray[i].toUpperCase().equals(colList.get(j).get("class_columnname").toString()))
							{
								basci_cols+=","+colsArray[i];
								basci_values+=",'"+valueArray[i].replaceAll("'", "")+"'";
								break;
							}
							else if(j==colList.size()-1)
							{
								cols+=","+colsArray[i];
								//values+=","+valueArray[i];
								values+=",'"+valueArray[i].replaceAll("'", "")+"'";
							}
							
							if("MC_FIELD1".equals(colsArray[i].toUpperCase()))
							{
								//show_text=valueArray[i];
								show_text="'"+valueArray[i].replaceAll("'", "")+"'";
							}
						}
					}
				}
				String sql3 = "";
				String sql="select SEQ_"+params[1]+".nextval from dual";
				int new_sn=db.queryInt(sql);
				sql3 = "insert into "+params[1]+"(SN"+cols+") values("+new_sn+values+")";
				
				int ret = db.insert(sql3);
				sql="select SEQ_T_MAP_BASIC.nextval from dual";
				int basic_sn=db.queryInt(sql);
				sql3 = "insert into t_map_class_basics(SN,op_brch,SHOW_TEXT,STATUS_FLAG,MARKER_SN,TABLE_NAME,MARKER_TYPE"+basci_cols+") values("+basic_sn+",'"+userInfo.getBrch_no()+"',"+show_text+",1,"+new_sn+",'"+params[1]+"',"+marker_class+basci_values+")";
				System.out.println("sql3:"+sql3);
				int ret1 = db.insert(sql3);
				if(ret==1&&ret1==1){
					returnBean.setIf_success(true);
					returnBean.setMsg("信息新增成功！");
				}else{
					returnBean.setIf_success(false);
					returnBean.setMsg("数据库数据异常！9N3N4J2H3L！");
				}
			 
			} catch (Exception e) {
				e.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg(e.getMessage());
			}
		
		
		}
		else if(!"".equals(bean.getSN()))//更新
		{
	  		
			String sql = "";
			if(!"".equals(filename))
			{ 
				params[3]=params[3]+","+bean.getQuery_operator()+"='"+filename+"'";
			}
			String cols[]=params[3].split(",");
			String basci_cols="";
			String colss="";
			String show_text="";
			
			for(int i=0;i<cols.length;i++)
			{
				 if(cols[i].length()>0)
				 {
				for(int j=0;j<colList.size();j++)
				{
			 
						if(cols[i].toUpperCase().indexOf(colList.get(j).get("class_columnname").toString()+"=")!=-1)
						{
							basci_cols+=","+cols[i].split("=")[0]+"='"+cols[i].split("=")[1].replaceAll("'", "")+"'"+""; 
							break;
						}
						else if(j==colList.size()-1)
						{
							colss+=","+cols[i].split("=")[0]+"='"+cols[i].split("=")[1].replaceAll("'", "")+"'";
					 
						} 
						if(cols[i].toUpperCase().indexOf("MC_FIELD1=")!=-1)
						{
							show_text=","+cols[i].replace("MC_FIELD1", "SHOW_TEXT").split("=")[0]+"='"+cols[i].replace("MC_FIELD1", "SHOW_TEXT").split("=")[1].replaceAll("'", "")+"'";
						}
					
				}
				 }
			}
			sql = "update "+params[1]+" set  SN=sn "+colss+" where  sn='"+bean.getSN()+"'";
			try {
				 
				int updateNum = db.update(sql);
				sql = "update t_map_class_basics set  SN=sn "+basci_cols+show_text+" where  marker_sn='"+bean.getSN()+"' and table_name='"+params[1].toUpperCase()+"'";
				int updateNum1 = db.update(sql);
				if(updateNum==1&&updateNum1==1){
					returnBean.setIf_success(true);
					returnBean.setMsg("修改成功！");
					db.commit();
				}else{
					returnBean.setIf_success(false);
					returnBean.setMsg("数据库操作失败！92J3HL1J3N4！");
					db.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnBean.setIf_success(false);
				returnBean.setMsg(e.getMessage());
				try {
					db.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
					returnBean.setIf_success(false);
					returnBean.setMsg(e2.getMessage());
				}
			} 
		}
		return returnBean;
		
	} 
}
