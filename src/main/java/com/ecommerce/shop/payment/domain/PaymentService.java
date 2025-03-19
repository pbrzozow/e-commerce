package com.ecommerce.shop.payment.domain;

import com.ecommerce.shop.payment.dto.PaymentRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentCreator paymentCreator;

    Payment process(PaymentRequest paymentRequest) {
        Payment payment = paymentCreator.from(paymentRequest);
        CreditCard creditCard = payment.getCreditCard();
        if (!CreditCardValidator.validate(creditCard)) {
            payment.setPaymentStatus(PaymentStatus.REJECTED);
        }
        return paymentRepository.save(payment);
    }

    Payment refund(String orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        payment.setPaymentStatus(PaymentStatus.REFUNDED);
        paymentRepository.save(payment);
        return paymentRepository.save(payment);
    }
}
