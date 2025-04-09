package com.ecommerce.shop.product.domain;


import com.ecommerce.shop.product.dto.ProductDto;
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
@Document(collection = "products")
class Product {
    @Id
    private String id;
    private String name;
    private long amount;
    private double price;
    private String description;
    private String image;
    private Category category;

    ProductDto dto() {
        return ProductDto.builder()
                .id(id)
                .name(name)
                .amount(amount)
                .price(price)
                .description(description)
                .image(image)
                .category(category.dto())
                .build();
    }
}
