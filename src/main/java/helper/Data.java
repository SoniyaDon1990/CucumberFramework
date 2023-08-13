package helper;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Data {
	static HashMap<String, String> map = new HashMap<String,String>(); 

	
	public String getData(String header) {
		
		return map.get(header);
	}

	
	public void setData(String filePath, String fileName, String sheetName, String testCaseName) throws IOException {
		File file = new File(filePath+"\\"+fileName);
		FileInputStream input = new FileInputStream(file);
		Workbook book = null;
		book = new HSSFWorkbook(input);
		Sheet sheet = book.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

		for (int i = 0; i < rowCount+1; i++) {
			Row row = sheet.getRow(i);
			
			if(row.getCell(0).getStringCellValue().equals(testCaseName)) {
				Row headRow = sheet.getRow(0);
				for (int j = 0; j <= row.getLastCellNum()+1; j++) {
					try {
						System.out.println(row.getCell(j).getStringCellValue());
					map.put(headRow.getCell(j).getStringCellValue(),row.getCell(j).getStringCellValue());
					}catch(Exception e) {
					}
				}
			}
		} 
		book.close();
	}

}
