package com.tongdatech.sys.bean;

import com.tongdatech.sys.inter.TreeTransform;
import com.tongdatech.sys.pojo.TreeNode;

/**
 * 地区对象bean       <br>
 * @author xl 
 * @version   2014-4-10 下午04:47:02
 */
public class Area implements TreeTransform {
	/** String area_no 地区号*/
	private String area_no;
	/** String area_name 地区名称*/
	private String area_name;
	/** String area_parent 上级地区*/
	private String area_parent;
	/** String gov_code 行政区号*/
	private String gov_code;
	/** String area_lvl 地区级别*/
	private String area_lvl;
	/** String area_flag 地区标识*/
	private String area_flag;
	
	/** String check_flag 树选中标识*/
	private String check_flag;
	
	

	/* (non-Javadoc)
	 * @see com.tongdatech.sys.inter.TreeTransform#TreeTrans()
	 */
	@Override
	public TreeNode TreeTrans() {
		TreeNode tn =new TreeNode();
		tn.setId(area_no);
		tn.setText(area_name);
		if("1".equals(check_flag)) tn.setChecked(true);
		return tn;
	}



	/**
	 * @return the area_no
	 */
	public String getArea_no() {
		return area_no;
	}



	/**
	 * @param area_no the area_no to set
	 */
	public void setArea_no(String area_no) {
		this.area_no = area_no;
	}



	/**
	 * @return the area_name
	 */
	public String getArea_name() {
		return area_name;
	}



	/**
	 * @param area_name the area_name to set
	 */
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}



	/**
	 * @return the area_parent
	 */
	public String getArea_parent() {
		return area_parent;
	}



	/**
	 * @param area_parent the area_parent to set
	 */
	public void setArea_parent(String area_parent) {
		this.area_parent = area_parent;
	}



	/**
	 * @return the gov_code
	 */
	public String getGov_code() {
		return gov_code;
	}



	/**
	 * @param gov_code the gov_code to set
	 */
	public void setGov_code(String gov_code) {
		this.gov_code = gov_code;
	}



	/**
	 * @return the area_lvl
	 */
	public String getArea_lvl() {
		return area_lvl;
	}



	/**
	 * @param area_lvl the area_lvl to set
	 */
	public void setArea_lvl(String area_lvl) {
		this.area_lvl = area_lvl;
	}



	/**
	 * @return the area_flag
	 */
	public String getArea_flag() {
		return area_flag;
	}



	/**
	 * @param area_flag the area_flag to set
	 */
	public void setArea_flag(String area_flag) {
		this.area_flag = area_flag;
	}


	/**
	 * @return the check_flag
	 */
	public String getCheck_flag() {
		return check_flag;
	}



	/**
	 * @param check_flag the check_flag to set
	 */
	public void setCheck_flag(String check_flag) {
		this.check_flag = check_flag;
	}



}
