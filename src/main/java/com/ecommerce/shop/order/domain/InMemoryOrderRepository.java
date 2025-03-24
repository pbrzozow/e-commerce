package com.ecommerce.shop.order.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class InMemoryOrderRepository implements OrderRepository {
    private final ConcurrentHashMap<String, Order> orders = new ConcurrentHashMap<>();

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    public Order save(Order order) {
        String id = String.valueOf(orders.size());
        order.setId(id);
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public List<Order> findAllByStatus(OrderStatus orderStatus) {
        return List.of();
    }

    @Override
    public List<Order> findAllByCustomerInfo_Email(String email) {
        return List.of();
    }

    @Override
    public <S extends Order> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Order> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Order> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Order> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Order> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Order> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Order, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Order> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public List<Order> findAllById(Iterable<String> strings) {
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
    public void delete(Order entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Order> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Order> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return null;
    }
}
