package org.integration.erp.purchaseorderreleaseapp.Dtos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.integration.erp.purchaseorderreleaseapp.entities.Organization;

@Data
public class PurchaseOrderDto {
    private Long poNumber;
    private String userName;
    private String type;
    private OrganizationDto org;
    private String strategy;
    private String releaseGroup;
    private String status;
}
