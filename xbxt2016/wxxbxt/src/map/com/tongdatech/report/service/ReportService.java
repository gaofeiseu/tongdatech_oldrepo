package com.tongdatech.report.service;

import com.tongdatech.report.bean.ReportBean;
import com.tongdatech.report.dao.ReportDao;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;

public class ReportService {
	private ReportDao dao = new ReportDao();
	public AjaxMsg query_table_1(ReportBean bean) {
		return dao.query_table_1(bean);
	}
	public void report_log(String result_type,String log_str){
		dao.report_log(result_type,log_str);
	}
	public AjaxMsg run_rz(ReportBean bean) {
		return dao.run_rz(bean);
	}
	public AjaxMsg getexcel(ReportBean bean) {
		return dao.getexcel(bean);
	}
	public AjaxMsg getexcel2(ReportBean bean) {
		return dao.getexcel2(bean);
	}
	public AjaxMsg excelOne(ReportBean bean,UserInfo userinfo,int ExcelEdition,int type) throws Exception {
		return dao.excelOne(bean,userinfo,ExcelEdition,type);
	}
	public AjaxMsg excelTwo(ReportBean bean, UserInfo userinfo,int excelEdition,int type) throws Exception {
		return dao.excelTwo(bean,userinfo,excelEdition,type);
	}
	public AjaxMsg doExcelOne(ReportBean bean,UserInfo userinfo) throws Exception {
		return dao.doExcelOne(bean,userinfo);
	}
	public AjaxMsg doExcelTwo(ReportBean bean,UserInfo userinfo) throws Exception {
		return dao.doExcelTwo(bean,userinfo);
	}
	

}
