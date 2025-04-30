package org.integration.erp.controllers;

import org.integration.erp.dtos.OrganizationDto;
import org.integration.erp.dtos.PurchaseOrderDto;
import org.integration.erp.dtos.PoResponseDto;
import org.integration.erp.dtos.PoResponseRequestDto;
import org.integration.erp.entities.Organization;
import org.integration.erp.entities.PurchaseOrder;
import org.integration.erp.entities.ReleaseStatus;
import org.integration.erp.entities.User;
import org.integration.erp.exceptions.PurchaseOrderCreateFailException;
import org.integration.erp.exceptions.UserNotExistException;
import org.integration.erp.services.OrganizationService;
import org.integration.erp.services.PoService;
import org.integration.erp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/po/v1")
public class PurchaseOrderController {

    @Autowired
    private PoService poService;

    @Autowired
    private UserService userService;

    @PostMapping("create-po")
    public PurchaseOrderDto createPo(@RequestBody PurchaseOrderDto purchaseOrderDto)
    {
        PurchaseOrder newPo = poService.createAndSavePo(from(purchaseOrderDto));

        if(newPo == null)
        {
            throw new PurchaseOrderCreateFailException("Purchase order not created successfully");
        }

        return from(newPo);
    }

    @PostMapping("release-status/response")
    public ResponseEntity<PoResponseDto> poReleaseResponse(@RequestBody PoResponseRequestDto poResponseRequestDto)
    {
        PurchaseOrder po = poService.updatePoStatus(from(poResponseRequestDto));
        PoResponseDto poResponseDto = new PoResponseDto();
        poResponseDto.setPoNumber(po.getPoNumber());
        poResponseDto.setReleaseStatus(po.getStatus());

        return new ResponseEntity<PoResponseDto>(poResponseDto,null,200);
    }

    PurchaseOrder from(PurchaseOrderDto purchaseOrderDto)
    {
        User user = userService.getUser(purchaseOrderDto.getUsername());
        if(user==null)
        {
            throw new UserNotExistException("User not found");
        }

        PurchaseOrder po = new PurchaseOrder();
        po.setPoNumber(purchaseOrderDto.getPoNumber());
        po.setUser(user);
        Organization org = new Organization();
        org.setPurchaseOrg(purchaseOrderDto.getOrg().getPurchaseOrg());
        org.setPurchaseGroup(purchaseOrderDto.getOrg().getPurchaseGroup());
        org.setCompanyCode(purchaseOrderDto.getOrg().getCompanyCode());
        po.setOrg(org);
        po.setType(purchaseOrderDto.getType());
        po.setStrategy(purchaseOrderDto.getStrategy());
        po.setReleaseGroup(purchaseOrderDto.getReleaseGroup());
        po.setStatus(ReleaseStatus.TO_BE_RELEASED );
        return po;
    }

    PurchaseOrderDto from(PurchaseOrder purchaseOrder)
    {
        PurchaseOrderDto poDto = new PurchaseOrderDto();
        poDto.setPoNumber(purchaseOrder.getPoNumber());
        poDto.setUsername(purchaseOrder.getUser().getUsername());
        poDto.setType(purchaseOrder.getType());
        poDto.setStrategy(purchaseOrder.getStrategy());
        poDto.setReleaseGroup(purchaseOrder.getReleaseGroup());


        OrganizationDto orgDto = new OrganizationDto();
        orgDto.setPurchaseOrg(purchaseOrder.getOrg().getPurchaseOrg());
        orgDto.setPurchaseGroup(purchaseOrder.getOrg().getPurchaseGroup());
        orgDto.setCompanyCode(purchaseOrder.getOrg().getCompanyCode());
        poDto.setOrg(orgDto);
        return poDto;
    }

    PurchaseOrder from(PoResponseRequestDto poResponseRequestDto)
    {
        PurchaseOrder po = new PurchaseOrder();
        po.setPoNumber(poResponseRequestDto.getPoNumber());
        po.setStatus(poResponseRequestDto.getReleaseStatus());
        return po;
    }
}
