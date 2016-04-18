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
	 * 资产查询
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
	 * 资产查询列表
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
	 * Excel导出功能
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
		excelBean.setFliename("客户资产明细");//设置导出excel名称
		excelBean.setHeadtext("客户资产明细");//设置表头
   
		String[] titletext={
				"客户姓名",
				"客户经理",
			    "资产等级",
				"身份证",
				"手机",
				"座机",
				"定期",
				"活期",
				"理财",
				"保险",
				"国债",
				"总资产",
			
				"性别",
				"年龄",
			    "渠道维度总和",
				"代扣电费",
			    "代扣数字电视费",
				"代扣水费",
				"代扣燃气费",
				"代扣医疗保险",
				"代扣电信通讯费",
				"代扣移动手机费",
				"代发工资",
				"网银",
				"网点机构号",
				"网点名称",
			    "统计年月",
				"非营标识"
		};//双行表头 第二行   没个元素占用1列
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
				};//设置对应数据集合中的 数据名称 key
		excelBean.setDatakey(datakey);

		/**调用服务层*/
		PageUI p=custZcService.query(pagination,custZcBean,userInfo);
		
		excelBean.setData(p.getRows());                               //设置对应数据集合
		excelBean.create();

		/**调用excel导出*/
		return "excel";
	}
	
	/**
	 * 资产查询列表
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
	 * 资产查询明细init
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
			am.setMsg("客户经理关联成功!");
		}else{
			am.setSuccess(false);
			am.setMsg("客户经理关联失败!");
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