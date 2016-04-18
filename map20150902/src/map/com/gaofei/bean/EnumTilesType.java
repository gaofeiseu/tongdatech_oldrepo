package com.gaofei.bean;

public enum EnumTilesType {
	MAPABC(1),
	MAPSA(2),
	MAPSADL(3);
	private int code;
	private EnumTilesType(int _code){
		this.code = _code;
	}
	public String toString() {
		return String.valueOf(code);
	}
}
