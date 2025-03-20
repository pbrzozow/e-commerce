package com.ecommerce.infrastructure.shipment;

import com.ecommerce.shop.order.domain.spi.ShipmentPort;
import com.ecommerce.shop.order.domain.spi.dto.ShipmentRequest;
import com.ecommerce.shop.order.domain.spi.dto.ShipmentResponse;
import com.ecommerce.shop.order.domain.spi.dto.ShippingAddress;
import com.ecommerce.shop.shipment.domain.ShipmentFacade;
import com.ecommerce.shop.shipment.dto.AddressDto;
import com.ecommerce.shop.shipment.dto.ShipmentDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipmentAdapter implements ShipmentPort {
    private final ShipmentFacade shipmentFacade;

    @Override
    public ShipmentResponse ship(ShipmentRequest request) {
        com.ecommerce.shop.shipment.dto.ShipmentRequest shipmentRequest = mapToDomainRequest(request);
        ShipmentDto shipment = shipmentFacade.ship(shipmentRequest);
        return new ShipmentResponse(shipment.orderId(), shipment.shipmentStatusDto().name());
    }

    private static com.ecommerce.shop.shipment.dto.ShipmentRequest mapToDomainRequest(ShipmentRequest request) {
        ShippingAddress address = request.address();
        AddressDto addressDto = new AddressDto(address.street(), address.postalCode(), address.country());
        return new com.ecommerce.shop.shipment.dto.ShipmentRequest(request.orderId(), addressDto);
    }

    @Override
    public void cancel(String orderId) {
        shipmentFacade.cancel(orderId);
    }
}
