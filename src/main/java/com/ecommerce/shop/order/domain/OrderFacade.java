package com.ecommerce.shop.order.domain;


import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public OrderDto delivered(String id) {
        requireNonNull(id);
        Order order = orderService.delivered(id);
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

    public OrderDto getOrder(String id) {
        requireNonNull(id);
        Order order = orderService.getOrder(id);
        return order.dto();
    }

    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders()
                .stream()
                .map(Order::dto)
                .toList();
    }

    public List<OrderDto> getUserOrders() {
        return orderService.getUserOrders()
                .stream()
                .map(Order::dto)
                .toList();
    }

}
