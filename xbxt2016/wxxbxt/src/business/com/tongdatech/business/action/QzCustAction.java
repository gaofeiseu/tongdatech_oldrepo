package com.tongdatech.business.action;


 
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.business.bean.QzCustBean;
import com.tongdatech.business.service.QzCustService;
import com.tongdatech.map_mobile.bean.JspMsg;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.service.MenuIpService;
import com.tongdatech.sys.util.JsonUtil;

public class QzCustAction  extends PaginationAction  implements  ModelDriven<QzCustBean>
{

	private static final long serialVersionUID = -12287761761261387L;

	private QzCustBean qzCustBean = new QzCustBean();
	
	QzCustService qzCustService =new QzCustService();
	private MenuIpService menuIpService =new MenuIpService();

	private JspMsg  jspMsg;

 
	/**
	 * 资产查询
	 * @return String
	 * @throws Exception 
	 */
	public String init() throws Exception{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		
//		boolean flag=menuIpService.getMenuIpNum("411", request);
//		if(flag)
//		{
			return "init";
//		}
//	 
//		return "ip_err";
	}
	 

	/**
	 * 潜在客户查询列表
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String query() throws Exception{
  
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = qzCustService.qzCust(pagination,qzCustBean,userInfo);
		JsonUtil.ToJson(rs,response);
		return null;
	}
	
	
	
 
	public QzCustBean getQzCustBean() {
		return qzCustBean;
	}


	public void setQzCustBean(QzCustBean qzCustBean) {
		this.qzCustBean = qzCustBean;
	}


	public QzCustService getQzCustService() {
		return qzCustService;
	}


	public void setQzCustService(QzCustService qzCustService) {
		this.qzCustService = qzCustService;
	}


	public MenuIpService getMenuIpService() {
		return menuIpService;
	}


	public void setMenuIpService(MenuIpService menuIpService) {
		this.menuIpService = menuIpService;
	}


	public JspMsg getJspMsg() {
		return jspMsg;
	}

	public void setJspMsg(JspMsg jspMsg) {
		this.jspMsg = jspMsg;
	}


	@Override
	public QzCustBean getModel() {
		// TODO Auto-generated method stub
		return qzCustBean;
	}

	

}