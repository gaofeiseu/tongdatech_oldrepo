package com.tongdatech.sys.util;

import com.opensymphony.xwork2.util.TextParseUtil;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * ϵͳ�������ù�����       <br>
 * @author xl 
 * @version    2014-4-11 ����11:21:19
 */
public class ParamsUtil {

	public final static String ParamHome="application";
	public final static String ParamSys="ParamSys";
	public final static String ParamJson="ParamJson";

	private ValueStack valueStack;
	
	/**
	 * @param valueStack  Struts2 ֵջ
	 */
	public ParamsUtil(ValueStack valueStack) {
		this.valueStack=valueStack;
	}
	
	/**
	 * ��ȡϵͳ������ӦKEY��VALUEֵ
	 * @param paramName ��������
	 * @param paramKey  ����KEYֵ
	 * @return  ����VALUEֵ
	 */
	public String getValue(String paramName, String paramKey) {
		String el="#"+ParamHome+"."+ParamSys+"."+paramName+"[\""+paramKey+"\"]";
	    String rs=valueStack.findString(el);
	     
	    if(rs == null) return "";
	    else return rs;
	}
	
	
	/**
     * Converts all instances of ${...} in <code>expression</code> to the value returned
     * by a call to {@link ValueStack#findValue(java.lang.String)}. If an item cannot
     * be found on the stack (null is returned), then the entire variable ${...} is not
     * displayed, just as if the item was on the stack but returned an empty string.
     *
     * @param expression
     * @return Translated variable String
     */
    public  String translateVariables (String expression) {
        return TextParseUtil.translateVariables(expression, valueStack , null);
    }

}
