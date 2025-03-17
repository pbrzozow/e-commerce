package com.ecommerce.shop.order.domain;

import java.util.concurrent.ConcurrentHashMap;

class InMemoryOrderRepository implements OrderRepository{
    private final ConcurrentHashMap<String, Order> orders = new ConcurrentHashMap<>();
    @Override
    public Order findById(String id) {
        return orders.get(id);
    }
    public Order save(Order order){
        String id = String.valueOf(orders.size());
        order.setId(id);
        orders.put(order.getId(),order);
        return order;
    }
}
