package com.g2.tradingApp.repository;


import com.g2.tradingApp.dto.ExpenseDTO;
import com.g2.tradingApp.dto.ExpenseSummaryByTagDTO;
import com.g2.tradingApp.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = Expense.QUERY_EXPENSE_DETAILS, nativeQuery = true)
    List<ExpenseDTO> getAllExpenses();

    @Query(value = Expense.QUERY_EXPENSE_DETAILS + " where ed.is_active = 1", nativeQuery = true)
    List<ExpenseDTO> getActiveExpenses();

    @Query(value = Expense.QUERY_EXPENSE_DETAILS + " where ed.is_active = 1 " +
            "and ed.expense_date between :startDate and :endDate", nativeQuery = true)
    List<ExpenseDTO> searchExpenses(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = Expense.QUERY_EXPENSE_DETAILS + " where ed.expense_id = :expenseId", nativeQuery = true)
    ExpenseDTO getExpenseDetails(@Param("expenseId") Long expenseId);

    @Query(value = Expense.QUERY_EXPENSE_SUMMARY_BY_TAGS, nativeQuery = true)
    List<ExpenseSummaryByTagDTO> getExpenseSummaryByTag(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = Expense.QUERY_EXPENSE_DETAILS + " where ed.is_active = 1 " +
            "and ed.expense_date between :startDate and :endDate " +
            "and ed.expense_id in (select et.expense_id from expense_tags et where et.tag_id = :tagId)", nativeQuery = true)
    List<ExpenseDTO> searchExpensesByTag(@Param("tagId") Long tagId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
