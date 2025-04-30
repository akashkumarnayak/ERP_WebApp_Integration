package org.integration.erp.dtos;

import lombok.Data;
import org.integration.erp.entities.PurchaseOrderDocType;
import org.integration.erp.entities.ReleaseStrategy;

@Data
public class PurchaseOrderDto {
    Long poNumber;
    String username;
    PurchaseOrderDocType type;
    OrganizationDto org;
    ReleaseStrategy strategy;
    String releaseGroup;
}
