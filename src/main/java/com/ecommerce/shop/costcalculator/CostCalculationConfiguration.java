package com.ecommerce.shop.costcalculator;

import com.ecommerce.shop.product.domain.ProductFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CostCalculationConfiguration {

    @Bean
    CostCalculatorFacade costCalculatorFacade(ProductFacade productFacade){
        return new CostCalculatorFacade(productFacade);
    }
}
