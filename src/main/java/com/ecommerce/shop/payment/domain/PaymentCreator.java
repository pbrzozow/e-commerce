package com.ecommerce.shop.payment.domain;

import com.ecommerce.shop.payment.dto.CreditCardDto;
import com.ecommerce.shop.payment.dto.PaymentRequest;

class PaymentCreator {


    Payment from(PaymentRequest request) {
        return Payment.builder()
                .orderId(request.orderId())
                .creditCard(from(request.creditCardDto()))
                .paymentStatus(PaymentStatus.SUCCESSFUL)
                .build();
    }

    private CreditCard from(CreditCardDto cardDto) {
        return CreditCard.builder()
                .cardNumber(cardDto.cardNumber())
                .cardHolderName(cardDto.cardHolderName())
                .expirationDate(cardDto.expirationDate())
                .cvv(cardDto.cvv())
                .build();
    }
}
