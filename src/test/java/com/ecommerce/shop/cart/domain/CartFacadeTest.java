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
        when(currentUserGetter.getSignedInUsername()).thenReturn(Optional.of("Kasia"));
        CostCalculatorFacade calculatorFacade = mock();
        when(calculatorFacade.calculate(any())).thenReturn(0.0);
        cartFacade = new CartConfiguration().cartFacade(currentUserGetter,calculatorFacade);
    }

    @Test
    void shouldAddAProductToCartSuccessfully(){
        cartFacade.add(itemId,2);

        CartDto cart = cartFacade.getCart();

        List<CartItemDto> expected = List.of(new CartItemDto(itemId, 2));
        assertEquals(expected,cart.getItems());
    }
    @Test
    void shouldUpdateCartSuccessfully(){
        cartFacade.add(itemId,2);

        cartFacade.update(itemId,1);

        CartDto cart = cartFacade.getCart();

        List<CartItemDto> expected = List.of(new CartItemDto(itemId, 1));
        assertEquals(expected,cart.getItems());
    }

    @Test
    void shouldReturnAnEmptyCartWhenUserHaveNotAddedAnything(){
        CartDto cart = cartFacade.getCart();

        CartDto emptyCart = CartDto.builder().username("Kasia")
                .items(List.of())
                .build();
        assertNotNull(cart);
        assertEquals(emptyCart,cart);
    }
    @Test
    void shouldDeleteProductIfQuantityEqualsZero(){

        cartFacade.add(itemId, 1);

        cartFacade.update(itemId, 0);

        CartDto cart = cartFacade.getCart();
        assertEquals(List.of(),cart.getItems());
    }
    @Test
    void shouldClearCartSuccessfully(){
    cartFacade.add(itemId,1);
    CartDto clearedCart = cartFacade.clearCart();

    assertEquals(new CartDto("Kasia",List.of(),0),clearedCart);
    }

    @Test
    void shouldThrowExceptionWhenAddingQuantityBelowZero(){

        assertThrows(IllegalArgumentException.class,()->cartFacade.add(itemId,-1));
    }
    @Test
    void shouldAddTwoProductsWithTheSameId(){
        cartFacade.add(itemId, 1);
        cartFacade.add(itemId, 4);

        CartDto cart = cartFacade.getCart();
        CartDto expected =  new CartDto("Kasia",List.of(new CartItemDto(itemId,5)),0.0);
        assertEquals(expected,cart);
    }

    @Test
    void shouldThrowExceptionWhenUpdateQuantityBelowZero(){

        assertThrows(IllegalArgumentException.class,()->cartFacade.update(itemId,-1));
    }
}
