package com.ecommerce.shop.payment.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentConfiguration {

    @Bean
    PaymentFacade paymentFacade(PaymentRepository paymentRepository) {
        PaymentCreator paymentCreator = new PaymentCreator();
        PaymentService paymentService = new PaymentService(paymentRepository, paymentCreator);
        return new PaymentFacade(paymentService);
    }

}
