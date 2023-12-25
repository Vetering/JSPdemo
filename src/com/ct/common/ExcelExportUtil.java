// ExcelExportUtil.java
package com.ct.common;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelExportUtil {
    public static byte[] exportToExcel(List<Map<String, String>> data) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("导出数据");
            createHeaderRow(sheet);

            int rowCount = 1;
            for (Map<String, String> rowMap : data) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                for (String key : rowMap.keySet()) {
                    Cell cell = row.createCell(columnCount++);
                    // 使用UTF-8编码写入字符串数据
                    cell.setCellValue(new String(rowMap.get(key).getBytes("UTF-8"), "ISO-8859-1"));
                }
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

    private static void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        String[] columns = {"id", "StudentID", "name", "sex", "_class", "AdmissionTime", "Age", "_faculty"};
        int columnCount = 0;
        for (String column : columns) {
            Cell cell = headerRow.createCell(columnCount++);
            cell.setCellValue(column);
        }
    }
}
