package com.surjo.admin.mapper;

import com.surjo.admin.entity.CategoryEntity;
import com.surjo.admin.entity.TypeEntity;
import com.surjo.admin.model.Category;
import com.surjo.admin.model.Type;
import com.surjo.admin.repository.TypeRepository;
import com.surjo.asset.common.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class TypeMapper {

    private final TypeRepository typeRepository;

    public TypeMapper(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public ResultMapper<TypeEntity, Type> entityToDomainMapper() {
        return entity -> new Type()
                .setId(entity.getId())
                .setName(entity.getName());
    }

    public ResultMapper<Type, TypeEntity> domainToEntityMapper() {
        return domain -> typeRepository.findById(domain.getId()).orElse(new TypeEntity())
                .setId(domain.getId())
                .setName(domain.getName());
    }
}
