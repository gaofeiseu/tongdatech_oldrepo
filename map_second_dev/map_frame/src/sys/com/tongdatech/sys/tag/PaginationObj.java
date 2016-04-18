package com.tongdatech.sys.tag;

import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;
import com.tongdatech.sys.pojo.Pagination;

public class PaginationObj extends Component {

	public static final String ANSWER = "struts.paginationObj.answer";
	private static Logger log =  Logger.getLogger(PaginationObj.class);
	private static final String br="\n";
	Pagination page;
	
	String style;
	String value;
	String pageList;
	String buttons;
	String layout;
	String excel_url;

	public PaginationObj(ValueStack stack) {
		super(stack);
	}
	
	
	@Override
	public boolean start(Writer writer) {
        page = (Pagination) findValue(value, Pagination.class);
        stack.getContext().put(ANSWER, page);
        
        try {
			writer.write(pageHtml());
		} catch (IOException e) {
			log.error("TAG",e);
		}
	

		return super.start(writer);
	}

    private String pageHtml(){
    	int rowsSt=(page.getPage()-1)*page.getRows()+1;
		int rowsEd=(page.getPage())*page.getRows();
		int totalPage=(int) Math.ceil((double)page.getTotal()/page.getRows());
		
		boolean excel_flag=false;

    	
    	
    	StringBuffer sb = new StringBuffer();
    	sb.append("<div class=\"datagrid-pager pagination\" style=\""+style+"\">");sb.append(br);
    	sb.append("<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\">");sb.append(br);
    	sb.append("<tbody><tr>");sb.append(br);
    	
    	sb.append(showPageList());
    	
    	sb.append(showSeparator());
    	sb.append("<td><a href=\""+page.getUri()+"?rows="+page.getRows()+"&page="+1+page.getURLParam()+"\" class=\"l-btn l-btn-plain\"><span class=\"l-btn-left\">" +
    			"<span class=\"l-btn-text\"><span class=\"l-btn-empty pagination-first\">" +
    			"&nbsp;</span></span></span></a></td>");sb.append(br);
    			
    	sb.append("<td><a href=\""+page.getUri()+"?rows="+page.getRows()+"&page="+Math.max(1,page.getPage()-1)+page.getURLParam()+"\" class=\"l-btn l-btn-plain\"><span class=\"l-btn-left\">" +
    	    	"<span class=\"l-btn-text\"><span class=\"l-btn-empty pagination-first\">" +
    	    	"&nbsp;</span></span></span></a></td>");sb.append(br);
    			
    	sb.append(showSeparator());
    	
    	sb.append("<td><span style=\"padding-left: 6px;\">第</span></td>");sb.append(br);
    	String onkeyup="onkeyup=\"window.location.href='"+page.getUri()+"?page='+this.value+'&rows="+page.getRows()+page.getURLParam()+"'\"";
    	sb.append("<td><input type=\"text\" size=\"2\" value=\""+page.getPage()+"\" class=\"pagination-num\" "+onkeyup+"></td>");sb.append(br);
    	sb.append("<td><span style=\"padding-right: 6px;\">共"+totalPage+"页</span></td>");sb.append(br);
    	
    	sb.append(showSeparator());
    	
    	sb.append("<td><a href=\""+page.getUri()+"?rows="+page.getRows()+"&page="+Math.min(totalPage,page.getPage()+1)+page.getURLParam()+"\" class=\"l-btn l-btn-plain\"><span class=\"l-btn-left\">" +
    	    	"<span class=\"l-btn-text\"><span class=\"l-btn-empty pagination-next\">" +
    	    	"&nbsp;</span></span></span></a></td>");sb.append(br);
    	
    	sb.append("<td><a href=\""+page.getUri()+"?rows="+page.getRows()+"&page="+totalPage+page.getURLParam()+"\" class=\"l-btn l-btn-plain\"><span class=\"l-btn-left\">" +
    	    	"<span class=\"l-btn-text\"><span class=\"l-btn-empty pagination-last\">" +
    	    	"&nbsp;</span></span></span></a></td>");sb.append(br);
    	
    	sb.append(showSeparator());
    	
    	if(buttons!=null&&!"".equals(buttons)){
    		String[] buttonArray = buttons.split(",");
    		for(String button:buttonArray){
    			if("excel".equals(button)){excel_flag=true;sb.append(showButton("导出","icon-excel","javascript:pageExcel()"));sb.append(br);}
    			if("back".equals(button)) {
    				String href ="javascript:history.go(-1);";
    				if(page.getBack_url()!=null&&!"".equals(page.getBack_url()))href=page.getBack_url();
    				sb.append(showButton("返回","icon-back" ,href)); sb.append(br);
    			}
    			if("print".equals(button)){sb.append(showButton("打印","icon-print","javascript:pagePrint()"));sb.append(br);}
    		}
    	}
    	sb.append("</tr></tbody></table>");sb.append(br);
    	sb.append("<div class=\"pagination-info\">显示"+rowsSt+"到"+rowsEd+",共"+page.getTotal()+"记录</div>");sb.append(br);
    	sb.append("<div style=\"clear: both;\"></div>");sb.append(br);
    	sb.append("</div>");sb.append(br);
    	
    	if(excel_flag){
    	sb.append("<script type=\"text/javascript\">");sb.append(br);
    	sb.append("var pageExcelParam={'queryParm':'"+page.getURLParam()+"','total':"+page.getTotal()+",'pageSize':"+page.getRows()+",'pageNumber':"+page.getPage()+",'url':'"+excel_url+"','maxPage':"+totalPage+"};"); 	 
    	sb.append("</script>");sb.append(br);
    	}
    	return sb.toString();
    }

    private String showPageList(){
    	StringBuffer sb = new StringBuffer();
    	if(pageList==null||"".equals(pageList))pageList="20,40,60,80";
    	String[] options=pageList.split(",");
    	String onChange="onChange=\"window.location.href='"+page.getUri()+"?rows='+this.value+'&page="+1+page.getURLParam()+"'\"";
    	sb.append("<td><select class=\"pagination-page-list\" "+onChange+">");sb.append(br);
    	for(String option:options){
    		String selected ="";
    		if(option.equals(String.valueOf(page.getRows()))) selected="selected=\"selected\"";
    		sb.append("<option "+selected+">"+option+"</option>");
    	}
    	sb.append("</select></td>");sb.append(br);
    	return sb.toString();
    }
    private String showSeparator(){
    	StringBuffer sb = new StringBuffer();
    	sb.append("<td><div class=\"pagination-btn-separator\"></div></td>");sb.append(br);
		return sb.toString();
    }
    private String showButton(String name,String css,String href){
    	StringBuffer sb = new StringBuffer();
    	sb.append("<td><a href=\""+href+"\" class=\"l-btn l-btn-plain\"><span class=\"l-btn-left\">" +
    	    	"<span class=\"l-btn-text\"><span class=\"l-btn-empty "+css+"\">" +
    	    	"&nbsp;</span>"+name+"</span></span></a></td>");sb.append(br);
		return sb.toString();
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
