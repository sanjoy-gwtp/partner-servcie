package com.surjo.admin.mapper;

import com.surjo.admin.entity.CartEntity;
import com.surjo.admin.model.Cart;
import com.surjo.admin.repository.CartRepository;
import com.surjo.asset.common.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    private final CartRepository cartRepository;
    private final ProductMapper productMapper;

    public CartMapper(CartRepository cartRepository, ProductMapper productMapper) {
        this.cartRepository = cartRepository;
        this.productMapper = productMapper;
    }
    public ResultMapper<CartEntity, Cart> entityToDomainMapper() {
        return entity -> new Cart()
                .setId(entity.getId())
                .setCustomer(entity.getCustomer())
                .setQuantity(entity.getQuantity())
                .setProduct(productMapper.entityToDomainMapper().map(entity.getProduct()));
    }

    public ResultMapper<Cart, CartEntity> domainToEntityMapper() {
        return domain ->
            cartRepository.findById(domain.getId()).orElse(new CartEntity())
                .setId(domain.getId())
                .setCustomer(domain.getCustomer())
                .setQuantity(domain.getQuantity())
                .setProduct(productMapper.domainToEntityMapper().map(domain.getProduct()));
    }
}
