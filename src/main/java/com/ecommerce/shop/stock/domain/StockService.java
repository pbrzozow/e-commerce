package com.ecommerce.shop.stock.domain;

import lombok.RequiredArgsConstructor;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
class StockService {
    private final StockRepository stockRepository;

    ProductStock update(String productId, long amount) {
        ProductStock stock = getStock(productId);
        stock.setAvailable(amount);
        stockRepository.save(stock);
        return stock;
    }


    ProductStock getStock(String productId) {
        ProductStock stock = stockRepository.findByProductId(productId);
        if (stock==null){
            stock = new ProductStock();
            stock.setProductId(productId);
        }
        return stock;
    }
}
