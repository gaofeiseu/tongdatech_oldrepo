package com.tongdatech.map_mobile.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class FTPDemo {
	private FTPClient ftp;
	/**
	 * 
	 * @param path	上传到FTP服务器的哪个路径下
	 * @param addr	FTP服务器的远程连接地址
	 * @param port	FTP服务的端口号，一般是21
	 * @param username	FTP的用户名
	 * @param password	FTP的密码
	 * @return
	 * @throws Exception
	 */
	private boolean connect(String path,String addr,int port,String username,String password) throws Exception {
		boolean status = false;
		if(ftp!=null&&ftp.isConnected()){
			status = true;
		}else{
			ftp = new FTPClient();
	        int reply_code;
	        ftp.connect(addr,port);
	        ftp.login(username,password);
	        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);//以二进制形式进行文件传送
	        reply_code = ftp.getReplyCode();
	        if (!FTPReply.isPositiveCompletion(reply_code)) {
	            ftp.disconnect();
	            return status;
	        }
	        ftp.changeWorkingDirectory(path);
	        status = true;
		}
		return status;
	}
	/**
	 * 上传指定文件到FTP服务器，FTP上传参数由connect函数进行设置
	 * @param file	需要上传到FTP服务器的文件
	 * @param path	上传到FTP服务器的哪个路径下
	 * @param addr	FTP服务器的远程连接地址
	 * @param port	FTP服务器的端口号，一般是21
	 * @param username	FTP的用户名
	 * @param password	FTP的密码
	 * @return
	 */
	public boolean uploadFileWithFTP(File file,String path,String addr,int port,String username,String password){
		boolean status = false;
		FileInputStream is = null;
		try{
			if(file.exists()){
				if(file.isFile()){
					//通过基本校验，文件存在，且文件类型属实
					if(ftp==null||!ftp.isConnected()){
						//如果ftp对象还没有被初始化，则首先正确的初始化ftp对象
						if(connect(path,addr,port,username,password)){
							is = new FileInputStream(file);
							if(ftp.storeFile(file.getName(), is)){
								status = true;
								System.out.println("++++++++++++++++++++++++++++++++++++++++++手机图片FTP同步成功++++++++++++++++++++++++++++++++++++++");
							}else{
								status = false;
								throw new Exception("FTP上传过程发生错误！上传失败！");
							}
						}else{
							status = false;
							throw new Exception("FTP对象创建失败！");
						}
					}
				}else{
					status = false;
					throw new Exception("上传FTP服务器的只能是文件，不能是任何其他的文件类型！");
				}
			}else{
				status = false;
				throw new Exception("当前需要进行FTP上传操作的文件不存在！"+file.getPath());
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			status = false;
		}
		finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
					status = false;
					System.out.println("FileInputStream释放失败！");
				}
			}
			disconnect();
			System.out.println("++++++++++++++++++++++++++++++++++++++++++FTP服务结束！++++++++++++++++++++++++++++++++++++++");
		}
		return status;
	}
	private void disconnect(){
		boolean if_success = false;
		if(ftp!=null&&ftp.isConnected()){
			try{
				ftp.logout();
				ftp.disconnect();
				ftp = null;
				if_success = true;
			}
			catch(Exception ex){
				if_success = false;
				ex.printStackTrace();
			}
		}else{
			if_success = true;
		}
		if(if_success){
			System.out.println("++++++++++++++++++++++++++++++++++++++++++FTP已经成功断开++++++++++++++++++++++++++++++++++++++");
		}else{
			System.out.println("++++++++++++++++++++++++++++++++++++++++++FTP断开失败，详见日志文件报错信息！++++++++++++++++++++++++++++++++++++++");
		}
	}
	/**
	 * 通过FTP下载文件
	 * @param ftp_addr	FTP_IP
	 * @param ftp_port	FTP_PORT
	 * @param ftp_username	FTP_USERNAME
	 * @param ftp_password	FTP_PASSWORD
	 * @param ftp_remotepath	FTP_REMOTE_PATH
	 * @param ftp_downloadFilename	FTP_DOWNLOAD_FILENAME
	 * @param localPath	local save path(not include filename,not include '/' last)
	 * @return
	 */
	public boolean downloadFileWithFTP(String ftp_addr,int ftp_port,String ftp_username,String ftp_password,String ftp_remotepath,String ftp_downloadFilename,String localPath){
		boolean if_success = false;
		try{
			if(ftp == null || !ftp.isConnected()){
				if(connect(ftp_remotepath, ftp_addr, ftp_port, ftp_username, ftp_password)){
					FTPFile[] files = ftp.listFiles();
					for(FTPFile file:files){
						if(file.getName().equals(ftp_downloadFilename)){
							File localFile = new File(localPath+"/"+file.getName());
							OutputStream os = new FileOutputStream(localFile);
							ftp.retrieveFile(file.getName(), os);
							os.close();
							break;
						}
					}
					if_success = true;
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			if_success = false;
		}
		finally{
			if(ftp!=null&&ftp.isConnected()){
				disconnect();
			}
		}
		return if_success;
	}
	public static void main(String[] args) {
		FTPDemo demo = new FTPDemo();
//		File file = new File("F:/2112_1409994705553226.jpg");
//		demo.uploadFileWithFTP(file, "/ftproot/", "58.214.6.229", 21, "zhuanglei", "Nj123456");
		demo.downloadFileWithFTP("58.214.6.229", 21, "zhuanglei", "Nj123456", "/ftproot/", "2112_1409994705553226.jpg", "F:/123");
	}
	
}
