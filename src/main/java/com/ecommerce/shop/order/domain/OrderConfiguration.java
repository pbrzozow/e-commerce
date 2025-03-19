package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.domain.cart.CartService;
import com.ecommerce.shop.order.domain.spi.PaymentPort;
import com.ecommerce.shop.order.domain.spi.ShipmentPort;
import com.ecommerce.shop.stock.domain.StockFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfiguration {

    OrderFacade orderFacade(PaymentPort paymentPort, ShipmentPort shipmentPort, StockFacade stockFacade, CartService cartService) {
        return orderFacade(new InMemoryOrderRepository(), paymentPort, shipmentPort, stockFacade, cartService);
    }

    @Bean
    OrderFacade orderFacade(OrderRepository orderRepository, PaymentPort paymentPort, ShipmentPort shipmentPort, StockFacade stockFacade, CartService cartService) {
        OrderCreator orderCreator = new OrderCreator();
        PaymentService paymentService = new PaymentService(paymentPort);
        ShipmentService shipmentService = new ShipmentService(shipmentPort);
        OrderService orderService = new OrderService(orderRepository, stockFacade, paymentService, shipmentService, cartService, orderCreator);
        return new OrderFacade(orderService, cartService);
    }


}
