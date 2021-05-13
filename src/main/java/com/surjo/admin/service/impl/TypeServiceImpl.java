package com.surjo.admin.service.impl;

import com.surjo.admin.entity.TypeEntity;
import com.surjo.admin.model.Type;
import com.surjo.admin.repository.TypeRepository;
import com.surjo.admin.service.TypeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final ModelMapper modelMapper;

    public TypeServiceImpl(TypeRepository typeRepository, ModelMapper modelMapper) {
        this.typeRepository = typeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long createType(Type type) {
        return typeRepository.save(modelMapper.map(type, TypeEntity.class)).getId();
    }

    @Override
    public List<Type> getAllTypeList() {
        return typeRepository.findAll().stream().map(typeEntity ->
                modelMapper.map(typeEntity,Type.class)).collect(Collectors.toList());
    }

    @Override
    public Type getTypeById(Long id) {
        return modelMapper.map(typeRepository.getOne(id),Type.class);
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.delete(typeRepository.getOne(id));
    }
}
