package com.surjo.admin.mapper;

import com.surjo.admin.entity.CartEntity;
import com.surjo.admin.entity.CategoryEntity;
import com.surjo.admin.model.Cart;
import com.surjo.admin.model.Category;
import com.surjo.admin.repository.CategoryRepository;
import com.surjo.asset.common.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private final CategoryRepository categoryRepository;

    public CategoryMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResultMapper<CategoryEntity, Category> entityToDomainMapper() {
        return entity -> new Category()
                .setId(entity.getId())
                .setName(entity.getName());
    }

    public ResultMapper<Category, CategoryEntity> domainToEntityMapper() {
        return domain ->
                categoryRepository.findById(domain.getId()).orElse(new CategoryEntity())
                        .setId(domain.getId())
                        .setName(domain.getName());
    }
}
