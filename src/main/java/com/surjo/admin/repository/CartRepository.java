package com.surjo.admin.repository;

import com.surjo.admin.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity,Long> {

    List<CartEntity> findCartEntitiesByCustomer(String customer);

}
