package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.domain.spi.PaymentPort;
import com.ecommerce.shop.order.domain.spi.ShipmentPort;
import com.ecommerce.shop.stock.domain.StockFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfiguration {

    OrderFacade orderFacade(PaymentPort paymentPort, ShipmentPort shipmentPort, StockFacade stockFacade, CartFacade cartFacade) {
        return orderFacade(new InMemoryOrderRepository(), paymentPort, shipmentPort, stockFacade, cartFacade);
    }

    @Bean
    OrderFacade orderFacade(OrderRepository orderRepository, PaymentPort paymentPort, ShipmentPort shipmentPort, StockFacade stockFacade, CartFacade cartFacade) {
        OrderCreator orderCreator = new OrderCreator();
        PaymentService paymentService = new PaymentService(paymentPort);
        ShipmentService shipmentService = new ShipmentService(shipmentPort);
        OrderManager orderManager = new OrderManager(orderRepository, stockFacade, paymentService, shipmentService, cartFacade, orderCreator);
        return new OrderFacade(orderManager);
    }


}
