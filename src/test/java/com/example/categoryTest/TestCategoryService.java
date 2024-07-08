package com.example.categoryTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.exception.CategoryNotFoundException;
import com.example.exception.CategroyIsAlreadyPresent;
import com.example.model.Category;
import com.example.repository.CategoryRepository;
import com.example.service.Impl.CategoryServiceImpl;

class TestCategoryService {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category(1L, "TestCategory", "Test Description");
    }

    @Test
    void testSaveCategory() throws CategroyIsAlreadyPresent {
    
        when(categoryRepository.save(category)).thenReturn(category);

        
        Category savedCategory = categoryService.saveCategory(category);

    
        assertNotNull(savedCategory);
        assertEquals("TestCategory", savedCategory.getCategoryName());
        assertEquals("Test Description", savedCategory.getCategoryDescription());
    }

    @Test
    void testGetCategory() throws CategoryNotFoundException {
        
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        when(categoryRepository.findAll()).thenReturn(categories);

        
        List<Category> retrievedCategories = categoryService.getCategory();

        
        assertNotNull(retrievedCategories);
        assertEquals(1, retrievedCategories.size());
        assertEquals("TestCategory", retrievedCategories.get(0).getCategoryName());
    }

    @Test
    void testUpdateCategory_Success() throws CategoryNotFoundException {
      
        Category category1 = new Category();
        category1.setCategoryId(1L);
        category1.setCategoryName("Updated Category");
        category1.setCategoryDescription("Updated Description");


        when(categoryRepository.findById(1L)).thenReturn(Optional.of(new Category())); 
        when(categoryRepository.save(any())).thenReturn(category1);

        ResponseEntity<Category> responseEntity = categoryService.updateCategory(category1);

    
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals("Updated Category", responseEntity.getBody().getCategoryName());
    }

    @Test
    void testDeleteCategory_Success() {
        Long categoryId = 1L;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(new Category()));

        assertDoesNotThrow(() -> categoryService.deleteCategory(categoryId));
        
        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

        
}
    
