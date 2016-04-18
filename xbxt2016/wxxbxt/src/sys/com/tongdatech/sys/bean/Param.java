/**
 * File name:Param.java
 * Create author:XY
 * Create date:2014-3-18
 */
package com.tongdatech.sys.bean;

import com.tongdatech.sys.annotation.PageParam;

/**
 * @author XY
 *
 */
public class Param {
	@PageParam
	private String sn;
	@PageParam
	private String type;
	@PageParam
	private String value;
	@PageParam
	private String text="";
	@PageParam
	private String flag;
	@PageParam
	private int order_id;
	
    
	public String getSn() {
		return sn;
	}


	public void setSn(String sn) {
		this.sn = sn;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}



	/**
	 * @return the order_id
	 */
	public int getOrder_id() {
		return order_id;
	}


	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}


	@Override
	public String toString() {
		return "Param [sn=" + sn + ", type=" + type + ", value=" + value
				+ ", text=" + text + ", flag=" + flag + ", order_id="
				+ order_id + "]";
	}
}