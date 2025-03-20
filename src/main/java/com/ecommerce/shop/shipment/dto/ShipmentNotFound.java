package com.ecommerce.shop.shipment.dto;

public class ShipmentNotFound extends RuntimeException {
    public ShipmentNotFound(String id) {
        super("Shipment with id: " + id + " was not found.");
    }
}
