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
@Document(collection = "products")
class Product {
    @Id
    private String id;
    private String name;
    private Double price;
    private Long availableAmount;
    private String description;
    private String image;
    private Category category;

    ProductDto dto() {
        return ProductDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .availableAmount(availableAmount)
                .description(description)
                .image(image)
                .category(category.dto())
                .build();
    }
}
