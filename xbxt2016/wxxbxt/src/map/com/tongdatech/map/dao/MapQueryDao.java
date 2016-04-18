package com.tongdatech.map.dao;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.tongdatech.map.bean.MapBean;
import com.tongdatech.map.bean.ReturnBean;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserInfo;

public class MapQueryDao extends BaseDao {

 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> getParams(String type) throws Exception {
		String sql = "select value,text from t_sys_param where type='"+type+"' and flag=1 order by order_id asc" ;
		return  db.query(sql);
	}
	/**
	 * 标注查询中树的市级层查询
	 * @param brch_no
	 * @param children_class_sn
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> getCityList(String brch_no, String children_class_sn) throws Exception {
		List<Map> list_map = new ArrayList<Map>();
		String children_class_table_name = "T_MAP_CLASS_MC"+(int)(Double.parseDouble(children_class_sn));
		String sql = "";
		sql = "select a.* from (select a.brch_no,a.brch_name from t_sys_brch a,t_map_class_basics b,t_map_class_info c " +
				" where b.table_name='"+children_class_table_name+"' and c.sn="+children_class_sn+
				" and a.brch_no=b.op_brch and (b.op_brch in (select brch_no from t_sys_brch start with brch_no = '"+brch_no+"' connect by prior brch_up=brch_no) " +
				" or b.op_brch in(select brch_no from t_sys_brch start with brch_no = '"+brch_no+"' connect by prior brch_no=brch_up) " +
				" or (c.class_table_name=b.table_name and instr(c.read_brch_no,'"+brch_no+"->'||b.op_brch)>0) )" +
				" group by a.brch_no,a.brch_name) a,t_sys_brch b where a.brch_no=b.brch_no order by b.order_id";
		list_map = db.query(sql);
		return list_map;
	}
	/**
	 * 标注查询中树的网店层查询
	 * @param brch_no
	 * @param children_class_sn
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> getWDList(String brch_no, String children_class_sn) throws Exception {
		List<Map> list_map = new ArrayList<Map>();
//		String children_class_table_name = "T_MAP_CLASS_MC"+(new BigDecimal(children_class_sn)).intValue();
		String sql = "";
		sql = "";
		list_map = db.query(sql);
		return list_map;
	}
	/**
	 * 标注查询界面上子类型的获取函数
	 * @param brch_no
	 * @param maker_type
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> getMaptables(String brch_no,String maker_type) throws Exception {
//		String sql = "select SN,class_name||'（'||F_brchname(brch_no)||'）' class_name from T_MAP_CLASS_INFO where (brch_no='"+brch_no+"' or F_Map_read_brch_check(read_brch_no,'"+brch_no+"')>0 )and  marker_class='"+maker_type+"' and class_status=1" ;
//		String sql = "select SN,class_name from T_MAP_CLASS_INFO where marker_class='"+maker_type+"' and class_status=1" ;
		String sql = "";
		sql = "select a.sn as SN,a.class_name as class_name from t_map_class_info a "
				+" where a.marker_class='"+maker_type+"' and a.class_status='1'  "
				+" and exists(select 1 from t_map_class_basics b where b.table_name=a.class_table_name  "
				+" and ( "
				+" (b.op_brch in (select brch_no from t_sys_brch start with brch_no = '"+brch_no+"' connect by prior brch_up=brch_no)) "
				+" or (b.op_brch in (select brch_no from t_sys_brch start with brch_no = '"+brch_no+"' connect by prior brch_no=brch_up)) "
				+" or (a.class_table_name=b.table_name and instr(a.read_brch_no,'"+brch_no+"->'||b.op_brch)>0) "
				+" ))";
		return db.query(sql);
	}
	
	@SuppressWarnings("unchecked")
	public ReturnBean mapDataQuery(MapBean bean,UserInfo userinfo,String type) {
		StringBuffer addsql=new StringBuffer();
		StringBuffer addsql2 = new StringBuffer();
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Set<String> sns = new HashSet<String>();
		Set<String> brchNoS = new HashSet<String>();
		ReturnBean returnBean = new ReturnBean();
		try {
			if(!"".equals(bean.getSN())&&bean.getSN()!=null)
			{
				String temps[]=bean.getSN().split(",");
				for(int i=0;i<temps.length;i++)
				{
					if(!"".equals(temps[i]))
					{
						if(temps[i].indexOf("#")>-1){
							try{
								String[] s_arr = temps[i].split("#");
								sns.add(s_arr[0]);
								brchNoS.add(s_arr[1]);
							}
							catch(Exception ex){
								ex.printStackTrace();
							}
						}
					}
				}
			}
			if(sns!=null&&sns.size()!=0)
			{
				addsql.append(" and b.sn in("+StringUtils.join(sns.toArray(),",")+") ");
			}else{
				throw new Exception ("SNS的长度为0！");
			}
			if(brchNoS!=null&&brchNoS.size()!=0){
				addsql2.append(" and a.op_brch in ("+StringUtils.join(brchNoS.toArray(),",")+") ");
			}
			if(!"".equals(bean.getMarker_class())&&bean.getMarker_class()!=null)
			{
				addsql.append(" and b.marker_class ='"+bean.getMarker_class()+"' ");
			}
			
			StringBuffer info_sql=new StringBuffer();
			info_sql.append( "select a.sn,a.lat,a.lng,a.mc_markers,a.show_text,a.status_flag,a.marker_sn,a.table_name,a.radius,a.marker_type,a.zoom,a.op_brch from t_map_class_basics a  where   ");
			info_sql.append(" a.lat is not null and a.lng is not null "+addsql2+" ");
			info_sql.append(" and exists(select 1 FROM T_MAP_CLASS_INFO b WHERE a.table_name=b.class_table_name and b.CLASS_STATUS='1'"+addsql +")");
			//新版本修改后，是原来的精准查找功能无法使用，修改部分功能 add by zl 20150616
//			System.out.println("query_property:"+bean.getQuery_property());
			if(!"".equals(bean.getQuery_property())&&bean.getQuery_property()!=null)
			{
				if(sns.size()==1){
					
					String sa = bean.getQuery_property();
					sa = sa.replaceAll("@@@@@@", "'").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
					System.out.println("sa:"+sa);
					/*if(bean.getQuery_property().contains("=")){
						sa = bean.getQuery_property().split("=")[0]+"='"+bean.getQuery_property().split("=")[1].replaceAll("'", "").trim()+"'";
					}else if(bean.getQuery_property().contains("LIKE")){
						sa = bean.getQuery_property().split("LIKE")[0]+" LIKE '"+bean.getQuery_property().split("LIKE")[1].replaceAll("'", "").trim()+"'";
					}else if(bean.getQuery_property().contains("&lt;&gt;")){
						sa = bean.getQuery_property().split("&lt;&gt;")[0]+" <> '"+bean.getQuery_property().split("&lt;&gt;")[1].replaceAll("'", "").trim()+"'";
					}else if(bean.getQuery_property().contains("NOT LIKE")){
						sa = bean.getQuery_property().split("NOT LIKE")[0]+" NOT LIKE '"+bean.getQuery_property().split("NOT LIKE")[1].replaceAll("'", "").trim()+"'";
					}*/
					
					
					info_sql.append(" and a.marker_sn in (SELECT sn FROM T_MAP_CLASS_MC"+StringUtils.join(sns.toArray(),",")+"  WHERE  "+sa+")");
				}else{
					throw new Exception ("详细查询传递的参数SN有误！");
				}
			}
			if(bean.getQuery_string()!=null&&!"".equals(bean.getQuery_string())&&(bean.getQuery_property()==null||"".equals(bean.getQuery_property()))){
				info_sql.append(" and a.show_text like '%"+bean.getQuery_string()+"%' ");
			}
			//新版本修改后，是原来的精准查找功能无法使用，修改部分功能 add by zl 20150616
			System.out.println("地图查询SQL:"+info_sql.toString());
			List<Map<String,Object>> datalist = db.query(info_sql.toString());
			if(datalist.size()>50)
			{
				List<Map<String,Object>> subdatalist1=datalist.subList(0, (datalist.size()-datalist.size()%2)/2+datalist.size()%2);
				List<Map<String,Object>> subdatalist2=datalist.subList( (datalist.size()-datalist.size()%2)/2+datalist.size()%2+1, datalist.size()-1);
				List<Map<String,Object>> data1 = new LinkedList<Map<String,Object>>();
				List<Map<String,Object>> data2 = new LinkedList<Map<String,Object>>();
				BoundListThread thread1=new BoundListThread(data1,subdatalist1,new BigDecimal(bean.getStartx()) ,new BigDecimal(bean.getStarty()),new BigDecimal(bean.getEndx()),new BigDecimal(bean.getEndy()));
				BoundListThread thread2=new BoundListThread(data2,subdatalist2,new BigDecimal(bean.getStartx()) ,new BigDecimal(bean.getStarty()),new BigDecimal(bean.getEndx()),new BigDecimal(bean.getEndy()));
				Thread t1=new Thread(thread1);
				Thread t2=new Thread(thread2);
				t1.start();
				t2.start();
				t1.join();
				t2.join();
			
				List<Map<String,Object>> data = new LinkedList<Map<String,Object>>();
				data.addAll(data1);
				data.addAll(data2);
				returnBean.setIf_success(true);
				returnBean.setList_map(data);
			}
			else
			{
				List<Map<String,Object>> data=getBoundsList(datalist,new BigDecimal(bean.getStartx()) ,new BigDecimal(bean.getStarty()),new BigDecimal(bean.getEndx()),new BigDecimal(bean.getEndy()));
				returnBean.setIf_success(true);
				returnBean.setList_map(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		return returnBean;
	}
	/**
	 * 标注查询界面，点击一个标注时触发的信息拉去函数
	 * @param bean
	 * @param userinfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ReturnBean queryOneMarkerInfo(MapBean bean,UserInfo userinfo)  {  
	 
		ReturnBean returnBean = new ReturnBean();
		String sql = "";
//		String querySql = "";
//		String tmpSql = "";
//		Map<String,Object> dataMaps=new HashMap<String,Object>();
//		Map<String,Object> colMaps=new HashMap<String,Object>();
//		Map<String,Object> typeMaps=new HashMap<String,Object>();
//		Map<String,Object> postMaps=new HashMap<String,Object>();
 		try{	
			sql="SELECT * from  " +bean.getHtml_table_name()+ " WHERE  sn='"+bean.getSN()+"'";
		 	Map<String,Object> dataMap = db.queryOneLine(sql);
//		 	sql="SELECT brch_no from  t_map_class_info WHERE  CLASS_TABLE_NAME='"+bean.getHtml_table_name()+"'";
		 	sql = "select op_brch as brch_no from t_map_class_basics where table_name='"+bean.getHtml_table_name()+"' and marker_sn='"+bean.getSN()+"'";
		 	String create_brch_no =db.queryString(sql); 
 			sql="select class_tablename table_name,class_columnname column_name ,class_columnsize "+
				"data_length,class_nullable nullable,class_columntype data_type,class_comments comments "+
				" from T_MAP_CLASS_STATUS where  CLASS_TABLENAME='"+bean.getHtml_table_name()+"' and CLASS_COLUMNSTATUS='1' order by sn asc";
				
 			List<Map<String,Object>> colList =new ArrayList<Map<String,Object>> ();;
				
 			colList = db.query(sql);

	
			returnBean.setIf_success(true);
			returnBean.setDataMaps(dataMap);//数据
			returnBean.setColLists(colList);//列
			returnBean.setCreateBrch(create_brch_no);

		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
 
		return returnBean;
	}
	
	public static List<Map<String,Object>>  getBoundsList(List<Map<String,Object>> datalist,BigDecimal startx ,BigDecimal starty,BigDecimal endx,BigDecimal endy)
	{
		List<Map<String,Object>> list=new LinkedList<Map<String,Object>>();
		Map<String,Object> tempMap=new HashMap<String,Object>();
		String latArray[];
		String lngArray[];
 
		
		for(int i=0;i<datalist.size();i++)
		{
			tempMap=datalist.get(i);
			latArray=tempMap.get("lat").toString().split("#");
			lngArray=tempMap.get("lng").toString().split("#");
			
			for(int j=0;j<latArray.length;j++)
			{
				if((startx.compareTo(new BigDecimal(latArray[j].replace(" ", "")))<0 && endx.compareTo(new BigDecimal(latArray[j].replace(" ", "")))>0) && (starty.compareTo(new BigDecimal(lngArray[j].replace(" ", "")))<0 && endy.compareTo(new BigDecimal(lngArray[j].replace(" ", "")))>0)){
					//在范围内
					list.add(tempMap);
					break;
				}
			}
		}
		return list;
	}
	/*
	 * 修改与复制 点、线、面
	 */
	public ReturnBean CopyAndUpdate(MapBean bean,String filename,UserInfo userinfo) {
		if(bean.getQuery_string().equals(""))
		{
			return null;
		}
		ReturnBean returnBean = new ReturnBean();
		String params[]=bean.getQuery_string().split("\\|");
//		String sn=bean.getSN();
	
		if(params[0].equals("copy"))// 复制
		{
			try {
				String[] ss = params[3].split(",");
				String as = "";
				for(int i=0;i<ss.length;i++){
					if(i!=0){
						String s = ss[i];
						if(s!=null&&!"".equals(s)){
							as += ",'"+s.replaceAll("'", "")+"'";
						}else{
							as += ",''";
						}
					}
				}
				params[3] = as;
				
				if(!"".equals(filename))
				{
					params[2]=params[2]+","+bean.getQuery_operator();
					params[3]=params[3]+",'"+filename+"'";
				}
				String cols[]=params[2].split(",");
				String show_text="";
			 
				for(int i=0;i<cols.length;i++)
				{
					if("MC_FIELD1".equals(cols[i].toUpperCase()))
					{
						show_text=params[3].split(",")[i];
					}
				}
				String sql3 = "";
				String sql="select SEQ_"+params[1]+".nextval from dual";
				int new_sn=db.queryInt(sql);
				
				sql3 = "insert into "+params[1]+"(SN"+params[2]+") select "+new_sn+params[3]+" from "+params[1]+ " where sn='"+bean.getSN()+"'";
				System.out.println(sql3);
				
				int ret = db.insert(sql3);
				sql="select SEQ_T_MAP_BASIC.nextval from dual";
				int basic_sn=db.queryInt(sql);
				sql3 = "insert into t_map_class_basics(SN,LAT,LNG,MC_MARKERS,SHOW_TEXT,STATUS_FLAG,MARKER_SN,TABLE_NAME,RADIUS,MARKER_TYPE,ZOOM,op_brch) select '"+basic_sn+"',LAT,LNG,MC_MARKERS,"+show_text+",STATUS_FLAG,"+new_sn+",TABLE_NAME,RADIUS,MARKER_TYPE,ZOOM,'"+userinfo.getBrch_no()+"'  from t_map_class_basics where marker_sn="+bean.getSN()+" and table_name='"+params[1].toUpperCase()+"'";
				
				int ret1 = db.insert(sql3);
				if(ret==1&&ret1==1){
					returnBean.setIf_success(true);
					returnBean.setMsg("信息复制成功！");
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
		else if(params[0].equals("update"))//更新
		{
			String sql = "";
			
			System.out.println(params[3]);
			String[] ss = params[3].split(",");
			String as = "";
			for(int i=0;i<ss.length;i++){
				if(i!=0){
					String s = ss[i];
					if(s!=null&&!"".equals(s)){
						as += ","+s.split("=")[0]+"='"+s.split("=")[1].replaceAll("'", "")+"'";
					}
				}
			}
			params[3] = as;
			
			if(!"".equals(filename))
			{ 
				params[3]=params[3]+","+bean.getQuery_operator()+"='"+filename+"'";
			}
			String cols[]=params[3].split(",");
			String show_text="";
			for(int i=0;i<cols.length;i++)
			{
				if(cols[i].toUpperCase().indexOf("MC_FIELD1=")!=-1)
				{
					show_text=","+cols[i].replace("MC_FIELD1", "SHOW_TEXT");
				}
			}
			sql = "update "+params[1]+" set  SN=sn "+params[3]+" where  sn='"+bean.getSN()+"'";
			try {
				 
				int updateNum = db.update(sql);
				sql = "update t_map_class_basics set  SN=sn "+show_text+" where  marker_sn='"+bean.getSN()+"' and table_name='"+params[1].toUpperCase()+"'";
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
	
	/*
	 * 查询字段
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> toQueryColumn(MapBean bean) {
		String sql="select CLASS_TABLE_NAME from T_MAP_CLASS_INFO where sn='"+bean.getSN()+"'";
		List<Map> resultMap =new ArrayList<Map>();
		try {
			String table_name = db.queryString(sql);
		
			sql="select CLASS_COLUMNNAME,CLASS_COMMENTS,CLASS_COLUMNTYPE from T_MAP_CLASS_STATUS where CLASS_TABLENAME='"+table_name+"' and CLASS_COLUMNTYPE<>'4' and CLASS_COLUMNSTATUS='1' and column_type='2'";
			resultMap =db.query(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return resultMap;
	}
	
}
