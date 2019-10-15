package com.g2.tradingApp.dto;

import java.util.Date;

public interface TradeDTO extends AuditDTO {

    Long getTradeId();

    Long getTradeTypeId();

    String getTradeTypeName();

    Long getExchangeId();

    String getExchangeName();

    Long getStockId();

    String getStockName();

    Long getQuantity();

    double getStockPrice();

    double getBrokerage();

    double getGST();

    double getTradeValue();

    Date getTradeDate();

}
