package com.ecommerce.shop.order.domain.spi;

import com.ecommerce.shop.order.domain.spi.dto.PaymentRequest;
import com.ecommerce.shop.order.domain.spi.dto.PaymentResponse;
import com.ecommerce.shop.order.domain.spi.dto.RefundRequest;

public interface PaymentPort {
    PaymentResponse process(PaymentRequest request);
    PaymentResponse refund(RefundRequest request);
}
