package com.ecommerce.shop.payment.domain;

import org.springframework.data.repository.Repository;

interface PaymentRepository extends Repository<Payment, String> {
    Payment save(Payment payment);

    Payment findByOrderId(String orderId);
}
