package com.tongdatech.map_mobile.bean;

import java.util.List;



/**
 * Jsp 返回的信息以及跳转按钮配置<br>
 * @author xl 
 * @version   2014-2-28 下午05:28:14
 */
public class JspMsg extends Msg{
	
    /** backParam 回传参数 <br>
     *  可用于消息页面跳转按钮<br>
     *  e.g. {param1=value1,param2=value2}
     * */
    private Object backParam;
	/** List<JspBtn> btns 消息页面跳转按钮*/
	private List<JspBtn> btns;
	
	
	public JspMsg() {
		super();
		setSuccess(false);

	}
	/**
	 * @param obj 其他Msg对象
	 */
	public JspMsg(Msg obj) {
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
	/**
	 * @return the btns
	 */
	public List<JspBtn> getBtns() {
		return btns;
	}
	/**
	 * @param btns the btns to set
	 */
	public void setBtns(List<JspBtn> btns) {
		this.btns = btns;
	}




	
	

}
