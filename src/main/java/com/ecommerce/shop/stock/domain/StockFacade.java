package com.ecommerce.shop.stock.domain;

import com.ecommerce.shop.stock.dto.StockDto;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class StockFacade {
    private final StockService stockService;


    public StockDto update(String productId, long amount) {
        requireNonNull(productId);
        validateQuantity(amount);
        ProductStock productStock = stockService.update(productId, amount);
        return productStock.dto();
    }

    public StockDto getStock(String productId) {
        requireNonNull(productId);
        ProductStock stock = stockService.getStock(productId);
        return stock.dto();
    }

    private static void validateQuantity(long quantity) {
        if (quantity <0){
            throw new IllegalArgumentException("Provided quantity was below zero.");
        }
    }
}
