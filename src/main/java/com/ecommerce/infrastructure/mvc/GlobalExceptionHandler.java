package com.ecommerce.infrastructure.mvc;

import com.ecommerce.shop.order.dto.exception.ItemNotFoundException;
import com.ecommerce.shop.order.dto.exception.PaymentFailureException;
import com.ecommerce.shop.order.dto.exception.RefundFailureException;
import com.ecommerce.shop.product.dto.InsufficientStockException;
import com.ecommerce.shop.product.dto.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    ResponseEntity<String> handleItemNotFound(ItemNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({PaymentFailureException.class, RefundFailureException.class})
    ResponseEntity<String> handlePaymentsFailures(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<String> handleProductNotFound(ProductNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientStockException.class)
    ResponseEntity<String> handleInsufficientStock(InsufficientStockException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }


}
