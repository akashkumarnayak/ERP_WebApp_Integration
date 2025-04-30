package org.integration.erp.services;

import org.integration.erp.entities.Organization;
import org.integration.erp.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization checkOrganization(Organization organization) {
        Optional<Organization> organizationOptional = organizationRepository.findByCompanyCodeAndPurchaseGroupAndPurchaseOrg(organization.getCompanyCode()
                ,organization.getPurchaseGroup(),organization.getPurchaseOrg());

        if (organizationOptional.isPresent()) {
            return organizationOptional.get();
        }
        else
            return null;
    }

//    public Organization createOrganization(String companyCode, String purchaseOrg, String purchaseGroup) {
//
//    }
}
