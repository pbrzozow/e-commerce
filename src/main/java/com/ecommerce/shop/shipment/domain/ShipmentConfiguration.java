package com.ecommerce.shop.shipment.domain;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ShipmentConfiguration {

    @Bean
    ShipmentFacade shipmentFacade(ShipmentRepository shipmentRepository, ApplicationEventPublisher applicationEventPublisher) {
        ShipmentCreator shipmentCreator = new ShipmentCreator();
        ShipmentService shipmentService = new ShipmentService(shipmentRepository, shipmentCreator, applicationEventPublisher);
        return new ShipmentFacade(shipmentService);
    }
}
