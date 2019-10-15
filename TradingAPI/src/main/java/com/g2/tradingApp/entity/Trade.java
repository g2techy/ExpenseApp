package com.g2.tradingApp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Trades")
public class Trade extends Audit {

    public static final String QUERY_TRADE_DETAILS = "" +
            "select t.trade_id as tradeId, t.trade_type_id as tradeTypeId, t.trade_type_name as tradeTypeName, t.exchange_id as exchangeId, t.exchange_name as exchangeName,\n" +
            "       t.stock_id as stockId, t.stock_name as stockName, t.quantity, t.stock_price as stockPrice, t.brokerage, t.GST, \n" +
            "       t.trade_value as tradeValue, t.trade_date as tradeDate, " +
            "       t.is_active as isActive, t.created_by as createdBy, t.created_date as createdDate, t.updated_by as updatedBy, t.updated_date as updatedDate\n" +
            "  from v_trade_details t";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeId;

    private Long tradeTypeId;

    private Long exchangeId;

    private Long stockId;

    private Long quantity;

    private double stockPrice;

    private double brokerage;

    @Column(name = "GST")
    private double GST;

    private Date tradeDate;

    private Boolean isActive = true;

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getTradeTypeId() {
        return tradeTypeId;
    }

    public void setTradeTypeId(Long tradeTypeId) {
        this.tradeTypeId = tradeTypeId;
    }

    public Long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Long exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public double getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(double brokerage) {
        this.brokerage = brokerage;
    }

    public double getGST() {
        return GST;
    }

    public void setGST(double GST) {
        this.GST = GST;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
