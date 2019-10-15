package com.g2.tradingApp.dto;

import java.util.Date;

public class ExpenseSearchDTO {

    private Long tagId;

    private Long fundSourceId;

    private Date startDate;

    private Date endDate;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getFundSourceId() {
        return fundSourceId;
    }

    public void setFundSourceId(Long fundSourceId) {
        this.fundSourceId = fundSourceId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
