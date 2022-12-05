package utilities;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.WebDriver;

import base.BaseClass;




public class ReadExcel extends BaseClass {
	
	WebDriver driver;

	XSSFWorkbook workbook;

	XSSFSheet sheet;

// Invoking excel file path from properties file 

	String excelFilePath = read.getValue("excelpath");

	public ReadExcel(WebDriver driver) {

		this.driver = driver;

		try {

// Getting Excel File 

			FileInputStream file = new FileInputStream(System.getProperty("user.dir") + excelFilePath);

			workbook = new XSSFWorkbook(file);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/****** Get excel data *******/

	public int getdata(int sheetNo, int row, int col) {

// Getting required Sheet 

		sheet = workbook.getSheetAt(sheetNo);

// Getting required data in cell 

		int data = (int) sheet.getRow(row).getCell(col).getNumericCellValue();

		return data;

	}

}
