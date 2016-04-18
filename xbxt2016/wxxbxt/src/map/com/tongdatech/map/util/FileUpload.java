package com.tongdatech.map.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;




/**
 * CPNAMS
 * @author 
 * @since 
 * @version 1.0 
 */
public class FileUpload {
	private final Log logger = LogFactory.getLog(FileUpload.class);
	private String FILE_UPLOAD_PATH = "";


	public FileUpload(String filePath){
		this.FILE_UPLOAD_PATH = filePath;
	}
	
		/**
		 * 
		 * 上传任意文件
		 * @param request
		 * 返回类型，文件名
		 */
		@SuppressWarnings("unchecked")
		public static String upload(File sourceFile,String filename) {
			String name=filename;
		
				Random random = new Random();
				StringBuffer randomnum = new StringBuffer();
				for(int i = 0; i < 3; i++)
				{
					  randomnum.append(random.nextInt(10));//取三个随机数追加到StringBuffer
				}
					
				//文件路径+文件名  
				//name=getFileNameNoEx(name)+"_"+ System.currentTimeMillis()+"_"+randomnum+"."+getExtensionName(name);  
				name=UUID.randomUUID().toString()+"."+getExtensionName(name);
				String webrootPath = ServletActionContext.getServletContext().getRealPath("/");
				String filefolderPath = webrootPath+"/MarkerImage"+System.getProperty("file.separator");
				
				System.out.println("filepath:"+filefolderPath);
				
				System.out.println("filepath:"+filefolderPath+name);
				
				if(sourceFile.renameTo(new File(filefolderPath+name))){
					System.out.println("上传成功！");
				 
				}else{
					System.out.println("上传失败！");
				 
				}
//				FileOutputStream fos=new FileOutputStream(new File(filefolderPath+name));
//				RandomAccessFile raf=new RandomAccessFile(sourceFile, "rw");
//				 byte[]  buff=new byte[1024];  
//		            //用于保存实际读取的字节数  
//		         int hasRead=0;  
//		            //循环读取  
//		            while((hasRead=raf.read(buff))>0){  
//		                //打印读取的内容,并将字节转为字符串输入  
//		            	fos.write(raf.read(buff));
//		                  
//		            }  
//					
//				
//				fos.flush();
//				fos.close();
//			    raf.close();
		
			return "/MarkerImage/"+name;
		}
		public static String  getFileName(String filename)
		{
		    Random random = new Random();
	        StringBuffer randomnum = new StringBuffer();
	        for(int i = 0; i < 3; i++)
	        {
	        	randomnum.append(random.nextInt(10));//取三个随机数追加到StringBuffer
	        }
			return  getFileNameNoEx(filename)+"_"+ System.currentTimeMillis()+"_"+randomnum+"."+getExtensionName(filename);  
		        
		}
		
		/* 
		 * Java文件操作 获取不带扩展名的文件名 
		 * 
		 *   
		 *      Author: mll 
		 */  
		public static  String getFileNameNoEx(String filename) {   
		        if ((filename != null) && (filename.length() > 0)) {   
		            int dot = filename.lastIndexOf('.');   
		            if ((dot >-1) && (dot < (filename.length()))) {   
		                return filename.substring(0, dot);   
		            }   
		        }   
		        return filename;   
		 } 
		/* 
		* Java文件操作 获取文件扩展名 
		* 
		*  
		*      Author: mll 
		*/  
		public static  String getExtensionName(String filename) {   
			     if ((filename != null) && (filename.length() > 0)) {   
			          int dot = filename.lastIndexOf('.');   
			          if ((dot >-1) && (dot < (filename.length() - 1))) {   
			              return filename.substring(dot + 1);   
			          }   
			    }   
			  return filename;   
		} 
		
		public static void copyInputStreamToFile(InputStream is, File output)
		throws IOException {

			if (!output.exists()) {
				output.createNewFile();
			}
		
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(output));
			byte[] buff = new byte[8096];
		
			int k = -1;
			while ((k = is.read(buff)) != -1) {
		
				bos.write(buff, 0, k);
		
			}
			bos.close();
			is.close();
		
		}
		
}
