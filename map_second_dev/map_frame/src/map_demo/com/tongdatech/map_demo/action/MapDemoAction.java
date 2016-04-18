package com.tongdatech.map_demo.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.tongdatech.map_demo.bean.MapDemoBean;
import com.tongdatech.map_demo.service.MapDemoService;
import com.tongdatech.sys.base.PaginationAction;

public class MapDemoAction extends PaginationAction implements ModelDriven<MapDemoBean>{
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(MapDemoAction.class);
	private MapDemoBean bean = new MapDemoBean();
	private MapDemoService service = new MapDemoService();
	
	public String init_demo(){
		return "init_demo";
	}
	public String init_doc(){
		return "init_doc";
	}
	
	public MapDemoBean getModel() {
		return bean;
	}

}
