package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category saveCategory(Category category){
       return categoryRepository.save(category);
    }

    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Long id,String categoryName,String categoryDescription) {
                categoryRepository.updateCategory(id,categoryName,categoryDescription);
                return new Category(id,categoryName,categoryDescription);
    }

    public void deleteCategory(Long id) {
       categoryRepository.deleteById(id);
    }
}
