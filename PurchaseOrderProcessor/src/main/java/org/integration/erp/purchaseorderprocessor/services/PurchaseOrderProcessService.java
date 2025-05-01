package org.integration.erp.purchaseorderprocessor.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.cfg.Environment;
import org.integration.erp.purchaseorderprocessor.entities.Organization;
import org.integration.erp.purchaseorderprocessor.entities.PurchaseOrder;
import org.integration.erp.purchaseorderprocessor.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PurchaseOrderProcessService {

    @Autowired
    private OrganizationService organizationService;


    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @RetryableTopic(attempts = "3", backoff = @Backoff(delay=1000,multiplier = 1.5), dltTopicSuffix = ".po-release-app-310-DLT")
    @KafkaListener(topics = "PO-310", groupId = "po-release-app-310")
    public void processPurchaseOrder(String purchaseOrder, Acknowledgment ack){

        ObjectMapper mapper = new ObjectMapper();
        PurchaseOrder po = null;
        try {
            po = mapper.readValue(purchaseOrder, PurchaseOrder.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Organization dbFetchedOrg = organizationService.checkAndGetOrganization(po.getOrg());

        if (dbFetchedOrg != null) {
            po.setOrg(dbFetchedOrg);
        }

        purchaseOrderRepository.save(po);
        ack.acknowledge();
    }
}
