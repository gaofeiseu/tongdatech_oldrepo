
package com.tongdatech.map.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;

public class CommonUtil {
	
	public static String getNotNullStr(Object obj){
		if(null==obj){
			return "";
		}
		if(obj instanceof BigDecimal){
			return ((BigDecimal)obj).toString();
		}
		return obj.toString().trim();
	}
	
	public static String getCurrentTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());		
	}
	
	/**
	 * ���ɳ���ʱ���ص�json�ַ���
	 * @param code	�������
	 * @param msg	������Ϣ
	 * @return
	 */
	public static String genErrorJson(String code,String msg){
		Map<String, String> resultMap = new HashMap<String, String>();
		if(null==code || "".equals(code)){
			code = "0";
		}
		resultMap.put("code", code);	//����code
		if(null==msg || "".equals(msg)){
			msg = "���ݻ�ȡʧ�ܣ������쳣��";
		}
		Map<String, String> msgMap = new HashMap<String, String>();
		msgMap.put("msg", msg);
		
		resultMap.put("msg", new JSONObject(msgMap).toString());	//����msg
		return new JSONObject(resultMap).toString();
	}
	
	/**
	 * ���ɳ���ʱ���ص�json�ַ���
	 * @return
	 */
	public static String genErrorJson(){
		return genErrorJson(null,null);
	}	
	
	/**
	 * ���ɼ��ܽ����õ���Կ
	 * @param strNum ���ִ�
	 * @return
	 */
	public static String genCryptKey(String strNum){
		if(null==strNum){
			return null;
		}
		String regex = "\\d+";
		if(!strNum.matches(regex)){
			return null;
		}		
		String key = strNum;
		if (key.length() > 8) {
			key = key.substring(key.length() - 8);
		} else {
			DecimalFormat df = new DecimalFormat("00000000");
			key = df.format(Long.parseLong(strNum));
		}
		
		return key;
	}
	
	/* ��ֹ����
	 *�磺 ���ݿ�Ӫ��ƽ̨��ѵPPT0609$1374221479234.ppt ��д�����ݿ�Ӫ��ƽ̨��ѵPPT0609.ppt
	 */
  public static List<Map<String, Object>> getShortFileName(List<Map<String, Object>> dd)
  {
	  List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
	  for (int i = 0; i < dd.size(); i++) {
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			HashMap<String, Object> map2 = new HashMap<String, Object>();
			HashMap<String, Object> map3 = new HashMap<String, Object>();
			HashMap<String, Object> map4 = new HashMap<String, Object>();
			Map<String, Object> fileMap = dd.get(i);
			String up_title1 = fileMap.get("up_title1").toString();
			String up_title2 = fileMap.get("up_title2").toString();
			String up_title3 = fileMap.get("up_title3").toString();
			String up_title4 = fileMap.get("up_title4").toString();
			if(up_title1!=null&&!"".equals(up_title1))
			{
				String shortfilename=getShortFileName(up_title1)+"."+getExtensionName(up_title1);
				map1.put("filename", up_title1);
				map1.put("shortfilename", shortfilename);
				fileList.add(map1);
			}
			if(up_title2!=null&&!"".equals(up_title2))
			{
				String shortfilename=getShortFileName(up_title2)+"."+getExtensionName(up_title2);
				map2.put("filename", up_title2);
				map2.put("shortfilename", shortfilename);
				fileList.add(map2);
			}
			if(up_title3!=null&&!"".equals(up_title3))
			{
				String shortfilename=getShortFileName(up_title3)+"."+getExtensionName(up_title3);
				map3.put("filename", up_title3);
				map3.put("shortfilename", shortfilename);
				fileList.add(map3);
			}
			if(up_title4!=null&&!"".equals(up_title4))
			{
				String shortfilename=getShortFileName(up_title4)+"."+getExtensionName(up_title4);
				map4.put("filename", up_title4);
				map4.put("shortfilename", shortfilename);
				fileList.add(map4);
			}
			
	  }
	  
	  return fileList;
	  
  }
  public static String getExtensionName(String filename) {   
      if ((filename != null) && (filename.length() > 0)) {   
          int dot = filename.lastIndexOf('.');   
          if ((dot >-1) && (dot < (filename.length() - 1))) {   
              return filename.substring(dot + 1);   
          }   
      }   
      return filename;   
  }   
  /* 
  * Java�ļ����� ��ȡ������չ�����ļ��� 
  * 
  *  Created on: 2011-8-2 
  *      Author: blueeagle 
  */  
    public static String getShortFileName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('$');   
            if ((dot >-1) && (dot < (filename.length()))) {   
                return filename.substring(0, dot);   
            }   
        }   
        return filename;   
    }  
	
}
