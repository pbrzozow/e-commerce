package com.ecommerce.shop.stock.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class StockConfiguration {

    StockFacade stockFacade(){
        return stockFacade(new InMemoryStockRepository());
    }

    @Bean
    StockFacade stockFacade(StockRepository stockRepository){
        StockService stockService = new StockService(stockRepository);
        return new StockFacade(stockService);
    }
}
