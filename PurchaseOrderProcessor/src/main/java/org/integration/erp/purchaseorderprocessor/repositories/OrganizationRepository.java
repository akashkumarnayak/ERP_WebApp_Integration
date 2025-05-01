package org.integration.erp.purchaseorderprocessor.repositories;

import org.integration.erp.purchaseorderprocessor.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findOrganizationByCompanyCodeAndPurchaseOrgAndPurchaseGroup(String companyCode, String purchaseOrg, String purchaseGroup);
}
