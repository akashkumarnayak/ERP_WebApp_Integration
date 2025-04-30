package org.integration.erp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class PurchaseOrder {

    @Id
    Long poNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    User user;

    @Enumerated(EnumType.STRING)
    PurchaseOrderDocType type;

    @ManyToOne(cascade = CascadeType.ALL)
    Organization org;

    @Enumerated(EnumType.STRING)
    ReleaseStrategy strategy;

    String releaseGroup;

    @Enumerated(EnumType.STRING)
    ReleaseStatus status;

}
