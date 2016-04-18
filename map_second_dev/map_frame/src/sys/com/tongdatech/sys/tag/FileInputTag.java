package com.tongdatech.sys.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class FileInputTag extends ComponentTagSupport {

	/** long serialVersionUID*/
	private static final long serialVersionUID = -8229940771418003492L;
	String name;
    String id;
    String height;
    String width;
    String accept;
    String capture;
    String onchange;


	/* (non-Javadoc)
	 * @see org.apache.struts2.views.jsp.ComponentTagSupport#getBean(com.opensymphony.xwork2.util.ValueStack, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new FileInput(stack);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.views.jsp.ComponentTagSupport#populateParams()
	 */
	@Override
	protected void populateParams() {
		FileInput fileInput = (FileInput) getComponent();
		fileInput.setAccept(accept);
		fileInput.setCapture(capture);
		fileInput.setHeight(height);
		fileInput.setId(id);
		fileInput.setName(name);
		fileInput.setWidth(width);
		fileInput.setOnchange(onchange);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return the accept
	 */
	public String getAccept() {
		return accept;
	}

	/**
	 * @param accept the accept to set
	 */
	public void setAccept(String accept) {
		this.accept = accept;
	}

	/**
	 * @return the capture
	 */
	public String getCapture() {
		return capture;
	}

	/**
	 * @param capture the capture to set
	 */
	public void setCapture(String capture) {
		this.capture = capture;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}


	

}
