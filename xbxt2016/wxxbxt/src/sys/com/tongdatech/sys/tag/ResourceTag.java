package com.tongdatech.sys.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class ResourceTag  extends ComponentTagSupport{

	private static final long serialVersionUID = 3539496661090822936L;
	String simple;


	/* (non-Javadoc)
	 * @see org.apache.struts2.views.jsp.ComponentTagSupport#getBean(com.opensymphony.xwork2.util.ValueStack, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new ResourceOfJSAndCSS(stack);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.views.jsp.ComponentTagSupport#populateParams()
	 */
	@Override
	protected void populateParams() {
		((ResourceOfJSAndCSS) getComponent()).setSimple(simple);
	}

	public void setSimple(String simple) {
		this.simple = simple;
	}
}
