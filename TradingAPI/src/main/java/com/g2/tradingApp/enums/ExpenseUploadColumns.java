package com.g2.tradingApp.enums;

public enum ExpenseUploadColumns {
    ExpenseName("Expense Name"), ExpenseAmount("Expense Amount"), ExpenseDate("Expense Date"), ExpenseCategory("Expense Category"),
    IsExpense("Is Expense"), ExpenseDesc("Expense Desc"), FundSource("Fund Source");

    private final String columnName;

    private ExpenseUploadColumns(String columnName){
        this.columnName = columnName;
    }

    public String getColumnName(){
        return this.columnName;
    }

}
