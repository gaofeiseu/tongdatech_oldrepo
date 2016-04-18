package com.tongdatech.map.util;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

public class FileUtils {
	/**
	 * 获取当前系统的文件分隔符
	 * 
	 * @return
	 */
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}

	/**
	 * Excel模板生成函数
	 * @param excel_output_url	Excel模板输出路径
	 * @param excel_sheetname	Excel模板的sheet名称
	 * @param list_columnname	Excel模板对应的列名
	 * @return
	 */
	public static boolean createExcel(String excel_output_url,String excel_sheetname,
			List<String> list_columnname,List<String> list_comments) {
		boolean if_success = false;

		try {
			// 创建新的Excel 工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
			// HSSFSheet sheet = workbook.createSheet();
			HSSFSheet sheet = workbook.createSheet(excel_sheetname);
			// 用于格式化单元格的数据
			//HSSFDataFormat format = workbook.createDataFormat();
			// 创建新行(row),并将单元格(cell)放入其中. 行号从0开始计算.
			HSSFRow row = sheet.createRow((short) 0);//创建列名行----0就是第一行
			// 设置字体
			HSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short) 20); // 字体高度
			font.setColor(Font.COLOR_RED); // 字体颜色
			font.setFontName("宋体"); // 字体
			font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 宽度
			font.setItalic(false); // 是否使用斜体
			// font.setStrikeout(true); //是否使用划线

			// 设置单元格类型
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平布局：居中
			cellStyle.setWrapText(true);//自动换行？

			// 添加单元格注释
			// 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.
			HSSFPatriarch patr = sheet.createDrawingPatriarch();

			// 创建单元格
			HSSFCell cell;
			for(int i=0;i<list_columnname.size();i++){
				cell = row.createCell(i);
				HSSFRichTextString hssfString = new HSSFRichTextString(list_columnname.get(i));
				cell.setCellValue(hssfString);// 设置单元格内容
				cell.setCellStyle(cellStyle);// 设置单元格样式
				//cellStyle.setDataFormat(format.getFormat("0.0"));
				cell.setCellType(Cell.CELL_TYPE_STRING);// 指定单元格格式：数值、公式或字符串
				
				// 定义注释的大小和位置,详见文档
				HSSFComment comment = patr.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) (i+1), 2, (short) (i+4), 7));
				// 设置注释内容
				comment.setString(new HSSFRichTextString(list_comments.get(i)));
				// 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.
				comment.setAuthor("gaofeiseu");
				
				cell.setCellComment(comment);// 添加注释
			}
			
			for(int i=0;i<list_columnname.size();i++){
				sheet.autoSizeColumn((short) i); // 调整第一列宽度
			}

			try {
				FileOutputStream fileOut = new FileOutputStream(
						excel_output_url);
				workbook.write(fileOut);
				fileOut.close();
				if_success=true;
			} catch (Exception e) {
				e.printStackTrace();
				if_success = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if_success = false;
		}
		return if_success;
	}
	
	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>();
//		for(int i=0;i<10;i++){
//			list.add("第"+i+"列");
//		}
//		String url = "E:/Workspaces/googlemap/bdm/WebRoot/ExcelSpace/aaee.xls";
//		createExcel(url, list);
	}
}
