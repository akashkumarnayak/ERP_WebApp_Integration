package org.integration.erp.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class OrganizationDto {

    private Long id;
    private String purchaseOrg;
    private String purchaseGroup;
    private String companyCode;
}
