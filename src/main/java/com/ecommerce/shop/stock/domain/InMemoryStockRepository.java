package com.ecommerce.shop.stock.domain;

import java.util.concurrent.ConcurrentHashMap;

class InMemoryStockRepository implements StockRepository{
    private final ConcurrentHashMap<String, ProductStock> stocks = new ConcurrentHashMap<>();

    @Override
    public ProductStock findByProductId(String id) {
        return stocks.get(id);
    }

    @Override
    public ProductStock save(ProductStock productStock) {
        stocks.put(productStock.getProductId(),productStock);
        return productStock;
    }
}
