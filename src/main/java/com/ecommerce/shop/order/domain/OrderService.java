package com.ecommerce.shop.order.domain;

import com.ecommerce.infrastructure.authentication.CurrentUserGetter;
import com.ecommerce.shop.order.domain.cart.CartService;
import com.ecommerce.shop.order.domain.shared.Cart;
import com.ecommerce.shop.order.domain.shared.Item;
import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.stock.domain.StockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
        Order order = findById(id);
        if (order.getStatus() == OrderStatus.CREATED) {
            List<Item> items = order.getCart().getItems();
            allocateStock(items);
            order.setStatus(OrderStatus.PROCESSING);
        }
        return orderRepository.save(order);
    }

    private Order findById(String id) {
        return orderRepository.findById(id).orElseThrow();

    }

    private void allocateStock(List<Item> items) {
        for (Item item : items) {
            String productId = item.getProductId();
            stockFacade.allocate(productId, item.getQuantity());
        }
    }

    Order ship(String id) {
        Order order = findById(id);
        if (order.getStatus() == OrderStatus.PROCESSING) {
            shipmentService.ship(order);
            order.setStatus(OrderStatus.SHIPPING);
        }
        return orderRepository.save(order);
    }

    Order delivered(String id) {
        Order order = findById(id);
        if (order.getStatus() != OrderStatus.ANNULLED) {
            order.setStatus(OrderStatus.DELIVERED);
        }
        return orderRepository.save(order);
    }

    Order cancel(String id) {
        Order order = findById(id);
        if (order.getStatus() != OrderStatus.DELIVERED) {
            shipmentService.cancel(id);
            order.setStatus(OrderStatus.ANNULLED);
        }
        return orderRepository.save(order);
    }

    Order refund(String id) {
        Order order = findById(id);
        if (order.getStatus() != OrderStatus.REFUNDED) {
            paymentService.refund(id);
            order.setStatus(OrderStatus.REFUNDED);
        }
        return orderRepository.save(order);
    }

    List<Order> getUserOrders() {
        String username = CurrentUserGetter.getSignedInUserEmail().orElseThrow();
        return orderRepository.findAllByCustomerInfo_Email(username);
    }

    @Scheduled(cron = "*/30 * * * * *")
    void simulateOrderProcessing() {
        List<Order> createdOrders = orderRepository.findAllByStatus(OrderStatus.CREATED);
        createdOrders.forEach(order -> process(order.getId()));
    }

    @Scheduled(cron = "0 * * * * *")
    void simulateOrderShipping() {
        List<Order> processingOrders = orderRepository.findAllByStatus(OrderStatus.PROCESSING);
        processingOrders.forEach(order -> ship(order.getId()));
    }


}
