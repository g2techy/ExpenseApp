package com.g2.tradingApp.service.impl;

import com.g2.tradingApp.entity.StockMaster;
import com.g2.tradingApp.exception.BusinessException;
import com.g2.tradingApp.exception.DataNotFoundException;
import com.g2.tradingApp.repository.StockMasterRepository;
import com.g2.tradingApp.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private static Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockMasterRepository stockMasterRepository;

    @Override
    public List<StockMaster> getAllStokes() {
        LOGGER.debug("Executing method getAllStokes");
        List<StockMaster> stocks = null;

        try {
            stocks = stockMasterRepository.findAll();
        } catch (Exception e) {
            throw new BusinessException(e);
        }

        if(stocks == null || CollectionUtils.isEmpty(stocks)){
            throw new DataNotFoundException("Stocks not found");
        }
        LOGGER.debug("Executed method getAllStokes");
        return stocks;
    }

    @Override
    public List<StockMaster> getActiveStokes() {
        LOGGER.debug("Executing method getActiveStokes");
        List<StockMaster> stocks = null;

        try {
            stocks = stockMasterRepository.getStocksByIsActive(true);
        } catch (Exception e) {
            throw new BusinessException(e);
        }

        if(stocks == null || CollectionUtils.isEmpty(stocks)){
            throw new DataNotFoundException("Active stocks not found");
        }
        LOGGER.debug("Executed method getActiveStokes");
        return stocks;
    }

    @Override
    public List<StockMaster> searchStokes(String term) {
        LOGGER.debug("Executing method searchStokes");
        if(term == null || StringUtils.isEmpty(term)){
            return getActiveStokes();
        }
        List<StockMaster> stocks = null;

        try {
            stocks = stockMasterRepository.searchStocks(term);
        } catch (Exception e) {
            throw new BusinessException(e);
        }

        if(stocks == null || CollectionUtils.isEmpty(stocks)){
            throw new DataNotFoundException("Stocks not found. Search by: " + term);
        }
        LOGGER.debug("Executed method searchStokes");
        return stocks;
    }
}
