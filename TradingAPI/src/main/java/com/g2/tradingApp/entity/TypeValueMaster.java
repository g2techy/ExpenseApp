package com.g2.tradingApp.entity;

import javax.persistence.*;

@Entity
public class TypeValueMaster extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeValueId;

    private String typeValueName;

    private Long typeId;

    private Long parentTypeValueId;

    private Boolean isActive = true;

    public Long getTypeValueId() {
        return typeValueId;
    }

    public void setTypeValueId(Long typeValueId) {
        this.typeValueId = typeValueId;
    }

    public String getTypeValueName() {
        return typeValueName;
    }

    public void setTypeValueName(String typeValueName) {
        this.typeValueName = typeValueName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getParentTypeValueId() {
        return parentTypeValueId;
    }

    public void setParentTypeValueId(Long parentTypeValueId) {
        this.parentTypeValueId = parentTypeValueId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
