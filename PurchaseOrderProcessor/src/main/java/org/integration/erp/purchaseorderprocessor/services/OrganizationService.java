package org.integration.erp.purchaseorderprocessor.services;

import org.integration.erp.purchaseorderprocessor.entities.Organization;
import org.integration.erp.purchaseorderprocessor.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository orgRepository;

    Organization checkAndGetOrganization(Organization org)
    {
        Optional<Organization> organization = orgRepository.findOrganizationByCompanyCodeAndPurchaseOrgAndPurchaseGroup(org.getCompanyCode(), org.getPurchaseOrg(), org.getPurchaseGroup());

        if (organization.isPresent()) {
            return organization.get();
        }

        return null;
    }
}
