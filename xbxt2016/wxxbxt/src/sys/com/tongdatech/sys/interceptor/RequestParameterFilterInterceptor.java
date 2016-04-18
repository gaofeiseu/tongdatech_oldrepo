package com.tongdatech.sys.interceptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tongdatech.sys.util.VulnerabilitiesCheck;


public class RequestParameterFilterInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private static String NO="NO";
	private static String SPLIT=",";

	private static Map<String,String> exculdes=new HashMap<String,String>(); 
	static{
	    URL url = RequestParameterFilterInterceptor.class.getResource("/configs/");
	    if(url!=null){
		File file = new File(url.getPath());
    	FilenameFilter filter= new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				boolean rs = name.startsWith("request_")&&name.endsWith(".properties");
				return rs;
			}
    		
    	};
		File[] files = file.listFiles(filter);
		for(File f:files){
			Properties properties=new Properties();
			try {
				properties.load(new FileInputStream(f));
				for(Entry<Object, Object> entry :properties.entrySet()){
					exculdes.put(entry.getKey().toString(),entry.getValue().toString());
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    }
	}
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request= (HttpServletRequest)(invocation.getInvocationContext()).get(StrutsStatics.HTTP_REQUEST);
		String uri=request.getRequestURI();
		uri=trimUri(uri);
		String confs=exculdes.get(uri);
		if(NO.equals(confs)){
			return invocation.invoke();  
		}
		Map map=invocation.getInvocationContext().getParameters();  
        Set keys = map.keySet();  
                Iterator iters = keys.iterator();  
                while(iters.hasNext()){  
                    Object key = iters.next();  
                    Object value = map.get(key);  
                    map.put(key, transfer((String[])value,confs));  
                }  
        return invocation.invoke();  
	}

	private String[] transfer(String[] strs,String confs) {
		List<String> rs=new ArrayList<String>();
		for(String str:strs){
			if(confs==null){
				rs.add(VulnerabilitiesCheck.encode(str));
			}else{
				rs.add(VulnerabilitiesCheck.encode(str,confs.split(SPLIT)));
			}
			
		}
		return rs.toArray(new String[strs.length]);
	}
	private String trimUri(String uri){
		
		Pattern pattern = Pattern.compile("^/*(\\w*)(\\.action)?$");
		Matcher m = pattern.matcher(uri);
		if(m.matches()){
			return m.group(1);
		}
		return uri;
	}
//	public static void main(String[] args) {
//		String x=new RequestParameterFilterInterceptor().trimUri("/wel_com");
//		System.out.println(x);
//	}
}

