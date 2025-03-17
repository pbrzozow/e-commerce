package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderFacadeTest {
    private OrderFacade orderFacade;

    @BeforeEach
    void setUp(){
        orderFacade = new OrderConfiguration().orderFacade();
    }

    @Test
    void shouldCreateOrderSuccessfully(){
        CreateOrderRequest orderRequest = new CreateOrderRequest();
        OrderDto order = orderFacade.create(orderRequest);

    }
    @Test
    void shouldRetrieveOrdersByEmail(){

    }
    @Test
    void shouldCancelOrderSuccessfully(){

    }
    @Test
    void shouldRefundOrderSuccessfully(){

    }
    @Test
    void shouldProcessOrderSuccessfully(){

    }
}
