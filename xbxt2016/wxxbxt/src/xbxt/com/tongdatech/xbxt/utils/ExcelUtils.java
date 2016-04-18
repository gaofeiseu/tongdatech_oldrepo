package com.tongdatech.xbxt.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class ExcelUtils {
	//private static final String VERSION = "2.3.23";
	
	/**
	 * 
	 * @param dataMap	数据集
	 * @param templateName	模板名
	 * @param outputFolderPath	输出文件路径
	 * @param outputFilename	输出文件名
	 */
	public static void createExcel(Object data_obj,String templateName,String outputFolderPath,String outputFilename){
		try{
			Configuration config = new Configuration();
			config.setDefaultEncoding("UTF-8");
			config.setClassForTemplateLoading(ExcelUtils.class, "/com/tongdatech/xbxt/template");
			Template template = config.getTemplate(templateName);
			File outFile = new File(outputFolderPath+File.separator+outputFilename);
			if(!outFile.getParentFile().exists()){
				outFile.getParentFile().mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
			template.process(data_obj, out);
			out.flush();
			out.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
