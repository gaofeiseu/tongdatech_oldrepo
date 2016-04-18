package com.tongdatech.sys.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.UUID;

public class FileUtil {

	/**
	 * 去除文件名中的非法字符
	 * @param filename
	 * @return String
	 */
	public static String checkFileName(String fileName){
		String regex="\\\\|/|:|\\*|\\?|\\\"|<|>|\\|";
		return fileName=fileName.replaceAll(regex, "");
		
	}
	/**
	 * 生成系统存储用文件名
	 * @param fileName
	 * @return String
	 */
	public static String createFileName(String fileName){
		String realName=UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
		return realName;
		
	}
	public static void transferFile(String oldPath,String newPath) throws Exception {  
        int byteread = 0;
        File oldFile = new File(oldPath);
        FileInputStream fin = null;
        FileOutputStream fout = null;
        try{
            if(oldFile.exists()){
                fin = new FileInputStream(oldFile);
                fout = new FileOutputStream(newPath);
                byte[] buffer = new byte[fin.available()];
                while( (byteread = fin.read(buffer)) != -1){
                    fout.write(buffer,0,byteread);
                }
            }else{
                throw new Exception("需要转移的文件不存在!");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            if(fin != null){
                fin.close();
                delFile(oldFile);
            }
            if(fout != null){
            	fout.close();
            }
            
        }
    }
	private static void delFile(File file) throws Exception {  
        if(!file.exists()) {  
            throw new Exception("文件"+file.getName()+"不存在,请确认!");  
        }  
        if(file.isFile()){  
            if(file.canWrite()){  
                file.delete();  
            }else{  
                throw new Exception("文件"+file.getName()+"只读,无法删除,请手动删除!");  
            }  
        }else{  
            throw new Exception("文件"+file.getName()+"不是一个标准的文件,有可能为目录,请确认!");  
        }  
    }
	
	
	public static String formatFileLength(long fileLength){
		long kb = 1024;
		long mb = kb*1024;
		long gb = mb*1024;
		long tb = gb*1024;
		if(fileLength<kb){
			return "1KB";
		}
		if(kb<fileLength && fileLength<mb){
			return fileLength/kb+"KB";
		}
		if(mb<fileLength && fileLength<gb){
			return fileLength/mb+"MB";
		}
		if(gb<fileLength && fileLength<tb){
			return fileLength/gb+"GB";
		}
		return fileLength/tb+"TB";
	}
	public static String getTxt(String path){
		return getTxt(path,0,true);
	}
	public static String getTxt(String path,int n,boolean isbr){
		StringBuffer sb=new StringBuffer();	
		try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));
            int i=0;
            String str=null;
			while((str = br.readLine()) != null)
            {   if(n>0&&i>n)break;
                if(isbr)sb.append(str+"\\n");
                else sb.append(str);
            	i++;
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		return sb.toString();
	}
}
