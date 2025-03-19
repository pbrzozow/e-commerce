package com.ecommerce.shop.order.domain.cart;


import com.ecommerce.infrastructure.authentication.CurrentUserGetter;

import com.ecommerce.shop.order.domain.shared.Cart;
import com.ecommerce.shop.order.domain.shared.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartFacadeTest {

    private CartService cartService;
    private final Item item = new Item("1", "bag", "www.image.com", 50.0, 3);


    @BeforeEach
    void setUp() {
        CurrentUserGetter currentUserGetter = mock();
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));
        cartService = new CartConfiguration().cartService(currentUserGetter);
    }

    @Test
    void shouldAddAProductToCartSuccessfully() {
        cartService.add(item.dto());

        Cart cart = cartService.getCart();
        Cart expected = new Cart("Kasia", List.of(item), 150);
        assertEquals(expected, cart);
    }


    @Test
    void shouldUpdateCartSuccessfully() {
        cartService.add(item.dto());

        cartService.update(item.getProductId(), 1);

        Cart cart = cartService.getCart();
        Item cartItem = item;
        cartItem.setQuantity(1);
        List<Item> expected = List.of(cartItem);
        assertEquals(expected, cart.getItems());
    }

    @Test
    void shouldReturnAnEmptyCartWhenUserHaveNotAddedAnything() {
        Cart cart = cartService.getCart();

        Cart emptyCart = Cart.builder().username("Kasia")
                .items(List.of())
                .build();
        assertNotNull(cart);
        assertEquals(emptyCart, cart);
    }

    @Test
    void shouldDeleteProductIfQuantityEqualsZero() {

        cartService.add(item.dto());

        cartService.update(item.getProductId(), 0);

        Cart cart = cartService.getCart();
        assertEquals(List.of(), cart.getItems());
    }

    @Test
    void shouldClearCartSuccessfully() {
        cartService.add(item.dto());
        Cart clearedCart = cartService.clearCart();

        assertEquals(new Cart("Kasia", List.of(), 0), clearedCart);
    }

    @Test
    void shouldThrowExceptionWhenAddingQuantityBelowZero() {
        Item cartItem = item;
        item.setQuantity(-1);
        assertThrows(IllegalArgumentException.class, () -> cartService.add(cartItem.dto()));
    }

    @Test
    void shouldAddTwoProductsWithTheSameId() {
        cartService.add(item.dto());
        Item cartItem = item;
        cartItem.setQuantity(5);
        cartService.add(cartItem.dto());

        Cart cart = cartService.getCart();
        Cart expected = new Cart("Kasia", List.of(cartItem), 5 * 50);
        assertEquals(expected, cart);
    }

    @Test
    void shouldThrowExceptionWhenUpdateQuantityBelowZero() {

        assertThrows(IllegalArgumentException.class, () -> cartService.update(item.getProductId(), -1));
    }
}
