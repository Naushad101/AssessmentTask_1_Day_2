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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Category;
import com.example.service.CategoryService;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.saveCategory(category),HttpStatus.CREATED);
    }


    @GetMapping("/getCategory")
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> getCategory = categoryService.getCategory();
        return ResponseEntity.status(HttpStatus.FOUND).body(getCategory);
    }

    @PutMapping("/category")
    public ResponseEntity<Category> updateCategory(@RequestParam("id") Long id, @RequestParam("categoryName") String categoryName, @RequestParam("categoryDescription") String categoryDescription ){
        Category updatedCategory = categoryService.updateCategory(id,categoryName,categoryDescription);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedCategory);
    } 
    
    @DeleteMapping("/deleteCategory")
    public ResponseEntity<String> deleteCategory(@RequestParam("id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Deleted category with id : " + id);
    }
}
