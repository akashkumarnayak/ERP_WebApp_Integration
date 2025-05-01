package org.integration.erp.purchaseorderprocessor.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderProcessService {

    @Autowired
    private ObjectMapper objectMapper;

    public void processPurchaseOrder(String purchaseOrder, Acknowledgment ack) {

        ObjectMapper mapper = new ObjectMapper();


    }
}
