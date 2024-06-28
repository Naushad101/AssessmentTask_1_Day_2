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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Category;
import com.example.model.SubCategory;
import com.example.service.SubCategoryService;

@RestController
@RequestMapping("/api/subcategories")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;
    
    
    @PostMapping("/")
    public ResponseEntity<SubCategory> saveSubCategory(@RequestBody SubCategory subCategory) {
        SubCategory savedSubCategory = subCategoryService.saveSubCategory(subCategory);
        return new ResponseEntity<>(savedSubCategory, HttpStatus.CREATED);
    }
    
    
    @GetMapping("/")
    public ResponseEntity<List<SubCategory>> getAllSubCategories() {
        List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long id) {
        SubCategory subCategory = subCategoryService.getSubCategoryById(id);
        if (subCategory != null) {
            return new ResponseEntity<>(subCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long id, @RequestBody SubCategory subCategory) {
        SubCategory updatedSubCategory = subCategoryService.updateSubCategory(id, subCategory);
        if (updatedSubCategory != null) {
            return new ResponseEntity<>(updatedSubCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        boolean deleted = subCategoryService.deleteSubCategory(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

