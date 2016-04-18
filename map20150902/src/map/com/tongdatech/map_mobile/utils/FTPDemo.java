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
	 * @param path	�ϴ���FTP���������ĸ�·����
	 * @param addr	FTP��������Զ�����ӵ�ַ
	 * @param port	FTP����Ķ˿ںţ�һ����21
	 * @param username	FTP���û���
	 * @param password	FTP������
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
	        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);//�Զ�������ʽ�����ļ�����
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
	 * �ϴ�ָ���ļ���FTP��������FTP�ϴ�������connect������������
	 * @param file	��Ҫ�ϴ���FTP���������ļ�
	 * @param path	�ϴ���FTP���������ĸ�·����
	 * @param addr	FTP��������Զ�����ӵ�ַ
	 * @param port	FTP�������Ķ˿ںţ�һ����21
	 * @param username	FTP���û���
	 * @param password	FTP������
	 * @return
	 */
	public boolean uploadFileWithFTP(File file,String path,String addr,int port,String username,String password){
		boolean status = false;
		FileInputStream is = null;
		try{
			if(file.exists()){
				if(file.isFile()){
					//ͨ������У�飬�ļ����ڣ����ļ�������ʵ
					if(ftp==null||!ftp.isConnected()){
						//���ftp����û�б���ʼ������������ȷ�ĳ�ʼ��ftp����
						if(connect(path,addr,port,username,password)){
							is = new FileInputStream(file);
							if(ftp.storeFile(file.getName(), is)){
								status = true;
								System.out.println("++++++++++++++++++++++++++++++++++++++++++�ֻ�ͼƬFTPͬ���ɹ�++++++++++++++++++++++++++++++++++++++");
							}else{
								status = false;
								throw new Exception("FTP�ϴ����̷��������ϴ�ʧ�ܣ�");
							}
						}else{
							status = false;
							throw new Exception("FTP���󴴽�ʧ�ܣ�");
						}
					}
				}else{
					status = false;
					throw new Exception("�ϴ�FTP��������ֻ�����ļ����������κ��������ļ����ͣ�");
				}
			}else{
				status = false;
				throw new Exception("��ǰ��Ҫ����FTP�ϴ��������ļ������ڣ�"+file.getPath());
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
					System.out.println("FileInputStream�ͷ�ʧ�ܣ�");
				}
			}
			disconnect();
			System.out.println("++++++++++++++++++++++++++++++++++++++++++FTP���������++++++++++++++++++++++++++++++++++++++");
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
			System.out.println("++++++++++++++++++++++++++++++++++++++++++FTP�Ѿ��ɹ��Ͽ�++++++++++++++++++++++++++++++++++++++");
		}else{
			System.out.println("++++++++++++++++++++++++++++++++++++++++++FTP�Ͽ�ʧ�ܣ������־�ļ�������Ϣ��++++++++++++++++++++++++++++++++++++++");
		}
	}
	/**
	 * ͨ��FTP�����ļ�
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
