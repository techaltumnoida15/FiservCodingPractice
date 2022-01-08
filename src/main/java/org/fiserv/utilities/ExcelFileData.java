package org.fiserv.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.fiserv.constants.FrameworkConstants;

public final class ExcelFileData {
	private ExcelFileData() {}
	
	public static List<Map<String, String>> readData(int index) throws Exception {
		FileInputStream fip = new FileInputStream(new File(FrameworkConstants.getExcelpath()));
		
		XSSFWorkbook wb = new XSSFWorkbook(fip);
		XSSFSheet sheet = wb.getSheetAt(index);
		
		//Get Rows
		int xRows = sheet.getLastRowNum() + 1;
		System.out.println("Total rows are = " + xRows);
		
		//Get columns
		int xCols = sheet.getRow(0).getLastCellNum();
		System.out.println("Total columns are = " + xCols);
		
		Map<String, String> excelData = null;
		List<Map<String, String>> testDetails = new ArrayList<Map<String,String>>();
		
		for(int i = 1; i<xRows; i++) {
			excelData = new HashMap<String, String>();
			
			for(int j = 0; j< xCols; j++) {
				String key = null;
				try {
					key = getStringCellCalue(sheet.getRow(0).getCell(j));
				} catch (Exception e) {}
				String value = null;
				
				try {
					value = getStringCellCalue(sheet.getRow(i).getCell(j));
				} catch (Exception e) {}
				
				excelData.put(key, value);
			}
			testDetails.add(excelData);
		}
		wb.close();
		return testDetails;
	}

	private static String getStringCellCalue(XSSFCell cell) throws Exception {
		String cellValue = null;
		if(cell.getCellType().name().equals(org.apache.poi.ss.usermodel.CellType.BLANK.name())) {
			cellValue = "";
		}
		else if(cell.getCellType().name().equals(org.apache.poi.ss.usermodel.CellType.NUMERIC.name())) {
			cellValue = String.valueOf(cell.getNumericCellValue());
		}
		else if(cell.getCellType().name().equals(org.apache.poi.ss.usermodel.CellType.FORMULA.name())) {
			throw new Exception("Pls remove formula from cell.");
		}
		else {
			cellValue = cell.getStringCellValue();
		}
		return cellValue;
	}
}
