package driverFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import utilities.Utilities;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class DataProvider {
	///private static FileInputStream fis;
	static Utilities utility = new Utilities();
	
	public static void main(String[] args) throws IOException {
		
		GetDataFromExcel();
		
		
	}
	public static void GetDataFromExcel() throws IOException {
		//Create an object of File class to open xlsx file 
		File file = new File("./src/test/resources/data/DataNew.xlsx");
		//Create an object of FileInputStream class to read excel file
		FileInputStream fis = new FileInputStream(file);
		
		/*try {
			WorkbookFactory.create(file);
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//create object of XSSFWorkbook class
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		//Read sheet inside the workbook by its name
		XSSFSheet sheet = wb.getSheet("Sheet1");
		//Find number of rows in excel file
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		System.out.println(rowCount);
		for(int i=1;i<rowCount;i++) {
			Row row =sheet.getRow(i);
			int cellCount=row.getLastCellNum();
			for(int j=0;j<cellCount;j++) {
		
				System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
			}
		}
		
	}
	
	   
	}
	

