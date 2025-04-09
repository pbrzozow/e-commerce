package com.ecommerce.shop.stock;

import com.ecommerce.shop.product.dto.ProductDto;
import com.ecommerce.shop.stock.domain.StockFacade;
import com.ecommerce.shop.stock.dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class ProductStockController {
    private final StockFacade stockFacade;

    @PostMapping("/stock")
    ResponseEntity<StockDto> updateStock(@RequestBody StockDto stockDto) {
        StockDto update = stockFacade.update(stockDto.getProductId(), stockDto.getAmount());
        return ResponseEntity.ok(update);
    }
}
