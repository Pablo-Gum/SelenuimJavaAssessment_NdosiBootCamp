package Basics;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;

import java.lang.RuntimeException;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    // Define instance variables
    public FileInputStream fis = null;
    static String xlFilePath;
    static Workbook workbook;
    static Sheet sheet;
    static Row row;
    static Cell rell;


    // Method to initialize the Excel workbook
    public void initializeWorkbook(String xlFilePath) throws IOException {
        this.xlFilePath = xlFilePath;
        fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    // Method to get data from a specific cell in the Excel sheet
    public static String getCellData(String sheetName, String colName, int rowNum) {

        try {

            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row is missing in sheet: " + sheetName);
            }

            int col_Num = -1;

            for (int i = 0; i < headerRow.getLastCellNum(); i++) {

                String currentColumn =
                        headerRow.getCell(i).getStringCellValue().trim().toUpperCase();

                String givenColumn = colName.trim().toUpperCase();

                if (currentColumn.equals(givenColumn)) {
                    col_Num = i;
                    break;
                }
            }

            if (col_Num == -1) {
                throw new RuntimeException("Column not found: " + colName);
            }

            Row dataRow = sheet.getRow(rowNum);
            if (dataRow == null) {
                throw new RuntimeException("Row not found: " + rowNum);
            }

            Cell cell = dataRow.getCell(col_Num);
            if (cell == null) {
                return "";
            }

            // 🔥 Use DataFormatter (BEST PRACTICE)
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error in row " + rowNum + " or column " + colName;
        }
    }

}
