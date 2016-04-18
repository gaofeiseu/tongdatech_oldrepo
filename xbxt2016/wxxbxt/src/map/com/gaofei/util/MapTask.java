package com.gaofei.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.concurrent.Callable;

import com.gaofei.bean.EnumTilesType;
import com.gaofei.bean.TaskBean;

public class MapTask implements Callable<Object> {
	private TaskBean taskBean;
	
	public MapTask(TaskBean _taskBean){
		this.taskBean = _taskBean;
	}

	@Override
	public Object call() throws Exception {
		String str = "";
		try{
			String url = "";
			String f = "";
			Random random = new Random();
			int i = random.nextInt(2);
			int x = taskBean.getX();
			int y = taskBean.getY();
			int z = taskBean.getZ();
			if(taskBean.getTile_type() == EnumTilesType.MAPABC){
				url = "http://mt"+i+".google.cn/vt/lyrs=m@259000000&hl=zh-CN&gl=CN&src=app&x="+x+"&y="+y+"&z="+z+"&s=Galileo";
				f = Params.mapabc_folder;
			}else if(taskBean.getTile_type() == EnumTilesType.MAPSA){
				url = "http://mt"+i+".google.cn/vt?lyrs=s@"+Params.mapsa_num+"&hl=zh-CN&gl=CN&src=app&rlbl=1&x="+x+"&y="+y+"&z="+z+"&s=Galileo";
				f = Params.mapSa_folder;
			}else if(taskBean.getTile_type() == EnumTilesType.MAPSADL){
				url = "http://mt"+i+".google.cn/vt/imgtp=png32&lyrs=h@271000000,highlight:0x35b58c9b668dcd83:0x8ffbb60b79df1b06@1%7Cstyle:maps&hl=zh-CN&gl=CN&src=app&expIds=201527&rlbl=1&x="+x+"&y="+y+"&z="+z+"&s=Galileo";
				f = Params.mapSaDL_folder;
			}else{
				throw new Exception ("下载地图的类型不正确！");
			}
			str = doWork(url,f);
		}catch(Exception ex){
			ex.printStackTrace();
			str = ex.getMessage();
		}
		return str;
	}
	private String doWork(String url,String f){
		boolean if_success = false;
		for(int i=0;i<20;i++){//单次下载循环次数
			boolean flag = false;
			try{
				flag = download(url, f);
			}
			catch(Exception e){
				flag = false;
			}
			if(!flag){
				continue;
			}else{
				if_success = true;
				break;
			}
		}
		if(if_success){
			return "SUCCESS";
		}else{
			return "ERROR=="+url;
		}
	}
	private boolean download(String url,String f) throws Exception {
		boolean if_success = false;
		String path_folder = taskBean.getSave_path()+"/"+f+"/"+""+taskBean.getZ()+"/"+taskBean.getX();
		File folder = new File(path_folder);
		if(!folder.exists()){
			folder.mkdirs();
		}
		FileOutputStream os = null;
		InputStream in = null;
		try{
			URLConnection con = (new URL(url)).openConnection();
			con.setConnectTimeout(2000);
			con.setReadTimeout(2000);
			con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			in = con.getInputStream();
			os = new FileOutputStream(path_folder+"/"+taskBean.getY()+".png");
		    byte[] buffer = new byte[4 * 1024];
		    int read;
		    while ((read = in.read(buffer)) > 0) { 
		    	os.write(buffer, 0, read);
		    }
		    if_success = true;
		}
		catch(Exception ex){
		//	System.out.println("----------"+ex);
			if_success = false;
		}
		finally{
			if(os != null){
				os.close();
			}
			if(in != null){
				in.close();
			}
		}
		return if_success;
	}
}
