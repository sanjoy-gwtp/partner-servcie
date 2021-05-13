package com.surjo.admin.service;

import com.surjo.admin.model.Cart;


import java.util.List;

public interface CartService {

    Long createCart(Cart cart);

    List<Cart> getAllCart();

    List<Cart> getCartByCustomer();

    void deleteCart(Long id);

    Cart getCartById(Long id);
}
