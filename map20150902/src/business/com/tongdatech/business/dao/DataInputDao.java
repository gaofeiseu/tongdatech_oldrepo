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
			returnBean.setMsg("�ɹ���ȡ�������б���Ϣ��");
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("��ȡ�������б�ʧ�ܣ����ݿ��쳣��");
		}
		return returnBean;
	}

	@SuppressWarnings("unchecked")
	public ReturnBean getTemplat(DataInputBean bean) {
		ReturnBean returnBean = new ReturnBean();
		@SuppressWarnings("unused")
		String bigtype = bean.getTemplat_mainclass_sn();//������
		@SuppressWarnings("unused")
		String depttype = bean.getTemplat_usertype_sn();//��������
		String class_sn = bean.getTemplat_childclass_sn();//������SN
		String class_name = bean.getTemplat_childclass_name();//����������
		String showFileName = "{"+class_name+"}";//����ǰ̨չʾ�ļ���������ʱ����
		String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
//		System.out.println("webrootpath-------------->"+webrootPath);
		String excel_folderpath = webrootPath+"/ExcelSpace";//excel�ļ��ı���·��
		String excel_filename = UUID.randomUUID().toString()+".xls";
		String realFileUrl = "/ExcelSpace/"+excel_filename;//ǰ̨�����ӵ���ʵURL
		String excel_url = excel_folderpath+"/"+excel_filename;//excelģ�����ɺ�����·��
		String sheetname = "{"+class_name+"}ģ��";
		
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
				returnBean.setMsg("������û���κ����ԣ�");
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
					typeStr += "�������ַ�����";
				}else if("2".equals(list_map.get(i).get("class_columntype"))){
					typeStr += "���������֣�";
				}else if("3".equals(list_map.get(i).get("class_columntype"))){
					typeStr += "���������֣��ٷ�������ֱ���������ֲ��֣��ٷֺŲ���Ҫ���룩��С�������3λ��";
				}else if("4".equals(list_map.get(i).get("class_columntype"))){
					typeStr += "�������ַ��������Ȳ�Ҫ����50���ַ���";
				}
				if("1".equals(list_map.get(i).get("class_columntype"))){
					typeStr += "���Ȳ�Ҫ����"+String.valueOf(list_map.get(i).get("class_columnsize"))+"���ַ�����";
				}
				if("1".equals(list_map.get(i).get("class_nullable"))){
					typeStr += "����Ϊ��";
				}else if("2".equals(list_map.get(i).get("class_nullable"))){
					typeStr += "������Ϊ��";
				}
				list_columncomments.add(typeStr);
			}
			list_columnname.add("γ��");
			list_columncomments.add("���磺32.1234567");
			list_columnname.add("����");
			list_columncomments.add("���磺116.354004");
			boolean if_success = FileUtils.createExcel(excel_url, sheetname,list_columnname,list_columncomments);
			if(if_success){
				returnBean.setIf_success(true);
				returnBean.setMsg("ģ�����ɳɹ���");
				List<String> returnList = new ArrayList<String>();
				returnList.add(showFileName);//��fakename��ӽ�����List
				returnList.add(realFileUrl);//��realname��ӽ�����List
				returnBean.setReturnList(returnList);
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("ģ������ʧ�ܣ�");
			}
		}
		return returnBean;
	}

	public ReturnBean uploadTemplat(File[] upload,DataInputBean bean) {
		ReturnBean returnBean = new ReturnBean();
		String u_filename = "";
		String u_childclass_sn = "";
		u_filename = bean.getUpload_excel_name();//�ϴ����ļ����ļ���
		u_childclass_sn = bean.getUpload_childclass_sn();//�ϴ����ļ���Ӧ��������SN
		
		String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
		String filefolderPath = webrootPath+"/ExcelSpace/ExcelUpload"+FileUtils.getFileSeparator();
		String uuidFilename = UUID.randomUUID().toString()+"."+u_filename.split("\\.")[u_filename.split("\\.").length-1];
		String filepath = filefolderPath+uuidFilename;
		
		//------------------------------��ʼ�����ļ��ϴ�	ED-------------------------------------
		try {
			if(upload[0].renameTo(new File(filepath))){
				//--------------------------------ȡ���ϴ���Excel�ļ��е�����-------ST------------------------
				ExcelBean excelBean = new ExcelBean();
				excelBean.setFilepath(filepath);
				ExcelReader excelReader = new ExcelReader(excelBean);
		        excelReader.readExcelTitle();
		        excelReader.readExcelContent();
		        excelBean = excelReader.getExcelBean();
		        
		        List<String> list_title = new ArrayList<String>();//Excel�ļ��е�����
		        List<List<String>> list_rows = new ArrayList<List<String>>();//Excel�ļ��е�ÿ���е�ÿ�����ݼ���
		        list_title = excelBean.getList_title();
		        list_rows = excelBean.getList_rows();
		        if(list_rows.size()==0){
		        	returnBean.setIf_success(false);
		        	returnBean.setMsg("�ϴ���Excel�ļ���û����Ч���ݣ���ȷ�Ϻ����½��в�����");
		        }else{
		        	//ȡ����Excel�ļ��е����ݣ��Ҹ����ݲ�Ϊ�գ�
		        	//--------------------------------ȡ��Excelģ���Ӧ��У������----ST---------------------------
					
			        try {
						String sql = "";
						sql = "select class_table_name from t_data_class_info where sn="
							+u_childclass_sn+" and class_status='1'";
						String table_name = db.queryString(sql);//Excelģ���Ӧ�������͵ı���
						if(table_name!=null){
							//�ɹ�ȡ�������Ͷ�Ӧ����Ϣ����
							String sql1 = "";
							sql1 = "select class_columnname,class_comments,class_columntype,class_nullable,class_columnsize " +
									"from t_data_class_status where class_tablename='"+table_name+"' and class_sn="+u_childclass_sn+" and class_columnstatus='1' and COLUMN_TYPE='2' order by sn ";
							@SuppressWarnings("unchecked")
							List<Map<String,Object>> list_map = db.query(sql1);
							if(list_map.size()==0){
								returnBean.setIf_success(false);
								returnBean.setMsg("��������[ID="+u_childclass_sn+"]�����ݿ���û�ж�Ӧ��״̬��Ϣ���뷵�����½��в�����");
							}else{
								//����������������Ѿ��ҵ�
								//��γ�Ⱥ;��ȶ�Ӧ��������������list_map�У�����ͳһ����ʹ��
								Map<String,Object> columnMap = new HashMap<String,Object>();
								columnMap.put("class_columnname", "LAT");
								columnMap.put("class_comments", "γ��");
								columnMap.put("class_columntype", "1");//γ��Ϊ�ַ�������
								columnMap.put("class_nullable", "2");//���ַ����Ƿ�Ϊ�գ���γ����Ϣ���벻��Ϊ�գ�
								columnMap.put("class_columnsize", "4000");//���ַ�����������ĳ����Ƕ��٣���γ����ϢĬ��Ϊ���Ϊ60
								list_map.add(columnMap);
								columnMap = new HashMap<String,Object>();
								columnMap.put("class_columnname", "LNG");
								columnMap.put("class_comments", "����");
								columnMap.put("class_columntype", "1");//γ��Ϊ�ַ�������
								columnMap.put("class_nullable", "2");//���ַ����Ƿ�Ϊ�գ���γ����Ϣ��������Ϊ�գ�
								columnMap.put("class_columnsize", "4000");//���ַ�����������ĳ����Ƕ��٣���γ����Ϣ��Ĭ�����ֵΪ60
								list_map.add(columnMap);
								//����һ������ȡ������list_map-----���������ݿ��ж�Ӧ�����йؼ��ֵ��������������ݿ����������ݿ��б�ע�����������ͣ��Ƿ����Ϊ�գ��������󳤶ȣ�
								List<String> error_list_db_notin_excel = new ArrayList<String>();//���ݿ���Ҫ����ֶΣ���Excel��û�и���
								for(int n=0;n<list_map.size();n++){
									String comments = String.valueOf(list_map.get(n).get("class_comments"));//���������еı�ע��Ϣ������ʼ���ϴ���Excel�е��������бȶ�
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
								//��ȡ����ͬ���ȽϺ�Ľ��LIST
								if(error_list_db_notin_excel.size()!=0){
									returnBean.setIf_success(false);
									String resultMsg = "���ݿ�����Ҫ���ֶ���Excel�в����ڣ�ȱʧ���ֶ����£�";
									for(String s:error_list_db_notin_excel){
										resultMsg+=s+"��";
									}
									returnBean.setMsg(resultMsg);
								}else{
									//ͨ�������Ƚϼ��
									//list_map-----ͨ����֤�����ݿ���Ҫ�ĸ��ֶμ�����������
									//list_rows----Excel���и��и�������
									//list_title---Excel���еĸ�����������list_map.get(n).get("class_comments")���Ӧ
									if(list_title.size()!=list_map.size()){
										returnBean.setIf_success(false);
										returnBean.setMsg("Excel�е��������������ݿ��������������������ͬ��");
									}else{
										//��list_map����list_title��˳�������������
										List<Map<String,Object>> list_map_order = new ArrayList<Map<String,Object>>();//�������List_map
										boolean if_success2 = true;
										for(int i=0;i<list_title.size();i++){
											String title_1 = list_title.get(i);//excel�е���
											boolean if_correct1=false;
											for(int j=0;j<list_map.size();j++){
												String title_2 = String.valueOf(list_map.get(j).get("class_comments"));//���ݿ��е���
												if(title_1.equals(title_2)){
													if_correct1 = true;//����ҵ����Ӧ��Excel�����ݿ���
													list_map_order.add(list_map.get(j));
													break;
												}else{
													if_correct1 = false;
													continue;
												}
											}
											if(if_correct1){
												//����excel�е��д������ݿ��ж�Ӧ����
												continue;
											}else{
												returnBean.setIf_success(false);
												returnBean.setMsg("Excel�е���["+title_1+"]�����ݿ���û�����Ӧ��ƥ��ֵ������ʧ�ܣ�");
												if_success2 = false;
												break;
											}
										}
										if(!if_success2){
											//û��ƥ��ֵ
											//��ʱʲô������
										}else{
											if(list_map_order.size()==list_title.size()){
												//����У���Ƿ�ɿգ�
												//list_map_order----->���ݿ���ж�Ӧ����������---˳���Ѿ�����list_title��˳����й�������
												//list_title----->excel������
												//list_rows------>excel�������ݣ������������У�
												boolean if_success4 = true;
												for(int i=0;i<list_map_order.size();i++){
													boolean if_cannull1 = (("1".equals(list_map_order.get(i).get("class_nullable")))?(true):("2".equals(list_map_order.get(i).get("class_nullable"))?(false):(false)));
													if(if_cannull1){
														
													}else{
														//����Ϊ��
														boolean if_success3 = true;
														for(int j=0;j<list_rows.size();j++){
															if("".equals(list_rows.get(j).get(i))||list_rows.get(j).get(i)==null){
																if_success3=false;
																break;
															}
														}
														if(!if_success3){
															returnBean.setIf_success(false);
															returnBean.setMsg(returnBean.getMsg()+"Excel�е�["+list_title.get(i)+"]�в���Ϊ�գ�");
															if_success4=false;
															break;
														}
													}
												}
												if(if_success4){
													//�Ƿ�Ϊ��У��ͨ��
													//���У����ֵ�����Ƿ���ȷ��
													boolean if_success6 = true;
													for(int i=0;i<list_map_order.size();i++){
														boolean if_num1 = (("2".equals(list_map_order.get(i).get("class_columntype"))||"3".equals(list_map_order.get(i).get("class_columntype")))?(true):(false));
														if(!if_num1){
															//������ַ������ͣ��Ͳ���Ҫ����У����
														}else{
															//�������ֵ���ͣ�����Ҫ����У�飡
															boolean if_success5 = true;
															for(int j=0;j<list_rows.size();j++){
																if(!isInteger(list_rows.get(j).get(i))&&!isDouble(list_rows.get(j).get(i))){
																	if_success5 = false;
																	break;
																}
															}
															if(!if_success5){
																returnBean.setIf_success(false);
																returnBean.setMsg(returnBean.getMsg()+"Excel�е�["+list_title.get(i)+"]�е���ֵ���Ͳ�ƥ�䣡ֻ������ֵ�ͣ�");
																if_success6=false;
																break;
															}
														}
													}
													if(if_success6){
														//У����ֵ����ͨ��
														//��ʼУ���ַ������Ƿ���Ϲ涨
														boolean if_success8 = true;
														for(int i=0;i<list_map_order.size();i++){
															boolean if_size1 = (("2".equals(list_map_order.get(i).get("class_columntype"))||"3".equals(list_map_order.get(i).get("class_columntype")))?(false):(true));
															if(if_size1){
																//��Ҫ���е�Ԫ�����ݳ���У��
																boolean if_success7 = true;
																int column_size = Integer.parseInt(String.valueOf(list_map_order.get(i).get("class_columnsize")));//��������2��3  ������1��4
																for(int j=0;j<list_rows.size();j++){//ȡ��ÿһ�У�Ȼ�������ѭ��ȡ���ض�����
																	if(list_rows.get(j).get(i).length()>column_size){
																		//���ȳ�����Χ
																		if_success7=false;
																		break;
																	}
																}
																if(!if_success7){
																	returnBean.setIf_success(false);
																	returnBean.setMsg(returnBean.getMsg()+"Excel�е�["+list_title.get(i)+"]�е���ֵ���ȳ����趨ֵ["+column_size+"]������ʧ�ܣ�");
																	if_success8=false;
																	break;
																}
															}else{
																//����Ҫ���е�Ԫ�񳤶�У��
															}
														}
														if(if_success8){
															System.out.println("�ɹ�ͨ��У�飬Excel���и�������׼���������ݿ���������");
															System.out.println("list_rows:"+list_rows.toString());
															List<String> list_colname = new ArrayList<String>();
															for(int ii=0;ii<list_map.size();ii++){
																list_colname.add(String.valueOf(list_map.get(ii).get("class_columnname")));
															}
															System.out.println("list_colname:"+list_colname.toString());
															String sql10 = "";
															sql10 = "select post_class from t_data_class_info where class_table_name='"+table_name+"' and class_status='1' ";
															String post_class = "";//���������˼�
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
																System.out.println("insertSQL��"+sql11);
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
															returnBean.setMsg("�ɹ���������{"+success_num+"}����ʧ��{"+error_num+"}����");
														}else{
															System.out.println("���һ��У��ʧ�ܣ�");
														}
													}
												}
											}else{
												returnBean.setIf_success(false);
												returnBean.setMsg(returnBean.getMsg()+"������ƥ�䣡����ʧ�ܣ�3BFK12KBBA��");
											}
										}
									}
								}
							}
						}else{
							//���ݿ��в����ڸ������Ͷ�Ӧ����Ϣ����
							returnBean.setIf_success(false);
							returnBean.setMsg("�������������ݿ��в����ڣ��뷵�����³��ԣ�");
						}
					} catch (Exception e) {
						returnBean.setIf_success(false);
						returnBean.setMsg(e.getMessage());
					}
					//--------------------------------ȡ��Excelģ���Ӧ��У������----ED---------------------------
		        }
				//--------------------------------ȡ���ϴ���Excel�ļ��е�����-------ED------------------------
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("�ļ��ϴ�ʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg(e.getMessage());
		}
		
		return returnBean;
	}

	/**
	 * �ж��Ƿ�������
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str){
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
	    return pattern.matcher(str).matches();
	}
	/**
	 * �ж��Ƿ��Ǹ�����
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str){
	    Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
	    return pattern.matcher(str).matches();
	}
	
}
