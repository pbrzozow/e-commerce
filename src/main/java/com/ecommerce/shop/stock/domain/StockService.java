package com.ecommerce.shop.stock.domain;

import com.ecommerce.shop.stock.dto.InsufficientStockException;
import lombok.RequiredArgsConstructor;


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
        if (stock == null) {
            stock = new ProductStock();
            stock.setProductId(productId);
        }
        return stock;
    }

    ProductStock allocate(String productId, long amount) {
        ProductStock stock = getStock(productId);
        if (stock.getAvailable() - amount < 0) {
            throw new InsufficientStockException("Product amount is insufficient.");
        }
        return update(productId, stock.getAvailable() - amount);
    }
}
