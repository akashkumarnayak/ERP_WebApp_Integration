package org.integration.erp.exceptions;

public class PurchaseOrderCreateFailException extends RuntimeException {
    public PurchaseOrderCreateFailException(String message) {
        super(message);
    }
}
