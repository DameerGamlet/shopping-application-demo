package com.order.demo.exception;

public class OrderIsNullException extends RuntimeException {
    public OrderIsNullException(String message) {
        super(message);
    }
}
