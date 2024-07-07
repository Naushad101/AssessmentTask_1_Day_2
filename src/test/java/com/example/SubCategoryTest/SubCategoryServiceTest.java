package com.example.SubCategoryTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.exception.CategoryNotFoundException;
import com.example.exception.SubCategoryIsAlreadyPresent;
import com.example.exception.SubCategoryNotFoundException;
import com.example.model.Category;
import com.example.model.SubCategory;
import com.example.repository.CategoryRepository;
import com.example.repository.SubCategoryRepository;
import com.example.service.SubCategoryService;

public class SubCategoryServiceTest {

    private SubCategoryService subCategoryService;
    private SubCategoryRepository subCategoryRepository;
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        subCategoryRepository = mock(SubCategoryRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        subCategoryService = new SubCategoryService(subCategoryRepository, categoryRepository);
    }

   @Test
void testSaveSubCategory_Success() throws SubCategoryIsAlreadyPresent, CategoryNotFoundException {
  
    Category category = new Category();
    category.setCategoryName("Test Category");

    
    SubCategory subCategory = new SubCategory();
    subCategory.setSubCategoryName("Test SubCategory");
    subCategory.setCategory(category); 

    
    when(subCategoryRepository.findBySubCategoryName(subCategory.getSubCategoryName())).thenReturn(Optional.empty());
    when(categoryRepository.findByCategoryName(category.getCategoryName())).thenReturn(Optional.of(category));

    
    when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);

    
    ResponseEntity<SubCategory> responseEntity = subCategoryService.saveSubCategory(subCategory);

    
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals("Test SubCategory", responseEntity.getBody().getSubCategoryName());
    verify(subCategoryRepository, times(1)).save(subCategory);
}


    @Test
    void testSaveSubCategory_SubCategoryAlreadyExists() {
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryName("Test SubCategory");

        when(subCategoryRepository.findBySubCategoryName(subCategory.getSubCategoryName())).thenReturn(Optional.of(subCategory));

        SubCategoryIsAlreadyPresent exception = assertThrows(SubCategoryIsAlreadyPresent.class, () -> subCategoryService.saveSubCategory(subCategory));
        assertEquals("Subcategory " + subCategory.getSubCategoryName() + " is already present", exception.getMessage());

        verify(subCategoryRepository, never()).save(any());
    }

    @Test
    void testSaveSubCategory_CategoryNotFound() {

        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryName("Test SubCategory");
        subCategory.setCategory(new Category());
        subCategory.getCategory().setCategoryName("Nonexistent Category");

        when(subCategoryRepository.findBySubCategoryName(subCategory.getSubCategoryName())).thenReturn(Optional.empty());
        when(categoryRepository.findByCategoryName(subCategory.getCategory().getCategoryName())).thenReturn(Optional.empty());

        CategoryNotFoundException exception = assertThrows(CategoryNotFoundException.class, () -> subCategoryService.saveSubCategory(subCategory));
        assertEquals("Category " + subCategory.getCategory().getCategoryName() + " is not present in database", exception.getMessage());

        verify(subCategoryRepository, never()).save(any());
    }

    @Test
    void testGetAllSubCategories_Success() throws SubCategoryNotFoundException {
        List<SubCategory> subCategories = Collections.singletonList(new SubCategory());

        when(subCategoryRepository.findAll()).thenReturn(subCategories);

        List<SubCategory> result = subCategoryService.getAllSubCategories();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(subCategoryRepository, times(1)).findAll();
    }

    @Test
    void testGetSubCategoryById_Success() throws SubCategoryNotFoundException {
       
        Long subCategoryId = 1L;

        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryId(subCategoryId);
        subCategory.setSubCategoryName("Test SubCategory");


        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.of(subCategory));

        ResponseEntity<SubCategory> responseEntity = subCategoryService.getSubCategoryById(subCategoryId);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals("Test SubCategory", responseEntity.getBody().getSubCategoryName());
        verify(subCategoryRepository, times(1)).findById(subCategoryId);
    }

    @Test
    void testGetSubCategoryById_SubCategoryNotFound() {
        
        Long subCategoryId = 1L;

        
        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.empty());

   
        SubCategoryNotFoundException exception = assertThrows(SubCategoryNotFoundException.class, () -> subCategoryService.getSubCategoryById(subCategoryId));
        assertEquals("SubCategory with id " + subCategoryId + " is not present in database", exception.getMessage());

        
        verify(subCategoryRepository, times(1)).findById(subCategoryId);
    }

    @Test
    public void testUpdateSubCategory_Success() throws SubCategoryNotFoundException {
     
        Long subCategoryId = 1L;

     
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryId(subCategoryId);
        subCategory.setSubCategoryName("Updated SubCategory");

        
        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.of(new SubCategory()));

        
        when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);

        
        ResponseEntity<SubCategory> responseEntity = subCategoryService.updateSubCategory(subCategoryId, subCategory);

       
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals("Updated SubCategory", responseEntity.getBody().getSubCategoryName());
        verify(subCategoryRepository, times(1)).findById(subCategoryId);
        verify(subCategoryRepository, times(1)).save(subCategory);
    }

    @Test
    void testUpdateSubCategory_SubCategoryNotFound() {
       
        Long subCategoryId = 1L;

       
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryId(subCategoryId);
        subCategory.setSubCategoryName("Updated SubCategory");

       
        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.empty());

       
        SubCategoryNotFoundException exception = assertThrows(SubCategoryNotFoundException.class, () -> subCategoryService.updateSubCategory(subCategoryId, subCategory));
        assertEquals("SubCategory with id " + subCategoryId + " is not present in database", exception.getMessage());

        verify(subCategoryRepository, never()).save(any());
    }

    @Test
    void testDeleteSubCategory_Success() throws SubCategoryNotFoundException {
       
        Long subCategoryId = 1L;

       
        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.of(new SubCategory()));

      
        assertDoesNotThrow(() -> subCategoryService.deleteSubCategory(subCategoryId));

      
        verify(subCategoryRepository, times(1)).deleteById(subCategoryId);
    }

    @Test
    void testDeleteSubCategory_SubCategoryNotFound() {
        
        Long subCategoryId = 1L;

       
        when(subCategoryRepository.findById(subCategoryId)).thenReturn(Optional.empty());

       
        SubCategoryNotFoundException exception = assertThrows(SubCategoryNotFoundException.class, () -> subCategoryService.deleteSubCategory(subCategoryId));
        assertEquals("Subcategory with id " + subCategoryId + " is not present in database", exception.getMessage());

       
        verify(subCategoryRepository, never()).deleteById(any());
    }
}
