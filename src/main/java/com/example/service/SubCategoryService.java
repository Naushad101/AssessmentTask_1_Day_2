package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exception.CategoryNotFoundException;
import com.example.model.SubCategory;
import com.example.repository.CategoryRepository;
import com.example.repository.SubCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired 
    CategoryRepository categoryRepository;

    public SubCategory saveSubCategory(SubCategory subCategory) {
        if(!categoryRepository.findAll().contains(subCategory.getCategory().getCategoryName())){
            throw new CategoryNotFoundException("Category is Not present");
        }
        return subCategoryRepository.save(subCategory);
    }


    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }


    public SubCategory getSubCategoryById(Long id) {
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        return optionalSubCategory.orElse(null);
    }

    public SubCategory updateSubCategory(Long id, SubCategory subCategory) {
        Optional<SubCategory> optionalExistingSubCategory = subCategoryRepository.findById(id);
        if (optionalExistingSubCategory.isPresent()) {
            SubCategory existingSubCategory = optionalExistingSubCategory.get();
            existingSubCategory.setSubCategoryName(subCategory.getSubCategoryName());
            existingSubCategory.setSubCategoryDescription(subCategory.getSubCategoryDescription());
            existingSubCategory.setCategory(subCategory.getCategory());
            return subCategoryRepository.save(existingSubCategory);
        } else {
            return null;
        }
    }

    public boolean deleteSubCategory(Long id) {
        if (subCategoryRepository.existsById(id)) {
            subCategoryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
