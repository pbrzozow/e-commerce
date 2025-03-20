package com.ecommerce.infrastructure.shipment;

import com.ecommerce.shop.shipment.domain.ShipmentFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ShipmentAdapterConfiguration {

    @Bean
    ShipmentAdapter shipmentAdapter(ShipmentFacade shipmentFacade) {
        return new ShipmentAdapter(shipmentFacade);
    }
}
