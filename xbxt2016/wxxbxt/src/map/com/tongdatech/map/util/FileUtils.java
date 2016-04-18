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
	 * ��ȡ��ǰϵͳ���ļ��ָ���
	 * 
	 * @return
	 */
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}

	/**
	 * Excelģ�����ɺ���
	 * @param excel_output_url	Excelģ�����·��
	 * @param excel_sheetname	Excelģ���sheet����
	 * @param list_columnname	Excelģ���Ӧ������
	 * @return
	 */
	public static boolean createExcel(String excel_output_url,String excel_sheetname,
			List<String> list_columnname,List<String> list_comments) {
		boolean if_success = false;

		try {
			// �����µ�Excel ������
			HSSFWorkbook workbook = new HSSFWorkbook();
			// ��Excel�������н�һ����������Ϊȱʡֵ, Ҳ����ָ��Sheet����
			// HSSFSheet sheet = workbook.createSheet();
			HSSFSheet sheet = workbook.createSheet(excel_sheetname);
			// ���ڸ�ʽ����Ԫ�������
			//HSSFDataFormat format = workbook.createDataFormat();
			// ��������(row),������Ԫ��(cell)��������. �кŴ�0��ʼ����.
			HSSFRow row = sheet.createRow((short) 0);//����������----0���ǵ�һ��
			// ��������
			HSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short) 20); // ����߶�
			font.setColor(Font.COLOR_RED); // ������ɫ
			font.setFontName("����"); // ����
			font.setBoldweight(Font.BOLDWEIGHT_BOLD); // ���
			font.setItalic(false); // �Ƿ�ʹ��б��
			// font.setStrikeout(true); //�Ƿ�ʹ�û���

			// ���õ�Ԫ������
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // ˮƽ���֣�����
			cellStyle.setWrapText(true);//�Զ����У�

			// ��ӵ�Ԫ��ע��
			// ����HSSFPatriarch����,HSSFPatriarch������ע�͵�����.
			HSSFPatriarch patr = sheet.createDrawingPatriarch();

			// ������Ԫ��
			HSSFCell cell;
			for(int i=0;i<list_columnname.size();i++){
				cell = row.createCell(i);
				HSSFRichTextString hssfString = new HSSFRichTextString(list_columnname.get(i));
				cell.setCellValue(hssfString);// ���õ�Ԫ������
				cell.setCellStyle(cellStyle);// ���õ�Ԫ����ʽ
				//cellStyle.setDataFormat(format.getFormat("0.0"));
				cell.setCellType(Cell.CELL_TYPE_STRING);// ָ����Ԫ���ʽ����ֵ����ʽ���ַ���
				
				// ����ע�͵Ĵ�С��λ��,����ĵ�
				HSSFComment comment = patr.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) (i+1), 2, (short) (i+4), 7));
				// ����ע������
				comment.setString(new HSSFRichTextString(list_comments.get(i)));
				// ����ע������. ������ƶ�����Ԫ�����ǿ�����״̬���п���������.
				comment.setAuthor("gaofeiseu");
				
				cell.setCellComment(comment);// ���ע��
			}
			
			for(int i=0;i<list_columnname.size();i++){
				sheet.autoSizeColumn((short) i); // ������һ�п��
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
//			list.add("��"+i+"��");
//		}
//		String url = "E:/Workspaces/googlemap/bdm/WebRoot/ExcelSpace/aaee.xls";
//		createExcel(url, list);
	}
}
