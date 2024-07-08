package com.example.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.model.SubCategory;
import java.util.List;

@Service
public interface SubCategoryService {

    public ResponseEntity<SubCategory> saveSubCategory(SubCategory subCategory);

    public List<SubCategory> getAllSubCategories();

    public ResponseEntity<SubCategory> getSubCategoryById(Long id);
    
    public ResponseEntity<SubCategory> updateSubCategory(Long id, SubCategory subCategory);

    public void deleteSubCategory(Long id);
}
