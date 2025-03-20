package com.ecommerce.shop.shipment.domain;

import com.ecommerce.shop.shipment.domain.event.ShipmentDeliveredEvent;
import com.ecommerce.shop.shipment.dto.ShipmentNotFound;
import com.ecommerce.shop.shipment.dto.ShipmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@RequiredArgsConstructor
class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final ShipmentCreator shipmentCreator;
    private final ApplicationEventPublisher eventPublisher;

    Shipment ship(ShipmentRequest request) {
        Shipment shipment = shipmentCreator.from(request);
        return shipmentRepository.save(shipment);
    }

    Shipment delivered(String id) {
        Shipment shipment = shipmentRepository.findByOrderId(id);
        if (shipment == null) {
            throw new ShipmentNotFound(id);
        }
        shipment.setStatus(Status.DELIVERED);
        eventPublisher.publishEvent(new ShipmentDeliveredEvent(this, id));
        return shipmentRepository.save(shipment);

    }

    Shipment cancel(String id) {
        Shipment shipment = shipmentRepository.findByOrderId(id);
        if (shipment == null) {
            throw new ShipmentNotFound(id);
        }
        shipment.setStatus(Status.CANCELLED);
        return shipmentRepository.save(shipment);
    }

    @Scheduled(cron = "0 */2 * * * *")
    void simulateOrderShipping() {
        List<Shipment> shipments = shipmentRepository.findAllByStatus(Status.SHIPPING);
        shipments.forEach(shipment -> delivered(shipment.getOrderId()));
    }
}
