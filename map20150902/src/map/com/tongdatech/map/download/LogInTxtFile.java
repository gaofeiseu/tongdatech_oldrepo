package com.tongdatech.map.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogInTxtFile {
	private static final String path = "E:/googletile_21/log/";
	private static String filenameTemp;
	public static void main(String[] args) {
        try {
            for (int i = 0; i < 5; i++) {
            	LogInTxtFile.creatTxtFile("test");
            	LogInTxtFile.writeTxtFile("显示的是追加的信息" + i);
                String str = LogInTxtFile.readDate();
                System.out.println("*********\n" + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static String getNowTime(){
		String nowTime = "";
		SimpleDateFormat df = new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss_sss");
		nowTime = df.format(new Date());
		return nowTime;
	}
	public static boolean creatTxtFile(String name) throws IOException {
         boolean flag = false;
         filenameTemp = path + name + getNowTime()+".txt";
         File filename = new File(filenameTemp);
         if (!filename.exists()) {
             filename.createNewFile();
             flag = true;
         }
         return flag;
	}
	
	public static boolean writeTxtFile(String newStr) throws IOException {
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
        String filein = newStr + "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
            File file = new File(filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }
	public static String readDate() {
        // 定义一个待返回的空字符串
        String strs = "";
        try {
            FileReader read = new FileReader(new File(filenameTemp));
            StringBuffer sb = new StringBuffer();
            char ch[] = new char[1024];
            int d = read.read(ch);
            while (d != -1) {
                String str = new String(ch, 0, d);
                sb.append(str);
                d = read.read(ch);
            }
            System.out.print(sb.toString());
            String a = sb.toString().replaceAll("@@@@@", ",");
            strs = a.substring(0, a.length() - 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strs;
    }

}
