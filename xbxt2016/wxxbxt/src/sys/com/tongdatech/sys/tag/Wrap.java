package com.tongdatech.sys.tag;

import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class Wrap extends Component {
	private static Logger log =  Logger.getLogger(Wrap.class);
	public static final String ANSWER = "struts.wrap.answer";
	Boolean answer;
    //String isTabs;
    String title;
    
    String height;
    

	public Wrap(ValueStack stack, boolean istabs) {
		super(stack);
		this.answer=istabs;
	}
	
    @Override
	public boolean start(Writer writer) {
//        answer = (Boolean) findValue(isTabs, Boolean.class);
//
//        if (answer == null) {
//            answer = Boolean.FALSE;
//        }
//        stack.getContext().put(ANSWER, answer);
        
//        if(answer.booleanValue())return false;
        
    	String tmpTitle=(String)findValue(title, String.class);
    	if(tmpTitle!=null&&!"".equals(tmpTitle)){
    		title=tmpTitle;
    	}
        try {
        	if(!answer.booleanValue()){
        		writer.write(startHtml());
        	}		
		} catch (IOException e) {
			log.error("TAG",e);
		} 
        
        return true;
    }


    
    @Override
	public boolean end(Writer writer, String body) {
        stack.getContext().put(ANSWER, answer);
        boolean rs = super.end(writer, body);
        try {
        	if(!answer.booleanValue()){
        		writer.write(endHtml());
        	}
			
		} catch (IOException e) {
			log.error("TAG",e);
		} 
        return rs;
    }
    private String startHtml(){
    	String br="\n";
    	StringBuffer sb = new StringBuffer();
    	if(height!=null&&!"".equals(height)){
    		sb.append("<table class=\"comWrapTab\" height=\""+height+"\">");sb.append(br);
    	}else{
    		sb.append("<table class=\"comWrapTab\" height=\"100%\">");sb.append(br);
    	}
        
        
        sb.append("<tr height=\"29px\">");sb.append(br);
        sb.append("<td id=\"left_head\"></td>");sb.append(br);
        sb.append("<td id=\"mid_head\">");sb.append(br);
        
        if(title!=null&&!"".equals(title)){
        	sb.append("<ul>");sb.append(br);
            sb.append("<li id=\"l_title\"></li>");sb.append(br);
            sb.append("<li id=\"m_title\">"+title+"</li>");sb.append(br);
            sb.append("<li id=\"r_title\"></li>");sb.append(br);
            sb.append("</ul>");sb.append(br);
        }
        
        
        sb.append("</td>");sb.append(br);      
        sb.append("<td id=\"right_head\"></td>");sb.append(br);
        sb.append("</tr>");sb.append(br);
        
        sb.append("<tr>");sb.append(br);
        sb.append("<td id=\"left_body\"></td>");sb.append(br);
        sb.append("<td id=\"mid_body\">");sb.append(br);
        sb.append("<div id=\"WrapDiv\">");sb.append(br);

		return sb.toString();
    }
    private String endHtml(){
    	String br="\n";
    	StringBuffer sb = new StringBuffer();
     
    	sb.append("</div>");sb.append(br);  
        sb.append("</td>");sb.append(br);      
        sb.append("<td id=\"right_body\"></td>");sb.append(br);
        sb.append("</tr>");sb.append(br);
        
        sb.append("<tr height=\"17px\">");sb.append(br);
        sb.append("<td id=\"left_buttom\"></td>");sb.append(br);
        sb.append("<td id=\"mid_buttom\"></td>");sb.append(br);
        sb.append("<td id=\"right_buttom\"></td>");sb.append(br);
        sb.append("</tr>");sb.append(br);
        
        sb.append("</table>");sb.append(br);

		return sb.toString();
    }

//	public void setIsTabs(String isTabs) {
//		this.isTabs = isTabs;
//	}

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
