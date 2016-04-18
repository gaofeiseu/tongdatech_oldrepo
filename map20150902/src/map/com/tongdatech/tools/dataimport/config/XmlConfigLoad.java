package com.tongdatech.tools.dataimport.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tongdatech.sys.util.ReflectionUtil;
import com.tongdatech.tools.dataimport.ImportProcess;



public class XmlConfigLoad {

	private static final Logger LOG = Logger.getLogger(XmlConfigLoad.class);
	private static final String INTERFACE_NAME=ImportProcess.class.getName();
	

    
    
    class xmlFilter implements FilenameFilter{
		@Override
		public boolean accept(File dir, String name) {  
			return name.startsWith("import_")&&name.endsWith(".xml");
		}
    	
    }
    public Map<String, BeanConfig> loadConfiguration(String base_path){

    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}  
    	
    	Map<String, BeanConfig> mp = new HashMap<String, BeanConfig>();
    	File file = new File(base_path);
    	FilenameFilter filter= new xmlFilter();
		File[] files = file.listFiles(filter);
		for(File f:files){
			try {
				//InputStream is = new FileInputStream(f);
				//InputSource in = new InputSource(is);
				
                Document doc = db.parse(f);  
				Element rootElement = doc.getDocumentElement();
				NodeList children = rootElement.getChildNodes();
	            int childSize = children.getLength();
	            for (int i = 0; i < childSize; i++) {
	            	Node childNode = children.item(i);

	                if (childNode instanceof Element) {
	                    Element child = (Element) childNode;
	                    final String nodeName = child.getNodeName();
	                    if ("bean".equals(nodeName)) {	                    	
	                        String name = child.getAttribute("name");
	                        String title = child.getAttribute("title");
	                        String impl = child.getAttribute("class");
	                        String params = child.getAttribute("params");
	                        boolean isParams=true;
	                        if(params==null||"false".equals("params"))isParams=false;
	                        
	                        String types = child.getAttribute("types");types=types.toLowerCase();
	                        String[] typeArray=types.split(",");
	                        Set<String> typeSet= new HashSet<String>(Arrays.asList(typeArray));
	                        if(verifyClass(impl)){
	                        	BeanConfig config = new BeanConfig(name, impl, title, typeSet,isParams);
	                        	NodeList chains = child.getChildNodes();
	                        	List<String> chainsList =new ArrayList<String>();
	                        	for(int j=0;j<chains.getLength();j++){
	                        		Node chain = chains.item(j);
	                        		if (chain instanceof Element){
	                        			Element ele = (Element) chain;
	                        			chainsList.add(ele.getAttribute("name"));
	                        		}
	                        		
	                        		
	                        	}
	                        	config.setChains(chainsList);
	                        	mp.put(name,config);
	                        }else{
	                        	continue;
	                        }
	                        
	                    }
	                }
	            	
	            }
			} catch (FileNotFoundException e) {
				LOG.debug("导入配置文件不存在",e);
			} catch (SAXException e) {
				LOG.debug("xml解析错误",e);				
			} catch (IOException e) {
				LOG.debug("IO错误",e);
			}
			
		}
		return mp;
    	
    }
    
    protected boolean verifyClass(String className) {
        if (className.indexOf('{') > -1) {
            
                LOG.debug("Action class [" + className + "] contains a wildcard " +
                        "replacement value, so it can't be verified");
            
            return false;
        }
        try {        
        	Class<?> clazz = Class.forName(className);
        	if(!ReflectionUtil.isInterface(clazz, INTERFACE_NAME, true)){
        		LOG.debug("未实现接口"+INTERFACE_NAME);
        		return false;
        	}

        } catch (ClassNotFoundException e) {
            LOG.debug("Class not found for action [#0]", e);
            return false;
           
        } catch (RuntimeException ex) {
            LOG.info("Unable to verify action class [#0] exists at initialization",ex);
            LOG.debug("Action verification cause", ex);
            return false;
        } catch (Exception ex) {
            LOG.debug("Unable to verify action class [#0]", ex);
            return false;
        }
        return true;
    }
}
