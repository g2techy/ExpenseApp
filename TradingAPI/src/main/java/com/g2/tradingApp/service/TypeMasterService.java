package com.g2.tradingApp.service;

import com.g2.tradingApp.entity.TypeMaster;

import java.util.List;

public interface TypeMasterService {

    List<TypeMaster> getAllTypeMasters();

    List<TypeMaster> getActiveTypeMasters();

    TypeMaster getTypeMaster(Long typeId);

}
