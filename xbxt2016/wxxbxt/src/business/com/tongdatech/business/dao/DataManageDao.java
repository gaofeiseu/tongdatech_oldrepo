package com.tongdatech.business.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.tongdatech.business.bean.DataBean;
import com.tongdatech.business.bean.ReturnBean;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class DataManageDao extends BaseDao {

 
 
	
	public List<Map> getSNINFO(UserInfo userinfo) throws Exception {
		String sql = "select SN value,class_name text from T_DATA_CLASS_INFO where post_class = '"+userinfo.getBrch_no()+"' and class_status=1" ;
		return  db.query(sql);
	}
	
	
	public PageUI doquery(Pagination pagination,DataBean bean) throws Exception
	{	
		if(bean.getSN()!=null&&!"".equals(bean.getSN()))
		{
		String sql="SELECT SN,POST_CLASS,MARKER_CLASS,CLASS_NAME,CLASS_TABLE_NAME,CLASS_TYPE1,CLASS_TYPE2,CLASS_STATUS " +
	     " FROM T_DATA_CLASS_INFO WHERE  CLASS_STATUS='1' and sn='"+bean.getSN()+"'" ;
	
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
				" from T_DATA_CLASS_STATUS where  CLASS_TABLENAME='"+class_table_name+"' and CLASS_COLUMNSTATUS='1' order by sn asc";
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
						col_names=col_name;
					else
						col_names+=" , " + col_name;
					if(!"".equals(bean.getQuery_string())&&bean.getQuery_string()!=null)
					{
						tmpSql=" "+col_name + " like '%" +bean.getQuery_string() +"%' " ;
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
					querySql=" where "+querySql;
				}
				else
				{
					if(!"".equals(bean.getQuery_property())&&bean.getQuery_property()!=null)
					{
						querySql=" where "+bean.getQuery_property();
					}
				}
	
				sql="select "+col_names+" from "+class_table_name+querySql;
				String listsql = db.queryPageStrOrder(sql +"  order by sn", pagination.getStnum(),pagination.getEdnum());
				
				PageUI rs = new PageUI();
				rs.setRows(db.query(listsql));
				int total = db.count(sql);
				rs.setTotal(total);
				pagination.setTotal(total);
				return rs;
		}
		return null;
	 
		 
	}
	@SuppressWarnings("unchecked")
	public List<Map> queryColumn(DataBean bean) throws Exception
	{
		String 	sql="";
		 
		sql="select class_tablename table_name,class_columnname column_name ,class_columnsize "+
		"data_length,class_nullable nullable,class_columntype data_type,class_comments comments "+
		" from T_DATA_CLASS_STATUS where  CLASS_SN='"+bean.getSN()+"'  and CLASS_COLUMNSTATUS='1'  order by sn asc";
		List<Map> colList = new ArrayList<Map>();
		colList = db.query(sql);
		return colList; 
	}
	
	public ReturnBean deleteData(DataBean bean) {
		ReturnBean returnBean = new ReturnBean();
		try {
			
			String sql3 = "";
			sql3 = "delete from "+bean.getQuery_string()+" where sn='"+bean.getSN()+"'";
			
			int ret = db.delete(sql3);
			
			if(ret==1){
				returnBean.setIf_success(true);
				returnBean.setMsg("数据删除成功！");
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
	 
	public ReturnBean doSave(DataBean bean,String filename) {
		if(bean.getQuery_string().equals(""))
		{
			return null;
		}
		ReturnBean returnBean = new ReturnBean();
		String params[]=bean.getQuery_string().split("\\^");
		String sn=bean.getSN();
		if("".equals(bean.getSN())||bean.getSN()==null)// 复制
		{
			try {
				if(!"".equals(filename))
				{
					params[2]=params[2]+","+bean.getQuery_operator();
					params[3]=params[3]+",'"+filename+"'";
				}
				String sql3 = "";
				sql3 = "insert into "+params[1]+"("+params[2]+") select "+params[3]+" from dual  ";
				
				int ret = db.insert(sql3);
				
				if(ret==1){
					returnBean.setIf_success(true);
					returnBean.setMsg("数据新增成功！");
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
			sql = "update "+params[1]+" set  SN=sn "+params[3]+" where  sn='"+bean.getSN()+"'";
			try {
				 
				int updateNum = db.update(sql);
				if(updateNum==1){
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
	/*
	 * 查询字段
	 */
	@SuppressWarnings("unchecked")
	public List<Map> toQueryColumn(DataBean bean) {
		String sql="select CLASS_TABLE_NAME from T_DATA_CLASS_INFO where sn='"+bean.getSN()+"'";
		List<Map> resultMap =new ArrayList<Map>();
		try {
			String table_name = db.queryString(sql);
		
			sql="select CLASS_COLUMNNAME,CLASS_COMMENTS,CLASS_COLUMNTYPE from T_DATA_CLASS_STATUS where CLASS_TABLENAME='"+table_name+"' and CLASS_COLUMNTYPE<>'4' and CLASS_COLUMNSTATUS='1' and column_type='2'";
			resultMap =db.query(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return resultMap;
	}

	
}
