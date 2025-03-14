package com.ecommerce.shop.cart.domain;

import com.ecommerce.shop.cart.dto.CartDto;
import com.ecommerce.shop.cart.dto.CartItemDto;
import com.ecommerce.shop.costcalculator.CostCalculatorFacade;
import com.ecommerce.infrastructure.authentication.CurrentUserGetter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartFacadeTest {
    private CartFacade cartFacade;
    private CurrentUserGetter currentUserGetter;

    private final String itemId = "1";

    @BeforeEach
    void setUp(){

        currentUserGetter = mock();
        CostCalculatorFacade calculatorFacade = mock();
        when(calculatorFacade.calculate(any())).thenReturn(0.0);
        cartFacade = new CartConfiguration().cartFacade(currentUserGetter,calculatorFacade);
    }

    @Test
    void shouldAddAProductToCartSuccessfully(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));
        cartFacade.add(itemId,2);

        CartDto cart = cartFacade.getCart();

        List<CartItemDto> expected = List.of(new CartItemDto(itemId, 2));
        assertEquals(expected,cart.getItems());
    }
    @Test
    void shouldUpdateCartSuccessfully(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));

        cartFacade.add(itemId,2);

        cartFacade.update(itemId,1);

        CartDto cart = cartFacade.getCart();

        List<CartItemDto> expected = List.of(new CartItemDto(itemId, 1));
        assertEquals(expected,cart.getItems());
    }

    @Test
    void shouldReturnAnEmptyCartWhenUserHaveNotAddedAnything(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));
        CartDto cart = cartFacade.getCart();

        CartDto emptyCart = CartDto.builder().username("Kasia")
                .items(List.of())
                .build();
        assertNotNull(cart);
        assertEquals(emptyCart,cart);
    }
    @Test
    void shouldDeleteProductIfQuantityEqualsZero(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));

        cartFacade.add(itemId, 1);

        cartFacade.update(itemId, 0);

        CartDto cart = cartFacade.getCart();
        assertEquals(List.of(),cart.getItems());
    }

    @Test
    void shouldThrowExceptionWhenAddingQuantityBelowZero(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));

        assertThrows(IllegalArgumentException.class,()->cartFacade.add(itemId,-1));
    }
    @Test
    void shouldAddTwoProductsWithTheSameId(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));

        cartFacade.add(itemId, 1);
        cartFacade.add(itemId, 4);

        CartDto cart = cartFacade.getCart();
        CartDto expected =  new CartDto("Kasia",List.of(new CartItemDto(itemId,5)),0.0);
        assertEquals(expected,cart);
    }

    @Test
    void shouldThrowExceptionWhenUpdateQuantityBelowZero(){
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));

        assertThrows(IllegalArgumentException.class,()->cartFacade.update(itemId,-1));
    }
}
