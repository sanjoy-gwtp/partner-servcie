package com.surjo.admin.service;

import com.surjo.admin.model.Cart;
import com.surjo.admin.model.Category;

import java.util.List;

public interface CategoryService {

    Long createCategory(Category category);

    List<Category> getAllCategory();

    void deleteCategory(Long id);

    Category getCategoryById(Long id);
}
