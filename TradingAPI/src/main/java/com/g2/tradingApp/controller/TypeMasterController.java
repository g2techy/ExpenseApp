package com.g2.tradingApp.controller;

import com.g2.tradingApp.api.ApiResponse;
import com.g2.tradingApp.entity.TypeMaster;
import com.g2.tradingApp.service.TypeMasterService;

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
@RequestMapping("/api/types/")
public class TypeMasterController {

    private static Logger LOGGER = LoggerFactory.getLogger(TypeMasterController.class);

    @Autowired
    private TypeMasterService typeMasterService;

    @GetMapping("all")
    @ApiOperation("Get all types")
    public ResponseEntity<ApiResponse> getTypes(){
        LOGGER.info("Api : api/trades/types/all");
        List<TypeMaster> typeMasterList = typeMasterService.getAllTypeMasters();
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(typeMasterList);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("active")
    @ApiOperation("Get active types")
    public ResponseEntity<ApiResponse> getActiveTypes() {
        LOGGER.info("Api : api/trades/types/active");
        List<TypeMaster> typeMasterList = typeMasterService.getActiveTypeMasters();
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(typeMasterList);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("{typeId}")
    @ApiOperation("Get type for given typeId")
    public ResponseEntity<ApiResponse> getType(@PathVariable("typeId") Long typeId) {
        LOGGER.info("Api : api/trades/types/" + typeId);
        TypeMaster typeMaster = typeMasterService.getTypeMaster(typeId);
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(typeMaster);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

}
