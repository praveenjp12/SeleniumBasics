package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ReadExcelData {

    public static ArrayList<HashMap<String, String>> getData() {
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\wwwpr\\IdeaProjects\\MySeleniumSamples\\src\\test\\java\\Utilities\\InputData.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("username", row.getCell(0).toString());
                map.put("email",row.getCell(1).toString());
                map.put("password",row.getCell(2).toString());
                map.put("dateofbirth",row.getCell(3).toString());
                map.put("gender",row.getCell(4).toString());
                map.put("employmentstatus",row.getCell(5).toString());
                data.add(map);
            }
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Object[][] inputData(String fileName) {
        String[][] tabArray = null;
        try {
            FileInputStream ExcelFile = new FileInputStream(
                    new File(System.getProperty("user.dir")+
                            "\\src\\test\\java\\Utilities\\"
                            +fileName+
                            ".xlsx"
                    ));
            XSSFWorkbook excelWBook = new XSSFWorkbook(ExcelFile);
            XSSFSheet excelWSheet = excelWBook.getSheetAt(0);
            int startRow = 1;
            int startCol = 0;
            int totalRows = excelWSheet.getLastRowNum();
            int totalCols = excelWSheet.getRow(0).getLastCellNum();
            tabArray = new String[totalRows][totalCols];
            for (int i = startRow; i <= totalRows; i++) {
                Row row = excelWSheet.getRow(i);
                for (int j = startCol; j < totalCols; j++) {
                    Cell cell = row.getCell(j);
                    cell.setCellType(Cell.CELL_TYPE_STRING);  // Ensures uniform string format
                    tabArray[i - 1][j] = cell.getStringCellValue();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tabArray;
    }
}
