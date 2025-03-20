package com.ecommerce.shop.shipment.dto;

import lombok.Builder;

@Builder
public record ShipmentDto(String orderId, AddressDto addressDto, ShipmentStatusDto shipmentStatusDto) {
}
