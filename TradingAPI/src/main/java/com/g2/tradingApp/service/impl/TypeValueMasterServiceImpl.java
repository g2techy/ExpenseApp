package com.g2.tradingApp.service.impl;

import com.g2.tradingApp.entity.TypeValueMaster;
import com.g2.tradingApp.exception.BusinessException;
import com.g2.tradingApp.exception.DataNotFoundException;
import com.g2.tradingApp.repository.TypeValueMasterRepository;
import com.g2.tradingApp.service.TypeValueMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TypeValueMasterServiceImpl implements TypeValueMasterService {

    private static Logger LOGGER = LoggerFactory.getLogger(TypeValueMasterServiceImpl.class);

    @Autowired
    private TypeValueMasterRepository typeValueMasterRepository;

    @Override
    public List<TypeValueMaster> getAllTypeValues() {
        LOGGER.debug("Executing method getAllTypeValues");
        List<TypeValueMaster> typeValueMasterList = null;
        try {
            typeValueMasterList = typeValueMasterRepository.findAll();
        }catch (Exception e){
            throw new BusinessException(e);
        }

        if(typeValueMasterList == null || CollectionUtils.isEmpty(typeValueMasterList)){
            throw new DataNotFoundException("Type values not found");
        }
        LOGGER.debug("Executed method getAllTypeValues");
        return typeValueMasterList;
    }

    @Override
    public List<TypeValueMaster> getActiveTypeValues() {
        LOGGER.debug("Executing method getActiveTypeValues");
        List<TypeValueMaster> typeValueMasterList = null;
        try {
            typeValueMasterList = typeValueMasterRepository.getActiveTypeValuesByIsActive(true);
        }catch (Exception e){
            throw new BusinessException(e);
        }

        if(typeValueMasterList == null || CollectionUtils.isEmpty(typeValueMasterList)){
            throw new DataNotFoundException("Active type values not found");
        }
        LOGGER.debug("Executed method getActiveTypeValues");
        return typeValueMasterList;
    }

    @Override
    public List<TypeValueMaster> getTypeValues(Long typeId) {
        LOGGER.debug("Executing method getTypeValues");
        List<TypeValueMaster> typeValueMasterList = null;
        try {
            typeValueMasterList = typeValueMasterRepository.getTypeValuesByTypeId(typeId);
        }catch (Exception e){
            throw new BusinessException(e);
        }

        if(typeValueMasterList == null || CollectionUtils.isEmpty(typeValueMasterList)){
            throw new DataNotFoundException("Type values not found for typeId: " + typeId);
        }
        LOGGER.debug("Executed method getTypeValues");
        return typeValueMasterList;
    }

    @Override
    public TypeValueMaster saveTypeValue(TypeValueMaster typeValueMaster) {
        return null;
    }

}
