package org.integration.erp.purchaseorderreleaseapp.controllers;

import org.integration.erp.purchaseorderreleaseapp.Dtos.OrganizationDto;
import org.integration.erp.purchaseorderreleaseapp.Dtos.PurchaseOrderDto;
import org.integration.erp.purchaseorderreleaseapp.Dtos.ReleasePoDto;
import org.integration.erp.purchaseorderreleaseapp.entities.Organization;
import org.integration.erp.purchaseorderreleaseapp.entities.PurchaseOrder;
import org.integration.erp.purchaseorderreleaseapp.exceptions.ERPPurchaseOrderStatusUpdateException;
import org.integration.erp.purchaseorderreleaseapp.exceptions.PurchaseOrderDeleteFailException;
import org.integration.erp.purchaseorderreleaseapp.repositories.PurchaseOrderRepository;
import org.integration.erp.purchaseorderreleaseapp.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/po/v1")
public class PurchaseOrderController {

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @GetMapping("/get-po")
    public List<PurchaseOrderDto> getAllPurchaseOrder()
    {
         List<PurchaseOrder> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();
         List<PurchaseOrderDto> purchaseOrderDtos = new ArrayList<>();

         for (PurchaseOrder purchaseOrder : purchaseOrders) {
             purchaseOrderDtos.add(from(purchaseOrder));
         }

         return purchaseOrderDtos;
    }

    @GetMapping("/get-po/user/{username}")
    public List<PurchaseOrderDto> getPurchaseOrderByUsername(@PathVariable String username)
    {
        List<PurchaseOrder> purchaseOrders =  purchaseOrderService.getPurchaseOrdersByUserName(username);
        List<PurchaseOrderDto> purchaseOrderDtos = new ArrayList<>();

        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            purchaseOrderDtos.add(from(purchaseOrder));
        }

        return purchaseOrderDtos;
    }

    @GetMapping("/get-po/company-code/{companyCode}")
    public List<PurchaseOrderDto> getPurchaseOrderByCompanyCode(@PathVariable String companyCode)
    {
        List<PurchaseOrder> purchaseOrder = purchaseOrderService.getPurchaseOrdersByCompanyCode(companyCode);
        List<PurchaseOrderDto> purchaseOrderDtos = new ArrayList<>();

        for(PurchaseOrder po : purchaseOrder)
        {
            purchaseOrderDtos.add(from(po));
        }

        return purchaseOrderDtos;
    }

    @PostMapping("/release-po")
    public ReleasePoDto releasePurchaseOrder(@RequestBody ReleasePoDto releasePoDto)
    {
        return purchaseOrderService.deletePurchaseOrder(releasePoDto);
    }

    PurchaseOrderDto from(PurchaseOrder purchaseOrder) {
        PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
        purchaseOrderDto.setPoNumber(purchaseOrder.getPoNumber());
        purchaseOrderDto.setUserName(purchaseOrder.getUserName());
        purchaseOrderDto.setType(purchaseOrder.getType());

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setCompanyCode(purchaseOrder.getOrg().getCompanyCode());
        organizationDto.setPurchaseOrg(purchaseOrder.getOrg().getPurchaseOrg());
        organizationDto.setPurchaseGroup(purchaseOrder.getOrg().getPurchaseGroup());
        purchaseOrderDto.setOrg(organizationDto);

        purchaseOrderDto.setStatus(purchaseOrder.getStatus());
        purchaseOrderDto.setStrategy(purchaseOrder.getStrategy());
        purchaseOrderDto.setReleaseGroup(purchaseOrder.getReleaseGroup());

        return purchaseOrderDto;
    }
}
