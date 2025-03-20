package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.domain.spi.ShipmentPort;
import com.ecommerce.shop.order.domain.spi.dto.ShipmentRequest;
import com.ecommerce.shop.order.domain.spi.dto.ShipmentResponse;
import com.ecommerce.shop.order.domain.spi.dto.ShippingAddress;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ShipmentService {
    private final ShipmentPort shipmentPort;


    ShipmentResponse ship(Order order) {
        ShippingAddress shippingAddress = mapToShippingAddress(order.getCustomerInfo().getOrderAddress());
        ShipmentRequest shipmentRequest = new ShipmentRequest(order.getId(), shippingAddress);
        return shipmentPort.ship(shipmentRequest);
    }

    void cancel(String orderId) {
        shipmentPort.cancel(orderId);
    }

    private ShippingAddress mapToShippingAddress(OrderAddress orderAddress) {
        return new ShippingAddress(orderAddress.getStreet(), orderAddress.getPostalCode(), orderAddress.getCountry());
    }
}
