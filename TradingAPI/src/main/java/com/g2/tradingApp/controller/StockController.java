package com.g2.tradingApp.controller;

import com.g2.tradingApp.api.ApiResponse;
import com.g2.tradingApp.entity.StockMaster;
import com.g2.tradingApp.service.StockService;
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
@RequestMapping("/api/trades/stocks/")
public class StockController {

    private static Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    @ApiOperation("Get all stocks")
    @GetMapping
    public ResponseEntity<ApiResponse> getAllStocks(){
        LOGGER.info("Api : api/trades/stocks");
        List<StockMaster> stocks = stockService.getAllStokes();
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(stocks);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Get all stocks")
    @GetMapping("active")
    public ResponseEntity<ApiResponse> getActiveStocks(){
        LOGGER.info("Api : api/trades/stocks/active");
        List<StockMaster> stocks = stockService.getActiveStokes();
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(stocks);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Search stocks by stock code, name")
    @GetMapping("search/{term}")
    public ResponseEntity<ApiResponse> searchStocks(@PathVariable String term){
        LOGGER.info("Api : api/trades/stocks/search/");
        List<StockMaster> stocks = stockService.searchStokes(term);
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(stocks);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

}
