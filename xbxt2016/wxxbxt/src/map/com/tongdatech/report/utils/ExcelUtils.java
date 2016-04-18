package com.tongdatech.report.utils;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.tongdatech.report.bean.ExcelBean;
import com.tongdatech.report.bean.TitleBean;

public class ExcelUtils {
	public boolean writeExcel(ExcelBean bean){
		boolean if_success = false;
		try{
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = null;
			if(bean.getSheetName()==null||"".equals(bean.getSheetName())){
				sheet = wb.createSheet();
			}else{
				sheet = wb.createSheet(bean.getSheetName());
			}
			for(int i=0;i<bean.getList_colname().size();i++){
				sheet.autoSizeColumn(i+1);
			}
			List<List<TitleBean>> list_list_beantitle = new ArrayList<List<TitleBean>>();
			for(List<TitleBean> list : list_list_beantitle){
				for(TitleBean title : list){
					sheet.addMergedRegion(new CellRangeAddress(title.getFirst_row(),title.getLast_row(),title.getFirst_col(),title.getLast_col()));
				}
			}
			List<List<TitleBean>> list_list_titlebean = bean.getList_list_titlebean();
			List<Map<String,Object>> list_data = bean.getList_rows();
			int title_size = list_list_titlebean.size();
			int data_size = list_data.size();
			HSSFCellStyle cellstyle = wb.createCellStyle();
			cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFRow row = null;
			HSSFCell cell = null;
			List<TitleBean> list_final_title = list_list_titlebean.get(list_list_titlebean.size()-1);
			Pattern p = Pattern.compile("^//d+(//.//d+)?$");
            Matcher matcher = null;
			for(int i=0;i<(title_size+data_size);i++){
				row = sheet.createRow(i);
				if(i<title_size){
					//title row
					List<TitleBean> list_titlebean = list_list_titlebean.get(i);
					for(int j=0;j<list_titlebean.size();j++){
						cell = row.createCell(list_titlebean.get(j).getFirst_row());
						cell.setCellValue(list_titlebean.get(j).getStr());
						cell.setCellStyle(cellstyle);
					}
				}else{
					//data row
					for(int j=0;j<list_final_title.size();j++){
						cell = row.createCell(j);
						String str = list_final_title.get(j).getStr();
						matcher = p.matcher(str);
						if (matcher.matches()) {
                            //double
                            cell.setCellValue(Double.parseDouble(str));
                        } else {
                            HSSFRichTextString richString = new HSSFRichTextString(str);
                            cell.setCellValue(richString);
                        }
						cell.setCellValue(list_final_title.get(j).getStr());
						cell.setCellStyle(cellstyle);
					}
				}
			}
			try {
				FileOutputStream fout = new FileOutputStream(bean.getFilePath()+bean.getExcelFileName());
				wb.write(fout);
				fout.close();
				if_success = true;
			} catch (Exception e) {
				throw e;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			if_success = false;
		}
		return if_success;
	}
}
