package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.domain.spi.PaymentPort;
import com.ecommerce.shop.order.domain.spi.dto.PaymentRequest;
import com.ecommerce.shop.order.domain.spi.dto.PaymentResponse;
import com.ecommerce.shop.order.domain.spi.dto.PaymentStatus;
import com.ecommerce.shop.order.domain.spi.dto.RefundRequest;
import com.ecommerce.shop.order.dto.PaymentDetails;
import com.ecommerce.shop.order.dto.exception.PaymentFailureException;
import com.ecommerce.shop.order.dto.exception.RefundFailureException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class PaymentService {
    private final PaymentPort paymentPort;

    PaymentResponse process(Order order, PaymentDetails paymentDetails) {
        PaymentRequest paymentRequest = new PaymentRequest(order.getId(), order.getCart().getPrice(), paymentDetails);
        PaymentResponse response = paymentPort.process(paymentRequest);
        if (response.paymentStatus() == PaymentStatus.REJECTED) {
            throw new PaymentFailureException(paymentRequest.orderId());
        }
        return response;
    }

    PaymentResponse refund(String orderId) {
        RefundRequest request = new RefundRequest(orderId);
        PaymentResponse response = paymentPort.refund(request);
        if (response.paymentStatus() != PaymentStatus.SUCCESSFUL) {
            throw new RefundFailureException(orderId);
        }
        return response;
    }
}
