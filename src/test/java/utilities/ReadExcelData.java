// 
// Decompiled by Procyon v0.5.36
// 

package utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import logger.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.nio.file.Path;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReadExcelData {

	DataFormatter formatter = new DataFormatter(Locale.US);



	@SuppressWarnings("resource")
	public String readDataFromExcelEnv(String sheetName, String testName, String env) throws IOException

	{

		String filepath = new File("").getAbsolutePath() + File.separator + "src/test/java" + File.separator + "testData" + File.separator + "Test_Data.xlsx";
		InputStream inputStream = getClass().getResourceAsStream("/com/Test_Data.xlsx");
		// FileInputStream inputStream = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		Sheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		for (int i = 0; i < rowCount + 1; i++) {
			Row row = sheet.getRow(i);
			// Create a loop to print cell values in a row
			if (row.getCell(0).getStringCellValue().equalsIgnoreCase(testName)) {
				if (env.equalsIgnoreCase("dev")) {
					String valueToEnter = formatter.formatCellValue(row.getCell(1));
					return valueToEnter;
				} else if (env.equalsIgnoreCase("stage")) {
					String valueToEnter = formatter.formatCellValue(row.getCell(2));
					return valueToEnter;
				} else if (env.equalsIgnoreCase("prod")) {
					String valueToEnter = formatter.formatCellValue(row.getCell(3));
					return valueToEnter;
				} else if (env.equalsIgnoreCase("NA")) {
					String valueToEnter = formatter.formatCellValue(row.getCell(2));
					return valueToEnter;
				}
			}

		}
		return null;

	}

}

