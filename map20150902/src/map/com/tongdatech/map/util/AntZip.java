package com.tongdatech.map.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class AntZip {
	private static final String ENCODE = "GBK";
	/**
	 * 将文件List中的所有文件打包到zipFileName的ZIP包中
	 * @param filepath_list
	 * @param zipFileName
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void zipFile(List<String> filepath_list,String zipFileName) throws FileNotFoundException, UnsupportedEncodingException {
		
		BufferedOutputStream bos = null;

		FileOutputStream out = null;

		ZipOutputStream zOut = null;
		
		// 创建文件输出对象out,提示:注意中文支持

		out = new FileOutputStream(new String(zipFileName.getBytes(ENCODE)));

		bos = new BufferedOutputStream(out);

		// 將文件輸出ZIP输出流接起来

		zOut = new ZipOutputStream(bos);
		
		for(int i=0;i<filepath_list.size();i++){
			String inputFilePath = filepath_list.get(i);
			File inputFile = new File(inputFilePath);

			if (!inputFile.exists())

				throw new RuntimeException("原始文件不存在!!!");

			File basetarZipFile = new File(zipFileName).getParentFile();

			if (!basetarZipFile.exists() && !basetarZipFile.mkdirs())

				throw new RuntimeException("目标文件无法创建!!!");

			try {
				zip(zOut, inputFile, inputFile.getName());

			} catch (Exception e) {

				e.printStackTrace();

			}
		}
		CloseIoUtil.closeAll(zOut, bos, out);
		
	}

	/**
	 * 根据输入的文件与输出流对文件进行打包， 主要用于多文件打包成一个ZIP包
	 * 
	 * @param inputFile
	 * @param ouputStream
	 */
	public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
		try {
			if (inputFile.exists()) {
				/**
				 * 如果是目录的话这里是不采取操作的， 至于目录的打包正在研究中
				 **/
				if (inputFile.isFile()) {
					FileInputStream IN = new FileInputStream(inputFile);
					BufferedInputStream bins = new BufferedInputStream(IN, 512);
					// org.apache.tools.zip.ZipEntry
					ZipEntry entry = new ZipEntry(inputFile.getName());
					ouputStream.putNextEntry(entry);
					// 向压缩文件中输出数据
					int nNumber;
					byte[] buffer = new byte[512];
					while ((nNumber = bins.read(buffer)) != -1) {
						ouputStream.write(buffer, 0, nNumber);
					}
					// 关闭创建的流对象
					bins.close();
					IN.close();
				} else {
					try {
						File[] files = inputFile.listFiles();
						for (int i = 0; i < files.length; i++) {
							zipFile(files[i], ouputStream);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void zip(String inputFilePath, String zipFileName) {

		File inputFile = new File(inputFilePath);

		if (!inputFile.exists())

			throw new RuntimeException("原始文件不存在!!!");

		File basetarZipFile = new File(zipFileName).getParentFile();

		if (!basetarZipFile.exists() && !basetarZipFile.mkdirs())

			throw new RuntimeException("目标文件无法创建!!!");

		BufferedOutputStream bos = null;

		FileOutputStream out = null;

		ZipOutputStream zOut = null;

		try {

			// 创建文件输出对象out,提示:注意中文支持

			out = new FileOutputStream(new String(zipFileName.getBytes(ENCODE)));

			bos = new BufferedOutputStream(out);

			// 將文件輸出ZIP输出流接起来

			zOut = new ZipOutputStream(bos);

			zip(zOut, inputFile, inputFile.getName());

			CloseIoUtil.closeAll(zOut, bos, out);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private static void zip(ZipOutputStream zOut, File file, String base) {

		try {

			// 如果文件句柄是目录

			if (file.isDirectory()) {

				// 获取目录下的文件

				File[] listFiles = file.listFiles();

				// 建立ZIP条目

				zOut.putNextEntry(new ZipEntry(base + "/"));

				base = (base.length() == 0 ? "" : base + "/");

				if (listFiles != null && listFiles.length > 0)

					// 遍历目录下文件

					for (File f : listFiles)

						// 递归进入本方法

						zip(zOut, f, base + f.getName());

			}

			// 如果文件句柄是文件

			else {

				if (base == "") {

					base = file.getName();

				}

				// 填入文件句柄

				zOut.putNextEntry(new ZipEntry(base));

				// 开始压缩

				// 从文件入流读,写入ZIP 出流

				writeFile(zOut, file);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private static void writeFile(ZipOutputStream zOut, File file)

	throws IOException {

		FileInputStream in = null;

		BufferedInputStream bis = null;

		in = new FileInputStream(file);

		bis = new BufferedInputStream(in);

		int len = 0;

		byte[] buff = new byte[2048];

		while ((len = bis.read(buff)) != -1)

			zOut.write(buff, 0, len);

		zOut.flush();

		CloseIoUtil.closeAll(bis, in);

	}

	/****
	 * 
	 * 解压
	 * 
	 * 
	 * 
	 * @param zipPath
	 * 
	 *            zip文件路径
	 * 
	 * @param destinationPath
	 * 
	 *            解压的目的地点
	 * 
	 * @param ecode
	 * 
	 *            文件名的编码字符集
	 */

	public static void unZip(String zipPath, String destinationPath) {
		System.out.println("unzip destinationPath:"+destinationPath);
		File zipFile = new File(zipPath);

		if (!zipFile.exists())

			throw new RuntimeException("zip file " + zipPath

			+ " does not exist.");

		Project proj = new Project();

		Expand expand = new Expand();

		expand.setProject(proj);

		expand.setTaskType("unzip");

		expand.setTaskName("unzip");

		expand.setSrc(zipFile);

		expand.setDest(new File(destinationPath));

		expand.setEncoding(ENCODE);

		expand.execute();

		System.out.println("unzip done!!!");

	}

	public static void main(String[] args) {

		String dir = new String("F:\\我的备份\\文档\\MyEclipse+9.0正式版破解与激活(亲测可用)");

		dir = new String("F:/111.JPG");

		zip(dir, "f:/BZBXB/zipant.zip");

		unZip("f:/BZBXB/zipant.zip", "f:/XX/xx/");

	}

}
