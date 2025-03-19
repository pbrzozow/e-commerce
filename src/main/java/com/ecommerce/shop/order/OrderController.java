package com.ecommerce.shop.order;

import com.ecommerce.shop.order.domain.OrderFacade;
import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
class OrderController {
    private final OrderFacade orderFacade;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequest orderRequest) {
        OrderDto order = orderFacade.create(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<OrderDto> processOrder(@PathVariable String id) {
        OrderDto order = orderFacade.process(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{id}/ship")
    public ResponseEntity<OrderDto> shipOrder(@PathVariable String id) {
        OrderDto order = orderFacade.ship(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable String id) {
        OrderDto order = orderFacade.cancel(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{id}/refund")
    public ResponseEntity<OrderDto> refundOrder(@PathVariable String id) {
        OrderDto order = orderFacade.refund(id);
        return ResponseEntity.ok(order);
    }
}

