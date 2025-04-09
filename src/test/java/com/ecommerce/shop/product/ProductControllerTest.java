package com.ecommerce.shop.product;

import com.ecommerce.shop.product.domain.ProductFacade;
import com.ecommerce.shop.product.dto.CategoryDto;
import com.ecommerce.shop.product.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductFacade productFacade;

    @Test
    @WithMockUser
    void shouldReturnAllProducts() throws Exception {
        when(productFacade.findAll(any())).thenReturn(new PageImpl<>(List.of(
                createSampleProducts("Bag"),
                createSampleProducts("Shoes")
        )));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].name").value("Bag"))
                .andExpect(jsonPath("$.content[1].name").value("Shoes"));
    }

    private ProductDto createSampleProducts(String name) {
        return ProductDto.builder()
                .name(name)
                .category(CategoryDto.LEATHER)
                .price(6)
                .build();
    }
}
