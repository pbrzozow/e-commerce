package com.ecommerce.infrastructure.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentAdapterConfiguration {

    @Bean
    PaymentAdapter paymentAdapter(){
        return new PaymentAdapter();
    }
}
