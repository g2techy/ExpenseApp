package com.g2.tradingApp.service.impl;

import com.g2.tradingApp.constants.ExpenseConstants;
import com.g2.tradingApp.constants.GlobalConstants;
import com.g2.tradingApp.dto.ExpenseDetailsDTO;
import com.g2.tradingApp.dto.ExpenseSearchDTO;
import com.g2.tradingApp.entity.TypeValueMaster;
import com.g2.tradingApp.enums.ExpenseUploadColumns;
import com.g2.tradingApp.enums.TypeMasters;
import com.g2.tradingApp.exception.BusinessException;
import com.g2.tradingApp.service.ExpenseUploadService;
import com.g2.tradingApp.service.TypeValueMasterService;
import com.g2.tradingApp.utilities.CommonUtility;
import com.g2.tradingApp.utilities.ExcelUtility;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseUploadServiceImpl implements ExpenseUploadService {

    private static Logger LOGGER = LoggerFactory.getLogger(ExpenseUploadServiceImpl.class);

    @Autowired
    private TypeValueMasterService typeValueMasterService;

    @Override
    public ByteArrayInputStream downloadTemplateFile() {
        LOGGER.debug("Executing method getUploadTemplate");

        Workbook workbook =  ExcelUtility.createEmptyWorkbook(ExpenseConstants.EXPENSE_DOWNLOAD_TEMPLATE_SHEET_NAME);
        Sheet sheet = workbook.getSheetAt(0);

        ExcelUtility.addHeaderRow(sheet, getExportColumns());
        ExcelUtility.setDropDownList(sheet, ExpenseUploadColumns.ExpenseCategory.ordinal(), getExpenseCategories());
        ExcelUtility.setDropDownList(sheet, ExpenseUploadColumns.IsExpense.ordinal(), GlobalConstants.LIST_YES_NO);
        ExcelUtility.setDropDownList(sheet, ExpenseUploadColumns.FundSource.ordinal(), getFundSources());

        LOGGER.debug("Executing method getUploadTemplate");
        return getByteArrays(workbook);
    }

    @Override
    public ByteArrayInputStream uploadExpenses(InputStream inputStream) {
        Workbook uploadedWB = null;
        try {
            uploadedWB = ExcelUtility.getWorkbookFromSteam(inputStream);
        } catch (IOException e) {
            throw new BusinessException(e);
        }

        Sheet sheet = uploadedWB.getSheetAt(0);
        if(sheet == null) {
            throw new BusinessException("There is no spreadsheet in uploaded file.");
        }

        Map<Integer, String> headerColumns = new HashMap<>();
        List<ExpenseDetailsDTO> dataRows = new ArrayList<>();
        for(Row row : sheet){
            /*Header row*/
            if(row.getRowNum() == 0){
                for (Cell cell : row) {
                    headerColumns.put(cell.getColumnIndex(), cell.getStringCellValue());
                }
                if(!isHeaderRowValid(headerColumns)){
                    throw new BusinessException("Header column(s) name or order(s) not valid.");
                }
                continue;
            }
            /*Data rows*/
            ExpenseDetailsDTO expDTO = extractDataRow(row);
            dataRows.add(expDTO);
        }

        List<ExpenseDetailsDTO> validRows = dataRows.stream().filter(d -> CollectionUtils.isEmpty(d.getErrors())).collect(Collectors.toList());
        List<ExpenseDetailsDTO> inValidRows = dataRows.stream().filter(d -> !CollectionUtils.isEmpty(d.getErrors())).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(validRows)){
            saveExpenseData(validRows);
        }

        Workbook uploadResult = getUploadResult(dataRows);
        return getByteArrays(uploadResult);
    }

    private List<String> getExportColumns() {
        return Arrays.stream(ExpenseUploadColumns.values()).map(col -> col.getColumnName()).collect(Collectors.toList());
    }

    private ByteArrayInputStream getByteArrays(Workbook workbook) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);
        } catch (IOException e) {
            throw new BusinessException(e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private ExpenseDetailsDTO extractDataRow(Row dataRow) {
        ExpenseDetailsDTO expDTO = new ExpenseDetailsDTO();
        List<String> errors = new ArrayList<>();
        Map<String, Long> categories = getExpenseCategoriesMap();
        Map<String, Long> fundSources = getFundSourcesMap();
        List<String> yesNoList = Arrays.stream(GlobalConstants.LIST_YES_NO).collect(Collectors.toList());

        for (Cell cell : dataRow) {
            String cellValue = cell.getStringCellValue();
            switch(cell.getColumnIndex()) {
                case 0:
                    if(StringUtils.isEmpty(cellValue)){
                        errors.add("Expense Name is mandatory.");
                    } else if(cellValue.length() > 100) {
                        errors.add("Expense Name length is 100 characters.");
                    } else {
                        expDTO.setExpenseName(cellValue);
                    }
                    break;
                case 1:
                    if(StringUtils.isEmpty(cellValue)){
                        errors.add("Expense Amount is mandatory.");
                    } else if(!StringUtils.isNumeric(cellValue)){
                        errors.add("Expense Amount is mandatory.");
                    } else {
                        expDTO.setExpenseAmount(Float.parseFloat(cellValue));
                    }
                    break;
                case 2:
                    if(StringUtils.isEmpty(cellValue)){
                        errors.add("Expense Date is mandatory.");
                    } else if(!CommonUtility.isValidDate(cellValue)){
                        errors.add("Expense Date is invalid.");
                    } else {
                        expDTO.setExpenseDate(cell.getDateCellValue());
                    }
                    break;
                case 3:
                    if(StringUtils.isEmpty(cellValue)){
                        errors.add("Expense Category is mandatory.");
                    } else if(!categories.containsKey(cellValue)){
                        errors.add("Expense Category is invalid.");
                    } else {
                        expDTO.setCategoryName(cellValue);
                        expDTO.setCategoryId(categories.get(cellValue));
                    }
                    break;
                case 4:
                    if(StringUtils.isEmpty(cellValue)){
                        errors.add("Is Expense is mandatory.");
                    } else if(yesNoList.indexOf(cellValue) == -1){
                        errors.add("Is Expense is invalid.");
                    } else {
                        expDTO.setIsExpense(yesNoList.get(0).equalsIgnoreCase(cellValue));
                    }
                    break;
                case 5:
                    if(!StringUtils.isEmpty(cellValue) && cellValue.length() > 500) {
                        errors.add("Expense Desc length is 500 characters.");
                    } else {
                        expDTO.setExpenseName(cellValue);
                    }
                    break;
                case 6:
                    if(StringUtils.isEmpty(cellValue)){
                        errors.add("Expense Category is mandatory.");
                    } else if(!fundSources.containsKey(cellValue)){
                        errors.add("Expense Category is invalid.");
                    } else {
                        expDTO.setFundSourceName(cellValue);
                        expDTO.setFundSourceId(fundSources.get(cellValue));
                    }
                    break;
            }
        }
        expDTO.setErrors(errors);
        return expDTO;
    }

    private void saveExpenseData(List<ExpenseDetailsDTO> expDetailsList){

    }

    private Workbook getUploadResult(List<ExpenseDetailsDTO> expDetailsList) {
        Workbook workbook = ExcelUtility.createEmptyWorkbook(ExpenseConstants.EXPENSE_DOWNLOAD_TEMPLATE_SHEET_NAME);
        Sheet sheet = workbook.getSheetAt(0);

        List<String> exportColumns = getExportColumns();
        exportColumns.add(0,"Status");
        exportColumns.add(exportColumns.size(), "Errors");
        ExcelUtility.addHeaderRow(sheet, exportColumns);

        int rowIndex=1;
        Map<Integer, List<Object>> dataRows = new HashMap<>();

        for (ExpenseDetailsDTO exp : expDetailsList){
            List<Object> cellValues = new ArrayList<>();
            cellValues.add(exp.getErrors().size() > 0 ? "Failed": "Success");
            cellValues.add(exp.getExpenseName());
            cellValues.add(exp.getExpenseAmount());
            cellValues.add(exp.getExpenseDate());
            cellValues.add(exp.getCategoryName());
            cellValues.add(exp.getIsExpense()?"Yes": "No");
            cellValues.add(exp.getExpenseDesc());
            cellValues.add(exp.getFundSourceName());
            cellValues.add(String.join(", ", exp.getErrors()));
            dataRows.put(rowIndex++, cellValues);
        }

        ExcelUtility.addRowsIntoSheet(sheet, dataRows);
        return workbook;
    }

    private boolean isHeaderRowValid(Map<Integer, String> headerColumns) {
        if(headerColumns == null || CollectionUtils.isEmpty(headerColumns)) {
            return false;
        }

        ExpenseUploadColumns[] uploadColumns = ExpenseUploadColumns.values();
        if(headerColumns.size() != uploadColumns.length){
            return false;
        }
        for(ExpenseUploadColumns col : uploadColumns){
            String colName = headerColumns.get(col.ordinal());
            if(colName == null || StringUtils.isEmpty(colName) || !col.getColumnName().equalsIgnoreCase(colName)){
                return false;
            }
        }
        return true;
    }

    private String[] getExpenseCategories() {
       return getTypeValueName(typeValueMasterService.getTypeValues(TypeMasters.ExpenseCategory.getTypeId()));
    }

    private String[] getFundSources() {
        return getTypeValueName(typeValueMasterService.getTypeValues(TypeMasters.ExpenseFundSource.getTypeId()));
    }

    private Map<String, Long> getExpenseCategoriesMap() {
        return typeValueMasterService.getTypeValues(TypeMasters.ExpenseCategory.getTypeId()).stream()
                .collect(Collectors.toMap(TypeValueMaster::getTypeValueName, TypeValueMaster::getTypeValueId));
    }

    private Map<String, Long> getFundSourcesMap() {
        return typeValueMasterService.getTypeValues(TypeMasters.ExpenseFundSource.getTypeId()).stream()
                .collect(Collectors.toMap(TypeValueMaster::getTypeValueName, TypeValueMaster::getTypeValueId));
    }

    private String[] getTypeValueName(List<TypeValueMaster> typeValueMasters) {
        if(typeValueMasters != null && !CollectionUtils.isEmpty(typeValueMasters)){
            return typeValueMasters.stream().map(tm -> tm.getTypeValueName()).toArray(String[]::new);
        }
        return null;
    }


}
