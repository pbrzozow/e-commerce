package com.shop.ecommerce.product;

import com.shop.ecommerce.product.domain.ProductFacade;
import com.shop.ecommerce.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class ProductController {
    private final ProductFacade productFacade;

    @GetMapping("/products")
    Page<ProductDto> getProducts(Pageable pageable){
        return productFacade.findAll(pageable);
    }

    @GetMapping("/product/{id}")
    ProductDto getProduct(@PathVariable String id){
        return productFacade.show(id);
    }
}
