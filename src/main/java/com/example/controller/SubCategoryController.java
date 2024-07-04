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

import com.example.exception.CategoryNotFoundException;
import com.example.exception.SubCategoryIsAlreadyPresent;
import com.example.exception.SubCategoryNotFoundException;
import com.example.model.Category;
import com.example.model.SubCategory;
import com.example.service.SubCategoryService;

@RestController
@RequestMapping("/api/subcategories")
public class SubCategoryController {

    
    private SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService){
        this.subCategoryService=subCategoryService;
    }
    
    
    @PostMapping()
    public ResponseEntity<SubCategory> saveSubCategory(@RequestBody SubCategory subCategory) throws SubCategoryIsAlreadyPresent,CategoryNotFoundException{
       return subCategoryService.saveSubCategory(subCategory);
    }
    
    
    @GetMapping()
    public ResponseEntity<List<SubCategory>> getAllSubCategories() throws SubCategoryNotFoundException {
        List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long id) throws SubCategoryNotFoundException{
       return subCategoryService.getSubCategoryById(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long id, @RequestBody SubCategory subCategory) throws SubCategoryNotFoundException {
       return subCategoryService.updateSubCategory(id, subCategory);
    }
    
    // Delete operation
    @DeleteMapping("/{id}")
    public void deleteSubCategory(@PathVariable Long id) throws SubCategoryNotFoundException {
       subCategoryService.deleteSubCategory(id);
    }
}

