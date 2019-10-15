package com.g2.tradingApp.repository;

import com.g2.tradingApp.entity.StockMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMasterRepository extends JpaRepository<StockMaster, Long> {

    List<StockMaster> getStocksByIsActive(boolean isActive);

    @Query(value = "select * from stock_master sm where sm.stock_code like %:term% or sm.stock_name like %:term%", nativeQuery = true)
    List<StockMaster> searchStocks(@Param("term") String term);

}

