package com.example.SubCategoryTest;

import com.example.controller.SubCategoryController;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.SubCategoryIsAlreadyPresent;
import com.example.exception.SubCategoryNotFoundException;
import com.example.model.Category;
import com.example.model.SubCategory;
import com.example.service.SubCategoryService;
import com.example.service.Impl.SubCategoryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SubCategoryControllerTest {

    @Mock
    private SubCategoryServiceImpl subCategoryService;

    @InjectMocks
    private SubCategoryController subCategoryController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSubCategory_Success() throws SubCategoryIsAlreadyPresent, CategoryNotFoundException {
       
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryName("Test SubCategory");

    
        when(subCategoryService.saveSubCategory(subCategory)).thenReturn(new ResponseEntity<>(subCategory, HttpStatus.CREATED));

        
        ResponseEntity<SubCategory> responseEntity = subCategoryController.saveSubCategory(subCategory);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Test SubCategory", responseEntity.getBody().getSubCategoryName());
        verify(subCategoryService, times(1)).saveSubCategory(subCategory);
    }

    @Test
    void testGetAllSubCategories_Success() throws SubCategoryNotFoundException {
        
        List<SubCategory> subCategories = new ArrayList<>();
        subCategories.add(new SubCategory(1L, "Test SubCategory 1","SubCategoryDesc", new Category()));
        subCategories.add(new SubCategory(2L, "Test SubCategory 2","SubCategoryDescription", new Category()));

        
        when(subCategoryService.getAllSubCategories()).thenReturn(subCategories);

       
        ResponseEntity<List<SubCategory>> responseEntity = subCategoryController.getAllSubCategories();

    
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        verify(subCategoryService, times(1)).getAllSubCategories();
    }

    @Test
    void testGetSubCategoryById_Success() throws SubCategoryNotFoundException {
        
        SubCategory subCategory = new SubCategory(1L, "Test SubCategory", "SubCategoryDescription",new Category());

       
        when(subCategoryService.getSubCategoryById(1L)).thenReturn(new ResponseEntity<>(subCategory, HttpStatus.FOUND));

       
        ResponseEntity<SubCategory> responseEntity = subCategoryController.getSubCategoryById(1L);

        
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals("Test SubCategory", responseEntity.getBody().getSubCategoryName());
        verify(subCategoryService, times(1)).getSubCategoryById(1L);
    }

    @Test
    void testUpdateSubCategory_Success() throws SubCategoryNotFoundException {
        
        SubCategory subCategory = new SubCategory(1L, "Test SubCategory", "SubCategoryDescription",new Category());

       
        when(subCategoryService.updateSubCategory(1L, subCategory)).thenReturn(new ResponseEntity<>(subCategory, HttpStatus.ACCEPTED));

       
        ResponseEntity<SubCategory> responseEntity = subCategoryController.updateSubCategory(1L, subCategory);

      
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals("Test SubCategory", responseEntity.getBody().getSubCategoryName());
        verify(subCategoryService, times(1)).updateSubCategory(1L, subCategory);
    }

    @Test
    void testDeleteSubCategory_Success() throws SubCategoryNotFoundException {
        
        subCategoryController.deleteSubCategory(1L);

        verify(subCategoryService, times(1)).deleteSubCategory(1L);
    }
}

