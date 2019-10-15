package com.g2.tradingApp.dto;

import java.util.Date;

public interface ExpenseDTO extends AuditDTO {

    Long getExpenseId();

    String getExpenseName();

    Date getExpenseDate();

    float getExpenseAmount();

    Long getCategoryId();

    String getCategoryName();

    boolean getIsExpense();

    String getExpenseDesc();

    Long getFundSourceId();

    String getFundSourceName();

    String getExpenseTags();

}
