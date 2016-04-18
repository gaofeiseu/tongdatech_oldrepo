package com.tongdatech.map.util;

import java.util.TimerTask;

import com.tongdatech.report.bean.ReportBean;
import com.tongdatech.report.service.ReportService;
import com.tongdatech.sys.pojo.AjaxMsg;

public class RZTimerTask extends TimerTask{
	private static boolean isRunning = false;
	private ReportService service = new ReportService();
	@Override
	public void run() {
		try{
			if (!isRunning) {
	            isRunning = true;
	            System.out.println("==============================��ʼִ������=================================");
	            try{
	            	AjaxMsg am = service.run_rz(new ReportBean());
		            if(am.isSuccess()){
		            	service.report_log("�ɹ�", "�������");
		            }else{
		            	service.report_log("ʧ��", am.getMsg());
		            }
	            }
	            catch(Exception ex){
	            	ex.printStackTrace();
	            	service.report_log("ʧ��", "ERROR:"+ex.getMessage());
	            }
	            System.out.println("==============================ִ���������=================================");
	            isRunning = false;
			} else {
	           service.report_log("ʧ��", "��һ������ִ�л�δ����...");
			}
		}
		catch(Exception ex){
			service.report_log("ʧ��", ex.getMessage());
		}
	}
}
