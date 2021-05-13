package com.surjo.admin.mapper;

import com.surjo.admin.entity.WishListEntity;
import com.surjo.admin.model.WishList;
import com.surjo.admin.repository.WishListRepository;
import com.surjo.asset.common.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class WishListMapper {

    private final WishListRepository wishListRepository;
    private final ProductMapper productMapper;

    public WishListMapper(WishListRepository wishListRepository, ProductMapper productMapper) {
        this.wishListRepository = wishListRepository;
        this.productMapper = productMapper;
    }

    public ResultMapper<WishListEntity, WishList> entityToDomainMapper() {
        return entity -> new WishList()
                .setId(entity.getId())
                .setCustomer(entity.getCustomer())
                .setProduct(productMapper.entityToDomainMapper().map(entity.getProduct()));
    }

    public ResultMapper<WishList, WishListEntity> domainToEntityMapper() {
        return domain -> wishListRepository.findById(domain.getId()).orElse(new WishListEntity())
                .setId(domain.getId())
                .setCustomer(domain.getCustomer())
                .setProduct(productMapper.domainToEntityMapper().map(domain.getProduct()));
    }
}
