package com.ecommerce.shop.stock.domain;

import com.ecommerce.shop.stock.dto.StockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockFacadeTest {
    private StockFacade stockFacade;

    private final String productId = "1";

    @BeforeEach
    void setUp(){
        stockFacade = new StockConfiguration().stockFacade();
    }

    @Test
    void shouldRetrieveProductStockSuccessfully(){
        stockFacade.update(productId,2);
        StockDto inStock = stockFacade.getStock(productId);
        assertEquals(new StockDto(productId,2),inStock);
    }

    @Test
    void shouldUpdateProductStockSuccessfully(){
        stockFacade.update(productId,2);
        StockDto stock = stockFacade.update(productId, 1);

        assertEquals(new StockDto(productId,1),stock);
    }

    @Test
    void shouldRetrieveProductStockWhenAmountEqualsZero(){
        StockDto inStock = stockFacade.getStock(productId);
        assertEquals(new StockDto(productId,0),inStock);
    }
}
