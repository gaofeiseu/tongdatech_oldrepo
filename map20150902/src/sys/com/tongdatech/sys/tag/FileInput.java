package com.tongdatech.sys.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class FileInput extends Component {
	private static Logger log =  Logger.getLogger(FileInput.class);


    String name;
    String id;
    String height;
    String width;
    String accept;
    String capture;
    String onchange;
    

	public FileInput(ValueStack stack) {
		super(stack);
	
	}
	
    @Override
	public boolean start(Writer writer) {
        try {
        	writer.write(startHtml());		
		} catch (IOException e) {
			log.error("TAG",e);
		}     
        return true;
    }


    

    private String startHtml(){
    	String br="\n";
    	StringBuffer sb = new StringBuffer();
    	
    	if(height==null||"".equals(height)){
    		height="20px";
    	}
    	if(width==null||"".equals(width)){
    		width="150px";
    	}
    	if(id==null||"".equals(id)){
    		id=UUID.randomUUID().toString();
    	}
    	if(accept==null||"".equals(accept)){
    		accept="";
    	}else{
    		accept=" accept=\""+accept+"\" ";
    	}
    	if(capture==null||"".equals(capture)){
    		capture="";
    	}else{
    		capture=" capture ";
    	}
    	if(onchange==null||"".equals(onchange)){
    		onchange="onchange=\"";
    	}else{
    		onchange=" onchange=\""+onchange+",";
    	}
    	String show_id="file_show_"+id;
        
        
        sb.append("<span class=\"file_input\">");sb.append(br);
        sb.append("<input type=\"text\" id=\""+show_id+"\" class=\"file_show\"   readonly=\"readonly\"  style=\"height:"+height+";width:"+width+"\">");sb.append(br);
        sb.append("&nbsp;<a style=\"height:"+height+";width:"+height+"\" class=\"file_link\"> ");sb.append(br);
        //sb.append("<span class=\"  icon-save\">");sb.append(br);
        sb.append("<input type=\"file\" class=\"file_real\"  id=\""+id+"\" "+accept+" "+capture+" ");sb.append(br);
        sb.append("name=\""+name+"\" style=\"height:"+height+";width:120px;margin-left: "+TagUtil.unitAdd(height,-120)+"; \" ");sb.append(br);
        //sb.append("cursor: pointer;position: relative; filter: Alpha(opacity=0);-moz-opacity:0;opacity: 0; \" ");sb.append(br);
        //sb.append("onchange=\"document.getElementById('"+show_id+"').value=this.value;\">");sb.append(br);
        sb.append(onchange);
        sb.append("$(this).parents('.file_input').find('.file_show').val(this.value);\" >");
        sb.append(br);
        //sb.append("</span>");sb.append(br); 
        sb.append("</a>");sb.append(br);
        sb.append("</span>");sb.append(br);      
 
        String s=sb.toString();
		return sb.toString();
    }

    
	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public static void setLog(Logger log) {
		FileInput.log = log;
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
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
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

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}






}
