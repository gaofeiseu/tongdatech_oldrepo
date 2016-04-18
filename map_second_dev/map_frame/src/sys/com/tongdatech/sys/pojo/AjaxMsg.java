package com.tongdatech.sys.pojo;

import com.tongdatech.sys.base.Msg;

/**
 * Ajax ��Ϣ����   <br>
 * @author xl 
 * @version   2014-2-28 ����05:24:42
 */
public class AjaxMsg extends Msg{
	
	/** Object backParam �ش�����*/
	private Object backParam;
	

	public AjaxMsg(){
		super();
		setSuccess(false);
	}
	/**
	 * @param obj ����Msg����
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
