package com.tongdatech.business.action;


 
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.business.bean.CustZcBean;
import com.tongdatech.business.service.CustZcService;
import com.tongdatech.map_mobile.bean.JspMsg;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.service.MenuIpService;
import com.tongdatech.sys.util.JsonUtil;
import com.tongdatech.sys.util.excel.ExcelForReport;

public class CustZcAction  extends PaginationAction  implements  ModelDriven<CustZcBean>
{

	private static final long serialVersionUID = -12287761761261387L;

	private CustZcBean custZcBean = new CustZcBean();
	
	CustZcService custZcService =new CustZcService();
	private MenuIpService menuIpService =new MenuIpService();
	  
	private JspMsg  jspMsg;

 
	/**
	 * �ʲ���ѯ
	 * @return String
	 * @throws Exception 
	 */
	public String init() throws Exception{
//		HttpServletRequest request = ServletActionContext.getRequest();
//		
//		boolean flag=menuIpService.getMenuIpNum("350", request);
//		if(flag)
//		{
			return "init";
//		}
//		return "ip_err";
	}
	
	/**
	 * �ʲ���ѯ�б�
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String query() throws Exception{
		Calendar now = Calendar.getInstance();  
	    int year=  now.get(Calendar.YEAR);  
	    int month=now.get(Calendar.MONTH)  ;  
	    if (custZcBean.getJiesuan_date() == null || custZcBean.getJiesuan_date().length() == 0) {
	    	custZcBean.setJiesuan_date(year+(month<10?"0":"")+month);
		}    
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI rs = custZcService.query(pagination,custZcBean,userInfo);
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
		Calendar now = Calendar.getInstance();  
	    int year=  now.get(Calendar.YEAR);  
	    int month=now.get(Calendar.MONTH)  ;  
	    if (custZcBean.getJiesuan_date() == null || custZcBean.getJiesuan_date().length() == 0) {
	    	custZcBean.setJiesuan_date(year+(month<10?"0":"")+month);
		}   
		excelBean.setFliename("�ͻ��ʲ���ϸ");//���õ���excel����
		excelBean.setHeadtext("�ͻ��ʲ���ϸ");//���ñ�ͷ
   
		String[] titletext={
				"�ͻ�����",
				"�ͻ�����",
			    "�ʲ��ȼ�",
				"���֤",
				"�ֻ�",
				"����",
				"����",
				"����",
				"���",
				"����",
				"��ծ",
				"���ʲ�",
			
				"�Ա�",
				"����",
			    "����ά���ܺ�",
				"���۵��",
			    "�������ֵ��ӷ�",
				"����ˮ��",
				"����ȼ����",
				"����ҽ�Ʊ���",
				"���۵���ͨѶ��",
				"�����ƶ��ֻ���",
				"��������",
				"����",
				"���������",
				"��������",
			    "ͳ������",
				"��Ӫ��ʶ"
		};//˫�б�ͷ �ڶ���   û��Ԫ��ռ��1��
		excelBean.setTitletext(titletext);
		String[] datakey={
				"custname",
				"manager_name",
				"zichan_grade",
			
				"pass_no",
		
				"sj",
				"dh",
				"dingqi_value",
				"huoqi_value",
				"licai",
				"baofei",
				"guozhai",
				"zichan_all",
			
				"sex",
				"age",
				"qudao_weidu_sum",
				"dk_power",
				"dk_tv",
				"dk_water",
				"dk_gas",
				"dk_med",
				"dk_telec",
				"dk_mphone",
				"df",
				"netbank",
				"open_office_code",
				"brch_name",
				"jiesuan_date",
				"feiying_tag"
				};//���ö�Ӧ���ݼ����е� �������� key
		excelBean.setDatakey(datakey);

		/**���÷����*/
		PageUI p=custZcService.query(pagination,custZcBean,userInfo);
		
		excelBean.setData(p.getRows());                               //���ö�Ӧ���ݼ���
		excelBean.create();

		/**����excel����*/
		return "excel";
	}
	
	/**
	 * �ʲ���ѯ�б�
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String queryManager() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		List<Map> rs = custZcService.queryManager(userInfo);
		JsonUtil.ToJson(rs,response);
		return null;
	}
	/**
	 * �ʲ���ѯ��ϸinit
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String mx_init() throws Exception{
 
		return "mx_init";
	}
	public void save() throws Exception{
		//log.info("HI~ACTION~add~" + role.getRole_name());
		AjaxMsg am = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		int r = custZcService.save(custZcBean,userInfo);
		if(r>0){
			am.setSuccess(true);
			am.setMsg("�ͻ���������ɹ�!");
		}else{
			am.setSuccess(false);
			am.setMsg("�ͻ��������ʧ��!");
		}
		
		JsonUtil.ToJson(am,response);
	}
	/**
	 * 	Dingqi
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String Dingqi() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI  rs = custZcService.queryDingqi(pagination,custZcBean.getCust_p_id());
		JsonUtil.ToJson(rs,response);
		return null;

	}

	/**
	 * 	Yibentong
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String Yibentong() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI  rs = custZcService.queryYibentong(pagination,custZcBean.getCust_p_id());
		JsonUtil.ToJson(rs,response);
		return null;

	}
	/**
	 * 	Huoqi
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String Huoqi() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI  rs = custZcService.queryHuoqi(pagination,custZcBean.getCust_p_id());
		JsonUtil.ToJson(rs,response);
		return null;

	}
	/**
	 * 	BaoXian
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String BaoXian() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI  rs = custZcService.queryBaoXian(pagination,custZcBean.getCust_p_id());
		JsonUtil.ToJson(rs,response);
		return null;

	}
	/**
	 * 	Licai
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String Licai() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI  rs = custZcService.queryLicai(pagination,custZcBean.getCust_p_id());
		JsonUtil.ToJson(rs,response);
		return null;

	}
	
	/**
	 * 	Cxguozhai
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String Cxguozhai() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI  rs = custZcService.queryCxguozhai(pagination,custZcBean.getCust_p_id());
		JsonUtil.ToJson(rs,response);
		return null;

	}
	
	
	/**
	 * 	Pzguozhai
	 * @return String
	 * @throws Exception 
	 */
	@Pageable
	public String Pzguozhai() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI  rs = custZcService.queryPzguozhai(pagination,custZcBean.getCust_p_id());
		JsonUtil.ToJson(rs,response);
		return null;

	}

 
	
	
	@Override
	public CustZcBean getModel() {
		// TODO Auto-generated method stub
		return custZcBean;
	}

 

	public CustZcService getCustZcService() {
		return custZcService;
	}

	public void setCustZcService(CustZcService custZcService) {
		this.custZcService = custZcService;
	}

 

	public MenuIpService getMenuIpService() {
		return menuIpService;
	}

	public void setMenuIpService(MenuIpService menuIpService) {
		this.menuIpService = menuIpService;
	}

	public CustZcBean getCustZcBean() {
		return custZcBean;
	}

	public void setCustZcBean(CustZcBean custZcBean) {
		this.custZcBean = custZcBean;
	}

	public JspMsg getJspMsg() {
		return jspMsg;
	}

	public void setJspMsg(JspMsg jspMsg) {
		this.jspMsg = jspMsg;
	}

	

}