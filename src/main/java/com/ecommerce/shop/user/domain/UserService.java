package com.ecommerce.shop.user.domain;

import com.ecommerce.infrastructure.authentication.CurrentUserGetter;
import com.ecommerce.shop.order.domain.OrderFacade;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class UserService {
    private final OrderFacade orderFacade;


}
