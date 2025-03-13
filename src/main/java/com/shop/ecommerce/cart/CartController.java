package com.shop.ecommerce.cart;

import com.shop.ecommerce.cart.domain.CartFacade;
import com.shop.ecommerce.cart.dto.CartDto;
import com.shop.ecommerce.cart.dto.CartItemDto;
import com.shop.ecommerce.product.dto.AddToCartRequest;
import com.shop.ecommerce.product.dto.UpdateItemRequest;
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
