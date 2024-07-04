package com.example.service;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.SubCategoryIsAlreadyPresent;
import com.example.exception.SubCategoryNotFoundException;
import com.example.model.SubCategory;
import com.example.repository.CategoryRepository;
import com.example.repository.SubCategoryRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SubCategoryService {


    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<SubCategory> saveSubCategory(SubCategory subCategory) throws SubCategoryIsAlreadyPresent, CategoryNotFoundException {
        log.info("Saving subcategory: {}", subCategory.getSubCategoryName());

        if (subCategoryRepository.findBySubCategoryName(subCategory.getSubCategoryName()).isPresent()) {
            String message = "Subcategory " + subCategory.getSubCategoryName() + " is already present";
            log.error(message);
            throw new SubCategoryIsAlreadyPresent(message);
        }

        if (categoryRepository.findByCategoryName(subCategory.getCategory().getCategoryName()).isEmpty()) {
            String message = "Category " + subCategory.getCategory().getCategoryName() + " is not present in database";
            log.error(message);
            throw new CategoryNotFoundException(message);
        }

        ResponseEntity<SubCategory> responseEntity = new ResponseEntity<>(subCategoryRepository.save(subCategory), HttpStatus.CREATED);
        log.info("Saved subcategory: {}", subCategory.getSubCategoryName());
        return responseEntity;
    }

    public List<SubCategory> getAllSubCategories() throws SubCategoryNotFoundException {
        log.info("Fetching all subcategories");

        try {
            List<SubCategory> subCategories = subCategoryRepository.findAll();
            log.info("Fetched {} subcategories", subCategories.size());
            return subCategories;
        } catch (DataAccessException e) {
            String message = "Failed to retrieve subcategories from the database";
            log.error(message, e);
            throw new SubCategoryNotFoundException(message+" "+e);
        }
    }

    public ResponseEntity<SubCategory> getSubCategoryById(Long id) throws SubCategoryNotFoundException {
        log.info("Fetching subcategory with id: {}", id);

        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        if (optionalSubCategory.isEmpty()) {
            String message = "SubCategory with id " + id + " is not present in database";
            log.error(message);
            throw new SubCategoryNotFoundException(message);
        }

        ResponseEntity<SubCategory> responseEntity = new ResponseEntity<>(optionalSubCategory.get(), HttpStatus.FOUND);
        log.info("Fetched subcategory with id {}: {}", id, responseEntity.getBody().getSubCategoryName());
        return responseEntity;
    }

    public ResponseEntity<SubCategory> updateSubCategory(Long id, SubCategory subCategory) throws SubCategoryNotFoundException {
        log.info("Updating subcategory with id: {}", id);

        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        if (optionalSubCategory.isEmpty()) {
            String message = "SubCategory with id " + id + " is not present in database";
            log.error(message);
            throw new SubCategoryNotFoundException(message);
        }

        SubCategory existingSubCategory = optionalSubCategory.get();
        existingSubCategory.setSubCategoryName(subCategory.getSubCategoryName());
        existingSubCategory.setSubCategoryDescription(subCategory.getSubCategoryDescription());
        existingSubCategory.setSubCategoryId(id);
        existingSubCategory.setCategory(existingSubCategory.getCategory());

        ResponseEntity<SubCategory> responseEntity = new ResponseEntity<>(subCategoryRepository.save(existingSubCategory), HttpStatus.ACCEPTED);
        log.info("Updated subcategory with id {}: {}", id, existingSubCategory.getSubCategoryName());
        return responseEntity;
    }

    public void deleteSubCategory(Long id) throws SubCategoryNotFoundException {
        log.info("Deleting subcategory with id: {}", id);

        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        if (optionalSubCategory.isEmpty()) {
            String message = "Subcategory with id " + id + " is not present in database";
            log.error(message);
            throw new SubCategoryNotFoundException(message);
        }

        subCategoryRepository.deleteById(id);
        log.info("Deleted subcategory with id: {}", id);
    }
}
