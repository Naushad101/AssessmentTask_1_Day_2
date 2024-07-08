package com.example.service.Impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.exception.CategoryNotFoundException;
import com.example.exception.CategroyIsAlreadyPresent;
import com.example.model.Category;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        log.info("Saving category: {}", category.getCategoryName());
        
        if (categoryRepository.findAll().contains(category.getCategoryName())) {
            throw new CategroyIsAlreadyPresent("Category " + category.getCategoryName() + " is already present");
        }

        Category savedCategory = categoryRepository.save(category);
        log.info("Saved category: {}", savedCategory.getCategoryName());
        return savedCategory;
    }

    public List<Category> getCategory(){
        log.info("Fetching all categories");

        try {
            List<Category> categories = categoryRepository.findAll();
            log.info("Fetched {} categories", categories.size());
            
            return categories;
        } catch (DataAccessException e) {
            String message = "Failed to retrieve categories from the database";
            log.error(message, e);
            throw new CategoryNotFoundException(message+" "+e);
        }
    }

    public ResponseEntity<Category> updateCategory(Category category){
        log.info("Updating category with id: {}", category.getCategoryId());

        if (categoryRepository.findById(category.getCategoryId()).isEmpty()) {
            throw new CategoryNotFoundException("Category with id " + category.getCategoryId() + " is not present in database");
        }

        Category existingCategory = categoryRepository.findById(category.getCategoryId()).orElseThrow();
        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setCategoryId(category.getCategoryId());
        existingCategory.setCategoryDescription(category.getCategoryDescription());

        ResponseEntity<Category> responseEntity = new ResponseEntity<>(categoryRepository.save(existingCategory), HttpStatus.ACCEPTED);
        log.info("Updated category with id {}: {}", category.getCategoryId(), existingCategory.getCategoryName());
        return responseEntity;
    }

    public void deleteCategory(Long id){
        log.info("Deleting category with id: {}", id);

        if (categoryRepository.findById(id).isEmpty()) {
            throw new CategoryNotFoundException("Category with id " + id + " is not present in Database");
        }

        categoryRepository.deleteById(id);
        log.info("Deleted category with id: {}", id);
    }

}
