package org.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.constants.Constants;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelUtil {

    private static Workbook book;
    private static Sheet sheet;

    public static Object[][] getTestData(String sheetName) {
        Object data[][] = null;
        try {
            FileInputStream ip = new FileInputStream(Constants.EXCELPATH);
            book = WorkbookFactory.create(ip);
            sheet = book.getSheet(sheetName);
            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void loadExcel() {

        try {
            FileInputStream ip = new FileInputStream(Constants.EXCELPATH);
            //book= WorkbookFactory.create(ip);
            ip = new FileInputStream(Constants.EXCELPATH);
            book = new XSSFWorkbook(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getDataFromExcel(String sheetName) {
        loadExcel();
        sheet = book.getSheet(sheetName);
        Map<String, String> allData = new TreeMap<String,String>();
        int lastRowNum = sheet.getLastRowNum();
        //int lastColumnNumber=3;
        String key = "";
        String value = "";
        for (int i = 1; i <= lastRowNum; i++) {
            key = sheet.getRow(i).getCell(0).getStringCellValue();
            value = sheet.getRow(i).getCell(1).getStringCellValue() + "," + sheet.getRow(i).getCell(2).getStringCellValue() + "," + sheet.getRow(i).getCell(3).getStringCellValue();
            allData.put(key, value);
        }
        return allData;
    }

    public static String getParticularCellData(Map<String, String> dataSet, String testCaseName, int columnNo){
        String cellData;
        cellData=dataSet.get(testCaseName).split(",")[columnNo];
        return cellData;
    }


    public static List<String> getAllColumnData(String sheetName, int columnNo){
        loadExcel();
        sheet = book.getSheet(sheetName);
        List<String> cellDataList = new ArrayList<String>();
        String cellData="";
        int lastRowNum = sheet.getLastRowNum();
        for (int i=1;i<=lastRowNum;i++){
            cellData=sheet.getRow(i).getCell(columnNo).getStringCellValue();
            cellDataList.add(cellData);
        }
        return cellDataList;
    }

    public static int getTotalRowCount(String sheetName) {
        loadExcel();
        sheet = book.getSheet(sheetName);
        return book.getSheet(sheetName).getLastRowNum();
    }

    public static String getCellContent(String sheetname, int rownum, int colnum) {
        loadExcel();
        sheet = book.getSheet(sheetname);
        CellType celltype = sheet.getRow(rownum).getCell(colnum).getCellType();
        Cell cell = sheet.getRow(rownum).getCell(colnum);
        String temp = "";
        if (celltype == CellType.STRING) {
            temp = cell.getStringCellValue();
        } else if (celltype == CellType.NUMERIC || celltype == CellType.FORMULA) {
            temp = String.valueOf(cell.getNumericCellValue());
        } else if (celltype == CellType.BOOLEAN) {
            temp = String.valueOf(cell.getBooleanCellValue());
        } else if (celltype == CellType.ERROR) {
            temp = String.valueOf(cell.getErrorCellValue());
        }
        return temp;
    }

    @DataProvider
    public Object[][] getRegisterData() {
        Object regData[][] = ExcelUtil.getTestData(Constants.EXCELPATH);
        return regData;
    }

    public static int getLastRowNum(String sheetname) {
        return book.getSheet(sheetname).getLastRowNum();
    }

    public static int getRowNumForRowName(String sheetname, String rowName) {
        int rownum = 0;
        sheet = book.getSheet(sheetname);
        for (int i = 1; i <= getLastRowNum(sheetname); i++) {
            if (rowName.equalsIgnoreCase(sheet.getRow(i).getCell(3).getStringCellValue())) {
                rownum = i;
                break;
            }
        }
        return rownum;
    }

}
