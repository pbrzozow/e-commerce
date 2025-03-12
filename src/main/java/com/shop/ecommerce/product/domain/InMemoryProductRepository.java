package com.shop.ecommerce.product.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class InMemoryProductRepository implements ProductRepository {
    Map<String,Product> products = new HashMap<>();
    @Override
    public Product save(Product product) {
        products.put(product.getId(),product);
        return product;
    }

    @Override
    public Product findById(String id) {
        return products.get(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(products.values()),pageable,products.size());
    }
}
