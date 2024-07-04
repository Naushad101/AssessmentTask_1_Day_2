package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.CategoryNotFoundException;
import com.example.model.Category;
import com.example.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {


    CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @PostMapping()
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.saveCategory(category),HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> getCategory = categoryService.getCategory();
        return ResponseEntity.status(HttpStatus.FOUND).body(getCategory);
    }

    @PutMapping()
    public ResponseEntity<Category> updateCategory(Category category){
        return categoryService.updateCategory(category);
    } 
    
    @DeleteMapping()
    public void deleteCategory(@RequestParam("id") Long id){
        categoryService.deleteCategory(id);
    }
}
