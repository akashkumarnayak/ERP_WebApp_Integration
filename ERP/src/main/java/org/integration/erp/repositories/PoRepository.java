package org.integration.erp.repositories;

import org.integration.erp.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoRepository extends JpaRepository<PurchaseOrder, Long> {

    Optional<PurchaseOrder> findBypoNumber(Long poNumber);
}
