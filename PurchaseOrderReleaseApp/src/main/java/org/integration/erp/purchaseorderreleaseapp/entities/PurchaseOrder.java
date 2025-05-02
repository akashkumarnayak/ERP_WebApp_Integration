package org.integration.erp.purchaseorderreleaseapp.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PurchaseOrder {

    @Id
    Long poNumber;
    String userName;
    String type;

    @ManyToOne(cascade = CascadeType.ALL)
    Organization org;
    String strategy;
    String releaseGroup;
    String status;
}
