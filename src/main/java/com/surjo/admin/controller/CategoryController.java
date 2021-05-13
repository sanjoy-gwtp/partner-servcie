package com.surjo.admin.controller;

import com.surjo.admin.model.Cart;
import com.surjo.admin.model.Category;
import com.surjo.admin.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/category",produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping
    public ResponseEntity getAllCategory() {
        List<Category> categoryList = categoryService.getAllCategory();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @DeleteMapping
    public ResponseEntity deleteCategory(@RequestParam(name="id",required = true)Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.accepted().build();
    }
}
