package com.tongdatech.echarts_front.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.echarts_front.bean.EchartsDemoBean;
import com.tongdatech.echarts_front.bean.ReturnBean;
import com.tongdatech.echarts_front.service.EchartsDemoService;
import com.tongdatech.map.util.PubFunc;
import com.tongdatech.sys.base.PaginationAction;
import com.tongdatech.sys.util.JsonUtil;

public class EchartsDemoAction extends PaginationAction implements ModelDriven<EchartsDemoBean>{
	private static final long serialVersionUID = 8843440802212282449L;
	private static Logger log =  Logger.getLogger(EchartsDemoAction.class);
	private EchartsDemoBean bean = new EchartsDemoBean();
	private EchartsDemoService service = new EchartsDemoService();
	private File[] upload;
	
	public String init(){
		System.out.println("百度echarts-----init");
		return "init";
	}
	
	public String init2(){
		System.out.println("测试页面1-----init");
		return "init2";
	}
	
	public String init3(){
		System.out.println("柱状和折线-----------init");
		return "init3";
	}
	
	public String init4(){
		System.out.println("饼图-----init");
		return "init4";
	}
	
	public String init5(){
		System.out.println("仪表盘-----init");
		return "init5";
	}
	
	public String init6(){
		System.out.println("漏斗图-----init");
		return "init6";
	}
	
	public String init7(){
		System.out.println("地图2级联动-----init");
		return "init7";
	}
	
	public String init8(){
		System.out.println("标线眩光特效-----init");
		return "init8";
	}
	
	public String init9(){
		System.out.println("员工分布图-----init");
		return "init9";
	}
	
	public void loaddata() throws Exception{
		ReturnBean returnBean = new ReturnBean();
		HttpServletResponse response = ServletActionContext.getResponse();
		returnBean = service.loaddata(bean);
		JsonUtil.ToJson(returnBean,response);
		return;
	}
	
 
	@SuppressWarnings("unchecked")
	public String zhanbi() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String, Object> map=new HashMap<String, Object>(); 
 
		List<Map> list= service.zhanbi();
 
		Map<String ,String >paramMap1=new HashMap<String ,String >();
		paramMap1.put("legendColumName", "年龄");
		paramMap1.put("xAxisColumName", "机构名称");
		paramMap1.put("seriesColumName", "年龄段活期");
		Map map1=this.zhanbisss(list, paramMap1);
		
		
		Map<String ,String >paramMap2=new HashMap<String ,String >();
		paramMap2.put("legendColumName", "年龄");
		paramMap2.put("xAxisColumName", "机构名称");
		paramMap2.put("seriesColumName", "人数");
		Map map2=this.zhanbisss(list, paramMap2);
		map.put("data1", map1);
		map.put("data2", map2);
		PubFunc.jsonOutput(response, map);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Map zhanbisss(List<Map> dataList,Map paramMap) throws Exception{
	 
		Map<String, Object> map=new HashMap<String, Object>(); 
		List<String> legend=new ArrayList<String>();
 
		List<String> xAxisData=new ArrayList<String>();
		List<Map> series=new ArrayList<Map>();
	 
 

		List<List<String>> listdata= new ArrayList<List<String>> ();

		
		
		for(int i=0;i<dataList.size();i++)
		{
			 
			  List<String> tempList=new ArrayList<String>();
			
			  int num=0;
			  if(dataList.get(i).get(paramMap.get("legendColumName"))!=null&&!"".equals(dataList.get(i).get(paramMap.get("legendColumName"))))
			  { 
				  if(dataList.get(i).get(paramMap.get("xAxisColumName"))!=null&&!"".equals(dataList.get(i).get(paramMap.get("xAxisColumName"))))
				  {
							   
					   boolean legend_flag=false;
					   for(int k=0;k<legend.size();k++)
					   {
							if(legend.get(k).equals(dataList.get(i).get(paramMap.get("legendColumName"))))
							{
								legend_flag=true;
								num=k;
								continue;
							}
					   } 
					   if(legend_flag==false)
					   {
						   legend.add( dataList.get(i).get(paramMap.get("legendColumName")).toString());
						   listdata.add(tempList);
						   num=listdata.size()-1;
					   }
					   boolean xAxisData_flag=false;
					   for(int k=0;k<xAxisData.size();k++)
					   {
							if(xAxisData.get(k).equals(dataList.get(i).get(paramMap.get("xAxisColumName"))))
							{
								xAxisData_flag=true;
								continue;
							}
					   } 
					   if(xAxisData_flag==false)
					   {
						   xAxisData.add( dataList.get(i).get(paramMap.get("xAxisColumName")).toString());
					   } 
					   BigDecimal value = new BigDecimal(dataList.get(i).get(paramMap.get("seriesColumName")).toString());
					   listdata.get(num).add(value.toString());
				
				   } 
			   }  
		} 

		for(int i=0;i<legend.size();i++)
		{
			Map <String, Object> data=new HashMap<String, Object>(); 
			data.put("name", legend.get(i));
			data.put("type", "bar");
			data.put("data", listdata.get(i));
			series.add(data);
		} 
		Map<String,Object> xAxisMap=new HashMap<String,Object>();
		xAxisMap.put("type", "category");
		xAxisMap.put("data", xAxisData); 
		map.put("legend", legend);  
		map.put("xAxis", xAxisMap); 
		map.put("series", series);   
		return map;
	}
	
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		EchartsDemoAction.log = log;
	}
	public EchartsDemoBean getBean() {
		return bean;
	}
	public void setBean(EchartsDemoBean bean) {
		this.bean = bean;
	}
	public EchartsDemoService getService() {
		return service;
	}
	public void setService(EchartsDemoService service) {
		this.service = service;
	}
	public File[] getUpload() {
		return upload;
	}
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public EchartsDemoBean getModel() {
		return bean;
	}
}
