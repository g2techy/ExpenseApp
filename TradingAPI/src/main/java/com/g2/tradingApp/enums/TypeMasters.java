package com.g2.tradingApp.enums;

public enum TypeMasters {
    TradeType(1L, "TradeType"),
    Exchange(2L, "Exchange"),
    ExpenseCategory(3L, "ExpenseCategory"),
    ExpenseTag(4L, "ExpenseTag"),
    ExpenseFundSource(5L, "ExpenseFundSource");

    private final Long typeId;
    private final String typeName;

    private TypeMasters(Long typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

}
