package com.ecommerce.shop.product.domain;

import com.ecommerce.shop.product.dto.InsufficientStockException;
import com.ecommerce.shop.product.dto.ProductDto;
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

    public ProductDto add(ProductDto productDto) {
        requireNonNull(productDto);
        Product product = productCreator.from(productDto);
        product = productRepository.save(product);
        return product.dto();
    }

    public ProductDto show(String id) {
        requireNonNull(id);
        Product product = productRepository.findOneOrThrow(id);
        return product.dto();
    }

    public Page<ProductDto> findAll(Pageable pageable) {
        requireNonNull(pageable);
        return productRepository.findAll(pageable)
                .map(Product::dto);
    }

    public Page<ProductDto> findByCategory(String categoryDto, Pageable pageable) {
        requireNonNull(categoryDto);
        Category category = Category.valueOf(categoryDto.toUpperCase());
        return productRepository.findAllByCategory(category, pageable)
                .map(Product::dto);
    }

    public ProductDto allocateStock(String productId, long wantedAmount) {
        requireNonNull(productId);
        Product product = productRepository.findOneOrThrow(productId);
        long currentAmount = product.getAmount();
        if (currentAmount - wantedAmount < 0) {
            throw new InsufficientStockException(product.getName() +" stock is insufficient.");

        }
        product.setAmount(currentAmount - wantedAmount);
        product = productRepository.save(product);
        return product.dto();
    }
}
