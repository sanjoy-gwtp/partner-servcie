package com.surjo.admin.service.impl;

import com.surjo.admin.entity.PictureEntity;
import com.surjo.admin.entity.ProductEntity;
import com.surjo.admin.mapper.PictureMapper;
import com.surjo.admin.model.Picture;
import com.surjo.admin.repository.PictureRepository;
import com.surjo.admin.repository.ProductRepository;
import com.surjo.admin.service.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final ProductRepository productRepository;
    private final PictureMapper pictureMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, ProductRepository productRepository, PictureMapper pictureMapper) {
        this.pictureRepository = pictureRepository;
        this.productRepository = productRepository;
        this.pictureMapper = pictureMapper;
    }


    @Transactional
    public Long createPicture(Picture picture) {
        return pictureRepository.save(pictureMapper.domainToEntityMapper().map(picture)).getId();
    }

    @Override
    public List<Picture> getAllPictureList() {
        return pictureRepository.findAll().stream().map(
                pictureEntity -> pictureMapper.entityToDomainMapper().map(pictureEntity)).collect(Collectors.toList());
    }

    @Override
    public Picture getPictureById(Long id) {
        return pictureMapper.entityToDomainMapper().map(pictureRepository.getOne(id));
    }

    @Transactional
    public void deletePicture(Long id) {
        pictureRepository.delete(pictureRepository.getOne(id));
    }

    @Transactional
    public void deletePicturesByProductId(Long productId) {
        ProductEntity productEntity=productRepository.getOne(productId);
       for(PictureEntity pictureEntity: pictureRepository.findPicturesByProduct(productEntity)){
           pictureRepository.delete(pictureEntity);
       }
    }
}
