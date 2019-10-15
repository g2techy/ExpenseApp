package com.g2.tradingApp.dto;

import java.util.Date;

public interface AuditDTO {

    boolean getIsActive();

    Long getCreatedBy();

    Date getCreatedDate();

    Long getUpdatedBy();

    Date getUpdatedDate();

}
