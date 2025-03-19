package com.ecommerce.shop.payment.domain;

import com.ecommerce.shop.payment.dto.PaymentDto;
import com.ecommerce.shop.payment.dto.PaymentStatusDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Payment {
    private String orderId;
    private CreditCard creditCard;
    private PaymentStatus paymentStatus;

    PaymentDto dto() {
        return PaymentDto.builder()
                .orderId(orderId)
                .paymentStatusDto(PaymentStatusDto.valueOf(paymentStatus.name()))
                .build();
    }
}
