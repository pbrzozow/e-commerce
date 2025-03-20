package com.ecommerce.shop.shipment.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

interface ShipmentRepository extends MongoRepository<Shipment, String> {
    Shipment save(Shipment shipment);

    Shipment findByOrderId(String id);

    List<Shipment> findAllByStatus(Status status);
}
