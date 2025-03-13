package com.shop.ecommerce.costcalculator.domain;

import com.shop.ecommerce.cart.dto.CartItemDto;
import com.shop.ecommerce.product.domain.ProductFacade;
import com.shop.ecommerce.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class CostCalculatorFacade {
    private final ProductFacade productFacade;


    public double calculate(List<CartItemDto> items) {
        requireNonNull(items);
        double sum = 0;
        for (CartItemDto item : items) {
            ProductDto product = productFacade.show(item.getProductId());
            sum +=product.getPrice()*item.getQuantity();
        }
        return sum;
    }
}
