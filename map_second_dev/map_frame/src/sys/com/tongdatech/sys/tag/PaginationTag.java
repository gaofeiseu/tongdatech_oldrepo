package com.tongdatech.sys.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class PaginationTag  extends ComponentTagSupport{

	
	
	private static final long serialVersionUID = 2632958998231091130L;
	String style;
	String value;
	String pageList;
	String buttons;
	String layout;
	String excel_url;


	/* (non-Javadoc)
	 * @see org.apache.struts2.views.jsp.ComponentTagSupport#getBean(com.opensymphony.xwork2.util.ValueStack, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new PaginationObj(stack);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.views.jsp.ComponentTagSupport#populateParams()
	 */
	@Override
	protected void populateParams() {
		PaginationObj page = (PaginationObj) getComponent();
		page.setStyle(style);
		page.setValue(value);
		page.setLayout(layout);
		page.setPageList(pageList);
		page.setButtons(buttons);
		page.setExcel_url(excel_url);
		
		
  }
		
	

	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param pageList the pageList to set
	 */
	public void setPageList(String pageList) {
		this.pageList = pageList;
	}

	/**
	 * @param buttons the buttons to set
	 */
	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

	/**
	 * @param layout the layout to set
	 */
	public void setLayout(String layout) {
		this.layout = layout;
	}

	/**
	 * @param excel_url the excel_url to set
	 */
	public void setExcel_url(String excel_url) {
		this.excel_url = excel_url;
	}

	

	
}
