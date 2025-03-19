package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.domain.cart.CartService;
import com.ecommerce.shop.order.domain.shared.Cart;
import com.ecommerce.shop.order.domain.shared.Item;
import com.ecommerce.shop.order.dto.CartDto;
import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.ItemDto;
import com.ecommerce.shop.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.requireNonNull;

@Transactional
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;


    public OrderDto create(CreateOrderRequest orderRequest) {
        requireNonNull(orderRequest);
        Order order = orderService.create(orderRequest);
        return order.dto();
    }

    public OrderDto process(String id) {
        requireNonNull(id);
        Order order = orderService.process(id);
        return order.dto();
    }

    public OrderDto ship(String id) {
        requireNonNull(id);
        Order order = orderService.ship(id);
        return order.dto();
    }

    public OrderDto cancel(String id) {
        requireNonNull(id);
        Order order = orderService.cancel(id);
        return order.dto();
    }

    public OrderDto refund(String id) {
        requireNonNull(id);
        Order order = orderService.refund(id);
        return order.dto();
    }

}
