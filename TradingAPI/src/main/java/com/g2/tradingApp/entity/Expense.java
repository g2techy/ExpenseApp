package com.g2.tradingApp.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "expenses")
public class Expense extends Audit {

    public static final String QUERY_EXPENSE_DETAILS = "select ed.expense_id as expenseId, ed.expense_name as expenseName, ed.expense_date as expenseDate, ed.expense_amount as expenseAmount, \n" +
            "   ed.category_id as categoryId, ed.category_name as categoryName, IIF(ed.is_expense = 1, 'true', 'false') as isExpense, ed.expense_desc as expenseDesc, \n" +
            "   ed.fund_source_id as fundSourceId, ed.fund_source_name as fundSourceName, ed.expense_tags as expenseTags, \n" +
            "   ed.is_active as isActive, ed.created_by as createdBy, ed.created_date as createdDate, ed.updated_by as updatedBy, ed.updated_date as updatedDate\n" +
            "  from v_expense_details ed";

    public static final String QUERY_EXPENSE_SUMMARY_BY_TAGS = "select et.tag_id as tagId, et.tag_name as tagName, count(et.expense_id) as expenseCount, sum(ed.expense_amount) as expenseAmount\n" +
            "  from v_expense_tag_details et\n" +
            " inner join expenses ed on ed.expense_id = et.expense_id" +
            " where ed.is_active = 1 and ed.expense_date between :startDate and :endDate " +
            " group by et.tag_id, et.tag_name\n" +
            " order by expenseCount desc";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private String expenseName;

    private Date expenseDate;

    private float expenseAmount;

    private Long categoryId;

    private boolean isExpense;

    private String expenseDesc;

    private Long fundSourceId;

    private boolean isActive = true;

    @Transient
    private List<ExpenseTag> expenseTags;

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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<ExpenseTag> getExpenseTags() {
        return expenseTags;
    }

    public void setExpenseTags(List<ExpenseTag> tags) {
        this.expenseTags = tags;
    }
}
