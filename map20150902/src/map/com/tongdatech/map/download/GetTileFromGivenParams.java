package com.tongdatech.map.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class GetTileFromGivenParams {
	private static final int MAP_SERVER_NUM_MAX = 2;
	private static final int MAP_SERVER_NUM_MIN = 0;
	private String path = "";
	public GetTileFromGivenParams(String output_path){
		this.path = output_path;
	}
	public void run(int _x,int _y,int _z,String map_type) throws Exception{
			String filename = _y+".png";
			String savePath = path+"/"+_z+"/"+_x;
			String urlString = getUrlString(_x, _y, _z,map_type);
			try{
				download(urlString, filename, savePath);
			}catch ( Exception e) {
				e.printStackTrace();
			}
	}
	public static String getUrlString(int x,int y,int z,String map_type){
		int mapserver_num = (int)(1+Math.random()*(MAP_SERVER_NUM_MAX-MAP_SERVER_NUM_MIN+1));
		String map_url = "";
		if("1".equals(map_type)){
			map_url = "http://mt"+(mapserver_num-1)+".google.cn/vt/lyrs=m@241000000&hl=zh-CN&gl=CN&src=app&x="+x+"&y="+y+"&z="+z+"&s=Galileo";
		}else if("2".equals(map_type)){
			map_url = "http://mt"+(mapserver_num-1)+".google.cn/vt/lyrs=s@156&hl=zh-CN&gl=CN&src=app&rlbl=1&x="+x+"&y="+y+"&z="+z+"&s=Galileo";
			//map_url = "http://mt"+(mapserver_num-1)+".google.cn/vt/imgtp=png32&lyrs=h@271000000,highlight:0x35b58c9b668dcd83:0x8ffbb60b79df1b06@1%7Cstyle:maps&hl=zh-CN&gl=CN&src=app&expIds=201527&rlbl=1&x="+x+"&y="+y+"&z="+z+"&s=Galileo";
		}else if("3".equals(map_type)){
			map_url = "http://mt"+(mapserver_num-1)+".google.cn/vt/imgtp=png32&lyrs=h@271000000,highlight:0x35b58c9b668dcd83:0x8ffbb60b79df1b06@1%7Cstyle:maps&hl=zh-CN&gl=CN&src=app&expIds=201527&rlbl=1&x="+x+"&y="+y+"&z="+z+"&s=Galileo";
		}
		return map_url;
	}
	public static void download(String urlString, String filename,String savePath){
		InputStream is = null;
		OutputStream os = null;
		try {
			System.out.println("URL--->"+urlString);
			// 构造URL  
			URL url = new URL(urlString);  
			// 打开连接  
			URLConnection con = url.openConnection();
			con.setRequestProperty("User-Agent", "MSIE");//-----add
			//设置请求的路径  
			con.connect();
	        con.setConnectTimeout(3000);//设置10秒链接超时
	        con.setReadTimeout(3000);//设置10秒读取数据超时
	        // 输入流  
	        is = con.getInputStream(); 
	      
	        // 1K的数据缓冲  
	        byte[] bs = new byte[1024];
	        // 读取到的数据长度  
	        int len;  
	        // 输出的文件流  
	       File sf=new File(savePath);
	       if(!sf.exists()){
	           sf.mkdirs();
	       }  
	       os = new FileOutputStream(sf.getPath()+"/"+filename);
	        // 开始读取  
	        while ((len = is.read(bs)) != -1) {
	          os.write(bs, 0, len);
	          os.flush();
	        }
	        // 完毕，关闭所有链接
	        os.flush();
	       
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 if(os!=null){
				 try {
					 os.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
			 if(is!=null){
				 try {
					 is.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		}
    }
}
