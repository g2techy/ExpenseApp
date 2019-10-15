package com.g2.tradingApp.service.impl;

import com.g2.tradingApp.entity.TypeMaster;
import com.g2.tradingApp.exception.BusinessException;
import com.g2.tradingApp.exception.DataNotFoundException;
import com.g2.tradingApp.repository.TypeMasterRepository;
import com.g2.tradingApp.service.TypeMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class TypeMasterServiceImpl implements TypeMasterService {

    private static Logger LOGGER = LoggerFactory.getLogger(TypeMasterServiceImpl.class);

    @Autowired
    private TypeMasterRepository typeMasterRepository;

    @Override
    public List<TypeMaster> getAllTypeMasters() {
        LOGGER.debug("Executing method getAllTypeMasters");
        List<TypeMaster> typeMasterList = null;
        try {
            typeMasterList = typeMasterRepository.findAll();
        }catch (Exception e){
            throw new BusinessException(e);
        }

        if(typeMasterList == null || CollectionUtils.isEmpty(typeMasterList)){
            throw new DataNotFoundException("Types not found");
        }
        LOGGER.debug("Executed method getAllTypeMasters");
        return typeMasterList;
    }

    @Override
    public List<TypeMaster> getActiveTypeMasters() {
        LOGGER.debug("Executing method getActiveTypeMasters");
        List<TypeMaster> typeMasterList = null;
        try {
            typeMasterList = typeMasterRepository.getTypeMastersByIsActive(true);
        } catch (Exception e){
            throw new BusinessException(e);
        }

        if(typeMasterList == null || CollectionUtils.isEmpty(typeMasterList)){
            throw new DataNotFoundException("Active types not found");
        }
        LOGGER.debug("Executed method getActiveTypeMasters");
        return typeMasterList;
    }

    @Override
    public TypeMaster getTypeMaster(Long typeId) {
        LOGGER.debug("Executing method getTypeMaster");
        LOGGER.debug("Params: " + typeId);
        Optional<TypeMaster> typeMaster = null;
        try {
            typeMaster = typeMasterRepository.findById(typeId);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        if(typeMaster == null || !typeMaster.isPresent()){
            throw new DataNotFoundException("Type not found for typeId: " + typeId);
        }
        LOGGER.debug("Executed method getTypeMaster");
        return typeMaster.get();
    }

}
