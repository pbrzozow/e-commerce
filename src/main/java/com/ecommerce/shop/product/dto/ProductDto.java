package com.ecommerce.shop.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private Double price;
    private Long availableAmount;
    private String description;
    private String image;
    private CategoryDto category;
}
