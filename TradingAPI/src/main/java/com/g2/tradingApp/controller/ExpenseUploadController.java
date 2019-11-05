package com.g2.tradingApp.controller;

import com.g2.tradingApp.constants.ExpenseConstants;
import com.g2.tradingApp.constants.GlobalConstants;
import com.g2.tradingApp.exception.BusinessException;
import com.g2.tradingApp.service.ExpenseUploadService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/expenses/")
public class ExpenseUploadController {

    private static Logger LOGGER = LoggerFactory.getLogger(ExpenseUploadController.class);

    @Autowired
    private ExpenseUploadService expenseUploadService;

    @ApiOperation("Download template file")
    @GetMapping("download-template")
    public ResponseEntity<InputStreamResource> downloadTemplateFile(){
        LOGGER.info("Api : api/expenses/download-template");
        ByteArrayInputStream byteIOStream = expenseUploadService.downloadTemplateFile();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(GlobalConstants.MEDIA_TYPE_EXCEL_FILE))
                .cacheControl(CacheControl.noCache())
                .header(GlobalConstants.RESPONSE_HEADER_CONTENT_DISPOSITION, "attachment; filename=" + ExpenseConstants.EXPENSE_DOWNLOAD_FILE_NAME)
                .body(new InputStreamResource(byteIOStream));
    }

    @PostMapping("upload")
    public ResponseEntity<?> uploadExpenses(MultipartFile file) {
        LOGGER.info("Api : api/expenses/upload");
        if(file.isEmpty()){
            throw new BusinessException("Uploaded file is empty");
        }

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e){
            throw new BusinessException("Error:" + e.getMessage() + ", Cause:" + e.getCause());
        }

        ByteArrayInputStream byteIOStream = expenseUploadService.uploadExpenses(inputStream);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(GlobalConstants.MEDIA_TYPE_EXCEL_FILE))
                .cacheControl(CacheControl.noCache())
                .header(GlobalConstants.RESPONSE_HEADER_CONTENT_DISPOSITION, "attachment; filename=" + ExpenseConstants.EXPENSE_UPlOAD_RESULT_FILE_NAME)
                .body(new InputStreamResource(byteIOStream));
    }

}
