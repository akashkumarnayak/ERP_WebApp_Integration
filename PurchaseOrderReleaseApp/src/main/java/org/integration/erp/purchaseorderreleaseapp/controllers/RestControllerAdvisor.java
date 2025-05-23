package org.integration.erp.purchaseorderreleaseapp.controllers;

import org.integration.erp.purchaseorderreleaseapp.exceptions.ERPPurchaseOrderStatusUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler(ERPPurchaseOrderStatusUpdateException.class)
    public ResponseEntity<String> exceptionHandler(Exception exception)
    {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
