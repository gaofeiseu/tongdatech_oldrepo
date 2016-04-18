package com.tongdatech.tools.dataimport.action;

import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tongdatech.sys.pojo.JspMsg;
import com.tongdatech.tools.dataimport.ImportContext;
import com.tongdatech.tools.dataimport.ImportManger;
import com.tongdatech.tools.dataimport.config.BeanConfig;

public class DemoImportAction  extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -25980672710961L;
	
	private String KEY="import_context";
	private String name;
	private ImportContext context;
	private JspMsg jspMsg;
	
	/**
	 * ��ʼ������context����session
	 * @return
	 * @throws Exception
	 */
	public String init() throws Exception{
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		BeanConfig beanConfig=ImportManger.getType(name);
		//HttpServletRequest request = ServletActionContext.getRequest();
		if(beanConfig!=null){
			context=new ImportContext(beanConfig);
			session.put(KEY, context);
			//request.setAttribute(KEY, context);
			return "init";
		}else{
			jspMsg.setMsg("δ�ҵ���Ϊ"+name+"�ĵ�������");
			return "MSG";
		}
		
	}

	public String getKEY() {
		return KEY;
	}

	public void setKEY(String kEY) {
		KEY = kEY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImportContext getContext() {
		return context;
	}

	public void setContext(ImportContext context) {
		this.context = context;
	}

	public JspMsg getJspMsg() {
		return jspMsg;
	}

	public void setJspMsg(JspMsg jspMsg) {
		this.jspMsg = jspMsg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	



	
	
	
	
	
	

}
