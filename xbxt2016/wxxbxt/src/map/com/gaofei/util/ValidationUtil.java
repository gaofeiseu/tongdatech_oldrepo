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
				throw new Exception("ѡ���·����"+save_path+"������һ���ļ��У�");
			}
		}else{
			throw new Exception("ѡ���·����"+save_path+"�������ڣ�");
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
			throw new Exception("�������Ͳ�����"+map_types+"����ʽ����Ӧ�������磨1#2#3�����֣�1������ͨ��·ͼ��2��������ͼ��3�������ǵ�·ͼ");
		}
	}
	private static void validateZoom(String zoom_st,String zoom_ed) throws Exception{
		try{
			int zoom_s = Integer.parseInt(zoom_st);
			int zoom_e = Integer.parseInt(zoom_ed);
			if(zoom_s>=zoom_e){
				throw new Exception("һ����Ϊ����ͼ�����ز����е���ʼ����Ӧ�ñȽ�������С");
			}
			if(zoom_s<1||zoom_s>20||zoom_e<1||zoom_e>20){
				throw new Exception("һ����Ϊ����ͼ�����ز����е���ʼ�����ͽ�������Ӧ����1��20������");
			}
		}
		catch(Exception ex){
			if("".equals(ex.getMessage())){
				throw new Exception("���ص�ͼ����������"+zoom_st+"��"+zoom_ed+"����ʽ����Ӧ����1��20������");
			}else{
				if(ex instanceof NumberFormatException){
					throw new Exception("���ص�ͼ����������"+zoom_st+"��"+zoom_ed+"����ʽ����Ӧ����1��20������");
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
				throw new Exception ("��ʼ����Ӧ�ñȽ�������С�Ŷ�");
			}
			if(lon_s<min_lon||lon_s>max_lon||lon_e<min_lon||lon_e>max_lon){
				throw new Exception ("����Ӧ��λ��"+min_lon+"��"+max_lon+"֮��");
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
				throw new Exception ("��ʼγ����ֵ��Ӧ�ñȽ���γ�ȴ�Ŷ�");
			}
			if(lat_s<min_lat||lat_s>max_lat||lat_e<min_lat||lat_e>max_lat){
				throw new Exception ("����Ӧ��λ��"+min_lat+"��"+max_lat+"֮��");
			}
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
