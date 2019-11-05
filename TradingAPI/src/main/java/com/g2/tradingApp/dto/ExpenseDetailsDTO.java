package com.g2.tradingApp.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseDetailsDTO {

    private Long expenseId;

    private String expenseName;

    private Date expenseDate;

    private float expenseAmount;

    private Long categoryId;

    private String categoryName;

    private boolean isExpense;

    private String expenseDesc;

    private Long fundSourceId;

    private String fundSourceName;

    private List<String> errors = new ArrayList<>();

    private List<ExpenseTagDetailsDTO> expenseTags;

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public float getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(float expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean getIsExpense() {
        return isExpense;
    }

    public void setIsExpense(boolean isExpense) {
        this.isExpense = isExpense;
    }

    public String getExpenseDesc() {
        return expenseDesc;
    }

    public void setExpenseDesc(String expenseDesc) {
        this.expenseDesc = expenseDesc;
    }

    public Long getFundSourceId() {
        return fundSourceId;
    }

    public void setFundSourceId(Long fundSourceId) {
        this.fundSourceId = fundSourceId;
    }

    public String getFundSourceName() {
        return fundSourceName;
    }

    public void setFundSourceName(String fundSourceName) {
        this.fundSourceName = fundSourceName;
    }

    public List<ExpenseTagDetailsDTO> getExpenseTags() {
        return expenseTags;
    }

    public void setExpenseTags(List<ExpenseTagDetailsDTO> expenseTags) {
        this.expenseTags = expenseTags;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
