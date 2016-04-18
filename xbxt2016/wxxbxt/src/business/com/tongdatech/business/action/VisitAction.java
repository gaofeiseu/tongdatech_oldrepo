package com.tongdatech.business.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.business.bean.Param;
import com.tongdatech.business.service.VisitService;
import com.tongdatech.map_mobile.bean.JspBtn;
import com.tongdatech.map_mobile.bean.JspMsg;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.excel.ExcelForReport;

public class VisitAction  extends PaginationAction  implements  ModelDriven<Param>
{

	private static final long serialVersionUID = -12287761761261387L;

	private Param param = new Param();
	
	VisitService visitService =new VisitService();
	private JspMsg  jspMsg;

	private String file_path;
	private InputStream inputStream;
	private String fileName;
	/**
	 * �߷ò�ѯ
	 * @return String
	 */
	public String init(){
		return "init";
	}
	
	/**
	 * �߷ò�ѯ�б�
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String query() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = visitService.query(pagination,param,userInfo);
		JsonUtil.ToJson(rs,response);
		return null;
	}

	/**
	 * Excel��������
	 * @return String
	 * @throws Exception
	 */
	@Pageable
	public String excel() throws Exception{

		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		excelBean=new ExcelForReport(excelEdition);
		
		excelBean.setFliename("�߷ü�¼");//���õ���excel����
		excelBean.setHeadtext("�߷ü�¼");//���ñ�ͷ
   
		String[] titletext={"�û���","�߷�����","�߷��û���","�߷�����","�߷ñ�ע","ǩ��ʱ��","�߷�����"};//˫�б�ͷ �ڶ���   û��Ԫ��ռ��1��
		excelBean.setTitletext(titletext);
		String[] datakey={"login_name","visit_type","visit_custom_name","visit_content","visit_note","check_in_time","visit_class"};//���ö�Ӧ���ݼ����е� �������� key
		excelBean.setDatakey(datakey);

		/**���÷����*/
		PageUI p=visitService.query(pagination,param,userInfo);
		
		excelBean.setData(p.getRows());                               //���ö�Ӧ���ݼ���
		excelBean.create();

		/**����excel����*/
		return "excel";
	}
	
	public String downloadfile(){
		Map<String,Object> map = new HashMap<String,Object>();
		map = visitService.downloadfile(param);
		if(map==null){
			jspMsg = new JspMsg();
			jspMsg.setMsg("�ļ��������Ϣ�����ݿ��в����ڣ�����ϵ����Ա");
			JspBtn jp=new JspBtn();
        	jp.setName("����");

        	jp.setUrl("/fileop_downloadinit.action");
        	List<JspBtn> btns = new ArrayList<JspBtn>();
        	btns.add(jp);
        	jspMsg.setBtns(btns);
    		return "MSG";
		}
		file_path = String.valueOf(map.get("img_path"));
		
    	File file=visitService.getFile(file_path);
    	if(file==null){
    		jspMsg=new JspMsg();
    		jspMsg.setMsg("�ļ���������"+file_path);
    		JspBtn jp=new JspBtn();
        	jp.setName("����");

        	jp.setUrl("/fileop_downloadinit.action");
        	List<JspBtn> btns = new ArrayList<JspBtn>();
        	btns.add(jp);
        	jspMsg.setBtns(btns);
    		return "MSG";
    	}
    	try {
			inputStream=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	try {
    		fileName = String.valueOf(map.get("old_img_name"));
			fileName=new String(fileName.getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	return "download";
	}
	@Override
	public Param getModel() {
		// TODO Auto-generated method stub
		return param;
	}

	public VisitService getVisitService() {
		return visitService;
	}

	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}

	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}

	public JspMsg getJspMsg() {
		return jspMsg;
	}

	public void setJspMsg(JspMsg jspMsg) {
		this.jspMsg = jspMsg;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String filePath) {
		file_path = filePath;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


}