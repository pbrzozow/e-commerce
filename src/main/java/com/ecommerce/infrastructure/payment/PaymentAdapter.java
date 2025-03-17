package com.ecommerce.infrastructure.payment;

import com.ecommerce.shop.order.domain.spi.PaymentPort;
import com.ecommerce.shop.order.domain.spi.dto.PaymentRequest;
import com.ecommerce.shop.order.domain.spi.dto.PaymentResponse;
import com.ecommerce.shop.order.domain.spi.dto.RefundRequest;

public class PaymentAdapter implements PaymentPort {
    @Override
    public PaymentResponse process(PaymentRequest request) {
        return null;
    }

    @Override
    public PaymentResponse refund(RefundRequest request) {
        return null;
    }
}
