package com.ecommerce.shop.order.domain;


import com.ecommerce.shop.cart.domain.CartFacade;
import com.ecommerce.shop.cart.dto.CartDto;
import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.stock.domain.StockFacade;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
class OrderManager {
    private final OrderRepository orderRepository;
    private final StockFacade stockFacade;
    private final PaymentService paymentService;
    private final ShipmentService shipmentService;
    private final CartFacade cartFacade;
    private final OrderCreator orderCreator;

    Order create(CreateOrderRequest request) {
        CartDto cart = cartFacade.getCart();
        Order createdOrder = orderCreator.from(request, cart);
        Order order = orderRepository.save(createdOrder);
        paymentService.process(order,request.paymentDetails());
        cartFacade.clearCart();
        return order;
    }
    Order process(String id){
        Order order = orderRepository.findById(id);
        if (order.getStatus()==OrderStatus.CREATED){
            List<OrderItem> items = order.getOrderCart().getItems();
            allocateStock(items);
            order.setStatus(OrderStatus.PROCESSING);
        }
        return orderRepository.save(order);
    }

    private void allocateStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            String productId = item.getProductId();
            stockFacade.allocate(productId,item.getQuantity());
        }
    }

    Order ship(String id){
        Order order = orderRepository.findById(id);
        if (order.getStatus()==OrderStatus.PROCESSING){
            shipmentService.ship(order);
            order.setStatus(OrderStatus.SHIPPING);
        }
        return orderRepository.save(order);
    }



    Order cancel(String id){
        Order order = orderRepository.findById(id);
        if (order.getStatus()!=OrderStatus.DELIVERED){
            order.setStatus(OrderStatus.ANNULLED);
            //TODO 1 implement cancel logic
        }
        return orderRepository.save(order);
    }

    Order refund(String id){
        Order order = orderRepository.findById(id);
        if (order.getStatus()!=OrderStatus.REFUNDED){
            paymentService.refund(id);
            order.setStatus(OrderStatus.REFUNDED);
        }
        return orderRepository.save(order);
    }
}
