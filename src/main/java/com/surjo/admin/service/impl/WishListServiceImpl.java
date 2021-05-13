package com.surjo.admin.service.impl;

import com.surjo.admin.entity.WishListEntity;
import com.surjo.admin.model.WishList;
import com.surjo.admin.repository.WishListRepository;
import com.surjo.admin.service.WishListService;
import com.surjo.security.core.CustomSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;
    private final ModelMapper modelMapper;
    private final CustomSession customSession;

    public WishListServiceImpl(WishListRepository wishListRepository, ModelMapper modelMapper, CustomSession customSession) {
        this.wishListRepository = wishListRepository;
        this.modelMapper = modelMapper;
        this.customSession = customSession;
    }

    @Override
    public Long createWishList(WishList wishList) {
        return wishListRepository.save(modelMapper.map(wishList, WishListEntity.class)).getId();
    }

    @Override
    public List<WishList> getAllWishList() {
        return wishListRepository.findAll().stream().map(
                wishListEntity -> modelMapper.map(wishListEntity,WishList.class)
        ).collect(Collectors.toList());
    }

    @Override
    public WishList getWishListById(Long id) {
        return modelMapper.map(wishListRepository.getOne(id),WishList.class);
    }

    @Override
    public void deleteWishList(Long id) {
        wishListRepository.delete(wishListRepository.getOne(id));
    }

    @Override
    public List<WishList> getWishListByCustomer() {
        return wishListRepository.findWishListEntitiesByCustomer(customSession.getUsername()).stream().map(
                wishListEntity -> modelMapper.map(wishListEntity,WishList.class)
        ).collect(Collectors.toList());
    }
}
