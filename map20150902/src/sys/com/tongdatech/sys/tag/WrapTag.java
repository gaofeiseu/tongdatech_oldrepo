package com.tongdatech.sys.tag;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class WrapTag extends ComponentTagSupport {

	String title;
	String height;
	private static final long serialVersionUID = -3643004952430421087L;

	/* (non-Javadoc)
	 * @see org.apache.struts2.views.jsp.ComponentTagSupport#getBean(com.opensymphony.xwork2.util.ValueStack, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		
		ServletContext sc = req.getSession().getServletContext();
		boolean istabs = (Boolean)(sc.getAttribute("isTabs"));
		return new Wrap(stack,istabs);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.views.jsp.ComponentTagSupport#populateParams()
	 */
	@Override
	protected void populateParams() {
		Wrap wrap = (Wrap) getComponent();
		wrap.setTitle(title);
		wrap.setHeight(height);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	

}
