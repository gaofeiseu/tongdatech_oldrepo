package com.tongdatech.echarts_front.bean;

import java.util.List;
import java.util.Map;

public class ZhuzhuangBean {
	
	private Map<String,Object> tooltip;
	private Map<String,Object> legend;
	private Map<String,Object> toolbox;
	private boolean calculable;
	private List<Map<String,Object>> xAxis;
	private List<Map<String,Object>> yAxis;
	private List<Map<String,Object>> series;
	
	
	public Map<String, Object> getTooltip() {
		return tooltip;
	}
	public void setTooltip(Map<String, Object> tooltip) {
		this.tooltip = tooltip;
	}
	public Map<String, Object> getLegend() {
		return legend;
	}
	public void setLegend(Map<String, Object> legend) {
		this.legend = legend;
	}
	public Map<String, Object> getToolbox() {
		return toolbox;
	}
	public void setToolbox(Map<String, Object> toolbox) {
		this.toolbox = toolbox;
	}
	public boolean isCalculable() {
		return calculable;
	}
	public void setCalculable(boolean calculable) {
		this.calculable = calculable;
	}
	public List<Map<String, Object>> getxAxis() {
		return xAxis;
	}
	public void setxAxis(List<Map<String, Object>> xAxis) {
		this.xAxis = xAxis;
	}
	public List<Map<String, Object>> getyAxis() {
		return yAxis;
	}
	public void setyAxis(List<Map<String, Object>> yAxis) {
		this.yAxis = yAxis;
	}
	public List<Map<String, Object>> getSeries() {
		return series;
	}
	public void setSeries(List<Map<String, Object>> series) {
		this.series = series;
	}
	@Override
	public String toString() {
		return "ZhuzhuangBean [tooltip=" + tooltip.toString() + ", legend=" + legend.toString()
				+ ", toolbox=" + toolbox.toString() + ", calculable=" + calculable
				+ ", xAxis=" + xAxis.toString() + ", yAxis=" + yAxis.toString() + ", series="
				+ series.toString() + "]";
	}
	
}
