package com.shop.ecommerce.costcalculator.domain;

import com.shop.ecommerce.cart.dto.CartItemDto;
import com.shop.ecommerce.product.domain.ProductFacade;
import com.shop.ecommerce.product.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CostCalculatorFacadeTest {
     private CostCalculatorFacade calculatorFacade;
     private ProductFacade productFacade;

    @BeforeEach
    void setUp(){
        productFacade = mock();
        calculatorFacade =new CostCalculationConfiguration().costCalculatorFacade(productFacade);
    }

    @Test
    void shouldCalculatePriceSuccessfully(){
        double price = 45.5;
        int quantity = 3;
        ProductDto product = ProductDto.builder().id("1").price(price).build();
        CartItemDto cartItemDto = new CartItemDto("1",quantity);
        when(productFacade.show("1")).thenReturn(product);

        double cost = calculatorFacade.calculate(List.of(cartItemDto));
        assertEquals(price*quantity,cost);
    }


}
