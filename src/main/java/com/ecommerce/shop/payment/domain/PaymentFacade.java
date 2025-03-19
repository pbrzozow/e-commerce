package com.ecommerce.shop.payment.domain;

import com.ecommerce.shop.payment.dto.PaymentDto;
import com.ecommerce.shop.payment.dto.PaymentRequest;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class PaymentFacade {
    private final PaymentService paymentService;

    public PaymentDto process(PaymentRequest paymentRequest) {
        requireNonNull(paymentRequest);
        Payment payment = paymentService.process(paymentRequest);
        return payment.dto();
    }

    public PaymentDto refund(String orderId) {
        requireNonNull(orderId);
        Payment refund = paymentService.refund(orderId);
        return refund.dto();
    }
}
