package com.ecommerce.shop.product.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

class InMemoryProductRepository implements ProductRepository {
    private final ConcurrentHashMap<String, Product> products = new ConcurrentHashMap<>();

    @Override
    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product findById(String id) {
        return products.get(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(products.values()), pageable, products.size());
    }

    @Override
    public Page<Product> findAllByCategory(Category category, Pageable pageable) {
        List<Product> productList = products.values()
                .stream()
                .filter(p -> p.getCategory() == category)
                .toList();
        return new PageImpl<>(productList, pageable, productList.size());
    }
}
