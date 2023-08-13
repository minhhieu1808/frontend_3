package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

        public static void main(String[] args) throws IOException {

            JDBCConfig config = new JDBCConfig();
            Connection con = config.connect();
            final String excelFilePath = "C:/Users/Admin/Downloads/books.xlsx";
            Table1 table1 = new Table1();
            Table2 table2 = new Table2();
            table1.writeExcel(con, excelFilePath);
            table2.writeExcel(con, excelFilePath);
        }
}