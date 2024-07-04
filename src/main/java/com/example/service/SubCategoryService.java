package com.example.service;
import org.hibernate.mapping.Subclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exception.CategoryNotFoundException;
import com.example.exception.SubCategoryIsAlreadyPresent;
import com.example.exception.SubCategoryNotFoundException;
import com.example.model.Category;
import com.example.model.SubCategory;
import com.example.repository.CategoryRepository;
import com.example.repository.SubCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    private SubCategoryRepository subCategoryRepository;
    private CategoryRepository categoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository,CategoryRepository categoryRepository){
        this.subCategoryRepository=subCategoryRepository;
        this.categoryRepository=categoryRepository;
    }

    
    public ResponseEntity<SubCategory> saveSubCategory(SubCategory subCategory) throws SubCategoryIsAlreadyPresent,CategoryNotFoundException{
        if(subCategoryRepository.findBySubCategoryName(subCategory.getSubCategoryName()).isPresent()){
            throw new SubCategoryIsAlreadyPresent("Subcategory "+ subCategory.getSubCategoryName()+ " is already present");
        }

        if(categoryRepository.findByCategoryName(subCategory.getCategory().getCategoryName()).isEmpty()){
            throw new CategoryNotFoundException("Category " + subCategory.getCategory().getCategoryName() + " is not present in database");
        }
        return new ResponseEntity<>(subCategoryRepository.save(subCategory),HttpStatus.CREATED);
    }


    public List<SubCategory> getAllSubCategories() throws SubCategoryNotFoundException{
        try{
            return subCategoryRepository.findAll();
        }
        catch(DataAccessException e){
            throw new SubCategoryNotFoundException("Failed to retrieve categories from the database "+e);
        }
    }


    public ResponseEntity<SubCategory> getSubCategoryById(Long id) throws SubCategoryNotFoundException {
        if(subCategoryRepository.findById(id).isEmpty()){
            throw new SubCategoryNotFoundException("SubCategory with " +id+ " is not present in database");
        }
        return new ResponseEntity<>(subCategoryRepository.findById(id).orElseThrow(),HttpStatus.FOUND);
    }

    public ResponseEntity<SubCategory> updateSubCategory(Long id, SubCategory subCategory) throws SubCategoryNotFoundException {
        if(subCategoryRepository.findById(id).isEmpty()){
            throw new SubCategoryNotFoundException("SubCategory with " +id + " is not present in database");
        }
        SubCategory existingSubCategory = subCategoryRepository.findById(id).orElseThrow();
     
        existingSubCategory.setSubCategoryName(subCategory.getSubCategoryName());
        existingSubCategory.setSubCategoryDescription(subCategory.getSubCategoryDescription());
        existingSubCategory.setSubCategoryId(id);
        existingSubCategory.setCategory(existingSubCategory.getCategory());
        return new ResponseEntity<>(subCategoryRepository.save(existingSubCategory),HttpStatus.ACCEPTED);

    }

    public void deleteSubCategory(Long id) throws SubCategoryNotFoundException{
       if(subCategoryRepository.findById(id).isEmpty()){
            throw new SubCategoryNotFoundException("Subcategory with id " +id+ " is not present in database");
       }
       subCategoryRepository.deleteById(id);
    }
}
