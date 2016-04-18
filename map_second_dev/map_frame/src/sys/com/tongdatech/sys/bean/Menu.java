package com.tongdatech.sys.bean;

import com.tongdatech.sys.inter.TreeTransform;
import com.tongdatech.sys.pojo.TreeNode;

/**
 * 菜单对象bean      <br>
 * @author xl 
 * @version    2014-4-11 上午10:33:59
 */
public class Menu implements TreeTransform{
	private int menu_id;
	private String menu_name;
	private String menu_url;
	private String menu_flag;
	private int menu_parent;
	private String menu_style;
	private int order_id;
	private String check_flag;
	private String ip_flag;
	



	/* (non-Javadoc)
	 * @see com.tongdatech.sys.inter.TreeTransform#TreeTrans()
	 */
	@Override
	public TreeNode TreeTrans() {
		TreeNode tn =new TreeNode();
		tn.setId(String.valueOf(menu_id));
		tn.setText(menu_name);
		tn.setIconCls(menu_style);
		tn.setObj(this);
		if("1".equals(check_flag)) tn.setChecked(true);
		return tn;
	}
	
	
	/**
	 * @return the menu_id
	 */
	public int getMenu_id() {
		return menu_id;
	}
	/**
	 * @param menu_id the menu_id to set
	 */
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	/**
	 * @return the menu_name
	 */
	public String getMenu_name() {
		return menu_name;
	}
	/**
	 * @param menu_name the menu_name to set
	 */
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	/**
	 * @return the menu_url
	 */
	public String getMenu_url() {
		return menu_url;
	}
	/**
	 * @param menu_url the menu_url to set
	 */
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	/**
	 * @return the menu_flag
	 */
	public String getMenu_flag() {
		return menu_flag;
	}
	/**
	 * @param menu_flag the menu_flag to set
	 */
	public void setMenu_flag(String menu_flag) {
		this.menu_flag = menu_flag;
	}


	/**
	 * @return the order_id
	 */
	public int getOrder_id() {
		return order_id;
	}
	/**
	 * @return the menu_parent
	 */
	public int getMenu_parent() {
		return menu_parent;
	}


	/**
	 * @param menu_parent the menu_parent to set
	 */
	public void setMenu_parent(int menu_parent) {
		this.menu_parent = menu_parent;
	}


	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	/**
	 * @return the menu_style
	 */
	public String getMenu_style() {
		return menu_style;
	}


	/**
	 * @param menu_style the menu_style to set
	 */
	public void setMenu_style(String menu_style) {
		this.menu_style = menu_style;
	}


	public String getCheck_flag() {
		return check_flag;
	}


	public void setCheck_flag(String checkFlag) {
		check_flag = checkFlag;
	}


	public String getIp_flag() {
		return ip_flag;
	}


	public void setIp_flag(String ipFlag) {
		ip_flag = ipFlag;
	}
	
	

}
