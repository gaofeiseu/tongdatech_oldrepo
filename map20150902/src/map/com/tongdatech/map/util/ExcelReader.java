package com.tongdatech.map.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * Excel������
 * @author Mr.GaoFei
 *
 */
public class ExcelReader {
	private String filepath;
	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;
	private ExcelBean excelBean;
	public ExcelReader(ExcelBean bean){
		this.excelBean=bean;
		this.filepath=bean.getFilepath();
	}
	public void readExcelTitle(){
		List<String> list_row = new ArrayList<String>();
		try {
			InputStream is = new FileInputStream(filepath);
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
			sheet = wb.getSheetAt(0);
			row = sheet.getRow(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//����������
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("Excel�ļ���������Ϊ��"+colNum);
		for(int i=0;i<colNum;i++){
			list_row.add(getCellFormatValue(row.getCell(i)));
		}
		excelBean.setList_title(list_row);
		return;
	}
//	/**
//	 * ��ȡ��Ԫ����������Ϊ�ַ������͵�����
//	 * @param cell
//	 * @return
//	 */
//	private String getStringCellValue(HSSFCell cell) {
//        String strCell = "";
//        switch (cell.getCellType()) {
//        case HSSFCell.CELL_TYPE_STRING:
//            strCell = cell.getStringCellValue();
//            break;
//        case HSSFCell.CELL_TYPE_NUMERIC:
//            strCell = String.valueOf(cell.getNumericCellValue());
//            break;
//        case HSSFCell.CELL_TYPE_BOOLEAN:
//            strCell = String.valueOf(cell.getBooleanCellValue());
//            break;
//        case HSSFCell.CELL_TYPE_BLANK:
//            strCell = "";
//            break;
//        default:
//            strCell = "";
//            break;
//        }
//        if (strCell.equals("") || strCell == null) {
//            return "";
//        }
//        if (cell == null){
//        	return "";
//        }
//        return strCell;
//    }
	
	/**
     * ��ȡExcel��������
     * @param InputStream
     * @return Map ������Ԫ���������ݵ�Map����
     */
    public void readExcelContent() {
        List<List<String>> list_rows = new ArrayList<List<String>>();
        try {
        	InputStream is = new FileInputStream(filepath);
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            List<String> list_row = new ArrayList<String>();
            while (j < colNum) {
                // ÿ����Ԫ�������������"-"�ָ���Ժ���Ҫʱ��String���replace()������ԭ����
                // Ҳ���Խ�ÿ����Ԫ����������õ�һ��javabean�������У���ʱ��Ҫ�½�һ��javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                list_row.add(getCellFormatValue(row.getCell(j)).trim());
                j++;
            }
            list_rows.add(list_row);
        }
        excelBean.setList_rows(list_rows);
        return;
    }
//    /**
//     * ��ȡ��Ԫ����������Ϊ�������͵�����
//     * 
//     * @param cell
//     *            Excel��Ԫ��
//     * @return String ��Ԫ����������
//     */
//    private String getDateCellValue(HSSFCell cell) {
//        String result = "";
//        try {
//            int cellType = cell.getCellType();
//            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
//                Date date = cell.getDateCellValue();
//                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
//                        + "-" + date.getDate();
//            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
//                String date = getStringCellValue(cell);
//                result = date.replaceAll("[����]", "-").replace("��", "").trim();
//            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
//                result = "";
//            }
//        } catch (Exception e) {
//            System.out.println("���ڸ�ʽ����ȷ!");
//            e.printStackTrace();
//        }
//        return result;
//    }

    /**
     * ����HSSFCell������������
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // �жϵ�ǰCell��Type
            switch (cell.getCellType()) {
            // �����ǰCell��TypeΪNUMERIC
            case Cell.CELL_TYPE_NUMERIC:
            case Cell.CELL_TYPE_FORMULA: {
                // �жϵ�ǰ��cell�Ƿ�ΪDate
                if (DateUtil.isCellDateFormatted(cell)) {
                    // �����Date������ת��ΪData��ʽ
                    
                    //����1�������ӵ�data��ʽ�Ǵ�ʱ����ģ�2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //����2�������ӵ�data��ʽ�ǲ�����ʱ����ģ�2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // ����Ǵ�����
                else {
                    // ȡ�õ�ǰCell����ֵ
                	cell.setCellType(Cell.CELL_TYPE_STRING);
                	cellvalue = cell.getStringCellValue();
                    //cellvalue = String.valueOf(cell.getNumericCellValue())+"@Num";
                }
                break;
            }
            // �����ǰCell��TypeΪSTRIN
            case Cell.CELL_TYPE_STRING:
                // ȡ�õ�ǰ��Cell�ַ���
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // Ĭ�ϵ�Cellֵ
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
    
    public ExcelBean getExcelBean(){
    	return excelBean;
    }
    public static void main(String[] args) {
    	String filepath = "E:/Workspaces/googlemap/bdm/WebRoot/ExcelSpace/ExcelUpload/69557adf-0deb-4ff8-b4b7-0d06634d3085.xls";
    	ExcelBean bean = new ExcelBean();
    	// �Զ�ȡExcel���������
		bean.setFilepath(filepath);
        ExcelReader excelReader = new ExcelReader(bean);
        excelReader.readExcelTitle();
        excelReader.readExcelContent();
        System.out.println("���Excel���ı���:");
        bean = excelReader.getExcelBean();
        for (String s : bean.getList_title()) {
            System.out.print(s + "#");
        }
        System.out.println("");
        // �Զ�ȡExcel������ݲ���
        System.out.println("���Excel��������:");
        for(int i=0;i<bean.getList_rows().size();i++){
        	for(int j=0;j<bean.getList_rows().get(i).size();j++){
        		System.out.print(bean.getList_rows().get(i).get(j)+"@");
        		if(j==(bean.getList_rows().get(i).size()-1)){
        			System.out.println("");
        		}
        	}
        }
	}
}
