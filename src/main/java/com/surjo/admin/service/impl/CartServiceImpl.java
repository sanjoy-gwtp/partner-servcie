package com.surjo.admin.service.impl;

import com.surjo.admin.entity.CartEntity;
import com.surjo.admin.model.Cart;
import com.surjo.admin.repository.CartRepository;
import com.surjo.admin.service.CartService;
import com.surjo.security.core.CustomSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final CustomSession customSession;

    public CartServiceImpl(CartRepository cartRepository, ModelMapper modelMapper, CustomSession customSession) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
        this.customSession = customSession;
    }


    @Override
    public Long createCart(Cart cart) {
        return cartRepository.save(modelMapper.map(cart, CartEntity.class)).getId();
    }

    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll().stream().map(
                cartEntity -> modelMapper.map(cartEntity, Cart.class)).collect(Collectors.toList());
    }

    @Override
    public List<Cart> getCartByCustomer() {
        String customer=customSession.getUsername();
        return cartRepository.findCartEntitiesByCustomer(customer).stream().map(
                cartEntity -> modelMapper.map(cartEntity, Cart.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.delete(cartRepository.getOne(id));
    }

    @Override
    public Cart getCartById(Long id) {
        return modelMapper.map(cartRepository.getOne(id),Cart.class);
    }
}
