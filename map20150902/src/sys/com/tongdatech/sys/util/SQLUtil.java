package com.tongdatech.sys.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SQLת��������       <br>
 * @author xl 
 * @version    2014-4-11 ����11:36:17
 */
public class SQLUtil {
    @Deprecated
	public static String brchSql(String brch_no,String brch_flag){
		String rs="";
		if(brch_flag!=null&&!"".equals(brch_flag)){
			rs=" in (select brch_no from  t_brch start with brch_no='"+brch_no+"' connect by prior brch_no=brch_up)";
		}else{
			rs=" ='"+brch_no+"'";
		}
		return rs;
		
	}
	/**
	 * date����ת sql ��ʽĬ��"yyyy-MM-dd"
	 * @param date
	 * @return sql
	 */
	public static String dateSql(Date date){				
		return  dateSql(date,"yyyy-MM-dd");		
	}
	/**
	 * date����ת sql
	 * @param date
	 * @param dateformat
	 * @return sql
	 */
	public static String dateSql(Date date,String dateformat){
		if(date==null) return " null ";
		SimpleDateFormat sf=new SimpleDateFormat(dateformat);
		dateformat=dateformat.replaceAll("HH", "HH24");
		dateformat=dateformat.replaceAll("mm", "mi");
		String rs=" to_date('"+sf.format(date)+"','"+dateformat+"')";
		return rs;
		
	}
}
