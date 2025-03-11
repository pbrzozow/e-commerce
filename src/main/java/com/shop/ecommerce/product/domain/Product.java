package com.shop.ecommerce.product.domain;


import com.shop.ecommerce.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
class Product {
    @Id
    private String id;

    ProductDto dto() {
        return ProductDto.builder()
                .id(id)
                .build();
    }
}
