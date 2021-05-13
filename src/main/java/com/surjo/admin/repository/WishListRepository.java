package com.surjo.admin.repository;

import com.surjo.admin.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishListEntity,Long>{

    List<WishListEntity> findWishListEntitiesByCustomer(String customer);

}
