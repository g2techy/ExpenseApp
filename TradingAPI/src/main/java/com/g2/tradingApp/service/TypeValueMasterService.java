package com.g2.tradingApp.service;

import com.g2.tradingApp.entity.TypeValueMaster;

import java.util.List;

public interface TypeValueMasterService {

    List<TypeValueMaster> getAllTypeValues();

    List<TypeValueMaster> getActiveTypeValues();

    List<TypeValueMaster> getTypeValues(Long typeId);

    TypeValueMaster saveTypeValue(TypeValueMaster typeValueMaster);

}
