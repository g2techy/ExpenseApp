package com.g2.tradingApp.controller;

import com.g2.tradingApp.api.ApiResponse;
import com.g2.tradingApp.dto.*;
import com.g2.tradingApp.entity.Expense;
import com.g2.tradingApp.service.ExpenseService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses/")
public class ExpenseController {

    private static Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    @ApiOperation("Get all expenses")
    @GetMapping
    public ResponseEntity<ApiResponse> getAllExpenses(){
        LOGGER.info("Api : api/expenses");
        List<ExpenseDTO> expenseList = expenseService.getAllExpenses();
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(expenseList);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Get active expenses")
    @GetMapping("active")
    public ResponseEntity<ApiResponse> getActiveExpenses(){
        LOGGER.info("Api : api/expenses/active");
        List<ExpenseDTO> expenseList = expenseService.getActiveExpenses();
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(expenseList);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Get active expenses")
    @PostMapping("search")
    public ResponseEntity<ApiResponse> searchExpenses(@RequestBody ExpenseSearchDTO expenseSearchDTO){
        LOGGER.info("Api : api/expenses/search");
        List<ExpenseDTO> expenseList = expenseService.searchExpenses(expenseSearchDTO);
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(expenseList);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Get expense details")
    @GetMapping("{expenseId}")
    public ResponseEntity<ApiResponse> getExpense(@PathVariable Long expenseId){
        LOGGER.info("Api : api/expenses/" + expenseId);
        ExpenseDetailsDTO expenseDetailsDTO = expenseService.getExpenseDetails(expenseId);
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(expenseDetailsDTO);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Save expenses details")
    @PostMapping("save")
    public ResponseEntity<ApiResponse> saveExpenseDetails(@RequestBody Expense expense) {
        LOGGER.info("Api : api/expenses/save");
        Expense savedExpense = expenseService.saveExpense(expense);
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(savedExpense);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Delete expense")
    @PostMapping("{expenseId}/delete")
    public ResponseEntity<ApiResponse> deleteExpense(@PathVariable Long expenseId) {
        LOGGER.info("Api : api/expenses/{expenseId}/delete");
        expenseService.deleteExpense(expenseId);
        ApiResponse apiResponse = ApiResponse.getSuccessResponse("Expense deleted successfully", null);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Get expense summary by tags")
    @PostMapping("summary-by-tag")
    public ResponseEntity<ApiResponse> getExpenseSummaryByTag(@RequestBody ExpenseSearchDTO expenseSearchDTO){
        LOGGER.info("Api : api/expenses/summary-by-tag");
        List<ExpenseSummaryByTagDTO> expenseList = expenseService.getExpenseSummaryByTag(expenseSearchDTO);
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(expenseList);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ApiOperation("Get active expenses")
    @PostMapping("find-by-tag")
    public ResponseEntity<ApiResponse> findExpensesByTag(@RequestBody ExpenseSearchDTO expenseSearchDTO){
        LOGGER.info("Api : api/expenses/find-by-tag");
        List<ExpenseDTO> expenseList = expenseService.searchExpensesByTag(expenseSearchDTO);
        ApiResponse apiResponse = ApiResponse.getSuccessResponse(expenseList);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

}
