package com.surjo.admin.repository;

import com.surjo.admin.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    List<OrderEntity> findOrderEntityByCustomer(String customer);

}
