package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.domain.cart.CartService;
import com.ecommerce.shop.order.domain.shared.Cart;
import com.ecommerce.shop.order.domain.shared.Item;
import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.stock.domain.StockFacade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class OrderService {
    private final OrderRepository orderRepository;
    private final StockFacade stockFacade;
    private final PaymentService paymentService;
    private final ShipmentService shipmentService;
    private final CartService cartService;
    private final OrderCreator orderCreator;

    Order create(CreateOrderRequest request) {
        Cart cart = cartService.getCart();
        Order createdOrder = orderCreator.from(request.customerDto(), cart);
        Order order = orderRepository.save(createdOrder);
        paymentService.process(order, request.paymentDetails());
        cartService.clearCart();
        return order;
    }

    Order process(String id) {
        Order order = orderRepository.findById(id);
        if (order.getStatus() == OrderStatus.CREATED) {
            List<Item> items = order.getCart().getItems();
            allocateStock(items);
            order.setStatus(OrderStatus.PROCESSING);
        }
        return orderRepository.save(order);
    }

    private void allocateStock(List<Item> items) {
        for (Item item : items) {
            String productId = item.getProductId();
            stockFacade.allocate(productId, item.getQuantity());
        }
    }

    Order ship(String id) {
        Order order = orderRepository.findById(id);
        if (order.getStatus() == OrderStatus.PROCESSING) {
            shipmentService.ship(order);
            order.setStatus(OrderStatus.SHIPPING);
        }
        return orderRepository.save(order);
    }

    Order cancel(String id) {
        Order order = orderRepository.findById(id);
        if (order.getStatus() != OrderStatus.DELIVERED) {
            shipmentService.cancel(id);
            order.setStatus(OrderStatus.ANNULLED);
        }
        return orderRepository.save(order);
    }

    Order refund(String id) {
        Order order = orderRepository.findById(id);
        if (order.getStatus() != OrderStatus.REFUNDED) {
            paymentService.refund(id);
            order.setStatus(OrderStatus.REFUNDED);
        }
        return orderRepository.save(order);
    }
}
