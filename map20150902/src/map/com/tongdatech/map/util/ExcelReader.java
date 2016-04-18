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
 * Excel操作类
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
		//标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("Excel文件的列总数为："+colNum);
		for(int i=0;i<colNum;i++){
			list_row.add(getCellFormatValue(row.getCell(i)));
		}
		excelBean.setList_title(list_row);
		return;
	}
//	/**
//	 * 获取单元格数据内容为字符串类型的数据
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
     * 读取Excel数据内容
     * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
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
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            List<String> list_row = new ArrayList<String>();
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
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
//     * 获取单元格数据内容为日期类型的数据
//     * 
//     * @param cell
//     *            Excel单元格
//     * @return String 单元格数据内容
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
//                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
//            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
//                result = "";
//            }
//        } catch (Exception e) {
//            System.out.println("日期格式不正确!");
//            e.printStackTrace();
//        }
//        return result;
//    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case Cell.CELL_TYPE_NUMERIC:
            case Cell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                	cell.setCellType(Cell.CELL_TYPE_STRING);
                	cellvalue = cell.getStringCellValue();
                    //cellvalue = String.valueOf(cell.getNumericCellValue())+"@Num";
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case Cell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
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
    	// 对读取Excel表格标题测试
		bean.setFilepath(filepath);
        ExcelReader excelReader = new ExcelReader(bean);
        excelReader.readExcelTitle();
        excelReader.readExcelContent();
        System.out.println("获得Excel表格的标题:");
        bean = excelReader.getExcelBean();
        for (String s : bean.getList_title()) {
            System.out.print(s + "#");
        }
        System.out.println("");
        // 对读取Excel表格内容测试
        System.out.println("获得Excel表格的内容:");
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
