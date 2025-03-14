package com.ecommerce.shop.stock.domain;

import org.springframework.data.repository.Repository;

interface StockRepository extends Repository<ProductStock,String> {
ProductStock findByProductId(String id);
ProductStock save(ProductStock productStock);

}
