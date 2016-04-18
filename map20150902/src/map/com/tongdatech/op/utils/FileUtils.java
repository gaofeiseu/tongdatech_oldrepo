package com.tongdatech.op.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {

	public static Map<String,Object> readTxtFile(String filePath){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		List<String> list = new ArrayList<String>();
		boolean if_success = false;
		String msg = "";
        try {
                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){//判断文件是否存在
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        list.add(lineTxt);
                    }
                    read.close();
                    if_success = true;
                }else{
                	System.err.println("找不到指定的文件");
                	if_success = false;
                    msg = "------->找不到指定的文件";
                }
        } catch (Exception e) {
            System.err.println("------->读取文件内容出错");
            e.printStackTrace();
            if_success = false;
            msg = "------->读取文件内容出错";
        }
        finally{
        	returnMap.put("if_success", if_success);
        	returnMap.put("msg", msg);
        	returnMap.put("list", list);
        }
        return returnMap;
    }
	public static boolean writeTxtFile(Map<String,Object> map){
		String filepath = "D:\\JS_MAP\\WebRoot\\map\\mapjs\\map_config.js";
		//无锡---->D:\\JS_MAP\\WebRoot\\map\\mapjs\\map_config.js
		//本机---->D:\\MAP_JS_NEW\\WebRoot\\map\\mapjs\\map_config.js
		String fileoutput = filepath+"12345";
		boolean if_success = false;
		try {
			BufferedReader in =new BufferedReader(new FileReader(filepath));
	        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileoutput)));
	        String line = "";
	        while((line = in.readLine())!=null){
	        	if(!"NULL".equals(map.get("url"))){
	        		if(line.indexOf("config.url ")>-1){
		        		out.println("config.url = \"http://"+String.valueOf(map.get("url"))+"/\";");
		        		continue;
		        	}
	        		if(line.indexOf("config.url_icon ")>-1){
		        		out.println("config.url_icon = \"http://"+String.valueOf(map.get("url"))+"/mapfiles/\";");
		        		continue;
		        	}
	        	}
	        	if(!"NULL".equals(map.get("maxZoon"))){
	        		if(line.indexOf("config.maxZoon ")>-1){
		        		out.println("config.maxZoon = "+String.valueOf(map.get("maxZoon"))+";");
		        		continue;
		        	}
	        	}
	        	if(!"NULL".equals(map.get("minZoon"))){
	        		if(line.indexOf("config.minZoon ")>-1){
		        		out.println("config.minZoon = "+String.valueOf(map.get("minZoon"))+";");
		        		continue;
		        	}
	        	}
	        	out.println(line);
	        }
	        in.close();
	        out.close();
	        if_success = true;
		} catch (Exception e) {
			e.printStackTrace();
			if_success = false;
		}
		if(if_success){
			File file = new File(fileoutput);
			File file2 = new File(filepath);
			if(file2.delete()){
				if(file.renameTo(file2)){
					if_success = true;
				}else{
					if_success = false;
				}
			}else{
				if_success = false;
			}
		}
		return if_success;
	}
	public static void main(String[] args) {
//		System.err.println(readTxtFile("D:\\MAP_JS_NEW\\WebRoot\\OpMaintance\\js\\test.js").toString());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("url", "123.456.789");
		map.put("maxZoon", "9999");
		map.put("minZoon", "NULL");
		if(writeTxtFile(map)){
			System.out.println("success");
		}else{
			System.out.println("error");
		}
	}
}
