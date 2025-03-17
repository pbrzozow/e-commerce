package com.ecommerce.shop.order.domain.spi;

import com.ecommerce.shop.order.domain.spi.dto.ShipmentRequest;
import com.ecommerce.shop.order.domain.spi.dto.ShipmentResponse;

public interface ShipmentPort {
    ShipmentResponse ship(ShipmentRequest request);

}
