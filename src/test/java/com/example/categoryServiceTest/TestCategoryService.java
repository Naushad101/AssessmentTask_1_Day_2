package com.example.categoryServiceTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.model.Category;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class TestCategoryService {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category(1L, "TestCategory", "Test Description");
    }

    @Test
    public void testSaveCategory() {
    
        when(categoryRepository.save(category)).thenReturn(category);

        
        Category savedCategory = categoryService.saveCategory(category);

    
        assertNotNull(savedCategory);
        assertEquals("TestCategory", savedCategory.getCategoryName());
        assertEquals("Test Description", savedCategory.getCategoryDescription());
    }

    @Test
    public void testGetCategory() {
        
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        when(categoryRepository.findAll()).thenReturn(categories);

        
        List<Category> retrievedCategories = categoryService.getCategory();

        
        assertNotNull(retrievedCategories);
        assertEquals(1, retrievedCategories.size());
        assertEquals("TestCategory", retrievedCategories.get(0).getCategoryName());
    }

    @Test
    public void testUpdateCategory() {
        
        String updatedName = "UpdatedTestCategory";
        String updatedDescription = "Updated Test Description";

       
        Category updatedCategory = categoryService.updateCategory(1L, updatedName, updatedDescription);

        
        assertNotNull(updatedCategory);
        assertEquals(updatedName, updatedCategory.getCategoryName());
        assertEquals(updatedDescription, updatedCategory.getCategoryDescription());
    }

    @Test
    public void testDeleteCategory() {
       
        categoryService.deleteCategory(1L);

       
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
