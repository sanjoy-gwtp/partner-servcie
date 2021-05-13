package com.surjo.admin.repository;

import com.surjo.admin.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by sanjoy on 3/9/19.
 */
public interface ProductRepository extends JpaRepository<ProductEntity,Long> , JpaSpecificationExecutor<ProductEntity> {

}
