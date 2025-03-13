package com.shop.ecommerce.product.domain;

import com.shop.ecommerce.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import static java.util.Objects.requireNonNull;

@Transactional
@RequiredArgsConstructor
public class ProductFacade {
    private final ProductRepository productRepository;
    private final ProductCreator productCreator;

    public ProductDto add(ProductDto productDto){
        requireNonNull(productDto);
        Product product = productCreator.from(productDto);
        product = productRepository.save(product);
        return product.dto();
    }
    public ProductDto show(String id){
        requireNonNull(id);
        Product product = productRepository.findOneOrThrow(id);
        return product.dto();
    }

    public Page<ProductDto> findAll(Pageable pageable){
        requireNonNull(pageable);
        return productRepository
                .findAll(pageable).map(Product::dto);
    }
}
