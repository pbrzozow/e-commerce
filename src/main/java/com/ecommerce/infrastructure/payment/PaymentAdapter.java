package com.ecommerce.infrastructure.payment;

import com.ecommerce.shop.order.domain.spi.PaymentPort;
import com.ecommerce.shop.order.domain.spi.dto.PaymentRequest;
import com.ecommerce.shop.order.domain.spi.dto.PaymentResponse;
import com.ecommerce.shop.order.domain.spi.dto.PaymentStatus;
import com.ecommerce.shop.order.domain.spi.dto.RefundRequest;
import com.ecommerce.shop.order.dto.PaymentDetails;
import com.ecommerce.shop.payment.domain.PaymentFacade;
import com.ecommerce.shop.payment.dto.CreditCardDto;
import com.ecommerce.shop.payment.dto.PaymentDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentAdapter implements PaymentPort {
    private final PaymentFacade paymentFacade;

    @Override
    public PaymentResponse process(PaymentRequest request) {
        com.ecommerce.shop.payment.dto.PaymentRequest paymentRequest = mapToDomainRequest(request);
        PaymentDto payment = paymentFacade.process(paymentRequest);
        return mapToResponse(payment);
    }

    @Override
    public PaymentResponse refund(RefundRequest request) {
        PaymentDto refund = paymentFacade.refund(request.orderId());
        return mapToResponse(refund);
    }

    private PaymentResponse mapToResponse(PaymentDto payment) {
        return new PaymentResponse(payment.orderId(), PaymentStatus.valueOf(payment.paymentStatusDto().name()));
    }


    private com.ecommerce.shop.payment.dto.PaymentRequest mapToDomainRequest(PaymentRequest request) {
        PaymentDetails card = request.paymentDetails();
        CreditCardDto cardDto = getCreditCardDto(card);
        return new com.ecommerce.shop.payment.dto.PaymentRequest(request.orderId(), request.price(), cardDto);
    }

    private static CreditCardDto getCreditCardDto(PaymentDetails details) {
        String cardNum = details.cardNumber();
        String holderName = details.cardHolderName();
        String expiration = details.expirationDate();
        String cvv = details.cvv();
        return new CreditCardDto(cardNum, holderName, expiration, cvv);
    }


}
