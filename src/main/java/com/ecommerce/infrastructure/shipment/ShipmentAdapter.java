package com.ecommerce.infrastructure.shipment;

import com.ecommerce.shop.order.domain.spi.ShipmentPort;
import com.ecommerce.shop.order.domain.spi.dto.ShipmentRequest;
import com.ecommerce.shop.order.domain.spi.dto.ShipmentResponse;

public class ShipmentAdapter implements ShipmentPort {
    @Override
    public ShipmentResponse ship(ShipmentRequest request) {
        return null;
    }
}
