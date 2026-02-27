package Basics;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelReader {

    // Define instance variables
    public FileOutputStream fos = null;
    public FileInputStream fis = null;
    public static XSSFWorkbook workbook = null;
    public static XSSFSheet sheet = null;
    public static XSSFRow row = null;
    public static XSSFCell cell = null;
    static String xlFilePath;


    // Method to initialize the Excel workbook
    public void initializeWorkbook(String xlFilePath) throws IOException {
        this.xlFilePath = xlFilePath;
        fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    // Method to get data from a specific cell in the Excel sheet
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            int col_Num = 1; // Initialize the column number to 1 (assuming the first column)

            // Get the sheet by name
            sheet = workbook.getSheet(sheetName);

            // Get the first row (header row) to find the column index
            row = sheet.getRow(0);

            // Iterate through the header row to find the matching column name
            for (int i = 0; i < row.getLastCellNum(); i++) {
                // Convert the current column name to uppercase for comparison
                String currentColumn = row.getCell(i).getStringCellValue().trim().toUpperCase();

                // Convert the provided column name to uppercase for comparison
                String givenColumn = colName.trim().toUpperCase();

                // Check if the current column matches the provided column name
                if (currentColumn.equals(givenColumn)) {
                    col_Num = i;
                    break; // Exit the loop when a match is found
                }
            }

            // Get the specified row
            row = sheet.getRow(rowNum);

            // Get the cell at the determined column index
            cell = row.getCell(col_Num);

            // Check the cell type and return the appropriate cell value
            if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
                String cellValue = Integer.toString((int) cell.getNumericCellValue());

                // Check if the cell contains a date and format it accordingly
                if (DateUtil.isCellDateFormatted(cell)) {
                    DateFormat df = new SimpleDateFormat("MM/yy/dd");
                    Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
            } else if (cell.getCellType() == CellType.BLANK) {
                return "";
            } else {
                // Handle boolean values
                return String.valueOf(cell.getBooleanCellValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in ExcelFile";
        }
    }

}
