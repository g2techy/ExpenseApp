package com.g2.tradingApp.repository;

import com.g2.tradingApp.entity.TypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMasterRepository extends JpaRepository<TypeMaster, Long> {

    List<TypeMaster> getTypeMastersByIsActive(Boolean isActive);

}
