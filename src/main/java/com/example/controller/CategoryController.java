package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.CategoryNotFoundException;
import com.example.exception.CategroyIsAlreadyPresent;
import com.example.model.Category;
import com.example.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) throws CategroyIsAlreadyPresent {
        log.info("Saving category: {}", category.getCategoryName());
        ResponseEntity<Category> responseEntity = new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
        log.info("Saved category: {}", responseEntity.getBody().getCategoryName());
        return responseEntity;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getCategory() throws CategoryNotFoundException {
        log.info("Fetching all categories");
        List<Category> categories = categoryService.getCategory();
        log.info("Fetched {} categories", categories.size());
        return ResponseEntity.status(HttpStatus.FOUND).body(categories);
    }

    @PutMapping()
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws CategoryNotFoundException {
        log.info("Updating category with id: {}", category.getCategoryId());
        ResponseEntity<Category> responseEntity = categoryService.updateCategory(category);
        log.info("Updated category with id {}: {}", category.getCategoryId(), responseEntity.getBody().getCategoryName());
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id) throws CategoryNotFoundException {
        log.info("Deleting category with id: {}", id);
        categoryService.deleteCategory(id);
        log.info("Deleted category with id: {}", id);
    }
}
