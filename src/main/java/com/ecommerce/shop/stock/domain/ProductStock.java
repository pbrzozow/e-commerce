package com.ecommerce.shop.stock.domain;

import com.ecommerce.shop.stock.dto.StockDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "stocks")
class ProductStock {
    @Id
    private String productId;
    private long available;

    StockDto dto() {
    return StockDto.builder()
            .productId(productId)
            .amount(available)
            .build();
    }
}
