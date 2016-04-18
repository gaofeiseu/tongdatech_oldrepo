package com.gaofei.bean;

import java.util.ArrayList;
import java.util.List;

public class BaseBean {
	private String save_path;
	private List<EnumTilesType> list_map_type;
	private int st_zoom;
	private int ed_zoom;
	private double st_lon;
	private double ed_lon;
	private double st_lat;
	private double ed_lat;
	
	public BaseBean(String[] args) {
		this.save_path = args[0];
		String[] types = args[1].split("#");
		List<EnumTilesType> list_map_type = new ArrayList<EnumTilesType>();
		for(String type : types){
			if(EnumTilesType.MAPABC.toString().equals(type)){
				list_map_type.add(EnumTilesType.MAPABC);
			}else if(EnumTilesType.MAPSA.toString().equals(type)){
				list_map_type.add(EnumTilesType.MAPSA);
			}else if(EnumTilesType.MAPSADL.toString().equals(type)){
				list_map_type.add(EnumTilesType.MAPSADL);
			}
		}
		this.list_map_type = list_map_type;
		this.st_zoom = Integer.parseInt(args[2]);
		this.ed_zoom = Integer.parseInt(args[3]);
		this.st_lon = Double.parseDouble(args[4]);
		this.ed_lon = Double.parseDouble(args[6]);
		this.st_lat = Double.parseDouble(args[5]);
		this.ed_lat = Double.parseDouble(args[7]);
	}
	public BaseBean() {
		
	}
	public BaseBean(String save_path, List<EnumTilesType> list_map_type, int st_zoom, int ed_zoom, double st_lon,
			double ed_lon, double st_lat, double ed_lat) {
		this.save_path = save_path;
		this.list_map_type = list_map_type;
		this.st_zoom = st_zoom;
		this.ed_zoom = ed_zoom;
		this.st_lon = st_lon;
		this.ed_lon = ed_lon;
		this.st_lat = st_lat;
		this.ed_lat = ed_lat;
	}
	public String getSave_path() {
		return save_path;
	}
	public void setSave_path(String save_path) {
		this.save_path = save_path;
	}
	public List<EnumTilesType> getList_map_type() {
		return list_map_type;
	}
	public void setList_map_type(List<EnumTilesType> list_map_type) {
		this.list_map_type = list_map_type;
	}
	public int getSt_zoom() {
		return st_zoom;
	}
	public void setSt_zoom(int st_zoom) {
		this.st_zoom = st_zoom;
	}
	public int getEd_zoom() {
		return ed_zoom;
	}
	public void setEd_zoom(int ed_zoom) {
		this.ed_zoom = ed_zoom;
	}
	public double getSt_lon() {
		return st_lon;
	}
	public void setSt_lon(double st_lon) {
		this.st_lon = st_lon;
	}
	public double getEd_lon() {
		return ed_lon;
	}
	public void setEd_lon(double ed_lon) {
		this.ed_lon = ed_lon;
	}
	public double getSt_lat() {
		return st_lat;
	}
	public void setSt_lat(double st_lat) {
		this.st_lat = st_lat;
	}
	public double getEd_lat() {
		return ed_lat;
	}
	public void setEd_lat(double ed_lat) {
		this.ed_lat = ed_lat;
	}
	@Override
	public String toString() {
		return "BaseBean [save_path=" + save_path + ", list_map_type=" + list_map_type + ", st_zoom=" + st_zoom
				+ ", ed_zoom=" + ed_zoom + ", st_lon=" + st_lon + ", ed_lon=" + ed_lon + ", st_lat=" + st_lat
				+ ", ed_lat=" + ed_lat + "]";
	}
}
