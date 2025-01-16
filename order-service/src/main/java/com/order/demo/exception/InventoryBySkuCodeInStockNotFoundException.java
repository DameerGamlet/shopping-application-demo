package com.order.demo.exception;

public class InventoryBySkuCodeInStockNotFoundException extends RuntimeException {
    public InventoryBySkuCodeInStockNotFoundException(String message) {
        super(message);
    }
}
