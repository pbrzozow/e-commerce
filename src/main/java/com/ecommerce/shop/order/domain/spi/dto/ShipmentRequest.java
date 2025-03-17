package com.ecommerce.shop.order.domain.spi.dto;


public record ShipmentRequest(String orderId,ShippingAddress address) {
}
