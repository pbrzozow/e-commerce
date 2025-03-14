package com.ecommerce.shop.product.domain;

import com.ecommerce.shop.product.dto.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

interface ProductRepository extends Repository<Product,String> {

    Product save(Product product);
    Product findById(String title);
    Page<Product> findAll(Pageable pageable);

    default Product findOneOrThrow(String id) {
        Product product= findById(id);
        if(product == null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }
}