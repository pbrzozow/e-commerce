package com.ecommerce.shop.order.domain.cart;

import com.ecommerce.shop.order.domain.shared.Cart;
import com.ecommerce.shop.order.domain.shared.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartFacadeTest {

    private CartService cartService;
    private final Item item = new Item("1", "bag", "www.image.com", 50.0, 3);


    @BeforeEach
    void setUp() {
        cartService = new CartConfiguration().cartService();
    }

    @Test
    void shouldAddAProductToCartSuccessfully() {
        cartService.add(item.dto());

        Cart cart = cartService.getCart();

        assertEquals(List.of(item), cart.getItems());
        assertEquals(150, cart.getPrice());
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


        assertEquals(List.of(), cart.getItems());
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

        assertEquals(List.of(), clearedCart.getItems());
        assertEquals(0, clearedCart.getPrice());
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

        assertEquals(List.of(cartItem), cart.getItems());
        assertEquals(5 * 50, cart.getPrice());
    }

    @Test
    void shouldThrowExceptionWhenUpdateQuantityBelowZero() {

        assertThrows(IllegalArgumentException.class, () -> cartService.update(item.getProductId(), -1));
    }
}
