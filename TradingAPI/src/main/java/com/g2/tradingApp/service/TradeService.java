package com.g2.tradingApp.service;

import com.g2.tradingApp.dto.TradeDTO;
import com.g2.tradingApp.entity.Trade;

import java.util.List;

public interface TradeService {

    List<TradeDTO> getAllTrades();

    List<TradeDTO> getActiveTrades();

    TradeDTO getTrade(Long tradeId);

    Trade saveTrade(Trade trade);

}
