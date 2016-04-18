package com.tongdatech.map_tile.bean;

import com.tongdatech.sys.annotation.PageParam;

public class MapTileBean {

	@PageParam
	private String select_folder;
	@PageParam
	private String select_Type;
	@PageParam
	private String select_Zoom;
	@PageParam
	private String select_X;
	@PageParam
	private String select_Y;

	public String getSelect_Type() {
		return select_Type;
	}

	public void setSelect_Type(String select_Type) {
		this.select_Type = select_Type;
	}

	public String getSelect_Zoom() {
		return select_Zoom;
	}

	public void setSelect_Zoom(String select_Zoom) {
		this.select_Zoom = select_Zoom;
	}

	public String getSelect_X() {
		return select_X;
	}

	public void setSelect_X(String select_X) {
		this.select_X = select_X;
	}

	public String getSelect_Y() {
		return select_Y;
	}

	public void setSelect_Y(String select_Y) {
		this.select_Y = select_Y;
	}

	public String getSelect_folder() {
		return select_folder;
	}

	public void setSelect_folder(String select_folder) {
		this.select_folder = select_folder;
	}

	@Override
	public String toString() {
		return "MapTileBean [select_folder=" + select_folder + ", select_Type="
				+ select_Type + ", select_Zoom=" + select_Zoom + ", select_X="
				+ select_X + ", select_Y=" + select_Y + "]";
	}
	
}
