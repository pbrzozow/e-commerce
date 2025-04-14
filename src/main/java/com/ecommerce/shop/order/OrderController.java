package com.ecommerce.shop.order;

import com.ecommerce.shop.order.domain.OrderFacade;
import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
class OrderController {
    private final OrderFacade orderFacade;

    @PostMapping("/orders")
    ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequest orderRequest) {
        OrderDto order = orderFacade.create(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PostMapping("/order/{id}/process")
    ResponseEntity<OrderDto> processOrder(@PathVariable String id) {
        OrderDto order = orderFacade.process(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/order/{id}/ship")
    ResponseEntity<OrderDto> shipOrder(@PathVariable String id) {
        OrderDto order = orderFacade.ship(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/order/{id}/cancel")
    ResponseEntity<OrderDto> cancelOrder(@PathVariable String id) {
        OrderDto order = orderFacade.cancel(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/order/{id}/refund")
    ResponseEntity<OrderDto> refundOrder(@PathVariable String id) {
        OrderDto order = orderFacade.refund(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/order/{id}")
    ResponseEntity<OrderDto> getOrder(@PathVariable("id") String id) {
        OrderDto order = orderFacade.getOrder(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/admin/orders")
    ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderFacade.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders")
    ResponseEntity<List<OrderDto>> getUserOrders() {
        List<OrderDto> userOrders = orderFacade.getUserOrders();
        return ResponseEntity.ok(userOrders);
    }
}

