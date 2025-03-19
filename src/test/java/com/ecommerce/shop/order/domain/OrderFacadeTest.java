//package com.ecommerce.shop.order.domain;
//
//import com.ecommerce.shop.cart.dto.CartDto;
//import com.ecommerce.shop.cart.dto.CartItemDto;
//import com.ecommerce.shop.order.domain.spi.PaymentPort;
//import com.ecommerce.shop.order.domain.spi.ShipmentPort;
//import com.ecommerce.shop.order.domain.spi.dto.PaymentResponse;
//import com.ecommerce.shop.order.domain.spi.dto.PaymentStatus;
//import com.ecommerce.shop.order.dto.*;
//import com.ecommerce.shop.stock.domain.StockFacade;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//class OrderFacadeTest {
//    private OrderFacade orderFacade;
//
//    private final AddressDto addressDto = new AddressDto("Kwiatowa", "35-088", "Poland");
//    private final CustomerDto customerDto = new CustomerDto("Kasia", "Nowak", "k@n.com", addressDto);
//    private final PaymentDetails paymentDetails = new PaymentDetails("143224424", "Kasia", "2024-02-04", "1111");
//    private final CreateOrderRequest orderRequest = new CreateOrderRequest(customerDto, paymentDetails);
//    private OrderDto order;
//
//
//    @BeforeEach
//    void setUp() {
//        ShipmentPort shipmentPort = mock();
//        PaymentPort paymentPort = mock();
//        StockFacade stockFacade = mock();
//        CartFacade cartFacade = mock();
//        when(cartFacade.getCart()).thenReturn(new CartDto("Kasia", List.of(new CartItemDto("1", 2)), 5.0));
//        when(paymentPort.refund(any())).thenReturn(new PaymentResponse("0", PaymentStatus.SUCCESSFUL));
//        orderFacade = new OrderConfiguration().orderFacade(paymentPort, shipmentPort, stockFacade, cartFacade);
//        order = orderFacade.create(orderRequest);
//    }
//
//    @Test
//    void shouldCreateOrderSuccessfully() {
//
//        assertEquals(OrderStatusDto.CREATED, order.status());
//    }
//
//    @Test
//    void shouldProcessOrderSuccessfully() {
//
//        OrderDto processedOrder = orderFacade.process(order.id());
//        assertEquals(OrderStatusDto.PROCESSING, processedOrder.status());
//    }
//
//    @Test
//    void shouldCancelOrderSuccessfully() {
//
//        OrderDto cancelledOrder = orderFacade.cancel(order.id());
//        assertEquals(OrderStatusDto.ANNULLED, cancelledOrder.status());
//    }
//
//    @Test
//    void shouldRefundOrderSuccessfully() {
//        OrderDto refundedOrder = orderFacade.refund(order.id());
//        assertEquals(OrderStatusDto.REFUNDED, refundedOrder.status());
//    }
//
//}
