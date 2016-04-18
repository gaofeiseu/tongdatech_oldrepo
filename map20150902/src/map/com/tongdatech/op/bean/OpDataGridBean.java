package com.tongdatech.op.bean;

import java.util.List;
import java.util.Map;

public class OpDataGridBean {
	private int total;
	private List<Map<String,Object>> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Map<String, Object>> getRows() {
		return rows;
	}
	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}
}
