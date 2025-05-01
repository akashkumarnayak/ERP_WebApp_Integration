package org.integration.erp.purchaseorderprocessor.repositories;

import org.integration.erp.purchaseorderprocessor.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
