package com.tongdatech.map.StrutsFilter;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.dispatcher.ng.ExecuteOperations;
import org.apache.struts2.dispatcher.ng.InitOperations;
import org.apache.struts2.dispatcher.ng.PrepareOperations;
import org.apache.struts2.dispatcher.ng.filter.FilterHostConfig;

public class MyStrutsFilter implements StrutsStatics, Filter {
	protected PrepareOperations prepare;  
    protected ExecuteOperations execute;  
    protected List<Pattern> excludedPatterns;

    public MyStrutsFilter() {
        this.excludedPatterns = null;  
    }
    
	@Override
	public void destroy() {
		this.prepare.cleanupDispatcher();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        try {  
            if (request.getRequestURI().contains("/services/mobileservice")) {
                chain.doFilter(req, res);
            }
            else if(request.getRequestURI().contains("/bdmservice")){
            	chain.doFilter(req, res);
            }
            else if(request.getRequestURI().contains("MailCsService")){
            	chain.doFilter(req, res);
            }
            else if(request.getRequestURI().contains("baidu_map/js/map,oppc,tile,control")){
            	System.out.println("--------->map,oppc,tile,control");
            	chain.doFilter(req, res);
            }
            
//            else if(request.getRequestURI().contains("/mobile_login.action")){
//            	System.out.println(request.getRequestURI());
//            	chain.doFilter(req, res);
//            }
            else {
                HttpServletResponse response = (HttpServletResponse) res;  
  
                this.prepare.setEncodingAndLocale(request, response);  
  
                this.prepare.createActionContext(request, response);  
                this.prepare.assignDispatcherToThread();  
                if ((this.excludedPatterns != null)
                        && (this.prepare.isUrlExcluded(request,
                                this.excludedPatterns))) {  
                    chain.doFilter(request, response);  
                } else {  
                    request = this.prepare.wrapRequest(request);  
                    ActionMapping mapping = this.prepare.findActionMapping(  
                            request, response, true);  
                    if (mapping == null) {  
                        boolean handled = this.execute  
                                .executeStaticResourceRequest(request, response);  
                        if (!(handled))  
                            chain.doFilter(request, response);  
                    } else {  
                        this.execute.executeAction(request, response, mapping);  
                    }  
                }  
  
            }  
        } finally {  
            this.prepare.cleanupRequest(request);  
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		InitOperations init = new InitOperations();  
        try {  
            FilterHostConfig config = new FilterHostConfig(filterConfig);  
            init.initLogging(config);  
            Dispatcher dispatcher = init.initDispatcher(config);  
            init.initStaticContentLoader(config, dispatcher);  
  
            this.prepare = new PrepareOperations(filterConfig  
                    .getServletContext(), dispatcher);  
            this.execute = new ExecuteOperations(filterConfig  
                    .getServletContext(), dispatcher);  
            this.excludedPatterns = init.buildExcludedPatternsList(dispatcher);  
  
            postInit(dispatcher, filterConfig);  
        } finally {  
            init.cleanup();  
        }
	}
	
	protected void postInit(Dispatcher dispatcher, FilterConfig filterConfig) {  
    }

}
