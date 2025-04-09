package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.domain.cart.CartService;
import com.ecommerce.shop.order.domain.spi.PaymentPort;
import com.ecommerce.shop.order.domain.spi.ShipmentPort;
import com.ecommerce.shop.product.domain.ProductFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfiguration {

    OrderFacade orderFacade(PaymentPort paymentPort, ShipmentPort shipmentPort, CartService cartService, ProductFacade productFacade) {
        return orderFacade(new InMemoryOrderRepository(), paymentPort, shipmentPort, cartService, productFacade);
    }

    @Bean
    OrderFacade orderFacade(OrderRepository orderRepository, PaymentPort paymentPort, ShipmentPort shipmentPort, CartService cartService, ProductFacade productFacade) {
        OrderCreator orderCreator = new OrderCreator();
        PaymentService paymentService = new PaymentService(paymentPort);
        ShipmentService shipmentService = new ShipmentService(shipmentPort);
        OrderService orderService = new OrderService(orderRepository, paymentService, shipmentService, cartService, orderCreator, productFacade);
        return new OrderFacade(orderService);
    }


}
