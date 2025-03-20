package com.ecommerce.shop.payment.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;

interface PaymentRepository extends MongoRepository<Payment, String> {
    Payment save(Payment payment);

    Payment findByOrderId(String orderId);
}
