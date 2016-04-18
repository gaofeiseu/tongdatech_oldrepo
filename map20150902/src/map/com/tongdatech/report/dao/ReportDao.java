package com.tongdatech.report.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.tongdatech.map.util.AntZip;
import com.tongdatech.map.util.ExcelWriteBean;
import com.tongdatech.map.util.ExcelWriter;
import com.tongdatech.report.bean.ReportBean;
import com.tongdatech.sys.base.BaseDao;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.util.excel.ExcelBean;
import com.tongdatech.sys.util.excel.ExcelForReport;

public class ReportDao extends BaseDao{
	private static final Logger log = Logger.getLogger(ReportDao.class);
	
	@SuppressWarnings("unchecked")
	public AjaxMsg query_table_1(ReportBean bean) {
		AjaxMsg am = new AjaxMsg();
		try{
			String sql = "";
//			sql = "select max(sn) as sn from t_map_report_param";
			sql = "select distinct(sn) as sn from t_map_report_param";
			List<Map<String,Object>> list = db.query(sql);
			if(list.size()!=0){
				int[] i_arr = new int[list.size()];
				for(Map<String,Object> m : list){
					i_arr[list.indexOf(m)] = Integer.parseInt(m.get("sn").toString());
				}
				Arrays.sort(i_arr);
				String in_sql = "";
				for(int i : i_arr){
					in_sql += ",column"+i;
				}
				if(!"".equals(in_sql)){
					sql = "select sn,type,to_char(data_time,'yyyy.mm.dd') as time,brch_no,user_num,brch_num"+in_sql+" from t_map_day_report where to_char(data_time,'yyyy.mm.dd')=to_char(sysdate,'yyyy.mm.dd')";
					List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
					list_map = db.query(sql);
					am.setSuccess(true);
					am.setMsg("查询成功！");
					am.setBackParam(list_map);
				}else{
					throw new Exception ("t_map_report_param中的参数有问题！sql:"+sql);
				}
			}else{
				throw new Exception ("t_map_report_param查询SN失败！sql:"+sql);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}
	
	public AjaxMsg run_rz(ReportBean bean) {
		AjaxMsg returnAM = new AjaxMsg();
		try{
			AjaxMsg am1 = getData();
			AjaxMsg am2 = getData2();
			returnAM.setSuccess(am1.isSuccess()&&am2.isSuccess());
			returnAM.setMsg(am1.getMsg()+"\n"+am2.getMsg());
		}
		catch(Exception ex){
			returnAM.setSuccess(false);
			returnAM.setMsg(ex.getMessage());
		}
		return returnAM;
	}
	
	@SuppressWarnings("unchecked")
	private AjaxMsg getData2(){
		AjaxMsg am = new AjaxMsg();
		try{
			String sql = "";
			sql = "delete from t_map_day_report2 where date_time=to_char(sysdate,'yyyy.mm.dd')";
			db.delete(sql);//在本次汇总前，先删除当天前一次的汇总数据
			sql = "select b.brch_name,a.* from (with tab as( "
					+" select a.brch_no,nvl(login_num,0) as login_num from  "
					+" (select a.brch_no from t_sys_brch a where a.brch_flag=1)a, "
					+" (select count(1) as login_num,c.brch_no from t_sys_log_login a, t_sys_brch c where a.brch_no=c.brch_no group by c.brch_no) b "
					+" where a.brch_no=b.brch_no(+)    "
					+" ), "
					+" tab2 as( "
					+" select a.brch_no,nvl(check_in_num,0) as check_in_num from  "
					+" (select a.brch_no from t_sys_brch a where a.brch_flag=1)a, "
					+" (select count(1) as check_in_num,c.brch_no from a_wap_check_in_info a,t_sys_user b,t_sys_brch c where a.login_id=b.user_id and b.brch_no=c.brch_no group by c.brch_no) b "
					+" where a.brch_no=b.brch_no(+)    "
					+" ), "
					+" tab3 as( "
					+" select a.brch_no,nvl(upload_num,0) as upload_num from  "
					+" (select a.brch_no from t_sys_brch a where a.brch_flag=1)a, "
					+" (select count(1) as upload_num,c.brch_no from a_file_upload_info a,t_sys_user b,t_sys_brch c where a.UPLOAD_USER_ID=b.user_id and b.brch_no=c.brch_no group by c.brch_no) b "
					+" where a.brch_no=b.brch_no(+)    "
					+" ), "
					+" brch as (select a.brch_no from t_sys_brch a where a.brch_flag=1 "
					+" ), "
					+" brch_path as ( "
					+" select connect_by_root(brch_no) as root_no,brch_no,level lvl from (select * from t_sys_brch where brch_flag=1)  "
					+" start with brch_no in (select brch_no from t_sys_brch where brch_flag=1) connect by prior brch_no=brch_up "
					+" ) "
					+" select to_char(sum(e.upload_num)) as upload_num,to_char(sum(a.login_num)) as login_num,to_char(sum(d.check_in_num)) as check_in_num,b.root_no " 
					+" from tab a,brch_path b,brch c,tab2 d,tab3 e "
					+" where a.brch_no=b.BRCH_NO and b.root_no=c.brch_no and a.brch_no=d.brch_no and a.brch_no=e.brch_no "
					+" GROUP BY b.root_no order by b.root_no) a,t_sys_brch b where a.root_no=b.brch_no and b.brch_no!='113080' order by a.root_no";
			log.info("getData2-->db.query(sql)-->"+sql);
			List<Map<String,Object>> list_map = db.query(sql);
			
			if(list_map.size()!=0){
				db.connect();
				db.startTransaction();
				try{
					for(Map<String,Object> m : list_map){
						sql = "insert into t_map_day_report2(sn,brch_no,brch_name,upload_num,login_num,check_in_num) values (SEQ_T_MAP_DAY_REPORT2.nextval,'"+m.get("root_no")+"','"+m.get("brch_name")+"','"+m.get("upload_num")+"','"+m.get("login_num")+"','"+m.get("check_in_num")+"')";
						db.delete(sql);
					}
					db.commit();
					am.setSuccess(true);
					am.setMsg("日终2完毕");
				}
				catch(Exception ex){
					ex.printStackTrace();
					db.rollback();
					throw ex;
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			}else{
				am.setSuccess(false);
				am.setMsg("日终2执行失败，查询的内容长度是0");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}
	
	/**
	 * 请注意：此功能中所用到的Oracle函数pivot只能在11G版本中使用，10G版本中是编译通不过的
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private AjaxMsg getData(){
		AjaxMsg am = new AjaxMsg();
		try{
			String sql = "";
			sql = "delete from a_wap_check_in_info where not regexp_like(login_id,'^[[:digit:]]+$')";//删除无效数据
			db.delete(sql);
			sql = "select sn,params_value,type from t_map_report_param  order by sn";
			List<Map<String,Object>> l_compare = db.query(sql);//已经存在的日报表的数据列对应关系
			System.out.println("size of t_map_report_param:"+l_compare.size());
			sql = "select sn,class_table_name from t_map_class_info where class_status='1' group by class_table_name,sn order by sn";
			List<Map<String,Object>> l_insert = db.query(sql);//本次数据查询中存在的所有列，用于与上面的结果集进行比对以及后续生成SQL语句
			System.out.println("size of different class_table_name in t_map_class_info:"+l_insert.size());
			//比对子类型列是否有新增现象，如果有新增现象，将在后续动作中进行参数表更新操作
			List<Map<String,Object>> list_wait_insert = new ArrayList<Map<String,Object>>();
			List<String> list_wait_insert_comparetable = new ArrayList<String>();
			for(Map<String,Object> m_insert : l_insert){
				String sn = "";
				for(Map<String,Object> m_compare : l_compare){
					if(m_compare.get("params_value").toString().equalsIgnoreCase(m_insert.get("class_table_name").toString())){
						Map<String,Object> t_m = new HashMap<String,Object>();
						sn = m_compare.get("sn").toString();
						t_m.put("column_sn", sn);
						t_m.put("class_table_name", m_compare.get("params_value"));
						t_m.put("class_type", m_compare.get("type"));
						System.out.println("==>"+t_m.get("class_table_name"));
						list_wait_insert.add(t_m);
						break;
					}
				}
				if("".equals(sn)){
					list_wait_insert_comparetable.add(m_insert.get("class_table_name").toString());
				}
			}
			//如果有参数表中没有的子类型，将进行参数表更新操作
			if(list_wait_insert_comparetable.size()!=0){
				db.connect();
				db.startTransaction();
				try{
					for(String class_table_name : list_wait_insert_comparetable){
						String sn = db.queryString("select SEQ_T_MAP_REPORT_PARAM.nextval from dual");
						String class_type = db.queryString("select marker_class from t_map_class_info where class_table_name='"+class_table_name+"'");
						sql = "insert into t_map_report_param (sn,type,params_value) values ("+sn+",'"+class_type+"','"+class_table_name.trim()+"')";
						if(db.insert(sql)==1){
							Map<String,Object> t_m = new HashMap<String,Object>();
							t_m.put("column_sn", sn);
							t_m.put("class_table_name", class_table_name);
							t_m.put("class_type", class_type);
							System.out.println("-->"+class_table_name);
							list_wait_insert.add(t_m);
						}else{
							throw new Exception ("insert error:sql:"+sql);
						}
					}
					db.commit();
				}
				catch(Exception ex){
					db.rollback();
					throw ex;
				}
				finally{
					db.endTransaction();
					db.disconnect();
				}
			}
			
			sql = "delete from t_map_day_report where to_char(data_time,'yyyy.mm.dd')=to_char(sysdate,'yyyy.mm.dd')";
			db.delete(sql);//在本次汇总前，先删除当天前一次的汇总数据
			System.out.println("delete today data complete");
			//insert user_num and brch_num
			sql = "with tab as( "
					+" select a.brch_no,nvl(user_num,0)user_num,1 all_brch from  "
					+" (select a.brch_no from t_sys_brch a where a.brch_flag=1)a, "
					+" (select count(distinct a.user_id)user_num,c.brch_no from t_sys_user a, t_sys_brch c where a.brch_no=c.brch_no group by c.brch_no ) b "
					+" where a.brch_no=b.brch_no(+)    "
					+" ), "
					+" brch as (select a.brch_no from t_sys_brch a where a.brch_flag=1 "
					+" ), "
					+" brch_path as ( "
					+" select connect_by_root(brch_no)root_no,brch_no,level lvl from (select * from t_sys_brch where brch_flag=1) start with brch_no  "
					+" in (select brch_no from t_sys_brch where brch_flag=1) connect by prior brch_no=brch_up "
					+" ) "
					+" select sum(all_brch) all_brch,sum(user_num) user_num,b.root_no from tab a,brch_path b,brch c "
					+" where a.brch_no=b.BRCH_NO and b.root_no=c.brch_no  "
					+" GROUP BY b.root_no order by b.root_no";
			List<Map<String,Object>> list_m_UsernumAndBrchnum = db.query(sql);
			System.out.println("size of user_num and brch_num:"+list_m_UsernumAndBrchnum.size());
			if(list_m_UsernumAndBrchnum.size()!=0){
				db.connect();
				db.startTransaction();
				try{
					for(Map<String,Object> m : list_m_UsernumAndBrchnum){
						sql = "insert into t_map_day_report (sn,type,brch_no,user_num,brch_num) values (SEQ_T_MAP_DAY_REPORT.nextval,'1','"+m.get("root_no").toString()+"',"+m.get("user_num")+","+m.get("all_brch")+")";
						if(db.insert(sql)==1){
							continue;
						}else{
							throw new Exception ("when insert t_map_day_report,error happens:sql:"+sql);
						}
					}
					db.commit();
					System.out.println("insert into t_map_day_report complete");
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
				throw new Exception ("user_num and brch_num insert error:sql:"+sql);
			}
			
			//insert marker with update by brch_no
			String in_sql = "";
			//System.out.println("list_wait_insert:"+list_wait_insert.toString());
			for(Map<String,Object> m : list_wait_insert){
				if(list_wait_insert.indexOf(m)==0){
					in_sql += "'"+m.get("class_table_name")+"'";
				}else{
					in_sql += ",'"+m.get("class_table_name")+"'";
				}
			}
			sql = "select * from  "
					+" (with tab as(select nvl(a.op_brch,'0') as brch_no,b.class_table_name,count(1) as num  "
					+" from t_map_class_basics a,t_map_class_info b where a.table_name=b.class_table_name group by b.class_table_name,a.op_brch "
					+" ), "
					+" brch as (select a.brch_no from t_sys_brch a where a.brch_flag=1 "
					+" ), "
					+" brch_path as ( "
					+" select connect_by_root(brch_no) root_no,brch_no,level lvl from (select * from t_sys_brch where brch_flag=1) start with brch_no  "
					+" in (select brch_no from t_sys_brch where brch_flag=1) connect by prior brch_no=brch_up "
					+" ) "
					+" select a.class_table_name,b.root_no as op_brch,sum(a.num) num from tab a,brch_path b,brch c "
					+" where a.brch_no=b.BRCH_NO and b.root_no=c.brch_no  "
					+" GROUP BY b.root_no,a.class_table_name)   "
					+" pivot (   "
					+"   sum(num) for class_table_name in ("+in_sql+")  " 
					+" ) order by 1";
			List<Map<String,Object>> list_m = db.query(sql);
			System.out.println("size of markers_num_list need to update:"+list_m.size());
//			list_wait_insert
//			t_m.put("column_sn", sn);
//			t_m.put("class_table_name", m_compare.get("params_value"));
//			t_m.put("class_type", m_compare.get("type"));
			if(list_m.size()!=0){
				db.connect();
				db.startTransaction();
				try{
					for(Map<String,Object> m : list_m){//结果集循环
						String c_sql = "";
						for(Map<String,Object> m2 : list_wait_insert){//子类型列名循环
							Object v_o = m.get("'"+m2.get("class_table_name").toString().toLowerCase()+"'");
							Object k_o = m2.get("column_sn");
							int v = 0;
							int k = 0;
							if(v_o!=null&&!"".equals(v_o)){
								try{
									v = (int)Double.parseDouble(v_o.toString());
								}
								catch(Exception ex){
									try{
										v = Integer.parseInt(v_o.toString());
									}
									catch(Exception ex2){
										
									}
								}
							}
							if(k_o!=null&&!"".equals(k_o)){
								try{
									k = (int)Double.parseDouble(k_o.toString());
								}
								catch(Exception ex){
									try{
										k = Integer.parseInt(k_o.toString());
									}
									catch(Exception ex2){
										
									}
								}
							}
							if(list_wait_insert.indexOf(m2)==0){
								c_sql += "column"+k+"="+v;
							}else{
								c_sql += ",column"+k+"="+v;
							}
						}
						sql = "update t_map_day_report set "+c_sql+" where brch_no='"+m.get("op_brch")+"' and to_char(data_time,'yyyy.mm.dd')=to_char(sysdate,'yyyy.mm.dd') ";
						if(db.update(sql)==1){
							
						}else{
							throw new Exception ("when update marker_num into t_map_day_report,error occured:sql:"+sql);
						}
					}
					db.commit();
					System.out.println("update data to t_map_day_report complete");
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
				throw new Exception ("when query marker_num for each brch,error occured:sql:"+sql);
			}
			am.setSuccess(true);
			am.setMsg("日终1完毕！");
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}
	
	@SuppressWarnings("unchecked")
	public AjaxMsg excelOne(ReportBean bean,UserInfo userinfo,int ExcelEdition,int calc_type) throws Exception {
		AjaxMsg am = new AjaxMsg();
		try{
			ExcelBean excelBean = new ExcelForReport(ExcelEdition);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			excelBean.setFliename("电子地图走访平台使用情况统计表"+sdf.format(new Date()));
			excelBean.setHeadtext("电子地图走访平台使用情况统计表");
			List<String> first_title_list = new ArrayList<String>();//第一行表头
			List<String> second_title_list = new ArrayList<String>();//第二行表头
			List<String> data_key_list = new ArrayList<String>();
			String sql = "";
			sql = "select a.sn as sn,a.type as type,a.params_value as value,b.class_name as class_name from t_map_report_param a,t_map_class_info b where a.params_value=b.class_table_name order by a.type,a.sn ";
			List<Map<String,Object>> list_param = db.query(sql);
			String a_sql = "";
			a_sql += "select a.brch_name as brch_name,a.brch_no as brch_no,to_char(b.brch_num) as brch_num,to_char(b.user_num) as user_num";
			first_title_list.add("统计项目");
			first_title_list.add("");
			second_title_list.add("地市");
			data_key_list.add("brch_name");
			second_title_list.add("网点机构号");
			data_key_list.add("brch_no");
			first_title_list.add("注册机构数量");
			second_title_list.add("累计结存");
			data_key_list.add("brch_num");
			first_title_list.add("注册人员数量");
			second_title_list.add("累计结存");
			data_key_list.add("user_num");
			
			boolean if_first_point = true;
			boolean if_second_point = true;
			for(Map<String,Object> m : list_param){
				if("1".equals(m.get("type"))||"3".equals(m.get("type"))){
					int i = (int)Double.parseDouble(m.get("sn").toString());
					a_sql += ",to_char(b.column"+i+") as column"+i;
					if("1".equals(m.get("type"))&&if_first_point){
						first_title_list.add("累计标注点数量");
						if_first_point = false;
					}else if("3".equals(m.get("type"))&&if_second_point){
						first_title_list.add("累计标注面数量");
						if_second_point = false;
					}else{
						first_title_list.add("");
					}
					second_title_list.add(""+m.get("class_name"));
					data_key_list.add("column"+i);
				}
			}
			if(calc_type==1){//all brch
				a_sql += " from t_sys_brch a,t_map_day_report b where a.brch_no=b.brch_no and b.brch_no in (select brch_no from t_sys_brch start with brch_no = '"+userinfo.getBrch_no()+"' connect by prior brch_no=brch_up) and to_char(b.data_time,'yyyy.mm.dd')='"+bean.getTime()+"' order by a.brch_mode,a.order_id";//order by b.sn//to_char(sysdate,'yyyy.mm.dd')
			}else if(calc_type==2){//only city level brch
				//a_sql += " from t_sys_brch a,t_map_day_report b where a.brch_no=b.brch_no and a.brch_mode='3' and b.brch_no in (select brch_no from t_sys_brch start with brch_no = '"+userinfo.getBrch_no()+"' connect by prior brch_no=brch_up) and to_char(b.data_time,'yyyy.mm.dd')='"+bean.getTime()+"' order by a.order_id";//order by b.sn//to_char(sysdate,'yyyy.mm.dd')
				a_sql += " from t_sys_brch a,t_map_day_report b where a.brch_no=b.brch_no and to_number(a.brch_mode)>=3 and b.brch_no in (select brch_no from t_sys_brch start with brch_no = '"+userinfo.getBrch_no()+"' connect by prior brch_no=brch_up) and to_char(b.data_time,'yyyy.mm.dd')='"+bean.getTime()+"' order by a.order_id";//order by b.sn//to_char(sysdate,'yyyy.mm.dd')
			}
			log.info("excel one sql:"+a_sql);
			List<Map<String,Object>> list_map = db.query(a_sql);
			excelBean.setToptext(first_title_list.toArray(new String[first_title_list.size()]));
			excelBean.setTitletext(second_title_list.toArray(new String[second_title_list.size()]));
			excelBean.setDatakey(data_key_list.toArray(new String[data_key_list.size()]));
			excelBean.setData(list_map);
			excelBean.create();
			
			String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
			String exceloutputpath = webrootPath+"/ExcelOutputSpace/";
			String exceluuidfilename = UUID.randomUUID().toString()+".xls";
			FileOutputStream fout = new FileOutputStream(exceloutputpath+exceluuidfilename);
			excelBean.getWb().write(fout);
			fout.close();
			
			boolean if_success3 = false;
			String zip_path = "";
			try {
				List<String> list_zipfile = new ArrayList<String>();
				list_zipfile.add(exceloutputpath+exceluuidfilename);
				String s1 = UUID.randomUUID().toString();
				String zip_name = exceloutputpath+s1+".zip";
				zip_path = "/ExcelOutputSpace/"+s1+".zip";
				AntZip.zipFile(list_zipfile, zip_name);
				if_success3 = true;
			} catch (Exception e) {
				e.printStackTrace();
				if_success3 = false;
			}
			if(if_success3){
				File file1 = new File(exceloutputpath+exceluuidfilename);
				if(file1.delete()){
					am.setSuccess(true);
					am.setMsg("获取文件成功！");
					am.setBackParam(zip_path);
				}
			}else{
				am.setSuccess(false);
				am.setMsg("文件上传且解析成功，但是打包失败！");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	@SuppressWarnings("unchecked")
	public AjaxMsg getexcel(ReportBean bean) {
		AjaxMsg am = new AjaxMsg();
		try{
			String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
			String exceloutputpath = webrootPath+"/ExcelOutputSpace/";
			String exceluuidfilename = UUID.randomUUID().toString()+".xls";
			ExcelWriteBean excelBean = new ExcelWriteBean();
			excelBean.setFilepath(exceloutputpath);
			excelBean.setExcelFilename(exceluuidfilename);
			excelBean.setSheetname("电子地图走访平台使用情况统计表");
			///////////////////////////////////////////////////
			List<String> list_title = new ArrayList<String>();
			List<String> list_colname = new ArrayList<String>();
			
			String sql = "";
			sql = "select a.sn as sn,a.type as type,a.params_value as value,b.class_name as class_name from t_map_report_param a,t_map_class_info b where a.params_value=b.class_table_name order by a.type,a.sn ";
			List<Map<String,Object>> list_param = db.query(sql);
			String a_sql = "";
			a_sql += "select a.brch_name as brch_name,a.brch_no as brch_no,to_char(b.brch_num) as brch_num,to_char(b.user_num) as user_num";
			list_title.add("地市");
			list_colname.add("brch_name");
			list_title.add("网点机构号");
			list_colname.add("brch_no");
//			list_title.add("统计机构数量_本期新增");
//			list_colname.add("brch_num");
			list_title.add("统计机构数量_累计结存");
			list_colname.add("brch_num");
//			list_title.add("注册人员数量_本期新增");
//			list_colname.add("user_num");
			list_title.add("注册人员数量_累计结存");
			list_colname.add("user_num");
//			//point--add
//			for(Map<String,Object> m : list_param){
//				int i = (int)Double.parseDouble(m.get("sn").toString());
//				if("1".equals(m.get("type"))){
//					a_sql += ",to_char(b.column"+i+") as column"+i;
//					list_title.add("本期新增点数量_"+m.get("class_name"));
//					list_colname.add("column"+i);
//				}
//			}
			//point--history
			for(Map<String,Object> m : list_param){
				int i = (int)Double.parseDouble(m.get("sn").toString());
				if("1".equals(m.get("type"))){
					list_title.add("累计标注点数量_"+m.get("class_name"));
					list_colname.add("column"+i);
				}
			}
//			//area--add
//			for(Map<String,Object> m : list_param){
//				int i = (int)Double.parseDouble(m.get("sn").toString());
//				if("3".equals(m.get("type"))){
//					a_sql += ",to_char(b.column"+i+") as column"+i;
//					list_title.add("本期新增面数量_"+m.get("class_name"));
//					list_colname.add("column"+i);
//				}
//			}
			//area--history
			for(Map<String,Object> m : list_param){
				int i = (int)Double.parseDouble(m.get("sn").toString());
				if("3".equals(m.get("type"))){
					list_title.add("累计标注面数量_"+m.get("class_name"));
					list_colname.add("column"+i);
				}
			}
			a_sql += " from t_sys_brch a,t_map_day_report b where a.brch_no=b.brch_no and to_char(b.data_time,'yyyy.mm.dd')=to_char(sysdate,'yyyy.mm.dd') order by b.sn";
			System.out.println("a_sql:"+a_sql);
			List<Map<String,Object>> list_map = db.query(a_sql);
			
			excelBean.setList_colname(list_colname);
			excelBean.setList_title(list_title);
			excelBean.setList_rows(list_map);
			
			boolean if_success1 = false;
			ExcelWriter writer = new ExcelWriter();
			if_success1 = writer.writeExcel2(excelBean);
			String zip_path = "";
			if(if_success1){
				boolean if_success3 = false;
				try {
					List<String> list_zipfile = new ArrayList<String>();
					list_zipfile.add(excelBean.getFilepath()+excelBean.getExcelFilename());
					String s1 = UUID.randomUUID().toString();
					String zip_name = exceloutputpath+s1+".zip";
					zip_path = "/ExcelOutputSpace/"+s1+".zip";
					AntZip.zipFile(list_zipfile, zip_name);
					if_success3 = true;
				} catch (Exception e) {
					e.printStackTrace();
					if_success3 = false;
				}
				if(if_success3){
					File file1 = new File(excelBean.getFilepath()+excelBean.getExcelFilename());
					if(file1.delete()){
						am.setSuccess(true);
						am.setMsg("获取文件成功！");
						am.setBackParam(zip_path);
					}
				}else{
					am.setSuccess(false);
					am.setMsg("文件上传且解析成功，但是打包失败！");
				}
			}else{
				am.setSuccess(false);
				am.setMsg("excel文件生成失败！");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}
	/**
	 * 
	 * @param bean
	 * @param userinfo
	 * @param excelEdition
	 * @param calc_type		1-全部机构	2-只市级机构
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public AjaxMsg excelTwo(ReportBean bean, UserInfo userinfo,int excelEdition,int calc_type) throws Exception {
		AjaxMsg am = new AjaxMsg();
		try{
			ExcelBean excelBean = new ExcelForReport(excelEdition);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String time_str = sdf.format(new Date());
			excelBean.setFliename("电子地图走访平台走访情况统计表"+time_str);
			excelBean.setHeadtext("电子地图走访平台走访情况统计表");
			List<String> first_title_list = new ArrayList<String>();//第一行表头
			List<String> second_title_list = new ArrayList<String>();//第二行表头
			List<String> data_key_list = new ArrayList<String>();
			String sql = "";
			
			first_title_list.add("统计周期");
			second_title_list.add("地市");
			data_key_list.add("brch_name");
			first_title_list.add(time_str);
			second_title_list.add("网点机构号");
			data_key_list.add("root_no");
			first_title_list.add("");
			second_title_list.add("累计登录次数");
			data_key_list.add("login_num");
			first_title_list.add("");
			second_title_list.add("累计签到次数");
			data_key_list.add("check_in_num");
			first_title_list.add("");
			second_title_list.add("走访计划累计上传次数");
			data_key_list.add("upload_num");
			if(calc_type==1){//all brch
				sql = "select a.* from (select brch_name,brch_no as root_no,login_num,check_in_num,upload_num from t_map_day_report2 where brch_no in (select brch_no from t_sys_brch start with brch_no = '"+userinfo.getBrch_no()+"' connect by prior brch_no=brch_up) and date_time='"+bean.getTime()+"') a,t_sys_brch b where a.root_no=b.brch_no order by b.brch_mode,b.order_id ";//to_char(sysdate,'yyyy.mm.dd')
			}else if(calc_type==2){//only city level brch
				sql = "select a.* from (select brch_name,brch_no as root_no,login_num,check_in_num,upload_num from t_map_day_report2 where brch_no in (select brch_no from t_sys_brch start with brch_no = '"+userinfo.getBrch_no()+"' connect by prior brch_no=brch_up) and date_time='"+bean.getTime()+"') a,t_sys_brch b where a.root_no=b.brch_no and b.brch_mode='3' order by b.order_id ";//to_char(sysdate,'yyyy.mm.dd')
			}
			log.info("excel two sql:"+sql);
			List<Map<String,Object>> list_map = db.query(sql);
			excelBean.setToptext(first_title_list.toArray(new String[first_title_list.size()]));
			excelBean.setTitletext(second_title_list.toArray(new String[second_title_list.size()]));
			excelBean.setDatakey(data_key_list.toArray(new String[data_key_list.size()]));
			excelBean.setData(list_map);
			excelBean.create();
			
			String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
			String exceloutputpath = webrootPath+"/ExcelOutputSpace/";
			String exceluuidfilename = UUID.randomUUID().toString()+".xls";
			FileOutputStream fout = new FileOutputStream(exceloutputpath+exceluuidfilename);
			excelBean.getWb().write(fout);
			fout.close();
			
			boolean if_success3 = false;
			String zip_path = "";
			try {
				List<String> list_zipfile = new ArrayList<String>();
				list_zipfile.add(exceloutputpath+exceluuidfilename);
				String s1 = UUID.randomUUID().toString();
				String zip_name = exceloutputpath+s1+".zip";
				zip_path = "/ExcelOutputSpace/"+s1+".zip";
				AntZip.zipFile(list_zipfile, zip_name);
				if_success3 = true;
			} catch (Exception e) {
				e.printStackTrace();
				if_success3 = false;
			}
			if(if_success3){
				File file1 = new File(exceloutputpath+exceluuidfilename);
				if(file1.delete()){
					am.setSuccess(true);
					am.setMsg("获取文件成功！");
					am.setBackParam(zip_path);
				}
			}else{
				am.setSuccess(false);
				am.setMsg("文件上传且解析成功，但是打包失败！");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}
	
	
	@SuppressWarnings("unchecked")
	public AjaxMsg getexcel2(ReportBean bean) {
		AjaxMsg am = new AjaxMsg();
		try{
			String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
			String exceloutputpath = webrootPath+"/ExcelOutputSpace/";
			String exceluuidfilename = UUID.randomUUID().toString()+".xls";
			ExcelWriteBean excelBean = new ExcelWriteBean();
			excelBean.setFilepath(exceloutputpath);
			excelBean.setExcelFilename(exceluuidfilename);
			excelBean.setSheetname("电子地图走访平台走访情况统计表");
			///////////////////////////////////////////////////
			List<String> list_title = new ArrayList<String>();
			List<String> list_colname = new ArrayList<String>();
			
			String sql = "";
			
			list_title.add("地市");
			list_colname.add("brch_name");
			list_title.add("网点机构号");
			list_colname.add("brch_no");
//			list_title.add("本期登陆次数");
//			list_colname.add("login_num");
			list_title.add("累计登录次数");
			list_colname.add("login_num");
//			list_title.add("本期签到次数");
//			list_colname.add("check_in_num");
			list_title.add("累计签到次数");
			list_colname.add("check_in_num");
//			list_title.add("走访计划本期上传次数");
//			list_colname.add("upload_num");
			list_title.add("走访计划累计上传次数");
			list_colname.add("upload_num");
			
			sql = "select b.brch_name,a.* from (with tab as( "
					+" select a.brch_no,nvl(login_num,0) as login_num from  "
					+" (select a.brch_no from t_sys_brch a where a.brch_flag=1)a, "
					+" (select count(1) as login_num,c.brch_no from t_sys_log_login a, t_sys_brch c where a.brch_no=c.brch_no group by c.brch_no) b "
					+" where a.brch_no=b.brch_no(+)    "
					+" ), "
					+" tab2 as( "
					+" select a.brch_no,nvl(check_in_num,0) as check_in_num from  "
					+" (select a.brch_no from t_sys_brch a where a.brch_flag=1)a, "
					+" (select count(1) as check_in_num,c.brch_no from a_wap_check_in_info a,t_sys_user b,t_sys_brch c where a.login_id=b.user_id and b.brch_no=c.brch_no group by c.brch_no) b "
					+" where a.brch_no=b.brch_no(+)    "
					+" ), "
					+" tab3 as( "
					+" select a.brch_no,nvl(upload_num,0) as upload_num from  "
					+" (select a.brch_no from t_sys_brch a where a.brch_flag=1)a, "
					+" (select count(1) as upload_num,c.brch_no from a_file_upload_info a,t_sys_user b,t_sys_brch c where a.UPLOAD_USER_ID=b.user_id and b.brch_no=c.brch_no group by c.brch_no) b "
					+" where a.brch_no=b.brch_no(+)    "
					+" ), "
					+" brch as (select a.brch_no from t_sys_brch a where a.brch_flag=1 "
					+" ), "
					+" brch_path as ( "
					+" select connect_by_root(brch_no) as root_no,brch_no,level lvl from (select * from t_sys_brch where brch_flag=1)  "
					+" start with brch_no in (select brch_no from t_sys_brch where brch_flag=1) connect by prior brch_no=brch_up "
					+" ) "
					+" select to_char(sum(e.upload_num)) as upload_num,to_char(sum(a.login_num)) as login_num,to_char(sum(d.check_in_num)) as check_in_num,b.root_no as brch_no from tab a,brch_path b,brch c,tab2 d,tab3 e "
					+" where a.brch_no=b.BRCH_NO and b.root_no=c.brch_no and a.brch_no=d.brch_no and a.brch_no=e.brch_no "
					+" GROUP BY b.root_no order by b.root_no) a,t_sys_brch b where a.root_no=b.brch_no order by a.root_no";
			
			List<Map<String,Object>> list_map = db.query(sql);
			
			excelBean.setList_colname(list_colname);
			excelBean.setList_title(list_title);
			excelBean.setList_rows(list_map);
			
			boolean if_success1 = false;
			ExcelWriter writer = new ExcelWriter();
			if_success1 = writer.writeExcel2(excelBean);
			String zip_path = "";
			if(if_success1){
				boolean if_success3 = false;
				try {
					List<String> list_zipfile = new ArrayList<String>();
					list_zipfile.add(excelBean.getFilepath()+excelBean.getExcelFilename());
					String s1 = UUID.randomUUID().toString();
					String zip_name = exceloutputpath+s1+".zip";
					zip_path = "/ExcelOutputSpace/"+s1+".zip";
					AntZip.zipFile(list_zipfile, zip_name);
					if_success3 = true;
				} catch (Exception e) {
					e.printStackTrace();
					if_success3 = false;
				}
				if(if_success3){
					File file1 = new File(excelBean.getFilepath()+excelBean.getExcelFilename());
					if(file1.delete()){
						am.setSuccess(true);
						am.setMsg("获取文件成功！");
						am.setBackParam(zip_path);
					}
				}else{
					am.setSuccess(false);
					am.setMsg("文件上传且解析成功，但是打包失败！");
				}
			}else{
				am.setSuccess(false);
				am.setMsg("excel文件生成失败！");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			am.setSuccess(false);
			am.setMsg(ex.getMessage());
		}
		return am;
	}

	public AjaxMsg doExcelOne(ReportBean bean,UserInfo userinfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		int total = 0;
		try{
			String sql = "";
			sql = "select count(1) from t_map_day_report where brch_no in (select brch_no from t_sys_brch start with brch_no = '"+userinfo.getBrch_no()+"' connect by prior brch_no=brch_up) and to_char(data_time,'yyyy.mm.dd')='"+bean.getTime()+"'";
			log.info("doExcelOne SQL:"+sql);
			total = db.count(sql);
		}
		catch(Exception ex){
			total = 0;
		}
		am.setBackParam(total);
		return am;
	}

	public AjaxMsg doExcelTwo(ReportBean bean,UserInfo userinfo) throws Exception {
		AjaxMsg am = new AjaxMsg();
		int total = 0;
		try{
			String sql = "";
			sql = "select count(1) from t_map_day_report2 where brch_no in (select brch_no from t_sys_brch start with brch_no = '"+userinfo.getBrch_no()+"' connect by prior brch_no=brch_up) and date_time='"+bean.getTime()+"'";
			log.info("doExcelTwo SQL:"+sql);
			total = db.count(sql);
		}
		catch(Exception ex){
			total = 0;
		}
		am.setBackParam(total);
		return am;
	}

	public void report_log(String result_type, String log_str) {
		try{
			String sql = "insert into t_map_report_log (sn,result_type,log_str) values (SEQ_T_MAP_REPORT_LOG.nextval,'"+result_type+"','"+log_str+"')";
			db.insert(sql);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
	
}
