package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.exception.CategoryNotFoundException;
import com.example.exception.CategroyIsAlreadyPresent;
import com.example.model.Category;
import com.example.repository.CategoryRepository;

@Service
public class CategoryService {

    
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    public Category saveCategory(Category category){
        if(categoryRepository.findAll().contains(category.getCategoryName())){
            throw new CategroyIsAlreadyPresent("Category is already present");
        }
       return categoryRepository.save(category);
    }

    public List<Category> getCategory(){ 
        try {
            return categoryRepository.findAll();
        } catch (DataAccessException e) {
            throw new CategoryNotFoundException("Failed to retrieve categories from the database "+e);
        }
    }

    public ResponseEntity<Category> updateCategory(Category category) {

                if(categoryRepository.findById(category.getCategoryId()).isEmpty()){
                    throw new CategoryNotFoundException("Category with id " + category.getCategoryId() + " is not present in database");
                }
                Category existingCategory = categoryRepository.findById(category.getCategoryId()).get();
                existingCategory.setCategoryName(category.getCategoryName());
                existingCategory.setCategoryId(category.getCategoryId());
                existingCategory.setCategoryDescription(category.getCategoryDescription());
                return new ResponseEntity<>(categoryRepository.save(existingCategory),HttpStatus.ACCEPTED);

    }

    public void deleteCategory(Long id) {
        if(categoryRepository.findById(id).isEmpty()){
            throw new CategoryNotFoundException("Category with id "+id+" is not present in Database");
        }
       categoryRepository.deleteById(id);
    }
}
