package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Table2 {
    public static final int COLUMN_INDEX_ID         = 4;
    public static final int COLUMN_INDEX_NAME      = 5;
    public static final int COLUMN_INDEX_SLUG     = 6;
    private static CellStyle cellStyleFormatNumber = null;
    public static void writeExcel(Connection con, String excelFilePath) throws IOException {
        // Create Workbook
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = getWorkbook(inputStream,excelFilePath);

        // Create sheet
        Sheet sheet = workbook.getSheet("Books");// Create sheet with sheet name

        int rowIndex = 0;

        // Write header
        writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        final List<Category> categoryList = getCategories(con);
        for (Category category : categoryList) {
            // Create row
            Row row = sheet.getRow(rowIndex);
            if(row == null) row=sheet.createRow(rowIndex);
            // Write data on row
            writeCategory(sheet, category, row);
            rowIndex++;
        }


        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }

    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }


    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.getRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Name");

        cell = row.createCell(COLUMN_INDEX_SLUG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Slug");

    }

    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
//        font.setFontName("Times New Roman");
//        font.setBold(true);
        font.setFontHeightInPoints((short) 11); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    private static List<Category> getCategories(Connection con) {
        String query = "select * from product";
        List<Category> listCategory = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String slug = rs.getString("slug");
                listCategory.add(new Category(id, name, slug));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCategory;
    }
    private static void writeCategory(Sheet sheet, Category category, Row row) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(category.getId());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(category.getName());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(COLUMN_INDEX_SLUG);
        cell.setCellValue(category.getSlug());
        cell.setCellStyle(cellStyle);

    }

    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
