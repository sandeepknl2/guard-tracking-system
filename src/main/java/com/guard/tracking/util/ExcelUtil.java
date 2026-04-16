package com.guard.tracking.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.guard.tracking.model.Attendance;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ExcelUtil {

    private static final String FILE_PATH = "src/main/resources/attendance.xlsx";

    // ✅ WRITE CHECK-IN
    public static String writeCheckIn(String name, String location) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum() + 1;

            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(name);
            row.createCell(1).setCellValue(LocalDateTime.now().toString());
            row.createCell(3).setCellValue(location);

            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            workbook.write(fos);

            return "Check-in successful";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error in check-in";
        }
    }

    // ✅ WRITE CHECK-OUT
    public static String writeCheckOut(String name, String time, double hours) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                Cell nameCell = row.getCell(0);
                Cell checkoutCell = row.getCell(2);

                if (nameCell != null &&
                    name.equals(getCellValueAsString(nameCell)) &&
                    (checkoutCell == null || getCellValueAsString(checkoutCell) == null)) {

                    row.createCell(2).setCellValue(time);
                    row.createCell(4).setCellValue(hours);
                    break;
                }
            }

            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            workbook.write(fos);

            return "Check-out successful";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error in check-out";
        }
    }

    // ✅ READ ALL DATA
    public static List<Attendance> readAll() {

        List<Attendance> list = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                if (row.getRowNum() == 0) continue; // skip header

                Attendance a = new Attendance(
                        getCellValueAsString(row.getCell(0)),
                        getCellValueAsString(row.getCell(1)),
                        getCellValueAsString(row.getCell(2)),
                        getCellValueAsString(row.getCell(3)),
                        row.getCell(4) != null ? row.getCell(4).getNumericCellValue() : 0
                );

                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ FIND LAST RECORD
    public static Attendance findLastRecord(String name) {

        List<Attendance> list = readAll();

        for (int i = list.size() - 1; i >= 0; i--) {
            if (name.equals(list.get(i).getGuardName())) {
                return list.get(i);
            }
        }

        return null;
    }

    // ✅ SAFE CELL READER (IMPORTANT FIX)
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            default:
                return null;
        }
    }
}