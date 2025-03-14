package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.cart.dto.CartDto;
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
    private CartDto cartDto;
    private LocalDateTime createdAt;
    private OrderStatus status;
}
