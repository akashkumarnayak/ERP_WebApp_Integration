package org.integration.erp.purchaseorderprocessor.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

@Entity
@Data
@Table(name = "Organization", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"company_code", "purchase_group", "purchase_org"})
})
public class Organization {

    @Id
    Long id;

    String purchaseOrg;
    String purchaseGroup;
    String companyCode;

}
