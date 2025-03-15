package com.ecommerce.shop.order.domain.spi;

import com.ecommerce.shop.order.domain.spi.dto.PaymentRequest;
import com.ecommerce.shop.order.domain.spi.dto.PaymentResponse;

public interface PaymentPort {
    PaymentResponse processPayment(PaymentRequest request);
}
