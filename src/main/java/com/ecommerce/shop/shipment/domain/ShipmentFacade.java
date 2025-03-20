package com.ecommerce.shop.shipment.domain;

import com.ecommerce.shop.shipment.dto.ShipmentDto;
import com.ecommerce.shop.shipment.dto.ShipmentRequest;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class ShipmentFacade {
    private final ShipmentService shipmentService;

    public ShipmentDto ship(ShipmentRequest request) {
        Shipment shipment = shipmentService.ship(request);
        return shipment.dto();
    }

    public ShipmentDto delivered(String id) {
        Shipment shipment = shipmentService.delivered(id);
        return shipment.dto();
    }

    public void cancel(String id) {
        requireNonNull(id);
        shipmentService.cancel(id);
    }
}
