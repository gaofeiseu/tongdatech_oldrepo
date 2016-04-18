package com.tongdatech.xbxt.utils;

import javax.xml.datatype.XMLGregorianCalendar;

import cn.cpst.rit.model.Mail;
import cn.cpst.rit.service.MailCsServiceGn;
import cn.cpst.rit.service.MailCsServiceGnPortType;

import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.xbxt.bean.InterfaceMailInfo;

public class XBUtils {
	private static String url = "";
	private static String serKind = "";
	private static String serSign = "";
	private static boolean if_available = false;
	private static Integer timeout = 0;
	
	public static void init(String _url,String _serKind,String _serSign,Integer _timeout) throws Exception {
		url = _url;
		serKind = _serKind;
		serSign = _serSign;
		timeout = _timeout;
		if_available = true;
	}
	
	public static AjaxMsg get_ttxx(String mail_no){
		AjaxMsg am = new AjaxMsg();
		if(if_available){
			if(mail_no!=null&&!"".equals(mail_no.trim())){
				String return_str = "";
				try{
					MailCsServiceGnPortType service = new MailCsServiceGn().getMailCsServiceGnHttpPort();
					Mail m = service.getMails(serKind, serSign, mail_no);
					String action_infoout = m.getActionInfoOut().getValue();
					String mail_code = m.getMailCode().getValue();
					String office_name = m.getOfficeName().getValue();
					String relation_office_desc = m.getRelationOfficeDesc().getValue();
					XMLGregorianCalendar c = m.getActionDateTime();
					String year = String.valueOf(c.getYear());
					String month = (c.getMonth()>9)?(""+c.getMonth()):("0"+c.getMonth());
					String day = (c.getDay()>9)?(""+c.getDay()):("0"+c.getDay());
					String hour = (c.getHour()>9)?(""+c.getHour()):("0"+c.getHour());
					String minute = (c.getMinute()>9)?(""+c.getMinute()):("0"+c.getMinute());
					String second = (c.getSecond()>9)?(""+c.getSecond()):("0"+c.getSecond());
//					if("已妥投".equals(action_infoout)){
//						am.setSuccess(true);
//					}else{
//						am.setSuccess(false);
//					}
					String info = "action_infoout:"+action_infoout+" mail_code:"+mail_code+" office_name:"+office_name
							+" relation_office_desc:"+relation_office_desc+" time:"+year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
//					mail_code 
//					action_info_out 
//					office_name 
//					relation_office_desc 
//					year 
//					month 
//					day 
//					hour 
//					minute 
//					second
					InterfaceMailInfo mailinfo = new InterfaceMailInfo(mail_code,action_infoout, office_name, relation_office_desc, year, month, day, hour, minute, second);
					am.setSuccess(true);
					am.setBackParam(mailinfo);
					am.setMsg(info);
				}catch(Exception ex){
					System.err.println(ex.getMessage());
					am.setSuccess(false);
					am.setMsg("接口错误：[url:"+url+",serKind:"+serKind+",serSign:"+serSign+",mail_no:"+mail_no+"]  ReturnMessage:"+return_str+"  ErrorMessage:"+ex.getMessage());
				}
			}else{
				am.setSuccess(false);
				am.setMsg("邮件号码为空！");
			}
		}else{
			am.setSuccess(false);
			am.setMsg("还没有进行参数初始化设置！");
		}
		return am;
	}
}
