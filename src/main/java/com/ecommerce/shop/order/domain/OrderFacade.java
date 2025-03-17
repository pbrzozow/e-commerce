package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.requireNonNull;

@Transactional
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderManager orderManager;


    public OrderDto create(CreateOrderRequest orderRequest) {
        requireNonNull(orderRequest);
        Order order = orderManager.create(orderRequest);
        return order.dto();
    }
    public OrderDto process(String id){
        requireNonNull(id);
        Order order = orderManager.process(id);
        return order.dto();
    }
    public OrderDto ship(String id){
        requireNonNull(id);
        Order order = orderManager.ship(id);
        return order.dto();
    }
    public OrderDto cancel(String id){
        requireNonNull(id);
        Order order = orderManager.cancel(id);
        return order.dto();
    }
    public OrderDto refund(String id){
        requireNonNull(id);
        Order order = orderManager.refund(id);
        return order.dto();
    }
}
