package org.integration.erp.purchaseorderreleaseapp.repositories;

import org.integration.erp.purchaseorderreleaseapp.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    List<PurchaseOrder> findPurchaseOrderByUserName(String userName);
    List<PurchaseOrder> findPurchaseOrderByOrg_CompanyCode(String orgCompanyCode);

    @Transactional
    @Modifying
    @Query("DELETE FROM PurchaseOrder po WHERE po.poNumber = :poNumber")
    void deletePurchaseOrderByPoNumber(Long poNumber);
}
