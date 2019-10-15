package com.g2.tradingApp.dto;

public interface ExpenseSummaryByTagDTO {

    Long getTagId();

    String getTagName();

    Long getExpenseCount();

    float getExpenseAmount();

}
