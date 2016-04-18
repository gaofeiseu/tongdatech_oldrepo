package com.tongdatech.map_mobile.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.tongdatech.map.util.ServiceConfig;
import com.tongdatech.map.util.WebServiceClient;
import com.tongdatech.map_mobile.bean.MobileBean;
import com.tongdatech.map_mobile.bean.MobileReturnBean;
import com.tongdatech.map_mobile.dao.MobileDao;
import com.tongdatech.map_mobile.utils.FTPDemo;
import com.tongdatech.map_mobile.utils.SecurityUtil;

public class MobileService {
	MobileDao dao = new MobileDao();
	private static final String WAP_LOGIN = "wap_login";
	private static final String WAP_CHECK_IN = "wap_check_in";
	private static final String WAP_GET_HISTORY = "wap_get_history";
	private static final String WAP_IMG_KEEPINDB = "wap_img_keepindb";
	private static final String WAP_GET_HISTORY_TOTAL_NUM = "wap_get_history_total_num";
	
	private static final String FTP_PATH = ServiceConfig.get("FTP_PATH");//"/JS_MAP/file_folder/mobile_img_folder/";
	private static final String FTP_IP = ServiceConfig.get("FTP_IP");//"10.135.13.200";
	private static final int FTP_PORT = Integer.parseInt(ServiceConfig.get("FTP_PORT"));//21;
	private static final String FTP_USERNAME = ServiceConfig.get("FTP_USERNAME");//"administrator";
	private static final String FTP_PASSWORD = ServiceConfig.get("FTP_PASSWORD");//"abc123!";
	
