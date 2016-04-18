package com.tongdatech.sys.util.excel;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import com.tongdatech.sys.util.ReflectionUtil;



/**
 * 报表通用excel导出模版       <br>
 * @author xl 
 * @version    2014-4-11 下午02:59:34
 */
public class ExcelForReport extends ExcelBean {

	public ExcelForReport(int excelEdition) {
		super(excelEdition);
		
	}

	@Override
	protected void bulidHead() {
		rownum=0;
		Row r = sheet.createRow(rownum);
		r.setHeightInPoints(18);
		Cell c = r.createCell(0);
		c.setCellValue(createHelper.createRichTextString(headtext));
		sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,0,colnum-1));
		
		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		//cs.cloneStyleFrom(cellStyle);
		Font ff=wb.createFont();
		ff.setFontName("黑体");
		ff.setBoldweight(Font.BOLDWEIGHT_BOLD);
		ff.setFontHeightInPoints((short) 14);
		cs.setFont(ff);
		c.setCellStyle(cs);

	}

	@Override
	protected void bulidSubHead() {
		if(subtext==null) return;
		CellStyle cs1 = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();
		CellStyle cs3 = wb.createCellStyle();
		cs1.setAlignment(CellStyle.ALIGN_LEFT);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);
		cs3.setAlignment(CellStyle.ALIGN_RIGHT);
	

		
		
		rownum=sheet.getLastRowNum()+1;
		Row r = sheet.createRow(rownum);
		r.setHeightInPoints(14);
		Cell c=null;Cell c2=null;Cell c3=null;
	    if(subtext.length==1){
	    	c = r.createCell(0);
	    	c.setCellValue(createHelper.createRichTextString(subtext[0]));
	    	sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,0,colnum-1));
	   
	    }else if(subtext.length==2){
	    	c = r.createCell(0);
	    	c.setCellValue(createHelper.createRichTextString(subtext[0]));
	    	c2 = r.createCell(colnum/2);
	    	c2.setCellValue(createHelper.createRichTextString(subtext[1]));
	    	sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,0,colnum/2-1));
	    	sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colnum/2,colnum-1));
	  
	    }else if(subtext.length>=3){
	    	c = r.createCell(0);
	    	c.setCellValue(createHelper.createRichTextString(subtext[0]));
	    	c2 = r.createCell(colnum/3);
	    	c2.setCellValue(createHelper.createRichTextString(subtext[1]));
	    	c3 = r.createCell(2*colnum/3);
	    	c3.setCellValue(createHelper.createRichTextString(subtext[2]));
	    	sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,0,colnum/3-1));
	    	sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,colnum/3,2*colnum/3-1));
	    	sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,2*colnum/3,colnum-1));
	  
	    }
	    if(c!=null)c.setCellStyle(cs1);
	    if(c2!=null)c2.setCellStyle(cs2);
	    if(c3!=null)c3.setCellStyle(cs3);
	    if(c2!=null&&c3==null)c2.setCellStyle(cs3);
	 
	


		


	}

	@Override
	protected void bulidTitle() {
		if(titletext==null)return;
		
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();
		cs.cloneStyleFrom(cellStyle);
		cs.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cs2.cloneStyleFrom(cs);
		cs2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		rownum=sheet.getLastRowNum()+1;
		Row r = sheet.createRow(rownum);
		
		if(toptext!=null){
			int j=0;
			for(int i=0;i<toptext.length;i++){
				Cell c = r.createCell(i);
				String text= toptext[i];
				if("".equals(text)){
				}else{
					if(i!=0)sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,j,i-1));
					c.setCellValue(createHelper.createRichTextString(text));	
					j=i;
				}
				c.setCellStyle(cs);

			}
		}
		rownum=rownum+1;
		Row r2 = sheet.createRow(rownum);
		for(int i=0;i<titletext.length;i++){
			Cell c = r2.createCell(i);
			c.setCellValue(createHelper.createRichTextString(titletext[i]));
			c.setCellStyle(cs2);
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void bulidData() {
		if(data==null||data.size()==0)return;
		CellStyle odd = wb.createCellStyle();
		CellStyle even = wb.createCellStyle();
		odd.cloneStyleFrom(cellStyle);
		odd.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		odd.setFillPattern(CellStyle.SOLID_FOREGROUND);
		even.cloneStyleFrom(cellStyle);
		rownum=sheet.getLastRowNum()+1;
	
		
		for(int i=0;i<data.size()&&i<=EXCEL07;i++){
			Row r = sheet.createRow(rownum+i);
			
			Object obj=data.get(i);



			for(int j=0;j<datakey.length;j++){
				Cell c = r.createCell(j);
				
				if(i%2==0){
					c.setCellStyle(odd);
				}else{
					c.setCellStyle(even);
				}
				
				String rcStr="";
				if(obj instanceof Map){
					Object rc = ((Map)obj).get(datakey[j]);
					
					if(rc==null)continue;
					if(rc instanceof Integer){
						int ii=((Integer)rc).intValue();
						rcStr=String.valueOf(ii);				
					}else if(rc instanceof Double){
						double dd=((Double)rc).doubleValue();
						int ii=(int)dd;
						if(dd==ii)rcStr=String.valueOf(ii);
						else rcStr=String.valueOf(dd);
					}else{
						rcStr=rc.toString();
					}
					
				}else {
					try {
				    Object tmp=ReflectionUtil.getFieldValue(obj, datakey[j]);
					if(tmp!=null){
						if(tmp instanceof java.util.Date){
							SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
							rcStr=df.format(tmp);
						}else rcStr=tmp.toString();
						
					}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				c.setCellValue(createHelper.createRichTextString(rcStr));

				
			}
		}
		

	}

	@Override
	protected void bulidFoot() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void freeDraw() {
		// TODO Auto-generated method stub

	}

}
