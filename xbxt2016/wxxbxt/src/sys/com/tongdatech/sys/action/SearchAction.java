package com.tongdatech.sys.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.service.SearchService;
import com.tongdatech.sys.util.JsonUtil;

/**
 * 搜索Action      <br>
 * @author xl 
 * @version    2014-4-11 上午09:48:20
 */
public class SearchAction extends ActionSupport{


	private static final long serialVersionUID = 5844852268708112057L;
	/** String q easyUI Combo等动态查询参数*/
	private String q;

	private SearchService  searchService  = new SearchService();
	
	/**
	 * 机构搜索框
	 * @return String
	 * @throws Exception
	 */
	public String brch() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		List<?> rs =searchService.brch(q,userInfo);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	/**
	 * 菜单搜索框
	 * @return String
	 * @throws Exception
	 */
	public String menu() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo)session.get(UserInfo.USER_INFO);
		List<?> rs =searchService.menu(q,userInfo);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs,response);
		return null;
	}
	/**
	 * 地区搜索框
	 * @author cly
	 * @return String
	 * @throws Exception
	 */
	public String area() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userinfo = (UserInfo)session.get(UserInfo.USER_INFO);
//		if(q!=null&&!"".equals(q.trim())){
//			System.out.println("默认字符："+q);
//			System.out.println("(ISO-8859-1)==>(GBK)："+new String(q.getBytes("ISO-8859-1"),"GBK"));
//			System.out.println("(UTF-8)==>(GBK)："+new String(q.getBytes("UTF-8"),"GBK"));
//		}
		List<?> rs = searchService.area(q, userinfo);
		HttpServletResponse response = ServletActionContext.getResponse();
    	JsonUtil.ToJson(rs,response);
    	return null;
	}
	
	

	
	/**
	 * @return the q
	 */
	public String getQ() {
		return q;
	}

	/**
	 * @param q the q to set
	 */
	public void setQ(String q) {
		this.q = q;
	}
}
