package org.integration.erp.purchaseorderreleaseapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Organization", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"company_code", "purchase_group", "purchase_org"})
})
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String purchaseOrg;
    String purchaseGroup;
    String companyCode;

}
