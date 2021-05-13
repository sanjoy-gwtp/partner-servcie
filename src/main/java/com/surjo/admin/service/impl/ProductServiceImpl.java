package com.surjo.admin.service.impl;

import com.surjo.admin.entity.ProductEntity;
import com.surjo.admin.mapper.ProductMapper;
import com.surjo.admin.model.Picture;
import com.surjo.admin.model.Product;
import com.surjo.admin.repository.ProductRepository;
import com.surjo.admin.repository.specification.ProductSpecification;
import com.surjo.admin.service.PictureService;
import com.surjo.admin.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sanjoy on 3/9/19.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PictureService pictureService;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, PictureService pictureService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.pictureService = pictureService;
    }

    @Transactional
    public Long createProduct(Product product) {
        List<Picture> pictureList=product.getPictures();
        ProductEntity productEntity= productRepository.save(productMapper.domainToEntityMapper().map(product));
        if(product.getId()!=null){
            pictureService.deletePicturesByProductId(product.getId());
        }
        for(Picture picture:pictureList){
            picture.setId(null);
            picture.setProduct(productEntity.getId());
            pictureService.createPicture(picture);
        }
        return productEntity.getId();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll().stream().map(
                productEntity -> productMapper.entityToDomainMapper().map(productEntity)).collect(Collectors.toList());
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.delete(productRepository.getOne(id));
    }

    @Override
    public Product getProductById(Long id) {
        return productMapper.entityToDomainMapper().map(productRepository.getOne(id));
    }

    @Override
    public List<Product> searchProduct(String name) {
        return productRepository.findAll(ProductSpecification.searchProductByName(name)).stream().map(
                productEntity -> productMapper.entityToDomainMapper().map(productEntity)).collect(Collectors.toList());
    }

}
