package com.tongdatech.sys.pojo;

/**
 * jsp 跳转按钮 <br>
 * 显示名称 url <br>
 * 
 * @author xl
 *
 */
public class JspBtn {
	

	/** String name 显示名称*/
	private String name;
	/** String url 跳转链接*/
	private String url;

	
	/** String iconCls 按钮icon样式*/
	private String iconCls;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param iconCls the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}
	
	

	
	
	

}
