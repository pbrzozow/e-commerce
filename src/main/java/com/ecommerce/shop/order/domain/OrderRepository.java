package com.ecommerce.shop.order.domain;


import org.springframework.data.repository.Repository;

interface OrderRepository extends Repository<Order,String> {
    Order findById(String id);
    Order save(Order order);
}

