package com.surjo.admin.mapper;

import com.surjo.admin.entity.PictureEntity;
import com.surjo.admin.model.Picture;
import com.surjo.admin.repository.PictureRepository;
import com.surjo.admin.repository.ProductRepository;
import com.surjo.asset.common.ResultMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PictureMapper {

    private final PictureRepository pictureRepository;
    private final ProductRepository productRepository;

    public PictureMapper(PictureRepository pictureRepository, ProductRepository productRepository) {
        this.pictureRepository = pictureRepository;
        this.productRepository = productRepository;
    }

    public ResultMapper<PictureEntity, Picture> entityToDomainMapper() {
        return entity -> new Picture()
                .setId(entity.getId())
                .setSmall(new String(entity.getSmall()))
                .setBig(new String(entity.getBig()))
                .setSmallImageName(entity.getSmallImageName())
                .setSmallImageType(entity.getSmallImageType())
                .setBigImageName(entity.getBigImageName())
                .setBigImageType(entity.getBigImageType())
                .setProduct(entity.getProduct().getId());
    }

    public ResultMapper<Picture, PictureEntity> domainToEntityMapper() {
        return domain ->{
            PictureEntity entity= new PictureEntity();
            if(domain.getId()!=null){
                entity=pictureRepository.getOne(domain.getId());
            }
            entity.setId(domain.getId())
                    .setSmall(domain.getSmall().getBytes())
                    .setBig(domain.getBig().getBytes())
                    .setSmallImageName(domain.getSmallImageName())
                    .setSmallImageType(domain.getSmallImageType())
                    .setBigImageName(domain.getBigImageName())
                    .setBigImageType(domain.getBigImageType())
                    .setProduct(productRepository.getOne(domain.getProduct()));
            return entity;
        };
    }
}
