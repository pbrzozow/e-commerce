package com.ecommerce.shop.shipment.dto;

public record ShipmentRequest(String orderId, AddressDto addressDto) {
}
