package com.shop.ecommerce.costcalculator;

import com.shop.ecommerce.product.domain.ProductFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CostCalculationConfiguration {

    @Bean
    CostCalculatorFacade costCalculatorFacade(ProductFacade productFacade){
        return new CostCalculatorFacade(productFacade);
    }
}
