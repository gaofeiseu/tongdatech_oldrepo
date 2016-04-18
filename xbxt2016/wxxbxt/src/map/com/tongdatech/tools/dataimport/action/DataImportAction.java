package com.tongdatech.tools.dataimport.action;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tongdatech.sys.bean.UserInfo;
import com.tongdatech.sys.pojo.JspMsg;
import com.tongdatech.tools.dataimport.ImportContext;
import com.tongdatech.tools.dataimport.ImportManger;
import com.tongdatech.tools.dataimport.ImportProcess;
import com.tongdatech.tools.dataimport.ImportTool;
import com.tongdatech.tools.dataimport.ImportToolFactory;
import com.tongdatech.tools.dataimport.config.BeanConfig;

public class DataImportAction  extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -259801110672710961L;
	
	private String KEY="import_context";
	private String name;
	private ImportContext context;
	private JspMsg jspMsg;
	
	private File   datafile;
	private String datafileFileName;
	
	private static char delimiter;
	
	private String type;
	// <dispatch-policy> 标签可以为长时操作新开放队列 调优时可以考虑
	
	private String jsonData;
	private int chainIndex=-1;
	
	/**
	 * 初始化，将context放入session
	 * @return
	 * @throws Exception
	 */
	public String init() throws Exception{
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		BeanConfig beanConfig=ImportManger.getType(name);
		//HttpServletRequest request = ServletActionContext.getRequest();
		if(beanConfig!=null){
			context=new ImportContext(beanConfig);
			session.put(KEY, context);
			//request.setAttribute(KEY, context);
			return "params";
		}else{
			jspMsg.setMsg("未找到名为"+name+"的导入配置");
			return "MSG";
		}
		
	}
	
	/**
	 * 加载file，选择处理方法
	 * @return
	 * @throws Exception
	 */
	public String upload()throws Exception{
		jspMsg=new JspMsg();
		if(datafile==null){
			jspMsg.setMsg("请选择一个文件");
			return "MSG";
		}
		String base_dir = (String) ServletActionContext.getServletContext().getAttribute("filePath");
		File dir = new File(base_dir+"/import");
		if(!dir.exists()){
			dir.mkdirs();
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		context=(ImportContext)session.get(KEY);
		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		context=(ImportContext)request.getAttribute(KEY);
		
		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		
		if(ImportManger.checkFile(datafileFileName)){
			if(ImportManger.saveFile(dir.getPath()+"/"+UUID.randomUUID()+"="+datafileFileName,datafile,context)){
				ImportTool tool= context.getTool();
			    if(tool==null){
			    	tool=new ImportToolFactory(type).getImportTool();
			    	context.setTool(tool);
			    }
			    tool.setFile(context.getFile());
				tool.setParameters(parameters);
				tool.parseParam();				
				
				return result();
				
				
			}else{
				jspMsg.setMsg("文件保存失败");
				return "MSG";
			}
			
		}else{
			jspMsg.setMsg("上传文件类型不支持");
			return "MSG";
		}
				
	}
	
	
	/**
	 * 将处理后的数据提交入库
	 * @return
	 * @throws Exception
	 */
	public String over() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		ImportProcess process = context.getProcess();
		UserInfo userInfo=(UserInfo) session.get(UserInfo.USER_INFO);
		jspMsg=process.saveData(context,userInfo);
		return "MSG";
	}
	
	
	/**
	 * 加载界面所需的数据
	 * @return
	 * @throws Exception
	 */
	public String result() throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		
		context=(ImportContext) session.get(KEY);
		if(chainIndex<0)chainIndex=0;
		
		String nowChain = "";
		String lastChain = "";
		try{
			List<String> chains=context.getConfig().getChains();
			if(chainIndex<chains.size())nowChain =chains.get(chainIndex);
			if(chainIndex-1>=0)lastChain=chains.get(chainIndex-1);
			chainIndex=chainIndex+1;
		}catch (Exception e) {
		}
		ImportTool tool= context.getTool();
		tool.setDelimiter(delimiter);
		ImportProcess process = context.getProcess();
		
		if(BeanConfig.CHAINS_CHOOSE.equals(lastChain)){
			context.loadCols(parameters,process.getCols());
		}
		
		//跳转逻辑
		
		if(BeanConfig.CHAINS_RENDER.equals(nowChain)){
			context.setRender(tool.beforeRender());
			return "render";
		}else if(BeanConfig.CHAINS_CHOOSE.equals(nowChain)){
			
			jsonData=new Gson().toJson(process.getCols());
			context.setRender(tool.beforeRender());
			return "choose";
		}else {
			return over();
		}
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the context
	 */
	public ImportContext getContext() {
		return context;
	}
	/**
	 * @return the jspMsg
	 */
	public JspMsg getJspMsg() {
		return jspMsg;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param context the context to set
	 */
	public void setContext(ImportContext context) {
		this.context = context;
	}
	/**
	 * @param jspMsg the jspMsg to set
	 */
	public void setJspMsg(JspMsg jspMsg) {
		this.jspMsg = jspMsg;
	}
	/**
	 * @param datafile the datafile to set
	 */
	public void setDatafile(File datafile) {
		this.datafile = datafile;
	}
	/**
	 * @return the datafile
	 */
	public File getDatafile() {
		return datafile;
	}

	/**
	 * @return the datafileFileName
	 */
	public String getDatafileFileName() {
		return datafileFileName;
	}

	/**
	 * @param datafileFileName the datafileFileName to set
	 */
	public void setDatafileFileName(String datafileFileName) {
		this.datafileFileName = datafileFileName;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param jsonData the jsonData to set
	 */
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	/**
	 * @return the jsonData
	 */
	public String getJsonData() {
		return jsonData;
	}

	/**
	 * @return the chainIndex
	 */
	public int getChainIndex() {
		return chainIndex;
	}

	/**
	 * @param chainIndex the chainIndex to set
	 */
	public void setChainIndex(int chainIndex) {
		this.chainIndex = chainIndex;
	}

	public char getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(char delimiter) {
		DataImportAction.delimiter = delimiter;
	}


	



	
	
	
	
	
	

}
