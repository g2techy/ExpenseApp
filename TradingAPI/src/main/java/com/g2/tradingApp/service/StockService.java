package com.g2.tradingApp.service;

import com.g2.tradingApp.entity.StockMaster;

import java.util.List;

public interface StockService {

    List<StockMaster> getAllStokes();

    List<StockMaster> getActiveStokes();

    List<StockMaster> searchStokes(String term);

}
