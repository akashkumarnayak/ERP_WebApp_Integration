package org.integration.erp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.UUID;

@Entity
@Data
@Table(name = "Organization", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"company_code", "purchase_group", "purchase_org"})
})
public class Organization {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NaturalId
    String purchaseOrg;

    @NaturalId
    String purchaseGroup;

    @NaturalId
    String companyCode;

}
