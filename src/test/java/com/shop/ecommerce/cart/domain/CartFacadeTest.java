package com.shop.ecommerce.cart.domain;

import com.shop.ecommerce.cart.dto.CartDto;
import com.shop.ecommerce.cart.dto.CartItemDto;
import com.shop.ecommerce.product.dto.CategoryDto;
import com.shop.ecommerce.product.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WithMockUser(username = "kasia")
public class CartFacadeTest {
    private CartFacade cartFacade;

    ProductDto leatherBag = ProductDto.builder().id("001").name("Leather Bag").price(40.4).category(CategoryDto.LEATHER).build();

    @BeforeEach
    void setUp(){
        cartFacade = new CartConfiguration().cartFacade();
    }

    @Test
    void shouldAddAProductToCartSuccessfully(){

        cartFacade.add(leatherBag,2);

        CartDto cart = cartFacade.getCart();

        List<CartItemDto> expected = List.of(new CartItemDto(leatherBag.getId(), 2));
        assertEquals(expected,cart.getItems());
    }

    @Test
    void shouldReturnAnEmptyCartWhenUserHaveNotAddedAnything(){
        CartDto cart = cartFacade.getCart();
        CartDto emptyCart = new CartDto("kasia",List.of());
        assertEquals(emptyCart,cart);
    }
    @Test
    void shouldDeleteProductIfQuantityEqualsZero(){
        cartFacade.add(leatherBag, 1);

        cartFacade.add(leatherBag, 0);
        CartDto cart = cartFacade.getCart();
        assertEquals(List.of(),cart.getItems());
    }

    @Test
    void shouldThrowExceptionWhenQuantityBelowZero(){
        assertThrows(IllegalArgumentException.class,()->cartFacade.add(leatherBag,-1));
    }
}
