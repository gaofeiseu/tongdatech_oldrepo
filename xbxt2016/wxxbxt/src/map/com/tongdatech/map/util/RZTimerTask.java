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
	            System.out.println("==============================开始执行任务=================================");
	            try{
	            	AjaxMsg am = service.run_rz(new ReportBean());
		            if(am.isSuccess()){
		            	service.report_log("成功", "日终完毕");
		            }else{
		            	service.report_log("失败", am.getMsg());
		            }
	            }
	            catch(Exception ex){
	            	ex.printStackTrace();
	            	service.report_log("失败", "ERROR:"+ex.getMessage());
	            }
	            System.out.println("==============================执行任务完成=================================");
	            isRunning = false;
			} else {
	           service.report_log("失败", "上一次任务执行还未结束...");
			}
		}
		catch(Exception ex){
			service.report_log("失败", ex.getMessage());
		}
	}
}
