package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.SubCategory;
import com.example.repository.SubCategoryRepository;

@Service
public class SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    public SubCategory saveSubCategory(SubCategory subCategory){
            return subCategoryRepository.save(subCategory);
    }
}
