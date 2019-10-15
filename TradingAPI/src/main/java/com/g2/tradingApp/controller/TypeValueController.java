package com.g2.tradingApp.controller;

import com.g2.tradingApp.api.ApiResponse;
import com.g2.tradingApp.entity.TypeValueMaster;
import com.g2.tradingApp.repository.TypeValueMasterRepository;
import com.g2.tradingApp.service.TypeValueMasterService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/type-values")
public class TypeValueController {

    private static Logger LOGGER = LoggerFactory.getLogger(TypeValueController.class);

    @Autowired
    private TypeValueMasterService typeValueMasterService;

    @ApiOperation("Get all type values")
    @GetMapping
    public ResponseEntity<ApiResponse> getAllTypeValues(){
        LOGGER.info("Api : api/type-values");
        List<TypeValueMaster> typeMasterList = typeValueMasterService.getAllTypeValues();
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(typeMasterList);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Get active type values")
    @GetMapping("active")
    public ResponseEntity<ApiResponse> getActiveTypeValues(){
        LOGGER.info("Api : api/type-values/active");
        List<TypeValueMaster> typeMasterList = typeValueMasterService.getActiveTypeValues();
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(typeMasterList);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Get active type values")
    @GetMapping("{typeId}")
    public ResponseEntity<ApiResponse> getTypeValues(@PathVariable Long typeId){
        LOGGER.info("Api : api/type-values/" + typeId);
        List<TypeValueMaster> typeMasterList = typeValueMasterService.getTypeValues(typeId);
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(typeMasterList);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

}
