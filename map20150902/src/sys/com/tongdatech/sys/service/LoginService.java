package com.tongdatech.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tongdatech.sys.base.BaseService;
import com.tongdatech.sys.bean.Role;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.dao.LoginDao;
import com.tongdatech.sys.pojo.JspMsg;
import com.tongdatech.sys.util.SecurityUtil;

public class LoginService extends BaseService {
	
		private LoginDao loginDao = new LoginDao();
		
		
		
		
		public JspMsg isLogin(String userid, String password,Map<String, Object> session) throws Exception
		{
			JspMsg msg=new JspMsg();
			
			Map<?,?> user = loginDao.getUser(userid);
			Map<String,String> back=new HashMap<String,String>();
			
			if(user==null||user.size()==0){	
				msg.setMsg("用户名不存在!");
				back.put("FieldError","userid");
			}else if(!"1".equals(user.get("user_flag"))){
				msg.setMsg("用户名已被注销!");
				back.put("FieldError","userid");
				
			}else if(!SecurityUtil.md5(password).equals(user.get("password"))){
				msg.setMsg("密码错误!");
				back.put("FieldError","password");
				
			}else{
				msg.setSuccess(true);
				UserInfo userInfo = new UserInfo();
				userInfo.setUser_id(((Double) user.get("user_id")).intValue());
				userInfo.setUser_name((String)user.get("user_name"));
				userInfo.setNick_name((String)user.get("nick_name"));
		 
				userInfo.setLat((String)user.get("lat"));
				userInfo.setLng((String)user.get("lng"));
				userInfo.setBrch_no(String.valueOf(user.get("brch_no")));
				userInfo.setBrch_name(String.valueOf(user.get("brch_name")));
				System.out.println("=====>"+userInfo.toString());
				session.put("userInfo", userInfo);
			    
			}
			msg.setBackParam(back);
			return msg;
			
		}

		public List<Role> getRoles(String username, Map<String, Object> sessionMap) throws Exception {
		
			List<Role> rs = loginDao.getRoles(username);
			return rs;
		}
		public String getLoginLog(UserInfo userInfo) throws Exception{
			loginDao.getLoginLog(userInfo);
			return null;
		}
}


