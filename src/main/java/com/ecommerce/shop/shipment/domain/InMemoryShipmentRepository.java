package com.ecommerce.shop.shipment.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class InMemoryShipmentRepository implements ShipmentRepository {
    private final ConcurrentHashMap<String, Shipment> shipments = new ConcurrentHashMap<>();

    @Override
    public Shipment save(Shipment shipment) {
        shipments.put(shipment.getOrderId(), shipment);
        return shipment;
    }

    @Override
    public Shipment findByOrderId(String id) {
        return shipments.get(id);
    }

    @Override
    public List<Shipment> findAllByStatus(Status status) {
        return List.of();
    }

    @Override
    public <S extends Shipment> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Shipment> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Shipment> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Shipment> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Shipment> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Shipment> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Shipment> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Shipment> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Shipment, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Shipment> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Shipment> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Shipment> findAll() {
        return List.of();
    }

    @Override
    public List<Shipment> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Shipment entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Shipment> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Shipment> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Shipment> findAll(Pageable pageable) {
        return null;
    }
}
