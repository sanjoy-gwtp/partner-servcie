package com.surjo.admin.service;

import com.surjo.admin.model.Type;
import com.surjo.admin.model.WishList;

import java.util.List;

public interface WishListService {

    Long createWishList(WishList wishList);

    List<WishList> getAllWishList();

    WishList getWishListById(Long id);

    void deleteWishList(Long id);

    List<WishList> getWishListByCustomer();
}
