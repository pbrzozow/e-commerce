package com.ecommerce.shop.order.domain;

import com.ecommerce.infrastructure.authentication.CurrentUserGetter;
import com.ecommerce.shop.order.domain.cart.CartService;
import com.ecommerce.shop.order.domain.shared.Cart;
import com.ecommerce.shop.order.domain.shared.Item;
import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.exception.OrderNotFoundException;
import com.ecommerce.shop.product.domain.ProductFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final ShipmentService shipmentService;
    private final CartService cartService;
    private final OrderCreator orderCreator;
    private final ProductFacade productFacade;

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
        return orderRepository.findById(id)
                .orElseThrow(() ->new OrderNotFoundException(id));
    }

    private void allocateStock(List<Item> items) {
        for (Item item : items) {
            String productId = item.getProductId();
            productFacade.allocateStock(productId, item.getQuantity());
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
        String username = CurrentUserGetter.getSignedInUserEmail();
        return orderRepository.findAllByCustomerInfo_Email(username);
    }

    List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    Order getOrder(String id) {
        return findById(id);
    }
}
