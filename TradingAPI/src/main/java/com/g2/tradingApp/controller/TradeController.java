package com.g2.tradingApp.controller;

import com.g2.tradingApp.api.ApiResponse;
import com.g2.tradingApp.dto.TradeDTO;
import com.g2.tradingApp.entity.Trade;
import com.g2.tradingApp.service.TradeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/trades/")
public class TradeController {

    private static Logger LOGGER = LoggerFactory.getLogger(TradeController.class);

    @Autowired
    private TradeService tradeService;

    @ApiOperation("Get all trades")
    @GetMapping
    public ResponseEntity<ApiResponse> getAllTrades(){
        LOGGER.info("Api : api/trades");
        List<TradeDTO> trades = tradeService.getAllTrades();
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(trades);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Get active trades")
    @GetMapping("active")
    public ResponseEntity<ApiResponse> getActiveTrades(){
        LOGGER.info("Api : api/trades/active");
        List<TradeDTO> trades = tradeService.getActiveTrades();
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(trades);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Get trade details")
    @GetMapping("{tradeId}")
    public ResponseEntity<ApiResponse> getTradeDetails(@PathVariable Long tradeId){
        LOGGER.info("Api : api/trades/" + tradeId);
        TradeDTO trade = tradeService.getTrade(tradeId);
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(trade);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Save trade details")
    @PostMapping("save")
    public ResponseEntity<ApiResponse> saveTradeDetails(@RequestBody Trade trade) {
        LOGGER.info("Api : api/trades/save");
        Trade savedTrade = tradeService.saveTrade(trade);
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(savedTrade);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

}
