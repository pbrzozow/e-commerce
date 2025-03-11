package com.shop.ecommerce.product.domain;

import com.shop.ecommerce.product.dto.ProductDto;
import com.shop.ecommerce.product.dto.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductFacadeTest {
    private ProductFacade productFacade;

    @BeforeEach
    void setUp() {
        productFacade = new ProductConfiguration().productFacade();
    }

    @Test
    void shouldReturnSavedProducts(){
        String id = "2";
        ProductDto dto = new ProductDto(id);
        productFacade.add(dto);
        Page<ProductDto> product = productFacade.findAll(Pageable.ofSize(10));
        assertEquals(List.of(dto),product.stream().toList());
    }

    @Test
    void shouldFindSavedProductById(){
        String id = "3";
        ProductDto dto = new ProductDto(id);
        productFacade.add(dto);
        ProductDto product = productFacade.show(id);
        assertEquals(dto,product);
    }
    @Test
    void shouldSaveProductSuccessfully(){
        String id1 = "5";
        ProductDto dto1 = new ProductDto(id1);
        String id2 = "4";
        ProductDto dto2 = new ProductDto(id2);

        productFacade.add(dto1);
        productFacade.add(dto2);

        ProductDto product1 = productFacade.show(id1);
        ProductDto product2 = productFacade.show(id2);

        assertEquals(dto1,product1);
        assertEquals(dto2,product2);
    }
    @Test
    void shouldThrowExceptionWhenProductDoesNotExist(){
        assertThrows(ProductNotFoundException.class,()->productFacade.show("5"));
    }
}
