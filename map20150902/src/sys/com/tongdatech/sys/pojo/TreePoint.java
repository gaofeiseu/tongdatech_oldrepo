package com.tongdatech.sys.pojo;

/**
 * easyUI �϶�λ�� ö��   <br>
 * @author xl 
 * @version   2014-4-2 ����02:09:52
 */
public enum TreePoint {
	append("append"),
	top("top"),
	bottom("bottom");
	
	/** String value*/
	private String value;

	/**
	 * @param value
	 */
	private TreePoint(String value) {
		this.setValue(value);
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
}
