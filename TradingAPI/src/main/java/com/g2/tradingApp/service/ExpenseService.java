package com.g2.tradingApp.service;

import com.g2.tradingApp.dto.*;
import com.g2.tradingApp.entity.Expense;

import java.util.List;

public interface ExpenseService {

    List<ExpenseDTO> getAllExpenses();

    List<ExpenseDTO> getActiveExpenses();

    List<ExpenseDTO> searchExpenses(ExpenseSearchDTO expenseSearchDTO);

    ExpenseDetailsDTO getExpenseDetails(Long expenseId);

    Expense saveExpense(Expense expense);

    void deleteExpense(Long expenseId);

    List<ExpenseSummaryByTagDTO> getExpenseSummaryByTag(ExpenseSearchDTO expenseSearchDTO);

    List<ExpenseDTO> searchExpensesByTag(ExpenseSearchDTO expenseSearchDTO);

}
