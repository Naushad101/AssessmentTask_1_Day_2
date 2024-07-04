package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.model.SubCategory;
import com.example.service.SubCategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/subcategory")
@Slf4j
public class SubCategoryController {

   
    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService){
        this.subCategoryService = subCategoryService;
    }
    
    @PostMapping()
    public ResponseEntity<SubCategory> saveSubCategory(@RequestBody SubCategory subCategory) throws SubCategoryIsAlreadyPresent, CategoryNotFoundException {
        log.info("Saving subcategory: {}", subCategory.getSubCategoryName());
        ResponseEntity<SubCategory> responseEntity = subCategoryService.saveSubCategory(subCategory);
        log.info("Saved subcategory: {}", responseEntity.getBody().getSubCategoryName());
        return responseEntity;
    }
    
    @GetMapping()
    public ResponseEntity<List<SubCategory>> getAllSubCategories() throws SubCategoryNotFoundException {
        log.info("Fetching all subcategories");
        List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
        log.info("Fetched {} subcategories", subCategories.size());
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long id) throws SubCategoryNotFoundException {
        log.info("Fetching subcategory with id: {}", id);
        ResponseEntity<SubCategory> responseEntity = subCategoryService.getSubCategoryById(id);
        log.info("Fetched subcategory with id {}: {}", id, responseEntity.getBody().getSubCategoryName());
        return responseEntity;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long id, @RequestBody SubCategory subCategory) throws SubCategoryNotFoundException {
        log.info("Updating subcategory with id: {}", id);
        ResponseEntity<SubCategory> responseEntity = subCategoryService.updateSubCategory(id, subCategory);
        log.info("Updated subcategory with id {}: {}", id, responseEntity.getBody().getSubCategoryName());
        return responseEntity;
    }
    
    @DeleteMapping("/{id}")
    public void deleteSubCategory(@PathVariable Long id) throws SubCategoryNotFoundException {
        log.info("Deleting subcategory with id: {}", id);
        subCategoryService.deleteSubCategory(id);
        log.info("Deleted subcategory with id: {}", id);
    }
}
