package com.ecommerce.shop.product;

import com.ecommerce.shop.product.domain.ProductFacade;
import com.ecommerce.shop.product.dto.ProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
class ProductController {
    private final ProductFacade productFacade;

    @GetMapping("/products")
    ResponseEntity<Page<ProductDto>> getProducts(Pageable pageable) {
        Page<ProductDto> productPage = productFacade.findAll(pageable);
        return ResponseEntity.ok(productPage);
    }

    @GetMapping("/product/{id}")
    ResponseEntity<ProductDto> getProduct(@PathVariable String id) {
        ProductDto product = productFacade.show(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    ResponseEntity<ProductDto> addProduct(@RequestBody @Valid ProductDto productDto) {
        ProductDto product = productFacade.add(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
