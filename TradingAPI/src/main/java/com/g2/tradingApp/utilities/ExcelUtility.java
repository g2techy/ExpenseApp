package com.g2.tradingApp.utilities;

import com.g2.tradingApp.constants.GlobalConstants;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelUtility {

    public static Workbook createEmptyWorkbook(String sheetName) {
        Workbook workbook = new XSSFWorkbook();
        workbook.createSheet(sheetName);
        return workbook;
    }

    public static void addHeaderRow(Sheet sheet, List<String> headerColumns) {
        Row header = sheet.createRow(0);
        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        if(headerColumns != null && !CollectionUtils.isEmpty(headerColumns)){
            Integer cellIndex = 0;
            for(String col : headerColumns){
                Cell headerCell = header.createCell(cellIndex++);
                headerCell.setCellStyle(headerStyle);
                headerCell.setCellValue(col);
            }
        }
    }

    public static void setDropDownList(Sheet sheet, int cellIndex, String[] validValues){
        CellRangeAddressList addressList = new CellRangeAddressList(1, GlobalConstants.UPLOAD_FILE_ROWS_LIMIT, cellIndex, cellIndex);
        if(sheet instanceof XSSFSheet) {
            XSSFDataValidationHelper validationHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
            XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) validationHelper.createExplicitListConstraint(validValues);
            XSSFDataValidation dataValidation = (XSSFDataValidation) validationHelper.createValidation(dvConstraint, addressList);
            dataValidation.setShowErrorBox(true);
            sheet.addValidationData(dataValidation);
        } else if (sheet instanceof HSSFSheet) {
            DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(validValues);
            DataValidation dataValidation = new HSSFDataValidation(addressList, dvConstraint);
            dataValidation.setSuppressDropDownArrow(false);
            sheet.addValidationData(dataValidation);
        }
    }

    public static Workbook getWorkbookFromSteam(InputStream inputStream) throws IOException {

        Workbook workbook = new XSSFWorkbook(inputStream);

        return workbook;
    }

    public static void addRowsIntoSheet(Sheet sheet, final Map<Integer, List<Object>> dataRows) {
        if(dataRows != null && !CollectionUtils.isEmpty(dataRows)){
            for (Map.Entry<Integer, List<Object>> entry : dataRows.entrySet()) {
                Row row = sheet.createRow(entry.getKey());
                int cellIndex = 0;
                for(Object cellValue : entry.getValue()){
                    Cell cell = row.createCell(cellIndex++);
                    if(cellValue != null && !ObjectUtils.isEmpty(cellValue)){
                        if(cellValue instanceof Double){
                            cell.setCellValue((Double) cellValue);
                        } else if(cellValue instanceof Date){
                            cell.setCellValue((Date) cellValue);
                        } else {
                            cell.setCellValue(cellValue.toString());
                        }
                    }
                }
            }
        }
    }

}
