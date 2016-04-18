package com.tongdatech.echarts_front.bean;

import com.tongdatech.sys.annotation.PageParam;

public class EchartsDemoBean {

	@PageParam
	private String echarts_type;

	
	
	
	
	public String getEcharts_type() {
		return echarts_type;
	}
	public void setEcharts_type(String echarts_type) {
		this.echarts_type = echarts_type;
	}
}
