package com.JodaynDemo.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static Map<String, String> readDetails(String sheetName) throws IOException {
        Map<String, String> data = new HashMap<>();
        FileInputStream fis = new FileInputStream("C:\\Users\\hydroweb\\IdeaProjects\\DemoExerciseJodaynTest\\src\\test\\resources\\testData\\TestData.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName); // Sheet name e.g. "AccountDetails"

        if (sheet == null) {
            workbook.close();
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in Excel file.");
        }

        Row headerRow = sheet.getRow(0); // Column names
        Row dataRow = sheet.getRow(1);   // First data row (can be parameterized)

        if (headerRow == null || dataRow == null) {
            workbook.close();
            throw new IllegalStateException("Header or data row is missing in sheet: " + sheetName);
        }

        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            Cell headerCell = headerRow.getCell(i);
            Cell valueCell = dataRow.getCell(i);

            if (headerCell == null) continue; // skip if no header

            String key = getCellValueAsString(headerCell).trim();   // ✅ Trim key
            String value = getCellValueAsString(valueCell).trim(); // ✅ Trim value

            data.put(key, value);
        }

        workbook.close();

        // ✅ Optional: Log all read data for verification
        System.out.println("Excel data read from sheet '" + sheetName + "':");
        data.forEach((k, v) -> System.out.println("[" + k + "] = [" + v + "]"));

        return data;
    }

//    public static Map<String, String> readAccountDetails(String sheetName, int rowIndex) throws IOException {
//        Map<String, String> data = new HashMap<>();
//        FileInputStream fis = new FileInputStream("C:\\Users\\hydroweb\\Desktop\\TestData.xlsx");
//        Workbook workbook = new XSSFWorkbook(fis);
//        Sheet sheet = workbook.getSheet(sheetName);
//
//        if (sheet == null) {
//            workbook.close();
//            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found.");
//        }
//
//        Row headerRow = sheet.getRow(0);
//        Row dataRow = sheet.getRow(rowIndex);
//
//        if (headerRow == null || dataRow == null) {
//            workbook.close();
//            throw new IllegalStateException("Missing header or data row at index " + rowIndex);
//        }
//
//        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
//            Cell headerCell = headerRow.getCell(i);
//            Cell valueCell = dataRow.getCell(i);
//            if (headerCell == null) continue;
//            String key = getCellValueAsString(headerCell);
//            String value = getCellValueAsString(valueCell);
//            data.put(key, value);
//        }
//
//        workbook.close();
//        return data;
//    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getDateCellValue().toString(); // You can format this if needed
                } else {
                    yield String.valueOf((int) cell.getNumericCellValue());
                }
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}
