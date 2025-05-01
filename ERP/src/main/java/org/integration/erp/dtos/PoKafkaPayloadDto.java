package org.integration.erp.dtos;

import lombok.Data;
import org.integration.erp.entities.Organization;
import org.integration.erp.entities.PurchaseOrderDocType;
import org.integration.erp.entities.ReleaseStatus;
import org.integration.erp.entities.ReleaseStrategy;

@Data
public class PoKafkaPayloadDto {
    Long poNumber;
    String userName;
    PurchaseOrderDocType type;
    Organization org;
    ReleaseStrategy strategy;
    String releaseGroup;
    ReleaseStatus status;
}
