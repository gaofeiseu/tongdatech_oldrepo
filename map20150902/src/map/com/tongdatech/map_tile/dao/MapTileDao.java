package com.tongdatech.map_tile.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tongdatech.map_tile.bean.MapTileBean;
import com.tongdatech.map_tile.bean.MapTileReturnBean;
import com.tongdatech.map_tile.utils.MapTileUtils;
import com.tongdatech.sys.base.BaseDao;

public class MapTileDao extends BaseDao{

	@SuppressWarnings("unused")
	public MapTileReturnBean doop(MapTileBean bean) {
		MapTileReturnBean returnBean = new MapTileReturnBean();
		List<File> list_need_op = new ArrayList<File>();
		String folder_path = "";
		if(!"".equals(bean.getSelect_folder())&&bean.getSelect_folder()!=null){
			folder_path = bean.getSelect_folder();
			File folder_file = new File(folder_path);
			if(folder_file.isDirectory()){
				File[] file_list_maptype = folder_file.listFiles();
				if(file_list_maptype.length>0){
					for(File file_maptype : file_list_maptype){
						if(file_maptype.isDirectory()){
							if("mapabc".equals(file_maptype.getName())){
								list_need_op.add(file_maptype);
							}else if("mapSa".equals(file_maptype.getName())){
								list_need_op.add(file_maptype);
							}else if("mapSaDL".equals(file_maptype.getName())){
								list_need_op.add(file_maptype);
							}else{
								continue;
							}
						}
					}
				}else{
					returnBean.setIf_success(false);
					returnBean.setMsg("需要操作的文件夹事实上是空的！");
				}
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("需要的是文件夹路径，而不是文件路径！");
			}
		}else{
			returnBean.setIf_success(false);
			returnBean.setMsg("文件路径不能为空!");
		}
		List<File> list_zoom = new ArrayList<File>();
		for(File file : list_need_op){
			File[] file_zoom_array = file.listFiles();
			for(File file_zoom:file_zoom_array){
				if(file_zoom.isDirectory()){
					try{
						int zoom = Integer.parseInt(file_zoom.getName());
						list_zoom.add(file_zoom);
					}
					catch(Exception ex){
						System.out.println("zoom========>"+file_zoom.getPath());
					}
				}
			}
		}
		List<File> list_x = new ArrayList<File>();
		for(File file:list_zoom){
			File[] file_x_array = file.listFiles();
			for(File file_x:file_x_array){
				if(file_x.isDirectory()){
					try{
						int x = Integer.parseInt(file_x.getName());
						list_x.add(file_x);
						System.out.println("x---success===========>"+file_x.getPath());
					}
					catch(Exception ex){
						System.out.println("x---error===========>"+file_x.getPath());
					}
				}
			}
		}
		List<String> list_y = new ArrayList<String>();
		for(File file:list_x){
			File[] file_y_array = file.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File file, String filename) {
					if(filename.endsWith(".jpg")||filename.endsWith(".png")||filename.endsWith(".bmp")||
							filename.endsWith(".JPG")||filename.endsWith(".PNG")||filename.endsWith(".BMP")){
						return true;
					}
					return false;
				}
			});
			for(File file_y:file_y_array){
				list_y.add(file_y.getPath());
				System.out.println("y---success===========>"+file_y.getPath());
			}
		}
		//				 D:\MAP_JS_NEW\WebRoot\maptile
		//               D:\MAP_JS_NEW\WebRoot\maptile\mapabc\16\54436\26604.png
		System.out.println("=================================================================================================");
		System.out.println("size--->"+list_y.size());
		try{
			for(String str:list_y){
				String aa = str.substring(folder_path.length()+1, str.length());
				String[] s_array = (str.substring(folder_path.length()+1, str.length())).split("\\\\");
				System.out.println(s_array[0]+"+"+s_array[1]+"+"+s_array[2]+"+"+s_array[3]);
				if(s_array.length!=4){
					System.out.println("--------<>-------"+str);
				}else{
					String sql = "select SEQ_A_TEST_IMG_TABLE.nextval from dual ";
					String sn = db.queryString(sql);
					try{
						db.putImgIntoDB(s_array[0], s_array[1], s_array[2], (s_array[3].split("\\."))[0],"."+(s_array[3].split("\\."))[1], MapTileUtils.image2byte(str), sn);
						System.out.println("------------------SUCCESS--------------------->"+str);
					}
					catch(Exception ex1){
						ex1.printStackTrace();
					}
				}
			}
			System.out.println("=====================ALL SUCCESS======================");
			returnBean.setIf_success(true);
			returnBean.setMsg("成功了！");
		}
		catch(Exception ex){
			ex.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("失败了，发生错误了！");
		}
		return returnBean;
	}

	public MapTileReturnBean foop(MapTileBean bean) {
		MapTileReturnBean returnBean = new MapTileReturnBean();
		try{
			byte[] data = null;
			data = db.getImgFromDB(bean.getSelect_Type(), bean.getSelect_Zoom(), bean.getSelect_X(), bean.getSelect_Y());
			String exe = "";
			exe = db.queryString("select exe from a_test_img_table where type='"+bean.getSelect_Type()
					+"' and zoom="+bean.getSelect_Zoom()+" and x="+bean.getSelect_X()+" and y="+bean.getSelect_Y()+" ");
			String temp_path = "F:\\002";
			String file_path = temp_path+"\\"+bean.getSelect_Type()+"_"+bean.getSelect_Zoom()+"_"+bean.getSelect_X()+"_"+bean.getSelect_Y()+exe;
			if(MapTileUtils.byte2image(data,file_path)){
				returnBean.setIf_success(true);
				returnBean.setMsg("成功啦！");
			}else{
				returnBean.setIf_success(false);
				returnBean.setMsg("失败啦！");
			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			returnBean.setIf_success(false);
			returnBean.setMsg("失败啦！");
		}
		return returnBean;
	}

	public Map<String,Object> doo(MapTileBean bean) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		try{
			String table_name = "";
			if("mapabc".equalsIgnoreCase(bean.getSelect_Type())){
				table_name = "a_map_tile_mapabc";
			}else if("mapSa".equalsIgnoreCase(bean.getSelect_Type())){
				table_name = "a_map_tile_mapsa";
			}else if("mapSaDL".equalsIgnoreCase(bean.getSelect_Type())){
				table_name = "a_map_tile_mapsadl";
			}else{
				table_name = "";
			}
			returnMap.put("zoom", bean.getSelect_Zoom());
			returnMap.put("x", bean.getSelect_X());
			returnMap.put("y", bean.getSelect_Y());
			byte[] data = null;
			data = db.getImgFromDB(table_name, bean.getSelect_Zoom(), bean.getSelect_X(), bean.getSelect_Y());
			String exe = "";
			exe = db.queryString("select exe from "+table_name+" where 1=1 and zoom="+bean.getSelect_Zoom()+" and x="+bean.getSelect_X()+" and y="+bean.getSelect_Y()+" ");
			if(data!=null){
				InputStream input = new ByteArrayInputStream(data);
				if(input!=null){
					returnMap.put("inputStream", input);
					returnMap.put("exe", exe);
				}
			}else{
				
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return returnMap;
	}

}
