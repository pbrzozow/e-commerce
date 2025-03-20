package com.ecommerce.shop.shipment.domain;

import com.ecommerce.shop.shipment.dto.ShipmentDto;
import com.ecommerce.shop.shipment.dto.ShipmentStatusDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Shipment {
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
