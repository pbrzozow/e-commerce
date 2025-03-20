package com.ecommerce.shop.shipment.domain;

import com.ecommerce.shop.shipment.dto.AddressDto;
import com.ecommerce.shop.shipment.dto.ShipmentRequest;

class ShipmentCreator {

    Shipment from(ShipmentRequest request) {
        return Shipment.builder()
                .orderId(request.orderId())
                .address(mapToAddress(request.addressDto()))
                .status(Status.SHIPPING)
                .build();
    }

    private Address mapToAddress(AddressDto addressDto) {
        return Address.builder()
                .street(addressDto.street())
                .postalCode(addressDto.postalCode())
                .country(addressDto.country())
                .build();
    }
}
