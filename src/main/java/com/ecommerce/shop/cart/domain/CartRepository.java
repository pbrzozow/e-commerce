package com.ecommerce.shop.cart.domain;


import org.springframework.data.repository.Repository;



interface CartRepository extends Repository<Cart,String> {
    Cart save(Cart cart);
    Cart findByUsername(String username);


}
