package com.ecommerce.shop.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderStatusSimulator {
    private final OrderRepository orderRepository;
    private final OrderFacade orderFacade;

    @Scheduled(cron = "*/30 * * * * *")
    public void simulateOrderProcessing() {
        List<Order> createdOrders = orderRepository.findAllByStatus(OrderStatus.CREATED);
        createdOrders.forEach(order -> orderFacade.process(order.getId()));
    }

    @Scheduled(cron = "0 * * * * *")
    public void simulateOrderShipping() {
        List<Order> processingOrders = orderRepository.findAllByStatus(OrderStatus.PROCESSING);
        processingOrders.forEach(order -> orderFacade.ship(order.getId()));
    }

}
