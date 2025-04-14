package com.ecommerce.shop.shipment.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShipmentStatusSimulator {
    private final ShipmentRepository shipmentRepository;
    private final ShipmentFacade shipmentFacade;

    @Scheduled(cron = "0 */2 * * * *")
    public void simulateOrderShipping() {
        List<Shipment> shipments = shipmentRepository.findAllByStatus(Status.SHIPPING);
        shipments.forEach(shipment -> shipmentFacade.delivered(shipment.getOrderId()));
    }
}
