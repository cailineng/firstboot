package com.lineng.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel文件工具类
 */
public class ExcelUtil {

	@SuppressWarnings("resource")
	public static List<List<String>> readXls(String filePath) {
		List<List<String>> resultList = new ArrayList<List<String>>();
		try {
			InputStream inputStream = getInputStream(filePath);
			if(inputStream != null) {
				//创建整个Excel对象
				HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
				if(workbook != null) {
					//逐个页面读取Excel
					int numberOfSheets = workbook.getNumberOfSheets();
					if(numberOfSheets > 0) {
						for(int sheetNum = 0; sheetNum < numberOfSheets; sheetNum++) { 
							HSSFSheet sheet = workbook.getSheetAt(sheetNum);
							if(sheet == null) {
								continue;
							}
							//逐行读取Excel
							int lastRowNum = sheet.getLastRowNum();
							if(lastRowNum > 0) {
								int lastCellNum = 0;
								HSSFRow row0 = sheet.getRow(0);
								if(row0 != null) {
									lastCellNum = row0.getLastCellNum();
								}
								if(lastCellNum == 0) { //第一行为空，无法确定列数，根据每行列数自行决定
									for(int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
										HSSFRow row = sheet.getRow(rowNum);
										if(row != null) {
											List<String> rowList = new ArrayList<String>();
											//逐个单元格读取Excel
											for(int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
												HSSFCell cell = row.getCell(cellNum);
												if(cell == null) {
													rowList.add("");
												} else {
													rowList.add(getCellString(cell));
												}					
											}
											resultList.add(rowList);
										}
									}
								} else { //以第一行列数为基础读取数据
									for(int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
										HSSFRow row = sheet.getRow(rowNum);
										if(row != null) {
											List<String> rowList = new ArrayList<String>();
											//逐个单元格读取Excel
											for(int cellNum = 0; cellNum < lastCellNum; cellNum++) {
												HSSFCell cell = row.getCell(cellNum);
												if(cell == null) {
													rowList.add("");
												} else {
													rowList.add(getCellString(cell));
												}					
											}
											resultList.add(rowList);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	@SuppressWarnings("resource")
	public static List<List<String>> readXlsx(String filePath) {
		List<List<String>> resultList = new ArrayList<List<String>>();
		try {
			InputStream inputStream = getInputStream(filePath);
			if(inputStream != null) {
				//创建整个Excel对象
				XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
				if(workbook != null) {
					//逐个页面读取Excel
					int numberOfSheets = workbook.getNumberOfSheets();
					if(numberOfSheets > 0) {
						for(int sheetNum = 0; sheetNum < numberOfSheets; sheetNum++) { 
							XSSFSheet sheet = workbook.getSheetAt(sheetNum);
							if(sheet == null) {
								continue;
							}
							//逐行读取Excel
							int lastRowNum = sheet.getLastRowNum();
							if(lastRowNum > 0) {
								int lastCellNum = 0;
								XSSFRow row0 = sheet.getRow(0);
								if(row0 != null) {
									lastCellNum = row0.getLastCellNum();
								}
								if(lastCellNum == 0) { //第一行为空，无法确定列数，根据每行列数自行决定
									for(int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
										XSSFRow row = sheet.getRow(rowNum);
										if(row != null) {
											List<String> rowList = new ArrayList<String>();
											//逐个单元格读取Excel
											for(int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
												XSSFCell cell = row.getCell(cellNum);
												if(cell == null) {
													rowList.add("");
												} else {
													rowList.add(getCellString(cell));
												}					
											}
											resultList.add(rowList);
										}
									}
								} else { //以第一行列数为基础读取数据
									for(int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
										XSSFRow row = sheet.getRow(rowNum);
										if(row != null) {
											List<String> rowList = new ArrayList<String>();
											//逐个单元格读取Excel
											for(int cellNum = 0; cellNum < lastCellNum; cellNum++) {
												XSSFCell cell = row.getCell(cellNum);
												if(cell == null) {
													rowList.add("");
												} else {
													rowList.add(getCellString(cell));
												}					
											}
											resultList.add(rowList);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	//获取文件输入流
	public static InputStream getInputStream(String filePath) {
		if(filePath!=null&&!filePath.equals("")) {
			try {
				File file = new File(filePath);
				if(!file.exists()) { //文件不存在，用网络路径尝试
					filePath = filePath.replace(" ", "%20");
					URL url = new URL(filePath);
					HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
					httpURLConnection.setDoInput(true); //设置是否要从 URL连接读取数据，默认为true
					httpURLConnection.connect();
					if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) { //读取成功
						return httpURLConnection.getInputStream();		
					} else { //读取错误
						return null;
					}						
				} else { //文件存在，直接读取文件
					return new FileInputStream(filePath);
				} 				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//获取Excel单元格字符
	@SuppressWarnings("deprecation")
	public static String getCellString(HSSFCell cell){
		switch (cell.getCellType()) {			
			case Cell.CELL_TYPE_BOOLEAN: //布尔值
				return cell.getBooleanCellValue() ? "TRUE" : "FALSE";			
			case Cell.CELL_TYPE_FORMULA: //公式值
				return cell.getCellFormula();
			case Cell.CELL_TYPE_NUMERIC: //数字值
				cell.setCellType(Cell.CELL_TYPE_STRING);
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_STRING: //字符值
				return cell.getStringCellValue();
			default:
				return "";
		}
	}
	
	//获取Excel单元格字符
	@SuppressWarnings("deprecation")
	public static String getCellString(XSSFCell cell){
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN: //布尔值
				return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
			case Cell.CELL_TYPE_FORMULA: //公式值
				return cell.getCellFormula();
			case Cell.CELL_TYPE_NUMERIC: //数字值
				cell.setCellType(Cell.CELL_TYPE_STRING);
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_STRING: //字符值
				return cell.getStringCellValue();
			default:
				return "";
		}
	}
	
	/**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param workbook HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook workbook) {
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(workbook == null) {
        	workbook = new HSSFWorkbook();
        }
        // 第二步，在workbook中添加一个sheet，对应Excel文件中的sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行，注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头
        HSSFCellStyle style = workbook.createCellStyle();
        // 声明列对象
        HSSFCell cell = null;
        // 创建标题
        for(int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        if(values!=null){
			// 创建内容
			for(int i = 0; i < values.length; i++) {
				row = sheet.createRow(i + 1);
				for(int j = 0; j < values[i].length; j++) {
					// 将内容按顺序赋给对应的列对象
					row.createCell(j).setCellValue(values[i][j]);
				}
			}
		}
        return workbook;
    }
	
}