package com.ecommerce.shop.product.domain;


import com.ecommerce.shop.product.dto.CategoryDto;
import com.ecommerce.shop.product.dto.ProductDto;
import com.ecommerce.shop.product.dto.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductFacadeTest {
    private ProductFacade productFacade;

    private final ProductDto bag = createProductDto("001","Leather Bag",40.5);
    private final ProductDto wallet = createProductDto("002","Leather wallet", 20.5);

    @BeforeEach
    void setUp() {
        productFacade = new ProductConfiguration().productFacade();
    }

    @Test
    void shouldReturnSavedProduct(){
        productFacade.add(bag);
        Page<ProductDto> products = productFacade.findAll(Pageable.ofSize(10));
        assertEquals(List.of(bag),products.stream().toList());
    }

    @Test
    void shouldFindSavedProductById(){

        productFacade.add(bag);
        ProductDto product = productFacade.show(bag.getId());
        assertEquals(bag,product);
    }
    @Test
    void shouldSaveProductSuccessfully(){

        productFacade.add(bag);
        productFacade.add(wallet);

        ProductDto retrievedBag = productFacade.show(bag.getId());
        ProductDto retrievedWallet = productFacade.show(wallet.getId());

        assertEquals(bag,retrievedBag);
        assertEquals(wallet,retrievedWallet);
    }
    @Test
    void shouldThrowExceptionWhenProductDoesNotExist(){
        assertThrows(ProductNotFoundException.class,()->productFacade.show(bag.getId()));
    }

    static private ProductDto createProductDto(String id,String name, Double price ) {
        return ProductDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .category(CategoryDto.LEATHER)
                .build();
    }
}
