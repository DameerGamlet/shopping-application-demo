package com.order.demo.exception;

import com.order.demo.exception.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderIsNullException.class)
    public ResponseEntity<ErrorResponse> handleOrderIsNullException(
            OrderIsNullException e,
            HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(InventoryBySkuCodeInStockNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInventoryNotFoundException(
            InventoryBySkuCodeInStockNotFoundException e,
            HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception,
            HttpStatus status,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(errorResponse);
    }
}
