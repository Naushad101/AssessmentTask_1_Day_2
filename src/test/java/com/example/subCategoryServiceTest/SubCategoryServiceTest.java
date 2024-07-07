// package com.example.subCategoryServiceTest;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.example.model.Category;
// import com.example.model.SubCategory;
// import com.example.repository.SubCategoryRepository;
// import com.example.service.SubCategoryService;

// @ExtendWith(MockitoExtension.class)
// public class SubCategoryServiceTest {

//     @Mock
//     private SubCategoryRepository subCategoryRepository;

//     @InjectMocks
//     private SubCategoryService subCategoryService;

//     private SubCategory subCategory;

//     @BeforeEach
//     public void setUp() {
//         subCategory = new SubCategory();
//         subCategory.setSubCategoryId(1L);
//         subCategory.setSubCategoryName("Test SubCategory");
//         subCategory.setSubCategoryDescription("Description");
//         subCategory.setCategory(new Category(1L, "TestCategory", "Test Description"));
//     }

//     @Test
//     public void testSaveSubCategory() {
      
//         when(subCategoryRepository.save(subCategory)).thenReturn(subCategory);

       
//         SubCategory savedSubCategory = subCategoryService.saveSubCategory(subCategory);

//         assertNotNull(savedSubCategory);
//         assertEquals("Test SubCategory", savedSubCategory.getSubCategoryName());
//     }

//     @Test
//     public void testGetAllSubCategories() {
//         List<SubCategory> subCategories = new ArrayList<>();
//         subCategories.add(subCategory);
//         when(subCategoryRepository.findAll()).thenReturn(subCategories);

       
//         List<SubCategory> retrievedSubCategories = subCategoryService.getAllSubCategories();

       
//         assertNotNull(retrievedSubCategories);
//         assertEquals(1, retrievedSubCategories.size());
//         assertEquals("Test SubCategory", retrievedSubCategories.get(0).getSubCategoryName());
//     }

//     @Test
//     public void testGetSubCategoryById() {
        
//         when(subCategoryRepository.findById(1L)).thenReturn(Optional.of(subCategory));

    
//         SubCategory retrievedSubCategory = subCategoryService.getSubCategoryById(1L);

        
//         assertNotNull(retrievedSubCategory);
//         assertEquals("Test SubCategory", retrievedSubCategory.getSubCategoryName());
//     }

//     @Test
//     public void testUpdateSubCategory() {
//         String updatedName = "Updated SubCategory";
//         subCategory.setSubCategoryName(updatedName);
//         when(subCategoryRepository.findById(1L)).thenReturn(Optional.of(subCategory));
//         when(subCategoryRepository.save(any())).thenReturn(subCategory);

//         SubCategory updated = new SubCategory();
//         updated.setSubCategoryName(updatedName);
//         SubCategory updatedSubCategory = subCategoryService.updateSubCategory(1L, updated);

        
//         assertNotNull(updatedSubCategory);
//         assertEquals(updatedName, updatedSubCategory.getSubCategoryName());
//     }

//     @Test
//     public void testDeleteSubCategory() {
        
//         when(subCategoryRepository.existsById(1L)).thenReturn(true);

        
//         boolean result = subCategoryService.deleteSubCategory(1L);

        
//         assertTrue(result);
//         verify(subCategoryRepository, times(1)).deleteById(1L);
//     }
// }

