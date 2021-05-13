package com.surjo.admin.service.impl;

import com.surjo.admin.entity.CategoryEntity;
import com.surjo.admin.model.Category;
import com.surjo.admin.repository.CategoryRepository;
import com.surjo.admin.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Long createCategory(Category category) {
        return categoryRepository.save(modelMapper.map(category, CategoryEntity.class)).getId();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll().stream().map(categoryEntity ->
                modelMapper.map(categoryEntity,Category.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.delete(categoryRepository.getOne(id));
    }

    @Override
    public Category getCategoryById(Long id) {
        return modelMapper.map(categoryRepository.getOne(id),Category.class);
    }
}
