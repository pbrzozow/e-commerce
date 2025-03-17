package com.ecommerce.infrastructure.shipment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ShipmentAdapterConfiguration {

    @Bean
    ShipmentAdapter shipmentAdapter(){
        return new ShipmentAdapter();
    }
}
