package com.shop.ecommerce.cart.domain;

import com.shop.ecommerce.cart.dto.CartDto;
import com.shop.ecommerce.infrastructure.authentication.CurrentUserGetter;
import com.shop.ecommerce.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class CartFacade {
    private final CartRepository cartRepository;
    private final CurrentUserGetter userGetter;

    CartItem add(ProductDto productDto,int quantity){
        requireNonNull(productDto);
       return null;
    }
    CartDto getCart(){
        return null;
    }
    CartItem delete(ProductDto productDto){
        return null;
    }
}
