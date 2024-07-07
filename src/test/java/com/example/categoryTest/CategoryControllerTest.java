package com.example.categoryTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.controller.CategoryController;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.CategroyIsAlreadyPresent;
import com.example.model.Category;
import com.example.service.CategoryService;

public class CategoryControllerTest {

    private CategoryController categoryController;
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        categoryService = mock(CategoryService.class);
        categoryController = new CategoryController(categoryService);
    }

    @Test
    void testSaveCategory() throws CategroyIsAlreadyPresent {
        
        Category category = new Category();
        category.setCategoryName("Test Category");

        
        when(categoryService.saveCategory(category)).thenReturn(category);

    
        ResponseEntity<Category> responseEntity = categoryController.saveCategory(category);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Test Category", responseEntity.getBody().getCategoryName());
        verify(categoryService, times(1)).saveCategory(category);
    }

    @Test
    void testGetCategory() throws CategoryNotFoundException {
        List<Category> categories = List.of(new Category(), new Category());

        when(categoryService.getCategory()).thenReturn(categories);

      
        ResponseEntity<List<Category>> responseEntity = categoryController.getCategory();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        verify(categoryService, times(1)).getCategory();
    }

    @Test
    void testUpdateCategory() throws CategoryNotFoundException {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("Updated Category");

       
        when(categoryService.updateCategory(category)).thenReturn(new ResponseEntity<>(category, HttpStatus.ACCEPTED));

        
        ResponseEntity<Category> responseEntity = categoryController.updateCategory(category);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals("Updated Category", responseEntity.getBody().getCategoryName());
        verify(categoryService, times(1)).updateCategory(category);
    }

    @Test
    void testDeleteCategory() throws CategoryNotFoundException {
        Long categoryId = 1L;

       
        assertDoesNotThrow(() -> categoryController.deleteCategory(categoryId));

        verify(categoryService, times(1)).deleteCategory(categoryId);
    }
}
