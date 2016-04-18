package com.tongdatech.map.bean;

import java.util.Map;

public class LatLngBean {
	private String status;
	private Map<?,?> result;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<?, ?> getResult() {
		return result;
	}
	public void setResult(Map<?, ?> result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "LatLngBean [status=" + status + ", result=" + result.toString() + "]";
	}
	
}
