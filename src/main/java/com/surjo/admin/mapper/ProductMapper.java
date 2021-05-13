package com.surjo.admin.mapper;

import com.surjo.admin.entity.ProductEntity;
import com.surjo.admin.model.*;
import com.surjo.admin.repository.CategoryRepository;
import com.surjo.admin.repository.ProductRepository;
import com.surjo.admin.repository.TypeRepository;
import com.surjo.asset.common.ResultMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final ProductRepository productRepository;
    private final PictureMapper pictureMapper;
    private final TypeMapper typeMapper;
    private final CategoryMapper categoryMapper;
    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;


    public ProductMapper(ProductRepository productRepository, PictureMapper pictureMapper,
                         TypeMapper typeMapper, CategoryMapper categoryMapper, TypeRepository typeRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.pictureMapper = pictureMapper;
        this.typeMapper = typeMapper;
        this.categoryMapper = categoryMapper;
        this.typeRepository = typeRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResultMapper<ProductEntity, Product> entityToDomainMapper() {
        return entity -> new Product()
                .setId(entity.getId())
                .setName(entity.getName())
                .setPrice(entity.getPrice())
                .setSalePrice(entity.getSalePrice())
                .setDiscount(entity.getDiscount())
                .setShortDetails(entity.getShortDetails())
                .setDescription(entity.getDescription())
                .setStock(entity.getStock())
                .setState(entity.getState())
                .setNewPro(entity.getNewPro())
                .setBrand(entity.getBrand())
                .setSale(entity.getSale())
                .setPictures(entity.getPictures()
                        .stream().map(pictureEntity ->
                                pictureMapper.entityToDomainMapper()
                                        .map(pictureEntity)).collect(Collectors.toList()))
                .setType(typeMapper.entityToDomainMapper().map(entity.getType()))
                .setCategory(categoryMapper.entityToDomainMapper().map(entity.getCategory()));
    }

    public ResultMapper<Product, ProductEntity> domainToEntityMapper() {
        return domain ->
                productRepository.findById(domain.getId()).orElse(new ProductEntity())
                        .setId(domain.getId())
                        .setName(domain.getName())
                        .setPrice(domain.getPrice())
                        .setSalePrice(domain.getSalePrice())
                        .setDiscount(domain.getDiscount())
                        .setShortDetails(domain.getShortDetails())
                        .setDescription(domain.getDescription())
                        .setStock(domain.getStock())
                        .setState(domain.getState())
                        .setNewPro(domain.getNewPro())
                        .setBrand(domain.getBrand())
                        .setSale(domain.getSale())
                        .setType(typeRepository.getOne(domain.getType().getId()))
                        .setCategory(categoryRepository.getOne(domain.getCategory().getId()));
    }
}
