package com.ecommerce.shop.order.domain;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

interface OrderRepository extends MongoRepository<Order, String> {
    Optional<Order> findById(String id);

    Order save(Order order);

    List<Order> findAllByStatus(OrderStatus orderStatus);

    List<Order> findAllByCustomerInfo_Email(String email);


}

