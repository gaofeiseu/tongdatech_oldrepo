package com.gaofei.util;

import java.io.File;

public class ValidationUtil {
	public static void validate(String[] args) throws Exception{
		validateSavePath(args[0]);
		validateMapType(args[1]);
		validateZoom(args[2],args[3]);
		validateLon(args[4],args[6]);
		validateLat(args[5],args[7]);
	}
	private static void validateSavePath(String save_path) throws Exception{
		File f = new File(save_path);
		if(f.exists()){
			if(!f.isDirectory()){
				throw new Exception("选择的路径【"+save_path+"】不是一个文件夹！");
			}
		}else{
			throw new Exception("选择的路径【"+save_path+"】不存在！");
		}
	}
	private static void validateMapType(String map_types) throws Exception{
		try{
			String[] map_type = map_types.split("#");
			if(map_type.length==0){
				throw new Exception ("");
			}
			for(String m : map_type){
				int i = Integer.parseInt(m);
				if(i<1||i>3){
					throw new Exception ("");
				}
			}
		}
		catch(Exception ex){
			throw new Exception("下载类型参数【"+map_types+"】格式错误！应该是形如（1#2#3）这种，1代表普通道路图、2代表卫星图、3代表卫星道路图");
		}
	}
	private static void validateZoom(String zoom_st,String zoom_ed) throws Exception{
		try{
			int zoom_s = Integer.parseInt(zoom_st);
			int zoom_e = Integer.parseInt(zoom_ed);
			if(zoom_s>=zoom_e){
				throw new Exception("一般认为，地图的下载参数中的起始层数应该比结束层数小");
			}
			if(zoom_s<1||zoom_s>20||zoom_e<1||zoom_e>20){
				throw new Exception("一般认为，地图的下载参数中的起始层数和结束层数应该是1到20的数字");
			}
		}
		catch(Exception ex){
			if("".equals(ex.getMessage())){
				throw new Exception("下载地图层数参数【"+zoom_st+"，"+zoom_ed+"】格式错误！应该是1到20的数字");
			}else{
				if(ex instanceof NumberFormatException){
					throw new Exception("下载地图层数参数【"+zoom_st+"，"+zoom_ed+"】格式错误！应该是1到20的数字");
				}
				throw ex;
			}
		}
	}
	private static void validateLon(String lon_st,String lon_ed) throws Exception{
		try{
			double min_lon = 75;
			double max_lon = 135;
			double lon_s = Double.parseDouble(lon_st);
			double lon_e = Double.parseDouble(lon_ed);
			if(lon_s>=lon_e){
				throw new Exception ("开始经度应该比结束经度小才对");
			}
			if(lon_s<min_lon||lon_s>max_lon||lon_e<min_lon||lon_e>max_lon){
				throw new Exception ("经度应该位于"+min_lon+"和"+max_lon+"之间");
			}
		}
		catch(Exception ex){
			throw ex;
		}
	}
	private static void validateLat(String lat_st,String lat_ed) throws Exception{
		try{
			double min_lat = 17;
			double max_lat = 53;
			double lat_s = Double.parseDouble(lat_st);
			double lat_e = Double.parseDouble(lat_ed);
			if(lat_s<=lat_e){
				throw new Exception ("开始纬度数值上应该比结束纬度大才对");
			}
			if(lat_s<min_lat||lat_s>max_lat||lat_e<min_lat||lat_e>max_lat){
				throw new Exception ("经度应该位于"+min_lat+"和"+max_lat+"之间");
			}
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
