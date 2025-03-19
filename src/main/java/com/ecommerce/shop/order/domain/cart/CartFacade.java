package com.ecommerce.shop.order.domain.cart;

import com.ecommerce.shop.order.domain.shared.Cart;
import com.ecommerce.shop.order.dto.CartDto;
import com.ecommerce.shop.order.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@RequiredArgsConstructor
public class CartFacade {
    private final CartService cartService;

    public CartDto addItem(ItemDto itemDto) {
        requireNonNull(itemDto);
        Cart cart = cartService.add(itemDto);
        return cart.dto();
    }

    public CartDto getCart() {
        Cart cart = cartService.getCart();
        return cart.dto();
    }

    public CartDto updateItem(String itemId, int quantity) {
        requireNonNull(itemId);
        Cart cart = cartService.update(itemId, quantity);
        return cart.dto();
    }
}
