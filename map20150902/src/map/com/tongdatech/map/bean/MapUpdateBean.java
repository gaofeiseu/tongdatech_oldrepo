package com.tongdatech.map.bean;

import com.tongdatech.sys.annotation.PageParam;

public class MapUpdateBean {
	@PageParam
	private String st_lat;
	@PageParam
	private String st_lng;
	@PageParam
	private String ed_lat;
	@PageParam
	private String ed_lng;
	@PageParam
	private String map_zoom;
	@PageParam
	private String download_url;
	@PageParam
	private String map_type;
	
	
	
	public String getMap_type() {
		return map_type;
	}
	public void setMap_type(String map_type) {
		this.map_type = map_type;
	}
	public String getSt_lat() {
		return st_lat;
	}
	public void setSt_lat(String st_lat) {
		this.st_lat = st_lat;
	}
	public String getSt_lng() {
		return st_lng;
	}
	public void setSt_lng(String st_lng) {
		this.st_lng = st_lng;
	}
	public String getEd_lat() {
		return ed_lat;
	}
	public void setEd_lat(String ed_lat) {
		this.ed_lat = ed_lat;
	}
	public String getEd_lng() {
		return ed_lng;
	}
	public void setEd_lng(String ed_lng) {
		this.ed_lng = ed_lng;
	}
	public String getMap_zoom() {
		return map_zoom;
	}
	public void setMap_zoom(String map_zoom) {
		this.map_zoom = map_zoom;
	}
	public String getDownload_url() {
		return download_url;
	}
	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}
	@Override
	public String toString() {
		return "MapUpdateBean [st_lat=" + st_lat + ", st_lng=" + st_lng
				+ ", ed_lat=" + ed_lat + ", ed_lng=" + ed_lng + ", map_zoom="
				+ map_zoom + ", download_url=" + download_url + ", map_type="
				+ map_type + "]";
	}
	
}
