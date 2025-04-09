package com.ecommerce.shop.order;

import com.ecommerce.shop.order.domain.cart.CartFacade;
import com.ecommerce.shop.order.dto.CartDto;
import com.ecommerce.shop.order.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
class CartController {
    private final CartFacade cartFacade;

    @PostMapping("/items")
    ResponseEntity<CartDto> addItem(@RequestBody ItemDto itemDto) {
        CartDto cart = cartFacade.addItem(itemDto);
        return ResponseEntity.ok(cart);
    }

    @GetMapping
    ResponseEntity<CartDto> getCart() {
        CartDto cart = cartFacade.getCart();
        return ResponseEntity.ok(cart);

    }

    @PutMapping("/items/{itemId}")
    ResponseEntity<CartDto> updateItem(@PathVariable String itemId, @RequestParam int quantity) {
        CartDto cart = cartFacade.updateItem(itemId, quantity);
        return ResponseEntity.ok(cart);
    }

}
