package com.g2.tradingApp.repository;

import com.g2.tradingApp.entity.TypeValueMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeValueMasterRepository extends JpaRepository<TypeValueMaster, Long> {

    List<TypeValueMaster> getActiveTypeValuesByIsActive(Boolean isActive);

    List<TypeValueMaster> getTypeValuesByTypeId(Long typeId);

}
