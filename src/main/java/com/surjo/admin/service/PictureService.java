package com.surjo.admin.service;

import com.surjo.admin.model.Order;
import com.surjo.admin.model.Picture;

import java.util.List;

public interface PictureService {

    Long createPicture(Picture picture);

    List<Picture> getAllPictureList();

    Picture getPictureById(Long id);

    void deletePicture(Long id);

    void deletePicturesByProductId(Long productId);
}
