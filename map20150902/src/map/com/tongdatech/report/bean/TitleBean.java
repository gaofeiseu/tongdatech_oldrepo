package com.tongdatech.report.bean;

public class TitleBean {
	private String str;
	private int first_row;
	private int last_row;
	private int first_col;
	private int last_col;
	public int getFirst_row() {
		return first_row;
	}
	public void setFirst_row(int first_row) {
		this.first_row = first_row;
	}
	public int getLast_row() {
		return last_row;
	}
	public void setLast_row(int last_row) {
		this.last_row = last_row;
	}
	public int getFirst_col() {
		return first_col;
	}
	public void setFirst_col(int first_col) {
		this.first_col = first_col;
	}
	public int getLast_col() {
		return last_col;
	}
	public void setLast_col(int last_col) {
		this.last_col = last_col;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String toString() {
		return "TitleBean [str=" + str + ", first_row=" + first_row
				+ ", last_row=" + last_row + ", first_col=" + first_col
				+ ", last_col=" + last_col + "]";
	}
}
