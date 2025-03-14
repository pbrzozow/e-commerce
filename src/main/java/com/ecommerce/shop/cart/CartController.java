package com.ecommerce.shop.cart;

import com.ecommerce.shop.cart.domain.CartFacade;
import com.ecommerce.shop.cart.dto.CartDto;
import com.ecommerce.shop.cart.dto.CartItemDto;
import com.ecommerce.shop.product.dto.AddToCartRequest;
import com.ecommerce.shop.product.dto.UpdateItemRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
class CartController {
    private final CartFacade cartFacade;

    @PostMapping
    CartItemDto addItem(@Valid @RequestBody AddToCartRequest request){
    return cartFacade.add(request.id(), request.quantity());
    }

    @PutMapping
    CartItemDto updateItem(@Valid @RequestBody UpdateItemRequest request){
        return cartFacade.update(request.id(), request.quantity());
    }
    @GetMapping
    CartDto getCart(){
        return cartFacade.getCart();
    }

}
