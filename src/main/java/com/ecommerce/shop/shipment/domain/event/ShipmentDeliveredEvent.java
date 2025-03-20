package com.ecommerce.shop.shipment.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ShipmentDeliveredEvent extends ApplicationEvent {
    private final String shipmentId;

    public ShipmentDeliveredEvent(Object subject, String shipmentId) {
        super(subject);
        this.shipmentId = shipmentId;
    }

}
