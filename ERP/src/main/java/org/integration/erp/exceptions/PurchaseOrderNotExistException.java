package org.integration.erp.exceptions;

public class PurchaseOrderNotExistException extends RuntimeException {
    public PurchaseOrderNotExistException(String message) {
        super(message);
    }
}
