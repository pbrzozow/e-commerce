package com.ecommerce.infrastructure.payment;

import com.ecommerce.shop.payment.domain.PaymentFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentAdapterConfiguration {

    @Bean
    PaymentAdapter paymentAdapter(PaymentFacade paymentFacade) {
        return new PaymentAdapter(paymentFacade);
    }
}
