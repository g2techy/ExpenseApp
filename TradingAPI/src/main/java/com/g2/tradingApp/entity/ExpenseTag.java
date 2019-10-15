package com.g2.tradingApp.entity;

import javax.persistence.*;

@Entity
@Table(name = "expense_tags")
public class ExpenseTag extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseTagId;

    private Long expenseId;

    private Long tagId;

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

}
