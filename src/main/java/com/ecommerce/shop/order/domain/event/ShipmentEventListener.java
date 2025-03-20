package com.ecommerce.shop.order.domain.event;

import com.ecommerce.shop.order.domain.OrderFacade;
import com.ecommerce.shop.shipment.domain.event.ShipmentDeliveredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ShipmentEventListener {
    private final OrderFacade orderFacade;

    @EventListener
    public void onShipmentDelivered(ShipmentDeliveredEvent event) {
        String orderId = event.getShipmentId();
        orderFacade.delivered(orderId);
    }
}
