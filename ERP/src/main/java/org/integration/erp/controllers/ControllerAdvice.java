package org.integration.erp.controllers;

import org.integration.erp.exceptions.PurchaseOrderAlreadyReleasedException;
import org.integration.erp.exceptions.PurchaseOrderCreateFailException;
import org.integration.erp.exceptions.PurchaseOrderNotExistException;
import org.integration.erp.exceptions.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({PurchaseOrderAlreadyReleasedException.class, PurchaseOrderCreateFailException.class,
            PurchaseOrderNotExistException.class, UserNotExistException.class})
    public ResponseEntity<String> exceptionHandler(Exception exception)
    {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
