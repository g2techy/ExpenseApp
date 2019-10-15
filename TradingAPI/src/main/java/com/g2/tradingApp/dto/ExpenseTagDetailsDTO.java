package com.g2.tradingApp.dto;

public class ExpenseTagDetailsDTO {

    private Long expenseTagId;

    private Long expenseId;

    private Long tagId;

    private String tagName;

    public Long getExpenseTagId() {
        return expenseTagId;
    }

    public void setExpenseTagId(Long expenseTagId) {
        this.expenseTagId = expenseTagId;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}
