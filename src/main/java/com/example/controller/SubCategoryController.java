package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Category;
import com.example.model.SubCategory;
import com.example.service.SubCategoryService;

@RestController
public class SubCategoryController {

    @Autowired
    SubCategoryService subCategoryService;
    
@PostMapping("/Subcategory")
    public ResponseEntity<SubCategory> saveSubCategory(@RequestBody SubCategory subCategory){
        return new ResponseEntity<>(subCategoryService.saveSubCategory(subCategory),HttpStatus.CREATED);
    }

}
