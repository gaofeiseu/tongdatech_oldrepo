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
	 * ���ļ�List�е������ļ������zipFileName��ZIP����
	 * @param filepath_list
	 * @param zipFileName
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void zipFile(List<String> filepath_list,String zipFileName) throws FileNotFoundException, UnsupportedEncodingException {
		
		BufferedOutputStream bos = null;

		FileOutputStream out = null;

		ZipOutputStream zOut = null;
		
		// �����ļ��������out,��ʾ:ע������֧��

		out = new FileOutputStream(new String(zipFileName.getBytes(ENCODE)));

		bos = new BufferedOutputStream(out);

		// ���ļ�ݔ��ZIP�����������

		zOut = new ZipOutputStream(bos);
		
		for(int i=0;i<filepath_list.size();i++){
			String inputFilePath = filepath_list.get(i);
			File inputFile = new File(inputFilePath);

			if (!inputFile.exists())

				throw new RuntimeException("ԭʼ�ļ�������!!!");

			File basetarZipFile = new File(zipFileName).getParentFile();

			if (!basetarZipFile.exists() && !basetarZipFile.mkdirs())

				throw new RuntimeException("Ŀ���ļ��޷�����!!!");

			try {
				zip(zOut, inputFile, inputFile.getName());

			} catch (Exception e) {

				e.printStackTrace();

			}
		}
		CloseIoUtil.closeAll(zOut, bos, out);
		
	}

	/**
	 * ����������ļ�����������ļ����д���� ��Ҫ���ڶ��ļ������һ��ZIP��
	 * 
	 * @param inputFile
	 * @param ouputStream
	 */
	public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
		try {
			if (inputFile.exists()) {
				/**
				 * �����Ŀ¼�Ļ������ǲ���ȡ�����ģ� ����Ŀ¼�Ĵ�������о���
				 **/
				if (inputFile.isFile()) {
					FileInputStream IN = new FileInputStream(inputFile);
					BufferedInputStream bins = new BufferedInputStream(IN, 512);
					// org.apache.tools.zip.ZipEntry
					ZipEntry entry = new ZipEntry(inputFile.getName());
					ouputStream.putNextEntry(entry);
					// ��ѹ���ļ����������
					int nNumber;
					byte[] buffer = new byte[512];
					while ((nNumber = bins.read(buffer)) != -1) {
						ouputStream.write(buffer, 0, nNumber);
					}
					// �رմ�����������
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

			throw new RuntimeException("ԭʼ�ļ�������!!!");

		File basetarZipFile = new File(zipFileName).getParentFile();

		if (!basetarZipFile.exists() && !basetarZipFile.mkdirs())

			throw new RuntimeException("Ŀ���ļ��޷�����!!!");

		BufferedOutputStream bos = null;

		FileOutputStream out = null;

		ZipOutputStream zOut = null;

		try {

			// �����ļ��������out,��ʾ:ע������֧��

			out = new FileOutputStream(new String(zipFileName.getBytes(ENCODE)));

			bos = new BufferedOutputStream(out);

			// ���ļ�ݔ��ZIP�����������

			zOut = new ZipOutputStream(bos);

			zip(zOut, inputFile, inputFile.getName());

			CloseIoUtil.closeAll(zOut, bos, out);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private static void zip(ZipOutputStream zOut, File file, String base) {

		try {

			// ����ļ������Ŀ¼

			if (file.isDirectory()) {

				// ��ȡĿ¼�µ��ļ�

				File[] listFiles = file.listFiles();

				// ����ZIP��Ŀ

				zOut.putNextEntry(new ZipEntry(base + "/"));

				base = (base.length() == 0 ? "" : base + "/");

				if (listFiles != null && listFiles.length > 0)

					// ����Ŀ¼���ļ�

					for (File f : listFiles)

						// �ݹ���뱾����

						zip(zOut, f, base + f.getName());

			}

			// ����ļ�������ļ�

			else {

				if (base == "") {

					base = file.getName();

				}

				// �����ļ����

				zOut.putNextEntry(new ZipEntry(base));

				// ��ʼѹ��

				// ���ļ�������,д��ZIP ����

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
	 * ��ѹ
	 * 
	 * 
	 * 
	 * @param zipPath
	 * 
	 *            zip�ļ�·��
	 * 
	 * @param destinationPath
	 * 
	 *            ��ѹ��Ŀ�ĵص�
	 * 
	 * @param ecode
	 * 
	 *            �ļ����ı����ַ���
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

		String dir = new String("F:\\�ҵı���\\�ĵ�\\MyEclipse+9.0��ʽ���ƽ��뼤��(�ײ����)");

		dir = new String("F:/111.JPG");

		zip(dir, "f:/BZBXB/zipant.zip");

		unZip("f:/BZBXB/zipant.zip", "f:/XX/xx/");

	}

}
