package org.integration.erp.purchaseorderprocessor.entities;

import jakarta.persistence.*;
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
