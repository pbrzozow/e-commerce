package com.shop.ecommerce.cart.domain;


import org.springframework.data.repository.Repository;



interface CartRepository extends Repository<Cart,String> {
    Cart save(Cart cart);
    Cart findByUsername(String username);


}
