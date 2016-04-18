package com.tongdatech.business.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;

import com.tongdatech.business.bean.DataInputBean;
import com.tongdatech.business.bean.ReturnBean;

import com.tongdatech.map.util.ExcelBean;
import com.tongdatech.map.util.ExcelReader;
import com.tongdatech.map.util.FileUtils;
import com.tongdatech.sys.base.BaseDao;

public class DataInputDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public ReturnBean getChildClassCombo(DataInputBean bean) {
		ReturnBean returnBean = new ReturnBean();
		String sql = "select to_char(sn) as value,class_name as text from t_data_class_info where marker_class='"
			+bean.getComboselect_main_class()+"' and post_class='"
			+bean.getComboselect_user_type()+"' and class_status='1' ";
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		try {
			list_map = db.query(sql);
			returnBean.setList_map(list_map);
			returnBean.setIf_success(true);
			returnBean.setMsg("成功获取子类型列表信息！");
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("获取子类型列表失败！数据库异常！");
		}
		return returnBean;
	}

	@SuppressWarnings("unchecked")
	public ReturnBean getTemplat(DataInputBean bean) {
		ReturnBean returnBean = new ReturnBean();
		@SuppressWarnings("unused")
		String bigtype = bean.getTemplat_mainclass_sn();//大类型
		@SuppressWarnings("unused")
		String depttype = bean.getTemplat_usertype_sn();//部门类型
		String class_sn = bean.getTemplat_childclass_sn();//子类型SN
		String class_name = bean.getTemplat_childclass_name();//子类型名称
		String showFileName = "{"+class_name+"}";//用于前台展示文件下载链接时候用
		String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
//		System.out.println("webrootpath-------------->"+webrootPath);
		String excel_folderpath = webrootPath+"/ExcelSpace";//excel文件的保存路径
		String excel_filename = UUID.randomUUID().toString()+".xls";
		String realFileUrl = "/ExcelSpace/"+excel_filename;//前台超链接的真实URL
		String excel_url = excel_folderpath+"/"+excel_filename;//excel模板生成后的输出路径
		String sheetname = "{"+class_name+"}模板";
		
		String sql = "";
		sql = "select b.class_comments as class_comments,b.class_columntype as class_columntype," +
				"b.class_nullable as class_nullable,b.class_columnsize as class_columnsize" +
				" from t_data_class_info a,t_data_class_status b" +
				" where a.sn="+class_sn+" and a.sn=b.class_sn and a.class_status='1' and b.COLUMN_TYPE='2' and b.class_columnstatus='1' order by b.sn ";
		System.out.println("sql----------------------->"+sql);
		List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
		boolean if_success1 = false;
		try {
			list_map = db.query(sql);
			if(list_map.size()==0){
				returnBean.setIf_success(false);
				returnBean.setMsg("该类型没有任何属性！");
				if_success1=false;
			}else{
				if_success1=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
			if_success1=false;
		}
		if(if_success1){
			List<String> list_columnname = new ArrayList<String>();
			List<String> list_columncomments = new ArrayList<String>();
			for(int i=0;i<list_map.size();i++){
				list_columnname.add(String.valueOf(list_map.get(i).get("class_comments")));
				String typeStr = "";
				if("1".equals(list_map.get(i).get("class_columntype"))){
					typeStr += "请输入字符串，";
				}else if("2".equals(list_map.get(i).get("class_columntype"))){
					typeStr += "请输入数字，";
				}else if("3".equals(list_map.get(i).get("class_columntype"))){
					typeStr += "请输入数字（百分率类型直接输入数字部分，百分号不需要输入），小数点后保留3位，";
				}else if("4".equals(list_map.get(i).get("class_columntype"))){
					typeStr += "请输入字符串，长度不要超过50个字符，";
				}
				if("1".equals(list_map.get(i).get("class_columntype"))){
					typeStr += "长度不要超过"+String.valueOf(list_map.get(i).get("class_columnsize"))+"个字符串，";
				}
				if("1".equals(list_map.get(i).get("class_nullable"))){
					typeStr += "可以为空";
				}else if("2".equals(list_map.get(i).get("class_nullable"))){
					typeStr += "不可以为空";
				}
				list_columncomments.add(typeStr);
			}
			list_columnname.add("纬度");
			list_columncomments.add("例如：32.1234567");
			list_columnname.add("经度");
			list_columncomments.add("例如：116.354004");
			boolean if_success = FileUtils.createExcel(excel_url, sheetname,list_columnname,list_columncomments);
			if(if_success){
				returnBean.setIf_success(true);
				returnBean.setMsg("模板生成成功！");
				List<String> returnList = new ArrayList<String>();
				returnList.add(showFileName);//将fakename添加进返回List
				returnList.add(realFileUrl);//将realname添加进返回List
				returnBean.setReturnList(returnList);
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("模板生成失败！");
			}
		}
		return returnBean;
	}

	public ReturnBean uploadTemplat(File[] upload,DataInputBean bean) {
		ReturnBean returnBean = new ReturnBean();
		String u_filename = "";
		String u_childclass_sn = "";
		u_filename = bean.getUpload_excel_name();//上传的文件的文件名
		u_childclass_sn = bean.getUpload_childclass_sn();//上传的文件对应的子类型SN
		
		String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
		String filefolderPath = webrootPath+"/ExcelSpace/ExcelUpload"+FileUtils.getFileSeparator();
		String uuidFilename = UUID.randomUUID().toString()+"."+u_filename.split("\\.")[u_filename.split("\\.").length-1];
		String filepath = filefolderPath+uuidFilename;
		
		//------------------------------开始进行文件上传	ED-------------------------------------
		try {
			if(upload[0].renameTo(new File(filepath))){
				//--------------------------------取出上传的Excel文件中的内容-------ST------------------------
				ExcelBean excelBean = new ExcelBean();
				excelBean.setFilepath(filepath);
				ExcelReader excelReader = new ExcelReader(excelBean);
		        excelReader.readExcelTitle();
		        excelReader.readExcelContent();
		        excelBean = excelReader.getExcelBean();
		        
		        List<String> list_title = new ArrayList<String>();//Excel文件中的列名
		        List<List<String>> list_rows = new ArrayList<List<String>>();//Excel文件中的每行中的每列数据集合
		        list_title = excelBean.getList_title();
		        list_rows = excelBean.getList_rows();
		        if(list_rows.size()==0){
		        	returnBean.setIf_success(false);
		        	returnBean.setMsg("上传的Excel文件中没有有效内容，请确认后重新进行操作！");
		        }else{
		        	//取出了Excel文件中的内容，且该内容不为空！
		        	//--------------------------------取出Excel模板对应的校验条件----ST---------------------------
					
			        try {
						String sql = "";
						sql = "select class_table_name from t_data_class_info where sn="
							+u_childclass_sn+" and class_status='1'";
						String table_name = db.queryString(sql);//Excel模板对应的子类型的表名
						if(table_name!=null){
							//成功取出子类型对应的信息表名
							String sql1 = "";
							sql1 = "select class_columnname,class_comments,class_columntype,class_nullable,class_columnsize " +
									"from t_data_class_status where class_tablename='"+table_name+"' and class_sn="+u_childclass_sn+" and class_columnstatus='1' and COLUMN_TYPE='2' order by sn ";
							@SuppressWarnings("unchecked")
							List<Map<String,Object>> list_map = db.query(sql1);
							if(list_map.size()==0){
								returnBean.setIf_success(false);
								returnBean.setMsg("该子类型[ID="+u_childclass_sn+"]在数据库中没有对应的状态信息，请返回重新进行操作！");
							}else{
								//该子类的限制条件已经找到
								//将纬度和经度对应的限制条件放入list_map中，方便统一调度使用
								Map<String,Object> columnMap = new HashMap<String,Object>();
								columnMap.put("class_columnname", "LAT");
								columnMap.put("class_comments", "纬度");
								columnMap.put("class_columntype", "1");//纬度为字符串类型
								columnMap.put("class_nullable", "2");//该字符串是否为空，经纬度信息必须不能为空！
								columnMap.put("class_columnsize", "4000");//该字符串长度允许的长度是多少，经纬度信息默认为最大为60
								list_map.add(columnMap);
								columnMap = new HashMap<String,Object>();
								columnMap.put("class_columnname", "LNG");
								columnMap.put("class_comments", "经度");
								columnMap.put("class_columntype", "1");//纬度为字符串类型
								columnMap.put("class_nullable", "2");//该字符串是否为空，经纬度信息均不允许为空！
								columnMap.put("class_columnsize", "4000");//该字符串长度允许的长度是多少，经纬度信息的默认最大值为60
								list_map.add(columnMap);
								//到这一步，获取完整的list_map-----该子类数据库中对应的所有关键字的限制条件（数据库列名，数据库列备注，列数据类型，是否可以为空，允许的最大长度）
								List<String> error_list_db_notin_excel = new ArrayList<String>();//数据库中要求的字段，而Excel中没有该列
								for(int n=0;n<list_map.size();n++){
									String comments = String.valueOf(list_map.get(n).get("class_comments"));//遍历出所有的备注信息，并开始和上传的Excel中的列名进行比对
									boolean if_find_equal = false;
									for(int m=0;m<list_title.size();m++){
										if(comments.equals(list_title.get(m))){
											if_find_equal=true;
											break;
										}else{
											if_find_equal=false;
											continue;
										}
									}
									if(if_find_equal){
										continue;
									}else{
										error_list_db_notin_excel.add(comments);
									}
								}
								//获取列名同步比较后的结果LIST
								if(error_list_db_notin_excel.size()!=0){
									returnBean.setIf_success(false);
									String resultMsg = "数据库中需要的字段在Excel中不存在，缺失的字段如下：";
									for(String s:error_list_db_notin_excel){
										resultMsg+=s+"，";
									}
									returnBean.setMsg(resultMsg);
								}else{
									//通过列名比较检测
									//list_map-----通过验证的数据库需要的各字段及其限制条件
									//list_rows----Excel表中各行各列数据
									//list_title---Excel表中的各列列名，与list_map.get(n).get("class_comments")相对应
									if(list_title.size()!=list_map.size()){
										returnBean.setIf_success(false);
										returnBean.setMsg("Excel中的列名数量和数据库中所需的属性数量不相同！");
									}else{
										//对list_map按照list_title的顺序进行重新排序
										List<Map<String,Object>> list_map_order = new ArrayList<Map<String,Object>>();//排序过的List_map
										boolean if_success2 = true;
										for(int i=0;i<list_title.size();i++){
											String title_1 = list_title.get(i);//excel中的列
											boolean if_correct1=false;
											for(int j=0;j<list_map.size();j++){
												String title_2 = String.valueOf(list_map.get(j).get("class_comments"));//数据库中的列
												if(title_1.equals(title_2)){
													if_correct1 = true;//如果找到相对应的Excel和数据库列
													list_map_order.add(list_map.get(j));
													break;
												}else{
													if_correct1 = false;
													continue;
												}
											}
											if(if_correct1){
												//本次excel中的列存在数据库中对应的列
												continue;
											}else{
												returnBean.setIf_success(false);
												returnBean.setMsg("Excel中的列["+title_1+"]与数据库列没有相对应的匹配值！操作失败！");
												if_success2 = false;
												break;
											}
										}
										if(!if_success2){
											//没有匹配值
											//暂时什么都不做
										}else{
											if(list_map_order.size()==list_title.size()){
												//首先校验是否可空：
												//list_map_order----->数据库各列对应的限制条件---顺序已经按照list_title的顺序进行过排序了
												//list_title----->excel各列名
												//list_rows------>excel各行内容（不包括标题行）
												boolean if_success4 = true;
												for(int i=0;i<list_map_order.size();i++){
													boolean if_cannull1 = (("1".equals(list_map_order.get(i).get("class_nullable")))?(true):("2".equals(list_map_order.get(i).get("class_nullable"))?(false):(false)));
													if(if_cannull1){
														
													}else{
														//不能为空
														boolean if_success3 = true;
														for(int j=0;j<list_rows.size();j++){
															if("".equals(list_rows.get(j).get(i))||list_rows.get(j).get(i)==null){
																if_success3=false;
																break;
															}
														}
														if(!if_success3){
															returnBean.setIf_success(false);
															returnBean.setMsg(returnBean.getMsg()+"Excel中的["+list_title.get(i)+"]列不能为空！");
															if_success4=false;
															break;
														}
													}
												}
												if(if_success4){
													//是否为空校验通过
													//其次校验数值类型是否正确：
													boolean if_success6 = true;
													for(int i=0;i<list_map_order.size();i++){
														boolean if_num1 = (("2".equals(list_map_order.get(i).get("class_columntype"))||"3".equals(list_map_order.get(i).get("class_columntype")))?(true):(false));
														if(!if_num1){
															//如果是字符串类型，就不需要额外校验了
														}else{
															//如果是数值类型，则需要进行校验！
															boolean if_success5 = true;
															for(int j=0;j<list_rows.size();j++){
																if(!isInteger(list_rows.get(j).get(i))&&!isDouble(list_rows.get(j).get(i))){
																	if_success5 = false;
																	break;
																}
															}
															if(!if_success5){
																returnBean.setIf_success(false);
																returnBean.setMsg(returnBean.getMsg()+"Excel中的["+list_title.get(i)+"]列的数值类型不匹配！只接受数值型！");
																if_success6=false;
																break;
															}
														}
													}
													if(if_success6){
														//校验数值类型通过
														//开始校验字符长度是否符合规定
														boolean if_success8 = true;
														for(int i=0;i<list_map_order.size();i++){
															boolean if_size1 = (("2".equals(list_map_order.get(i).get("class_columntype"))||"3".equals(list_map_order.get(i).get("class_columntype")))?(false):(true));
															if(if_size1){
																//需要进行单元格数据长度校验
																boolean if_success7 = true;
																int column_size = Integer.parseInt(String.valueOf(list_map_order.get(i).get("class_columnsize")));//不是类型2或3  是类型1和4
																for(int j=0;j<list_rows.size();j++){//取出每一行，然后下面的循环取出特定的列
																	if(list_rows.get(j).get(i).length()>column_size){
																		//长度超出范围
																		if_success7=false;
																		break;
																	}
																}
																if(!if_success7){
																	returnBean.setIf_success(false);
																	returnBean.setMsg(returnBean.getMsg()+"Excel中的["+list_title.get(i)+"]列的数值长度超过设定值["+column_size+"]，操作失败！");
																	if_success8=false;
																	break;
																}
															}else{
																//不需要进行单元格长度校验
															}
														}
														if(if_success8){
															System.out.println("成功通过校验，Excel各行各列数据准备进行数据库插入操作！");
															System.out.println("list_rows:"+list_rows.toString());
															List<String> list_colname = new ArrayList<String>();
															for(int ii=0;ii<list_map.size();ii++){
																list_colname.add(String.valueOf(list_map.get(ii).get("class_columnname")));
															}
															System.out.println("list_colname:"+list_colname.toString());
															String sql10 = "";
															sql10 = "select post_class from t_data_class_info where class_table_name='"+table_name+"' and class_status='1' ";
															String post_class = "";//邮政还是宜家
															post_class = db.queryString(sql10);
															String colString = "";
															for(int io=0;io<list_colname.size();io++){
																if(!"LAT".equals(String.valueOf(list_colname.get(io)))&&!"LNG".equals(String.valueOf(list_colname.get(io)))){
																	colString += ","+list_colname.get(io);
																}else{
																	continue;
																}
															}
															int success_num = 0;
															int error_num = 0;
															for(int is = 0;is<list_rows.size();is++){
																String sql11 = "";
																sql11 += "insert into "+table_name+" (sn,status_flag,lat,lng,post_flag"+colString;
																sql11 += ") values (";
																String sn10 = "";
																sn10 = db.queryString("select SEQ_"+table_name.toUpperCase()+".nextval from dual ");
																sql11 += sn10+",'1','"+list_rows.get(is).get(list_rows.get(is).size()-2)+"','"+list_rows.get(is).get(list_rows.get(is).size()-1)+"','"+post_class+"'";
																for(int su=0;su<list_rows.get(is).size()-2;su++){
																	sql11 += ","+"'"+list_rows.get(is).get(su)+"'";
																}
																sql11 += ") ";
																System.out.println("insertSQL："+sql11);
																try {
																	int insert_num11 = 0;
																	insert_num11 = db.insert(sql11);
																	if(insert_num11==1){
																		success_num++;
																	}else{
																		error_num++;
																	}
																} catch (Exception e) {
																	e.printStackTrace();
																	error_num++;
																}
															}
															returnBean.setIf_success(true);
															returnBean.setMsg("成功导入数据{"+success_num+"}条，失败{"+error_num+"}条！");
														}else{
															System.out.println("最后一步校验失败！");
														}
													}
												}
											}else{
												returnBean.setIf_success(false);
												returnBean.setMsg(returnBean.getMsg()+"列数不匹配！操作失败！3BFK12KBBA！");
											}
										}
									}
								}
							}
						}else{
							//数据库中不存在该子类型对应的信息表名
							returnBean.setIf_success(false);
							returnBean.setMsg("该子类型在数据库中不存在！请返回重新尝试！");
						}
					} catch (Exception e) {
						returnBean.setIf_success(false);
						returnBean.setMsg(e.getMessage());
					}
					//--------------------------------取出Excel模板对应的校验条件----ED---------------------------
		        }
				//--------------------------------取出上传的Excel文件中的内容-------ED------------------------
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("文件上传失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		
		return returnBean;
	}

	/**
	 * 判断是否是整数
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str){
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	    return pattern.matcher(str).matches();
	}
	/**
	 * 判断是否是浮点数
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str){
	    Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
	    return pattern.matcher(str).matches();
	}
	
}
