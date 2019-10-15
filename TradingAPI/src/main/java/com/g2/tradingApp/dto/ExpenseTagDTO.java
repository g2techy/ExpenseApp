package com.g2.tradingApp.dto;

public interface ExpenseTagDTO extends AuditDTO {

    Long getExpenseTagId();

    Long getExpenseId();

    Long getTagId();

    String getTagName();

}
