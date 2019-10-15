package com.g2.tradingApp.repository;

import com.g2.tradingApp.dto.TradeDTO;
import com.g2.tradingApp.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Query(value = Trade.QUERY_TRADE_DETAILS, nativeQuery = true)
    List<TradeDTO> getAllTrades();

    @Query(value = Trade.QUERY_TRADE_DETAILS + " where t.is_active=1", nativeQuery = true)
    List<TradeDTO> getActiveTrades();

    @Query(value = Trade.QUERY_TRADE_DETAILS + " where t.trade_id=:tradeId", nativeQuery = true)
    TradeDTO getTradeDetails(@Param("tradeId") Long tradeId);

}
