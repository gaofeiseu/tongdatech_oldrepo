package com.tongdatech.sys.pojo;

import com.tongdatech.sys.base.Msg;

/**
 * Ajax 消息返回   <br>
 * @author xl 
 * @version   2014-2-28 下午05:24:42
 */
public class AjaxMsg extends Msg{
	
	/** Object backParam 回传参数*/
	private Object backParam;
	

	public AjaxMsg(){
		super();
		setSuccess(false);
	}
	/**
	 * @param obj 其他Msg对象
	 */
	public AjaxMsg(Msg obj) {
		super(obj);
	}
	/**
	 * @return the backParam
	 */
	public Object getBackParam() {
		return backParam;
	}
	/**
	 * @param backParam the backParam to set
	 */
	public void setBackParam(Object backParam) {
		this.backParam = backParam;
	}

	
	
	
}
