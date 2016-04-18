package com.tongdatech.sys.bean;

import java.util.HashMap;
import java.util.Map;

import com.tongdatech.sys.inter.TreeTransform;
import com.tongdatech.sys.pojo.TreeNode;


/**
 * 机构对象bean   <br>
 * @author xl 
 * @version   2014-4-10 下午04:51:24
 *        <br>
 */
public class Brch implements TreeTransform{

	/** int brch_no*/
	private String  brch_no;     
	/** int brch_up*/
	private String  brch_up;    
	/** String brch_name*/
	private String  brch_name;   
	/** String brch_code*/
	private String  brch_code = "";  
	/** String brch_mode*/
	private String  brch_mode; 
	/** String area_no*/
	private String  area_no;  
	/** String brch_flag*/
	private String  brch_flag;   
	/** int order_id*/
	private int     order_id;  
	/** String spell_short*/
	private String  spell_short; 
	/** String spell_full*/
	private String  spell_full;
	
	/**<p>冗余字段*/
	
	/** String brch_up_name*/
	private String  brch_up_name;
	/** String area_name*/
	private String  area_name;
	
	
	/* (non-Javadoc)
	 * @see com.tongdatech.sys.inter.TreeTransform#TreeTrans()
	 */
	@Override
	public TreeNode TreeTrans() {
		TreeNode tn =new TreeNode();
		tn.setId(String.valueOf(brch_no));
		tn.setText(brch_name);
		Map<String,Object> atr = new HashMap<String,Object>();
		atr.put("area_no", area_no);
		atr.put("brch_mode", brch_mode);
		atr.put("brch_code", brch_code);
		tn.setAttributes(atr);
		//tn.setObj(this);
		return tn;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Brch [brch_no=" + brch_no + ", brch_up=" + brch_up
				+ ", brch_name=" + brch_name + ", brch_code=" + brch_code
				+ ", brch_mode=" + brch_mode + ", area_no=" + area_no
				+ ", brch_flag=" + brch_flag + ", order_id=" + order_id
				+ ", spell_short=" + spell_short + ", spell_full=" + spell_full
				+ "]";
	}



 
	public String getBrch_no() {
		return brch_no;
	}



	public void setBrch_no(String brchNo) {
		brch_no = brchNo;
	}



	public String getBrch_up() {
		return brch_up;
	}



	public void setBrch_up(String brchUp) {
		brch_up = brchUp;
	}



	/**
	 * @return the brch_name
	 */
	public String getBrch_name() {
		return brch_name;
	}
	/**
	 * @param brch_name the brch_name to set
	 */
	public void setBrch_name(String brch_name) {
		this.brch_name = brch_name;
	}
	/**
	 * @return the brch_code
	 */
	public String getBrch_code() {
		return brch_code;
	}
	/**
	 * @param brch_code the brch_code to set
	 */
	public void setBrch_code(String brch_code) {
		this.brch_code = brch_code;
	}
	/**
	 * @return the brch_mode
	 */
	public String getBrch_mode() {
		return brch_mode;
	}
	/**
	 * @param brch_mode the brch_mode to set
	 */
	public void setBrch_mode(String brch_mode) {
		this.brch_mode = brch_mode;
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
	 * @return the brch_flag
	 */
	public String getBrch_flag() {
		return brch_flag;
	}
	/**
	 * @param brch_flag the brch_flag to set
	 */
	public void setBrch_flag(String brch_flag) {
		this.brch_flag = brch_flag;
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
	/**
	 * @return the spell_short
	 */
	public String getSpell_short() {
		return spell_short;
	}
	/**
	 * @param spell_short the spell_short to set
	 */
	public void setSpell_short(String spell_short) {
		this.spell_short = spell_short;
	}
	/**
	 * @return the spell_full
	 */
	public String getSpell_full() {
		return spell_full;
	}
	/**
	 * @param spell_full the spell_full to set
	 */
	public void setSpell_full(String spell_full) {
		this.spell_full = spell_full;
	}



	/**
	 * @return the brch_up_name
	 */
	public String getBrch_up_name() {
		return brch_up_name;
	}



	/**
	 * @param brch_up_name the brch_up_name to set
	 */
	public void setBrch_up_name(String brch_up_name) {
		this.brch_up_name = brch_up_name;
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





	
	

}
