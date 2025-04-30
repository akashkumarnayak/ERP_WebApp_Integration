package org.integration.erp.repositories;

import org.integration.erp.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByCompanyCodeAndPurchaseGroupAndPurchaseOrg(String companyCode,String purchaseGroup,String purchaseOrg);

}
