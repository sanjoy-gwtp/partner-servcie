package com.surjo.admin.repository;

import com.surjo.admin.entity.PictureEntity;
import com.surjo.admin.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<PictureEntity,Long> {

    List<PictureEntity> findPicturesByProduct(ProductEntity product);

}
