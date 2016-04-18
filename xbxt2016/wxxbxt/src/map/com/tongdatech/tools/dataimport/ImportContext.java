package com.tongdatech.tools.dataimport;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.tongdatech.sys.pojo.PageUI;
import com.tongdatech.tools.dataimport.config.BeanConfig;

public class ImportContext implements Serializable{

	private static final long serialVersionUID = 7954222972510503907L;
	
	public static final String BEAN=BeanConfig.class.getName();
	public static final String TOOL=ImportTool.class.getName();
	public static final String PROCESS =ImportProcess.class.getName();
	public static final String FILE="FILE";
	
	private Map<String, Object> context;
	
	private int chainIndex=0;
	
	
	private Map<Integer,String> colsMap;
	private PageUI render;
	
	public ImportContext(Map<String, Object> context) {
		this.context = context;
	}
	
	public ImportContext(BeanConfig beanConfig) throws Exception {
		context = new HashMap<String, Object>();
		put(BEAN,beanConfig);
		Class<?> clazz = Class.forName(beanConfig.getClassName());
		Object process = clazz.newInstance();
		put(PROCESS,process);
		
		
	}
	public void loadCols(Map<String, Object> parameters, List<ImportCol> cols) {
		colsMap=new TreeMap<Integer, String>();
		if(cols!=null){
			for(String key :parameters.keySet()){
				String [] values=(String[])parameters.get(key);
				if(cols.contains(new ImportCol(values[0],""))){
					colsMap.put(Integer.parseInt(key), values[0]);
				}
			}
		}
		
	}
	public Map<Integer,String> getCols(){
		return colsMap;
	}
	public BeanConfig getConfig(){
		return (BeanConfig) get(BEAN);
	}
	public ImportTool getTool(){
		return (ImportTool) get(TOOL);
	}
	public void setTool(ImportTool importTool){
		put(TOOL,importTool);
	}
	public ImportProcess getProcess(){
		return (ImportProcess) get(PROCESS);
	}
	public void setProcess(ImportProcess importProcess){
		put(PROCESS,importProcess);
	}
	public void setFile(File f) {
		put(FILE,f);		
	}
	public File getFile() {
		return (File) get(FILE);		
	}
	public Object get(String key) {
	    return context.get(key);
	}
    public void put(String key, Object value) {
	    context.put(key, value);
	}

	/**
	 * @param chainIndex the chainIndex to set
	 */
	public void setChainIndex(int chainIndex) {
		this.chainIndex = chainIndex;
	}

	/**
	 * @return the chainIndex
	 */
	public int getChainIndex() {
		return chainIndex;
	}

	/**
	 * @return the render
	 */
	public PageUI getRender() {
		return render;
	}

	/**
	 * @param render the render to set
	 */
	public void setRender(PageUI render) {
		this.render = render;
	}





	
	
	
	
	
	
}
