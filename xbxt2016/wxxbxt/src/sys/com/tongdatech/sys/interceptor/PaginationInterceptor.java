package com.tongdatech.sys.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tongdatech.sys.annotation.Pageable;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.util.ReflectionUtil;


/**
 * ��ҳ������ <br>
 * �̳�PaginationAction  @see com.tongdatech.sys.base.PaginationAction<br>
 * ӵ��Pageable��ע @see com.tongdatech.sys.annotation.Pageable<br>
 * �ķ��������Զ���ҳ
 *  
 * @author xl 
 * @version    2014-4-11 ����10:41:06
 */
public class PaginationInterceptor extends AbstractInterceptor {


	private static final long serialVersionUID = 3658250753683227457L;
	//private static String pageable="com.tongdatech.sys.annotation.Pageable";
	private static String pageable=Pageable.class.getName();
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();
	    Class<? extends Object> clazz = action.getClass();
	   
	    String name = invocation.getProxy().getMethod();
	    if(PaginationAction.class.isAssignableFrom(clazz)
	    		&&ReflectionUtil.isAnnotation(clazz,pageable,name)){
	    	((PaginationAction)action).prePaging();
	    }
		return invocation.invoke();
	}

}
