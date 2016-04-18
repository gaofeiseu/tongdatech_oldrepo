package com.gaofei.bean;

public class TaskBean {
	private int x;
	private int y;
	private int z;
	private String save_path;
	private EnumTilesType tile_type;
	
	public TaskBean(int x, int y, int z, String save_path, EnumTilesType tile_type) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.save_path = save_path;
		this.tile_type = tile_type;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public String getSave_path() {
		return save_path;
	}
	public void setSave_path(String save_path) {
		this.save_path = save_path;
	}
	public EnumTilesType getTile_type() {
		return tile_type;
	}
	public void setTile_type(EnumTilesType tile_type) {
		this.tile_type = tile_type;
	}
	@Override
	public String toString() {
		return "TaskBean [x=" + x + ", y=" + y + ", z=" + z + ", save_path=" + save_path + ", tile_type=" + tile_type
				+ "]";
	}
	
}
