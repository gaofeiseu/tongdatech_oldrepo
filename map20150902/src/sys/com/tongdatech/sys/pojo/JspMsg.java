package com.tongdatech.sys.pojo;

import java.util.List;
import com.tongdatech.sys.base.Msg;



/**
 * Jsp ���ص���Ϣ�Լ���ת��ť����<br>
 * @author xl 
 * @version   2014-2-28 ����05:28:14
 */
public class JspMsg extends Msg{
	
    /** backParam �ش����� <br>
     *  ��������Ϣҳ����ת��ť<br>
     *  e.g. {param1=value1,param2=value2}
     * */
    private Object backParam;
	/** List<JspBtn> btns ��Ϣҳ����ת��ť*/
	private List<JspBtn> btns;
	
	
	public JspMsg() {
		super();
		setSuccess(false);

	}
	/**
	 * @param obj ����Msg����
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
