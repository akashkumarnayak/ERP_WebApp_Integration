package org.integration.erp.purchaseorderreleaseapp.services;

import org.integration.erp.purchaseorderreleaseapp.Dtos.ReleasePoDto;
import org.integration.erp.purchaseorderreleaseapp.entities.PurchaseOrder;
import org.integration.erp.purchaseorderreleaseapp.exceptions.ERPPurchaseOrderStatusUpdateException;
import org.integration.erp.purchaseorderreleaseapp.exceptions.PurchaseOrderDeleteFailException;
import org.integration.erp.purchaseorderreleaseapp.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PurchaseOrderService {


    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Value("${erp-response-api}")
    private String erpPOReleaseUpdateResponseApi;

    public List<PurchaseOrder> getAllPurchaseOrders() {
            return purchaseOrderRepository.findAll();
    }

    public List<PurchaseOrder> getPurchaseOrdersByUserName(String username) {
        return purchaseOrderRepository.findPurchaseOrderByUserName(username);
    }

    public List<PurchaseOrder> getPurchaseOrdersByCompanyCode(String companyCode) {
        return purchaseOrderRepository.findPurchaseOrderByOrg_CompanyCode(companyCode);
    }

    @Transactional
    public ReleasePoDto deletePurchaseOrder(ReleasePoDto releasePoDto) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ReleasePoDto> releasePoDtoResponseEntity = restTemplate.postForEntity(erpPOReleaseUpdateResponseApi, releasePoDto, ReleasePoDto.class);

        if(releasePoDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            Long poNumber = releasePoDtoResponseEntity.getBody().getPoNumber();
            purchaseOrderRepository.deletePurchaseOrderByPoNumber(poNumber);
            return releasePoDto;
        }

        throw new ERPPurchaseOrderStatusUpdateException("Purchase Order Status Update Failure in ERP");
    }
}
