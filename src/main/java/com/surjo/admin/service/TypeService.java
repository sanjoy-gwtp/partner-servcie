package com.surjo.admin.service;

import com.surjo.admin.model.Picture;
import com.surjo.admin.model.Type;

import java.util.List;

public interface TypeService {

    Long createType(Type type);

    List<Type> getAllTypeList();

    Type getTypeById(Long id);

    void deleteType(Long id);
}
