package com.shop.ecommerce.cart.domain;

import com.shop.ecommerce.cart.dto.CartDto;
import com.shop.ecommerce.cart.dto.CartItemDto;
import com.shop.ecommerce.infrastructure.authentication.CurrentUserGetter;
import com.shop.ecommerce.product.dto.CategoryDto;
import com.shop.ecommerce.product.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartFacadeTest {
    private CartFacade cartFacade;
    private CurrentUserGetter currentUserGetter;

    ProductDto leatherBag = ProductDto.builder().id("001").name("Leather Bag").price(40.4).category(CategoryDto.LEATHER).build();

    @BeforeEach
    void setUp(){
        currentUserGetter = mock();
        cartFacade = new CartConfiguration().cartFacade(currentUserGetter);
    }

    @Test
    void shouldAddAProductToCartSuccessfully(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));

        cartFacade.add(leatherBag,2);

        CartDto cart = cartFacade.getCart();

        List<CartItemDto> expected = List.of(new CartItemDto(leatherBag.getId(), 2));
        assertEquals(expected,cart.getItems());
    }
    @Test
    void shouldUpdateCartSuccessfully(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));

        cartFacade.add(leatherBag,2);

        cartFacade.update(leatherBag,1);

        CartDto cart = cartFacade.getCart();

        List<CartItemDto> expected = List.of(new CartItemDto(leatherBag.getId(), 1));
        assertEquals(expected,cart.getItems());
    }

    @Test
    void shouldReturnAnEmptyCartWhenUserHaveNotAddedAnything(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));
        CartDto cart = cartFacade.getCart();

        CartDto emptyCart = new CartDto("Kasia",List.of());
        assertNotNull(cart);
        assertEquals(emptyCart,cart);
    }
    @Test
    void shouldDeleteProductIfQuantityEqualsZero(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));

        cartFacade.add(leatherBag, 1);

        cartFacade.update(leatherBag, 0);

        CartDto cart = cartFacade.getCart();
        assertEquals(List.of(),cart.getItems());
    }

    @Test
    void shouldThrowExceptionWhenAddingQuantityBelowZero(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));

        assertThrows(IllegalArgumentException.class,()->cartFacade.add(leatherBag,-1));
    }
    @Test
    void shouldThrowExceptionWhenAddingQuantityEqualsZero(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));

        assertThrows(IllegalArgumentException.class,()->cartFacade.add(leatherBag,0));
    }

    @Test
    void shouldThrowExceptionWhenUpdateQuantityBelowZero(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));

        assertThrows(IllegalArgumentException.class,()->cartFacade.update(leatherBag,-1));
    }
}
