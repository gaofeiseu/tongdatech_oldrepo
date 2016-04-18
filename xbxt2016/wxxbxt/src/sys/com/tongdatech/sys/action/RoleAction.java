package com.tongdatech.sys.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.bean.Role;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.AjaxMsg;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.service.RoleService;
import com.tongdatech.sys.util.JsonUtil;


public class RoleAction extends PaginationAction implements ModelDriven<Role>{
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger log =  Logger.getLogger(RoleAction.class);
	private Role role = new Role();
	RoleService roleservice = new RoleService();
	
	@Override
	public Role getModel(){
		return role;
	}
	

	
	public RoleService getRoleservice() {
		return roleservice;
	}

	public void setRoleservice(RoleService roleservice) {
		this.roleservice = roleservice;
	}

	public String init(){
		return "init";
	}
	
	@Pageable
	public void query() throws Exception{
		//log.info("HI~ACITON~query~");
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI pageui = roleservice.query(pagination, role,userInfo);	//调用服务层
		JsonUtil.ToJson(pageui, response);	//返回JSON字符串
	}

	public void add() throws Exception{
		//log.info("HI~ACTION~add~" + role.getRole_name());
		AjaxMsg am = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();
		int r = roleservice.add(role);
		if(r>0){
			am.setSuccess(true);
			am.setMsg("角色新增成功!");
		}else{
			am.setSuccess(false);
			am.setMsg("角色新增失败!");
		}
		
		JsonUtil.ToJson(am,response);
	}

	public void edit() throws Exception{
		AjaxMsg am = new AjaxMsg();
		HttpServletResponse response = ServletActionContext.getResponse();
		//log.info("HI~ACTION~edit~" + role.getRole_id());
		int r = roleservice.edit(role);
		if(r>=0){
			am.setSuccess(true);
			am.setMsg("角色修改成功!");
		}else{
			am.setSuccess(false);
			am.setMsg("角色修改失败!");
		}
		
		JsonUtil.ToJson(am,response);
	}
	
	public void remove() throws Exception{
		AjaxMsg am = new AjaxMsg();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String sns = request.getParameter("sns");
		//log.info("~~remove~~" +sns);
		
		int r = roleservice.remove(sns, role);
		if(r>=0){
			am.setSuccess(true);
			am.setMsg("角色删除成功!");
		}else{
			am.setSuccess(false);
			am.setMsg("角色删除失败!");
		}
		
		JsonUtil.ToJson(am,response);
	}
	
	//模块授权
	public void savemenu() throws Exception{
		AjaxMsg am = new AjaxMsg();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String sns = request.getParameter("sns");
		int role_id = Integer.parseInt(request.getParameter("role_id"));
		//log.info("~~savemenu~~" + sns + "~~role_id=" + role_id);
		int r = roleservice.savemenu(sns,role_id);
		//log.info("~~rA~~" + r);
		if(r>0){
			am.setSuccess(true);
			am.setMsg("模块授权成功!");
		}else{
			am.setSuccess(false);
			am.setMsg("模块授权失败!");
		}
		
		JsonUtil.ToJson(am,response);
	}
	
	/**
	 * UICombo数据
	 * @return String
	 * @throws Exception
	 */
	@Pageable
	public String combo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> session = ActionContext.getContext().getSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.USER_INFO);
		PageUI pageui = roleservice.query(pagination, role,userInfo);	
		JsonUtil.ToJsonExclude(pageui.getRows(), response);	
		return null;
	}
}
