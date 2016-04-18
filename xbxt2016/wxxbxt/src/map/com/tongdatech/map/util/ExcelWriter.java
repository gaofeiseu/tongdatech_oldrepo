package com.tongdatech.map.util;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

public class ExcelWriter {
	public boolean writeExcel(ExcelWriteBean bean){
		boolean if_success = false;
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = null;
			if("".equals(bean.getSheetname())||bean.getSheetname()==null){
				sheet = wb.createSheet();
			}else{
				sheet = wb.createSheet(bean.getSheetname());
			}
			sheet.setColumnWidth(0, 12000);
			sheet.setColumnWidth(1, 10000);
			sheet.setColumnWidth(2, 10000);
			HSSFRow row = sheet.createRow(0);//创建标题行
			HSSFCellStyle align_style = wb.createCellStyle();
			align_style.setAlignment(CellStyle.ALIGN_CENTER);//创建一个居中格式
			HSSFCell cell = null;
			for(int i=0;i<bean.getList_title().size();i++){
				cell = row.createCell(i);
				cell.setCellValue(bean.getList_title().get(i));
				cell.setCellStyle(align_style);
			}
			for(int i=0;i<bean.getList_rows().size();i++){
				//遍历行
				row = sheet.createRow(i+1);
				for(int j=0;j<bean.getList_colname().size();j++){
					//遍历列
					row.createCell(j).setCellValue(String.valueOf(bean.getList_rows().get(i).get(bean.getList_colname().get(j))));
				}
			}
			try {
				FileOutputStream fout = new FileOutputStream(bean.getFilepath()+bean.getExcelFilename());
				wb.write(fout);
				fout.close();
				if_success = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if_success = false;
		}
		return if_success;
	}
	
	public boolean writeExcel2(ExcelWriteBean bean){
		boolean if_success = false;
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = null;
			if("".equals(bean.getSheetname())||bean.getSheetname()==null){
				sheet = wb.createSheet();
			}else{
				sheet = wb.createSheet(bean.getSheetname());
			}
			for(int i=0;i<bean.getList_title().size();i++){
				sheet.setColumnWidth(i, 6000);
			}
			HSSFRow row = sheet.createRow(0);//创建标题行
			HSSFCellStyle align_style = wb.createCellStyle();
			align_style.setAlignment(CellStyle.ALIGN_CENTER);//创建一个居中格式
			HSSFCell cell = null;
			for(int i=0;i<bean.getList_title().size();i++){
				cell = row.createCell(i);
				cell.setCellValue(bean.getList_title().get(i));
				cell.setCellStyle(align_style);
			}
			for(int i=0;i<bean.getList_rows().size();i++){
				//遍历行
				row = sheet.createRow(i+1);
				for(int j=0;j<bean.getList_colname().size();j++){
					//遍历列
					row.createCell(j).setCellValue(String.valueOf(bean.getList_rows().get(i).get(bean.getList_colname().get(j))));
				}
			}
			try {
				FileOutputStream fout = new FileOutputStream(bean.getFilepath()+bean.getExcelFilename());
				wb.write(fout);
				fout.close();
				if_success = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if_success = false;
		}
		return if_success;
	}
}
