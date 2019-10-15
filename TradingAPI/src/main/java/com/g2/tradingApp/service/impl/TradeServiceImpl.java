package com.g2.tradingApp.service.impl;

import com.g2.tradingApp.dto.TradeDTO;
import com.g2.tradingApp.entity.Trade;
import com.g2.tradingApp.exception.BusinessException;
import com.g2.tradingApp.exception.DataNotFoundException;
import com.g2.tradingApp.repository.TradeRepository;
import com.g2.tradingApp.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {

    private static Logger LOGGER = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public List<TradeDTO> getAllTrades() {
        LOGGER.debug("Executing method getAllTrades");
        List<TradeDTO> trades = null;

        try {
            trades = tradeRepository.getAllTrades();
        } catch (Exception e) {
            throw new BusinessException(e);
        }

        if(trades == null || CollectionUtils.isEmpty(trades)){
            throw new DataNotFoundException("Trades not found");
        }
        LOGGER.debug("Executed method getAllTrades");
        return trades;
    }

    @Override
    public List<TradeDTO> getActiveTrades() {
        LOGGER.debug("Executing method getActiveTrades");
        List<TradeDTO> trades = null;

        try {
            trades = tradeRepository.getActiveTrades();
        } catch (Exception e) {
            throw new BusinessException(e);
        }

        if(trades == null || CollectionUtils.isEmpty(trades)){
            throw new DataNotFoundException("Active trades not found");
        }
        LOGGER.debug("Executed method getActiveTrades");
        return trades;
    }

    @Override
    public TradeDTO getTrade(Long tradeId) {
        LOGGER.debug("Executing method getTrade");
        TradeDTO trade = null;
        try {
            trade = tradeRepository.getTradeDetails(tradeId);
        } catch (Exception e){
          throw new BusinessException(e);
        }
        if(trade == null) {
            throw new DataNotFoundException("Trade details not found for tradeId: " + tradeId);
        }
        LOGGER.debug("Executed method getTrade");
        return trade;
    }

    @Override
    public Trade saveTrade(Trade trade) {
        LOGGER.debug("Executing method saveTrade");
        Trade savedTrade = null;
        try {
            savedTrade = tradeRepository.save(trade);
        }catch (Exception e) {
            throw new BusinessException(e);
        }
        LOGGER.debug("Executed method saveTrade");
        return savedTrade;
    }
}
