package com.tongdatech.sys.service;

import com.tongdatech.sys.bean.Role;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.dao.RoleDao;
import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.sys.pojo.Pagination;

public class RoleService {
	
	private RoleDao roledao = new RoleDao();
	
	public PageUI query(Pagination pagination,Role role,UserInfo userinfo) throws Exception{
		
		return roledao.query(pagination, role,userinfo);
		
	}
	
	public int add(Role role) throws Exception{
		
		return roledao.add(role);
		
	}
	
	public int edit(Role role) throws Exception{
		
		return roledao.edit(role);
		
	}
	
	public int remove(String sns,Role role) throws Exception{
		
		return roledao.remove(sns, role);
	}
	
	public int savemenu(String sns,int role_id) throws Exception{
		
		return roledao.savemenu(sns, role_id);
	}
}
