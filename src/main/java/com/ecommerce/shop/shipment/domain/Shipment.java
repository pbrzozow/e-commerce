package com.ecommerce.shop.shipment.domain;

import com.ecommerce.shop.shipment.dto.ShipmentDto;
import com.ecommerce.shop.shipment.dto.ShipmentStatusDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Shipment {
    @Id
    private String orderId;
    private Address address;
    private Status status;

    ShipmentDto dto() {
        return ShipmentDto.builder()
                .orderId(orderId)
                .addressDto(address.dto())
                .shipmentStatusDto(ShipmentStatusDto.valueOf(status.name()))
                .build();
    }
}