	@SuppressWarnings("unchecked")
	public MobileReturnBean login(MobileBean bean) {
		String login_name = "";
		String pass_word = "";
		login_name = bean.getLogin_name();
		pass_word = bean.getPass_word();
		pass_word = SecurityUtil.md5(pass_word);
		Object[] objArr = new Object[2];
		objArr[0] = login_name;
		objArr[1] = pass_word;
		String clientResult = "";
		try {
			clientResult = new WebServiceClient().invokeBDM(WAP_LOGIN, objArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("login-----clientResult--->"+clientResult);
		MobileReturnBean returnBean = new MobileReturnBean();
		
		Map<String,Object> result_map = new HashMap<String,Object>();
		try {
			result_map = (Map<String, Object>) JSONUtil.deserialize(clientResult);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if("1".equals(result_map.get("code"))){
			returnBean.setIf_success(true);
		}else{
			returnBean.setIf_success(false);
		}
		returnBean.setReturnMap(result_map);
		return returnBean;
	}

	@SuppressWarnings("unchecked")
	public String check_in(MobileBean bean, File[] upload, String[] uploadContentType, String[] uploadFileName) {
		String resultStr = "";
		String img_db_sn = "";
		//<script>parent.callback('��Ƭ�ϴ��ɹ��ˣ�')</script>
		boolean if_upload_success = false;
		System.out.println("check_in bean:"+bean.toString());
		if(bean.getMobile_img_url()==null||"".equals(bean.getMobile_img_url())){
			if_upload_success = true;//TODO
		}else{
			if(upload!=null){
				if(upload[0].length()==0){
					System.out.println("�ļ���С��0����������ļ���������");
					System.out.println("�ļ�����"+bean.getFile_fake_name());
					if_upload_success = true;
				}else{
					try {
						//System.out.println(upload[0].exists());//��ȡ�����ļ��Ƿ����
						//System.out.println(upload[0].getPath());//��ȡ������ʱ�ļ���·��
						//System.out.println(upload[0].length());//��ȡ�����ļ��Ĵ�С
						System.out.println("�ļ�����"+bean.getFile_fake_name());
						String old_filename = bean.getFile_fake_name();
						String old_realfilename = "";//�ϴ����ļ���ԭ��ʵ����-----1
//						if("\\".equals(System.getProperties().getProperty("file.separator"))){
//							System.out.println("windowsϵͳ");
//							old_realfilename = old_filename.substring(old_filename.lastIndexOf("\\")+1, old_filename.length());
//						}else if("/".equals(System.getProperties().getProperty("file.separator"))){
//							System.out.println("Unixϵͳ");
//							old_realfilename = old_filename.substring(old_filename.lastIndexOf("/")+1, old_filename.length());
//						}
						old_filename = uploadFileName[0];
						old_realfilename = uploadFileName[0];
						String file_type = "";//�ϴ����ļ�������-----4
						String file_size = String.valueOf(upload[0].length());//�ϴ����ļ��Ĵ�С-----3
						if(old_filename==null||"".equals(old_filename)){
							System.out.println("����С���ֻ��޷���ȡ�ļ����ƣ�");
							file_type = ".png";
						}else{
							file_type = old_realfilename.substring(old_realfilename.lastIndexOf(".")+1, old_realfilename.length());
						}
						System.out.println("Mobile_img_url--->"+bean.getMobile_img_url());
						System.out.println("lat:"+bean.getCheck_in_lat()+"   lng:"+bean.getCheck_in_lng());
//						String realFilename = bean.getMobile_img_url()+UUID.randomUUID()+"."+file_type;//�ϴ����ļ��ķ���������·��-----2
						String realFilename = bean.getMobile_img_url()+"/"+UUID.randomUUID()+"."+file_type;// FTP_PATH 2015-4-22 10:34:10  gaofei
						System.out.println("realFilename:"+realFilename);
						if(upload[0].renameTo(new File(realFilename))){
							//resultStr = "<script>parent.callback('��Ƭ�ϴ��ɹ��ˣ�')</script>";
							//�ֻ�ͼƬ����·����
							//   D:\JS_MAP\file_folder\mobile_img_folder
							//ͬ��������������IP��
							//10.135.13.200
							//ͬ��������������FTP·����
							//   /JS_MAP/file_folder/mobile_img_folder/
							/******************************************************************/
							/******************************************************************/	
							/*************����ն˷�������Ӧ�÷��������ڲ�ͬ�豸����Ҫ��FTP���񣬽⿪����3��ע��*****************/						
							try{
								FTPDemo demo = new FTPDemo();
	                        	File file = new File(realFilename);
	                        	demo.uploadFileWithFTP(file, FTP_PATH, FTP_IP, FTP_PORT, FTP_USERNAME, FTP_PASSWORD);
							}
							catch(Exception ex){
								ex.printStackTrace();
							}
                        	
							Object[] objArr = new Object[4];
							objArr[0] = old_realfilename;
							objArr[1] = realFilename;
							objArr[2] = file_size;
							objArr[3] = file_type;
							String clientResult = "";
							try {
								clientResult = new WebServiceClient().invokeBDM(WAP_IMG_KEEPINDB, objArr);
							} catch (Exception e) {
								e.printStackTrace();
							}
							System.out.println("clientResult------------->"+clientResult);
							Map<String,Object> resultMap = new HashMap<String,Object>();
							try {
								resultMap = (Map<String, Object>) JSONUtil.deserialize(clientResult);
								if("0".equals(resultMap.get("code"))){
									if_upload_success = false;
								}else if("1".equals(resultMap.get("code"))){
									img_db_sn = String.valueOf(resultMap.get("msg"));
									if_upload_success = true;
								}
							} catch (JSONException e) {
								e.printStackTrace();
								if_upload_success = false;
							}
						}else{
							if_upload_success = false;
						}
					} catch (Exception e) {
						e.printStackTrace();
						if_upload_success = false;
					}
				}
			}else{
				if_upload_success = true;
			}
		}
		
		if(if_upload_success){
			String my_login_name = "";//APP��¼��
			String my_login_id = "";//APP��¼ID
			String check_in_lat = "";//ǩ��γ��
			String check_in_lng = "";//ǩ������
			String visit_type = "";//�߷�����
			String visit_custom_name = "";//�߷ÿͻ�����
			String visit_content = "";//�߷�����
			String visit_note = "";//�߷ñ�ע
			String visit_class = "";//�߷�����
			String custom_type = "";//�ͻ�����	2015-10-26 14:41:25
			
			my_login_name = bean.getMy_login_name();
			my_login_id = bean.getMy_login_id();
			check_in_lat = bean.getCheck_in_lat();
			check_in_lng = bean.getCheck_in_lng();
			visit_type = bean.getVisit_type();
			visit_custom_name = bean.getVisit_custom_name();
			visit_content = bean.getVisit_content();
			visit_note = bean.getVisit_note();
			visit_class = bean.getVisit_class();
			custom_type = bean.getCustom_type();
			
			Object[] objArr = new Object[12];//add custom type  increace array length from 11 to 12
			objArr[0] = my_login_name;
			objArr[1] = my_login_id;
			objArr[2] = check_in_lat;
			objArr[3] = check_in_lng;
			objArr[4] = visit_type;
			objArr[5] = visit_custom_name;
			objArr[6] = visit_content;
			objArr[7] = visit_note;
			objArr[8] = visit_class;
			objArr[9] = img_db_sn;
			objArr[10] = ((bean.getIf_wap()==null||"".equals(bean.getIf_wap()))?("0"):(bean.getIf_wap()));//if_wap---1 yes , other no
			objArr[11] = custom_type;//add custom type 2015-10-26 14:41:18
			String clientResult = "";
			try {
				clientResult = new WebServiceClient().invokeBDM(WAP_CHECK_IN, objArr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("clientResult------------->"+clientResult);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			try {
				resultMap = (Map<String, Object>) JSONUtil.deserialize(clientResult);
				if("0".equals(resultMap.get("code"))){
					resultStr = "<script>parent.callback('"+resultMap.get("msg")+"')</script>";
				}else if("1".equals(resultMap.get("code"))){
					resultStr = "<script>parent.callback('"+resultMap.get("msg")+"')</script>";
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			resultStr = "<script>parent.callback('��Ƭ�ϴ�ʧ�ܣ�')</script>";
		}
		
		
		return resultStr;
	}

	@SuppressWarnings("unchecked")
	public MobileReturnBean getHistory(MobileBean bean) {
		MobileReturnBean returnBean = new MobileReturnBean();
		String history_time_st = "";
		String history_time_ed = "";
		String my_login_name = "";
		String my_login_id = "";
		String history_now_num = "";
		String history_increace_num = "";
		
		history_time_st = bean.getHistory_time_st();
		history_time_ed = bean.getHistory_time_ed();
		my_login_name = bean.getMy_login_name();
		my_login_id = bean.getMy_login_id();
		history_now_num = bean.getHistory_now_num();
		history_increace_num = bean.getHistory_increace_num();
		
		Object[] objArr = new Object[6];
		objArr[0] = my_login_name;
		objArr[1] = my_login_id;
		objArr[2] = history_time_st;
		objArr[3] = history_time_ed;
		objArr[4] = history_now_num;
		objArr[5] = history_increace_num;
		String clientResult = "";
		try {
			clientResult = new WebServiceClient().invokeBDM(WAP_GET_HISTORY, objArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("clientResult------------->"+clientResult);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			resultMap = (Map<String, Object>) JSONUtil.deserialize(clientResult);
			if("0".equals(resultMap.get("code"))){
				returnBean.setIf_success(false);
				returnBean.setMsg(String.valueOf(resultMap.get("msg")));
			}else if("1".equals(resultMap.get("code"))){
				returnBean.setIf_success(true);
				returnBean.setReturnMap(resultMap);
				returnBean.setMsg("��ѯ�ɹ�������"+((List<Map<String,Object>>)resultMap.get("msg")).size()+"������.");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("service�������ݽ���ʧ�ܣ�");
		}
		return returnBean;
	}
	
	public MobileReturnBean checkUpdate(MobileBean bean) {
		String path = "";
		String vcode = "";
		MobileReturnBean returnBean = new MobileReturnBean();
		Map<String, Object> msgMap = new HashMap<String, Object>();
		
		try {
			path = bean.getMobile_img_url();
			System.out.println("path " + path);
			vcode = readFileByLines(path);
			returnBean.setIf_success(true);
			msgMap.put("VERSION_ID", vcode);
			returnBean.setReturnMap(msgMap);
		} catch (Exception e) {
			System.out.println(e);
			returnBean.setIf_success(false);
		}
		return returnBean;
	}
	
	public String readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				// ��ʾ�к�
				System.out.println("line " + line + ": " + tempString);
				return tempString;
				// line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public MobileReturnBean getHistoryTotalNum(MobileBean bean) {
		MobileReturnBean returnBean = new MobileReturnBean();
		String history_time_st = "";
		String history_time_ed = "";
		String my_login_name = "";
		String my_login_id = "";
		history_time_st = bean.getHistory_time_st();
		history_time_ed = bean.getHistory_time_ed();
		my_login_name = bean.getMy_login_name();
		my_login_id = bean.getMy_login_id();
		
		Object[] objArr = new Object[4];
		objArr[0] = my_login_name;
		objArr[1] = my_login_id;
		objArr[2] = history_time_st;
		objArr[3] = history_time_ed;
		String clientResult = "";
		try {
			clientResult = new WebServiceClient().invokeBDM(WAP_GET_HISTORY_TOTAL_NUM, objArr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("clientResult------------->"+clientResult);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			resultMap = (Map<String, Object>) JSONUtil.deserialize(clientResult);
			if("0".equals(resultMap.get("code"))){
				returnBean.setIf_success(false);
				returnBean.setMsg(String.valueOf(resultMap.get("msg")));
			}else if("1".equals(resultMap.get("code"))){
				returnBean.setIf_success(true);
				returnBean.setReturnMap(resultMap);//TODO 
				returnBean.setMsg("��ѯ�ɹ�������"+resultMap.get("msg")+"������.");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("service�������ݽ���ʧ�ܣ�");
		}
		return returnBean;
	}

}
