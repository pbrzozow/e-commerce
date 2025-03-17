package com.ecommerce.shop.order.domain;


import com.ecommerce.shop.cart.domain.CartFacade;
import com.ecommerce.shop.cart.dto.CartDto;
import com.ecommerce.shop.cart.dto.CartItemDto;
import com.ecommerce.shop.order.domain.spi.PaymentPort;
import com.ecommerce.shop.order.domain.spi.ShipmentPort;
import com.ecommerce.shop.order.domain.spi.dto.*;
import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.PaymentFailureException;
import com.ecommerce.shop.stock.domain.StockFacade;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
class OrderManager {
    private final OrderRepository orderRepository;
    private final StockFacade stockFacade;
    private final PaymentPort paymentPort;
    private final ShipmentPort shipmentPort;
    private final CartFacade cartFacade;
    private final OrderCreator orderCreator;

    Order create(CreateOrderRequest request) {
        CartDto cart = cartFacade.getCart();
        Order createdOrder = orderCreator.from(request, cart);
        Order order = orderRepository.save(createdOrder);
        PaymentRequest paymentRequest = new PaymentRequest(order.getId(),cart.getPrice());
        PaymentResponse payment = paymentPort.process(paymentRequest);
        if (payment.paymentStatus()==PaymentStatus.REJECTED){
            throw new PaymentFailureException(order.getId());
        }
        cartFacade.clearCart();
        return order;
    }
    Order process(String id){
        Order order = orderRepository.findById(id);
        if (order.getStatus()==OrderStatus.CREATED){
            List<CartItemDto> items = order.getCartDto().getItems();
            allocateStock(items);
            order.setStatus(OrderStatus.PROCESSING);
        }
        return orderRepository.save(order);
    }

    private void allocateStock(List<CartItemDto> items) {
        for (CartItemDto item : items) {
            String productId = item.getProductId();
            stockFacade.allocate(productId,item.getQuantity());
        }
    }

    Order ship(String id){
        Order order = orderRepository.findById(id);
        if (order.getStatus()==OrderStatus.PROCESSING){
            ShippingAddress shippingAddress = mapToShippingAddress(order.getCustomerInfo().getAddress());
            ShipmentRequest shipmentRequest = new ShipmentRequest(id,shippingAddress);
            shipmentPort.ship(shipmentRequest);
            order.setStatus(OrderStatus.SHIPPING);
        }
        return orderRepository.save(order);
    }
    private ShippingAddress mapToShippingAddress(Address address) {
        return new ShippingAddress(address.getStreet(), address.getPostalCode(), address.getCountry());
    }


    Order cancel(String id){
        Order order = orderRepository.findById(id);
        if (order.getStatus()!=OrderStatus.DELIVERED){
            order.setStatus(OrderStatus.ANNULLED);
        }
        return orderRepository.save(order);
    }
    Order refund(String id){
        Order order = orderRepository.findById(id);
        if (order.getStatus()!=OrderStatus.REFUNDED){
            RefundRequest refundRequest = new RefundRequest(id);
            PaymentResponse refund = paymentPort.refund(refundRequest);
            if (refund.paymentStatus()== PaymentStatus.SUCCESSFUL){
                order.setStatus(OrderStatus.REFUNDED);
            }
        }
        return orderRepository.save(order);
    }
}
