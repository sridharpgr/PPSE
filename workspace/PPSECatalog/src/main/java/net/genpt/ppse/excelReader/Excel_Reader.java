package net.genpt.ppse.excelReader;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader {
	
	public static final Logger logger = Logger.getLogger(Excel_Reader.class.getName());
	
	public String[][] getExcelData(String excelLocation, String sheetName){
		try{
			logger.info("I am in getExecelData");
			String dataSets[][]=null;
			FileInputStream fis = new FileInputStream(excelLocation);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int totalRow = sheet.getLastRowNum()+1;
			System.out.println(totalRow);
			int totalColumn = sheet.getRow(0).getLastCellNum();
			System.out.println(totalColumn);
			dataSets = new String[totalRow-1][totalColumn];
			Iterator<Row> rowIterator = sheet.iterator();
			
			int i = 0;
			int t = 0;
			
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				if(i++ != 0){
					int k = t;
					t++;
					Iterator<Cell> cellIterator = row.cellIterator();
					int j = 0;
					while(cellIterator.hasNext()){
						Cell cell = cellIterator.next();
						switch(cell.getCellType()){
						case Cell.CELL_TYPE_NUMERIC:
							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
						dataSets[k][j++] = cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
						case Cell.CELL_TYPE_BOOLEAN:
							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
						break;
						case Cell.CELL_TYPE_FORMULA:
							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						}
					}
					System.out.println("");
				}
			}
			fis.close();
			return dataSets;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
//	public static void main(String args[]){
//		Excel_Reader er = new Excel_Reader();
//		er.getExcelData("C:\\Users\\Admin\\workspace\\SpiProjectAutomation\\src\\main\\java\\com\\qa\\spiproject\\data\\TestData.xlsx", "Sheet1");
//	}

}
