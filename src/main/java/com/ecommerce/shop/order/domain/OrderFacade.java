package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.requireNonNull;

@Transactional
@RequiredArgsConstructor
public class OrderFacade {


    public OrderDto create(CreateOrderRequest orderRequest) {
        requireNonNull(orderRequest);
        return null;
    }
    public OrderDto process(String id){
        requireNonNull(id);
        return null;
    }
    public OrderDto ship(String id){
        requireNonNull(id);
        return null;
    }
    public OrderDto cancel(String id){
        requireNonNull(id);
        return null;
    }
    public OrderDto refund(String id){
        requireNonNull(id);
        return null;
    }
}
