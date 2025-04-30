package org.integration.erp.exceptions;

public class PurchaseOrderAlreadyReleasedException extends RuntimeException {
    public PurchaseOrderAlreadyReleasedException(String message) {
        super(message);
    }
}
