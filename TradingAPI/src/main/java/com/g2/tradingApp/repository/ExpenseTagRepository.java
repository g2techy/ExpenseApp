package com.g2.tradingApp.repository;

import com.g2.tradingApp.dto.ExpenseTagDTO;
import com.g2.tradingApp.entity.ExpenseTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseTagRepository extends JpaRepository<ExpenseTag, Long> {

    List<ExpenseTag> getExpensesByExpenseId(Long expenseId);

    @Query(value = "select et.expense_tag_id as expenseTagId, et.expense_id as expenseId, et.tag_id as tagId, tg.type_value_name as tagName, " +
            "              1 as isActive, et.created_by as createdBy, et.created_date as createdDate, et.updated_by as updatedBy, et.updated_date as updatedDate \n" +
            "         from expense_tags et\n" +
            "         Left Join type_value_master tg on tg.type_value_id = et.tag_id" +
            "        where et.expense_id = :expenseId", nativeQuery = true)
    List<ExpenseTagDTO> getExpenseTags(@Param("expenseId") Long expenseId);

}
