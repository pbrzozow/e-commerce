package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.cart.dto.CartDto;
import com.ecommerce.shop.order.dto.CustomerDto;
import com.ecommerce.shop.order.dto.OrderDto;
import com.ecommerce.shop.order.dto.OrderStatusDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Order {
    @Id
    private String id;
    private CustomerInfo customerInfo;
    private CartDto cartDto;
    private LocalDateTime createdAt;
    private OrderStatus status;

    OrderDto dto() {
        CustomerDto customerDto = new CustomerDto(customerInfo.getFirstName(),customerInfo.getLastName(),customerInfo.getEmail(),customerInfo.getAddress().dto());
        return OrderDto.builder()
                .customerDto(customerDto)
                .cartDto(cartDto)
                .createdAt(createdAt)
                .id(id)
                .status(OrderStatusDto.valueOf(status.name()))
                .build();
    }
}
